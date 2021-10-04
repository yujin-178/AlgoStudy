import java.util.Arrays;
import java.util.Scanner;

public class B2579_계단오르기_7 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] stairs = new int[N + 1];
		for (int n = 1; n <= N; n++) {
			stairs[n] = sc.nextInt();
		}

		int[][] DP = new int[4][N + 1];

		DP[0][1] = 0;
		DP[1][1] = stairs[0];
		DP[2][1] = 0;
		DP[3][1] = stairs[0];
		if (N >= 2) {
			DP[0][2] = stairs[1];
			DP[1][2] = 0;
			DP[2][2] = stairs[1];
			DP[3][2] = stairs[1]+ stairs[0];
		}
		if (N >= 3) {

			for (int n = 3; n <= N; n++) {

				DP[0][n] = Math.max(Math.max(DP[2][n - 2], DP[3][n - 2]) + stairs[n - 1], DP[1][n - 1]) + stairs[n];
				DP[1][n] = Math.max(Math.max(DP[0][n - 2], DP[1][n - 2]), Math.max(DP[2][n - 1], DP[3][n - 1]))
						+ stairs[n];
				DP[2][n] = Math.max(Math.max(DP[2][n - 2], DP[3][n - 2]) + stairs[n - 1], DP[1][n - 1]);
				DP[3][n] = Math.max(DP[1][n - 2] + stairs[n - 1], DP[1][n - 1]);

			}
		}
		int max = 0;
		for (int i = 0; i < 2; i++) {
			System.out.println(Arrays.toString(DP[i]));
			max = Math.max(max, DP[i][N]);
		}
		System.out.println(max);
	}
}
