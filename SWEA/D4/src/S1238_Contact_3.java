import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class S1238_Contact_3 {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("1238_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for (int tc = 1; tc <= 10; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken()); // 입력 개수
			int start = Integer.parseInt(st.nextToken()); // 시작 번호
			int[][] m = new int[101][101];
			boolean[] chk = new boolean[101];
			st = new StringTokenizer(br.readLine()); //
			int ans = 0;
			for (int i = 0; i < N / 2; i++) {
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				m[from][to] = 1;
				m[from][0] += 1;
			} // end input

			Queue<Integer> q = new LinkedList<>();
			q.add(start);
			chk[start] = true;
			int cnt = 1;
			int max = 0;
			while (!q.isEmpty()) {
				cnt = q.size();
				max = 0;
				for (int i = 0; i < cnt; i++) {
					int tmp = q.poll();
					max = Math.max(max, tmp);
					for (int j = 1; j < 101; j++) {
						if (!chk[j] && m[tmp][j] == 1) {
							chk[j] = true;
							q.add(j);
						}
					}
				}
				ans = max;
//				System.out.println("time : " + time + ", max : " + max);
			}
			System.out.println("#" + tc + " " + ans);

		}
	}

}
