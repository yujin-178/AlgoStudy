import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P12930_이상한문자만들기_1 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		System.out.println(solution(s));
	}

	static public String solution(String s) {
		char[] tmp = s.toCharArray();
		int len = tmp.length;
		int tmpPlus = 'A' - 'a';
		String answer = "";
		int cnt = 0;
		for (int i = 0; i < len; i++) {
			if (cnt % 2 == 0) {
				if (tmp[i] >= 'a' && tmp[i] <= 'z')
					tmp[i] += tmpPlus;
			} else {
				if (tmp[i] >= 'A' && tmp[i] <= 'Z')
					tmp[i] -= tmpPlus;
			}
			cnt++;
			if (tmp[i] == ' ')
				cnt = 0;
			answer += tmp[i] + "";
		}
		return answer;
	}

}
