import java.util.LinkedList;
import java.util.Queue;

public class Stesttest3 {
	static int[][] map;
	static boolean[][] chk;
	static int[] dr = { 0, 0, -1, 1 };
	static int[] dc = { 1, -1, 0, 0 };

	public static void main(String[] args) {
		map = new int[][] { { 0, 1, 1, 1, 0 }, { 0, 0, 0, 1, 0 }, { 0, 1, 1, 1, 0 }, { 0, 0, 0, 0, 0 } };
		chk = new boolean[4][5];
		System.out.println(bfs());
		for (int r = 0; r < 4; r++) {
			for (int c = 0; c < 5; c++) {
				System.out.print(map[r][c] + " ");
			}
			System.out.println();
		}
	}

	static int bfs() {
		Queue<Pos> q = new LinkedList<>();
		q.add(new Pos(0, 0));
		chk[0][0] = true;
		int day = 0;
		while (!q.isEmpty()) {
			day++;
			int l = q.size();
			for (int i = 0; i < l; i++) {
				Pos tmp = q.poll();

				for (int d = 0; d < 4; d++) {
					int nr = tmp.r + dr[d];
					int nc = tmp.c + dc[d];
					if (nr < 0 || nr >= 4 || nc < 0 || nc >= 5 || chk[nr][nc] || map[nr][nc] == 1)
						continue;
					if (nr == 0 && nc == 4)
						return day;
					q.add(new Pos(nr, nc));
					chk[nr][nc] = true;

				}
			}
		}
		return -1;
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
