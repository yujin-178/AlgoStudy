import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class B3109_2 {
	static int[] dr = { -1, 0, 1 }; // 우상 우 우하
	static int[] dc = { 1, 1, 1 }; // 우 우 우
	static char[][] map;
	static int R, C, cnt;
	static boolean complete;
	static Stack<Pipe> s;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("3109_input"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		cnt = 0;

		map = new char[R][C];
		for (int r = 0; r < R; r++) {
			String tmp = br.readLine();
			for (int c = 0; c < C; c++) {
				map[r][c] = tmp.charAt(c);
			}
		}
		for (int r = 0; r < R; r++) {
			s = new Stack<Pipe>(); // 새로운 파이프 저장 공간
			complete = false; // 
			s.push(new Pipe(r, 0));
			install(r, 0);

		}
		showMap();
		System.out.println(cnt);
	}

	static void showMap() {
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				System.out.print(map[r][c] + " ");
			}
			System.out.println();
		}
	}

	static void install(int r, int c) {
		if (c == C - 1) { // 끝에 도달하면
			for (int i = 0; i < s.size(); i++) {
				map[s.get(i).r][s.get(i).c] = 'x'; // 가설한 파이프를 확정하고 설치한다.
			}
			cnt++; // 파이프 개수 한개 증가
			complete = true; // 설치완료
			return;
		}

		for (int d = 0; d < 3; d++) {
			if (complete) // 설치했으니까 종료
				return;
			int newR = r + dr[d];
			int newC = c + dc[d];
			if (isAvailable(newR, newC)) { // 다음 위치에 설치 가능하다면
				s.push(new Pipe(newR, newC)); // 가설
				install(newR, newC);
				s.pop(); // 새로운 탐색을 위해 제거

			}
		}
		// 지금 문제는 중간에 설치완료하고 끝까지 나와야 하는데 중간에서 꼬리치기를 시작한다.
	}

	static boolean isAvailable(int r, int c) {
		if (r < 0 || r >= R || c < 0 || c >= C || map[r][c] == 'x')
			return false; // 맵을 벗어나거나, 설치(건물, 파이프) 되어 있거나
		return true;
	}

	static class Pipe {
		int r;
		int c;

		public Pipe(int r, int c) {
			super();
			this.r = r;
			this.c = c;

		}

	}

}
