import java.util.Arrays;

/**
  * @FileName : P42584_주식가격_1.java
  * @Date : 2021. 10. 11. 
  * @작성자 : KimYuJin
  * @특이점 : 시간초과 난다.....
  */
public class P42584_주식가격_1 {
	public static void main(String[] args) {
		int[] prices = new int[10000];
		solution(prices);
	}

	static public int[] solution(int[] prices) {
		int size = prices.length;
		int[] answer = new int[size];
		int[] chk = new int[10001];
		Arrays.fill(chk, size - 1);
		answer[size - 1] = 0;
		
		for (int idx = size - 2; idx >= 0; idx--) {

			chk[prices[idx]] = idx;
			int minIdx = size - 1;
			for (int chkIdx = 1; chkIdx < prices[idx]; chkIdx++) {
				if (minIdx > chk[chkIdx])
					minIdx = chk[chkIdx];
			}

			answer[idx] = minIdx - idx;
		}

		return answer;
	}

}
