import java.io.FileInputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

//public class Solution {
public class S4796_의석이의우뚝선산_2 {
	static int T, N;
	static LinkedList<Center> l;

	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("4796_input"));
		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();

		for (int tc = 1; tc <= T; tc++) {
			int ans = 0;
			N = sc.nextInt();
			l = new LinkedList<>();
			int[] num = new int[N];
			num[0] = sc.nextInt();
			num[1] = sc.nextInt();
			for (int n = 2; n < N; n++) {
				num[n] = sc.nextInt();
				if (num[n - 1] > num[n - 2] && num[n - 1] > num[n])
					l.add(new Center(n - 1));
			}

			if (l.isEmpty()) { // 센터가 없으면 0
				System.out.println("#" + tc + " " + 0);

			} else { // 센터가 있으면 센터마다 만들 수 있는 산 개수 계산
				for (int cIdx = 0; cIdx < l.size(); cIdx++) {
					int center = l.get(cIdx).idx;
					int mCnt = 0;
					for (int m = center; m > 0; m--) {
						if (num[m] > num[m - 1]) {
							++mCnt;
						} else
							break;
					}
					int pCnt = 0;
					for (int p = center; p < N - 1; p++) {
						if (num[p] > num[p + 1]) {
							++pCnt;
						} else
							break;
					}
					ans += (int) Math.pow(2, ((mCnt + 1) + pCnt - 3));
				}
				System.out.println("#" + tc + " " + ans);
			}
		}
		sc.close();
	}

	static class Center {
		int idx;

		public Center(int idx) {
			super();
			this.idx = idx;
		}

	}
}
