import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @FileName : B2636_치즈_1.java
 * @Date : 2021. 9. 15.
 * @작성자 : KimYuJin
 * @특이점 :
 */
public class B2636_치즈_1 {
	static int C, R, cheese;
	static int[][] map;
	static boolean[][] chk;
	static Queue<Pos> q;
	static int[] dr = { 0, 1, 0, -1 };
	static int[] dc = { 1, 0, -1, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		map = new int[R][C];
		q = new LinkedList<Pos>();

		for (int r = 0; r < R; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < C; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
//				System.out.print(map[r][c] + " ");
				if (map[r][c] == 1)
					cheese++;
			}
//			System.out.println();
		}
		
		int cnt = 0;
		int ans = 0;
		while (cheese != 0) {
			ans = 0;
			q.add(new Pos(0, 0));
			chk = new boolean[R][C];
			chk[0][0] = true;
			bfs();
//			if(cnt == 0)
//				showChk();
			for (int r = 0; r < R; r++) {
				for (int c = 0; c < C; c++) {
					if (map[r][c] == 1) {
						for (int d = 0; d < 4; d++) {
							int nr = r + dr[d];
							int nc = c + dc[d];
							if (chk[nr][nc]) {
								map[r][c] = 0;
								ans++;
								break;
							}
						}
					}
				}
			}
			cheese -= ans;
//			System.out.println(cheese);
			cnt++;
		}
		System.out.println(cnt);
		System.out.println(ans);
	}

	static void showChk() {
		System.out.println();
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				if(chk[r][c])
					System.out.print("1 ");
				else
					System.out.print("0 ");
					
			}
			System.out.println();
		}
	}

	static void bfs() {
		while (!q.isEmpty()) {
			Pos tmp = q.poll();
			for (int d = 0; d < 4; d++) {
				int nr = tmp.r + dr[d];
				int nc = tmp.c + dc[d];
				if (mapOut(nr, nc) && !chk[nr][nc] && map[nr][nc] == 0) {
					q.add(new Pos(nr, nc));
					chk[nr][nc] = true;
				}
			}

		}
		return;
	}

	static boolean mapOut(int r, int c) {
		if (r < 0 || r >= R || c < 0 || c >= C)
			return false;
		return true;
	}

	static class Pos {
		int r;
		int c;

		public Pos(int r, int c) {
			super();
			this.r = r;
			this.c = c;
//			System.out.println(r+","+c);
		}
	}
}
