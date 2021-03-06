import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S4311_오래된스마트폰_2 {
	static int N, O, M, ans, dep;
	static boolean[] oper;
	static int[] num;
	static boolean flag;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			O = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			num = new int[N];
			oper = new boolean[5];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				num[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < O; i++) {
				oper[Integer.parseInt(st.nextToken())] = true;
			}
			st = new StringTokenizer(br.readLine());
			ans = Integer.parseInt(st.nextToken());
			flag = false;
			dep = Integer.MAX_VALUE;
			dfs(0, 0, 0, false);
			if (flag)
				System.out.println("#" + tc + " " + dep);
			else
				System.out.println("#" + tc + " " + -1);
		}
	}

	static void dfs(int d, int sum, int now, boolean insert) {
//		System.out.println("sum : "+ sum + ", now : "+now );
		if (insert && (sum < 0 || sum > 999))
			return;
		if ((d != 0 && sum == ans && insert) || (d != 0 && ans == now && !insert)) {
			flag = true;
			if (insert)
				dep = Math.min(dep, d);
			else
				dep = Math.min(dep, d + 1);
		}

		if (dep <= d)
			return;
		if (d == M)
			return;
		for (int i = 0; i < N; i++) {
			if (!insert) {
				dfs(d + 1, sum, now * 10 + num[i], false);
				dfs(d + 1, now * 10 + num[i], 0, true);
			} else
				dfs(d + 1, sum, now * 10 + num[i], true);
		}
		if (oper[1]) {
			dfs(d + 1, sum + now, 0, true);
		}

		if (insert && now != 0) {
			if (oper[2])
				dfs(d + 1, sum - now, 0, true);
			if (oper[3])
				dfs(d + 1, sum * now, 0, true);
			if (oper[4])
				dfs(d + 1, sum / now, 0, true);
		}

	}
}
