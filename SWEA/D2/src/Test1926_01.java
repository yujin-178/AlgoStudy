

import java.util.Scanner;
//import java.io.FileInputStream;

public class Test1926_01 {
//class Solution{
	public static void main(String args[]) throws Exception {
		Scanner sc = new Scanner(System.in);
		int num = sc.nextInt();
		int cnt = 1;
		int clapNum = 0;
		while (cnt <= num) {
			
			clapNum = chkNum(cnt);
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

	public static int chkNum(int num) {
		if (num == 0)
			return 0;
		else if (num % 10 == 3 || num % 10 == 6 || num % 10 == 9) {
			  return 1 + chkNum(num / 10) ;
		} else {
			return chkNum(num / 10);
		}
		
	}
}
