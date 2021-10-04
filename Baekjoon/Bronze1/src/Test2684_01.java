

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
//import java.util.Arrays;

public class Test2684_01 {
//public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		String s = bf.readLine();
		int P = Integer.valueOf(s); // 시뮬 횟수 기록
		int[] cntCase = new int[8]; // 3 동전수 케이스 일치 기록용
		boolean[] input = new boolean[40]; // 입력 데이터 40개짜리
		boolean[][] chkCase = { { false, false, false }, { false, false, true }, { false, true, false }, 
				{ false, true, true }, { true, false, false }, { true, false, true }, { true, true, false },
				{ true, true, true } }; // 체크할 케이스
		for (int p = 1; p <= P; p++) { //  P만큼 시뮬레이션 수행
			s = bf.readLine();
			for (int i = 0; i < 40; i++) {
				if (s.charAt(i) == 'H') // 입력문자 H
					input[i] = true;
				else // 입력문자 T
					input[i] = false;
			}// end i
			for (int i = 0; i < 8; i++) { // 초기화
				cntCase[i] = 0;
			} // end i
			for (int i = 0; i < 8; i++) { // 3 동전수 케이스
				for (int j = 0; j < 38; j++) { // 입력 38개 비교 a(n),a(n+1),a(n+2)이므로 38.39.40  
					if((chkCase[i][0] == input[j]) && (chkCase[i][1] == input[j+1]) && (chkCase[i][2] == input[j+2])) { // 이부분 손코딩 틀림 마지막 j랑i헷갈림
						cntCase[i]++; // 3개 요소가 일치한다면 해당 인덱스의 숫자가 증가
					}// end if
				}// end j
			}// end i
			for(int i= 0; i<8;i++) {
				System.out.print(cntCase[i] + " "); // 출력
			}
			System.out.println(); // 이부분 손코딩 틀림

		} // end p

	}
}
