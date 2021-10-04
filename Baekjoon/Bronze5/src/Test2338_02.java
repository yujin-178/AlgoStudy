
import java.util.Scanner;
import java.io.IOException;
import java.math.BigInteger;
public class Test2338_02 {
//public class Main {
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		BigInteger a = new BigInteger(sc.next()) ;
		BigInteger b = new BigInteger(sc.next()) ;
		System.out.println(a.add(b));
		System.out.println(a.subtract(b));
		System.out.println(a.multiply(b));
		sc.close();
	}
}
