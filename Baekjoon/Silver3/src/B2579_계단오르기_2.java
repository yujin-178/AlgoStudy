import java.util.Scanner;

public class B2579_계단오르기_2 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] stairs = new int[N];
		for (int n = 0; n < N; n++) {
			stairs[n] = sc.nextInt();
		}

		int[][] DP = new int[2][N];
		DP[0][0] = stairs[0];
		DP[1][0] = 1;
		DP[0][1] = stairs[1];
		DP[1][1] = 1;

		for (int n = 2; n < N; n++) {
			if (DP[1][n - 1] < 2) {
				if (DP[0][n - 1] > DP[0][n - 2]) {
					DP[0][n] = DP[0][n - 1] + stairs[n];
					DP[1][n] = DP[1][n - 1] + 1;
				} else {
					DP[0][n] = DP[0][n - 2] + stairs[n];
					DP[1][n] = 1;
				}
			} else {
				DP[0][n] = DP[0][n - 2] + stairs[n];
				DP[1][n] = 1;
			}
		}
		System.out.println(DP[0][N - 1]);
	}
}
