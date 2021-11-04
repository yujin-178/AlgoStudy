package naver.financial;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 * @FileName : test1030_3.java
 * @Date : 2021. 10. 30.
 * @작성자 : KimYuJin
 * @특이점 : 1. 두 수험자가 푼 문제 수가 같다., 단 푼 문제수가 5 미만인 경우에는 제외한다. 2. 두 수험자가 푼 문제의 번호가
 *      모두 같다. 3. 두 수험자가 푼 문제의 점수가 모두 같다. 입력 양식 {{"수험번호 문제번호 점수"},{"수험번호 문제번호
 *      점수"},...}
 *      
 *      이거 이름만 map사용하고 
 *      이름, 번호 , 점수의 2차원 배열에 점수를 저장하는 방식으로 풀면 되지 않을까?????
 *      
 * 
 */
public class test1030_3 {
	public static void main(String[] args) {
		String[] logs = { "1901 1 100", "1901 2 100", "1901 4 100", "1901 7 100", "1901 8 100", "1902 2 100",
				"1902 1 100", "1902 7 100", "1902 4 100", "1902 8 100", "1903 8 100", "1903 7 100", "1903 4 100",
				"1903 2 100", "1903 1 100", "1101 1 95", "1101 2 100", "1101 4 100", "1101 7 100", "1101 9 100",
				"1102 1 95", "1102 2 100", "1102 4 100", "1102 7 100", "1102 9 100" };
		System.out.println(Arrays.toString(solution(logs)));
	}

	static public String[] solution(String[] logs) {
		int len = logs.length;
		HashMap<String, Integer> studentName = new HashMap<String, Integer>();
		int stuCnt = 0;
		ArrayList<Student> studentScore = new ArrayList<Student>();
		for (int i = 0; i < len; i++) {
			StringTokenizer st = new StringTokenizer(logs[i]);
			String name = st.nextToken();
			if (studentName.get(name) == null) {
				studentName.put(name, stuCnt++);
				studentScore.add(new Student(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
			} else {
				studentScore.get(studentName.get(name)).getLogs().put(Integer.parseInt(st.nextToken()),
						Integer.parseInt(st.nextToken()));
			}
		}

		for (int i = 0; i < stuCnt; i++) {
			if(studentScore.get(i).getLogs().size() > )
		}

		String[] answer = {};
		return answer;
	}

	static class Student {
		private static HashMap<Integer, Integer> logs = null;

		public Student(int a, int b) {
			logs = new HashMap<Integer, Integer>();
			logs.put(a, b);
		}

		public HashMap<Integer, Integer> getLogs() {
			return logs;
		}
	}
}
