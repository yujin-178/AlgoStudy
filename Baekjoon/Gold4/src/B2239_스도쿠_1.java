import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B2239_스도쿠_1 {
	static boolean[][] col, row, zone;

	static int[][] map, ans;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		col = new boolean[9][10];
		row = new boolean[9][10];
		zone = new boolean[9][10];
		map = new int[9][9];
		ans = new int[9][9];

		for (int r = 0; r < 9; r++) {
			String tmp = br.readLine();
			for (int c = 0; c < 9; c++) {
				map[r][c] = tmp.charAt(c) - '0';
				ans[r][c] = map[r][c];
				col[r][map[r][c]] = true; // r행에 숫자가 있으면 true
				row[c][map[r][c]] = true; // c열에 숫자가 있으면 true
				zone[r / 3 * 3 + c / 3][map[r][c]] = true; // r / 3 + c % 3에 위치한 3by3 지역에 숫자가 있으면 true
			}
		}

		dfs(0);
		for (int r = 0; r < 9; r++) {
			for (int c = 0; c < 9; c++) {
				System.out.print(map[r][c]);
			}
			System.out.println();
		}
	}

	static boolean dfs(int cnt) {
		if (cnt == 81) {
			return true;
		}

		int r = cnt / 9;
		int c = cnt % 9;
		int zIdx = r / 3 * 3 + c / 3;
		if (map[r][c] == 0) {
			for (int i = 1; i <= 9; i++) {
				if (!col[r][i] && !row[c][i] && !zone[zIdx][i]) {
					col[r][i] = true;
					row[c][i] = true;
					zone[zIdx][i] = true;
					map[r][c] = i;

					if (dfs(cnt + 1))
						return true;

					col[r][i] = false;
					row[c][i] = false;
					zone[zIdx][i] = false;
					map[r][c] = 0;
				}
			}
		} else {
			return dfs(cnt + 1);
		}

		return false;
	}

}
