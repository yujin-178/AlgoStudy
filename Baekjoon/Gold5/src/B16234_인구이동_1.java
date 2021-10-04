import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B16234_인구이동_1 {
	static int N, R, L;
	static int[][] map;
	static boolean[][] chk;
	static int[] dr = { 0, 0, 1, -1 };
	static int[] dc = { 1, -1, 0, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		map = new int[N][N];

		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		int step = 0;
		int cnt = 1;
		while (cnt != 0) {
			cnt = 0;
			chk = new boolean[N][N];
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					if (!chk[r][c])
						cnt += bfs(r, c);
				}
			}
		}
	}

	static int bfs(int r, int c) {
		int cnt = 0;

		Queue<P> q = new LinkedList<>();
		q.add(new P(r, c));
		chk[r][c] = true;
		while (!q.isEmpty()) {
			P tmp = q.poll();

		}

		return cnt;
	}

	static class P {
		int r;
		int c;

		public P(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}

	}
}
