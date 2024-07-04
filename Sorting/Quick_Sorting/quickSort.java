import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        writer.write("정렬할 수들을 입력하세요. >>> ");
        writer.flush();
        String[] input = reader.readLine().split(" ");

        int[] arr = new int[input.length];
        for (int i=0;i<input.length;i++)
            arr[i] = Integer.parseInt(input[i]);

        writer.write("원래 배열: ");
        printfunc(writer,arr);

        quickSort(arr,0, arr.length-1);

        writer.write("퀵 정렬 후 배열: ");
        printfunc(writer,arr);
    }

    public static void quickSort(int[] arr, int p, int q){
        if (p<q){
            int pi = partition(arr,p,q);

            quickSort(arr,p,pi-1);
            quickSort(arr,pi+1,q);
        }
    }

    public static int partition(int[] arr, int p, int q){
        int pivot = arr[q];
        int i = p-1;

        for (int j = p; j<q;j++){
            if (arr[j] < pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        int temp = arr[i+1];
        arr[i+1] = arr[q];
        arr[q] = temp;

        return i+1;
    }

    public static void printfunc(BufferedWriter writer, int[] arr) throws IOException{
        for (int j : arr) writer.write(j+" ");
        writer.write("\n\n");
        writer.flush();
    }

}
