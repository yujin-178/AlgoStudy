import java.util.Arrays;
import java.util.Stack;

/**
 * @FileName : P42584_주식가격_2.java
 * @Date : 2021. 10. 11.
 * @작성자 : KimYuJin
 * @특이점 : stack이 비어 있는 경우 처리를 안해서 조금 헤맴 ㅋㅋㅋ
 */
public class P42584_주식가격_2 {
	public static void main(String[] args) {
		int[] prices = { 1, 2, 3, 2, 3 };
		System.out.println(Arrays.toString(solution(prices)));
	}

	static public int[] solution(int[] prices) {
		int size = prices.length;
		int[] answer = new int[size];
		Stack<Integer> s = new Stack<Integer>();
		
		for (int i = 0; i < size; i++) {
			while (!s.isEmpty() && prices[s.peek()] > prices[i]) {
				int tmp = s.pop();
				answer[tmp] = i - tmp;
			}
			s.push(i);
		}

		while (!s.isEmpty()) {
			int tmp = s.pop();
			answer[tmp] = size - 1 - tmp;
		}

		return answer;
	}

}
