import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
  * @FileName : P12904_가장긴팰린드롬_1.java
  * @Date : 2021. 10. 09. 
  * @작성자 : KimYuJin
  * @특이점 : 반례 : ecdabbcadc
  * 문자열을 뒤집어서 convolution 연산을 수행하는 알고리즘인데
  * 회문이 아닌 경우에도 탐지한다.
  */
public class P12904_가장긴팰린드롬_1 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		System.out.println(solution(s));
	}

	static public int solution(String s) {
		char[] input = s.toCharArray();
		int len = input.length;
		char[] reInput = new char[len];
		int ans = 0;

		for (int i = 0; i < len; i++) {
			reInput[len - 1 - i] = input[i];
			int reIdx = len - i - 1;
			int inIdx = 0;
			int tmp = 0;
			int ttmp = 0;
			for (int j = 0; j <= i; j++) {
				if (input[inIdx++] == reInput[reIdx++])
					tmp++;
				else
					tmp = 0;
				if (ttmp < tmp)
					ttmp = tmp;
			}
			if (ttmp > ans)
				ans = ttmp;
		}

		for (int i = 1; i < len; i++) {
			int inIdx = i;
			int reIdx = 0;
			int tmp = 0;
			int ttmp = 0;
			for (int j = i; j < len; j++) {
				if (input[inIdx++] == reInput[reIdx++])
					tmp++;
				else
					tmp = 0;
				if (ttmp < tmp)
					ttmp = tmp;
			}
			if (ttmp > ans)
				ans = ttmp;
		}
		return ans;
	}
}
