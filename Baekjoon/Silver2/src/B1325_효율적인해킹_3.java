import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B1325_효율적인해킹_3 {
	static int N, M, max;
	static ArrayList<Integer>[] c;
	static StringBuilder sb;
	static int[] cntTail;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		c = new ArrayList[N + 1];
		cntTail = new int[N + 1];
		for (int n = 0; n <= N; n++) {
			c[n] = new ArrayList<Integer>();
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

		for (int a : cntTail) {
			if (a == max)
				sb.append(a + " ");
		}
		System.out.println(sb.toString());
	}

	static void bfs(int start) {
		boolean[] chk = new boolean[N + 1];
		Queue<Integer> q = new LinkedList<>();
		q.add(start);
		chk[start] = true;
		int cnt = 0;
		while (!q.isEmpty()) {
			cnt++;
			for (int next : c[q.poll()]) {
				for (int nn : c[next]) {
					if (!chk[nn]) {
						chk[nn] = true;
						q.add(nn);
					}
				}
			}
		}

		cntTail[start] = cnt;
		if (cnt > max) {
			max = cnt;
		}

		return;

	}

}
