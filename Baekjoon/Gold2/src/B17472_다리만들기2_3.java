import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B17472_다리만들기2_3 {
	static int N, M, num, ans, cnt;
	static int[][] map, record;
	static boolean[][] chk;
	static int[] dr = { 0, 1, 0, -1 };
	static int[] dc = { 1, 0, -1, 0 };
	static int[][] mat;
	static Pos[] setBridge;
	static final int MAX = 40000;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("17472_input"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		record = new int[N][M];
		chk = new boolean[N][M];
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < M; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				if (!chk[r][c] && map[r][c] == 1) {
					map[r][c] = ++num;
					chk[r][c] = true;
					cntMap(r, c);
					System.out.println(cnt);
				}
			}
		}

		makeMatrix();

		int idx = 0;
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				if (map[r][c] != 0)
					setBridge[idx++] = new Pos(r, c, map[r][c]);
				System.out.print(map[r][c] + " ");
			}
			System.out.println();
		}

		makeBridge();

		System.out.println(ans);

	}

	static void makeBridge() {
		for (int i = 0; i < cnt; i++) {
			for (int j = 0; j < cnt; j++) {
				if (i == j)
					continue;
				int nr = setBridge[i].type;
				int nc = setBridge[j].type;
				if (nr == nc)
					continue;
				int distance = calcDis(setBridge[i], setBridge[j]) - 1;
				if (distance >= 2)
					mat[nr][nc] = Math.min(mat[nr][nc], distance);
			}
		}

		for (int i = 1; i <= num; i++) {
			for (int j = 1; j <= num; j++) {
				System.out.print(mat[i][j] == MAX ? 0 + " " : mat[i][j] + " ");
			}
			System.out.println();
		}

		prim();
	}

	static void prim() {
		ans = Integer.MAX_VALUE;
		for (int idx = 1; idx <= num; idx++) {
			int startIdx = idx;
			int tmp = 0;
			boolean[] visited = new boolean[num + 1];
			visited[startIdx] = true;
			int chkCnt = 1;
			System.out.println("-------------------------------");
			while (true) {
				int minIdx = 0;
				int minVal = MAX;
				for (int i = 1; i <= num; i++) {
					if (!visited[i] && mat[startIdx][i] > 1 && mat[startIdx][i] < minVal) {
						minVal = mat[startIdx][i];
						minIdx = i;
					}
				}
				System.out.println(minIdx + ", " + minVal);
				visited[minIdx] = true;
				startIdx = minIdx;
				tmp += minVal;
				chkCnt++;
				if (chkCnt == num)
					break;
			}
			ans = Math.min(ans, tmp);
		}
	}

	static void makeMatrix() {
		mat = new int[num + 1][num + 1];
		for (int r = 0; r <= num; r++) {
			for (int c = 0; c <= num; c++) {
				mat[r][c] = MAX;
			}
		}
		setBridge = new Pos[cnt];
	}

	static void cntMap(int r, int c) {
		cnt++;
		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			if (outMapChk(nr, nc))
				continue;
			if (!chk[nr][nc] && map[nr][nc] == 1) {
				map[nr][nc] = num;
				chk[nr][nc] = true;
				cntMap(nr, nc);
			}
		}

	}

	static boolean outMapChk(int r, int c) {
		if (r < 0 || r >= N || c < 0 || c >= M)
			return true;
		return false;
	}

	static int calcDis(Pos a, Pos b) {
		if (a.r == b.r) {
			return Math.abs(a.c - b.c) > 1 ? Math.abs(a.c - b.c) : 0;
		} else if (a.c == b.c) {
			return Math.abs(a.r - b.r) > 1 ? Math.abs(a.r - b.r) : 0;
		}
		return 0;
	}

	static class Pos {
		int r, c, type;

		public Pos(int r, int c, int type) {
			super();
			this.r = r;
			this.c = c;
			this.type = type;
		}
	}
}
