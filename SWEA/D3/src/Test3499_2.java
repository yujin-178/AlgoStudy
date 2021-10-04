

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//public class Solution {
public class Test3499_2 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("C:/CodingStudy/SWEA/D3/3499_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			int num = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());
			String[] line = new String[num];
			for (int i = 0; i < num; i++) {
				line[i] = st.nextToken();
			}
			StringBuilder sb = new StringBuilder();
			sb.append("#" + tc + " ");
			if (num % 2 == 0) {
				for (int i = 0; i < num; i++) {
					sb.append(line[(i % 2 == 0) ? (i / 2) : (i / 2) + num / 2] + " ");
				}
			} else {
				sb.append(line[0] + " ");
				for (int i = 1; i < num; i++) {
					sb.append(line[(i % 2 == 1) ? (i / 2) + num / 2 + 1 : (i / 2)] + " ");
				}
			}
			System.out.println(sb);

		}
	}
}
