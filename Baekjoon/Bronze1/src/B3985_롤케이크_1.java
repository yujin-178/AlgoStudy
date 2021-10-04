

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B3985_롤케이크_1 {
	static int N, L, max, maxIdx, exMax, exMaxIdx;
	static int[] cake, cnt;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		L = Integer.parseInt(br.readLine());
		N = Integer.parseInt(br.readLine());
		cake = new int[L + 1];
		cnt = new int[N + 1];
		for (int n = 1; n <= N; n++) { // 방청객 번호
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			if (exMax < end - start) {
				exMax = end - start;
				exMaxIdx = n;
			}

			for (int idx = start; idx <= end; idx++) {
				if (cake[idx] == 0) {
					cake[idx] = n;
					if (max < ++cnt[n]) {
						max = cnt[n];
						maxIdx = n;
					}
				}
			}

		}

		System.out.println(exMaxIdx);
		System.out.println(maxIdx);
	}
}
