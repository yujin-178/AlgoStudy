import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B1194_달이차오른다가자_1 {
	static int N, M;
	static char[][] map;
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		int sr = 0, sc = 0;
		for (int r = 0; r < N; r++) {
			String tmp = br.readLine();
			for (int c = 0; c < M; c++) {
				map[r][c] = tmp.charAt(c);
				
				if (map[r][c] == '0') {
					sr = r;
					sc = c;
					map[r][c] = '.';
				}
			}
		}

		System.out.println(bfs(sr, sc));

	}

	static int bfs(int r, int c) {
		Queue<MinSik> q = new LinkedList<>();
		q.add(new MinSik(r, c));
		int cnt = 0;
		while (!q.isEmpty()) {
			int next = q.size();
			++cnt;
			for (int i = 0; i < next; i++) {
				MinSik tmp = q.poll();
				for (int d = 0; d < 4; d++) {
					int nr = tmp.r + dr[d];
					int nc = tmp.c + dc[d];
					if (moveable(nr, nc)) { // 일단 이동 가능한곳 (벽, 맵 밖 제외)
			
						if (map[nr][nc] == '1')
							return cnt;
						if (map[nr][nc] >= 'A' && map[nr][nc] <= 'F' && openDoor(nr, nc, tmp)) { // 문이 있을때
							q.add(new MinSik(nr, nc, tmp));
						} if (map[nr][nc] >= 'a' && map[nr][nc] <= 'f') { // 키가 있을 때
							pickUpKey(nr, nc, tmp);
							q.add(new MinSik(nr, nc, tmp));
						} if(map[nr][nc] == '.') { // '.' 인 공간
							q.add(new MinSik(nr, nc, tmp));
						}
						
					}
				}
			}
		}

		return -1;

	}

	static void pickUpKey(int r, int c, MinSik m) {
		m.key[map[r][c] - 'a'] = true;
	}

	static boolean openDoor(int r, int c, MinSik m) {
		if (m.key[map[r][c] - 'A'])
			return true;
		return false;
	}

	static boolean moveable(int r, int c) {
		if (r < 0 || r >= N || c < 0 || c >= M || map[r][c] == '#')
			return false;
		return true;
	}

	static class MinSik {
		int r, c;
		boolean[] key;

		public MinSik(int r, int c) {
			super();
			this.r = r;
			this.c = c;
			this.key = new boolean[6];
		}

		public MinSik(int r, int c, MinSik m) {
			super();
			this.r = r;
			this.c = c;
			this.key = new boolean[6];
			for (int i = 0; i < 6; i++) {
				if (m.key[i])
					this.key[i] = true;
			}

		}

	}

}
