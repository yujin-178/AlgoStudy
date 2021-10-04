import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//public class Solution {
public class SWEA9229_D3_1 {
	static int M, N, R = 2;
	static int[] up;
	static int maxWeight;

	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("C:/CodingStudy/SWEA/D3/9229_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for (int tc = 1; tc <= T; tc++) {
			maxWeight = 0;
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			up = new int[N];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				up[i] = Integer.parseInt(st.nextToken());
			}
			findMax(0, 0, 0);
			if(maxWeight == 0)
				System.out.println("#"+tc+ " "+-1);
			else
				System.out.println("#"+tc+ " "+maxWeight);
		}	

	}

	public static void findMax(int idx, int cnt, int nowWeight) {
		if (cnt == R) {
			if (nowWeight <= M) {
				if (nowWeight > maxWeight)
					maxWeight = nowWeight;
			}
			return;
		}
		if (idx >= N) {
			return;
		}
		findMax(idx + 1, cnt + 1, nowWeight + up[idx]);
		findMax(idx + 1, cnt, nowWeight);

	}
}
