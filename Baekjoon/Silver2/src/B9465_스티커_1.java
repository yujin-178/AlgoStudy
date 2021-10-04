import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B9465_스티커_1 {
	static int[][] stamp, result;
	static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			stamp = new int[2][N];
			result = new int[2][N];
			for (int i = 0; i < 2; i++) {
				st = new StringTokenizer(br.readLine());
				for (int n = 0; n < N; n++) {
					stamp[i][n] = Integer.parseInt(st.nextToken());
				}
			}

			// 스티커가 1줄인 경우
			result[0][0] = stamp[0][0];
			result[1][0] = stamp[1][0];
			if (N > 1) { // 스티커가 2줄 인경우
				result[0][1] = stamp[1][0] + stamp[0][1];
				result[1][1] = stamp[0][0] + stamp[1][1];
			}
			if (N > 2) { // 스티커가 3줄 이상인 경우

				for (int n = 2; n < N; n++) {
					result[0][n] = Math.max(Math.max(result[0][n - 2], result[1][n - 2]), result[1][n - 1])
							+ stamp[0][n]; // n번째 위의 경우 : n-1 아래, n-2 위,아래 3개중 가장 큰 값을 사용한다.
					result[1][n] = Math.max(Math.max(result[1][n - 2], result[0][n - 2]), result[0][n - 1])
							+ stamp[1][n]; // n번째 아래의 경우 : n-1 위, n-2 위,아래 3개중 가장 큰 값을 사용한다.
				}

			}
			System.out.println(Math.max(result[0][N - 1], result[1][N - 1])); // 맨 마지막 값 둘 중 큰 값을 출력한다.

		}
	}

}
