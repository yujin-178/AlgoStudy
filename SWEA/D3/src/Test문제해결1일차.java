

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
//import java.util.Arrays;
import java.util.StringTokenizer;

//class Solution {
public class Test문제해결1일차 {
	public static void main(String[] args) throws NumberFormatException, IOException, Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		for (int tc = 1; tc <= 10; tc++) {
			int cnt = Integer.valueOf(br.readLine());
			int highH = 0;
			int lowH = 0;
			int[] box = new int[100];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 100; i++) {
				box[i] = Integer.valueOf(st.nextToken());
			}
			while (cnt-- != 0) {
				highH = findHigh(box);
				lowH = findLow(box);
				box[highH]--;

				box[lowH]++;
			}
			System.out.println("#" + tc + " " + (box[findHigh(box)] - box[findLow(box)]));
//			System.out.println(Arrays.toString(box));
		}

	}

	public static int findHigh(int[] box) {
		int max = 0;
		int maxIdx = 0;
		for (int i = 0; i < 100; i++) {
			if (box[i] > max) {
				max = box[i];
				maxIdx = i;
			}
		}
		return maxIdx;
	}

	public static int findLow(int[] box) {
		int min = 1000;
		int minIdx = 0;
		for (int i = 0; i < 100; i++) {
			if (box[i] < min) {
				min = box[i];
				minIdx = i;
			}

		}
		return minIdx;
	}
}
