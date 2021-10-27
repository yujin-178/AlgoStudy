import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class S4727_견우와직녀_2 {
	static int N, M;
	static int[][] map;
	static int[][] visited;
	static int[] dr = { 1, 0, -1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			visited = new int[N][N];

			for (int r = 0; r < N; r++) { // map input
				st = new StringTokenizer(br.readLine());
				for (int c = 0; c < N; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
				}
			} // end map input

			for (int r = 0; r < N; r++) { // 교차로 절벽 체크
				for (int c = 0; c < N; c++) {
					if (map[r][c] == 0) {
						boolean row = false;
						boolean col = false;
						for (int d = 0; d < 4; d++) {
							int nr = r + dr[d];
							int nc = c + dc[d];
							if (nr >= 0 && nc >= 0 && nr < N && nc < N && map[nr][nc] == 0) { // 맵을 벗어나지 않고 주변이 절벽인 경우
								if (d == 0 || d == 2) // 종으로 절벽이 있는 경우
									row = true;
								if (d == 1 || d == 3) // 횡으로 절벽이 있는 경우
									col = true;
							}
						}
						if (col && row)
							map[r][c] = -1; // 교차로
					}
				}
			} // end 교차로 절벽 체크

			System.out.println("#" + tc + " " + bfs());

		}

	}

	static int bfs() { // 이미 방문했던 모든 자리에 대해서 새로운 bfs를 수행함 용량이 매우 늘어나겠지만... 256다 사용하기전에 도착하지 않을까...?일단은
						// 이렇게 만들어보자
		int time = 1; // 시작 시간 일부러 1부터 시작함
		Queue<Pos> q = new LinkedList<Pos>();
		q.add(new Pos(0, 0, false, false));
		visited[0][0] = time;
		while (!q.isEmpty()) {
			time++;
			int nowQ = q.size();
			for (int i = 0; i < nowQ; i++) { // Queue 탐색
				Pos now = q.poll();
				if (map[now.r][now.c] == 1)
					q.add(now); // !!!!!!!!!!!!!현재 위치를 다시 넣어준다. 기다리는 애
				for (int d = 0; d < 4; d++) { // 사방 탐색
					int nr = now.r + dr[d];
					int nc = now.c + dc[d];
					if (nr < 0 || nc < 0 || nr >= N || nc >= N) // 맵을 벗어나면
						continue;

					if (nr == N - 1 && nc == N - 1) // 목적지에 도착하면
						return time - 1;

					if (map[nr][nc] == 1 && visited[nr][nc] == 0) { // 이미 방문한 곳은 추가로 확장할 필요가 없다.
						visited[nr][nc] = time;
						q.add(new Pos(nr, nc, now.make, false));
					}
					if (visited[nr][nc] == 0 && !now.step && map[nr][nc] != 1 && map[nr][nc] != -1 && map[nr][nc] != 0
							&& (time - 1) % map[nr][nc] == 0) {
						visited[nr][nc] = time;
						q.add(new Pos(nr, nc, now.make, true));
					}
					if (visited[nr][nc] == 0 && !now.step && !now.make && map[nr][nc] != -1 && map[nr][nc] == 0 && (time - 1) % M == 0) {
						// 다리 만들기 찬스를 사용하지 않은 경우
						visited[nr][nc] = time;
						q.add(new Pos(nr, nc, true, true));
					}
				} // end 사방 탐색
			} // end Queue 탐색
		}

		return time;
	}

	static class Pos {
		int r, c;
		boolean make;
		boolean step;

		public Pos(int r, int c, boolean make, boolean step) {
			this.r = r;
			this.c = c;
			this.make = make;
			this.step = step;
		}

	}
}
