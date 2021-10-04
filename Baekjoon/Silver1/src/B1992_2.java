import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class B1992_2 {
	static int N;
	static String[][] map;
	static boolean[][] chk;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("1992_input"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new String[N][N];
		chk = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			String tmp = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = tmp.charAt(j) + "";
			}
		} // end 입력
		visit(0, 0, N);
		String ans = sb.toString();
		for (int i = 1; i != N * 2; i *= 2) {
			ans = ans.replace("(0000)", "0");
			ans = ans.replace("(1111)", "1");
		}
		System.out.println(ans);
	}

	static void visit(int r, int c, int mid) {
		if (mid == 0) {
			sb.append(map[r][c]);
			return;
		}

		sb.append("(");
		visit(r, c, mid / 2);
		visit(r, c + mid / 2, mid / 2);
		visit(r + mid / 2, c, mid / 2);
		visit(r + mid / 2, c + mid / 2, mid / 2);
		sb.append(")");
	}
}
