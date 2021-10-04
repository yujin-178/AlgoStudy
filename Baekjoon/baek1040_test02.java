
package Baekjoon_test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class baek1040_test02 {
	static int[] numset = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };

	public static void main(String args[]) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		String[] s = bf.readLine().split(" ");
		// System.out.println(s);
		long[] a = { 0, 0 };
		for (int i = 0; i < 2; i++) {
			a[i] = Long.parseLong(s[i]);
			// System.out.println("입력받은 a = "+a[i]);
		}
		// int b = Integer.parseInt(bf.readLine());
		System.out.println(s[0].length());
		long tmpNum = 0;
		if (s[0].length() < a[1]) { // 찾고자 하는 자리수 보다 작은 경우 12 4이면 1234가 될때까지 카운트 해야함
			for (int i = 1; i <= s[0].length(); i++) {
				tmpNum *= 10;
				tmpNum += i;
			}
			a[0] = tmpNum; // 해당 경우에만 초기화
		}

		while (true) {
			numInit();
			long tmp = a[0];
			long chk = 0;
			// int lchk = 0;
			while ((tmp % 10) != 0) {
				numset[(int) (tmp % 10)]++;
				tmp /= 10;
				// lchk++;
			}
			for (int i = 0; i < 10; i++) {
				if (numset[i] != 0)
					chk++;
			}
			if (chk == a[1])
				break;
			// else if(chk)
			else
				a[0]++;

		}

		System.out.print(a[0]);

	}

	public static void numInit() {
		for (int i = 0; i < 10; i++) {
			numset[i] = 0;
		}
	}

}
