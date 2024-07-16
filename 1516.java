import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //최종 시간
        ArrayList<Integer> ans = new ArrayList<>();
        ArrayList<ArrayList<Integer>> prerequisites = new ArrayList<>();
        int n = Integer.parseInt(br.readLine()); //case 수

        for (int i = 0; i <= n; i++) {
            prerequisites.add(new ArrayList<>());
            ans.add(0);
        }

        for (int i = 1; i <= n; i++) {
            String[] input = br.readLine().split(" ");
            //첫번째 수 건설 시간을 ans에 다 넣기
            int buildTime = Integer.parseInt(input[0]);
            if (i < ans.size()) {
                ans.set(i, buildTime);
            } else {
                ans.add(buildTime);
            }

            //두 번째~ 마지막 전 수: 지어놔야 할 건물 저장
            for (int j = 1; j < input.length - 1; j++) {
                int prereq = Integer.parseInt(input[j]);
                prerequisites.get(i).add(prereq);
            }
        }

        //지어야 할 건물 중 최대 시간 찾고 더하기
        //여러 개일 경우 가장 오래 걸리는 건물이 완성 돼야 다음 건물
        for (int i = 1; i <= n; i++) {
            int maxPrereqTime = 0;
            for (int prereq : prerequisites.get(i)) {
                maxPrereqTime = Math.max(maxPrereqTime, ans.get(prereq));
            }
            ans.set(i, ans.get(i) + maxPrereqTime);
        }

        PrintWriter out = new PrintWriter(System.out);
        for (int i = 1; i <= n; i++) {
            out.println(ans.get(i));
        }
        out.flush();
        out.close();
        br.close();
    }
}