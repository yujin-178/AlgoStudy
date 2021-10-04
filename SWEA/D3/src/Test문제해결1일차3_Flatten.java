

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//class Solution {
public class Test문제해결1일차3_Flatten {
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
				int tmp = Integer.valueOf(st.nextToken()) - 1;
				if (highH < tmp)
					highH = tmp;
				if (lowH > tmp)
					lowH = tmp;
				box[tmp]++;
			}
			while (cnt-- != 0) {
				box[highH]--;
				box[highH - 1]++;
				box[lowH]--;
				box[lowH + 1]++;
				if (box[highH] == 0)
					highH--;
				if (box[lowH] == 0)
					lowH++;
			}
			System.out.println("#" + tc + " " + (highH - lowH));
		}
	}

}
