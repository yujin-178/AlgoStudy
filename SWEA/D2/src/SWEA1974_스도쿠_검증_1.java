import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//public class Solution {
public class SWEA1974_스도쿠_검증_1 {
	static boolean[] chk;
	static int[][] map = new int[9][9];

	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("C:/CodingStudy/SWEA/D2/1974_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			for (int i = 0; i < 9; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < 9; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			boolean ans = false;
			for(int i = 0;i<9;i++) {
				if(ans) continue;
				if(!verticalChk(i))
					ans = true;
			}
			for(int i = 0;i<9;i++) {
				if(ans) continue;
				if(!horizenChk(i))
					ans = true;
			}
			for(int i = 0;i<3;i++) {
				for(int j = 0;j<3;j++) {
				if(ans) continue;
				if(!blockChk(i, j))
					ans = true;
				}
			}
			if(ans)
				System.out.println("#"+tc+" " + 0);
			else
				System.out.println("#"+tc+" " + 1);
				

		}

	}

	static boolean verticalChk(int idx) {
		chk = new boolean[10];
		chk[0] = true;
		for (int i = 0; i < 9; i++) {
			chk[map[i][idx]] = true;
		}
		for (int i = 1; i < 10; i++) {
			if (!chk[i])
				return false;
		}
		return true;
	}

	static boolean horizenChk(int idx) {
		chk = new boolean[10];
		chk[0] = true;
		for (int i = 0; i < 9; i++) {
			chk[map[idx][i]] = true;
		}
		for (int i = 1; i < 10; i++) {
			if (!chk[i])
				return false;
		}
		return true;
	}

	static boolean blockChk(int col, int row) {
		chk = new boolean[10];
		chk[0] = true;
		for (int i = col * 3; i < col * 3 + 3; i++) {
			for (int j = row * 3; j < row * 3 + 3; j++) {
				chk[map[i][j]] = true;
			}
		}
		for (int i = 1; i < 10; i++) {
			if (!chk[i])
				return false;
		}
		return true;
	}
}
