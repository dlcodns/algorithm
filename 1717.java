import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int[] arr;
    //트리 높이
    static int[] rank;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);

        //입력받기 n+1개의 집합, 다음 입력 개
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        arr = new int[n+1];
        rank = new int[n+1];

        for (int i=0;i<=n;i++){
            arr[i] = i;  //0에 0, 1에 1 넣기
            rank[i] = 0;  //트리 높이 0
        }

        for (int i = 0; i < m; i++) {
            //입력받기 합or 포함, a가 포함된 집합, b가 포함된 집합
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            //2차원 배열에서 a와 b의 인덱스 찾기
            int indexA = find(a);
            int indexB = find(b);

            //0일 때
            if (s==0){
                union(a,b);
            }
            //a가 있는 있는 집합에 b 원소 다 넣고 b가 있는 집합을 없애기

            //1일 때
            else if (indexA == indexB) {
                System.out.println("YES");
            }

            else System.out.println("NO");
            //인덱스가 같으면 YES, 다르면 NO 출력

        }
        out.flush();
        out.close();
        br.close();
    }

    public static int find(int value){
        if (arr[value] != value){
            arr[value] = find(arr[value]);
        }
        return arr[value];
    }

    public static void union(int x, int y){
        //x, y가 다를 때, 같으면 같은 집합이라서
        if (x != y){
            //트리 높이가 더 큰 루트에 다른 루트를 설정하는 방식
            //트리 최대 높이를 줄여서 시간 복잡도를 줄이는 방식.....
            if (rank[x] > rank[y]){
                arr[y] = x;
            }
            else if (rank[x] < rank[y]){
                arr[x] = y;
            }
            else{
                arr[y] = x;
                rank[x]++;
            }
        }
    }
}
