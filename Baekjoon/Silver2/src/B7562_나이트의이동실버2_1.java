import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B7562_나이트의이동실버2_1 {
	static int[] dr = { -2, -1, 1, 2, 2, 1, -1, -2 }; // 나이트 이동에 따른 8방 탐색
	static int[] dc = { 1, 2, 2, 1, -1, -2, -2, -1 };
	static int N;
	static boolean[][] chk;
	static int desR, desC;
	static Queue<Pos> q;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("7562_input"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int T = Integer.parseInt(st.nextToken()); // tc 횟수 입력

		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine()); // 지도크기
			chk = new boolean[N][N]; // chk 지도 생성
			q = new LinkedList<>(); // q 초기화
			st = new StringTokenizer(br.readLine()); // 시작위치 받아서
			q.add(new Pos(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));// 시작 생성
			st = new StringTokenizer(br.readLine()); // 도착지 정보 받아서
			desR = Integer.parseInt(st.nextToken()); // 갱신
			desC = Integer.parseInt(st.nextToken());
			// ---------------------------------------------입력종료
			boolean arrival = false;// 도착 플래그 오프
			int outNum = 1; // 처음에 한개(시작위치) 들어 있어서
			int nextOut = 0; // 다음 스텝에 몇개 꺼내야 하는지 저장용
			int step = 0; // 몇번 동작했는지?

			while (true) {// simul 시작
				for (int i = 0; i < outNum; i++) {
					Pos tmp = q.poll(); // 일단 뱉어본다.
					if (tmp.r == desR && tmp.c == desC) { // 뱉은 애가 도착한 상태라면
						arrival = true; // 도착 플래그 온
						break; // 반복문 종료
					}
					for (int d = 0; d < 8; d++) { // 8방 탐색
						int newR = tmp.r + dr[d];
						int newC = tmp.c + dc[d];
						if (newR >= 0 && newR < N && newC >= 0 && newC < N && chk[newR][newC] == false) {
							q.add(new Pos(newR, newC));
							++nextOut;
						}
					}
				}
				if (arrival) // 도착했다면 종료
					break;
				++step; // 스텝 증가
				outNum = nextOut; // 뽑을 횟수 갱신
				nextOut = 0; // 초기화
			} // end simul
			System.out.println(step);
		} // end tc
		br.close();
	}// end main

	static class Pos {
		int r;
		int c;

		public Pos(int r, int c) {
			super();
			this.r = r;
			this.c = c;
			chk[r][c] = true; // 어차피 Queue에 들어갈 때 체크할거니까 생성하면 chk해버리자
		}

	}
}
