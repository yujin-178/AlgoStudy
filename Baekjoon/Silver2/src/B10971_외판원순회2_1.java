import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
  * @FileName : B10971_외판원순회2_1.java
  * @Date : 2021. 9. 8. 
  * @작성자 : KimYuJin
  * @특이점 : 마지막 방문지점의 거리를 더하는걸 생각하지 못하고 좀 헤맴
  */
public class B10971_외판원순회2_1 {
	static int N, cost;
	static int[][] map;
	static boolean[] chk;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		cost = Integer.MAX_VALUE;
		for (int n = 0; n < N; n++) { // 시작지점 n번
			chk = new boolean[N];
			chk[n] = true; // 시작 지점 표시
			permu(n, 1, n, 0); // 순열로 체크
		}
		System.out.println(cost);
	}

	// exPos 이전 위치
	static void permu(int exPos, int r, int n, int sum) {
		if(sum > cost)
			return;
		if (r == N) { // 시작 r = 1, N-1번 수행하면 마지막 마을에서 출발지 돌아가는 상황
			if (map[exPos][n] != 0) // 마지막 위치에서 출발지까지 가는 길이 있다면
				cost = Math.min(sum + map[exPos][n], cost); // 해당 거리 더해서 최소 비교
			return;
		}

		for (int idx = 0; idx < N; idx++) { // idx는 다음 행선지
			if (chk[idx]) // 방문한 곳이면 pass
				continue;
			if (map[exPos][idx] == 0) // 길이 없다면 pass
				continue;
			chk[idx] = true; // 방문 예정
			permu(idx, r + 1, n, sum + map[exPos][idx]);// 거리 및 다음 행선지
			chk[idx] = false; // 되돌아와서 방문 취소
		}
	}
}
