

import java.util.Scanner;

public class Main_1463_1로만들기_DFS {
	static int X;
	static int ans;
	static int[] memo;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		X = sc.nextInt();

		memo = new int[X + 1];
		ans = Integer.MAX_VALUE;

//		dfs(X);
		dfs(X, 0);
		System.out.println(ans);
	}

	static int dfs(int num) {
		if (num == 1) {
			return 0;
		}

		if (memo[num] == 0) {
			int tmp3 = Integer.MAX_VALUE, tmp2 = Integer.MAX_VALUE, tmp1 = Integer.MAX_VALUE;
			if (num % 3 == 0) {
				tmp3 = dfs(num / 3) + 1;
			}
			if (num % 2 == 0) {
				tmp2 = dfs(num / 2) + 1;
			}
			tmp1 = dfs(num - 1) + 1;
			memo[num] = Math.min(tmp2, tmp3);
			memo[num] = Math.min(memo[num], tmp1);
		}
		return memo[num];
	}
	
	static void dfs(int num, int cnt) {
		if(cnt>=ans) return;
		
		if(num==1) {
			ans = Math.min(ans, cnt);
			return;
		}
		
		dfs(num - 1, cnt+1);
		if (num % 3 == 0) {
			dfs(num / 3, cnt+1);
		}
		if (num % 2 == 0) {
			dfs(num / 2, cnt+1);
		}
		
	}
}
