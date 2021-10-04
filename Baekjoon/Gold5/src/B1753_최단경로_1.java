import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class B1753_최단경로_1 {
//	static Vertex[] v;
//	static int V, E, s;
//	static int ans[];
//	static boolean[] chk;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("1753_input"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		int s = Integer.parseInt(br.readLine());
		Vertex[] v = new Vertex[V + 1];
		int[] ans = new int[V + 1];
		boolean[]chk = new boolean[V + 1];
		Arrays.fill(ans, Integer.MAX_VALUE);
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			if (v[start] == null)
				v[start] = new Vertex(start);
			if (v[end] == null)
				v[end] = new Vertex(end);
			v[start].addEdge(new Edge(start, end, weight));
		}
		ans[s] = 0;
		chk[s] = true;
		int min = 0, current = s, minIdx = s;
		for (int i = 1; i <= V; ++i) {
			min = Integer.MAX_VALUE;
			minIdx = 0;
//			System.out.println("start : " + current);
			for (int j = 0; j < v[current].l.size(); j++) {
				int end = v[current].l.get(j).end;
				int weigth = v[current].l.get(j).weight;
				ans[end] = Math.min(ans[current] + weigth, ans[end]);
//				System.out.println("end : " + end);

				if (!chk[end] && min > ans[end]) {
					min = ans[end];
					minIdx = end;
				}
			}
			chk[minIdx] = true;
			current = minIdx;
			if (minIdx == 0)
				break;
		}
		for (int i = 1; i <= V; i++) {
			if (ans[i] == Integer.MAX_VALUE)
				System.out.println("INF");
			else
				System.out.println(ans[i]);
		}
	}

	static class Vertex {
		int num;
		LinkedList<Edge> l;

		public Vertex(int num) {
			super();
			this.num = num;
			l = new LinkedList<>();
//			l.add(new Edge(num, num, 0));
		}

		void addEdge(Edge e) {
			this.l.add(e);
		}

	}

	static class Edge {
		int start;
		int end;
		int weight;

		public Edge(int start, int end, int weight) {
			super();
			this.start = start;
			this.end = end;
			this.weight = weight;
		}

	}
}
