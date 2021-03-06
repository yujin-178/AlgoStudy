import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class B2667_단지번호붙이기_1 {
	static int[] dr = { 0, 0, 1, -1 };
	static int[] dc = { 1, -1, 0, 0 };
	static char[][] map;
	static boolean[][] chk;
	static int N;
	static LinkedList<Integer> ans;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new char[N][N];
		chk = new boolean[N][N];
		for (int n = 0; n < N; n++) {
			map[n] = br.readLine().toCharArray();
		}
		int cnt = 0;
		ans = new LinkedList<>();
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (!chk[r][c] && map[r][c] == '1') {
					cnt++;
					bfs(r, c);
				}
			}
		}
		System.out.println(cnt);
		Collections.sort(ans);
		for (int a : ans) {
			System.out.println(a);
		}
	}

	static void bfs(int r, int c) {
		Queue<Pos> q = new LinkedList<>();
		q.add(new Pos(r, c));
		chk[r][c] = true;
		int cnt = 1;
		while (!q.isEmpty()) {
			Pos now = q.poll();
			for (int d = 0; d < 4; d++) {
				int nr = now.r + dr[d];
				int nc = now.c + dc[d];
				if (moveable(nr, nc) && map[nr][nc] == '1') {
					cnt++;
					q.add(new Pos(nr, nc));
					chk[nr][nc] = true;
				}
			}
		}
		ans.add(cnt);

	}

	static boolean moveable(int r, int c) {
		if (r < 0 || c < 0 || r >= N || c >= N || chk[r][c])
			return false;
		return true;
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
