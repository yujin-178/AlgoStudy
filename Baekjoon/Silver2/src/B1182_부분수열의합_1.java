import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @FileName : B1182_부분수열의합_1.java
 * @Date : 2021. 9. 8.
 * @작성자 : KimYuJin
 * @특이점 : 웹이랑 DB, 건강이 안좋아서 거의 2주동안 문제를 못 풀었다. ㅠㅜ 이제 몸은 좀 좋아졌으니 하루에 한문제라도 풀어보자
 */
public class B1182_부분수열의합_1 {
	static boolean[] chk;
	static int[] input;
	static int N, S, cnt, chkcnt;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("1182_input"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		input = new int[N];
		chk = new boolean[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			input[i] = Integer.parseInt(st.nextToken());
		}
		subset(0, 0);
		System.out.println(cnt);
	}

	static void subset(int n, int sum) {

//		for (int i = 0; i < N; i++) {
//			System.out.print(chk[i] + " ");
//		}
//		System.out.println();
//
//		if (sum == S && chkcnt > 0) {
//			for (int i = 0; i < N; i++) {
//				if (chk[i])
//					System.out.print(input[i] + " ");
//			}
//			System.out.println();
//			cnt++;
//		}

		if (N == n) {
			if (sum == S && chkcnt > 0)
				cnt++;
			return;
		}

		chk[n] = true;
		chkcnt++;
		subset(n + 1, sum + input[n]);

		chk[n] = false;
		chkcnt--;
		subset(n + 1, sum);

	}
}
