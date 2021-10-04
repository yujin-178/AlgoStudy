import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

//public class Solution {
public class SWEA4789_1 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("C:/CodingStudy/SWEA/D3/4789_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			String tmp = br.readLine();
			int cnt = tmp.charAt(0) - '0';
			int ans = 0;
			for (int idx = 1; idx < tmp.length(); idx++) {
				if (cnt < idx) {
					ans += (idx - cnt);
					cnt = idx;
				}
				cnt += tmp.charAt(idx) - '0';
			}
			System.out.println("#" + tc + " " + ans);
		}
	}
}
