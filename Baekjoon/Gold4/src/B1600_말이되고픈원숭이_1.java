import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @FileName : B1600_말이되고픈원숭이_1.java
 * @Date : 2021. 9. 15.
 * @작성자 : KimYuJin
 * @특이점 :
 */
public class B1600_말이되고픈원숭이_1 {
	static int[] hdr = { -2, -2, -1, 1, 2, 2, 1, -1 };
	static int[] hdc = { -1, 1, 2, 2, 1, -1, -2, -2 };
	static int[] dr = { 1, 0, -1, 0 };
	static int[] dc = { 0, -1, 0, 1 };
	static int[][][] map;
	static int K, W, H;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		K = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		map = new int[H][W][2];
		for (int r = 0; r < H; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < W; c++) {
				map[r][c][0] = Integer.parseInt(st.nextToken());
				if (map[r][c][0] == 1)
					map[r][c][0] = Integer.MAX_VALUE; // 어차피 방문한 곳은 안갈꺼니까 걍 장애물은 최대치로 해버리자
			}
		}

		map[H - 1][W - 1][0] = bfs();
		System.out.println(map[H - 1][W - 1][0]);
		showMap();

	}

	static void showMap() {
		System.out.println();
		for (int r = 0; r < H; r++) {
			for (int c = 0; c < W; c++) {
				if (map[r][c][0] == Integer.MAX_VALUE)
					System.out.print("x ");
				else
					System.out.print(map[r][c][0] + " ");

			}
			System.out.println();
		}
	}

	static int bfs() {
		Queue<Pos> q = new LinkedList<Pos>();
		q.add(new Pos(0, 0, 0));
		if (0 == H - 1 && 0 == W - 1)
			return 0;
		while (!q.isEmpty()) { // q가 비어 있으면 밑에 return -1 만나서 -1리턴

			Pos tmp = q.poll(); // 처음은 위치 (0,0), 횟수= 0, k= 0

			for (int d = 0; d < 4; d++) { // 일단 1칸씩 움직인 결과
				int nr = tmp.r + dr[d];
				int nc = tmp.c + dc[d];
				if (mapOut(nr, nc) && map[nr][nc][0] == 0) { // 갈 수 있으면
					if (nr == H - 1 && nc == W - 1)
						return map[tmp.r][tmp.c][0] + 1;
					q.add(new Pos(nr, nc, tmp.k)); // 큐에 넣고
					map[nr][nc][1] = tmp.k; // 맵에 현재 k사용 기록
					map[nr][nc][0] = map[tmp.r][tmp.c][0] + 1; // 맵에 현재 횟수 기록
				}
			}

			if (tmp.k < K) { // k의 사용이 K보다 작을 때만 가능
				for (int d = 0; d < 8; d++) { // 말처럼 움직일 때
					int nr = tmp.r + hdr[d];
					int nc = tmp.c + hdc[d];
					if (mapOut(nr, nc) && map[nr][nc][0] == 0) { // 방문하지 않은 공간만
						if (nr == H - 1 && nc == W - 1)
							return map[tmp.r][tmp.c][0] + 1;
						q.add(new Pos(nr, nc, tmp.k + 1));
						map[nr][nc][1] = tmp.k + 1;
						map[nr][nc][0] = map[tmp.r][tmp.c][0] + 1;
					}

				}
			}

		}

		return -1;
	}

	static boolean mapOut(int r, int c) {
		if (r < 0 || r >= H || c < 0 || c >= W)
			return false;
		return true;
	}

	static class Pos {
		int r;
		int c;
		int k;

		public Pos(int r, int c, int k) {
			super();
			this.r = r;
			this.c = c;
			this.k = k;
		}

	}
}
