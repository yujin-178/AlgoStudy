import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B1197_최소스패닝트리1 {
	static int V, E;
	static int[] minEdge;
	static boolean[] chk;
	static int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());

		initMap();

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());

			map[s][e] = v;
			map[e][s] = v;
		}

//		for (int r = 0; r <= V; r++) {
//			for (int c = 0; c <= V; c++) {
//				System.out.print(map[r][c] == Integer.MAX_VALUE ? "x " : map[r][c] + " ");
//			}
//			System.out.println();
//		}

		int result = 0;
		minEdge[1] = 0;

		for (int i = 1; i <= V; i++) {
			int min = Integer.MAX_VALUE;
			int minVertex = -1;

			for (int j = 1; j <= V; j++) {
				if (!chk[j] && min > minEdge[j]) {
					min = minEdge[j];
					minVertex = j;
//					System.out.println(min + ", " + minVertex);
				}
			}
			chk[minVertex] = true;
			result += min;

			for (int j = 1; j <= V; j++) {
				if (!chk[j] && map[minVertex][j] != 0 && minEdge[j] > map[minVertex][j]) {
					minEdge[j] = map[minVertex][j];
				}
			}
		}
		System.out.println(result);

	}

	static void initMap() {
		minEdge = new int[V + 1];
		chk = new boolean[V + 1];
		map = new int[V + 1][V + 1];
		for (int r = 0; r <= V; r++) {
			for (int c = 0; c <= V; c++) {
				map[r][c] = Integer.MAX_VALUE;
			}
			minEdge[r] = Integer.MAX_VALUE;
		}
	}
}
