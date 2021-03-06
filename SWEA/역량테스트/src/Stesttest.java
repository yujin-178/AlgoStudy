import java.util.LinkedList;
import java.util.Queue;

public class Stesttest {
	static int[][] map;
	static int[] dr = { 0, 0, -1, 1 };
	static int[] dc = { 1, -1, 0, 0 };

	public static void main(String[] args) {
		map = new int[][] { { 0, 0, 1, 0 }, { 0, 0, 0, 1 }, { 0, 0, 0, 0 }, { 0, 1, 0, 0 } };
		bfs();
		for (int r = 0; r < 4; r++) {
			for (int c = 0; c < 4; c++) {
				System.out.print(map[r][c] + " ");
			}
			System.out.println();
		}
	}

	static void bfs() {
		Queue<Pos> q = new LinkedList<>();
		for (int r = 0; r < 4; r++) {
			for (int c = 0; c < 4; c++) {
				if (map[r][c] == 1)
					q.add(new Pos(r, c));
			}
		}
		int day = 1;
		while (!q.isEmpty()) {
			day++;
			int l = q.size();
			for (int i = 0; i < l; i++) {
				Pos tmp = q.poll();
				for (int d = 0; d < 4; d++) {
					int nr = tmp.r + dr[d];
					int nc = tmp.c + dc[d];
					if (nr >= 0 && nr < 4 && nc >= 0 && nc < 4 && map[nr][nc] == 0) {

						q.add(new Pos(nr, nc));
						map[nr][nc] = day;
					}
				}
			}
		}
	}

	static class Pos {
		int r, c;

		public Pos(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

}
