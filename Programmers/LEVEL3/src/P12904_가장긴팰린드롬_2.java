import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @FileName : P12904_가장긴팰린드롬_2.java
 * @Date : 2021. 10. 09.
 * @작성자 : KimYuJin
 * @특이점 : 완전 탐색을 이용
 * 큰 경우부터 시뮬레이션을 수행하였으며 답이 나오는 순간 탐색 종료
 * 추가적으로 일치하지 않는 경우 탐색을 중단해서 시간을 단축했다.
 */
public class P12904_가장긴팰린드롬_2 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		System.out.println(solution(s));
	}

	static public int solution(String s) {
		char[] input = s.toCharArray();
		int len = input.length;
		for (int l = len; l > 0; l--) { // 긴 길이부터
			for (int idx = 0; idx < len - l + 1; idx++) { // 탐색을 시작하는 위치
				int cnt = 0; // 몇개가 일치 하는지
				for (int nIdx = 0; nIdx < l; nIdx++) { // 비교하고자 하는 길이만큼 비교
					if (input[idx + nIdx] == input[idx + l - 1 - nIdx])  // 앞과 뒤부터 차례대로 비교
						cnt++; // 일치하면 개수 증가
					else // 일치하지 않으면 더 이상 비교 불필요
						break;
				}
				if (cnt == l) // 탐색한 길이와 일치하는 길이가 같다면
					return cnt; // 개수 리턴 하면서 종료
					// 길이가 긴 경우부터 시작하기 때문에 일치하면 바로 답이다.
			}
		}
		return 0;
	}
}
