import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B14888_연산지끼워넣기 {
	static int N, min, max;
	static int[] calc, num;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());

		calc = new int[4];
		num = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int n = 0; n < N; n++) {
			num[n] = Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 4; i++) {
			calc[i] = Integer.parseInt(st.nextToken());
		}
		min = Integer.MAX_VALUE;
		max = Integer.MIN_VALUE;
		dfs(num[0], 1);
		
		System.out.println(max);
		System.out.println(min);

	}

	static void dfs(int sum, int dep) {
		if (dep == N) {
			min = Math.min(min, sum);
			max = Math.max(max, sum);
			return;
		}

		for (int i = 0; i < 4; i++) {
			if (calc[i] == 0)
				continue;
			calc[i]--;
			switch (i+1) {
			case 1:
				dfs(sum + num[dep], dep + 1);
				break;
			case 2:
				dfs(sum - num[dep], dep + 1);
				break;
			case 3:
				dfs(sum * num[dep], dep + 1);
				break;
			case 4:
				dfs(sum / num[dep], dep + 1);
				break;
			}
			calc[i]++;
		}
	}
}
