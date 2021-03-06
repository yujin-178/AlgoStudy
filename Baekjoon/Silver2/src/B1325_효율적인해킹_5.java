import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B1325_효율적인해킹_5 {
	static int N, M, max;
	static LinkedList<Integer>[] c;
	static StringBuilder sb;
	static boolean[] chk;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		c = new LinkedList[N + 1];

		for (int n = 0; n <= N; n++) {
			c[n] = new LinkedList<Integer>();
		}
		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int target = Integer.parseInt(st.nextToken());
			c[target].add(start);
		}
		sb = new StringBuilder();
		for (int idx = 1; idx <= N; idx++) {
			bfs(idx);
		}
		System.out.println(sb.toString());
	}

	static void bfs(int start) {
		chk = new boolean[N + 1];
		Queue<Integer> q = new LinkedList<>();
		q.add(start);
		chk[start] = true;
		int cnt = 0;
		while (!q.isEmpty()) {
			cnt++;
			int nextSize = q.size();
			for (int j = 0; j < nextSize; j++) {
				int now = q.poll();
				for (int i = 0; i < c[now].size(); i++) {
					int tmp = c[now].get(i);
					if (!chk[tmp]) {
						chk[tmp] = true;
						q.add(tmp);
					}
				}
			}
		}

		if (cnt == max)
			sb.append(start + " ");
		else if (cnt > max) {
			max = cnt;
			sb = new StringBuilder();
			sb.append(start + " ");
		}

		return;

	}

}
