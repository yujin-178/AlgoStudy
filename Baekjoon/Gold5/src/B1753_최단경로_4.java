import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.StringTokenizer;

public class B1753_최단경로_4 {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("1753_input"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		int current = Integer.parseInt(br.readLine());
		Vertex[] v = new Vertex[V + 1];
		for (int i = 0; i <= V; i++) {
			v[i] = new Vertex(i);
		}
		int[] ans = new int[V + 1];
		boolean[] chk = new boolean[V + 1];
		Arrays.fill(ans, Integer.MAX_VALUE);
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());

			v[start].addEdge(end, weight);
		}
		ans[current] = 0;
		chk[current] = true;
		int min = 0, minIdx = 0;
		for (int i = 1; i <= V; ++i) {
			min = Integer.MAX_VALUE;
			minIdx = 0;

			Set<Integer> keys = v[current].l.keySet();
			Iterator<Integer> iter = keys.iterator();
			while (iter.hasNext()) {
				int end = iter.next();
				int weigth = v[current].l.get(end);
				ans[end] = Math.min(ans[current] + weigth, ans[end]);
			}

			for (int j = 1; j <= V; j++) {
				if (!chk[j] && min > ans[j]) {
					min = ans[j];
					minIdx = j;
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
		HashMap<Integer, Integer> l;

		public Vertex(int num) {
			super();
			this.num = num;
			l = new HashMap<>();
		}

		void addEdge(int end, int weight) {
			if (this.l.containsKey(end)) {
				if (this.l.get(end) > weight)
					this.l.put(end, weight);
			} else
				this.l.put(end, weight);
		}

	}

}
