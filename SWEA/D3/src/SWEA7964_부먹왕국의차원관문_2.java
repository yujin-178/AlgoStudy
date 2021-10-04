import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//public class Solution {
public class SWEA7964_부먹왕국의차원관문_2 {
	static int[] city;
	static int N, D, ans;

	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("C:/CodingStudy/SWEA/D3/7964_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			D = Integer.parseInt(st.nextToken());
			city = new int[N + 2]; // 0,N+1 을 고려해서 크기 결정
			city[0] = 1; // 맨처음에는 반드시 존재함
			city[N + 1] = 1; // 맨 끝 에는 반드시 존재함
			st = new StringTokenizer(br.readLine()); // 도시 정보 한줄 읽어오기
			for (int idx = 1; idx <= N; idx++) {
				city[idx] = Integer.parseInt(st.nextToken()); // 1부터 N까지 도시 정보 입력
			}
			searchGate(0, 0);
			System.out.println("#" + tc + " " + ans);

		} // end tc
	}// end main

	static void searchGate(int start, int cnt) {
		if (start >= ((N + 1) - D)) {// start가 (N+1 -D)보다 크거나 같은 경우 종료
			ans = cnt; // 마지막 게이트가 커버중
			return; // 이거 안하고 왜 종료 안되나 헤매고 있었다.... 멍청...
		}

		boolean exist = false; // 존재 여부

		for (int idx = (start + D); idx > start; --idx) {
			if (city[idx] == 1) {
				exist = true; // 범위 내에 존재한다고 함
				searchGate(idx, cnt);// 시작하는 위치를 idx로 새로 시작함
			}
		}
		if (!exist) { // 범위 내에 차원관문이 없다면?
			city[start + D] = 1;// 범위 내에 없으므로 차원관문 생성
			searchGate(start + D, cnt + 1); // 범위 끝자락에 생성한거라서 현재 차원 관문의 가장 끝자락으로 시작
		}

	}

}
