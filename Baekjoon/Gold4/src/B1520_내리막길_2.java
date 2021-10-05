import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B1520_내리막길_2 {
	static int M, N, cnt;
	static int[][] map;
	static int[] dr = { 0, 0, 1, -1 };
	static int[] dc = { 1, -1, 0, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());

		map = new int[M][N];

		for (int r = 0; r < M; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		dfs(0, 0, map[0][0]);
		System.out.println(cnt);
	}

	static void dfs(int r, int c, int h) {
		if (r == M - 1 && c == N - 1) {
			cnt++;
			return;
		}

		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			if (moveable(nr, nc)) {
				int nh = map[nr][nc];
				if (nh < h)
					dfs(nr, nc, nh);
			}
		}
	}

	static boolean moveable(int r, int c) {
		if (r < 0 || r >= M || c < 0 || c >= N)
			return false;
		return true;
	}

}
