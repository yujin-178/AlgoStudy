import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

//public class Solution {
public class S4796_의석이의우뚝선산_3 {
	static int T, N;
	static LinkedList<Center> l;
	static int[] h;

	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("4796_input"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		T = Integer.parseInt(st.nextToken());

		for (int tc = 1; tc <= T; tc++) {
			int ans = 0;
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			l = new LinkedList<>();
			h = new int[N + 1];

			st = new StringTokenizer(br.readLine());

			h[0] = 0;
			h[1] = Integer.parseInt(st.nextToken());
			int idx = 0;
			int cnt = 1;
			boolean incre = false;
			boolean decre = false;
			for (int i = 2; i <= N; i++) {
				h[i] = Integer.parseInt(st.nextToken());
				if (incre && decre) {
					if (N == i) {
						++cnt;
					}
					if ((h[i - 1] < h[i])) {
						l.add(new Center(idx, cnt));
						cnt = 1;
						incre = false;
						decre = false;
						continue;
					}
				}

				if (N == i && incre && h[i - 1] > h[i]) {
					cnt++;
					l.add(new Center(idx, cnt));
				}

				if (h[i - 1] < h[i]) {
					idx = i;
					++cnt;
					incre = true;
				} else {
					++cnt;
					decre = true;
				}

			}

			for (Center c : l) {
				ans += c.sub();
			}

			System.out.println("#" + tc + " " + ans);

		}
		br.close();
	}

	static class Center {
		int idx;
		int cnt;

		public Center(int idx, int cnt) {
			super();
			this.idx = idx;
			this.cnt = cnt;
		}

		public int sub() {
			if (cnt >= 3)
				return (int) Math.pow(2, (cnt - 3));
			else
				return 0;
		}

		@Override
		public String toString() {
			return "Center [idx=" + idx + ", cnt=" + cnt + "]";
		}

	}
}
