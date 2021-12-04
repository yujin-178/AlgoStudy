import java.util.HashMap;

/**
  * @FileName : test03_2.java
  * @Date : 2021. 12. 4. 
  * @작성자 : KimYuJin
  * @특이점 : 26.3 메모리 및 시간 초과
  */
public class test03_2 {

	public static void main(String[] args) {
		System.out.println("ans : " + solution("tooth"));
	}

	static char[][] keyboard = { { 'q', 'w', 'e', 'r', 't', 'y', 'u', 'i', 'o' },
			{ 'p', 'a', 's', 'd', 'f', 'g', 'h', 'j', 'k' }, { 'l', 'z', 'x', 'c', 'v', 'b', 'n', 'm', ' ' } };
	static int[][] map;
	static HashMap<String, Integer> record;

	static public int solution(String s) {
		int len = s.length();
		if (len == 1)
			return 0;
		map = new int[26][26];
		Pos[] alpha = new Pos[26];
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 9; j++) {
				if (keyboard[i][j] <= 'z' && keyboard[i][j] >= 'a')
					alpha[(int) (keyboard[i][j] - 'a')] = new Pos(i, j, keyboard[i][j] - 'a');
			}
		}

		for (int i = 0; i < 26; i++) {
			for (int j = 0; j < 26; j++) {
				if (i == j)
					continue;
				map[i][j] = calcDis(alpha[i], alpha[j]);
			}
		}

		int ans = 0;

		record = new HashMap<>();
		for (int i = 2; i <= len; i++) { // i 선택한 부분 문자열 길이
			for (int j = 0; j <= len - i; j++) { // j 시작 위치
				ans += calcComplex(s.substring(j, j + i), i);
			}
		}

		return ans;
	}

	static int calcComplex(String s, int len) {
		int ans = 0;
		if (record.containsKey(s.substring(0, s.length() - 1))) {
			System.out.print(s.substring(0, s.length() - 1) + "     ");
			ans += record.get(s.substring(0, s.length() - 1));
			ans += map[s.charAt(s.length() - 2) - 'a'][s.charAt(s.length() - 1) - 'a'];
		} else {
			for (int i = 0; i < len - 1; i++) {
				ans += map[s.charAt(i) - 'a'][s.charAt(i + 1) - 'a'];
			}
		}
		System.out.println(s);
		record.put(s, ans);
		return ans;
	}

	static int calcDis(Pos a, Pos b) {
		return Math.abs(a.r - b.r) + Math.abs(a.c - b.c);
	}

	static class Pos {
		int r, c, idx;

		public Pos(int r, int c, int idx) {
			super();
			this.r = r;
			this.c = c;
			this.idx = idx;
		}

	}
}
