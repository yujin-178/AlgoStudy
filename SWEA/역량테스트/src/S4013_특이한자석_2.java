import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class S4013_특이한자석_2 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int T = Integer.parseInt(st.nextToken());

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			int K = Integer.parseInt(st.nextToken());

			Magnetic[] m = new Magnetic[4];
			for (int i = 0; i < 4; i++) {
				st = new StringTokenizer(br.readLine());
				int[] tmp = new int[8];
				for (int j = 0; j < 8; j++) {
					tmp[j] = Integer.parseInt(st.nextToken());
				}
				m[i] = new Magnetic(tmp);
			}

			for (int k = 0; k < K; k++) {
				st = new StringTokenizer(br.readLine());
				int kk = Integer.parseInt(st.nextToken());
				int num = Integer.parseInt(st.nextToken());
				simul(kk - 1, num, m);
			}

			System.out.println("#" + tc + " " + calcScore(m));

		}
	}

	static void simul(int idx, int rotDir, Magnetic[] m) {

		int leftIdx = idx;
		int rightIdx = idx;
		// 자석 회전 가능 여부 체크
		boolean[] rotaChk = new boolean[4];
		int[] rotationDir = new int[4];
		int rDir = rotDir;
		rotationDir[idx] = rDir;
		rotaChk[idx] = true;
		while (--leftIdx >= 0) {
			if (m[leftIdx].mag.get(2) != m[leftIdx + 1].mag.get(6)) {
				rotaChk[leftIdx] = true;
				rDir *= -1;
				rotationDir[leftIdx] = rDir;
			} else // 회전 불가능 하면 다음은 영향 없음
				break;
		}
		rDir = rotDir;
		while (++rightIdx < 4) {
			if (m[rightIdx - 1].mag.get(2) != m[rightIdx].mag.get(6)) {
				rotaChk[rightIdx] = true;
				rDir *= -1;
				rotationDir[rightIdx] = rDir;
			} else
				break;
		}

		for (int i = 0; i < 4; i++) {
			if (rotaChk[i]) {
				if (rotationDir[i] == 1) {
					int tmp = m[i].mag.getLast();
					m[i].mag.pollLast();
					m[i].mag.offerFirst(tmp);
				} else {
					int tmp = m[i].mag.getFirst();
					m[i].mag.pollFirst();
					m[i].mag.offerLast(tmp);
				}

			}
		}

	}

	static int calcScore(Magnetic[] m) {
		int score = 0;
		for (int i = 0; i < 4; i++) {
			if (m[i].mag.getFirst() == 1)
				score += 1 << i;
		}
		return score;
	}

	static class Magnetic {
		LinkedList<Integer> mag;

		public Magnetic(int[] mag) {
			super();

			this.mag = new LinkedList<>();
			for (int idx = 0; idx < 8; idx++) {
				this.mag.add(mag[idx]);
			}
		}

	}
}
