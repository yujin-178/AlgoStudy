import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B9205_맥주마시면서걸어가기_복습_1 {
	static Pos[] p;

	static final int MAX = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("9205_input"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			p = new Pos[N + 2];
			for (int i = 0; i < N + 2; i++) {
				st = new StringTokenizer(br.readLine());
				p[i] = new Pos(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}

			int[][] map = new int[N + 2][N + 2];
			for (int r = 0; r < N + 2; r++) {
				for (int c = 0; c < N + 2; c++) {
					if (r == c)
						continue;
					int dis = calcDis(p[r], p[c]);
					map[r][c] = dis > 1000 ? MAX : dis;
				}
			}

			for (int k = 1; k < N + 1; k++) {
				for (int i = 0; i < N + 2; i++) {
					if (i == k)
						continue;
					for (int j = 0; j < N + 2; j++) {
						if (j == k || j == i)
							continue;
						if (map[i][j] == MAX && (map[i][k] == MAX || map[k][j] == MAX))
							map[i][j] = MAX;
						else if (map[i][k] == MAX || map[k][j] == MAX)
							map[i][j] = map[i][j];
						else
							map[i][j] = Math.min(map[i][k] + map[k][j], map[i][j]);

					}
				}
			}
			if (map[0][N - 1] != MAX)
				System.out.println("happy");
			else
				System.out.println("sad");

		}

	}

	static int calcDis(Pos p1, Pos p2) {
		return Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y);

	}

	static class Pos {
		int x;
		int y;
		public Pos(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
}