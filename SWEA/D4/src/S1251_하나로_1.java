import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S1251_하나로_1 {
	static long[] dis;
	static int N;
	static boolean[] chk;
	static long[][] map;
	static double ans;
	static double e;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("1251_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());

		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			Pos[] p = new Pos[N];
			st = new StringTokenizer(br.readLine());
			for (int n = 0; n < N; n++) {
				p[n] = new Pos(Integer.parseInt(st.nextToken()));
			}
			st = new StringTokenizer(br.readLine());
			for (int n = 0; n < N; n++) {
				p[n].setY(Integer.parseInt(st.nextToken()));
			}
			e = Double.parseDouble(br.readLine());
			// 입력 종료
			dis = new long[N];
			map = new long[N][N];
			chk = new boolean[N];
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					map[r][c] = makeWeight(p[r].x, p[r].y, p[c].x, p[c].y);
				}
				dis[r] = Long.MAX_VALUE;
			}

			ans = 0;
			dis[0] = 0;

			for (int i = 0; i < N; i++) {
				long min = Long.MAX_VALUE;
				int minIdx = -1;
				for (int j = 0; j < N; j++) {
					if (!chk[j] && min > dis[j]) {
						min = dis[j];
						minIdx = j;
					}
				}

				chk[minIdx] = true;
				ans += min*e;

				for (int j = 0; j < N; j++) {
					if (!chk[j] && map[minIdx][j] != 0 && dis[j] > map[minIdx][j]) {
						dis[j] = map[minIdx][j];
					}
				}

			}

			System.out.println("#" + tc + " " + (long)Math.round(ans));
		}
	}

	static long makeWeight(int x1, int y1, int x2, int y2) {
		return ((long) Math.pow(x1 - x2, 2) + (long) Math.pow(y1 - y2, 2));
	}

	static class Pos {
		int x;
		int y;

		public Pos(int x) {
			super();
			this.x = x;
		}

		void setY(int y) {
			this.y = y;
		}

	}
}
