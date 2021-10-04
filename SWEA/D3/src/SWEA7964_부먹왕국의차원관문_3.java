
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//public class Solution {
public class SWEA7964_부먹왕국의차원관문_3 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("C:/CodingStudy/SWEA/D3/7964_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for (int tc = 1; tc <= T; tc++) {
			int ans = 0;
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int D = Integer.parseInt(st.nextToken());
			int[] city = new int[N + 2]; // 0,N+1 을 고려해서 크기 결정
			city[0] = 1; // 맨처음에는 반드시 존재함
			city[N + 1] = 1; // 맨 끝 에는 반드시 존재함
			st = new StringTokenizer(br.readLine()); // 도시 정보 한줄 읽어오기
			for (int idx = 1; idx <= N; idx++) {
				city[idx] = Integer.parseInt(st.nextToken()); // 1부터 N까지 도시 정보 입력
			}
			int maxRange = 0;
			for (int i = 0; i < N + 1; i++) {

				if (maxRange == i && city[i] == 0) {// 범위 끝자락에 게이트가 없다면
					city[i] = 1; // 게이트 생성
					maxRange = i + D; // 게이트 범위 갱신
					++ans; // 건설 횟수 증가
				}
				if (city[i] == 1) // 1이면 범위 증가
					maxRange = i + D; // 범위 갱신
				if (maxRange >= N + 1) // 범위가 끝까지인 경우
					break;

			}

			System.out.println("#" + tc + " " + ans);

		} // end tc
	}// end main

}
