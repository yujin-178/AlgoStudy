import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S1247_최적경로_1 {

	static int[][] disCus;
	static boolean[] chk;
	static int N, ans;
	static Company cc;
	static Home hh;
	static Custom[] cus;
	static int[] set;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("1247_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			chk = new boolean[N];
			cus = new Custom[N];
			set = new int[N];
			ans = Integer.MAX_VALUE;
			st = new StringTokenizer(br.readLine());
			cc = new Company(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), N);
			hh = new Home(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), N);
			for (int n = 0; n < N; n++) {
				cus[n] = new Custom(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), n, N);
				cc.setDis(Math.abs(cc.c - cus[n].c) + Math.abs(cc.r - cus[n].r), n);
				hh.setDis(Math.abs(hh.c - cus[n].c) + Math.abs(hh.r - cus[n].r), n);
			}
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (i == j)
						continue;
					int absDis = Math.abs(cus[i].r - cus[j].r) + Math.abs(cus[i].c - cus[j].c);
					cus[i].setDis(absDis, j);
					cus[j].setDis(absDis, i);
				}
			}

			Permutation(0);
			System.out.println("#" + tc + " " + ans);


		}

	}
	static void Permutation(int n) {
		if (n == N) {
			int tmp = cc.dis[set[0]] + hh.dis[set[N - 1]]; // 회사에서 첫번째 고객 + 마지막 고객에서 집 거리
			for (int i = 0; i < N - 1; i++) {
				tmp += cus[set[i]].dis[set[i + 1]]; // 첫번째 고객부터 마지막 고객까지 거리 더 하기
			}
			ans = Math.min(ans, tmp); // 값 비교
			return;
		}

		for (int i = 0; i < N; i++) {
			if (chk[i])
				continue;
			chk[i] = true;
			set[n] = i;
			Permutation(n + 1);
			chk[i] = false;
		}
	}

	static class Company {
		int r;
		int c;
		int[] dis;

		public void setDis(int dis, int i) {
			this.dis[i] = dis;
		}

		public Company(int r, int c, int N) {
			super();
			this.r = r;
			this.c = c;
			this.dis = new int[N];
		}

	}

	static class Home {
		int r;
		int c;
		int[] dis;

		public void setDis(int dis, int i) {
			this.dis[i] = dis;
		}

		public Home(int r, int c, int N) {
			super();
			this.r = r;
			this.c = c;
			this.dis = new int[N];
		}
	}

	static class Custom {
		int r;
		int c;
		int number;
		int[] dis;

		public void setDis(int dis, int i) {
			this.dis[i] = dis;
		}

		public Custom(int r, int c, int number, int N) {
			super();
			this.r = r;
			this.c = c;
			this.number = number;
			this.dis = new int[N];

		}
	}
}
