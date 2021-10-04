import java.util.Scanner;

public class B2579_계단오르기_11 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] stairs = new int[N];
		for (int n = 0; n < N; n++) {
			stairs[n] = sc.nextInt();
		}

		int[][] DP = new int[4][N];

		DP[0][0] = stairs[0];
		DP[1][0] = 0;
		DP[2][0] = 0;
		DP[3][0] = stairs[0];
		if (N >= 2) {
			DP[0][1] = stairs[1];
			DP[1][1] = stairs[0] + stairs[1];
			DP[2][1] = stairs[0];
			DP[3][1] = stairs[0];
		}
		if (N >= 3) {

			for (int n = 2; n < N; n++) {

				DP[0][n] = Math.max(Math.max(DP[0][n - 2], DP[1][n - 2]), Math.max(DP[2][n - 1], DP[3][n - 1]))
						+ stairs[n];
				DP[1][n] = Math.max(Math.max(DP[2][n - 2], DP[3][n - 2]) + stairs[n - 1], DP[0][n - 1]) + stairs[n];
				DP[2][n] = Math.max(Math.max(DP[2][n - 2], DP[3][n - 2]) + stairs[n - 1], DP[0][n - 1]);
				DP[3][n] = Math.max(DP[0][n - 2] + stairs[n - 1], DP[1][n - 1]);

			}
		}
		int max = 0;
		for (int i = 0; i < 2; i++) {
			max = Math.max(max, DP[i][N - 1]);
		}
		System.out.println(max);
	}
}
