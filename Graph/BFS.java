import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static void main(String args[]) {
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

        System.out.println("BFS 0부터 시작(작은 숫자 우선 순위)");
        g.BFS(0);
    }
}

class Graph {
    private int V;
    private LinkedList<Integer> adj[];

    Graph(int v) {  //노드 7개 만들기
        V = v;
        adj = new LinkedList[v];
        for (int i = 0; i < v; i++)
            adj[i] = new LinkedList(); //2차원
    }

    public void addEdge(int v, int w) {  //양방향으로 연결짓기
        adj[v].add(w);
        adj[w].add(v);
    }

    public void BFS(int s) {  //s = 시작 지점, 
        boolean visited[] = new boolean[V];  //방문 확인
        Queue<Integer> queue = new LinkedList<>();  //큐 사용

        visited[s] = true;    //시작 지점 방문
        queue.add(s);

        //큐가 비면 반복 해제
        while (!queue.isEmpty()) {
            s = queue.poll();  //stack의 pop같은 역할
            System.out.print(s + " ");

            for (int n : adj[s]) { //adj[s]에 있는 인접한 노드를 반복
                if (!visited[n]) {  //방문 안 했을 때 true, add 처리
                    visited[n] = true;
                    queue.add(n);
                }
            }  //인접 노드가 끝나면 큐를 poll하고 프린트하기
        }
    }
}
