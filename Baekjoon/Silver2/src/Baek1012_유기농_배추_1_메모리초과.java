import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//public class Main {
public class Baek1012_유기농_배추_1_메모리초과 {
	static int M, N, K;
	static int[][] map;
	static boolean[][] chk;
	static LinkedList<Cabbage> cabb;
	static Queue<Cabbage> out;
	static int ans;
	static int[] dr = { 0, 1, 0, -1 };
	static int[] dc = { 1, 0, -1, 0 };

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("C:/CodingStudy/Baekjoon/Silver2/1012_input"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());

		for (int tc = 1; tc <= T; tc++) {
			ans = 0;
			st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			cabb = new LinkedList<>();
			out = new LinkedList<>();
			map = new int[N][M];
			chk = new boolean[N][M];
			for (int r = 0; r < N; r++) { // 맵 생성
				for (int c = 0; c < M; c++) {
					map[r][c] = 0;
				}
			}
			for (int k = 0; k < K; k++) { // 맵 에 배추 저장
				st = new StringTokenizer(br.readLine());
				int tmpc = Integer.parseInt(st.nextToken());
				int tmpr = Integer.parseInt(st.nextToken());
				cabb.add(new Cabbage(tmpr, tmpc));
				map[tmpr][tmpc] = 1;
			}
//			System.out.println(cabb.size());

			for (int r = 0; r < N; r++) { // 탐색을 시작한다.
				for (int c = 0; c < M; c++) {
					if (map[r][c] == 1) { // 지도에 배추가 있다면
						int cnt = -1;
						while (++cnt < cabb.size()) { // 어느 배추인지 파악하고
							if (cabb.get(cnt).c == c && cabb.get(cnt).r == r) {
								out.offer(cabb.remove(cnt)); // BFS에 사용할 Queue에 저장해준다.
								break;
							}
						} // 그룹에서 배추 하나 저장

						while (out.size() != 0) { // bfs 시작, 그룹 배추 삭제 시작
							Cabbage tmp = out.poll();// 일단 뱉는다.

							map[tmp.r][tmp.c] = 0; // 검색함
							chk[tmp.r][tmp.c] = true; // 검색함

							for (int d = 0; d < 4; d++) {
								int newr = tmp.r + dr[d];
								int newc = tmp.c + dc[d];
								if (newr < 0 || newr >= N || newc < 0 || newc >= M || chk[newr][newc])
									continue;// 지도는 벗어나거나 탐색한 구역이라면

								if (map[newr][newc] == 1) { // 주변에 1인 구역이 있으면
									out.offer(new Cabbage(newr, newc)); // 다시 집어 넣는다.
								}
							}
						} // end bfs

						++ans;
//						showChk();
					} // end if

				} // end c
			} // end r
			System.out.println(ans);

		}

	}

	static void showChk() {
		System.out.println("------------------");
		System.out.println("ans : " + ans);
		for (int n = 0; n < N; n++) {
			for (int m = 0; m < M; m++) {
				if (chk[n][m])
					System.out.print("x ");
				else
					System.out.print("o ");
			}
			System.out.println();
		}
		System.out.println();
	}

	static class Cabbage {
		int r;
		int c;

		public Cabbage(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
	}
}
