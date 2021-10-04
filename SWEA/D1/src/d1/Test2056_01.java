package d1;

import java.util.Scanner;

public class Test2056_01 {

	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			int num = sc.nextInt();
			int year = num / 10000;
			int day = num % 100;
			int month = (num % 10000) / 100;
			if ((month > 12) || (month < 0)) {
				System.out.println("#" + tc + "-1");
				continue;
			}
			boolean state = false;
			switch (month) {
			case 1:
			case 3:
			case 5:
			case 7:
			case 8:
			case 10:
			case 12:
				state = chk31(day);
				break;
			case 4:
			case 6:
			case 9:
			case 11:
				state = chk30(day);
				break;
			case 2:
				state = chk28(day);
				break;
			}
			if (state)
//				System.out.println(year + "/" + "요기2");
				System.out.println("#" + tc + " " +year + "/" + month + "/" + day);
			else
				System.out.println("#" + tc + "-1");
		}
		sc.close();
	}


	public static boolean chk30(int d) {
		if (d <= 30)
			return true;
		else
			return false;
	}

	public static boolean chk31(int d) {
		if (d <= 31)
			return true;
		else
			return false;
	}

	public static boolean chk28(int d) {
		if (d <= 28)
			return true;
		else
			return false;
	}

}
