

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

//public class Solution{
public class Test2805_2 {

	public static void main(String[] args) throws NumberFormatException, IOException {
//		System.setIn(new FileInputStream("C:/CodingStudy/SWEA/D3/Test2805_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.valueOf(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.valueOf(br.readLine());
			int value = (N / 2);
			int sum = 0;
			for (int c = 0; c < N; c++) {
				String lineNum = br.readLine();
				int cVal = 0;
				int rVal = 0;
				if ((c - value) > 0)
					cVal = (c - value);
				else
					cVal = -(c - value);
				for (int r = 0; r < N; r++) {
					if ((r - value) > 0)
						rVal = (r - value);
					else
						rVal = -(r - value);
					if ((cVal + rVal) <= (value)) {
						sum += (lineNum.charAt(r) - '0');
					}
				}
			}
			System.out.println("#" + tc + " " + sum);

		}
	}
}
