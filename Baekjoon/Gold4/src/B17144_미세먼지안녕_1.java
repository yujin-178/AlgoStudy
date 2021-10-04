import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B17144_미세먼지안녕_1 {
	static int R, C, T;
	static int[][] map;
	static int[] Cdr = { -1, 0, 1, 0 };
	static int[] Cdc = { 0, 1, 0, -1 };
	static int[] CCdr = { 1, 0, -1, 0 };
	static int[] CCdc = { 0, 1, 0, -1 };
	static int[][] airCleaner;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		// 맵 크기 및 시간 입력
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		map = new int[R][C];
		airCleaner = new int[2][2];
		for (int r = 0; r < R; r++) { // input map
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < C; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				if (map[r][c] == -1) {
					airCleaner = new int[][] { { r - 1, c }, { r, c } };
				}
			}
		} // input map end
//		showMap(0);

		for (int tc = 1; tc <= T; tc++) { // T초만큼 진행

			diffuseDust(); // 먼지 확산
			topCleaner();
			bottomCleaner();
//			showMap(tc);
		} // for tc end

		System.out.println(sumDust());
	}

	static void showMap(int tc) {
		System.out.println(tc + "초 이후 상태 ----------------");
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				System.out.print(map[r][c] + " ");
			}
			System.out.println();
		}
	}

	static int sumDust() {
		int sum = 0;
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				if (map[r][c] > 0)
					sum += map[r][c];
			}
		}
		return sum;
	}

	static void bottomCleaner() {
		int d = 0;
		int nr = airCleaner[1][0] + CCdr[d];
		int nc = airCleaner[1][1] + CCdc[d];
		map[nr][nc] = 0;
		int exr = airCleaner[1][0];
		int exc = airCleaner[1][1];

		while (map[nr][nc] != -1) {
			if ((nr == R - 1 && exr == R - 2) || (nc == C - 1 && exc == C - 2)
					|| (nr == airCleaner[1][0] && exr == airCleaner[1][0] + 1)) {
				d++;
			}

			exr = nr;
			exc = nc;
			nr += CCdr[d];
			nc += CCdc[d];
			map[exr][exc] = map[nr][nc];
		}
		map[exr][exc] = 0;
	}

	static void topCleaner() {
		int d = 0;
		int nr = airCleaner[0][0] + Cdr[d];
		int nc = airCleaner[0][1] + Cdc[d];
		map[nr][nc] = 0;
		int exr = airCleaner[0][0];
		int exc = airCleaner[0][1];

		while (map[nr][nc] != -1) {
			if ((nr == 0 && exr == 1) || (nc == C - 1 && exc == C - 2)
					|| (nr == airCleaner[0][0] && exr == airCleaner[0][0] - 1)) {
				d++;
			}

			exr = nr;
			exc = nc;
			nr += Cdr[d];
			nc += Cdc[d];
			map[exr][exc] = map[nr][nc];
		}
		map[exr][exc] = 0;
	}

	static void diffuseDust() { // 먼지 확산
		int[][] impMap = new int[R][C];
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				if (map[r][c] >= 5) {
					int cnt = 0;
					int difDust = map[r][c] / 5;
					for (int d = 0; d < 4; d++) {
						int nr = r + Cdr[d];
						int nc = c + Cdc[d];
						// 공기 청정기 없고 지도 범위 내에서 먼지 확산
						if (nr >= 0 && nr < R && nc >= 0 && nc < C && map[nr][nc] != -1) {
							cnt++;
							impMap[nr][nc] += difDust;
						}
					}
					// 확산한 먼지 만큼 감소
					map[r][c] -= difDust * cnt;
				}
			}
		}

		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				map[r][c] += impMap[r][c];
			}
		}
	}
}
