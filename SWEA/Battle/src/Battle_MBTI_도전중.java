import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Battle_MBTI_도전중 {
	static int n, max, p1, p2;
	static Person[] p;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			p = new Person[n];
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				p[i] = new Person(st.nextToken(), st.nextToken(), Integer.parseInt(st.nextToken()), i + 1);
			}

		}
	}

	static void comb(int start) {
		if (start == n - 1)
			return;

		for (int i = start + 1; i < n; i++) {
			if (p[start].mbti.equals(p[i].mbti))
				continue;
			if (p[start].mbti.contains("I") && p[i].mbti.contains("I"))
				continue;
			if (p[start].mbti.contains("P") && p[i].mbti.contains("T")
					|| p[start].mbti.contains("I") && p[i].mbti.contains("P"))
				continue;
		}

		comb(start + 1);
	}

	static class Person {
		String mbti, blood;
		int power, idx;

		public Person(String mbti, String blood, int power, int idx) {
			super();
			this.mbti = mbti;
			this.blood = blood;
			this.power = power;
			this.idx = idx;
		}

	}
}
