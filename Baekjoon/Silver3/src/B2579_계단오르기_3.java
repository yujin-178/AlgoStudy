import java.util.Scanner;

public class B2579_계단오르기_3 {
	static int[] DP, stairs;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		stairs = new int[N];
		for (int n = 0; n < N; n++) {
			stairs[n] = sc.nextInt();
		}

		DP = new int[N];
		DP[0] = stairs[0];
		DP[1] = stairs[1];
		for (int n = 2; n < N; n++) {

			DP[n] = select(n);

		}
		System.out.println(DP[N - 1]);
	}

	static int select(int n) {
		int max = 0;

		max = Math.max(max, DP[n - 1] + stairs[n]);
		max = Math.max(max, DP[n - 2] + stairs[n]);
		max = Math.max(max, stairs[n]);
		max = Math.max(max, DP[n - 1] + DP[n - 2]);
		max = Math.max(max, DP[n - 1]);
		max = Math.max(max, DP[n - 2]);

		return max;
	}
}
