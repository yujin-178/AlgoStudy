import java.util.Arrays;
import java.util.Scanner;

/**
 * @FileName : B1463_1로만들기_2.java
 * @Date : 2021. 9. 14.
 * @작성자 : KimYuJin
 * @특이점 : 1 이 되는 시점에 1이 더해져 있다.!!!!
 */
public class B1463_1로만들기_3 {
	static int ans, X;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		X = sc.nextInt();
		ans = Integer.MAX_VALUE;

		dfs(1, 0);

		System.out.println();
	}

	static void dfs(int num, int cnt) {
		if (ans < cnt)
			return;
		if (num > X)
			return;
		if (num == X) {
			ans = Math.min(ans, cnt);
			return;
		}

		dfs(num * 3, cnt + 1);
		dfs(num * 2, cnt + 1);
		dfs(num + 1, cnt + 1);
	}
}
