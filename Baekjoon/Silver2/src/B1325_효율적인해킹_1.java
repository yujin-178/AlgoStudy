import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class B1325_효율적인해킹_1 {
	static int N, M, max;
	static Computer[] c;
	static int[] cnt;
	static StringBuilder sb;
	static boolean[] chk;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		c = new Computer[N + 1];
		cnt = new int[N + 1];
		chk = new boolean[N + 1];
		for (int n = 0; n <= N; n++) {
			c[n] = new Computer(n);
		}
		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int target = Integer.parseInt(st.nextToken());
			c[target].nextIdx.add(c[start]);
		}
		sb = new StringBuilder();
		for (int idx = 1; idx <= N; idx++) {
			dfs(idx, 0, idx);
		}
		System.out.println(sb.toString());
	}

	static void dfs(int next, int dep, int start) {
//		System.out.println("start : " + start + ", next : " + next + ", dep : " + dep);
		if (c[next].nextIdx.size() == 0) {
			if (dep == max) {
				if (!chk[start]) {
					chk[start] = true;
					sb.append(start + " ");
				}
			} else if (dep > max) {
				max = dep;
				sb = new StringBuilder();
				chk[start] = true;
				sb.append(start + " ");
			}
			return;
		}

		for (int i = 0; i < c[next].nextIdx.size(); i++) {
			dfs(c[next].nextIdx.get(i).idx, dep + 1, start);
		}

	}

	static class Computer {
		int idx;
		LinkedList<Computer> nextIdx;

		public Computer(int idx) {
			super();
			this.idx = idx;
			this.nextIdx = new LinkedList<>();
		}
	}

}
