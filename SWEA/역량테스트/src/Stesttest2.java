import java.util.LinkedList;
import java.util.Queue;

public class Stesttest2 {
	static int[][] map;
	static int[] dr = { 0, 0, -1, 1 };
	static int[] dc = { 1, -1, 0, 0 };

	public static void main(String[] args) {
		map = new int[3][4];

		bfs(1, 1);
		for (int r = 0; r < 3; r++) {
			for (int c = 0; c < 4; c++) {
				System.out.print(map[r][c] + " ");
			}
			System.out.println();
		}
	}

	static void bfs(int row, int col) {
		Queue<Pos> q = new LinkedList<>();
		q.add(new Pos(row, col));
		int day = 1;
		map[row][col] = day;
		while (!q.isEmpty()) {
			day++;
			int tmpsize = q.size();
			for (int i = 0; i < tmpsize; i++) {
				Pos tmp = q.poll();
				for (int d = 0; d < 4; d++) {
					int nr = tmp.r + dr[d];
					int nc = tmp.c + dc[d];
					if (nr >= 0 && nr < 3 && nc >= 0 && nc < 4 && map[nr][nc] == 0) {
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
			super();
			this.r = r;
			this.c = c;
		}
	}
}
