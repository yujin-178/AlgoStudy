

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

//public class Solution{
public class Test2805_1 {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("C:/CodingStudy/SWEA/D3/Test2805_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.valueOf(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.valueOf(br.readLine());
			int value = (N / 2);
			int sum = 0;
			for (int c = 0; c < N; c++) {
				String lineNum = br.readLine();
				for (int r = 0; r < N; r++) {
					if (((c - value) * (c - value) + (r - value) * (r - value)) <= (value * value)) {
						sum += (lineNum.charAt(r) - '0');
//						System.out.println(c + " " + sum);
					}
				}
			}
			System.out.println("#" + tc+ " " + sum);

		}
	}
}
