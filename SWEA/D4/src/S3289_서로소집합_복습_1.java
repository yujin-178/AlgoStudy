import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S3289_서로소집합_복습_1 {
	static int N;
	static int[] num;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			make();
			int M = Integer.parseInt(st.nextToken());
			for (int m = 0; m < M; m++) {
				st = new StringTokenizer(br.readLine());
				if (st.nextToken().equals("0")) {
					
				} else {
					
				}
			}
		}

	}

	public static void make() {
		num = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			num[i] = i;
		}
	}
	
	public static int find(int a, int b) {
		if(num[a] == num[b])
			return 1;
		
	}
}
