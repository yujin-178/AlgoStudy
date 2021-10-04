
import java.io.IOException;
import java.util.Scanner;
public class Test2475_01 {
//	public class Main {
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(System.in);
		
		int sum = 0;
		for (int i = 0;i<5;i++) {
			sum += Math.pow(sc.nextInt(), 2);
		}
		System.out.println(sum%10);
		
		sc.close();
	}
}
