import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class B14502_연구소_1 {
	static int N, M, maxSafe;
	static int[][] map; // 맵
	static boolean[][] chk; // 체크
	static Queue<Virus> v; // 바이러스 bfs용
	static Stack<Wall> w; // 벽 정보
	static ArrayList<Empty> e; // 빈공간만 체크 하면 좋다.
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("14502_input"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		chk = new boolean[N][M];
		v = new LinkedList<>();
		w = new Stack<Wall>();
		e = new ArrayList<>();
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < M; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				if (map[r][c] == 2) {
					v.add(new Virus(r, c));
				} else if (map[r][c] == 1) {
					w.push(new Wall(r, c));
				} else {
					e.add(new Empty(r, c));
				}
			}
		}
		makeWallComb(0, 0); 
		System.out.println(maxSafe);

	}

	static void initChk() { // chk 시뮬 전으로 초기화
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				if (map[r][c] == 0) {
					chk[r][c] = false;
				} else if (map[r][c] == 2) {
					v.add(new Virus(r, c));
				}
			}
		}
	}

	static int cntEmpty() { // 안전구역 세기
		int cnt = 0;
		for (int i = 0; i < e.size(); i++) {
			if (chk[e.get(i).r][e.get(i).c] == false)
				cnt++;
		}
		return cnt;
	}

	static void bfs() { // 바이러스 퍼트리기
		int nowOut = v.size();
		int nextOut = 0;

		while (true) {
			for (int i = 0; i < nowOut; i++) {
				if (v.isEmpty())
					break;

				Virus tmp = v.poll();

				for (int d = 0; d < 4; d++) {
					int newR = tmp.r + dr[d];
					int newC = tmp.c + dc[d];
					if (newR >= 0 && newR < N && newC >= 0 && newC < M && chk[newR][newC] == false) {
						v.add(new Virus(newR, newC));
						nextOut++;
					}
				}
			}

			if (v.isEmpty())
				break;

			nowOut = nextOut;
			nextOut = 0;
		}
	}

	static void makeWallComb(int r, int n) { // 벽 3개 선택하기
		if (r == 3) {
			bfs();// 3개가 정해지면 시뮬레이션 시작
			int tmp = cntEmpty();// 현재 안전구역 세고
			maxSafe = Math.max(maxSafe, tmp); // 최대치 고르고
			initChk(); // chk 초기화 및 바이러스 초기화
			return;
		}
		if (n == e.size()) // 선택 범위 넘는거 방지
			return;
		w.push(new Wall(e.get(n).r, e.get(n).c));
		makeWallComb(r + 1, n + 1);// 1개 선택하고 다음
		Wall tmp = w.pop();
		map[tmp.r][tmp.c] = 0; // 벽 제거하면서
		chk[tmp.r][tmp.c] = false; // chk도 해제
		makeWallComb(r, n + 1); // 선택 안하고 다음
	}

	static class Empty {
		int r;
		int c;

		public Empty(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}

	}

	static class Wall {
		int r;
		int c;

		public Wall(int r, int c) {
			super();
			this.r = r;
			this.c = c;
			chk[r][c] = true;
			map[r][c] = 1;
		}

	}

	static class Virus {
		int r;
		int c;

		public Virus(int r, int c) {
			super();
			this.r = r;
			this.c = c;
			chk[r][c] = true;
		}

	}
}
