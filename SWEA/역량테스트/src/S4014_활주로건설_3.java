import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S4014_활주로건설_3 {
	static int N, X;
	static int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			X = Integer.parseInt(st.nextToken());
			map = new int[N][N];

			for (int r = 0; r < N; r++) {
				st = new StringTokenizer(br.readLine());
				for (int c = 0; c < N; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
				}
			}
			int ans = 0;
			for (int r = 0; r < N; r++) {
				if (rowSearch(r))
					ans++;
			}
			for (int c = 0; c < N; c++) {
				if (colSearch(c))
					ans++;
			}
			System.out.println("#" + tc + " " + ans);
		}

	}

	static boolean rowSearch(int r) {

		for (int c = 1; c < N - X; c++) {
			int tmp = map[r][c];
			int chkCnt = 0;
			for (int cc = c; cc < c + X; cc++) {
				if (tmp == map[r][cc])
					chkCnt++;
				else
					break;
			}
			if (chkCnt == X && map[r][c + X] == tmp + 1 && map[r][c - 1] == tmp) {
				System.out.println("-> " + r + ", " + c);
				return true;
			}
		}
		for (int c = N - 2; c > X; c--) {
			int tmp = map[r][c];
			int chkCnt = 0;
			for (int cc = c; cc > c - X; cc--) {
				if (tmp == map[r][cc])
					chkCnt++;
				else
					break;
			}
			if (chkCnt == X && map[r][c - X] == tmp + 1 && map[r][c + 1] == tmp) {
				System.out.println("<-  " + r + ", " + c);
				return true;
			}
		}
		return false;
	}

	static boolean colSearch(int c) {

		for (int r = 1; r < N - X; r++) {
			int tmp = map[r][c];
			int chkCnt = 0;
			for (int rr = r; rr < r + X; rr++) {
				if (tmp == map[rr][c])
					chkCnt++;
				else
					break;
			}
			if (chkCnt == X && map[r + X][c] == tmp + 1 && map[r - 1][c] == tmp) {
				System.out.println("아래 " + r + ", " + c);
				return true;
			}
		}
		for (int r = N - 2; r > X; r--) {
			int tmp = map[r][c];
			int chkCnt = 0;
			for (int rr = r; rr > r - X; rr--) {
				if (tmp == map[rr][c])
					chkCnt++;
				else
					break;
			}
			if (chkCnt == X && map[r - X][c] == tmp + 1 && map[r + 1][c] == tmp) {
				System.out.println("위 " + r + ", " + c);
				return true;
			}
		}
		return false;
	}
}
