import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @FileName : B14716_현수막_1.java
 * @Date : 2021. 9. 13.
 * @작성자 : KimYuJin
 * @특이점 : 1인 부분은 글자, 0은 글자가 아니다. 상하좌우대각선에 존재하면 하나의 글자다. 글자 개수 확인.... 행렬 탐색하다가 1을
 *      만나면 DFS로 주변에 있는 1들을 전부 0으로 바꾼다. 바꿀 1이 없다면 하나의 글자를 전부 0으로 바꾼거다 이때 cnt 1증가
 *      M,N은 1부터 250, 두번째줄 1 부터 나온다 탐색은 여기부터 하는걸로
 */
public class B14716_현수막_1 {
	static int cnt, M, N;
	static int[] dr = { -1, -1, -1, 0, 1, 1, 1, 0 };
	static int[] dc = { -1, 0, 1, 1, 1, 0, -1, -1 };
	static char[][] map;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("14716_input"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		map = new char[M][N];

		for (int r = 0; r < M; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				map[r][c] = st.nextToken().charAt(0);
			}
			
		}
		

		for (int r = 0; r < M; r++) {
			for (int c = 0; c < N; c++) {
				if (map[r][c] == '1') {
					dfs(r, c);
					cnt++;
				}
			}
		}
		System.out.println(cnt);

	}

	static void dfs(int r, int c) {

		for (int d = 0; d < 8; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];

			if (posDfs(nr, nc) && map[nr][nc] == '1') {
				map[nr][nc] = '0';
				dfs(nr, nc);
			}
		}

		return;
	}

	static boolean posDfs(int r, int c) {
		if (r < 0 || r >= M || c < 0 || c >= N)
			return false;
		return true;
	}
}
