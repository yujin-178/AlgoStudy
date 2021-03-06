import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @FileName : B17472_다리만들기2_4.java
 * @Date : 2021. 11. 28.
 * @작성자 : KimYuJin
 * @특이점 :
 */
public class B17472_다리만들기2_4 {
	static int N, M, cntIsland, cntLand, idxLand;
	static int[][] map, edgeMap;
//	static int[] parents;
	static Pos[] p;
	static int[] dr = { 0, 1, 0, -1 };
	static int[] dc = { 1, 0, -1, 0 };

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("17472_input"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];

		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < M; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				if (map[r][c] == 1)
					cntLand++;
			}
		}

		p = new Pos[cntLand];

		cntIsland = numberingMap();

//		for (int r = 0; r < N; r++) {
//			for (int c = 0; c < M; c++) {
//				System.out.print(map[r][c] + " ");
//			}
//			System.out.println();
//		}

//		System.out.println(cntIsland);

//		for (int i = 0; i < idxLand; i++) {
//			System.out.println(p[i]);
//		}

		edgeMap = new int[cntIsland + 1][cntIsland + 1];
//		parents = new int[cntIsland + 1];
		makeEdgeMap();

//		for (int i = 1; i <= cntIsland; i++) {
//			for (int j = 1; j <= cntIsland; j++) {
//				if (edgeMap[i][j] == Integer.MAX_VALUE)
//
//					System.out.print("X" + " ");
//				else
//					System.out.print(edgeMap[i][j] + " ");
//			}
//			System.out.println();
//		}

		System.out.println(findMinDis());

	}

	static int findMinDis() {
		int result = 0;
		boolean[] visited = new boolean[cntIsland + 1];
		int[] minEdge = new int[cntIsland + 1];
		for (int i = 0; i <= cntIsland; i++) {
			minEdge[i] = Integer.MAX_VALUE;
		}
		minEdge[1] = 0;
		for (int i = 1; i <= cntIsland; i++) {
			int min = Integer.MAX_VALUE;
			int minVertex = -1;
			for (int j = 1; j <= cntIsland; j++) {
				if (!visited[j] && min > minEdge[j]) { // 신장트리에 포함되지 않은 정점
					min = minEdge[j];
					minVertex = j;
				}
			}
			if (minVertex == -1)
				continue;

			visited[minVertex] = true; // 신장트리에 포함시킴
			result += min; // 간선비용 누적
			for (int j = 1; j <= cntIsland; j++) {
				if (!visited[j] && edgeMap[minVertex][j] != 0 && minEdge[j] > edgeMap[minVertex][j]) {
					minEdge[j] = edgeMap[minVertex][j];
				}
			}
		}
		for (int i = 1; i <= cntIsland; i++) {
			if (minEdge[i] == Integer.MAX_VALUE)
				return -1;
		}
		return result;

	}

//	static int find(int a) {
//		if (a == parents[a])
//			return a;
//		return parents[a] = find(parents[a]);
//	}
//
//	static boolean union(int a, int b) { // 두 원소의 집합을 합친다.
//		int aRoot = find(a);
//		int bRoot = find(b);
//		if (aRoot == bRoot)
//			return false;
//
//		parents[bRoot] = aRoot;
//		return true;
//	}

	static void makeEdgeMap() {

		for (int i = 1; i <= cntIsland; i++) {
			for (int j = 1; j <= cntIsland; j++) {
				edgeMap[i][j] = Integer.MAX_VALUE;
			}
		}

		for (int i = 0; i < idxLand; i++) {
			for (int j = 0; j < idxLand; j++) {
				if (i == j || p[i].type == p[j].type)
					continue;
				if (p[i].r == p[j].r) {
					edgeMap[p[i].type][p[j].type] = Math.min(edgeMap[p[i].type][p[j].type],
							chkCol(p[i].r, p[i].c, p[j].c));
				}
				if (p[i].c == p[j].c) {
					edgeMap[p[i].type][p[j].type] = Math.min(edgeMap[p[i].type][p[j].type],
							chkRow(p[i].c, p[i].r, p[j].r));
				}
			}
		}
	}

	static int chkRow(int c, int tmp1, int tmp2) {
		int r1 = Math.min(tmp1, tmp2);
		int r2 = Math.max(tmp1, tmp2);
		int dis = 0;
		for (int r = r1 + 1; r < r2; r++) {
			if (map[r][c] != 0)
				return Integer.MAX_VALUE;
			dis++;
		}
		if (dis < 2)
			return Integer.MAX_VALUE;
		return dis;
	}

	static int chkCol(int r, int tmp1, int tmp2) {
		int c1 = Math.min(tmp1, tmp2);
		int c2 = Math.max(tmp1, tmp2);
		int dis = 0;
		for (int c = c1 + 1; c < c2; c++) {
			if (map[r][c] != 0)
				return Integer.MAX_VALUE;
			dis++;
		}
		if (dis < 2)
			return Integer.MAX_VALUE;
		return dis;
	}

	static int numberingMap() {
		boolean[][] chk = new boolean[N][M];
		int num = 1;
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				if (map[r][c] != 0 && !chk[r][c])
					setNum(r, c, num++, chk);
			}
		}
		return num - 1;
	}

	static void setNum(int r, int c, int sNum, boolean chk[][]) {
		map[r][c] = sNum;
		chk[r][c] = true;
		p[idxLand++] = new Pos(r, c, sNum);
		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			if (moveable(nr, nc) && map[nr][nc] == 1 && !chk[nr][nc])
				setNum(nr, nc, sNum, chk);
		}
	}

	static boolean moveable(int r, int c) {
		if (r < 0 || c < 0 || r >= N || c >= M)
			return false;
		return true;
	}

	static class Pos {
		int r, c, type;

		public Pos(int r, int c, int type) {
			super();
			this.r = r;
			this.c = c;
			this.type = type;
		}

		@Override
		public String toString() {
			return "Pos [r=" + r + ", c=" + c + ", type=" + type + "]";
		}

	}
}
