import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Battle_물고기와바닷새_실패 {
	static int R, C, ans;

	static int[][] map;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			map = new int[R][C];
			for (int r = 0; r < R; r++) {
				st = new StringTokenizer(br.readLine());
				for (int c = 0; c < C; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
				}
			}

			ans = Integer.MAX_VALUE;
			simul(-1, 1, 0);
			System.out.println("#" + tc + " " + ans);
		}
	}

	static void simul(int col, int dep, int time) {
		if (time > 30)
			return;

		if (ans < time)
			return;
		if (chkFish(dep)) {
			if (col >= -1 && col <= C - 2) {
				int nc = col + 1;
				int[] tmp = huntFish(nc, dep);
				if (tmp[0] == -1) {
					simul(nc, dep, time + 1);
				} else {
					map[tmp[0]][tmp[1]] = 0;
					simul(nc, dep + 1, time + 2);
				}
			}
			if (col >= 1 && col <= C - 1) {
				int nc = col - 1;
				int[] tmp = huntFish(nc, dep);
				if (tmp[0] == -1) {
					simul(nc, dep, time + 1);
				} else {
					map[tmp[0]][tmp[1]] = 0;
					simul(nc, dep + 1, time + 2);
				}
			}

		} else {
			ans = Math.min(ans, time);
			return;
		}
	}

	static int[] huntFish(int col, int dep) {
		int[] ans = { -1, col };
		for (int r = 0; r < dep; r++) {
			if (map[r][col] == 2)
				break;
			if (map[r][col] == 1) {
				ans[0] = r;
				return ans;
			}
		}
		return ans;
	}

	static boolean chkFish(int dep) {
		for (int c = 0; c < C; c++) {
			for (int r = 0; r < dep; r++) {
				if (map[r][c] == 2)
					break;
				if (map[r][c] == 1)
					return true;
			}
		}
		return false;
	}

}
