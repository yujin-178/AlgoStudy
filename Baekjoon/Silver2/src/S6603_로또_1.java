import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S6603_로또_1 {
	static StringBuilder sb = new StringBuilder();
	static int num[];
	static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 첫번째 N 입력

		while (N != 0) { // N이 0이면 종료
			num = new int[N];
			for (int i = 0; i < N; i++) {
				num[i] = Integer.parseInt(st.nextToken());
			}
			comb(0, 0, "");
			sb.append("\n");

			// 다음줄 입력 후 첫 번째 숫자 N에 입력
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
		}
		System.out.println(sb.toString());
	}

	static void comb(int n, int r, String s) {
		if (r == 6) {
			sb.append(s).append("\n");
			return;
		}
		if (n >= N)
			return;

		comb(n + 1, r + 1, s + num[n] + " ");
		comb(n + 1, r, s);

	}
}
