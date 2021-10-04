import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B1325_효율적인해킹_4 {
	static int N, M, max;
	static ArrayList<Integer>[] c;
	static StringBuilder sb;
	static boolean[] chk;
	static boolean[] ans;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		c = new ArrayList[N + 1];
		ans = new boolean[N + 1];
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
			chk = new boolean[N + 1];
			chk[idx] = true;
			dfs(idx, 0, idx);
		}
		System.out.println(sb.toString());
	}

	static void dfs(int idx, int dep, int start) {
		if (c[idx].size() == 0) {
			if (!ans[start]) {
				if (max < dep) {
					max = dep;
					ans[start] = true;
					sb = new StringBuilder();
					sb.append(start + " ");
				} else if (max == dep) {
					ans[start] = true;
					sb.append(start + " ");
				}
			}
			return;
		}
		for (int a : c[idx]) {
			chk[a] = true;
			dfs(a, dep + 1, start);
			chk[a] = false;
		}
	}
}
