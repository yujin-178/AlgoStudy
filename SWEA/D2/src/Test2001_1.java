//package d2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//public class Solution {
public class Test2001_1 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.valueOf(br.readLine());
		StringTokenizer st;
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.valueOf(st.nextToken());
			int M = Integer.valueOf(st.nextToken());
			int[][] map = new int[N][N];
			for (int col = 0; col < N; col++) {
				st = new StringTokenizer(br.readLine());
				for (int row = 0; row < N; row++) {
					map[col][row] = Integer.valueOf(st.nextToken());
				}
			}
			int maxKill = 0;
			for (int c = 0; c <= N - M; c++) {
				for (int r = 0; r <= N - M; r++) {
					int sum = 0;
					for (int cm = 0; cm < M; cm++) {
						for (int rm = 0; rm < M; rm++) {
							sum += map[c + cm][r + rm];
						}
					}
					if (sum > maxKill)
						maxKill = sum;
				}
			}
			System.out.println("#" + tc + " " + maxKill);

		}
	}
}
