import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @FileName : P12904_가장긴팰린드롬_2.java
 * @Date : 2021. 10. 10.
 * @작성자 : KimYuJin
 * @특이점 : 완전 탐색을 이용했는데
 * 시간초과 난다.
 */
public class P12904_가장긴팰린드롬_2 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		System.out.println(solution(s));
	}

	static public int solution(String s) {
		char[] input = s.toCharArray();
		int len = input.length;
		int ans = 0;
		for (int l = 1; l <= len; l++) {
			for (int idx = 0; idx < len - l + 1; idx++) {
				int cnt = 0;
				for (int nIdx = 0; nIdx < l; nIdx++) {
					if (input[idx + nIdx] == input[idx + l - 1 - nIdx])
						cnt++;
				}
				if (cnt == l)
					ans = Math.max(ans, cnt);
			}
		}
		return ans;
	}
}
