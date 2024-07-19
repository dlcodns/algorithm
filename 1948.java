//가장 늦게 도착하는 사람
import java.util.*;

public class Main {
    static class Edge { //목적지랑 이동 시간 클래스
        int to, time;
        Edge(int to, int time) {
            this.to = to;
            this.time = time;
        }
    }

    static int[] inDegree;
    static int[] longestTime;
    static List<List<Edge>> graph;
    static List<List<Edge>> reverseGraph;
    static int[] parents;
    static int start, end;

    public static void main(String[] args) {
        //입력 받기
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();

        //도시 출발 array
        inDegree = new int[n + 1];
        //각 도시까지의 최장 경로 시간
        longestTime = new int[n + 1];
        //부모 노드
        parents = new int[n + 1];
        Arrays.fill(parents, -1);

        //도로 정보, 역추적 정보
        graph = new ArrayList<>();
        reverseGraph = new ArrayList<>();

        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
            reverseGraph.add(new ArrayList<>());
        }

        //입력 받아 도로 정보 채우기
        for (int i = 0; i < m; i++) {
            int from = sc.nextInt();
            int to = sc.nextInt();
            int time = sc.nextInt();
            graph.get(from).add(new Edge(to, time));
            reverseGraph.get(to).add(new Edge(from, time));
            inDegree[to]++;
        }

        //입략
        start = sc.nextInt();
        end = sc.nextInt();

        //위상 정렬 사용
        LongestPath(n);

        System.out.println(longestTime[end]);

        //최장 경로의 도로 수 카운트
        int count = countCriticalPaths();
        System.out.println(count);
    }

    //위상 정렬 함수
    private static void LongestPath(int n) {
        Queue<Integer> queue = new LinkedList<>();
        //0인 얘들 큐에 추가 : 처음 초기화 때 다 0
        for (int i = 1; i <= n; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
            }
        }

        while (!queue.isEmpty()) {
            int node = queue.poll();

            for (Edge edge : graph.get(node)) {
                //현재 노드에서 도착으로 가는 더 긴 경로가 있으면 업뎃
                if (longestTime[edge.to] < longestTime[node] + edge.time) {
                    longestTime[edge.to] = longestTime[node] + edge.time;
                    parents[edge.to] = node;
                }
                //위상 정렬 순서를 결정하기 위해
                //도착 노드의 숫자를 감소시키고 0이 되면 큐에 추가
                if (--inDegree[edge.to] == 0) {
                    queue.add(edge.to);
                }
            }
        }
    }

    private static int countCriticalPaths() {
        boolean[] visited = new boolean[longestTime.length];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(end);
        visited[end] = true;
        int count = 0;

        //역방향으로 최장 경로를 따라가면서 도로 수 카운트
        while (!queue.isEmpty()) {
            int node = queue.poll();

            for (Edge edge : reverseGraph.get(node)) {
                if (longestTime[node] - longestTime[edge.to] == edge.time) {
                    count++;
                    if (!visited[edge.to]) {
                        visited[edge.to] = true;
                        queue.add(edge.to);
                    }
                }
            }
        }

        return count;
    }
}
