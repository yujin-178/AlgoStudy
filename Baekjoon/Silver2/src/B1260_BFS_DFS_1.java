import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B1260_BFS_DFS_1 {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("1260_input"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int NodeNum = Integer.parseInt(st.nextToken());
		int EdgeNum = Integer.parseInt(st.nextToken());
		int start = Integer.parseInt(st.nextToken());
		boolean[] chk;
		Node[] n = new Node[1000 + 1];
		for (int i = 0; i < EdgeNum; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			if (n[from] == null) // 비어 있으면
				n[from] = new Node(from, to); // 생성 및 간선 정보 추가
			else
				n[from].addNext(to); // 있으면 간선 정보만 추가
			if (n[to] == null) // 비어 있으면 생성
				n[to] = new Node(to, from); // 번호만 주고 생성
			else
				n[to].addNext(from);
		}

		for (int i = 1; i <= 1000; i++) {
			if (n[i] != null)
				Collections.sort(n[i].next);
		}
		chk = new boolean[1000 + 1];
		chk[start] = true;
		dfs(start, chk, n);
		System.out.println();
		chk = new boolean[1000 + 1];
		bfs(start, chk, n);

	}

	static void dfs(int start, boolean[] chk, Node[] n) {
		System.out.print(start + " ");

		if (n[start].next.isEmpty())
			return;

		for (int i = 0; i < n[start].next.size(); i++) {
			int idx = n[start].next.get(i);
			if (!chk[idx]) {
				chk[idx] = true;
				dfs(idx, chk, n);
			}
		}

	}

	static void bfs(int start, boolean[] chk, Node[] n) {
		Queue<Integer> q = new LinkedList<>();
		q.add(start);
		chk[start] = true;
		int cnt = 0;
		while (!q.isEmpty()) {
			cnt = q.size();

			for (int i = 0; i < cnt; i++) {
				int tmp = q.poll();
				System.out.print(tmp + " ");

				for (int j = 0; j < n[tmp].next.size(); j++) {
					if (!chk[n[tmp].next.get(j)]) {
						chk[n[tmp].next.get(j)] = true;
						int next = 0 + n[tmp].next.get(j);
						q.add(next);
					}
				}

			}

		}

	}

	static class Node {
		int num;
		ArrayList<Integer> next = new ArrayList<>();

		public Node(int num) {
			super();
			this.num = num;
		}

		public Node(int num, int next) {
			super();
			this.num = num;
			this.next.add(next);
		}

		public void addNext(int next) {
			this.next.add(next);
		}
	}
}
