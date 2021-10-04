import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class S1767_프로세서연결하기_3 {

	static int[][] map;
	static boolean[][] chk, dfsMap;
	static int N, min;
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };
	static LinkedList<P> p;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("1767_input"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int T = Integer.parseInt(st.nextToken());

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			chk = new boolean[N][N];
			p = new LinkedList<>();
			for (int r = 0; r < N; r++) {
				st = new StringTokenizer(br.readLine());
				for (int c = 0; c < N; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
					if (map[r][c] == 1) {
						chk[r][c] = true;
						p.add(new P(r, c));
					}
				}
			} // end 입력
			min = Integer.MAX_VALUE;
			dfs(0, 0);
			System.out.println("#" + tc + " " + min);

		}
	}

	static void showChk(int n, int sum) {
		System.out.println(n + "번째 : " + sum + " ----------------------");
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (chk[r][c])
					System.out.print("o ");
				else
					System.out.print("x ");
			}
			System.out.println();
		}
		System.out.println();
	}

	static void dfs(int n, int sum) {
//		showChk(n,sum);
		if (n == p.size()) {
			min = Math.min(min, sum);
			return;
		}
		int nr = p.get(n).r;
		int nc = p.get(n).c;

		if (nr == 0 || nr == N - 1 || nc == 0 || nc == N - 1) {
			dfs(n + 1, sum);
		} else {
			for (int d = 0; d < 5; d++) {
				nr = p.get(n).r;
				nc = p.get(n).c;
				if (d < 4) {
					int tmp = check(p.get(n), d);
					if (tmp == -1)
						continue;
					while (!(nr == 0 || nr == N - 1 || nc == 0 || nc == N - 1)) {
						nr += dr[d];
						nc += dc[d];
						chk[nr][nc] = true;
					}

					dfs(n + 1, sum + tmp);

					while (!(nr == p.get(n).r && nc == p.get(n).c)) {
						chk[nr][nc] = false;
						nr -= dr[d];
						nc -= dc[d];
					}

				} else {
					dfs(n + 1, sum + 13);
				}
			}
		}
	}

	static int check(P p, int d) {
		int nr = p.r;
		int nc = p.c;
		int cnt = 0;
		if (nr == 0 || nr == N - 1 || nc == 0 || nc == N - 1) {
			return cnt;
		}
		while (true) {
			nc += dc[d];
			nr += dr[d];
			cnt++;
			if (nr < 0 || nr >= N || nc < 0 || nr >= N || chk[nr][nc])
				return -1;
			if (nr == 0 || nr == N - 1 || nc == 0 || nc == N - 1) {
				return cnt;
			}

		}
	}

	static class P {
		int r;
		int c;
		int[] line;

		public P(int r, int c) {
			super();
			this.r = r;
			this.c = c;
			this.line = new int[] { -1, -1, -1, -1 };
		}

	}

}
