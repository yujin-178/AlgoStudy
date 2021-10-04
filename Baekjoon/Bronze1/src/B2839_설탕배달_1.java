

import java.io.IOException;
import java.util.Scanner;

public class B2839_설탕배달_1 {
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int fn = N / 5;
		int tn = 0;
		boolean pass = false;
		for (int idx = 0; idx < N / 3; idx++) {
			tn = 0;
			while (fn * 5 + tn * 3 < N) {
				tn += 1;
			}
			if (fn * 5 + tn * 3 == N) {
				pass = true;
				break;
			}
			fn -= 1;
		}
		if (pass)
			System.out.println(fn + tn);
		else
			System.out.println(-1);
		sc.close();

	}
}
