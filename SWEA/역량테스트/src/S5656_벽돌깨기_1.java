import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class S5656_벽돌깨기_1 {
	static int N, W, H, cnt;
	static int[][] map;
	static boolean[][] bomb;
	static int[] dr = { 0, 0, 1, -1 };
	static int[] dc = { 1, -1, 0, 0 };

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("5656_input"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			map = new int[H][W];

			for (int r = 0; r < H; r++) {
				st = new StringTokenizer(br.readLine());
				for (int c = 0; c < W; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
				}
			}

			simul();
			int ans = 0;
			for (int c = 0; c < W; c++) {
				for (int r = 0; r < H - 1; r++) {
					if (map[r][c] != 0) {
						cnt++;
					}
				}
			}
			System.out.println("#" + tc + " " + ans);

		}
	}
	static void view() {
		for (int r = 0; r < H; r++) {
			for (int c = 0; c < W; c++) {
				if (bomb[r][c]) {
					System.out.print("o ");
				}
				else
					System.out.print("x ");
			}
			System.out.println();
		}
		System.out.println();
	}
	static void simul() {
		int max = 0;
		boolean[][] ansBomb = new boolean[H][W];
		for (int c = 0; c < W; c++) {
			bomb = new boolean[H][W];
			cnt = 0;
			view();
			int tmp = bfs(c);
			view();
			if (tmp > max)
				ansBomb = bomb;
			bombBreak(ansBomb);
			bombOrganize();
		}
	}

	static void bombOrganize() {
		for (int c = 0; c < W; c++) {
			for (int r = 0; r < H - 1; r++) {
				if (map[r][c] == 0) {
					map[r][c] = map[r + 1][c];
					map[r + 1][c] = 0;
				}
			}
		}
	}

	static void bombBreak(boolean[][] bomb) {
		for (int r = 0; r < H; r++) {
			for (int c = 0; c < H; c++) {
				if (bomb[r][c])
					map[r][c] = 0;
			}
		}
	}

	static int bfs(int c) {
		int cnt = 0;
		Queue<Pos> q = new LinkedList<>();

		for (int r = H - 1; r >= 0; r--) {
			if (map[r][c] != 0) {
				q.add(new Pos(r, c, map[r][c]));
				bomb[r][c] = true;
				break;
			}
		}

		while (!q.isEmpty()) {
			Pos tmp = q.poll();
			for (int d = 0; d < 4; d++) {
				int nr = tmp.r;
				int nc = tmp.c;
				int len = tmp.l;
				while (len != 1) {
					view();
					nr += dr[d];
					nc += dc[d];
					if (!moveable(nr, nc))
						break;
					bomb[nr][nc] = true;
					if (map[nr][nc] > 1)
						q.add(new Pos(nr, nc, map[nr][nc]));
					cnt++;
				}
			}
		}

		return cnt;

	}

	static boolean moveable(int r, int c) {
		if (r < 0 || c < 0 || r >= H || c >= W) {
			return false;
		}
		return true;
	}

	static class Pos {
		int r, c, l;

		public Pos(int r, int c, int l) {
			this.r = r;
			this.c = c;
			this.l = l;
		}

	}
}
