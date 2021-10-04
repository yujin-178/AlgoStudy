import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class S3234_준환이의양팔저울_1 {
	static int T, N, ans, totalSum;
	static int[] input; // 입력받은 숫자
	static boolean[] Rselect; // 오른쪽의 부분집합
	static int[] Rnum;
	static int[] Lnum;
	static boolean[] Rchk;
	static boolean[] Lchk;

	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("3234_input"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			input = new int[N];
			Rselect = new boolean[N];
			totalSum = 0;
			ans = 0;
			st = new StringTokenizer(br.readLine());
			for (int n = 0; n < N; n++) {
				input[n] = Integer.parseInt(st.nextToken());
				totalSum += input[n];
			} // end input

			Arrays.sort(input);
			subset(0, 0, 0);
			System.out.println("#" + tc + " " + ans);
		} // end tc
	}// end main

	static int fact(int num) {
		if (num == 0)
			return 1;
		return fact(num - 1) * num;
	}

	static void product(int n, int lIdx, int rIdx, int lSum, int rSum) {
		if (lSum < rSum) // 저울 고장나서 오른쪽이 왼쪽보다 크면 안된다.
			return;
		if (lSum > totalSum / 2) { // 절반보다 크다면 남은 경우의 팩토리얼
			ans += fact((Lnum.length - lIdx) + (Rnum.length - rIdx));
			return;
		}
		if (n == N) { // n이 N이면 lSum이 전체 합의 반보다 큰 경
			ans++;
			return;
		}

		if (lIdx < Lnum.length) {
			for (int i = 0; i < Lnum.length; i++) {
				if (Lchk[i])// 동일한 친구는 선택할 수 없도록
					continue;
				Lchk[i] = true;
				product(n + 1, lIdx + 1, rIdx, lSum + Lnum[i], rSum);
				Lchk[i] = false;
			}
		}
		if (rIdx < Rnum.length) {
			for (int i = 0; i < Rnum.length; i++) {
				if (Rchk[i])// 동일한 친구는 선택할 수 없도록
					continue;
				Rchk[i] = true;
				product(n + 1, lIdx, rIdx + 1, lSum, rSum + Rnum[i]);
				Rchk[i] = false;
			}
		}

	}

	static void subset(int n, int r, int Rsum) { // 부분집합으로 R,L이 나뉘는 경우 생성
		if (Rsum > totalSum / 2 + 1) // 초과하면 경우의 수가 안나온다.
			return;

		if (n == N) {
			Rnum = new int[r];
			Lnum = new int[N - r];
			Rchk = new boolean[r];
			Lchk = new boolean[N - r];
			int lIdx = 0;
			int rIdx = 0;
			for (int i = 0; i < N; i++) { // R과 L에 올라갈 숫자를 결정
				if (Rselect[i])
					Rnum[rIdx++] = input[i];
				else
					Lnum[lIdx++] = input[i];
			}
			product(0, 0, 0, 0, 0); // 순열 문제 시작
			return;
		}

		Rselect[n] = true;
		subset(n + 1, r + 1, input[n] + Rsum);
		Rselect[n] = false;

		subset(n + 1, r, Rsum);
	}
}
