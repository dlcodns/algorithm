import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        Graph g = new Graph(7);

        g.addEdge(0, 1);
        g.addEdge(0, 3);
        g.addEdge(1, 2);
        g.addEdge(1, 3);
        g.addEdge(1, 5);
        g.addEdge(1, 6);
        g.addEdge(2, 3);
        g.addEdge(2, 4);
        g.addEdge(2, 5);
        g.addEdge(3, 4);
        g.addEdge(4, 6);

        System.out.println("DFS 0부터 시작(작은 숫자 우선 순위)");
        g.DFS(0);
    }
}

class Graph {
    private int V;
    private LinkedList<Integer> adj[];

    Graph(int v) {  //노드 v개(7개) 만들기
        V = v;
        adj = new LinkedList[v];
        for (int i = 0; i < v; ++i)
            adj[i] = new LinkedList<>();
    }

    public void addEdge(int v, int w) {  //양방향으로 연결짓기
        adj[v].add(w);
        adj[w].add(v);
    }

    public void DFS(int s) {  //s = 시작 지점
        boolean visited[] = new boolean[V];
        DFSUtil(s, visited);
    }

    private void DFSUtil(int s, boolean visited[]) {
        visited[s] = true;  //시작 지점 방문
        System.out.print(s + " ");

        for (int n : adj[s]) {  //adj[s]에 있는 인접한 노드를 반복
            if (!visited[n]) {  //방문 안 했을 때 s를 n으로 두고 재귀 호출
                DFSUtil(n, visited);
            }
        }
    }
}
