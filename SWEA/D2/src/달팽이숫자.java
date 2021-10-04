

import java.util.Scanner;
//import java.io.FileInputStream;
//class Solution {
public class 달팽이숫자 {
	public static void main(String args[]) throws Exception {
		int[] dc = { 0, 1, 0, -1 };
		int[] dr = { 1, 0, -1, 0 };
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		sc.nextLine();// 개행문자 날리기
		for (int i = 1; i <= N; i++) {
			int mapSize = sc.nextInt();
			sc.nextLine(); // 개행문자 날리기
			int[][] map = new int[mapSize][mapSize]; // map make
			for (int c = 0; c < mapSize; c++) { // map init 런타임 에러 혹시 몰라서 
				for (int r = 0; r < mapSize; r++) {
					map[c][r] = 0;
				}
			} // end map init
			int col = 0;
			int row = -1;
			int dir = 0;
			int cnt = 1;
			while ((mapSize * mapSize) >= cnt) { // simul
				col += dc[dir % 4];
				row += dr[dir % 4];
				if (!((col >= 0) && (col < mapSize) && (row >= 0) && (row < mapSize) && (map[col][row] == 0))) {
					col -= dc[dir % 4];
					row -= dr[dir % 4];
					dir++;
					col += dc[dir % 4];
					row += dr[dir % 4];
				}
				map[col][row] = cnt++;
			} // end simul
			System.out.println("#" + i); // output
			for (int c = 0; c < mapSize; c++) {
				for (int r = 0; r < mapSize; r++) {
					System.out.print(map[c][r] + " ");
				}
				System.out.println();
			} // end output
		} // end for
		sc.close();
	}
}
