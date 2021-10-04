import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B4485_녹색옷입은애가젤다지_2_bfs메모리초과 {
	static int[][] map, chk;
	static int N, minCost;
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("4485_input"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int cnt = 0;
		while (N != 0) { // N 이 0이면 종료
			map = new int[N][N]; // 지도 입력 시작
			chk = new int[N][N];
			for (int r = 0; r < N; r++) {
				st = new StringTokenizer(br.readLine());
				for (int c = 0; c < N; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
				}
			} // 지도 입력 종료

			// 탐색 시작
			chk[0][0] = map[0][0];
			minCost = Integer.MAX_VALUE;
			bfs(0, 0, map[0][0]);

			System.out.println("Problem" + " " + (++cnt) + ": " + minCost); // 출력
			// N 업데이트
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
		}
	}

	static void bfs(int r, int c, int sum) {
		Pos p = new Pos(r, c, sum);
		Queue<Pos> q = new LinkedList<>();
		q.add(p);

		while (q.size() != 0) {
			int next = q.size();
			for (int i = 0; i < next; i++) {
				Pos tmp = q.poll();
				for (int d = 0; d < 4; d++) {
					int nr = tmp.r + dr[d];
					int nc = tmp.c + dc[d];
					if (moveable(nr, nc)) {
						int nsum = tmp.sum + map[nr][nc];
						if (chk[nr][nc] == 0) {
							q.add(new Pos(nr, nc, nsum));
							chk[nr][nc] = nsum;
						} else if (chk[nr][nc] > nsum) {
							q.add(new Pos(nr, nc, nsum));
							chk[nr][nc] = nsum;
						}
						if (nr == N - 1 && nc == N - 1) {
							minCost = Math.min(minCost, nsum);
						}
					}

				}
			}

		}
	}

	static class Pos {
		int r, c, sum;

		public Pos(int r, int c, int sum) {
			super();
			this.r = r;
			this.c = c;
			this.sum = sum;
		}

	}

	static boolean moveable(int r, int c) {
		if (r < 0 || r >= N || c < 0 || c >= N)
			return false;
		return true;
	}
}
