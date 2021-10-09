import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.StringTokenizer;

/**
  * @FileName : B2531_회전초밥_2.java
  * @Date : 2021. 10. 8. 
  * @작성자 : KimYuJin
  * @특이점 : 이전과 다르게 회전 행렬 부분은 %연산을 이용해서 구현함
  * 이 문제에서는 선택한 k개 중에서 중복된 음식이 있음을 파악해야함
  * 새로운 음식이 추가 되는 경우 sushi가 0->1 
  * 기존에 선택한 음식이 완전히 사라지는 경우 1->0
  * 위의 두 가지 경우에 cnt(선택한 음식의 종류)를 업데이트 시키면 된다.
  */
public class B2531_회전초밥_2 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int[] dish = new int[N];
		int[] sushi = new int[d + 1];
		for (int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine());
			dish[n] = Integer.parseInt(st.nextToken());
		}

		int cnt = 1;
		sushi[c]++;

		int max = 0;
		for (int idx = 0; idx < N + k - 1; idx++) {
			if (idx >= k) {
				sushi[dish[(idx - k) % N]]--;
				if (sushi[dish[(idx - k) % N]] == 0)
					cnt--;
			}
			if (sushi[dish[idx % N]] == 0)
				cnt++;
			sushi[dish[idx % N]]++;
			max = Math.max(max, cnt);
		}
		System.out.println(max);
	}
}