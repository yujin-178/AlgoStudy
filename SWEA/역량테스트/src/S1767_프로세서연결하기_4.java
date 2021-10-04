import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 * @author 김유진
 *	비용을 기준으로 최소를 찾았는데
 *	연결 못하는 코어에 대해 최대치를 물려주고
 *	나중에 연결 못하는 코어의 전선길이를 0으로 바꿔주는 작업을 안해서 한참 고민했다.
 */
public class S1767_프로세서연결하기_4 {

	static int[][] map;
	static boolean[][] chk;
	static int N, min;
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };
	static LinkedList<P> p;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("1767_input"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int T = Integer.parseInt(st.nextToken());

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			chk = new boolean[N][N];
			p = new LinkedList<>();
			for (int r = 0; r < N; r++) {
				st = new StringTokenizer(br.readLine());
				for (int c = 0; c < N; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
					if (map[r][c] == 1) {
						chk[r][c] = true;
						p.add(new P(r, c));
					}
				}
			} // end 입력
			min = Integer.MAX_VALUE;
			dfs(0, 0);
			System.out.println("#" + tc + " " + min%144); // 연결 못하는 core 개수 * 144를 제거 하기위해서 144의 나머지만 활용한다.

		}
	}

//	static void showChk(int n, int sum) {
//		System.out.println(n + "번째 : " + sum + " ----------------------");
//		for (int r = 0; r < N; r++) {
//			for (int c = 0; c < N; c++) {
//				if (chk[r][c])
//					System.out.print("O ");
//				else
//					System.out.print("X ");
//			}
//			System.out.println();
//		}
//		System.out.println();
//	}

	static void dfs(int n, int sum) { // n번째 Core, 전선길이 합 sum
		if (min <= sum)
			return; // 합이 최소보다 크면 안함

		if (n == p.size()) { // 마지막에
			min = Math.min(min, sum); // 전선길이 갱신
//			showChk(n, sum);
			return;
		}
		int nr = p.get(n).r; // 위치 입력
		int nc = p.get(n).c;

		if (nr == 0 || nr == N - 1 || nc == 0 || nc == N - 1) {
			dfs(n + 1, sum); // 해당 위치가 가장자리면 그냥 다음으로 넘어감
		} else {
			for (int d = 0; d < 5; d++) { // 상우하좌 + 선없음
				if (d < 4) { // 상우하좌
					nr = p.get(n).r;
					nc = p.get(n).c;
					int tmp = check(p.get(n), d);
					if (tmp == -1)// -1이면 선을 연결하지 못함 스킵
						continue;
					while (!(nr == 0 || nr == N - 1 || nc == 0 || nc == N - 1)) { // 아니면 가장자리까지 체크
						nr += dr[d];
						nc += dc[d];
						chk[nr][nc] = true;
					}

					dfs(n + 1, sum + tmp); // 다음 dfs

					while (!(nr == p.get(n).r && nc == p.get(n).c)) { // 전선 흔적 지우기
						chk[nr][nc] = false;
						nr -= dr[d];
						nc -= dc[d];
					}

				} else { // 전선 연결하지 못한 경우
					dfs(n + 1, sum + 144);// 지도 최대 길이 12보다 큰 13을 더함
				}
			}
		}
	}

	static int check(P p, int d) { // 해당 방향으로 가장자리 까지 연결이 가능한지 확인
		int nr = p.r;
		int nc = p.c;
		int cnt = 0;

		while (true) {
			nc += dc[d];
			nr += dr[d];
			cnt++;
//			if (nr < 0 || nr >= N || nc < 0 || nc >= N || chk[nr][nc])
			if (chk[nr][nc])
				return -1; // 맵을 벗어나거나 이미 true라면 -1 리턴
			if (nr == 0 || nr == N - 1 || nc == 0 || nc == N - 1) {
				return cnt; // 가장자리 도착하면
			}

		}
	}

	static class P {
		int r;
		int c;

		public P(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}

	}

}
