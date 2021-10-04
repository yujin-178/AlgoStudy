package Baekjoon_test;

import java.util.Scanner;

public class baek1040_test03 {
	static int[] numset = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };

	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		long a = sc.nextLong();
		long b = sc.nextLong();

		while (true) {
			numInit();
			long tmp = a;
			int chk = 0;
			while ((tmp % 10) != 0) {
				int idx = (int)(tmp % 10);
				numset[idx]++;
				tmp /= 10;
			}
			for (int i = 0; i < 10; i++) {
				if (numset[i] != 0)
					chk++;
			}
			if (chk == b)
				break;
			else
				a++;

		}

		System.out.print(a);
		sc.close();
	}

	public static void numInit() {
		for (int i = 0; i < 10; i++) {
			numset[i] = 0;
		}
	}
	
	
}
