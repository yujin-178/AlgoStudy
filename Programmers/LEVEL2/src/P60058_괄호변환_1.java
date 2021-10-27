public class P60058_괄호변환_1 {
	public static void main(String[] args) {
		String input = "ababcdcdababcdcd";
		System.out.println(solution(input));
	}

	static public int solution(String s) {
		int ans = s.length();
		int len = ans;
		char[] input = s.toCharArray();
		for (int i = 1; i <= s.length() / 2; i++) {
			int cnt = 1;
			String tmp = "";
			String answer = "";
			String now = "";
			for (int j = 0; j < len + i; j += i) {

				int start = j;
				int end = Math.min(len, j + i);
				if (start >= len)
					now = "";
				else
					now = s.substring(start, end);
//				System.out.println(start + ", " + end);
				if (now.equals(tmp)) {
					cnt++;
				} else {

					if (cnt <= 1)
						answer += tmp;
					else
						answer += cnt + tmp;
					tmp = now;
					cnt = 1;
				}

			}
//			System.out.println(answer);
			ans = Math.min(ans, answer.length());
		}

		return ans;
	}

}
