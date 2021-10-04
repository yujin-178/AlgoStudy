

import java.util.Scanner;
//import java.io.FileInputStream;

public class Test1970_01 {
//class Solution{
	public static void main(String args[]) throws Exception {
		Scanner sc = new Scanner(System.in);
		int[] coin = { 50000, 10000, 5000, 1000, 500, 100, 50, 10 };
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			int[] cnt = new int[coin.length];
			int setCoin = 0;
			int money = sc.nextInt();
			while (setCoin < coin.length) {
				if (coin[setCoin] <= money) {
					money -= coin[setCoin];
					cnt[setCoin]++;
				} else {
					setCoin++;
				}
			} // end while setCoin
//		System.out.println(Arrays.toString(cnt));
			System.out.println("#" + tc);
			for (int i = 0; i < setCoin; i++) {
				System.out.print(cnt[i] + " ");
			} // end i
			System.out.println();
		} // end tc
		sc.close();
	}
}
