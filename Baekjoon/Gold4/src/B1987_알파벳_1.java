import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B1987_알파벳_1 {
	static boolean[] alpa;
	static boolean[][] chk;
	static char[][] map;
	static int R, C, step;
	static int[] dr = { 0, 1, 0, -1 };
	static int[] dc = { 1, 0, -1, 0 };
	static boolean select;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("1987_input"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		alpa = new boolean[26];
		map = new char[R][C];
		chk = new boolean[R][C];

		for (int r = 0; r < R; r++) {
			map[r] = br.readLine().toCharArray();
		}
		chk[0][0] = true;
		alpa[map[0][0] - 'A'] = true;
		Go(0, 0, 1);
		System.out.println(step);

	}

	static void Go(int r, int c, int cnt) {
		if (cnt == 26) {
			step = cnt;
			return;
		}

		for (int d = 0; d < 4; d++) {
			int newR = r + dr[d];
			int newC = c + dc[d];
			if (avail(newR, newC)) {
				alpa[map[newR][newC] - 'A'] = true;
//				chk[newR][newC] = true;
				Go(newR, newC, cnt + 1);
				alpa[map[newR][newC] - 'A'] = false;
//				chk[newR][newC] = false;
			}

		}
		if (step < cnt)
			step = cnt;
		return;

	}

	static boolean avail(int r, int c) {
		if (r < 0 || r >= R || c < 0 || c >= C || chk[r][c] == true || alpa[map[r][c] - 'A'] == true)
			return false;
		return true;
	}

}
