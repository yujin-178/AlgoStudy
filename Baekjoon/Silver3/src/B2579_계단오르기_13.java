import java.util.Scanner;

public class B2579_계단오르기_13 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] stairs = new int[N + 1];
		for (int n = 1; n < N + 1; n++) {
			stairs[n] = sc.nextInt();
		}

		int[] DP = new int[N + 1];

		DP[1] = stairs[1]; // n번째
		if (N >= 2)
			DP[2] = stairs[2] + stairs[1]; // n + n-1 번째
		if (N >= 3)
			DP[3] = Math.max(stairs[2] + stairs[3], stairs[1] + stairs[3]);
		// n + n-1 or n + n-2 번째
		if (N >= 4) {
			for (int n = 4; n <= N; n++) {

				DP[n] = Math.max(DP[n - 2], DP[n - 3] + stairs[n - 1]) + stairs[n];
				// n-2번째 + n번째 계단 선택
				// n-3번째 + n-1번째 + n번째 계단 선택

			}
		}

		System.out.println(DP[N]);
	}
}
