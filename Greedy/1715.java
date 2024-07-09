import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //첫째 줄에 최소 비교 횟수 읽기
        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        //카드 묶음을 읽고 우선순위 큐에 삽입
        //우선순위 큐: 항상 최솟값을 추출
        for (int i = 0; i < N; i++) {
            int size = Integer.parseInt(br.readLine());
            pq.add(size);
        }

        int total = 0;

        //가장 작은 두 묶음을 합치는 과정 반복
        while (pq.size() > 1) {
            int first = pq.poll();
            int second = pq.poll();
            int sum = first + second;
            total += sum;
            pq.add(sum);
        }
        System.out.println(total);
    }
}
