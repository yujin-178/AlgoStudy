

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Test문제해결2일차2_Ladder1 {
//class Solution {
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("C:/CodingStudy/SWEA/D3/Ladder1_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for (int i = 1; i <= 10; i++) {//----------------------------------10번 탐색
			int tmp = Integer.valueOf(br.readLine());
			int[][] map = new int[100][100];
			StringTokenizer st;
			for (int col = 0; col < 100; col++) { // --------------------------입력
				st = new StringTokenizer(br.readLine());
				for (int row = 0; row < 100; row++) {
					map[col][row] = Integer.valueOf(st.nextToken());
//					System.out.print(map[col][row] + " ");
				}
//				System.out.println();
			}// ---------------------------------------------------------------end 입력
			int ans = 0;
			boolean lose = false;
			for(int sRow = 0; sRow<100;sRow++) {
				if(map[0][sRow] == 1) { // ----------------------------------0행렬 1인 지점
//					System.out.print(sRow + " ");
					int col = 0;
					int row = sRow;
					while(col<100) {
						if((row - 1 >= 0) && (map[col][row - 1] == 1 )) {
							while((row>=0)&&(map[col][row] == 1)) row--;
							row++;
							col++;
						}else if((row + 1 < 100)&& (map[col][row+1] == 1)) {
							while((row<100)&& (map[col][row] == 1)) row++;
							row--;
							col++;
						}
						else {
							col++;
						}
					}
					if(map [col-1][row]==2) {
						ans = sRow;
						lose = true;
						break;
					}
				
				}
			}
			System.out.println("#"+i+" " +ans);
			
		} //---------------------------------------------------------------end 10번 탐색

		br.close();
	}
}
