import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B3109_3 {
	static int[] dr = { -1, 0, 1 }; // 우상 우 우하
	static int[] dc = { 1, 1, 1 }; // 우 우 우
	static char[][] map;
	static int R, C, cnt;
	static boolean complete;
	static int[] s;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("3109_input"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		cnt = 0;
		s = new int[C];
		map = new char[R][C];
		for (int r = 0; r < R; r++) {
			String tmp = br.readLine();
			for (int c = 0; c < C; c++) {
				map[r][c] = tmp.charAt(c);
			}
		}
		for (int r = 0; r < R; r++) {
			if(map[r][1] == 'x') continue;
			s[1] = r;
			complete = false; // 설치 여부
			install(r, 1);
		}
//		showMap();
		System.out.println(cnt);
	}

	static void showMap() {
		System.out.println(cnt + "번째 ------------------------------------");
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				System.out.print(map[r][c] + " ");
			}
			System.out.println();
		}
	}

	static void install(int r, int c) {
		if (c == C - 2) { // 끝에 도달하면
			for (int i = 1; i < C-1; i++) {
				map[s[i]][i] = 'x'; // 가설한 파이프를 확정하고 설치한다.
			}
			
			cnt++; // 파이프 개수 한개 증가
			showMap();
			complete = true; // 설치완료
			return;
		}

		for (int d = 0; d < 3; d++) {// 우상 우 우하 탐색
			if (complete) // 설치했으니까 종료
				return;
			int newR = r + dr[d];
			int newC = c + dc[d];
			if (isAvailable(newR, newC)) { // 다음 위치에 설치 가능하다면
				s[newC] = newR; // 가설
				install(newR, newC);
			}
		}
	}

	static boolean isAvailable(int r, int c) {
		if (r < 0 || r >= R || c < 0 || c >= C || map[r][c] == 'x')
			return false; // 맵을 벗어나거나, 설치(건물, 파이프) 되어 있거나
		return true;
	}

}
