import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class J1828_냉장고_1 {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("J1828_input"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		Sample[] s = new Sample[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			s[i] = (new Sample(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		Arrays.sort(s);
		System.out.println(simul(s));

	}

	static int simul(Sample[] s) {
		int cnt = 0;
		boolean[] chk = new boolean[s.length];
		int not = s.length;
		while (not != 0) {
			int start = 0;
			for (int i = 0; i < s.length; i++) {
				if (!chk[i]) {
					start = i;
					break;
				}

			}

			for (int i = start; i < s.length; i++) {
				if (chk[i])
					continue;
				if (s[start].end >= s[i].start ) {
					chk[i] = true;
					--not;
				}
			}
			cnt++;
		}
		return cnt;
	}

	static class Sample implements Comparable<Sample> {
		int start, end;

		public Sample(int start, int end) {
			super();
			this.start = start;
			this.end = end;
		}

		@Override
		public int compareTo(Sample o) {
			int val = this.end - o.end;
			if (val != 0)
				return val;
			else {
				return this.start - o.start;
			}
		}

	}
}
