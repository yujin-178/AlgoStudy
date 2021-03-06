import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.StringTokenizer;

public class B2213_트리의독립집합2 {
	static int N, idx;
	static int[][] DP;
	static Node[] node;
	static int[] w;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("2213_input"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());

		w = new int[N + 1];
		node = new Node[N + 1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			w[i] = Integer.parseInt(st.nextToken());
			node[i] = new Node();
		}
		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int start = Math.min(a, b);
			int end = Math.max(a, b);
			node[start].child.add(end);
			node[end].parents = start;
		}
		DP = new int[N + 1][2];
		ArrayList<Integer>[] ans = (dfs(1));
		StringBuilder sb = new StringBuilder();
		if (DP[1][0] >= DP[1][1]) {
			System.out.println(DP[1][0]);
			Collections.sort(ans[0]);
			for (int a : ans[0]) {
				sb.append(a).append(" ");
			}
			sb.deleteCharAt(sb.lastIndexOf(" "));
			System.out.println(sb.toString());
		} else {
			System.out.println(DP[1][1]);
			Collections.sort(ans[1]);
			for (int a : ans[1]) {
				sb.append(a).append(" ");
			}
			sb.deleteCharAt(sb.lastIndexOf(" "));
			System.out.println(sb.toString());
		}

	}

	static ArrayList<Integer>[] dfs(int i) {
		ArrayList<Integer>[] upload = new ArrayList[2];
		upload[0] = new ArrayList<Integer>();
		upload[1] = new ArrayList<Integer>();

		if (node[i].child.isEmpty()) { // leaf node

			DP[i][0] = 0;
			DP[i][1] = w[i];
			upload[1].add(i);
			return upload;

		} else if (node[i].child.size() == 1) { // 분기 없이 전달되는 노드
			int next = node[i].child.get(0);
			ArrayList<Integer>[] tmp = dfs(next);
			// i 노드를 선택하지 않는 경우
			int sIdx = DP[next][0] >= DP[next][1] ? 0 : 1;
			DP[i][0] = DP[next][sIdx];
			Iterator<Integer> iter = tmp[sIdx].iterator();
			while (iter.hasNext()) {
				upload[0].add(iter.next());
			}
			// i 노드를 선택하는 경우
			DP[i][1] = w[i] + DP[next][0];
			iter = tmp[0].iterator();
			while (iter.hasNext()) {
				upload[1].add(iter.next());
			}
			upload[1].add(i);

			return upload;

		} else { // 분기가 존재하는 노드
			int len = node[i].child.size();
			DP[i][1] = w[i];
			DP[i][0] = 0;
			ArrayList<Integer>[] tmp = new ArrayList[2];
			upload[1].add(i);

			for (int ni = 0; ni < len; ni++) {
				int next = node[i].child.get(ni);
				tmp = dfs(next);
				DP[i][1] += DP[next][0];
				Iterator<Integer> iter = tmp[0].iterator();
				while (iter.hasNext()) {
					upload[1].add(iter.next());
				}

				int sIdx = DP[next][0] >= DP[next][1] ? 0 : 1;

				DP[i][0] += DP[next][sIdx];
				iter = tmp[sIdx].iterator();
				while (iter.hasNext()) {
					upload[0].add(iter.next());
				}

			}

			return upload;

		}

	}

	static class Node {
		int parents;
		ArrayList<Integer> child;

		public Node() {
			super();
			this.parents = -1;
			this.child = new ArrayList<>();
		}

	}

}
