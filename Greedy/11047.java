import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //n과 k를 띄어쓰기로 나누어 배열로 만들기
        String[] firstLine = br.readLine().split(" ");
        int N = Integer.parseInt(firstLine[0]);
        int K = Integer.parseInt(firstLine[1]);

        int[] A = new int[N];

        //배열 A를 만들고 for문으로 한 줄씩 읽기
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(br.readLine());
        }

        int count=0;

        //A를 큰 수부터 -1씩 하면서 불러오기
        for (int i = N-1;i>=0;i--){
            //젤 먼저 K보다 작은 숫자가 나오면 걜 상대로
            if (A[i] <= K){
                //K를 A[i]로 나눈 값을 count에 더하기
                count += K / A[i];
                //K에 위 식의 나머지를 넣음
                K %= A[i];
            }
        }
        System.out.println(count);
    }
}