import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B17471_게리맨더링_3 {
	static int N, min;
	static boolean[] chk;
	static boolean[][] map;
	static int[] people;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());

		chk = new boolean[N];
		people = new int[N];
		map = new boolean[N][N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			people[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			for (int j = 0; j < num; j++) {
				map[i][Integer.parseInt(st.nextToken()) - 1] = true;
			}
		}

		// 입력 종료

		min = Integer.MAX_VALUE;
		comb(0, 0);
		if (min == Integer.MAX_VALUE)
			System.out.println("-1");
		else
			System.out.println(min);
	}

	static void comb(int idx, int cnt) {
		if (idx == N) {
			if (cnt == 0 || cnt == N)
				return;
			int trueSum = 0;
			int falseSum = 0;
			if (chkBoolean(cnt, true) && chkBoolean(N - cnt, false)) {
				for (int i = 0; i < N; i++) {
					if (chk[i])
						trueSum += people[i];
					else
						falseSum += people[i];
				}
				min = Math.min(min, Math.abs(trueSum - falseSum));
			}
			return;
		}

		chk[idx] = true;
		comb(idx + 1, cnt + 1);
		chk[idx] = false;
		comb(idx + 1, cnt);

	}

	static boolean chkBoolean(int cnt, boolean side) {
		boolean[] mapChk = new boolean[N];
		Queue<Integer> q = new LinkedList<Integer>();
		for (int i = 0; i < N; i++) {
			if (chk[i] == side) {
				q.add(i);
				mapChk[i] = true;
				break;
			}
		}

		int trueCnt = 1;
		if (cnt == 1)
			return true;

		while (!q.isEmpty()) {
			int now = q.poll();
			for (int c = 0; c < N; c++) {
				if (chk[c] == side && map[now][c] && !mapChk[c]) {
					q.add(c);
					mapChk[c] = true;
					trueCnt++;
					if (trueCnt == cnt) {
						return true;
					}
				}
			}
		}

		return false;
	}

}
