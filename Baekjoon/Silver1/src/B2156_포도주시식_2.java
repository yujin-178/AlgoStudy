import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * @FileName : B2156_포도주시식_2.java
 * @Date : 2021. 9. 14.
 * @작성자 : KimYuJin
 * @특이점 :
 */
public class B2156_포도주시식_2 {

	static int[][] DP;
	static int[] wine;

	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("2156_input"));
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		wine = new int[N];
		for (int n = 0; n < N; n++) {
			wine[n] = sc.nextInt();
		}

		DP = new int[6][N];

		DP[0][0] = 0;
		DP[1][0] = 0;
		DP[2][0] = wine[0];
		DP[3][0] = 0;
		DP[4][0] = wine[0];
		DP[5][0] = wine[0];

		DP[0][1] = 0;
		DP[1][1] = wine[1];
		DP[2][1] = 0;
		DP[3][1] = wine[1];
		DP[4][1] = 0;
		DP[5][1] = wine[1] + wine[0];
//		DP[5][1] = wine[1];
//	  n
//0 XXO
//1 XOO
//2 OXO
//3 XOX
//4 OXX
//5 OOX
		for (int n = 2; n < N; n++) {

			DP[0][n] = Math.max(DP[3][n - 2], DP[4][n - 2] > DP[5][n - 2] ? DP[4][n - 2] : DP[5][n - 2]) + wine[n];
			DP[1][n] = Math.max(DP[3][n - 2], DP[4][n - 2] > DP[5][n - 2] ? DP[4][n - 2] : DP[5][n - 2]) + wine[n - 1]
					+ wine[n];
			DP[2][n] = Math.max(DP[0][n - 2], DP[1][n - 2] > DP[2][n - 2] ? DP[1][n - 2] : DP[2][n - 2]) + wine[n];
			DP[3][n] = Math.max(DP[3][n - 2], DP[4][n - 2] > DP[5][n - 2] ? DP[4][n - 2] : DP[5][n - 2]) + wine[n - 1];
			DP[4][n] = Math.max(DP[0][n - 2], DP[1][n - 2] > DP[2][n - 2] ? DP[1][n - 2] : DP[2][n - 2]);
			DP[5][n] = Math.max(DP[0][n - 2], DP[2][n - 2] ) + wine[n - 1];

		}
		int max = 0;
		for(int i = 0;i<6;i++) {
			max = Math.max(max, DP[i][N-1]);
		}
		System.out.println(max);
	}

}
