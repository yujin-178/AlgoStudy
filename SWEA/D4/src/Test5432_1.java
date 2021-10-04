import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

//public class Solution{
public class Test5432_1 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("C:/CodingStudy/SWEA/D4/5432_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.valueOf(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			String str = br.readLine();
			char[] c = new char[str.length()];
			c[0] = str.charAt(0);
			int laserNum = 0;
			int plateNum = 0;
			int cnt = 0;
			int sum = 0;
			if (c[0] == '(') {
				++cnt;
				plateNum++;
			}
			for (int idx = 1; idx < str.length(); idx++) {
				
				c[idx] = str.charAt(idx);
				if ((c[idx - 1] == '(') && (c[idx] == ')')) {
					++laserNum;
					sum+=(plateNum-1);
				}
				if (c[idx] == '(') {
					++plateNum;
					++cnt;
				}
				else
					--plateNum;
			}
			sum +=(cnt - laserNum);
			System.out.println ("#" + tc +" "+sum);		

		}
	}
}
