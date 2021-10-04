import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B16236_아기상어_1 {
	static boolean[][] chk;
	static int[][] map;
	static int N, eat, step;
	static Shark s;
	static int fish;
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, -1, 0, 1 };

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("16236_input"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		fish = 0;

		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				if (map[r][c] == 9)
					s = new Shark(r, c);
				else if (map[r][c] > 0 && map[r][c] <= 6) {
					++fish; // 남은 물고기 총합
				}
			}
		}

		eat = 0; // 먹은 물고기 수
		step = 0; // 몇번 움직였는지?
		while (fish != 0) { // 남은 물고기가 0이 라면 종료
			chk = new boolean[N][N]; // 매 턴 마다 새로 이동 가능 탐색해야함
			boolean next = bfs(s.r, s.c); // 탐색 물고기를 먹었다면 다음도 수행함
			if (fish != 0 && !next) { // 남은 물고기가 0이 아니고 현재 물고기를 먹지 않았다면
				step = 0; // 0으로
				break; // 종료
			}
		}
		System.out.println(step);

	}

	static boolean bfs(int r, int c) {
		Queue<Pos> q = new LinkedList<>();
		q.add(new Pos(s.r, s.c));
		int now = 1;
		int next = 0;
		boolean eatFish = false;
		int sec = 0;
		while (!(eatFish || q.isEmpty())) { // 물고기를 먹었거나 Queue가 비어있다면 종료
			for (int i = 0; i < now; i++) { // 현재 탐색해야 할 위치 만큼
				Pos tmp = q.poll(); // 현재 탐색 위치
				for (int d = 0; d < 4; d++) { // 4방 탐색
					int nr = tmp.r + dr[d]; // 다음 위치
					int nc = tmp.c + dc[d]; // 갱신
					if (possible(nr, nc)) { // 이동 가능 한지?

						if (eat(nr, nc)) { // 물고기를 먹었다면
							eatFish = true; // 물고기 먹음
							break; // 종료
						}
						chk[nr][nc] = true;// 탐색 가능하면 다음 위치 체크
						q.add(new Pos(nr, nc)); // 다음 위치 저장
						++next; // 다음 Queue 개수 증가
					}
				}
				if (eatFish) // 물고기를 먹었다면
					break; // 탐색 종료
			}
			++sec; // 시간 흐름
			now = next; // 다음에 나올 큐 개수 파악
			next = 0; // 개수 초기화
		}
		if (eatFish) {
			step += sec;
		}
		return eatFish;
	}

	static boolean possible(int r, int c) {
		if (r >= N || r < 0 || c >= N || c < 0 || chk[r][c] || (map[r][c] > s.w))
			return false;
		return true;
	}

	static boolean eat(int r, int c) {
		if (map[r][c] < s.w && map[r][c] > 0) {
			map[r][c] = 0;
			s.r = r; // 위치 갱신
			s.c = c;
			--fish;
			if (s.w == (++eat)) { //
				++s.w;
				eat = 0;
			}
			return true;
		}
		return false;
	}

	static class Pos {
		int r;
		int c;

		public Pos(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}

	}

	static class Shark {
		int r;
		int c;
		int w;

		public Shark(int r, int c) {
			super();
			this.r = r;
			this.c = c;
			this.w = 2;
		}
	}
}
