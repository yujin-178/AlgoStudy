

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//class Solution {
public class 달팽이숫자2 {
	public static void main(String args[]) throws IOException {
		int[] dc = { 0, 1, 0, -1 };
		int[] dr = { 1, 0, -1, 0 };
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.valueOf(br.readLine());
		for (int i = 1; i <= N; i++) {
			int mapSize = Integer.valueOf(br.readLine());
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
		br.close();
	}
}
