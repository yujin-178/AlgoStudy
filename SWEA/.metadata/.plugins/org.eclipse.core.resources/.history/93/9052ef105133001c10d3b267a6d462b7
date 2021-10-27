import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class S4727_견우와직녀_예인 {
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static int N, M;
	static int[][] map;
	static int[][][] cost;// 해당 좌표에도착하는 시간 기록하면서

	static class Point {
		int x;
		int y;
		int time;
		int cnt;//

		Point(int x, int y, int time, int cnt) {
			this.x = x;
			this.y = y;
			this.time = time;
			this.cnt = cnt;
		}
	}

	static int ans;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int TC = sc.nextInt();
		for (int tc = 1; tc <= TC; tc++) {
			N = sc.nextInt();
			M = sc.nextInt();
			map = new int[N][N];
			cost = new int[N][N][2];// 방문했을떄기준으로 시간과 방문안했을떄 기준으로 시간을 체크해준다.
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					map[i][j] = sc.nextInt();
					cost[i][j][0] = Integer.MAX_VALUE;
					cost[i][j][1] = Integer.MAX_VALUE;
				}
			} // input end
			ans = Integer.MAX_VALUE;
			bfs();

			System.out.println("#" + tc + " " + ans);
		}

	}

	private static void bfs() {
		Queue<Point> q = new LinkedList<>();
		q.add(new Point(0, 0, 0, 0));
		cost[0][0][0] = 0;
		while (!q.isEmpty()) {
			Point p = q.poll();
			if (p.x == N - 1 && p.y == N - 1) {
				ans = ans > p.time ? p.time : ans;
				continue;
			}
			for (int t = 0; t < 4; t++) {
				int x = p.x + dx[t];
				int y = p.y + dy[t];
				if (x >= 0 && x < N && y >= 0 && y < N) {
					if (map[x][y] == 1) {
						// 이미 방문한애가 더 짧은 시간에 왔다면 갈 필요 없음
						if (cost[x][y][p.cnt] > p.time + 1) {
							cost[x][y][p.cnt] = p.time + 1;
							q.add(new Point(x, y, p.time + 1, p.cnt));
						}

					} else if (map[x][y] == 0 && map[p.x][p.y] == 1 && p.cnt == 0) { // 다리를 만들어서 건너는 경우
						// 크로스이면 안됨
						if (isCross(x, y))
							continue;

						// M의 배수중 가장 가까운 시간에 건넌다.
						int time = M;
						while (p.time > time) {
							time += M;
						} // 건널 수 있는 시간이 결정됨

						if (cost[x][y][1] > time) {
							cost[x][y][1] = time;
							q.add(new Point(x, y, time, 1));
						}

					} else if (map[x][y] > 1 && map[p.x][p.y] == 1) {
						// map[x][y]
						int time = map[x][y];
						while (p.time > time) {

							time += map[x][y];
						}

						if (cost[x][y][p.cnt] > time) {
							cost[x][y][p.cnt] = time;
							q.add(new Point(x, y, time, p.cnt));
						}
					}
				}
			}

		}

	}

	private static boolean isCross(int x, int y) {
		for (int t = 0; t < 4; t++) {
			int j = (t + 2) % 4;
			int r1 = x + dx[t];
			int c1 = y + dy[t];
			int r2 = x + dx[j];
			int c2 = y + dy[j];
			if (r1 >= 0 && r1 < N && c1 >= 0 && c1 < N && r2 >= 0 && r2 < N && c2 >= 0 && c2 < N && map[r1][c1] == 0
					&& map[r2][c2] == 0) {
				return true;
			}
		}
		return false;
	}
}