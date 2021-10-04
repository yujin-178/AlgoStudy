import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B4963_섬의개수_1 {
	static int W, H, cnt;
	static int[][] map;
	static int[] dr = new int[] { -1, -1, -1, 0, 1, 1, 1, 0 };
	static int[] dc = new int[] { -1, 0, 1, 1, 1, 0, -1, -1 };

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		while (true) {
			st = new StringTokenizer(br.readLine());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			if (W == 0 && H == 0)
				break;
			cnt = 0;
			map = new int[H][W];
			for (int h = 0; h < H; h++) {
				st = new StringTokenizer(br.readLine());
				for (int w = 0; w < W; w++) {
					map[h][w] = Integer.parseInt(st.nextToken());
				}
			}

			for (int h = 0; h < H; h++) {
				for (int w = 0; w < W; w++) {
					if (map[h][w] == 1) {
						dfs(h, w);
						cnt++;
					}
				}
			}
			System.out.println(cnt);

		}

	}

	static void dfs(int r, int c) {
		for (int d = 0; d < 8; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			if (chk(nr, nc) && map[nr][nc] == 1) {
				map[nr][nc] = 0;
				dfs(nr, nc);
			}
		}
	}

	static boolean chk(int r, int c) {
		if (r < 0 || r >= H || c < 0 || c >= W)
			return false;
		return true;
	}
}
