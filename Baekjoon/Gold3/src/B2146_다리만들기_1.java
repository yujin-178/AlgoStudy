import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B2146_다리만들기_1 {
	static int N;
	static int[][] map;
	static int[] dr = { 0, 0, -1, 1 };
	static int[] dc = { -1, 1, 0, 0 };
	static Land[] l;
	static boolean[][] chk;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		chk = new boolean[N][N];
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		make();
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (map[r][c] != 0 && !chk[r][c])
					unionBfs(r, c);
			}
		}

		l = new Land[N * N];
		int cnt = 0;
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (map[r][c] != 0) {
					for (int d = 0; d < 4; d++) {
						int nr = r + dr[d];
						int nc = c + dc[d];
						if (nr >= 0 && nr < N && nc >= 0 && nc < N && map[nr][nc] == 0) {
							l[cnt++] = new Land(map[r][c], r, c);
							break;
						}
					}
				}
			}
		}
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < cnt; i++) {
			for (int j = 0; j < cnt; j++) {
				if (i == j || l[i].type == l[j].type)
					continue;
				min = Math.min(min, Math.abs(l[i].r - l[j].r) + Math.abs(l[i].c - l[j].c));
			}

		}
		System.out.println(min - 1);
	}

	static void make() {
		int type = 1;
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (map[r][c] == 1)
					map[r][c] = type++;
			}
		}
	}

	static void unionBfs(int r, int c) {
		Queue<Land> q = new LinkedList<>();
		q.add(new Land(map[r][c], r, c));
		chk[r][c] = true;

		while (!q.isEmpty()) {
			Land tmp = q.poll();
			for (int d = 0; d < 4; d++) {
				int nr = tmp.r + dr[d];
				int nc = tmp.c + dc[d];
				if (nr >= 0 && nr < N && nc >= 0 && nc < N && !chk[nr][nc] && map[nr][nc] != 0
						&& map[nr][nc] != tmp.type) {
					map[nr][nc] = tmp.type;
					chk[nr][nc] = true;
					q.add(new Land(tmp.type, nr, nc));
				}
			}

		}
	}

	static class Land {
		int type;
		int r;
		int c;

		public Land(int type, int r, int c) {
			super();
			this.type = type;
			this.r = r;
			this.c = c;
		}
	}
}
