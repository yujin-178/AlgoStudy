import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class S1953_탈주범검거_1 {
	static int[][] dr = new int[][] { {}, { 1, 0, -1, 0 }, { 1, -1 }, { 0, 0 }, { -1, 0 }, { 1, 0 }, { 0, 1 },
			{ 0, -1 } };
	static int[][] dc = new int[][] { {}, { 0, -1, 0, 1 }, { 0, 0 }, { -1, 1 }, { 0, 1 }, { 0, 1 }, { -1, 0 },
			{ -1, 0 } };

	static int N, M, R, C, L;
	static int[][] map;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("1953_input"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			map = new int[N][M];

			for (int r = 0; r < N; r++) {
				st = new StringTokenizer(br.readLine());
				for (int c = 0; c < M; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
				}
			}

			System.out.println("#" + tc + " " + bfs());

		}
	}

	static int bfs() {
		Queue<Pos> q = new LinkedList<>();
		q.add(new Pos(R, C));
		boolean[][] chk = new boolean[N][M];
		chk[R][C] = true;

		int time = 1;
		if (1 == L)
			return 1;
		while (!q.isEmpty() && time != L) {
			++time;
			int today = q.size();
			for (int i = 0; i < today; i++) {
				Pos p = q.poll();
				int type = map[p.r][p.c];
				int dSize = dr[type].length;
				for (int d = 0; d < dSize; d++) {
					int nr = p.r + dr[type][d];
					int nc = p.c + dc[type][d];
					if (chkNext(p.r, p.c, nr, nc) && !chk[nr][nc]) {
						chk[nr][nc] = true;
						q.add(new Pos(nr, nc));
					}
				}
			}
		}
		int cnt = 0;
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				if (chk[r][c])
					cnt++;
			}
		}
		return cnt;
	}

	static boolean chkNext(int r, int c, int nr, int nc) {
		boolean flag1 = (nr >= 0 && nc >= 0 && nr < N && nc < M);
		if (!flag1)
			return false;
		boolean flag2 = false;
		int type = map[nr][nc];
		int dSize = dr[type].length;
		for (int d = 0; d < dSize; d++) {
			if (r == dr[type][d] + nr && c == dc[type][d] + nc)
				flag2 = true;
		}

		if (flag2)
			return true;

		return false;
	}

	static class Pos {
		int r, c;

		public Pos(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}

	}
}
