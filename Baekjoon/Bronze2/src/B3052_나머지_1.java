import java.util.Scanner;

public class B3052_나머지_1 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int[] remainder = new int[42];
		int ans = 0;
		for(int i = 0; i<10;i++) {
			++remainder[sc.nextInt() % 42];
		}
		for(int i = 0; i<42;i++) {
			if(remainder[i] != 0) ++ans;
		}
		System.out.println(ans);
		sc.close();
	}
}
