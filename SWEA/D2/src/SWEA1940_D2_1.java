

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//public class Solution {
public class SWEA1940_D2_1 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for (int tc = 1; tc <= T; tc++) {
			int num = Integer.parseInt(br.readLine());
			int distance = 0;
			int speed = 0;
			for (int i = 0; i < num; i++) {
				st = new StringTokenizer(br.readLine());
				int cNum = Integer.parseInt(st.nextToken());
				if (cNum == 1) {
					speed += Integer.parseInt(st.nextToken());
				} else if (cNum == 2) {
					int tmp = Integer.parseInt(st.nextToken());
					if (tmp >= speed)
						speed = 0;
					else
						speed -= tmp;
				}
				distance += speed;
			}
			System.out.println("#" + tc + " " + distance);
		}
	}
}
