import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class B1992_쿼드트리_미완성_1 {
	static int N;
	static String[][] map;
	static boolean[][] chk;
	static Stack<String> s = new Stack<>();

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
		int mid = N / 2;
//		visit(mid, mid, mid / 2);
	}

	static void visit(int a, int b, int c, int d, int mid) {
		
	}
}
