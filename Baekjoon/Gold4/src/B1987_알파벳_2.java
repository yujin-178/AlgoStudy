import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B1987_알파벳_2 {
	static boolean[] alpha;
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
		alpha = new boolean[26];
		map = new char[R][C];

		for (int r = 0; r < R; r++) {
			map[r] = br.readLine().toCharArray();
		}

		alpha[map[0][0] - 'A'] = true;
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
				alpha[map[newR][newC] - 'A'] = true;
				Go(newR, newC, cnt + 1);
				alpha[map[newR][newC] - 'A'] = false;
			}

		}
		if (step < cnt)
			step = cnt;
		return;

	}

	static boolean avail(int r, int c) {
		if (r < 0 || r >= R || c < 0 || c >= C || alpha[map[r][c] - 'A'] == true)
			return false;
		return true;
	}

}
