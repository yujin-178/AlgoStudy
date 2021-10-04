import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S7465_창용마을무리의개수_1 {
	static int N, M, cnt;
	static int[] parents;

	static void make() {
		parents = new int[N + 1];
		for (int i = 0; i <= N; i++) {
			parents[i] = i;
		}
	}

	static int find(int now) {
		if (now == parents[now]) {
			return now;
		}

		return parents[now] = find(parents[now]);
	}

	static void union(int a, int b) {
		int rootA = find(a);
		int rootB = find(b);
		if (rootA == rootB)
			return;

		parents[rootB] = rootA;
		--cnt;
	}

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("7465_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			cnt = N;
			make();

			for (int m = 0; m < M; m++) {
				st = new StringTokenizer(br.readLine());
				union(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}
			System.out.println("#" + tc + " " + cnt);
		}
	}
}
