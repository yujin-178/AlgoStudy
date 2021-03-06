import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B17472_다리만들기2_5 {
	static int N, M, cntIsland, cntLand, idxLand;
	static int[][] map, edgeMap;
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

		cntIsland = numberingMap(); // 섬을 구분해서 넘버링을 수행하고 섬이 몇개인지 반환해준다.

		edgeMap = new int[cntIsland + 1][cntIsland + 1];
		makeEdgeMap();

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

	static void makeEdgeMap() {

		for (int i = 1; i <= cntIsland; i++) {
			for (int j = 1; j <= cntIsland; j++) {
				edgeMap[i][j] = Integer.MAX_VALUE; //edgeMap은 i 번째 섬에서 j번째 섬으로 갈때 걸리는 최대 비용을 기록하고 있다.
			}
		}

		for (int i = 0; i < idxLand; i++) {
			for (int j = 0; j < idxLand; j++) {
				if (i == j || p[i].type == p[j].type) // 다리는 직선으로만 건설 가능
					continue;
				if (p[i].r == p[j].r) { // 횡으로 연결 가능할 때 
					edgeMap[p[i].type][p[j].type] = Math.min(edgeMap[p[i].type][p[j].type],
							chkCol(p[i].r, p[i].c, p[j].c));
				}
				if (p[i].c == p[j].c) { // 종으로 연결 가능할 때
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

	static int numberingMap() { // 섬이 몇개인지 반환해준다.
		boolean[][] chk = new boolean[N][M];
		int num = 1;
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				if (map[r][c] != 0 && !chk[r][c])
					setNum(r, c, num++, chk); // 섬이 몇번섬인지 섬의 숫자를 바꿔준다.
			}
		}
		return num - 1;
	}

	static void setNum(int r, int c, int sNum, boolean chk[][]) { // dfs를 이용해서 섬마다 sNum으로 표기한다.
		map[r][c] = sNum;
		chk[r][c] = true;
		p[idxLand++] = new Pos(r, c, sNum); // 
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
