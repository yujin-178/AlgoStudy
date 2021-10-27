import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author 김유진 bfs 개념을 활용해서 풀었다. 다리가 생성되는 시점까지 기다리는 방법을 추가해야하기 때문에 해당 방법을 map이
 *         1인 곳에는 Pos 객체를 계속 남겨두는 방법을 활용했다.
 */
public class S4727_견우와직녀_3 {
	static int N, M;
	static int[][] map;
	static int[][][] visited;
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
			visited = new int[N][N][2];

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
						if (col && row) // 횡, 종 동시에 있다면
							map[r][c] = -1; // 교차로
					}
				}
			} // end 교차로 절벽 체크

			System.out.println("#" + tc + " " + bfs());

		}

	}

	static void printView() {
		for (int r = 0; r < N; r++) {

		}
	}

	static int bfs() { // 이미 방문했던 모든 자리에 대해서 새로운 bfs를 수행함 용량이 매우 늘어나겠지만... 256다 사용하기전에 도착하지 않을까...?일단은
						// 이렇게 만들어보자
		int time = 1; // 시작 시간 일부러 1부터 시작함
		Queue<Pos> q = new LinkedList<Pos>();
		q.add(new Pos(0, 0, 0, false));
		visited[0][0][0] = time;
		while (!q.isEmpty()) { // q가 비어있다면 종료한다. 하지만 내 로직에서는 비어있는 경우가 존재하지 않는다....
			time++; // 시간이 증가한다.
			int nowQ = q.size(); // Queue에 들어있는 개수를 확인한다.
			for (int i = 0; i < nowQ; i++) { // Queue에 들어있는 모든 Pos에 대해서 탐색
				Pos now = q.poll(); // 탐색하고자 하는 Pos 출력
				if (map[now.r][now.c] == 1) // 맵이 1인 경우 (다리가 다음에 사라지지 않는, 해당 위치에 계속 존재할 수 있는)
					q.add(now); // !!!!!!!!!!!!!현재 위치를 다시 넣어준다. m 시간에 연결되는 다리를 건너기 위해서 기다리는 애들이다. 
				for (int d = 0; d < 4; d++) { // 사방 탐색
					int nr = now.r + dr[d]; // 다음 위치
					int nc = now.c + dc[d];
					if (nr < 0 || nc < 0 || nr >= N || nc >= N) // 맵을 벗어나면 넘어가 버린다.
						continue;
					if (nr == N - 1 && nc == N - 1) // 다음 위치가 목적지인 경우 종료한다.
						return time - 1; // time이 1부터 시작하니까 1을 빼준 값을 넘겨준다.
					if (map[nr][nc] == 1) { // 지도가 1인 경우
						if (visited[nr][nc][now.make] == 0) { // 0이라면 아직 탐색하지 않은 => bfs니까 가장 빨리 도착한 상태이다. 
							visited[nr][nc][now.make] = time; // 기록
							q.add(new Pos(nr, nc, now.make, false)); // 다음에 탐색할 지역 추가
						}
					} else { // 지도가 1이 아닌 경우
						if (now.step || map[nr][nc] == -1) // 직전 시간에 다리를 밟았거나 지도가 교차로(-1)인 경우
							continue; // 넘어감
						if (map[nr][nc] == 0) { // 협곡인경우 (다리를 만들어야 하는)
							if (now.make == 0 && visited[nr][nc][0] == 0 && (time - 1) % M == 0) { // 아직 다리를 만든 적이 없고, 방문한 지역이 아니고, M주기와 일치한다면
								visited[nr][nc][1] = time; // 방문한다.
								q.add(new Pos(nr, nc, 1, true)); // 다음에 탐색할 지역에 추가한다.
							}
						} else { // 만들어져 있는 다리인 경우
							if (visited[nr][nc][now.make] == 0 && (time - 1) % map[nr][nc] == 0) { // 아직 방문한 적이 없고, 다리가 유지되는 주기와 일치한다면
								visited[nr][nc][now.make] = time; // 방문한다.
								q.add(new Pos(nr, nc, now.make, true)); // 다음에 탐색할 지역에 추가한다.
							}
						}
					}
				} // end 사방 탐색
			} // end Queue 탐색
		}
		return time;
	}

	static class Pos {
		int r, c;
		int make; // 다리 만들기 이용권을 사용했는지?
		boolean step; // 직전에 다리를 건넜는지?

		public Pos(int r, int c, int make, boolean step) {
			this.r = r;
			this.c = c;
			this.make = make;
			this.step = step;
		}

	}
}
