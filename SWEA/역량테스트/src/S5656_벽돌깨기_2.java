import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class S5656_벽돌깨기_2 {
	static int N, W, H, min;
	static boolean[][] bomb;
	static int[] dr = { 0, 0, 1, -1 };
	static int[] dc = { 1, -1, 0, 0 };

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("5656_input"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			int[][] map = new int[H][W];

			for (int r = 0; r < H; r++) {
				st = new StringTokenizer(br.readLine());
				for (int c = 0; c < W; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
				}
			}
			min = Integer.MAX_VALUE;
			go(0, map);
			System.out.println("#" + tc + " " + min);

		}
	}

	static void go(int cnt, int[][] map) {
		if (cnt == N) {
			int result = getRemain(map);
			min = Math.min(result, min);
			return;
		}
		int[][] newMap = new int[H][W];
		for (int c = 0; c < W; c++) {
			int r = 0;
			while (r < H && map[r][c] == 0)
				r++;
			if (r == H) {
				go(cnt + 1, map);
			} else {
				copy(map, newMap);
				boom(newMap, r, c, newMap[r][c]);
				down(newMap);
				go(cnt + 1, newMap);
			}
		}
	}

	static int getRemain(int[][] map) {
		int count = 0;
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				if (map[i][j] > 0)
					count++;
			}
		}
		return count;
	}

	static ArrayList<Integer> list = new ArrayList<>();

	static void down(int[][] map) {
		for (int c = 0; c < W; c++) {
			int r;
			for (r = H - 1; r >= 0; --r) {
				if (map[r][c] > 0) {
					list.add(map[r][c]);
					map[r][c] = 0;
				}
			}
			r = H;
			for (int b : list)
				map[--r][c] = b;
			list.clear();
		}
	}

	static void boom(int[][] map, int r, int c, int cnt) {
		map[r][c] = 0;
		if(cnt==1)
			return;
		
		for (int d = 0; d < 4; d++) {
			int nr = r;
			int nc = c;
			for (int k = 1; k < cnt; k++) {
				nr += dr[d];
				nc += dc[d];
				if (nr >= 0 && nr < H && nc >= 0 && nc < W && map[nr][nc] != 0) {
					boom(map, nr, nc, map[nr][nc]);
				}
			}
		}
	}

	static void copy(int[][] map, int[][] newMap) {
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				newMap[i][j] = map[i][j];
			}
		}
	}

}
