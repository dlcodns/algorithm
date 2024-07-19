//1에서 k번째 최단 경로 다익스트라
import java.util.*;

public class Main {
    //간선 클래스 목적지 가중치
    static class Edge {
        int to, weight;
        Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    //도시와 거리 노드 클래스(큐로 쓸거임
    static class Node implements Comparable<Node> {
        int city, dist;
        Node(int city, int dist) {
            this.city = city;
            this.dist = dist;
        }

        public int compareTo(Node other) {
            return Integer.compare(this.dist, other.dist);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        //입력 받기 도시, 간선, k번째 경로
        int n = sc.nextInt();
        int m = sc.nextInt();
        int k = sc.nextInt();

        //인접 리스트로 표현할 리스트 생성 - 초기화
        List<List<Edge>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        //입력 받기 출발지, 도착지, 간선 가중치
        for (int i = 0; i < m; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int w = sc.nextInt();
            graph.get(u).add(new Edge(v, w));
        }

        //다익스트라
        //k 번째 최단경로 저장할 우선순위 큐 배열: 이 코드에는 큐가 두 개
        PriorityQueue<Integer>[] distances = new PriorityQueue[n + 1];
        for (int i = 1; i <= n; i++) {
            distances[i] = new PriorityQueue<>(k, Collections.reverseOrder());
        }
        //시작은 거리 0 설정
        distances[1].add(0);

        //이건 최단 경로 큐 선언
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(1, 0));

        while (!pq.isEmpty()) {
            //큐에서 가장 짧은 거리를 가진 노드 꺼내기
            Node current = pq.poll();
            int currentCity = current.city;
            int currentDist = current.dist;

            for (Edge edge : graph.get(currentCity)) {
                int nextCity = edge.to;
                int nextDist = currentDist + edge.weight;

                //k개가 채워지지 않은 경우 추가하기
                if (distances[nextCity].size() < k) {
                    distances[nextCity].add(nextDist);
                    pq.add(new Node(nextCity, nextDist));
                }
                //k개가 채워졌는데 새롭게 찾은 경로가 더 짧을 때
                else if (distances[nextCity].peek() > nextDist) {
                    //가장 큰 값 삭제 : 항상 k개로 유지
                    distances[nextCity].poll();
                    //새로운 경로 넣기
                    distances[nextCity].add(nextDist);
                    pq.add(new Node(nextCity, nextDist));
                }
            }
        }

        //출력
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            if (distances[i].size() == k) {
                sb.append(distances[i].peek()).append("\n");
            } else {
                sb.append("-1\n");
            }
        }
        System.out.print(sb.toString());
    }
}
