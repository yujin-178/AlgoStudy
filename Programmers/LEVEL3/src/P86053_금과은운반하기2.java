import java.util.PriorityQueue;

public class P86053_금과은운반하기2 {
	public static void main(String[] args) {
		int a = 90;
		int b = 500;
		int[] g = { 70, 70, 0 };
		int[] s = { 0, 0, 500 };
		int[] w = { 100, 100, 2 };
		int[] t = { 4, 8, 1 };

		System.out.println(solution(a, b, g, s, w, t));
	}

	static public long solution(int a, int b, int[] g, int[] s, int[] w, int[] t) {
		return calcTime(a, b, g, s, w, t);
	}

	static long calcTime(int a, int b, int[] g, int[] s, int[] w, int[] t) {
		PriorityQueue<Truck> pq = new PriorityQueue<>();
		int len = w.length;
		for (int i = 0; i < len; i++) {
			pq.add(new Truck(i, t[i]));
		}
		long answer = 0;

		while (a > 0 && b > 0) {
			Truck truck = pq.poll();
			int idx = truck.i;
			double nowa = a / (double) (a + b);
			int tmpGold = (int) (w[idx] * nowa);
			int tmpSilver = w[idx] - tmpGold;

			if (g[idx] >= tmpGold && s[idx] >= tmpSilver) {
				a -= tmpGold;
				b -= tmpSilver;
				g[idx] -= tmpGold;
				s[idx] -= tmpSilver;
				answer = truck.t;
				pq.add(new Truck(idx, answer + (long) (2 * t[idx])));
			} else if (g[idx] >= tmpGold && s[idx] < tmpSilver) {
				tmpSilver = s[idx] >= 0 ? s[idx] : 0;
				tmpGold = w[idx] - tmpSilver >= g[idx] ? g[idx] : w[idx] - tmpSilver;
				a -= tmpGold;
				b -= tmpSilver;
				g[idx] -= tmpGold;
				s[idx] -= tmpSilver;
				answer = truck.t;
				pq.add(new Truck(idx, answer + (long) (2 * t[idx])));
			} else if (g[idx] < tmpGold && s[idx] >= tmpSilver) {
				tmpGold = g[idx] >= 0 ? g[idx] : 0;
				tmpSilver = w[idx] - tmpGold >= s[idx] ? s[idx] : w[idx] - tmpGold;
				a -= tmpGold;
				b -= tmpSilver;
				g[idx] -= tmpGold;
				s[idx] -= tmpSilver;
				answer = truck.t;
				pq.add(new Truck(idx, answer + (long) (2 * t[idx])));
			} else {
				tmpGold = g[idx] < 0 ? 0 : g[idx];
				tmpSilver = s[idx] < 0 ? 0 : s[idx];
				a -= tmpGold;
				b -= tmpSilver;
				g[idx] -= tmpGold;
				s[idx] -= tmpSilver;
				answer = truck.t;
			}

		}
		return answer;
	}

	static class Truck implements Comparable<Truck> {
		int i;
		long t;

		public Truck(int idx, long time) {
			this.i = idx;
			this.t = time;
		}

		@Override
		public int compareTo(Truck o) {
			return Long.compare(this.t, o.t);
		}
	}
}
