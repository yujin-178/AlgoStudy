

import java.util.Scanner;

public class Test1271_01 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		double a = sc.nextDouble();
		double b = sc.nextDouble();
		System.out.printf("%.0f\n", a / b);
		System.out.printf("%.0f", a % b);
		sc.close();
	}

}
