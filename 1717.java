import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int[] arr;
    static int[] rank;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

       arr = new int[n+1];
       rank = new int[n+1];

       for (int i=0;i<=n;i++){
           arr[i] = i;
           rank[i] = 0;
       }

        for (int i = 0; i < m; i++) {
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
        if (x != y){
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
