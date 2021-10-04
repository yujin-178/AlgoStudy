

import java.math.BigInteger;
import java.util.Scanner;

public class Test1271_02 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		BigInteger bN1 = new BigInteger(sc.next());
		BigInteger bN2 = new BigInteger(sc.next());
		
		System.out.println(bN1.divide(bN2));
		System.out.println(bN1.remainder(bN2));
		sc.close();
	}

}
