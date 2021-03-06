import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B2239_스도쿠_2 {
	static boolean[][] col, row, zone;

	static int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		col = new boolean[9][10];
		row = new boolean[9][10];
		zone = new boolean[9][10];
		map = new int[9][9];

		for (int r = 0; r < 9; r++) {
			String tmp = br.readLine();
			for (int c = 0; c < 9; c++) {
				map[r][c] = tmp.charAt(c) - '0';
				col[r][map[r][c]] = true; // r행에 숫자가 있으면 true
				row[c][map[r][c]] = true; // c열에 숫자가 있으면 true
				zone[r / 3 * 3 + c / 3][map[r][c]] = true; // r / 3 + c % 3에 위치한 3by3 지역에 숫자가 있으면 true
//				System.out.print(r / 3 * 3 + c / 3);
			}
//			System.out.println();
		}
		
		StringBuilder sb = new StringBuilder();
		for (int r = 0; r < 9; r++) {
			for (int c = 0; c < 9; c++) {
				if (map[r][c] == 0) {
					for (int i = 1; i <= 9; i++) {
						if (chkCol(r, i) && chkRow(c, i) && chkZone(r, c, i)) {
							col[r][i] = true;
							row[c][i] = true;
							zone[r / 3 * 3 + c / 3][i] = true;
							map[r][c] = i;
							break;
						}
					}
				}
				sb.append(map[r][c]);
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());

	}

	static boolean chkCol(int r, int num) {
		if (col[r][num])
			return false;
		return true;
	}

	static boolean chkRow(int c, int num) {
		if (row[c][num])
			return false;
		return true;
	}

	static boolean chkZone(int r, int c, int num) {
		if (zone[r / 3 * 3 + c / 3][num])
			return false;
		return true;
	}
}
