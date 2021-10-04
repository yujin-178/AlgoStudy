import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.IOException;

//public class Main {
public class Baek2563_1 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int num = Integer.parseInt(br.readLine());
		boolean[][] board = new boolean[101][101];
		for (int i = 1; i <= num; i++) {
			st = new StringTokenizer(br.readLine());
			int col = Integer.parseInt(st.nextToken());
			int row = Integer.parseInt(st.nextToken());
			
			for(int c = col; c<= col+9;c++) {
				for(int r= row; r<= row+9;r++) {
					board[c][r] = true;
				}
			}
		}
		int cnt = 0;
		for(int c = 1; c<= 100;c++) {
			for(int r= 1; r<= 100;r++) {
				if(board[c][r])
					++cnt;
			}
		}
		System.out.println(cnt);
	}
}
