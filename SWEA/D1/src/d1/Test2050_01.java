package d1;

import java.util.Scanner;

public class Test2050_01 {

	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
//		String[] st = sc.next().split("");
		char[] st = sc.next().toCharArray();
		for (int i = 0; i < st.length; i++) {
			System.out.printf("%s ", st[i]);
			System.out.printf("%d ", (int)st[i]-(int)'A');
			
		}
		sc.close();
	}

}
