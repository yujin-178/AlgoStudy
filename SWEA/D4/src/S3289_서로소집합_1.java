import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class S3289_서로소집합_1 {
	static int[] n;
	static int N, M;

	static void make() {
		n = new int[N + 1];
		for (int i = 0; i <= N; i++) {
			n[i] = i;
		}
	}

	static int find(int num) {
		if (num == n[num])
			return num;

		return n[num] = find(n[num]);
	}

	static void union(int n1, int n2) {
		int rootA = find(n1);
		int rootB = find(n2);
		if (rootA == rootB)
			return;

		n[rootB] = rootA;
	}

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("3289_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			StringBuilder sb = new StringBuilder();
			make();
			for (int m = 0; m < M; m++) {
				st = new StringTokenizer(br.readLine());
				switch (st.nextToken()) {
				case "1":
					if (find(Integer.parseInt(st.nextToken())) == find(Integer.parseInt(st.nextToken())))
						sb.append("1");
					else
						sb.append("0");
					break;
				case "0":
					union(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
					break;
				}
			}
//			System.out.println(Arrays.toString(n));
			System.out.println("#" + tc + " " + sb.toString());
		}

	}
}
