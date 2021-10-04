//package d3;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//public class Solution {
public class Test3499_1 {
	public static void main(String[] args) throws NumberFormatException, IOException {
//		System.setIn(new FileInputStream("C:/CodingStudy/SWEA/D3/3499_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			int num = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());
			String[] line1 = new String[num];
			String[] line2 = new String[num];
			int front1 = 0;
			int front2 = 0;
			int rear1 = 0;
			int rear2 = 0;
			for (int i = 0; i < num; i++) {
				if (num % 2 == 0) {
					if (i < num / 2)
						line1[front1++] = st.nextToken();
					else
						line2[front2++] = st.nextToken();
				} else {
					if (i <= num / 2)
						line1[front1++] = st.nextToken();
					else
						line2[front2++] = st.nextToken();
				}
			}
			StringBuilder sb = new StringBuilder();
			sb.append("#" + tc + " ");
			for (int i = 0; i < num; i++) {
				sb.append((i % 2 == 0) ? line1[rear1++] + " " : line2[rear2++] + " ");
			}
			System.out.println(sb);
		}
	}
}
