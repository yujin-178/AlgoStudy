

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Test11315 {
//class Solution {
	public static void main(String args[]) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in)); // 버퍼생성
		int[] dx = { -1, -1, 0, 1, 1, 1, 0, -1 }; // 8방탐색
		int[] dy = { 0, 1, 1, 1, 0, -1, -1, -1 };
		int T = Integer.valueOf(bf.readLine()); // 테스트 케이스 

		for (int tc = 1; tc <= T; tc++) { // 테스트 케이스만큼 시뮬레이션
			int N = Integer.valueOf(bf.readLine()); // 맵의 크기 획득
			int[][] map = new int[N][N]; // 정방행렬 사이즈 생상
			//int cnt = 0; // 돌 개수 카운트 용 !!!!!!!!!!! 다 풀고 주석 달다 보니 이거 조건 안걸었음!!!!!! 수정중 // 다시 생각해보니 어차피 행렬탐색이 400개면 끝난다 의미 없다....
			int ansCnt = 0; // 이어진 돌 개수 체크용
			// int[][] pos = new int[400][2]; // 돌이 최대 400개 (20*20) 놓을 수 있어서 400개 저장 // 이것도 사용 안함;;;;
			for (int c = 0; c < N; c++) { // 행렬
				String s = bf.readLine(); // 라인 획득
//				System.out.println(s);
				for (int r = 0; r < N; r++) { // 
					char tmp = s.charAt(r); // 해당 위치의 문자 획득
					if (tmp == 'o') { // 돌이 있다면
						map[c][r] = 1; // 1로 설정
						//pos[cnt][0] = c; // 행 위치 추출 // 이것도 사용 안함
						//pos[cnt][1] = r; // 열 위치 추출 // 이것도 사용 안함
						//cnt++; // 돌의 최대 개수 파악 // 이것도 사용 안함 

					} else { // 돌이 없는 곳
						map[c][r] = 0; 
					}
//					System.out.print(map[c][r] + " ");
//					System.out.print(tmp+ " ");
				} // end r
//				System.out.println();
			}// end c
			boolean isWin = false; // 이김 상태 확인용
			for (int c = 0; c < N; c++) {
				for (int r = 0; r < N; r++) {
					if (map[c][r] == 1) {

						for (int dd = 0; dd < 8; dd++) {
							int x = c + dx[dd];
							int y = r + dy[dd];
							if (x >= 0 && x < N && y >= 0 && y < N) {// 벗어남 체크용
								if (map[x][y] == 1) { // 돌이 1이라면
									ansCnt = 2; // 앞에서 돌이 있고, 다음 돌(방향을 정하는) 돌을 체크한 상태에서 시작
									while (ansCnt <= 5) { // 5개 이상이 될 때까지
										
										x = x + dx[dd]; // 행의 다음 위치
										y = y + dy[dd]; // 열의 다음 위치
										if(x >= 0 && x < N && y >= 0  && y < N) {// 맵을 벗어난다면
										if ((map[x][y] != 1) || (isWin)) // 종료 조건 : 다음이 1이 아니면 더이상 탐색의미 없음, 이기면 그냥 종료
											break; // 종료
										else
											ansCnt++; // 돌이 있다면 계속 카운트
										if (ansCnt >= 5) // 5개 이상이라면 
											isWin = true; // 이김상태 설정
										} //맵 벗어남 조건
										else // 쭉 갔는데 5개 이상 조건을 충족시키지 못하면
											break; // 그냥 종료
									}
								}
							} // end 벗어남 체크용
							if (isWin)// 이김상태 
								break; // 종료
						} // end dd

					} // end map[c][r] == 1
					if (isWin == true) // 이김상태
						break; // 종료
				} // end r
				if (isWin == true) // 이김상태
					break; // 종료

			}// end c
			if (isWin == true) // 이김상태라면
				System.out.printf("#%d YES\n",tc); // 오목임을 알려줌
			else
				System.out.printf("#%d NO\n",tc); // 오목이 없음을 알려줌
		}// end tc

	}

}
