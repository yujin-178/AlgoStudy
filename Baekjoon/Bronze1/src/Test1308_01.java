

import java.io.BufferedReader;//BufferedReader 사용
import java.io.InputStreamReader;// InputStreamReader 사용
import java.io.IOException;//IOException 사용
import java.util.StringTokenizer; //손코딩에서 빼먹음, StringTokenizer 사용

public class Test1308_01 { // 클래스
//public class Main {
	public static void main(String[] args) throws IOException { // 메인 메서드
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 한줄 문자열로 입력 받음 (today)
		StringTokenizer st = new StringTokenizer(br.readLine()); // " " 공백을 기준으로 문자열 분할
		int[] today = new int[3]; // 오늘 년/월/일
		int[] dDay = new int[3]; // D-day 년/월/일6

		for (int i = 0; i < today.length; i++) {
			today[i] = Integer.valueOf(st.nextToken()); // 분할 받은 문자열 숫자로 받아서 입력
		} // end i

		st = new StringTokenizer(br.readLine()); // 손코딩에서 빼먹음, 다음줄 읽어오기 (d-Day)

		for (int i = 0; i < dDay.length; i++) {
			dDay[i] = Integer.valueOf(st.nextToken());// 분할 받은 문자열 숫자로 받아서 입력
		} // end i

		if (today[0] + 1000 < dDay[0]) // 천년 이상인 경우 탈주
			System.out.print("gg");
		else if ((today[0] + 1000 == dDay[0]) && (today[1] < dDay[1])) // 년이 딱 천년일때 월이 더 긴지?
			System.out.print("gg");
		else if ((today[0] + 1000 == dDay[0]) && (today[1] == dDay[1]) && (today[2] <= dDay[2])) // 월까지 같으면 일이 더 긴지? !!!! 여기 '<=' 틀려서 90퍼에서 틀림 
			System.out.print("gg");
		else { // 이제부터 캠프 D-day 기다리는 경우
			int cnt = 0;// 날짜 세는 변수
			int[] month = { 0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 }; // index 0은 0으로 처리 1~12 사용
			final int LEAP_TWO_MONTH = 29;
			while (!((today[0] == dDay[0]) && (today[1] == dDay[1]) && (today[2] == dDay[2]))) {
				today[2]++;
				cnt++;
				int fullDay = 0;
				if (chkLeap(today[0]) && (today[1] == 2)) // 윤년이고 2월인 경우
					fullDay = LEAP_TWO_MONTH;
				else
					fullDay = month[today[1]]; // 윤년 아니면 월 별 말일 사용
				if (today[2] > fullDay) { // 일 초과시 월 증가
					today[2] = 1;
					today[1]++;
				} // end if
				if (today[1] > 12) { // 월 초과시 년 증가
					today[1] = 1;
					today[0]++;
				} // end if
			} // end while cnt++
			System.out.print("D-" + cnt);
		} // end D-day

	}// end main

	public static boolean chkLeap(int y) {
		if (y % 400 == 0)
			return true;
		else if (y % 100 == 0)
			return false;
		else if (y % 4 == 0)
			return true;
		else
			return false;
	}
}// end class
