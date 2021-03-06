import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class S1249_보급로_1_dfs {
	static int map[][], chk[][];
	static int N, minCost;
	static int[] dr = { 0, 1, 0, -1 };
	static int[] dc = { -1, 0, 1, 0 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {

			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			chk = new int[N][N];
			for (int r = 0; r < N; r++) {
				String tmp = br.readLine();
				for (int c = 0; c < N; c++) {
					map[r][c] = (int) (tmp.charAt(c) - '0');
					chk[r][c] = Integer.MAX_VALUE;
				}
			}
			minCost = Integer.MAX_VALUE;
			dfs(0, 0, 0);

			System.out.println("#" + tc + " " + minCost);
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
				int tmp = sum + map[nr][nc];
				if (chk[nr][nc] > tmp) {
					chk[nr][nc] = tmp;
					dfs(nr, nc, tmp);
				}
			}

		}
	}

	static boolean moveable(int r, int c) {
		if (r < 0 || r >= N || c < 0 || c >= N)
			return false;
		return true;
	}
}
