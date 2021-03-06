import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class S4727_견우와직녀_1 {
	static int M, N;
	static int[] dr = {0, 0, 1, 0, -1 };
	static int[] dc = {0, 1, 0, -1, 0 };
	static int[][] map;
	static boolean[][] chk;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		chk = new boolean[N][N];

		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				int chkNum = 0;
				if (map[r][c] == 0) {
					for (int d = 0; d < 4; d++) {
						int nr = r + dr[d];
						int nc = c + dc[d];
						if (nr >= 0 && nr < N && nc >= 0 && nc < N && map[nr][nc] == 0)
							chkNum++;
					}
				}
				if (chkNum >= 3)
					chk[r][c] = true;
			}
		}

		bfs();
	}

	static int bfs() {
		Queue<Pos> q = new LinkedList<>();
		q.add(new Pos(0, 0, 0));
		chk[0][0] = true;
		int day = 0;
		while (!q.isEmpty()) {
			day++;
			int l = q.size();
			for (int i = 0; i < l; i++) {
				Pos tmp = q.poll();
				for(int  i =0;)
			}
		}
		return -1;

	}

	static class Pos {
		int r, c, t;

		public Pos(int r, int c, int t) {
			this.r = r;
			this.c = c;
			this.t = t;
		}
	}
}
