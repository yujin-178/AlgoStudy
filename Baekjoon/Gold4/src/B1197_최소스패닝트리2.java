import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B1197_최소스패닝트리2 {
	static int V, E;
	static int[] head;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());

		Edge[] eArray = new Edge[E];

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int value = Integer.parseInt(st.nextToken());
			eArray[i] = new Edge(start, end, value);
		}

		Arrays.sort(eArray);

		makeHead();

		int cnt = 0;
		long ans = 0;
		for (int i = 0; i < E; i++) {
			if (findHead(eArray[i].s) != findHead(eArray[i].e)) {
				cnt++;
				ans += eArray[i].w;
				head[head[eArray[i].e]] = head[eArray[i].s];
			}
			if (cnt == V - 1)
				break;
		}
		System.out.println(ans);

	}

	static int findHead(int idx) {
		if (idx == head[idx])
			return idx;
		return head[idx] = findHead(head[idx]);
	}

	static void makeHead() {
		head = new int[V + 1];
		for (int i = 0; i <= V; i++)
			head[i] = i;
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
