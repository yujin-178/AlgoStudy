import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

/**
 * @author 김유진 bfs 개념을 활용해서 풀었다. 다리가 생성되는 시점까지 기다리는 방법을 추가해야하기 때문에 해당 방법을 map이
 *         1인 곳에는 Pos 객체를 계속 남겨두는 방법을 활용했다.
 */
public class S3124_최소스패닝트리_4 {
	static int V, E;
	static int[] head;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
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

			// Arrays.sort(eArray);
			Arrays.sort(eArray, new Comparator<Edge>() {

				@Override
				public int compare(Edge o1, Edge o2) {
					return o1.v - o2.v;
				}

			});

			makeHead();

			int cnt = 0;
			long ans = 0;
			for (int i = 0; i < E; i++) {

//				System.out.println(findHead(eArray[i].s) + ", " + findHead(eArray[i].e));
				if (findHead(eArray[i].s) != findHead(eArray[i].e)) {
					cnt++;
					ans += eArray[i].v;

					head[head[eArray[i].e]] = head[eArray[i].s];
				}

				if (cnt == V - 1)
					break;
			}

			System.out.println("#" + tc + " " + ans);
		}

	}

	static int findHead(int idx) {
		if (idx == head[idx]) // idx와 idx의 머리가 같다면
			return idx; // 리턴해준다.
		// 다르다면
		return head[idx] = findHead(head[idx]); // idx의 head의 head를 찾으러 간다.
		// 최종 결과물은 리턴되면서 각 idx의 head를 갱신한다.
	}

	static void makeHead() {
		head = new int[V + 1]; // 초기 본인의 head를 생성한다.
		for (int i = 0; i <= V; i++)
			head[i] = i; // 처음엔 자기 자신이다.
	}

	/*
	 * static class Edge implements Comparable<Edge> { int s, e, v;
	 * 
	 * public Edge(int s, int e, int v) { this.s = s; this.e = e; this.v = v; }
	 * 
	 * @Override public int compareTo(Edge o) { return this.v - o.v; }
	 * 
	 * }
	 */
	static class Edge {
		int s, e, v;

		public Edge(int s, int e, int v) {
			this.s = s;
			this.e = e;
			this.v = v;
		}

	}
}
