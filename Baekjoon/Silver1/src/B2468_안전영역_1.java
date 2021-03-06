import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
  * @FileName : B2468_안전영역_1.java
  * @Date : 2021. 10. 3. 
  * @작성자 : KimYuJin
  * @특이점 : 수위가 0인 경우도 있다..... ㅠㅜ
  */
public class B2468_안전영역_1 {
	static int N, maxH, ans, tmp;
	static int[][] map;
	static boolean[][] chk;
	static int[] dr = { 0, 0, 1, -1 };
	static int[] dc = { 1, -1, 0, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		map = new int[N][N];

		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				int tmp = Integer.parseInt(st.nextToken());
				map[r][c] = tmp;
				if (tmp > maxH) // 지도 보다 높은 경우는 탐색할 필요가 없다.
					maxH = tmp; // 그래서 최대 높이 저장
			}
		}

		for (int h = 0; h <= maxH; h++) { // 최대 높이 보다 낮은 경우에만 체크
			tmp = 0; // 지역 수 확인
			chk = new boolean[N][N];
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					if (!chk[r][c]) // 방문하지 않은 곳이라면
						bfs(r, c, h); // bfs 수행
				}
			}
			ans = Math.max(ans, tmp);
		}
		System.out.println(ans);
	}

	static void bfs(int r, int c, int h) {
		chk[r][c] = true;

		if (map[r][c] <= h) // 물 높이 이하라면 그냥 리턴
			return;

		Queue<Pos> q = new LinkedList<>(); // 더 높은 경우 주변 지역 찾기 위해 시작
		q.add(new Pos(r, c));
		while (!q.isEmpty()) {
			Pos next = q.poll();
			for (int d = 0; d < 4; d++) { // 사방 탐색
				int nr = next.r + dr[d];
				int nc = next.c + dc[d];
				if (moveable(nr, nc)) { // 이동 가능 하다면
					chk[nr][nc] = true; // 일단 체크
					if (map[nr][nc] > h) // 높이가 물 높이 보다 높다면
						q.add(new Pos(nr, nc)); // 다음 탐색 수행
				}
			}
		}

		++tmp; // 탐색이 끝나면 지역 수 1 증가
		return;
	}

	static boolean moveable(int r, int c) {
		if (r < 0 || r >= N || c < 0 || c >= N || chk[r][c])
			return false;
		return true;
	}

	static class Pos {
		int r, c;

		public Pos(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}

	}
}
