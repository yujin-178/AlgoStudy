import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P72310_신규아이디추천_1 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		System.out.println(solution(s));
	}

	static public String solution(String new_id) {

		char[] a = new_id.toLowerCase().toCharArray();
		int len = a.length;
		int cnt = 0;
		String answer = "";

		for (int i = 0; i < len; i++) {
			if (cnt == 0) {
				if ((a[i] >= 'a' && a[i] <= 'z') || a[i] == '-' || a[i] == '_' || (a[i] >= '0' && a[i] <= '9')) {
					a[cnt++] = a[i];
				}
			} else {
				if ((a[i] >= 'a' && a[i] <= 'z') || a[i] == '-' || a[i] == '_' || a[i] == '.'
						|| (a[i] >= '0' && a[i] <= '9')) {
					if (a[cnt - 1] == '.' && a[i] == '.')
						continue;
					else
						a[cnt++] = a[i];
				}
			}
			if (cnt == 15)
				break;
		}

		while (cnt > 0 && a[cnt - 1] == '.') {
			--cnt;
		}
		
		for (int i = 0; i < cnt; i++) {
			answer += a[i] + "";
		}
		
		if (cnt == 0) {
			answer += "a";
			cnt = 1;
		}

		if (cnt < 3) {
			while (cnt != 3) {
				answer+=answer.charAt(cnt-1)+"";
				++cnt;
			}
		}
		

		return answer;
	}
}
