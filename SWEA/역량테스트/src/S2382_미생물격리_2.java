import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class S2382_미생물격리_2 {
	static int N, M, K; // N : 지도 크기, M : 격리 시간, K : 미생물 개수
	static int[] dr = { 0, -1, 1, 0, 0 };
	static int[] dc = { 0, 0, 0, -1, 1 };
	static HashMap<String, PriorityQueue<Microbe>> map;
	static PriorityQueue<Microbe> colMicro;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("2382_input"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int T = Integer.parseInt(st.nextToken());

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			colMicro = new PriorityQueue<>();

			for (int k = 0; k < K; k++) {
				st = new StringTokenizer(br.readLine());
				int r = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				int n = Integer.parseInt(st.nextToken());
				int d = Integer.parseInt(st.nextToken());
				colMicro.add(new Microbe(k, r, c, d, n));
			}

			System.out.println("#" + tc + " " + cntMicrobe());
		}
	}

	static int cntMicrobe() { // 시뮬레이션
		int time = 0; // 격리시간 기록
		while (!colMicro.isEmpty() && time <= M) {
//			System.out.println(time +" : "+colMicro.size());
			map = new HashMap<>();
			while (!colMicro.isEmpty()) {
				Microbe tmp = colMicro.poll();
				int dir = tmp.d;
				tmp.c += dc[dir];
				tmp.r += dr[dir];
//				System.out.println(tmp.toString());
//				System.out.println(tmp.r + ", " + tmp.c + " :  " + tmp.n);
				chkEdge(tmp);
				if (!map.containsKey(tmp.toString()))
					map.put(tmp.toString(), new PriorityQueue<Microbe>());
				map.get(tmp.toString()).add(tmp);
			}

			Iterator<String> keys = map.keySet().iterator();

			while (keys.hasNext()) {
				String key = keys.next();
//				System.out.println(key + " : " + map.get(key).size());
				if (map.get(key).size() == 1) {
					colMicro.add(map.get(key).poll());
				} else {
					Microbe tmp = map.get(key).poll();
					while (map.get(key).size() != 0) {
						Microbe tmp2 = map.get(key).poll();
						tmp.n += tmp2.n;
					}
					colMicro.add(tmp);
				}
//				System.out.println(colMicro.peek());

			}
//			for(int i = 0; i<colMicro.size();i++ ) {
//				System.out.println(colMicro.poll());
//			}
			time++;

		}
		int ans = 0;
		while (!colMicro.isEmpty()) {
			ans += colMicro.poll().n;
		}

		return ans;
	}

	static void chkEdge(Microbe m) {
		if (m.r == 0 || m.r == N - 1 || m.c == 0 || m.c == N - 1) {
			m.n /= 2;
			m.reverse();
		}
	}

	static class Microbe implements Comparable<Microbe> {
		int i, r, c, d, n;

		public Microbe(int i, int r, int c, int d, int n) {
			super();
			this.i = i; // idx
			this.r = r; // r
			this.c = c;
			this.d = d; // 방향
			this.n = n; // 개수
		}

		public void reverse() {
			if (this.d == 1)
				this.d = 2;
			else if (this.d == 2)
				this.d = 1;
			else if (this.d == 3)
				this.d = 4;
			else if (this.d == 4)
				this.d = 3;
		}

		@Override
		public int compareTo(Microbe o) {
			return Integer.compare(this.n, o.n);
		}

		@Override
		public String toString() {
			return r + " " + c;
		}
	}
}
