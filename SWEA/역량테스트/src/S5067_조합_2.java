import java.util.Scanner;

public class S5067_조합_2 {
	static long[] fac;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();

		for (int tc = 1; tc <= T; tc++) {
			int N = sc.nextInt();
			int R = sc.nextInt();
			fac = new long[N + 1];
			fac[0] = 1;

			for (int i = 1; i <= N; i++)
				fac[i] = fac[i - 1] * i % 1234567891;
			System.out.println("#" + tc + " " + nCr(N, R, 1234567891));
		}
	}

	static long nCr(int n, int r, long p) {
		if (r == 0)
			return 1L;

		return (fac[n] * pow(fac[r], p - 2, p) % p * pow(fac[n - r], p - 2, p) % p) % p;
	}

	static long pow(long x, long y, long p) {
		long res = 1L;
		x = x % p;
		while (y > 0) {
			if (y % 2 == 1)
				res = (res * x) % p;
			y = y >> 1;
			x = (x * x) % p;
		}
		return res;
	}
}
