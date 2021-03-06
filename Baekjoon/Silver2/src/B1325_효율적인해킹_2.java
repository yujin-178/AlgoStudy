import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B1325_효율적인해킹_2 {
	static int N, M, max;
	static Computer[] c;
	static StringBuilder sb;
	static boolean[] chk;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		c = new Computer[N + 1];
		chk = new boolean[N + 1];
		for (int n = 0; n <= N; n++) {
			c[n] = new Computer();
		}
		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int target = Integer.parseInt(st.nextToken());
			c[target].nextIdx.add(start);
		}
		sb = new StringBuilder();
		for (int idx = 1; idx <= N; idx++) {
			bfs(idx);
		}
		System.out.println(sb.toString());
	}

	static void bfs(int start) {
		Queue<Computer> q = new LinkedList<>();
		q.add(c[start]);
		chk[start] = true;
		int cnt = 0;
		while (!q.isEmpty()) {
			cnt++;
			int nextSize = q.size();
			for (int j = 0; j < nextSize; j++) {
				Computer now = q.poll();
				for (int i = 0; i < now.nextIdx.size(); i++) {
					if (!chk[now.nextIdx.get(i)]) {
						q.add(c[now.nextIdx.get(i)]);
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

	static class Computer {

		LinkedList<Integer> nextIdx;

		public Computer() {
			super();

			this.nextIdx = new LinkedList<>();
		}
	}

}
