

import java.util.Scanner;

public class Test1926_02 {
	public static void main(String args[]) throws Exception {
		Scanner sc = new Scanner(System.in);
		int num = sc.nextInt();
		int cnt = 1;
		int clapNum = 0;
		while (cnt <= num) {
			clapNum = 0;
			int tmp = cnt;
			while(tmp != 0) {
				if((tmp % 10 == 3 || tmp % 10 == 6 || tmp % 10 == 9))
					clapNum++;
				tmp /=10;
			}
			
			if (clapNum == 0) {
				System.out.print(cnt);
			} else {
				for (int i = 0; i < clapNum; i++) {
					System.out.print("-");
				}
			}
			System.out.print(" ");
			cnt++;
		}
		sc.close();
	}

}
