import java.util.Scanner;

/**
 * @FileName : B2156_포도주시식_1.java
 * @Date : 2021. 9. 14.
 * @작성자 : KimYuJin
 * @특이점 : DP가 가능할 것 같은데 조건찾기가 어려워서 부분집합 개념으로 풀어볼려고 한다.
 * 		   ㅋㅋㅋ 시간초과 나왔다 다시 해보자
 */
public class B2156_포도주시식_1 {
	static int N, maxDrink;
	static int[] num, ans;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		num = new int[N];
		for (int i = 0; i < N; i++) {
			num[i] = sc.nextInt();
		}
		boolean[] chk = new boolean[N];
		subset(0, 0, chk);
		System.out.println(maxDrink);
		sc.close();
	}

	static void subset(int idx, int sum, boolean[] chk) {
		
		if (idx == N) {
			maxDrink = Math.max(sum, maxDrink);
			return;
		}
		
		if (idx < 2) {
			chk[idx] = true;
			subset(idx + 1, sum + num[idx], chk);
		} else {
			if (!(chk[idx - 2] == true && chk[idx - 1] == true)) {
				chk[idx] = true;
				subset(idx + 1, sum + num[idx], chk);
			}
		}
		chk[idx] = false;
		subset(idx + 1, sum, chk);

	}
}
