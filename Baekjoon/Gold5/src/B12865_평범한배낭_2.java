import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B12865_평범한배낭_2 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int num = Integer.parseInt(st.nextToken());
		int maxW = Integer.parseInt(st.nextToken());

		int[] W = new int[num + 1]; // 무게 저장
		int[] V = new int[num + 1]; // 값어치 저장
		for (int n = 1; n <= num; n++) {
			st = new StringTokenizer(br.readLine());
			W[n] = Integer.parseInt(st.nextToken()); // n번째 물품의 중량
			V[n] = Integer.parseInt(st.nextToken()); // n번쨰 물품의 값어치
		}

		int[][] DP = new int[num + 1][maxW + 1];
		for (int n = 1; n <= num; n++) {
			for (int w = 1; w <= maxW; w++) {
				if (W[n] <= w) // 현재 선택한 물품의 무게가 가방의 여유 허용중량 이내라면
					DP[n][w] = Math.max(DP[n - 1][w], V[n] + DP[n - 1][w - W[n]]); // 이전 물품까지 고려한 최대 값어치와 이번 물품을 고려한 최대
																					// 값어치중 큰 값을 대입한다.
				else
					DP[n][w] = DP[n - 1][w]; // 허용 중량이 부족하다면 이전 기록을 그대로 가져온다.
			}
		}
		System.out.println(DP[num][maxW]); // 마지막의 최대 값어치를 가져온다.

	}
}
