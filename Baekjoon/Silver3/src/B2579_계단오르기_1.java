import java.util.Scanner;

public class B2579_계단오르기_1 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] stairs = new int[N];
		for (int n = 0; n < N; n++) {
			stairs[n] = sc.nextInt();
		}

		int[] DP = new int[N];
		DP[0] = stairs[0];
		DP[1] = stairs[1];

		for (int n = 2; n < N; n++) {
			DP[n] = Math.max(DP[n - 1], DP[n - 2]) + stairs[n];
		}
		System.out.println(DP[N-1]);
	}
}
