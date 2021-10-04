import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek15686_치킨_배달_1 {
	static int N, M, Hcnt, Ccnt, MinDis = Integer.MAX_VALUE;
	static Chicken[] cc;
	static Home[] hh;
	static boolean[] chkCC;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("C:/CodingStudy/Baekjoon/Gold5/15686_input"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		hh = new Home[2 * N];
		cc = new Chicken[N * N];
		chkCC = new boolean[N * N];
		Hcnt = 0;
		Ccnt = 0;
		for (int r = 1; r < N + 1; r++) { // map save
			st = new StringTokenizer(br.readLine());
			for (int c = 1; c < N + 1; c++) {
				switch (st.nextToken()) {
				case "1":
					hh[Hcnt++] = new Home(r, c);
					break;
				case "2":
					cc[Ccnt++] = new Chicken(r, c);
				}
			}
		} // end map save


		CombDis(0, 0);
		System.out.println(MinDis);

	}

	static void CombDis(int cnt, int start) {
		if (cnt == M) {
			int sum = 0;
			for (int Hidx = 0; Hidx < Hcnt; Hidx++) {
				int minHome = Integer.MAX_VALUE;
				for (int Cidx = 0; Cidx < Ccnt; Cidx++) {
					if (!chkCC[Cidx])
						continue;
					int tmp = Math.abs(cc[Cidx].c - hh[Hidx].c) + Math.abs(cc[Cidx].r - hh[Hidx].r);
					if (minHome > tmp)
						minHome = tmp;
				}
				sum += minHome;
			}
			if (sum < MinDis)
				MinDis = sum;

			return;
		}

		for (int i = start; i < Ccnt; i++) {
			chkCC[i] = true;
			CombDis(cnt + 1, i + 1);
			chkCC[i] = false;
		}
	}

	static class Home {
		int r;
		int c;

		public Home(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}

	}

	static class Chicken {
		int r;
		int c;

		public Chicken(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}

	}
}
