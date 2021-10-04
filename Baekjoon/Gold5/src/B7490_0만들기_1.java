import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

/**
  * @FileName : B7490_0만들기_1.java
  * @Date : 2021. 9. 9. 
  * @작성자 : KimYuJin
  * @특이점 : 답 나오는 순서를 고려하지 않아서 한번 틀렸다.
  * 중간에 Product만들고 main에 추가 안하고 돌리면서 왜 결과가 안나오지 하고 헤맨적 있다....
  */
public class B7490_0만들기_1 {
	static int T, N;
	static char[] input;
	static int[] num, ans;
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
					sb.append(ans[i]).append(input[i] == '0' ? ' ' : input[i]);
				}
				sb.append(ans[N - 1]).append("\n");
			}
			return;
		}

		for (int i = 0; i < 3; i++) {
			char tmp = '-'; // 순서 주의!!!!!!!!!!
			if (i == 0)
				tmp = '0';
			else if (i == 1)
				tmp = '+';
			input[n] = tmp;
			Product(n + 1);
		}

	}

	static boolean chk() {
		int sum = 0;
		num = new int[N];
		for (int n = 1; n <= N; n++) {
			num[n - 1] = n;
		}
		for (int i = 0; i < N - 1; i++) {
			if (input[i] == '0') {
				if (num[i] < 0)
					num[i + 1] = num[i] * 10 - num[i + 1];
				else
					num[i + 1] = num[i] * 10 + num[i + 1];
				num[i] = 0;
			} else if (input[i] == '+') {
				sum += num[i];
			} else {
				num[i + 1] *= -1;
				sum += num[i];
			}
		}
		sum += num[N - 1];
		if (sum == 0)
			return true;
		else
			return false;
	}
}
