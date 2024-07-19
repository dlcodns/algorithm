//벨만 포드 알고리즘 타임머신
import java.util.*;

public class Main {
    //간선 클래스 목적지 가중치
    static class Edge {
        int from, to, weight;
        Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }

    static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        //입력 도시, 간선
        int N = sc.nextInt();
        int M = sc.nextInt();

        //간선 리스트 초기화, 입력 받기 시작, 도착, 간선 가중치
        List<Edge> edges = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            int from = sc.nextInt();
            int to = sc.nextInt();
            int weight = sc.nextInt();
            edges.add(new Edge(from, to, weight));
        }

        //거리 배열
        long[] dist = new long[N + 1];
        //무한대 초기화: 무한대면 -1 출력하게
        Arrays.fill(dist, INF);
        //시작 도시 거리 0 설정
        dist[1] = 0;

        //타임머신 음수
        boolean hasNegativeCycle = false;

        //벨만 포드 알고리즘
        for (int i = 1; i <= N; i++) {
            for (Edge edge : edges) {
                //시작 도시까지 도달 가능하고 여기서 시작도시에서 도착도시로
                // 가는 경로가 원래 아는 경로보다 더 짧으면 거리 갱신
                if (dist[edge.from] != INF && dist[edge.from] + edge.weight < dist[edge.to]) {
                    dist[edge.to] = dist[edge.from] + edge.weight;
                    if (i == N) {
                        //n번째 반복에서 갱신이 반복되면 음수 사이클이 
                        // 계속 되는 것이므로 true
                        hasNegativeCycle = true;
                    }
                }
            }
        }

        //출력
        if (hasNegativeCycle) {
            System.out.println(-1);
        } else {
            StringBuilder sb = new StringBuilder();
            for (int i = 2; i <= N; i++) {
                if (dist[i] == INF) {
                    sb.append("-1\n");
                } else {
                    sb.append(dist[i]).append("\n");
                }
            }
            System.out.print(sb.toString());
        }
    }
}
