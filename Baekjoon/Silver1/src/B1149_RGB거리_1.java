import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @FileName : B1149_RGB거리_1.java
 * @Date : 2021. 9. 14.
 * @작성자 : KimYuJin
 * @특이점 : 빨강, 초록, 파랑 1 != 2, n != n-1 i - 1 != i != i + 1
 * 
 */
public class B1149_RGB거리_1 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int r = 0;
		int g = 1;
		int b = 2;
		int[][] costMap = new int[3][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			costMap[r][i] = Integer.parseInt(st.nextToken());
			costMap[g][i] = Integer.parseInt(st.nextToken());
			costMap[b][i] = Integer.parseInt(st.nextToken());
		}

		int[][] sum = new int[3][N];
		sum[r][0] = costMap[r][0];
		sum[g][0] = costMap[g][0];
		sum[b][0] = costMap[b][0];

		for (int i = 1; i < N; i++) {
			sum[r][i] = Math.min(sum[g][i - 1] + costMap[r][i], sum[b][i - 1] + costMap[r][i]);
			sum[g][i] = Math.min(sum[r][i - 1] + costMap[g][i], sum[b][i - 1] + costMap[g][i]);
			sum[b][i] = Math.min(sum[r][i - 1] + costMap[b][i], sum[g][i - 1] + costMap[b][i]);
		}
		
		System.out.println(Math.min(sum[r][N-1], Math.min(sum[g][N-1], sum[b][N-1])));

	}
}
