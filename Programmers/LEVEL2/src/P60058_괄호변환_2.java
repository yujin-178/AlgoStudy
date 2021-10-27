public class P60058_괄호변환_2 {
	public static void main(String[] args) {
		String input = "()))((()";
		System.out.println(solution(input));
	}

	static public String solution(String p) {

		return dfs(p);
	}

	static String dfs(String input) {

		if ("".equals(input))
			return "";

		String u = "";
		String v = "";
		int openCnt = 0;
		int closeCnt = 0;
		boolean ballence = false;
		int len = input.length();
		for (int idx = 0; idx < len; idx++) {
			if ('(' == input.charAt(idx))
				++openCnt;
			else
				++closeCnt;
			if (openCnt < closeCnt)
				ballence = true;
			if (openCnt == closeCnt) {
				u = input.substring(0, idx + 1);
				v = input.substring(idx + 1, len);
				break;
			}
		}

		if (!ballence)
			return u + dfs(v);
		else {
			StringBuilder tmp = new StringBuilder();
			tmp.append("(");
			tmp.append(dfs(v));
			tmp.append(")");
			char[] uTmp = u.toCharArray();
			u = "";
			for (int i = 1; i < u.length(); i++) {
				u += "" + (uTmp[i] == '(' ? ')' : '(');
			}
			tmp.append(u);
			return tmp.toString();
		}
	}

}
