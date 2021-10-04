import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//public class Solution {
public class SWEA1233_1 {
	public static void main(String[] args) throws NumberFormatException, IOException {
//		System.setIn(new FileInputStream("C:/CodingStudy/SWEA/D4/1233_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		String chk = "/*+-";
		for (int tc = 1; tc <= 10; tc++) {
			boolean endFlag = false;
			int num = Integer.parseInt(br.readLine());
			for (int idx = 1; idx <= num; idx++) {
				st = new StringTokenizer(br.readLine());
				st.nextToken();
				String tmp = st.nextToken();
				if (endFlag)
					continue;
				if (tmp.length() == 1) {
					if (chk.contains(tmp)) {
						endFlag = !st.hasMoreTokens();
					} else {
						endFlag = st.hasMoreTokens();
					}
				} else {
					endFlag = st.hasMoreElements();
				}	
			}
			if (endFlag)
				System.out.println("#" + tc + " " + 0);
			else
				System.out.println("#" + tc + " " + 1);
		}
	}
}
