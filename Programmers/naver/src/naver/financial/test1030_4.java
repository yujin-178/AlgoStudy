package naver.financial;

/**
 * @FileName : test1030_4.java
 * @Date : 2021. 10. 30.
 * @작성자 : KimYuJin
 * @특이점 : nbyn 맵 0,0 에서 N-1,N-1로 감 점수는 랜덤, 0이 적힌 칸을 지나가면 현재까지의 점수에 -1을 곱할수 있다
 *      곱하지 않아도 좋다.
 * 
 */
public class test1030_4 {
	public static void main(String[] args) {
		int[][] board = { { 1, -7, -2, 1, -1 }, { 2, 3, 0, -1, -2 }, { 1, -1, 6, -1, -2 }, { -1, 1, -2, 0, 4 },
				{ -10, 5, -3, -1, 1 } };
		System.out.println(solution(board));
	}

	static int N, max;
	static int[] dr = { 0, 1 };
	static int[] dc = { 1, 0 };
	static int[][] map;

	static public int solution(int[][] board) {
		map = board;
		N = board[0].length;
		boolean[][] chk = new boolean[N][N];
		dfs(0, 0, map[0][0], chk);
		return max;
	}

	static void dfs(int r, int c, int sum, boolean[][] chk) {
		if (r == N - 1 && c == N - 1) {
			max = Math.max(sum, max);
			return;
		}

		for (int d = 0; d < 2; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];

			if (nr < 0 || nr >= N || nc < 0 || nc >= N || chk[nr][nc])
				continue;

			chk[nr][nc] = true;
			if (map[nr][nc] != 0)
				dfs(nr, nc, sum + map[nr][nc], chk);
			else {
				if (sum < 0)
					dfs(nr, nc, -sum, chk);
				else
					dfs(nr, nc, sum, chk);
			}
			chk[nr][nc] = false;

		}

	}

}
