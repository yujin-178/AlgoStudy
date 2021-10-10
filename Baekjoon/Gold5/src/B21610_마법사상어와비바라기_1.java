import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class B21610_마법사상어와비바라기_1 {
	static int N, M, cnt;
	static int[][] com, map;
	static boolean[][] chk;
	static int[] dr = { 0, -1, -1, -1, 0, 1, 1, 1 };
	static int[] dc = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static Cloud[] c;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		com = new int[M][2];
		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			com[m][0] = Integer.parseInt(st.nextToken()) - 1;
			com[m][1] = Integer.parseInt(st.nextToken());
		}

		initCloud();

		simul();

		System.out.println(cntWater());
	}

	static int cntWater() {
		int ans = 0;
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				ans += map[r][c];
			}
		}
		return ans;
	}

	static void simul() {
		for (int m = 0; m < M; m++) {
			chk = new boolean[N][N];
			moveRainDeleteCloud(m);

			waterCopyMakeCloud();

		}
	}

	static void waterCopyMakeCloud() {
		int[][] tmp = new int[N][N];
		for (int i = 0; i < cnt; i++) {
			for (int d = 0; d < 4; d++) {
				int nr = c[i].r + dr[d * 2 + 1];
				int nc = c[i].c + dc[d * 2 + 1];
				if (nr >= 0 && nr < N && nc >= 0 && nc < N) {
					if (map[nr][nc] > 0)
						++tmp[c[i].r][c[i].c];
				}
			}
		}

		Stack<Cloud> s = new Stack<Cloud>();

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				map[r][c] += tmp[r][c];
				if (!chk[r][c] && map[r][c] >= 2) {
					s.push(new Cloud(r, c));
					map[r][c] -= 2;
				}
			}
		}

		cnt = s.size();
		c = new Cloud[cnt];
		for (int i = 0; i < cnt; i++) {
			c[i] = s.pop();
		}
	}

	static void moveRainDeleteCloud(int m) {
		for (int i = 0; i < cnt; i++) {
			c[i].r += dr[com[m][0]] * com[m][1];
			c[i].c += dc[com[m][0]] * com[m][1];
			c[i].rePos();
			int nr = c[i].r;
			int nc = c[i].c;
			map[nr][nc] += 1;
			chk[nr][nc] = true;
		}

	}

	static void initCloud() {
		c = new Cloud[4];
		c[0] = new Cloud(N - 1, 0);
		c[1] = new Cloud(N - 1, 1);
		c[2] = new Cloud(N - 2, 0);
		c[3] = new Cloud(N - 2, 1);
		cnt = 4;

	}

	static class Cloud {
		int r, c;

		public Cloud(int r, int c) {
			this.r = r;
			this.c = c;
		}

		void rePos() {
			if (this.r >= N)
				this.r = this.r % N;
			if (this.c >= N)
				this.c = this.c % N;
			int tmp = 6000 - 6000 % N;
			if (this.r < 0)
				this.r = (this.r + tmp) % N;
			if (this.c < 0)
				this.c = (this.c + tmp) % N;
		}
	}
}
