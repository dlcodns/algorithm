import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        writer.write("정렬할 수들을 입력하세요. >>> ");
        writer.flush();
        //input 배열에 readline()해서 " "로 잘라서 저장
        String[] input = reader.readLine().split(" ");

        //int형인 arr 배열에 input 개수만큼 int 초기화 생성
        int[] arr = new int[input.length];
        for (int i = 0; i<input.length; i++)
            //Integer.parseInt: 문자열을 파싱하여 정수로 바꾸는 메서드
            arr[i] = Integer.parseInt(input[i]);

        writer.write("원래 배열: ");
        printfunc(writer, arr);

        insertionSort(arr);

        writer.write("삽입 정렬 후 배열: ");
        printfunc(writer, arr);
    }

    public static void insertionSort(int[] arr){
        int n = arr.length;
        int key, j;
        for(int i=1; i<n; i++){
            key = arr[i];
            for(j=i-1; j>=0 && arr[j]>key; j--) arr[j+1] = arr[j];
            arr[j+1] = key;
        }
    }

    public static void printfunc(BufferedWriter writer, int[] arr) throws IOException{
        for (int j : arr) writer.write(j + " ");
        writer.write("\n\n");
        writer.flush();
    }
}
