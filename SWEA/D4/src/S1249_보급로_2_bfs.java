import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class S1249_보급로_2_bfs {
	static int map[][], chk[][];

	static int N;
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
			System.out.println("#" + tc + " " + bfs());
		}
	}

	static int bfs() {
		Queue<Pos> q = new LinkedList<>();
		q.add(new Pos(0, 0));

		chk[0][0] = 0;
		int ans = Integer.MAX_VALUE;
		while (!q.isEmpty()) {
			Pos exPos = q.poll();
			for (int d = 0; d < 4; d++) {
				int nr = exPos.r + dr[d];
				int nc = exPos.c + dc[d];
				if (moveable(nr, nc)) {
					int tmp = chk[exPos.r][exPos.c] + map[nr][nc];
					if (nr == N - 1 && nc == N - 1) {
						ans = Math.min(ans, tmp);
					}
					if (tmp < chk[nr][nc]) {
						chk[nr][nc] = tmp;
						q.add(new Pos(nr, nc));
					}
				}
			}
		}
		return ans;
	}

	static class Pos  {
		int r, c;

		public Pos(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}

		

	}

	static boolean moveable(int r, int c) {
		if (r < 0 || r >= N || c < 0 || c >= N)
			return false;
		return true;
	}
}
