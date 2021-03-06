import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B4485_녹색옷입은애가젤다지_1_dfs시간초과 {
	static int[][] map, chk;
	static int N, minCost;
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("4485_input"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int cnt = 0;
		while (N != 0) { // N 이 0이면 종료
			map = new int[N][N]; // 지도 입력 시작
			chk = new int[N][N];
			for (int r = 0; r < N; r++) {
				st = new StringTokenizer(br.readLine());
				for (int c = 0; c < N; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
				}
			} // 지도 입력 종료

			// 탐색 시작
			chk[0][0] = map[0][0];
			minCost = Integer.MAX_VALUE;
			dfs(0, 0, map[0][0]);

			// N 업데이트
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			System.out.println("Problem" + " " + (++cnt) + ": " + minCost);
		}
	}

	static void dfs(int r, int c, int sum) {
		if (sum > minCost)
			return;
		if (r == N - 1 && c == N - 1) {
			minCost = Math.min(minCost, sum);
			return;
		}

		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			if (moveable(nr, nc)) {
				int next = sum + map[nr][nc];
				chk[nr][nc] = next;
				dfs(nr, nc, next);
				chk[nr][nc] = 0;
			}
		}
	}

	static boolean moveable(int r, int c) {
		if (r < 0 || r >= N || c < 0 || c >= N || chk[r][c] != 0)
			return false;
		return true;
	}
}
