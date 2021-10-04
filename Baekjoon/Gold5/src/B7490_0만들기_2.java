import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;


/**
  * @FileName : B7490_0만들기_2.java
  * @Date : 2021. 9. 12. 
  * @작성자 : KimYuJin
  * @특이점 : 스터디 후 리뷰하면서 수정할 부분 수정
  * 		이전에 숫자와 기호를 같은 배열에 대입하는 과정에서 공백을 0으로 삽입하는 부분이 남아있어서
  * 		코드를 읽는데 어려움을 줌
  */
public class B7490_0만들기_2 {
	static int T, N;
	static char[] input;
	static int[] ans;
	static StringBuilder sb;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("7490_input"));
		Scanner sc = new Scanner(System.in);

		T = sc.nextInt();

		for (int tc = 1; tc <= T; tc++) {
			N = sc.nextInt();
			sb = new StringBuilder();
			input = new char[N - 1];
			ans = new int[N];
			for (int n = 1; n <= N; n++) {
				ans[n - 1] = n;
			}
			Product(0);
			System.out.println(sb.toString());
		}
		sc.close();

	}

	static void Product(int n) {
		if (n == N - 1) {
			if (chk()) {
				for (int i = 0; i < N - 1; i++) {
					sb.append(ans[i]).append(input[i]);
				}
				sb.append(ans[N - 1]).append("\n");
			}
			return;
		}

		input[n] = ' ';
		Product(n + 1); // 공백인 상황
		input[n] = '+';
		Product(n + 1); // +인 상황
		input[n] = '-';
		Product(n + 1); // -인 상황

	}

	static boolean chk() {
		int sum = 0;
		int[] num = new int[N];
		for (int n = 1; n <= N; n++) {
			num[n - 1] = n;
		}
		for (int i = 0; i < N - 1; i++) {
			if (input[i] == ' ') {
				if (num[i] < 0) // 이전 값(num[i])이 음수라면 이전값에는 10을 곱하고 새로운 값(num[i+1])은 -를 곱해서 더한다.
					num[i + 1] = num[i] * 10 - num[i + 1];
				else // 이전 값이 양수라면 10을 곱하고 새로운 값은 그냥 더한다.
					num[i + 1] = num[i] * 10 + num[i + 1];
				num[i] = 0;
			} else if (input[i] == '+') { // 기호가 +라면 이전 값은 합산된다.
				sum += num[i];
			} else {
				num[i + 1] *= -1; // 기호가 -라면 다음 숫자는 음수로 변하며 이전값은 합산된다.
				sum += num[i];
			}
		}
		sum += num[N - 1]; // 맨 마지막 숫자는 위의 과정에서 합산되지 못하니까 더해준다.
		if (sum == 0) // 0이면 트루를
			return true;
		else // 아니면 false를 반환
			return false;
	}
}
