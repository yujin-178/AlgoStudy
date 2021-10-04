import java.util.Arrays;
import java.util.Scanner;


/**
  * @FileName : B1463_1로만들기_2.java
  * @Date : 2021. 9. 14. 
  * @작성자 : KimYuJin
  * @특이점 : 1 이 되는 시점에 1이 더해져 있다.!!!!
  */
public class B1463_1로만들기_2 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int X = sc.nextInt();
		int cnt = 0;
		int[] num = new int[X + 1];
		Arrays.fill(num, Integer.MAX_VALUE);
		num[0] = 0;
		for (int i = 1; i <= X; i++) {
			num[i] = Math.min(num[i - 1] + 1, num[i]);
			if (i % 2 == 0)
				num[i] = Math.min(num[i / 2] + 1, num[i]);
			if (i % 3 == 0)
				num[i] = Math.min(num[i / 3] + 1, num[i]);
		}

		System.out.println(num[X] - 1);
	}
}
