import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
//import java.util.Arrays;
import java.util.StringTokenizer;

public class B2531_회전초밥_1 {
//	public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 접시의 수
		int d = Integer.parseInt(st.nextToken()); // 초밥 종류 개수
		int k = Integer.parseInt(st.nextToken()); // 연속해서 먹는 접시의 수
		int c = Integer.parseInt(st.nextToken()); // 쿠폰 번호
		int[] food = new int[N + k - 1];
		int[] type = new int[d + 1];
		for (int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine());
			food[n] = Integer.parseInt(st.nextToken());
		}
		for (int n = N; n < N + k - 1; n++) {
			food[n] = food[n - N];
		}

		int cnt = 1; // 쿠폰
		int max = 0;
		type[c]++; // 쿠폰
		for (int idx = 0; idx < N + k - 1; idx++) {
			if (idx >= k) { // 음식의 개수가 k가 된 순간부터 동작함
				type[food[idx - k]]--; // idx - k 번째 음식 제거
				if (type[food[idx - k]] == 0) // 0이면
					cnt--; // 카운트 포함 안함
			}
			if (type[food[idx]] == 0) // 0이면
				cnt++;// 카운트 포함
			type[food[idx]]++;// 음식 증가
			max = Math.max(max, cnt); // 최대치 갱신
//			System.out.println(Arrays.toString(type) + " " + max);
		}
		System.out.println(max);
	}
}
