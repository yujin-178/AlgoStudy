import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B1520_내리막길_3 {
	static int M, N, cnt;
	static int[][] map;
	static int[][] DP;
	static int[] dr = { 0, 0, 1, -1 };
	static int[] dc = { 1, -1, 0, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());

		map = new int[M][N];
		DP = new int[M][N];

		for (int r = 0; r < M; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				DP[r][c] = -1;
			}
		}

		DP[M - 1][N - 1] = 1;
		dfs(0, 0);
//		viewDP();
		System.out.println(DP[0][0]);
	}

	static void viewDP() {
		System.out.println();
		for (int r = 0; r < M; r++) {
			for (int c = 0; c < N; c++) {
				System.out.printf("%02d ", DP[r][c]);
			}
			System.out.println();
		}
		System.out.println();
	}

	static void dfs(int r, int c) {

		if (r == M - 1 && c == N - 1) {
			DP[r][c] = 1;
			return;
		}
		if (DP[r][c] != -1)
			return;
		DP[r][c] = 0;
		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			if (moveable(nr, nc) && map[r][c] > map[nr][nc]) {
				dfs(nr, nc);
				DP[r][c] += DP[nr][nc];
			}
		}
		viewDP();
	}

	static boolean moveable(int r, int c) {
		if (r < 0 || r >= M || c < 0 || c >= N)
			return false;
		return true;
	}

}
