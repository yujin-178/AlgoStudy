import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class B17413_단어뒤집기2_2 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] line = br.readLine().toCharArray();
		StringBuilder ans = new StringBuilder();
		boolean bracket = false, white = false, word = false;
		Stack<Character> s = new Stack<>();
		for (int i = 0; i < line.length; i++) {
			if (line[i] == '<') { // 태그 시작
				bracket = true;
			} else if (line[i] == '>') { // 태그 종료
				ans.append(line[i]);
				bracket = false;
				// 전부 입력하는 동작
			} else if (!bracket && line[i] == ' ') { // 공백인 경우
				white = true;
			} else { // 태그 아닌 단어인 경우
				if(!bracket) 
				word = true;
			}

			if (bracket && !word) { // 태그인 경우
				ans.append(line[i]);
			} else if (bracket && word) { // 단어다음 바로 태그가 오는 경우
				while (!s.isEmpty()) { // 단어 전부 뒤집어서 입력
					ans.append(s.pop());
				}
				word = false; // 단어가 종료됨
				ans.append('<'); // 태그 시작 알림
			} else if (!word && white) { // 태그가 종료되고 공백이 시작인 경우
				ans.append(' ');
				white = false;
			} else if (word && white) { // 단어이면서 다음에 공백이 나온 경우
				while (!s.isEmpty()) {
					ans.append(s.pop());
				}
				ans.append(' ');
				word = false;
				white = false;
			} else if (word) { // 태그가 아닌 단어인 경우
				s.push(line[i]);
			}
		}
		while (!s.isEmpty()) { // 단어만 나오거나 마지막 단어인 경우
			ans.append(s.pop());
		}

		System.out.println(ans.toString());

	}
}
