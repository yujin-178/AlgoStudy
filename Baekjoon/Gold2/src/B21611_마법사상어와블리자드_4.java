import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
  * @FileName : B21611_마법사상어와블리자드_4.java
  * @Date : 2021. 10. 8. 
  * @작성자 : KimYuJin
  * @특이점 : bead[] 배열로 시뮬레이션에 따른 구슬을 상태를 업데이트 해놓고
  * 		map[][] 2차원 배열을 마법 사용하는 구간에서 break 조건으로 사용해서 한참 헤맸다.
  * 		조건 분기에 사용되는 파라미터를 좀 더 구체적으로 구상하고 사용해야겠다...
  */
public class B21611_마법사상어와블리자드_4 {
	static int N, M, score;
	static int[][] map;
	static int[][] com;
	static int[] dr = { 1, 0, -1, 0 };
	static int[] dc = { 0, 1, 0, -1 };
	static int[][] changeIdx;
	static int[] bead;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][N];
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		com = new int[M][2]; 
		for (int m = 0; m < M; m++) { // 하, 우, 상, 좌 순서로 사용하기 위해서 변환
			st = new StringTokenizer(br.readLine()); 
			switch (Integer.parseInt(st.nextToken())) {
			case 1:
				com[m][0] = 2;
				break;
			case 2:
				com[m][0] = 0;
				break;
			case 3:
				com[m][0] = 3;
				break;
			case 4:
				com[m][0] = 1;
				break;
			}
			com[m][1] = Integer.parseInt(st.nextToken());
		}

		setMatrix(); // 2차원 map에서 1차원 bead에 저장하는 함수

		for (int m = 0; m < M; m++) {
			simul(m);
		}
		System.out.println(score);
	}

	static void simul(int m) {
		// 블리자드
		int sr = N / 2 + dr[com[m][0]]; // 초기값 거리 증가
		int sc = N / 2 + dc[com[m][0]]; // 초기값 거리 증가
		int a = 0; // 사거리 비교용
		for (int i = 0; i < N * N - 1; i++) {
			if (changeIdx[i][0] == sr && changeIdx[i][1] == sc) { // 새로운 위치와 일치하는 인덱스면
				bead[i] = 0; // 0으로 만들고
				sr += dr[com[m][0]]; // 다음 위치
				sc += dc[com[m][0]]; // 다음 위치
				a++; // 거리 증가
				if (a == com[m][1]) // 사거리와 같아지면 종료
					break;
			}
		}

		setBead(); // 빈자리 메꾸기
//		showMap(m);

		// 구슬 파괴
		int exScore = -1; // 실제 score는 0이상이기 때문에 한번은 무조건 실행기키기 위해서 초기값으로 -1 사용
		while (score != exScore) { // 더이상 터질 구슬이 없을 때까지 반복(이전 점수와 지금 점수가 같은 경우)
			exScore = score; // 비교용으로 이전 점수 업데이트
			int exBead = bead[0]; // 비교용 구슬 
			int cnt = 1; // 비교용 구슬 선택했으니 1개
			for (int i = 1; i < N * N - 1; i++) { // 맵 끝까지
				if (exBead == bead[i]) // 구슬이 같으면 
					cnt++; // 개수 증가
				else { // 다른 구슬을 만난 순간
					if (cnt >= 4) { // 같은 구슬이 4개 이상이면
						int c = 0; // 터뜨릴 구슬 위치
						for (c = 0; c < cnt; c++) { // cnt개 만큼 초기화
							score += bead[i - c - 1]; // 현재 i는 달라진 구슬 index 즉 -1을 해줘야 같다고 센 구슬 index를 선택할 수 있다.
							bead[i - c - 1] = 0; // 구슬 없애기
						}
					}
					cnt = 1; // 새로운 구슬들을 비교해야 함
					exBead = bead[i]; // 구슬 값 업데이트
				}

			}

			setBead(); // 빈자리 메꾸기
		}

//		showMap(m);
		bead = increaseBead(); // 구슬 재구성
//		showMap(m);
	}

	static void setBead() { // 구슬이 중간에 터져있으면 나머지 구슬들을 앞으로 땡겨서 메꾸는 메서드
		int[] tmpBead = new int[N * N - 1]; // 임시 구슬 배열
		int idx = 0; // 새로운 구슬 인덱스
		for (int i = 0; i < N * N - 1; i++) {
			if (bead[i] != 0) // 차례대로 0이 기존 구슬 배열에서 0이 아닌 경우
				tmpBead[idx++] = bead[i]; // 새로운 배열에 채워준다.
		}
		for (int i = 0; i < N * N - 1; i++) {
			bead[i] = tmpBead[i]; // 새로운 배열로 업데이트 해준다.
		}
	}

	static int[] increaseBead() { // 3번째 단계 구슬 집합으로 구슬 재구성
		int[] newBead = new int[N * N - 1];
		int exBead = bead[0]; // 비교용 이전 구슬
		int cnt = 1; // 현재 0번째 한개
		int idx = 0; // 새로운 구슬 인덱스
		int i = 0; // 탐색하는 이전 구슬 인덱스
		while (bead[i++] != 0) { // 앞에서 구슬을 전부 땡겼기 때문에 0만나면 더이상 안해도 된다.
			if (exBead == bead[i]) // 이전 구슬과 같다면
				cnt++; // 개수 증가
			else { // 다른 경우
				newBead[idx++] = cnt; // 이전까지 센 개수 
				newBead[idx++] = exBead; // 센 구슬 의 종류
				if (idx >= N * N - 1) // 맵을 벗어나면
					break; // 종료

				exBead = bead[i]; // 이전 구슬을 새로 갱신
				cnt = 1; // 갱신하거 한개라 1로 갱신
			}
		}
		return newBead; // 새로 만든 구슬 배열 리턴
	}

	static void setMatrix() { // 초기 맵으로 1차원 구슬 배열 생성하는 메서드
		changeIdx = new int[N * N - 1][2]; // 구슬 index 가 map의  r,c를 저장하는 배열
		bead = new int[N * N - 1];// 구슬의 개수는 맵 크기 -1 (상어)
		int r = N / 2; // r 초기 위치
		int c = N / 2 - 1; // c 초기 위치
		int d = 0; // 방향
		int cnt = 0; // 구슬 인덱스
		while (!(r == 0 && c == -1)) { // 순서대로 돌면 맵을 벗어나는 순간이 0,-1 이다.

			if (map[r][c] != 0) // 맵이 0이 아니면
				bead[cnt] = map[r][c]; // 맵의 정보를 구슬에 저장
			changeIdx[cnt][0] = r; // r위치 저장
			changeIdx[cnt++][1] = c; // c위치 저장
			// 좌하 & 우상 대각선 || 좌상 대각선 || 우하 대각선
			if ((r == N - 1 - c) || (r < N / 2 && r - 1 == c) || (r > N / 2 && r == c))
				d = (++d % 4); // 방향 전환은 4 미만
			r += dr[d]; // r 위치 갱신
			c += dc[d]; // c 위치 갱신

		}
	}

	static void showMap(int m) { // 디버깅용 메서드
		System.out.println();
		System.out.println(m + 1 + "번째");
		int[][] map = new int[N][N];
		for (int i = 0; i < N * N - 1; i++) {
			int r = changeIdx[i][0];
			int c = changeIdx[i][1];
			map[r][c] = bead[i];
		}
		for (int r = 0; r < N; r++) {

			for (int c = 0; c < N; c++) {
				System.out.print(map[r][c] + " ");
			}
			System.out.println();
		}
	}

}
