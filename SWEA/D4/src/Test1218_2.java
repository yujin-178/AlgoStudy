import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

//public class Solution {
public class Test1218_2 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("C:/CodingStudy/SWEA/D4/1218_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Stack<Character> s = new Stack<Character>();
		for (int tc = 1; tc <= 10; tc++) {
			int bracketNum = Integer.valueOf(br.readLine());
			String str = br.readLine();
			boolean chk = true;
			int chkPop = 1;
			for (int idx = 0; idx < bracketNum; idx++) {
				char tmp = str.charAt(idx);
				if ((tmp == '(') || (tmp == '{') || (tmp == '[') || (tmp == '<')) {
					s.push(tmp);
					chkPop = 0;
				}
				else if (tmp == ')')
					chkPop = (s.peek() == '(') ? 1 : 2;
				else if (tmp == '}')
					chkPop = (s.peek() == '{') ? 1 : 2;
				else if (tmp == ']')
					chkPop = (s.peek() == '[') ? 1 : 2;
				else if (tmp == '>')
					chkPop = (s.peek() == '<') ? 1 : 2;

				if (chkPop == 1)
					s.pop();
				if (chkPop == 2) {
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
