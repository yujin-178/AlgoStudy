import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Test1861_1 {
	static int[] dc = { -1, 0, 1, 0 };
	static int[] dr = { 0, 1, 0, -1 };
	static int N = 0;
	static int[][] map;
	static roomInfo[] r;

	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("C:/CodingStudy/SWEA/D4/1861_input.txt")); // setIn을 활용하니까 입력이 편해졌다.
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {

			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			r = new roomInfo[N * N + 1]; // 1칸을 추가해서 방의 번호와 index를 일치시켰다.
			for (int nc = 0; nc < N; nc++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int nr = 0; nr < N; nr++) {
					map[nc][nr] = Integer.parseInt(st.nextToken());
					r[map[nc][nr]].col = nc;
					r[map[nc][nr]].row = nr;
				}
			}
			int maxNum = 0;
			int maxIdx = 0;
			for (int idx = N * N; idx > 0; idx--) { // 동길홍 조원은 마지막 위치 채우는 방법으로 가장 낮은 방의 번호를 획득했다.

				move(r[idx].col, r[idx].row, 1, idx); // 현재 위치 포함 1부터 시작
				if (maxNum <= r[idx].moveCnt) {
					maxIdx = idx;
					maxNum = r[idx].moveCnt;
				}
			}
			System.out.println("#" + tc + " " + maxIdx + " " + maxNum);
		}
	}// end main

	static class roomInfo { // 홍길동 조원은 방의 정보를 클래스로 만들어서 관리했다. 코드가 좀 더 깔끔해졌다.
		int col = 0;
		int row = 0;
		int moveCnt = 0;
	}// end roomInfo

	private static void move(int col, int row, int cnt, int idx) { // 방의 이동을 메서드로 만들어서 코드 보기가 깔끔해졌다.

		for (int dir = 0; dir < 4; dir++) {
			int ncol = col + dc[dir];
			int nrow = row + dr[dir];
			if (ncol >= 0 && ncol < N && nrow >= 0 && nrow < N && (map[ncol][nrow] == (map[col][row] + 1))) {
				move(ncol, nrow, cnt + 1, idx);
			}
		}

		if (r[idx].moveCnt < cnt)
			r[idx].moveCnt = cnt;
		return;

	}// end move

} // end class
