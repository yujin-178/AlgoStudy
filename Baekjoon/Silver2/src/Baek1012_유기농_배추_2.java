import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//public class Main {
public class Baek1012_유기농_배추_2 {
	static int M, N, K;

	static boolean[][] chk;
	static int ans;
	static int[] dr = { 0, 1, 0, -1 };
	static int[] dc = { 1, 0, -1, 0 };
	static LinkedList<cabbage> l = new LinkedList<>();
	static Queue<cabbage> q = new LinkedList<>();

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

			chk = new boolean[N][M];

			for (int k = 0; k < K; k++) {
				st = new StringTokenizer(br.readLine());
				int c = Integer.parseInt(st.nextToken());
				int r = Integer.parseInt(st.nextToken());
				chk[r][c] = true;
				l.add(new cabbage(r, c));
			}

			for (int r = 0; r < N; r++) {
				for (int c = 0; c < M; c++) {
					if (chk[r][c]) {
						bfs(r, c);
						++ans;
					}
				}
			}
			System.out.println(ans);
		}
	}

	static void bfs(int r, int c) {
		
		for (int i = 0; i < l.size(); i++) {
			if (l.get(i).c == c && l.get(i).r == r) {
				q.add(l.remove(i)); // 처음 탐색한 녀석을 Queue에 넣는다.
				chk[r][c] = false;
//				showChk();
				break;// 여기도 있으면 좀 더 빠르게 끝날듯?
			}
		}
		while (q.size() != 0) {
			for (int d = 0; d < 4; d++) {
				int newR = q.peek().r + dr[d]; // 맨 위에 있는 녀석
				int newC = q.peek().c + dc[d];
				if (newR < 0 || newR >= N || newC < 0 || newC >= M)
					continue;
				if (chk[newR][newC]) {// 탐색해서 있으면
					for (int i = 0; i < l.size(); i++) { // list에서 검색 
						if (l.get(i).c == newC && l.get(i).r == newR && !l.isEmpty()) {
							q.add(l.remove(i)); // 다시 Queue에 넣기
							chk[newR][newC] = false;
							break;// 시간초과는 해당 배추를 찾으면 멈추는 걸로 해결
							
						}
					}
				}
			}
			q.poll();
		}
		
	}
	static void showChk() {
		System.out.println("------------------");
		System.out.println("ans : " + ans);
		for (int n = 0; n < N; n++) {
			for (int m = 0; m < M; m++) {
				if (chk[n][m])
					System.out.print("o ");
				else
					System.out.print("x ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	
	static class cabbage {
		int r;
		int c;

		public cabbage(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
	}

}
