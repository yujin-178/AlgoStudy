import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B1592_영식이와_친구들 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());

		int[] cnt = new int[N];
		int now = 0;
		int ans = 0;
		while (true) {
			++cnt[now];
			++ans;
			if (cnt[now] == M)
				break;
			else {
				now += cnt[now] % 2 == 0 ? -L : L;
				if (now >= N || now < 0)
					now %= N;
				now = now < 0 ? now + N : now;
			}
//			System.out.println(Arrays.toString(cnt));
		}
		System.out.println(--ans);
	}
}
