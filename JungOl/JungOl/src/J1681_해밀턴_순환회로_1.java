import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class J1681_해밀턴_순환회로_1 {
	static int N, min;
	static int[][] cost;
	static boolean[] chk;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		cost = new int[N][N];
		chk = new boolean[N];
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				cost[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		min = Integer.MAX_VALUE;
		chk[0] = true;
		permu(0, 1, 0);
		System.out.println(min);

	}

	static void permu(int start, int n, int sum) {
		if (min < sum)
			return;
		if (n == N) {
			if (cost[start][0] != 0)
				min = Math.min(sum + cost[start][0], min);
			return;
		}

		for (int i = 0; i < N; i++) {
			if (chk[i] || cost[start][i] == 0)
				continue;
			chk[i] = true;
			permu(i, n + 1, cost[start][i] + sum);
			chk[i] = false;
		}
	}
}
