import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B2846_오르막길 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int[] num = new int[N];

		st = new StringTokenizer(br.readLine());
		num[0] = Integer.parseInt(st.nextToken());
		int[] sum = new int[N - 1];
		for (int n = 1; n < N; n++) {
			num[n] = Integer.parseInt(st.nextToken());
			sum[n - 1] = num[n] - num[n - 1];
		}
		int ans = Math.max(0, sum[0]);
		for (int n = 1; n < N - 1; n++) {
			if(sum[n]>0 && sum[n-1]>0)
				sum[n] +=sum[n-1];
			ans = Math.max(ans, sum[n]);
		}
		System.out.println(ans);
	}
}
