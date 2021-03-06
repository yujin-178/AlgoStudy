import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B1520_내리막길_1 {
	static int M, N, cnt;
	static int[][] map;
	static int[] dr = { 0, 0, 1, -1 };
	static int[] dc = { 1, -1, 0, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());

		map = new int[M][N];

		for (int r = 0; r < M; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		bfs();
		System.out.println(cnt);
	}

	static void bfs() {
		Queue<Pos> q = new LinkedList<>();
		q.add(new Pos(0, 0));

		while (!q.isEmpty()) {
			Pos tmp = q.poll();
			for (int d = 0; d < 4; d++) {
				int nr = tmp.r + dr[d];
				int nc = tmp.c + dc[d];
				if(moveable(nr,nc) && map[tmp.r][tmp.c] > map[nr][nc]) {
					if(nr == M-1 && nc == N-1)
						cnt++;
					else
						q.add(new Pos(nr,nc));
				}
				
			}
		}
	}

	static boolean moveable(int r, int c) {
		if (r < 0 || r >= M || c < 0 || c >= N)
			return false;
		return true;
	}

	static class Pos {
		int r, c;

		public Pos(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
	}
}
