import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class B1697_숨바꼭질_1 {
	static boolean[] chk;
	static int N, K;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		K = sc.nextInt();
		System.out.println(bfs(N)); //리턴해준 값 출력
	}

	static int bfs(int N) {
		Queue<Integer> q = new LinkedList<>();
		q.add(N);
		chk = new boolean[100001]; // 1차원 맵 생성
		chk[N] = true;
		int cnt = 0;
		int nextStep = 1; // 처음에는 시작위치 1개라
		if (N == K)
			return cnt;
		while (!q.isEmpty()) {
			for (int idx = 0; idx < nextStep; idx++) { // 해당 스텝에서 탐색해야 하는 위치를 전부 탐색한다.
				int tmp = q.poll(); 
				int[] next = { tmp - 1, tmp + 1, tmp * 2 }; // -1,+1, *2 칸 이동
				for (int i = 0; i < 3; i++) {
					if (chkOut(next[i]) && !chk[next[i]]) { // 이동 가능 범위를 벗어나지 않았다면
						if (next[i] == K) // 만약 동생의 위치와 동일하다면 끝
							return cnt + 1; // cnt는 1만큼 증가한 값을 리턴
						q.add(next[i]); // 다음에 탐색할 위치를
						chk[next[i]] = true;
					}

				}
			}
			nextStep = q.size();
			cnt++;
		}

		return Integer.MAX_VALUE;
	}

	static boolean chkOut(int i) { // 맵 벗어나는지 확인
		if (i < 0 || i > 100000)
			return false;
		return true;
	}
}
