import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

public class P72411_메뉴리뉴얼_1 {

	static HashMap<String, Integer> map = new HashMap<>();
	static boolean[] chk;
	static String order;
	static int tmplen;
	static int[] cntMax;

	public static void main(String[] args) {
		String[] input = { "XYZ", "XWY", "WXA" };
		int[] cour = { 2, 3, 4 };
		System.out.println(Arrays.toString(solution(input, cour)));
	}

	static public String[] solution(String[] orders, int[] course) {

		int olen = orders.length;
		int clen = course.length;

		cntMax = new int[course.length];
		for (int i = 0; i < olen; i++) {
			for (int j = 0; j < clen; j++) {
				chk = new boolean[26];
				order = orders[i];
				tmplen = orders[i].length();
				comb(0, 0, course[j], j);
			}
		}
		LinkedList<String> anstmp = new LinkedList<String>();
		Iterator<String> keys = map.keySet().iterator();

		while (keys.hasNext()) {
			String tmp = keys.next();
			for (int i = 0; i < clen; i++) {
				if (cntMax[i] != 1 && tmp.length() == course[i] && map.get(tmp) == cntMax[i]) {
					anstmp.add(tmp);
				}
			}
		}

		Collections.sort(anstmp);
		String[] answer = new String[anstmp.size()];
		int maxAns = anstmp.size();
		for (int i = 0; i < maxAns; i++) {
			answer[i] = anstmp.poll();
		}
		return answer;
	}

	static void comb(int start, int cnt, int course, int j) {
		if (cnt == course) {

			String tmp = "";
			for (int i = 0; i < 26; i++) {
				if (chk[i])
					tmp += "" + (char) ('A' + i);
			}

			int tmpInt = map.get(tmp) == null ? 0 : map.get(tmp);
			map.put(tmp, tmpInt + 1);
			cntMax[j] = Math.max(map.get(tmp), cntMax[j]);
			return;
		}

		for (int idx = start; idx < tmplen; idx++) {
			chk[order.charAt(idx) - 'A'] = true;
			comb(idx + 1, cnt + 1, course, j);
			chk[order.charAt(idx) - 'A'] = false;
		}
	}
}
