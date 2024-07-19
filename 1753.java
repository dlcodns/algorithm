//다익스트라
import java.util.*;

public class Main {
    //간선 만들기
    static class Edge {
        int to, weight;
        Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        //입력받기 정점, 간선, 시작 정점
        int V = sc.nextInt();
        int E = sc.nextInt();

        int K = sc.nextInt();

        //인접 리스트로 표현할 리스트 생성 - 초기화
        List<List<Edge>> graph = new ArrayList<>();
        for (int i = 0; i <= V; i++) {
            graph.add(new ArrayList<>());
        }

        //입력 받기 출발지, 도착지, 간선 가중치
        for (int i = 0; i < E; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int w = sc.nextInt();
            graph.get(u).add(new Edge(v, w));
        }

        //다익스트라 알고리즘
        int[] distances = dijkstra(V, graph, K);

        //만약 INF(MAX)면 INF 출력
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= V; i++) {
            if (distances[i] == INF) {
                sb.append("INF\n");
            } else {
                sb.append(distances[i]).append("\n");
            }
        }
        System.out.print(sb.toString());
    }

    //다익스트라
    private static int[] dijkstra(int V, List<List<Edge>> graph, int start) {
        int[] distances = new int[V + 1];
        //모든 거리를 무한대 초기화
        Arrays.fill(distances, INF);
        //시작 정점은 0
        distances[start] = 0;

        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(e -> e.weight));
        //시작 정점을 큐에 추가
        pq.add(new Edge(start, 0));

        //pq가 빌 때까지
        while (!pq.isEmpty()) {
            //가장 거리 짧은 정점 꺼내기
            Edge current = pq.poll();
            int currentNode = current.to;
            int currentDist = current.weight;

            //이미 알고있는 최단 거리보다 해당 노드까지의 거리가 더 길면 넘어가기
            if (currentDist > distances[currentNode]) {
                continue;
            }

            for (Edge edge : graph.get(currentNode)) {
                //currentnode를 가지고 넥스트 노드 거리 비교
                int nextNode = edge.to;
                int nextDist = currentDist + edge.weight;

                //더 짧은 거리로 업데이트
                if (nextDist < distances[nextNode]) {
                    distances[nextNode] = nextDist;
                    pq.add(new Edge(nextNode, nextDist));
                }
            }
        }

        return distances;
    }
}
