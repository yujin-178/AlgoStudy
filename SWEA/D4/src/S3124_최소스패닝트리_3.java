import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author deter
 * 왜 틀리는 거지....?
 *
 */
public class S3124_최소스패닝트리_3 {

	static int V, E;
	static Edge[] e;
	static int[] parents;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("3123_input"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			e = new Edge[E];
			for (int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine());
				int ss = Integer.parseInt(st.nextToken());
				int ee = Integer.parseInt(st.nextToken());
				int ww = Integer.parseInt(st.nextToken());
				e[i] = new Edge(ss, ee, ww);
			}
			Arrays.sort(e);
			make();
			int ans = 0;
			int pathNum = 0;
			for (int i = 0; i < E; i++) {
				if (union(e[i].s, e[i].e)) {
					ans += e[i].w;
					if (++pathNum == V - 1)
						break;
				}
			}

			System.out.println("#" + tc + " " + ans);

		}

	}

	static void make() {
		parents = new int[V + 1];
		for (int i = 0; i <= V; i++) {
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

	static class Edge implements Comparable<Edge> {
		int s;
		int e;
		int w;

		public Edge(int s, int e, int w) {
			super();
			this.s = s;
			this.e = e;
			this.w = w;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.w, o.w);
		}

	}
}
