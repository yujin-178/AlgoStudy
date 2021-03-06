import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B17472_다리만들기2_2 {
	static int N, M, num, ans;
	static int[][] map, record;
	static boolean[][] chk;
	static int[] dr = { 0, 1, 0, -1 };
	static int[] dc = { 1, 0, -1, 0 };
	static int[] set;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("17472_input"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		record = new int[N][M];
		chk = new boolean[N][M];
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < M; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				if (!chk[r][c] && map[r][c] == 1) {
					map[r][c] = ++num;
					chk[r][c] = true;
					cntMap(r, c);
				}
			}
		}

		makeSet(); // 섬 개수 만큼 set 만들기

//		for (int r = 0; r < N; r++) {
//			for (int c = 0; c < M; c++) {
//				System.out.print(map[r][c] + " ");
//			}
//			System.out.println();
//		}

		ans = Integer.MAX_VALUE;
		makeBridge(0, 0, map, 0);

		System.out.println(ans);

	}

	static void makeBridge(int exR, int exC, int[][] saveMap, int len) {
//		System.out.println(len);
		if (len > ans)
			return;
		if (exR == N) {
			System.out.println(Arrays.toString(set));
			int chkSet = set[1];
			for (int i = 2; i <= num; i++) {
				if (set[i] != chkSet)
					return;
			}
			System.out.println("안들려???");
			ans = Math.min(ans, len);
			return;
		}

		for (int r = exR; r < N; r++) {
			for (int c = exC; c < M; c++) {
				if (saveMap[r][c] > 0 && saveMap[r][c] <= num) {
					int nowIdx = saveMap[r][c];
					makeBridge(c < M - 1 ? r : r + 1, c < M - 1 ? c + 1 : 0, saveMap, len);
					for (int d = 0; d < 4; d++) {
						int nr = r + dr[d];
						int nc = c + dc[d];
						if (outMapChk(nr, nc))
							continue;
						if (saveMap[nr][nc] != 0)
							continue;
						int nowlen = 1;
						boolean out = false;
						while (saveMap[nr][nc] <= num) {
							nr += dr[d];
							nc += dc[d];
							if (outMapChk(nr, nc)) {
								out = true;
								break;
							}

							if (saveMap[nr][nc] > 0)
								break;
							++nowlen;
						}
//						System.out.println(r + ", " + c + ", len : " + nowlen);
						if (out || nowlen < 2)
							continue;

						if (saveMap[nr][nc] >= num || !unionSet(nowIdx, saveMap[nr][nc]))
							continue;

						int setR = r;
						int setC = c;
						for (int i = 0; i < nowlen; i++) {
							setR += dr[d];
							setC += dc[d];
//							System.out.println("setR : " + setR + ", setC : " + setC);
							saveMap[setR][setC] += num + 1;
						}

						makeBridge(c < M - 1 ? r : r + 1, c < M - 1 ? c + 1 : 0, saveMap, len + nowlen);
						setR = r;
						setC = c;
						for (int i = 0; i < nowlen; i++) {
							setR += dr[d];
							setC += dc[d];
							saveMap[setR][setC] -= num + 1;
						}

					}
				}
			}
		}
	}

	static boolean unionSet(int idxA, int idxB) {
		int Ahead = set[idxA];
		int Bhead = set[idxB];
		if (Ahead == Bhead)
			return false;
		set[Bhead] = Ahead;
		return true;
	}

	static int findSet(int idx) {
		if (idx == set[idx])
			return idx;

		return set[idx] = findSet(set[idx]);
	}

	static void makeSet() {
		set = new int[num + 1];
		for (int i = 1; i <= num; i++) {
			set[i] = i;
		}
	}

	static void cntMap(int r, int c) {

		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			if (outMapChk(nr, nc))
				continue;
			if (!chk[nr][nc] && map[nr][nc] == 1) {
				map[nr][nc] = num;
				chk[nr][nc] = true;
				cntMap(nr, nc);
			}
		}

	}

	static boolean outMapChk(int r, int c) {
		if (r < 0 || r >= N || c < 0 || c >= M)
			return true;
		return false;
	}

}
