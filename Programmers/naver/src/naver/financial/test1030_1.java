package naver.financial;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.StringTokenizer;

/**
 * @FileName : test1030_1.java
 * @Date : 2021. 10. 30.
 * @작성자 : KimYuJin
 * @특이점 : 구매한 고객들에게 10퍼 할일 쿠폰 지급 1. 물푸을 구매한 고객은 하루에 최대 1장 할인 쿠촌 2. 고객 한 명당 최대
 *      k까지의 할인 쿠폰을 ㅁ받는다. k =2, A 3일동안 물건 구매, 2wkd 고객 id는 문자열 배열 k가 변수
 */
public class test1030_1 {

	public static void main(String[] args) {
		String[] id_list = { "JAY", "JAY ELLE JAY MAY", "MAY ELLE MAY", "ELLE MAY", "ELLE ELLE ELLE", "MAY" };
		System.out.println(solution(id_list, 3));
	}

	static public int solution(String[] id_list, int k) {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		int len = id_list.length;
		for (int i = 0; i < len; i++) {
			StringTokenizer st = new StringTokenizer(id_list[i]);
			ArrayList<String> nameList = new ArrayList<>();
			while (st.hasMoreTokens()) {

				String tmp = st.nextToken();
				if (!nameList.contains(tmp)) {
					nameList.add(tmp);
					int coupon = map.get(tmp) == null ? 0 : map.get(tmp);
					map.put(tmp, k > coupon + 1 ? coupon + 1 : k);
				}
				//System.out.println(tmp + " : " + map.get(tmp));
			}
		}

		int answer = 0;
		Iterator<String> key = map.keySet().iterator();
		while (key.hasNext()) {
			answer += map.get(key.next());
		}

		return answer;
	}

}
