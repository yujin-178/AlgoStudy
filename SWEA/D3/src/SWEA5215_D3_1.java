

import java.awt.BufferCapabilities;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//public class Solution {
public class SWEA5215_D3_1 {
	static IngreInfo[] info = null;
	static int num;
	static int limit;
	static int maxScore;

	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("C:/CodingStudy/SWEA/D3/5215_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for (int tc = 1; tc <= T; tc++) {
			maxScore = 0;
			st = new StringTokenizer(br.readLine());
			num = Integer.parseInt(st.nextToken());
			limit = Integer.parseInt(st.nextToken());
			info = new IngreInfo[num];
			for (int i = 0; i < num; i++) {
				info[i] = new IngreInfo();
			}

			for (int i = 0; i < num; i++) {
				st = new StringTokenizer(br.readLine());
				info[i].score = Integer.parseInt(st.nextToken());
				info[i].calorie = Integer.parseInt(st.nextToken());
			} // end input

			findMax(0, 0, 0); // start find MaxScore
			System.out.println("#" + tc + " " + maxScore);

		} // end tc
	}// end main

	public static void findMax(int idx, int sumScore, int sumCalorie) {
		if (idx >= num) {
			if (sumCalorie <= limit) {
				if (maxScore < sumScore)
					maxScore = sumScore;
			}
			return;
		}

		if (sumCalorie + info[idx].calorie <= limit)
			findMax(idx + 1, sumScore + info[idx].score, sumCalorie + info[idx].calorie);
		findMax(idx + 1, sumScore, sumCalorie);

	}

	public static class IngreInfo {
		int score = 0;
		int calorie = 0;
	}
}
