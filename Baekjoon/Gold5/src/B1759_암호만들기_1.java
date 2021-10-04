import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B1759_암호만들기_1 {
	static char[] in;
	static boolean[] chk;
	static int L, C;
	static String[] strChk = { "a", "e", "i", "o", "u" };

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("1759_input"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		in = new char[C];
		chk = new boolean[C];
		for (int c = 0; c < C; c++) {
			in[c] = st.nextToken().charAt(0);
		}
		Arrays.sort(in);
		System.out.println(Arrays.toString(in));
		comb(0, 0);

	}

	static void comb(int n, int r) {
		if (r == L) {
			StringBuilder sb = new StringBuilder();

			for (int i = 0; i < C; i++) {
				if (chk[i])
					sb.append(in[i]);
			}
			String tmp = sb.toString();
			int cnt = 0;
			for (int i = 0; i < 5; i++) {
				if (tmp.contains(strChk[i]))
					++cnt;
			}
			if ((cnt >= 1) && (L - cnt >= 2))
				System.out.println(tmp);
			return;
		}
		if (n == C)
			return;

		chk[n] = true;
		comb(n + 1, r + 1);
		chk[n] = false;
		comb(n + 1, r);

	}
}
