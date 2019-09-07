import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String result = "";

        int n = scanner.nextInt();

        int[] array = new int[100001];
        array[0] = 100001;
        scanner.nextLine();

        for (int i = 0; i < n; i++){
            String line = scanner.nextLine();

            if (line.charAt(0) == 'I'){
                line = line.substring(7);
                int add = Integer.parseInt(line);

                insert(array, add, i + 1);
            }

            else{

                result += getMax(array, i + 1) + "\n";
            }
        }
        System.out.println(result);
    }

    public static void insert(int[] array, int add, int i){

        while (array[i] != 0){
            i--;
        }
        array[i] = add;

        while (array[i] > array[i / 2]){
            int swap = array[i / 2];
            array[i] = swap;
            array[i / 2] = add;

            i /= 2;
            if (i == 1)
                break;
        }
    }

    public static int getMax(int[] array, int i){
        int max = array[1];
        int last;
        while (array[i] == 0){
            i--;
        }
        last = array[i];

        array[i] = 0;

        array[1] = last;
        if (array[2] == 0) {
            return max;
        }

        if (array[3] == 0){
            if (array[1] < array[2]){
                int swap = array[1];
                array[1] = array[2];
                array[2] = swap;
            }
            return max;
        }

        if (array[2] >= array[3]){
            array[1] = array[2];
            array[2] = last;
            i = 2;
        }
        else{
            array[1] = array[3];
            array[3] = last;
            i = 3;
        }

        if (array[2 * i] == 0)
            return max;

        int parent = array[i];
        int leftChild = array[2 * i];
        int rightChild;

        if (array[2 * i + 1] == 0) {
            rightChild = -1;
        }
        else{
            rightChild = array[2 * i + 1];
        }

        while (parent < leftChild || parent < rightChild){

            if (leftChild >= rightChild){
                array[i] = leftChild;
                array[2 * i] = last;
                i = 2 * i;
            }
            else
            {
                array[i] = rightChild;
                array[2 * i + 1] = last;
                i = 2 * i + 1;
            }

            if (array[2 * i + 1] == 0)
                break;
            if (array[2 * i] == 0) {
                leftChild = array[2 * i];
                rightChild = -1;
            }
            else{
                leftChild = array[2 * i];
                rightChild = array[2 * i + 1];
            }
        }

        return max;
    }
}
