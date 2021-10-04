import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class S1767_프로세서연결하기_1 {
	static LinkedList<P> p;
	static L[] l;
	static int[][] map;
	static boolean[][] chk;
	static int min, max,test;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("1767_input"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int T = Integer.parseInt(st.nextToken());

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			chk = new boolean[N][N];
			p = new LinkedList<>();
			min = Integer.MAX_VALUE;
			max = 0;
			for (int r = 0; r < N; r++) {
				st = new StringTokenizer(br.readLine());
				for (int c = 0; c < N; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
					if (r != 0 && r != N - 1 && c != 0 && c != N - 1 && map[r][c] == 1) {
						p.add(new P(r, c)); // 가장자리를 제외한 프로세서 등록
					}
				}
			}

			for (int i = 0; i < p.size(); i++) {
				setLine(p.get(i), N); // 프로세서 별 전선 가능 기록
			}

			dfs(0, p.size(), 0, 0);
			System.out.println(min);
		}
	}

	static void dfs(int n, int N, int sum, int cnt) {
		System.out.print(test++ + "  : ");
		if (n == N) {
			if (max >= cnt) {
				max = cnt;
				min = Math.min(min, sum);
			}
			return;
		}

		for (int i = 0; i < 5; i++) {
			System.out.println(n);
			if (i < 4 && p.get(n).line[i].type == -1)
				continue;
			dfs(n + 1, N, sum + p.get(n).line[i].length, i < 4 ? cnt + 1 : cnt);
		}
	}

	static void setLine(P p, int N) {
		for (int r = 0; r < p.r; r++) { // 프로세서 상
			if (map[r][p.c] == 1) {
				p.line[2] = new L(-1, 0, p.c, p.r - 1, p.c, p.r - 1);
				break;
			}
			if (r == p.r - 1) {
				p.line[2] = new L(0, 0, p.c, p.r - 1, p.c, p.r - 1);
			}
		}

		for (int r = N - 1; r >= p.r + 1; r--) { // 프로세서 하
			if (map[r][p.c] == 1) {
				p.line[3] = new L(-1, N - 1, p.c, p.r + 1, p.c, N - 1 - (p.r + 1));
				break;
			}
			if (r == N - 1) {
				p.line[3] = new L(0, N - 1, p.c, p.r + 1, p.c, N - 1 - (p.r + 1));
			}
		}

		for (int c = 0; c < p.c; c++) { // 프로세서 좌
			if (map[p.r][c] == 1) {
				p.line[0] = new L(-1, p.r, 0, p.r, p.c - 1, p.c - 1);
				break;
			}
			if (c == p.r - 1) {
				p.line[0] = new L(1, 0, p.c, p.r - 1, p.c, p.r - 1);
			}
		}
		for (int c = N - 1; c > p.c; c--) { // 프로세서 우
			if (map[p.r][c] == 1) {
				p.line[1] = new L(-1, p.r, p.c + 1, p.r, N - 1, N - 1 - (p.c + 1));
				break;
			}
			if (c == N - 1) {
				p.line[1] = new L(1, p.r, p.c + 1, p.r, N - 1, N - 1 - (p.c + 1));
			}
		}
		p.line[4] = new L(-1, -1, -1, -1, -1, 0);
	}

	static class P {
		int r;
		int c;
		L[] line;

		// 0 좌 ,1우, 2상, 3하 ;
		public P(int r, int c) {
			super();
			this.r = r;
			this.c = c;
			this.line = new L[5];
		}
	}

	static class L {
		int type; // -1 불가능 , 1 가로, 0세로
		int sx;
		int sy;
		int dx;
		int dy;
		int length;

		public L(int type, int sx, int sy, int dx, int dy, int length) {
			super();
			this.type = type;
			this.sx = sx;
			this.sy = sy;
			this.dx = dx;
			this.dy = dy;
			this.length = length;
		}
	}
}
