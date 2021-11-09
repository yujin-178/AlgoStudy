
public class Solution_김유진 {
	static boolean chk[];
	static int N;
	static int[][] map;

	public int solution(int n, int[][] computers) {
		N = n;
		chk = new boolean[n];
		map = computers;
		int cnt = 0;
		for (int r = 0; r < n; r++) {
			for (int c = 0; c < n; c++) {
				if (map[r][c] == 1 && !chk[c]) {
					System.out.println(r + ", " + c);
					cnt++;
					chk[c] = true;
					dfs(c);
				}
			}
		}

		return cnt;
	}

	static void dfs(int c) {
		for (int i = 0; i < N; i++) {
			if (map[i][c] == 1 && !chk[i]) {
				chk[i] = true;
				if (i != c)
					dfs(i);
			}
		}
	}
}
