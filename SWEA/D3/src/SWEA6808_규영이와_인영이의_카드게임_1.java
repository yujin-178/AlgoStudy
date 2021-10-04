import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA6808_규영이와_인영이의_카드게임_1 {
	static boolean[] cards;
	static int R = 9;
	static int[] me;
	static int[] you;
	static int scoreMe;
	static int scoreYou;
	static int winCnt;
	static int loseCnt;

	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("C:/CodingStudy/SWEA/D3/6808_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			cards = new boolean[19]; // 카드 초기화
			cards[0] = true;
			me = new int[9];
			you = new int[9];
			winCnt = 0; // 이김 초기화
			loseCnt = 0; // 짐 초기화
			for (int i = 1; i <= 9; i++) { // 카드 입력
				int tmp = Integer.parseInt(st.nextToken()); // 카드 순서대로
				cards[tmp] = true; // 카드 사용 중
				me[i-1] = tmp; // 내 카드 순서대로 받기
			}
			calcScore(0); // 0번째 턴 부터 시작
			System.out.println("#" + tc +" " +winCnt + " " + loseCnt); // 출력
		}
	}//end main
	
	static void calcScore(int idx) {
		if(idx == R) { //(0~8)수행 후 9번째는 결산
			scoreMe = 0; // 점수 초기화
			scoreYou = 0; // 점수 초기화
			for(int i = 0; i<9;i++) { // 0~8 라운드
				if(you[i] < me[i]) // 내가 카드가 더  높다면
					scoreMe = you[i] + me[i]; // 내 점수 증가
				if( you[i] > me[i]) // 당신의 카드가 더 높다면;
					scoreYou = you[i] + me[i]; // 당신의 점수 증가
			}
			if(scoreMe > scoreYou) // 내 점수가 높다면 
				++winCnt;// 내가 이김
			if(scoreMe <  scoreYou) // 당신의 점수가 높다면
				++loseCnt; // 내가 짐
			return; // 종료
		}
		else { // (0~8)라운드
			for(int i = 1;  i<= 18;i++) { // 카드 1~18까지 조회
				if(cards[i]) continue; // 해당 카드가 사용중이라면 skip
				cards[i] = true; // 카드 사용 중 표시
				you[idx] = i; // 당신에게 카드 
				calcScore(idx+1); // 다음 턴
				cards[i] = false; // 카드 사용 해재

			}
		}
	}
}
