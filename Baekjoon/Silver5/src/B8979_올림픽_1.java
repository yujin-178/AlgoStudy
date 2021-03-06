import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B8979_올림픽_1 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		Score[] s = new Score[N];
		for (int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine());
			s[n] = new Score(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
					Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		Arrays.sort(s);
		int ans = 0;
		for (int n = 0; n < N; n++) {
			if (s[n].idx == K) {
				int t = n;
				int o = n;
				if (t == 0) { // 맨 앞인 경우 
					ans = 1; // 1등
					break;
				}
				while (true) {
					
					if (s[t].compareTo(s[--o]) != 0) {
						ans = o + 2; // 앞에 애와 다르다면 n번째 인덱스는 0부터 시작이니까 1을 더하고
						// 개수 + 1이 본인의 등수가 되니까 2를 더한다.
						break;
					}
				}
				break;
			}
		}
		System.out.println(ans);

	}

	static class Score implements Comparable<Score> {
		int idx, g, s, b;

		public Score(int idx, int g, int s, int b) {
			super();
			this.idx = idx;
			this.g = g;
			this.s = s;
			this.b = b;
		}

		@Override
		public int compareTo(Score o) {
			int gScore = o.g - this.g;
			if (gScore == 0) {
				int sScore = o.s - this.s;
				if (sScore == 0) {
					return o.b - this.b;
				} else
					return sScore;
			} else
				return gScore;
		}

	}
}
