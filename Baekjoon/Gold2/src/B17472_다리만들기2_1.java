import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B17472_다리만들기2_1 {
	static int N, M;
	static int[][] map;
	static int[] dr = { 0, 0, -1, 1 };
	static int[] dc = { -1, 1, 0, 0 };
	static Land[] l;
	static boolean[][] chk;
	static int[] parents;
	static ArrayList<Edge> e;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		chk = new boolean[N][M];
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < M; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		int landCnt = 1;
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				if (map[r][c] != 0 && !chk[r][c])
					unionBfs(r, c, landCnt++);
			}
		}
		
	
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				System.out.print(map[r][c] + " ");
			}
			System.out.println();
		}

		l = new Land[N * M];
		int cnt = 0;
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				if (map[r][c] != 0) {
					for (int d = 0; d < 4; d++) {
						int nr = r + dr[d];
						int nc = c + dc[d];
						if (nr >= 0 && nr < N && nc >= 0 && nc < N && map[nr][nc] == 0) {
							l[cnt++] = new Land(map[r][c], r, c);
							break;
						}
					}
				}
			}
		}
		System.out.println(cnt);
		e = new ArrayList<Edge>();

		for (int i = 0; i < cnt; i++) {
			for (int j = 0; j < cnt; j++) {
				if (i == j || l[i].type == l[j].type)
					continue;
				if (l[i].r == l[j].r) {
					if (Math.abs(l[i].c - l[j].c) > 1)
						e.add(new Edge(l[i].type, l[j].type, Math.abs(l[i].c - l[j].c)));
				} else if (l[i].c == l[j].c) {
					if (Math.abs(l[i].r - l[j].r) > 1)
						e.add(new Edge(l[i].type, l[j].type, Math.abs(l[i].r - l[j].r)));
				}
			}
		}
		System.out.println(e.size());
		Collections.sort(e);
		make(landCnt);
		
		for (Edge ee : e) {
			System.out.println(ee.start + ", "+ee.end + ", "+ee.weight);
		}
		


	}

	static void make(int n) {
		parents = new int[n + 1];
		for (int i = 1; i < n + 1; i++) {
			parents[i] = i;
		}
	}

	static int find(int a) {
		if (a == parents[a])
			return a;
		return parents[a] = find(parents[a]);
	}

	static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		if (aRoot == bRoot)
			return false;

		parents[bRoot] = aRoot;

		return true;
	}

	static void unionBfs(int r, int c, int landCnt) {
		Queue<Land> q = new LinkedList<>();
		q.add(new Land(landCnt, r, c));
		chk[r][c] = true;
		map[r][c] = landCnt;
		while (!q.isEmpty()) {
			Land tmp = q.poll();
			for (int d = 0; d < 4; d++) {
				int nr = tmp.r + dr[d];
				int nc = tmp.c + dc[d];
				if (nr >= 0 && nr < N && nc >= 0 && nc < M && !chk[nr][nc] && map[nr][nc] != 0) {
					map[nr][nc] = landCnt;
					chk[nr][nc] = true;
					q.add(new Land(landCnt, nr, nc));
				}
			}

		}
	}

	static class Land {
		int type;
		int r;
		int c;

		public Land(int type, int r, int c) {
			super();
			this.type = type;
			this.r = r;
			this.c = c;
		}
	}

	static class Edge implements Comparable<Edge> {
		int start, end, weight;

		public Edge(int start, int end, int weight) {
			super();
			this.start = start;
			this.end = end;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			// TODO Auto-generated method stub
			return Integer.compare(this.weight, o.weight);
		}

	}
}
