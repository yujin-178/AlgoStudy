import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

//public class Solution {
public class Test1218 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("C:/CodingStudy/SWEA/D4/1218_input.txt"));

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		Stack<Character> s = new Stack<Character>();

		for (int tc = 1; tc <= 10; tc++) {
			int bracketNum = Integer.valueOf(br.readLine());
			String str = br.readLine();
			boolean chk = true;
			for (int idx = 0; idx < bracketNum; idx++) {
//				System.out.println(str.charAt(idx));
				switch (str.charAt(idx)) {
				case '(':
					s.push('(');
					break;
				case '{':
					s.push('{');
					break;
				case '[':
					s.push('[');
					break;
				case '<':
					s.push('<');
					break;
				case ')':
					if (s.peek() == '(')
						s.pop();
					else
						chk = false;
					break;
				case '}':
					if (s.peek() == '{')
						s.pop();
					else
						chk = false;
					break;
				case ']':
					if (s.peek() == '[')
						s.pop();
					else
						chk = false;
						break;
				case '>':
					if (s.peek() == '<')
						s.pop();
					else
						chk = false;
					break;
				default:
					chk = false;
					break;
				}
				if (!chk)
					break;
			}
			if (chk)
				System.out.println("#" + tc + " 1");
			else
				System.out.println("#" + tc + " 0");

		}

	}
}
