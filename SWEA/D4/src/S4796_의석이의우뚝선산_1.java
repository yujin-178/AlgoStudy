import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

//public class Solution {
public class S4796_의석이의우뚝선산_1 {
	static int T, N;
	static LinkedList<Center> l;

	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("4796_input"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in), 2 * 500000);
		T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			int ans = 0;
			N = Integer.parseInt(br.readLine());
			l = new LinkedList<>();
			int[] diff = new int[N];
			int[] num = new int[N];
			StringTokenizer st = new StringTokenizer(br.readLine());
			num[0] = Integer.parseInt(st.nextToken());
			num[1] = Integer.parseInt(st.nextToken());
			diff[0] = num[0];
			diff[1] = num[1] - num[0];

			for (int n = 2; n < N; n++) {
				num[n] = Integer.parseInt(st.nextToken());
				diff[n] = num[n] - num[n - 1];
				if (diff[n - 1] > 0 && diff[n - 2] > 0 && diff[n] < 0)
					l.add(new Center(n - 1));
			}

			if (l.isEmpty()) { //
				System.out.println("#" + tc + " " + 0);
			} else {
				for (int cIdx = 0; cIdx < l.size(); cIdx++) {
					int center = l.get(cIdx).idx;
					int mCnt = 0;
					for (int m = center - 1; m >= 0; m--) {
						if (m > 0 && diff[m] > 0) {
							++mCnt;
						} else
							break;

					}
					int pCnt = 0;
					for (int p = center + 1; p < N; p++) {
						if (p < N && diff[p] < 0) {
							++pCnt;
						} else
							break;
					}
					ans += (int) Math.pow(2, ((mCnt + 1) + pCnt - 2));
				}
				System.out.println("#" + tc + " " + ans);
			}

		}
	}

	static class Center {
		int idx;

		public Center(int idx) {
			super();
			this.idx = idx;
		}

	}
}
