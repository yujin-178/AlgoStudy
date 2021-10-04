import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class S3308_최소증가부분수열Hard_1 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {

			int N = Integer.parseInt(br.readLine());

			int[] arr = new int[N];
			int[] LIS = new int[N];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}


			int max = 0;
			for (int i = 0; i < N; i++) {
				int idx = Arrays.binarySearch(LIS, 0, max, arr[i]);
				idx = idx < 0 ? -idx - 1 : idx;
				LIS[idx] = arr[i];
				if(idx == max)
					max++;
//				System.out.println(Arrays.toString(LIS));
			}
			System.out.println("#" + tc + " " + max);

		}

	}
}
