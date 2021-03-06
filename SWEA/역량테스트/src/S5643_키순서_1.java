import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S5643_키순서_1 {
	static int[][] map;
	static int N, M;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			map = new int[N + 1][N + 1];
			for (int m = 0; m < M; m++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				map[a][b] = -1;
				map[b][a] = +1;
			}

			for (int r = 1; r < N + 1; r++) {
				shorterDFS(r, r);
				tallerDFS(r, r);
			}
			

			int cnt = 0;
			for (int r = 1; r <= N; r++) {
				int cnt2 = 0;
				for (int c = 1; c <= N; c++) {
					if (map[r][c] != 0)
						cnt2++;
				}
				if (cnt2==N-1)
					cnt++;
			}
			System.out.println("#"+tc+" "+cnt);
		}
	}

	static void shorterDFS(int target, int start) {
		for (int c = 1; c < N + 1; c++) {
			if (map[start][c] == -1) {
				map[target][c] = -1;
				shorterDFS(target, c);
			}
		}
	}

	static void tallerDFS(int target, int start) {
		for (int c = 1; c < N + 1; c++) {
			if (map[start][c] == 1) {
				map[target][c] = 1;
				tallerDFS(target, c);
			}
		}
	}
}
