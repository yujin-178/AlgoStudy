import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

//public class Solution {
public class Test1218_3 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("C:/CodingStudy/SWEA/D4/1218_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int tc = 1; tc <= 10; tc++) {
			int idxMax = Integer.valueOf(br.readLine());
			String str = br.readLine();
			char[] strChar = new char[idxMax];
			int charIdx = 0;
			boolean chk = true;
			int chkPop = 1;
			int idx= 0;
			while(idx < idxMax) { // 최대 크기 초과시 종료
				char tmp = str.charAt(idx++);
//				System.out.println(tmp);
				if ((tmp == '(') || (tmp == '{') || (tmp == '[') || (tmp == '<')) {
					strChar[charIdx++] = tmp;
					chkPop = 0;
				}else if (tmp == ')')
					chkPop = (strChar[charIdx-1] == '(') ? 1 : 2;
				else if (tmp == '}')
					chkPop = (strChar[charIdx-1] == '{') ? 1 : 2;
				else if (tmp == ']')
					chkPop = (strChar[charIdx-1] == '[') ? 1 : 2;
				else if (tmp == '>')
					chkPop = (strChar[charIdx-1] == '<') ? 1 : 2;
				if (chkPop == 1)
					--charIdx;
				else if (chkPop == 2) {
					chk = false;
					break;
				}
			}
			
			if (chk)
				System.out.println("#" + tc + " 1");
			else
				System.out.println("#" + tc + " 0");
		}

	}
}
