import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//public class Main {
public class B7576_1 {
	static int[][] map;
	static int N, M;
	static int dayCnt;
	static Queue<Tomato> q = new LinkedList<>();
	static Queue<Tomato> nextQ = new LinkedList<>();
	static int[] dr = { 0, 1, 0, -1 };
	static int[] dc = { 1, 0, -1, 0 };

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("7576_input"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for (int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine());
			for (int m = 0; m < M; m++) {
				map[n][m] = Integer.parseInt(st.nextToken());
				if (map[n][m] == 1)
					q.add(new Tomato(n, m));
			}
		}
		do {
			bfs();

			while (!nextQ.isEmpty()) {
				q.add(nextQ.poll());
			}

			dayCnt++;

		} while (!q.isEmpty());
		boolean notEnd = false;
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				if (map[r][c] == 0)
					notEnd = true;
			}
		}
		if (notEnd)
			System.out.println(-1);
		else
			System.out.println(dayCnt - 1);

	}

	static void bfs() {
		while (!q.isEmpty()) {
			for (int d = 0; d < 4; d++) {
				int nextC = q.peek().c + dc[d];
				int nextR = q.peek().r + dr[d];
				if (nextC < 0 || nextC >= M || nextR < 0 || nextR >= N || map[nextR][nextC] == -1)
					continue;
				if (map[nextR][nextC] == 0) {
					nextQ.add(new Tomato(nextR, nextC));
					map[nextR][nextC] = 1;
				}
			}
			q.poll();
		}
	}

	static class Tomato {
		int r;
		int c;

		public Tomato(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}

	}
}
