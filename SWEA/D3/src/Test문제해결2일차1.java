

import java.io.BufferedReader;
import java.io.FileInputStream;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Test문제해결2일차1 {
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("C:/CodingStudy/SWEA/D3/Ladder1_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for (int i = 1; i <= 10; i++) {// 10번 탐색
			int tmp = Integer.valueOf(br.readLine());
			int[][] map = new int[100][100];
			StringTokenizer st;
			for (int col = 0; col < 100; col++) {
				st = new StringTokenizer(br.readLine());
				for (int row = 0; row < 100; row++) {
					map[col][row] = Integer.valueOf(st.nextToken());
				}
			}
			int loseIdx = 0;
			boolean lose = false;

			for (int sRow = 0; sRow < 100; sRow++) { // 첫 행의 1찾기 탐색
//				System.out.println(sRow);
				int col = 0;
				if (map[0][sRow] == 1) { // 1인경우 시작
					int row = sRow;
//					while ((map[col][row] == 100) && (col++ < 100)) { // 사다리 타기 로직 시작
					while ((col++ < 99)) { // 사다리 타기 로직 시작
						if ((row + 1 < 100) && ((row + 1) >= 0) && (map[col][row + 1] == 1)) { // 우측이 연결
							while ((row + 1 < 100) && ((row + 1) >= 0) && (map[col][row++] == 1))
								;
							row -= 2;
							col++;
						} else if ((row - 1 < 100) && ((row - 1) >= 0) && (map[col][row - 1] == 1)) { // 좌측이 연결
							while ((row - 1 < 100) && ((row - 1) >= 0) && (map[col][row--] == 1))
								;
							row += 2;
							col++;
						}
//						System.out.println(col + " " + row);
					}
					System.out.println(map[col - 2][row] == 2);
					if (map[col - 2][row] == 2) { // 패배 조건
						lose = true;
						loseIdx = sRow;
						break;
//						System.out.println("찾음");
					} // end 패배 조건

				} // end if 첫행 1
				if (lose) {
					break;
				}
			} // end 첫행 1 찾기

			System.out.println("#" + tmp + " " + loseIdx);
		} // end 10번 탐색

		br.close();
	}
}
