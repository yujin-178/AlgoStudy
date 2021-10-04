

import java.io.IOException;
import java.util.Scanner;

//public class Main {
public class Test1550_01 {
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		String[] a = sc.next().split("");
		long result = 0;
		long hexDigit = 1;
		for (int i = a.length - 1; i>=0;i--) {
//			System.out.println(a[i]);
			 switch(a[i]) {
			 case "0": result += 0*hexDigit; break;
			 case "1": result += 1*hexDigit; break;
			 case "2": result += 2*hexDigit; break;
			 case "3": result += 3*hexDigit; break;
			 case "4": result += 4*hexDigit; break;
			 case "5": result += 5*hexDigit; break;
			 case "6": result += 6*hexDigit; break;
			 case "7": result += 7*hexDigit; break;
			 case "8": result += 8*hexDigit; break;
			 case "9": result += 9*hexDigit; break;
			 case "A": result += 10*hexDigit; break;
			 case "B": result += 11*hexDigit; break;
			 case "C": result += 12*hexDigit; break;
			 case "D": result += 13*hexDigit; break;
			 case "E": result += 14*hexDigit; break;
			 case "F": result += 15*hexDigit; break;
			 }
			 hexDigit *= (long)16;
		}
		System.out.println(result);
		
		
		
		sc.close();
	}

}
