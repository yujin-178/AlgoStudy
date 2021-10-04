import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class B10026_적록색약_1 {
	static boolean[][][] chk;
	static char[][][] color;
	static int N;
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("10026_input"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int cnt = 0, cnt2 = 0;
		N = Integer.parseInt(br.readLine());
		color = new char[2][N][N];

		chk = new boolean[2][N][N];
		for (int n = 0; n < N; n++)
			color[0][n] = br.readLine().toCharArray();

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				color[1][r][c] = (color[0][r][c] == 'R' ? 'G' : color[0][r][c]);
				if (!chk[0][r][c]) {
					findSwarm(r, c, color[0][r][c], 0);
					++cnt;
				}
			}
		}
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (!chk[1][r][c]) {
					findSwarm(r, c, color[1][r][c], 1);
					++cnt2;
				}
			}
		}
		System.out.println(cnt + " " + cnt2);

	}

	static void findSwarm(int r, int c, char ans, int chkNum) {
		for (int d = 0; d < 4; d++) {
			int newC = c + dc[d];
			int newR = r + dr[d];
			if (possible(newR, newC, ans, chkNum)) {
				chk[chkNum][newR][newC] = true;
				findSwarm(newR, newC, ans, chkNum);
			}
		}
	}

	static boolean possible(int r, int c, char ans, int chkNum) {
		if (r >= N || r < 0 || c >= N || c < 0 || chk[chkNum][r][c] || color[chkNum][r][c] != ans)
			return false;
		return true;
	}
}
