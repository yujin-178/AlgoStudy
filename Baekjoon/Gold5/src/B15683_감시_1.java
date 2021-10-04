import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class B15683_감시_1 {
	static int M, N, ans;
	static char[][] map;
	static ArrayList<Camera> other;
	static ArrayList<Camera> five;
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };
	static boolean chk[][];

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("15683_input"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		ans = Integer.MAX_VALUE;
		map = new char[N][M];
		other = new ArrayList<>();
		five = new ArrayList<>();
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < M; c++) {
				map[r][c] = st.nextToken().charAt(0);
				if (map[r][c] == '5') {
					five.add(new Camera(r, c, 5));
				} else if (map[r][c] <= '4' && map[r][c] >= '1') { // 문자로 받았으니까!!!!
					other.add(new Camera(r, c, map[r][c] - '0')); // 문자로 입력하자!!!!
				}
			}
		} // end input data

		mapSetting();
//		showMap();
		permu(0);
		System.out.println(ans);

	}

	static void showMap() { // map 확인용
		System.out.println();
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				System.out.print(map[r][c] + " ");
			}
			System.out.println();
		}
	}

	static void showChk() { // chk 확인용
		System.out.println();
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				System.out.print(chk[r][c] ? "o " : "x ");
			}
			System.out.println();
		}
	}

	static int simul() { // 시뮬레이션 수행
		chkSetting(); // 초기 chk 세팅

		for (int i = 0; i < other.size(); i++) { // 1~4 카메라를 감시영역 체크

			switch (other.get(i).type) {
			case 1: // 1번 카메라는 한 방향만
				watch(i, other.get(i).dir);
				break;
			case 2: // 2번 카메라는 현재 주시 하는 방향과 반대 방향을
				watch(i, other.get(i).dir);
				watch(i, (other.get(i).dir + 2) % 4);
				break;
			case 3: // 3번 카메라는 현재 방향과 시계방향 90도 방향을
				watch(i, other.get(i).dir);
				watch(i, (other.get(i).dir + 1) % 4);
				break;
			case 4: // 4번 카메라는 현재 방향, 시계방향 90도, 반대방향 까지 3방향을
				watch(i, other.get(i).dir);
				watch(i, (other.get(i).dir + 1) % 4);
				watch(i, (other.get(i).dir + 2) % 4);
				break;
			}
		}

		return cntChk(); // 감시영역 카운트 리턴

	}

	static int cntChk() { // 감시 영역 카운트
		int cnt = 0;
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				if (chk[r][c] == false)
					++cnt;
			}
		}
		return cnt;
	}

	static void watch(int i, int d) { // i번째 카메라가 보고 있는 dir방향 감시 체크
		int r = other.get(i).r + dr[d];
		int c = other.get(i).c + dc[d];
		while (regionChk(r, c)) {
			chk[r][c] = true;
			r += dr[d];
			c += dc[d];
		}
	}

	static void permu(int r) { // 1~4번 카메라 방향 회전 하는 중복순열 4^other.size()
		if (r == other.size()) { // 사이즈랑 같으면 시뮬 수행 및 최소 사각지대 갱신
			ans = Math.min(ans, simul());
//			showChk();
			return;
		}

		for (int d = 0; d < 4; d++) {
			other.get(r).dir = d; // 감시카메라 방향 돌리기
			permu(r + 1); // 다음 감시 카메라 설정하러
		}

	}

	static void chkSetting() { // 5번 카메라 영역까지 포함한 잔여 공간으로 시뮬 지도 생성
		chk = new boolean[N][M];
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				if (map[r][c] != '0')
					chk[r][c] = true;
			}
		}
	}

	static void mapSetting() { // 5번 카메라는 돌릴 필요 없다.
		for (int i = 0; i < five.size(); i++) {
			for (int d = 0; d < 4; d++) {
				Camera tmp = five.get(i);
				int newR = tmp.r + dr[d];
				int newC = tmp.c + dc[d];
				while (regionChk(newR, newC)) {
					map[newR][newC] = '#';
					newR += dr[d];
					newC += dc[d];
				}
			}
		} // map setting complete
	}

	static boolean regionChk(int r, int c) { // 감시카메라 감시 영역 확장 가능?
		if (r < 0 || r >= N || c < 0 || c >= M || map[r][c] == '6') // 6이 아니라 '6' 이었다.!!!!
			return false;
		return true;
	}

	static class Camera { // 카메라 클래스
		int r, c;
		int dir;
		int type;

		public Camera(int r, int c, int type) {
			super();
			this.r = r;
			this.c = c;
			this.dir = 0;
			this.type = type;
		}
	}
}
