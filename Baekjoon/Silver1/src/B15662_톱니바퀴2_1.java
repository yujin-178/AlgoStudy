import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B15662_톱니바퀴2_1 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int num = Integer.parseInt(st.nextToken());
		Magnetic[] m = new Magnetic[num];
		for (int i = 0; i < num; i++) {
			m[i] = new Magnetic(br.readLine().toCharArray());
		}

		st = new StringTokenizer(br.readLine());
		int K = Integer.parseInt(st.nextToken());
		for (int k = 0; k < K; k++) {
			st = new StringTokenizer(br.readLine());
			int kk = Integer.parseInt(st.nextToken());
			int dir = Integer.parseInt(st.nextToken());
			simul(kk - 1, dir, m);
		}

		System.out.println(calcScore(m));

	}

	static void simul(int idx, int rotDir, Magnetic[] m) {
		int l = m.length;
		int leftIdx = idx;
		int rightIdx = idx;
		// 자석 회전 가능 여부 체크
		boolean[] rotaChk = new boolean[l];
		int[] rotationDir = new int[l];
		int rDir = rotDir;
		rotationDir[idx] = rDir;
		rotaChk[idx] = true;
		while (--leftIdx >= 0) {
			int targetLeft = (m[leftIdx].head + 2) % 8;
			int targetRight = (m[leftIdx + 1].head + 6) % 8;
			if (m[leftIdx].mag[targetLeft] != m[leftIdx + 1].mag[targetRight]) {
				rotaChk[leftIdx] = true;
				rDir *= -1;
				rotationDir[leftIdx] = rDir;
			} else // 회전 불가능 하면 다음은 영향 없음
				break;
		}
		rDir = rotDir;
		while (++rightIdx < l) {
			int targetLeft = (m[rightIdx - 1].head + 2) % 8;
			int targetRight = (m[rightIdx].head + 6) % 8;
			if (m[rightIdx - 1].mag[targetLeft] != m[rightIdx].mag[targetRight]) {
				rotaChk[rightIdx] = true;
				rDir *= -1;
				rotationDir[rightIdx] = rDir;
			} else
				break;
		}

		for (int i = 0; i < l; i++) {
			if (rotaChk[i]) {
				m[i].head += rotationDir[i] == 1 ? 7 : 1;
				m[i].head %= 8;
			}
		}

	}

	static int calcScore(Magnetic[] m) {
		int score = 0;
		for (int i = 0; i < m.length; i++) {
			if (m[i].mag[m[i].head] == 1)
				score += 1;
		}
		return score;
	}

	static class Magnetic {
		int head;
		int[] mag;

		public Magnetic(char[] mag) {
			super();
			this.head = 0;
			this.mag = new int[8];
			for (int idx = 0; idx < 8; idx++) {
				this.mag[idx] = mag[idx] - '0';
			}
		}

	}
}
