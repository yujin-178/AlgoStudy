import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S1263_사람네트워크2_1 {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("1263_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		int max = 1000; // 최대치

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int[][] map = new int[N][N];
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
					if (map[r][c] == 0)
						map[r][c] = max;
				}
			}

			for (int k = 0; k < N; k++) {
				for (int i = 0; i < N; i++) {
					if (i == k) continue;
					for (int j = 0; j < N; j++) {
						if (j == k || j == i) continue;
						map[i][j] = Math.min(map[i][k] + map[k][j], map[i][j]);
					}
				}
			}
			
			
			int ans = Integer.MAX_VALUE;
			for (int idx = 0; idx < N; idx++) {
				int sum = 0;
				for (int i = 0; i < N; i++) {
					if(idx == i) continue;
					sum += map[idx][i];
				}
				ans = Math.min(sum, ans);
			}

			System.out.println("#" + tc + " " + ans);
		}

	}
}
