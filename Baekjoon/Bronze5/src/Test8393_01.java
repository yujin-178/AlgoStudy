

import java.util.Scanner;
import java.io.IOException;

public class Test8393_01 {

//	public class Main{
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		long a = sc.nextInt();
		long ans = 0;
		while (a != 0) {
			ans += a;
			a--;
		}
	System.out.println(ans);
	sc.close();
	}
	
}

