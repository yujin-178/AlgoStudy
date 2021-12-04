
/**
  * @FileName : test01.java
  * @Date : 2021. 12. 4. 
  * @작성자 : KimYuJin
  * @특이점 : 84점
  */
public class test01 {
	public static void main(String[] args) {
		String[] s = { "######", ">#*###", "####*#", "#<#>>#", ">#*#*<", "######", };
		System.out.println(solution(s));
	}

	static char[][] map;
	static int[] dr = { 1, 0, 0 };
	static int[] dc = { 0, -1, 1 };

	static public int solution(String[] drum) {
		int len = drum.length;
		map = new char[len][];
		for (int i = 0; i < len; i++) {
			map[i] = drum[i].toCharArray();
		}
		int len2 = map[0].length;

		int ans = 0;

		for (int i = 0; i < len2; i++) {
			if (chk(i, len))
				ans++;
		}

		return ans;

	}

	static boolean chk(int startIdx, int endRow) {
		int cnt = 0;
		int r = 0;
		int c = startIdx;
		while (r != endRow) {
			switch (map[r][c]) {
			case '#':
				r += dr[0];
				c += dc[0];
				break;
			case '>':
				r += dr[2];
				c += dc[2];
				break;
			case '<':
				r += dr[1];
				c += dc[1];
				break;
			case '*':
				r += dr[0];
				c += dc[0];
				cnt++;
				break;
			}
			if (cnt == 2)
				return false;
		}
		return true;
	}
}
