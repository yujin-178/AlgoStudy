import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class J1733_오목_1 {
	static int[][] map;
	static int[] dr = { -1, 0, 1, 1 };
	static int[] dc = { 1, 1, 1, 0 };

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("1733_input"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		map = new int[19][19];

		for (int r = 0; r < 19; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < 19; c++) {
				map[r][c] = st.nextToken().charAt(0) - '0';
			}
		}
		for (int r = 0; r < 19; r++) {
			System.out.println(Arrays.toString(map[r]));
		}

		int winner = 0;
		int winR = 0;
		int winC = 0;
		out: for (int r = 0; r < 19; r++) {
			for (int c = 0; c < 19; c++) {
				if (map[r][c] != 0)
					winner = win(r, c);
				if (winner != 0) {
					winR = r;
					winC = c;
					break out;
				}

			}
		}

		if (winner == 0)
			System.out.println(0);
		else {
			System.out.println(winner);
			System.out.print((winR + 1) + " " + (winC + 1));
		}

	}

	static int win(int r, int c) {
		int stone = map[r][c];
		for (int d = 0; d < 4; d++) {
			int newR = r + dr[d];
			int newC = c + dc[d];
			int cnt = 1;
			while (newR >= 0 && newR < 19 && newC >= 0 && newC < 19 && map[newR][newC] == stone) {
				newR += dr[d];
				newC += dc[d];
				cnt++;
			}
			if (cnt == 5) {
				newR = r - dr[d];
				newC = c - dc[d];
				if (newR < 0 || newR >= 19 || newC < 0 || newC >= 19 || map[newR][newC] != stone)
					return stone;
			}
		}
		return 0;
	}
}
