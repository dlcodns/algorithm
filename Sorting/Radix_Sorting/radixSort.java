import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;

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

        radixSort(arr);

        writer.write("퀵 정렬 후 배열: ");
        printfunc(writer,arr);
    }

    public static void radixSort(int[] arr){
        int max = Arrays.stream(arr).max().getAsInt();
        for(int exp = 1; max / exp > 0; exp *= 10){
            countingSortByDigit(arr,exp);
        }
    }

    public static void countingSortByDigit(int[] arr, int exp) {
        int n = arr.length;
        int[] output = new int[n];
        int[] count = new int[10];
        Arrays.fill(count, 0);

        for (int i = 0; i < n; i++) {
            int digit = (arr[i] / exp) % 10;
            count[digit]++;
        }

        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        for (int i = n - 1; i >= 0; i--) {
            int digit = (arr[i] / exp) % 10;
            output[count[digit] - 1] = arr[i];
            count[digit]--;
        }

        System.arraycopy(output, 0, arr, 0, n);
    }


    public static void printfunc(BufferedWriter writer, int[] arr) throws IOException{
        for (int j : arr) writer.write(j+" ");
        writer.write("\n\n");
        writer.flush();
    }

}
