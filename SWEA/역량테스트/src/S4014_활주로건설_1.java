import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S4014_활주로건설_1 {
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
//				if (rowSearch(r))
//					System.out.println("good r : " + r);
			}
			for (int c = 0; c < N; c++) {
				if (colSearch(c))
					ans++;
//				if (colSearch(c))
//					System.out.println("good c : " + c);
			}
			System.out.println("#" + tc + " " + ans);
		}

	}

	static boolean rowSearch(int r) {
		int exH = map[r][0];
		boolean[] chk = new boolean[N];
		for (int c = 1; c < N; c++) {
			if (map[r][c] != exH) {
				if (Math.abs(map[r][c] - exH) > 1)
					return false;
				else if (map[r][c] - exH == 1) { // 지금 칸이 한칸 더 높다.
					for (int i = c - 1; i > c - 1 - X; i--) { // 이전 칸 부터 X칸 만큼 비어 여유가 있어야 한다.
						if (i < 0)
							return false;
						if (map[r][i] != exH || chk[i])
							return false;
						else
							chk[i] = true;
					}

				} else if (map[r][c] - exH == -1) { // 지금 칸이 한칸 더 낮다.
					for (int i = c; i < c + X; i++) { // 지금 칸 부터 X칸 만큼 여유가 있어야 한다.
						if (i >= N)
							return false;
						if (map[r][i] != map[r][c] || chk[i])
							return false;
						else
							chk[i] = true;
					}

				}
			}
			exH = map[r][c];
		}
		return true;
	}

	static boolean colSearch(int c) {
		int exH = map[0][c];
		boolean[] chk = new boolean[N];
		for (int r = 1; r < N; r++) {
			if (map[r][c] != exH) {
				if (Math.abs(map[r][c] - exH) > 1)
					return false;
				else if (map[r][c] - exH == 1) {
					for (int i = r - 1; i > r - 1 - X; i--) {
						if (i < 0)
							return false;
						if (map[i][c] != exH || chk[i])
							return false;
						else
							chk[i] = true;
					}
				} else if (map[r][c] - exH == -1) {
					for (int i = r; i < r + X; i++) {
						if (i >= N)
							return false;
						if (map[i][c] != map[r][c] || chk[i])
							return false;
						else
							chk[i] = true;
					}
				}
			}

			exH = map[r][c];
		}
		return true;
	}
}
