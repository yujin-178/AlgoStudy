import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B1303_전쟁전투_1 {
	static int N, M, cnt, w, b;
	static int[] dr = { 0, 1, 0, -1 };
	static int[] dc = { 1, 0, -1, 0 };
	static char[][] map;
	static boolean[][] chk;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[M][N];
		chk = new boolean[M][N];
		for (int r = 0; r < M; r++) {
			map[r] = br.readLine().toCharArray();
		}
		for (int r = 0; r < M; r++) {
			for (int c = 0; c < N; c++) {
				if (!chk[r][c]) {
					cnt = 1;
					chk[r][c] = true;
					dfs(r, c, map[r][c]);
					if (map[r][c] == 'W')
						w += cnt * cnt;
					else
						b += cnt * cnt;
				}
			}
		}

		System.out.println(w + " " + b);
	}

	static void dfs(int r, int c, char team) {

		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			if (mapChk(nr, nc) && !chk[nr][nc] && map[nr][nc] == team) {
				cnt++;
				chk[nr][nc] = true;
				dfs(nr, nc, team);
			}
		}
	}

	static boolean mapChk(int r, int c) {
		if (r < 0 || r >= M || c < 0 || c >= N)
			return false;
		return true;
	}
}
