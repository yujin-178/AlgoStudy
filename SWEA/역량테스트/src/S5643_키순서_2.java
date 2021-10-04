import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class S5643_키순서_2 {
	static int[][] map;
	static int N, M;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			map = new int[N + 1][N + 1];
			PriorityQueue<ShortEdge> qShort = new PriorityQueue<>();
			PriorityQueue<TallEdge> qTall = new PriorityQueue<>();
			for (int m = 0; m < M; m++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				qShort.add(new ShortEdge(a,b));
				qTall.add(new TallEdge(a,b));
			}
			
			for(int i = 0; i<M;i++) {
				
			}

			int cnt = 0;
			for (int r = 1; r <= N; r++) {
				int cnt2 = 0;
				for (int c = 1; c <= N; c++) {
					if (map[r][c] != 0)
						cnt2++;
				}
				if (cnt2 == N - 1)
					cnt++;
			}
			System.out.println("#" + tc + " " + cnt);
		}
	}

	static class ShortEdge implements Comparable<ShortEdge> {
		int s;
		int l;

		public ShortEdge(int s, int l) {
			this.s = s;
			this.l = l;
		}

		@Override
		public int compareTo(ShortEdge o) {

			return this.s - o.s;
		}
	}

	static class TallEdge implements Comparable<TallEdge> {
		int s;
		int l;

		public TallEdge(int s, int l) {
			this.s = s;
			this.l = l;
		}

		@Override
		public int compareTo(TallEdge o) {

			return this.l - o.l;
		}

	}
}
