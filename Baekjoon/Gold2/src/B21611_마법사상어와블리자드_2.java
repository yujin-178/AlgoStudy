import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B21611_마법사상어와블리자드_2 {
	static int N, M, maxBead, score;
	static int[][] map;
	static int[][] com;
	static int[] dr = { 1, 0, -1, 0 };
	static int[] dc = { 0, 1, 0, -1 };
	static int[][] changeIdx;
	static int[] bead;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][N];
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		com = new int[M][2];
		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			switch (Integer.parseInt(st.nextToken())) {
			case 1:
				com[m][0] = 2;
				break;
			case 2:
				com[m][0] = 0;
				break;
			case 3:
				com[m][0] = 3;
				break;
			case 4:
				com[m][0] = 1;
				break;
			}
			com[m][1] = Integer.parseInt(st.nextToken());
		}

		setMatrix();

		for (int m = 0; m < M; m++) {
			simul(m);
		}
		System.out.println(score);
	}

	static void simul(int m) {
		// 블리자드
		int sr = N / 2;
		int sc = N / 2;
		for (int a = 0; a < com[m][1]; a++) {
			sr += dr[com[m][0]];
			sc += dc[com[m][0]];
			if (sc < 0 || sc >= N || sr < 0 || sr >= N || map[sr][sc] == 0)
				break;
			int idx = -1;
			for (int i = 0; i < maxBead; i++) {
				if (changeIdx[i][0] == sr && changeIdx[i][1] == sc)
					idx = i;
			}
			if (idx != -1)
				bead[idx] = 0;

		}
		
		setBead();
//		showMap(m);

		// 구슬 파괴
		int exScore = -1;
		while (score != exScore) {
			exScore = score;
			int cnt = 1;
			int exBead = bead[0];
			for (int i = 1; i < N * N - 1; i++) {
				if (exBead == bead[i])
					cnt++;
				else {
					if (cnt >= 4) {
						int c = 0;
						for (c = 0; c < cnt; c++) {
							score += bead[i - c - 1];
							bead[i - c - 1] = 0;
						}
					}
					cnt = 1;
					exBead = bead[i];
				}
				
			}

			setBead();
		}

//		showMap(m);
		bead = increaseBead();
//		showMap(m);
	}

	static void setBead() {
		int[] tmpBead = new int[N * N - 1];
		int idx = 0;
		for (int i = 0; i < N * N - 1; i++) {
			if (bead[i] != 0)
				tmpBead[idx++] = bead[i];
		}
		maxBead = idx;
		for (int i = 0; i < N * N - 1; i++) {
			if (i < maxBead)
				bead[i] = tmpBead[i];
			else
				bead[i] = 0;
		}
	}

	static int[] increaseBead() {
		int[] newBead = new int[N * N - 1];
		int exBead = bead[0];
		int cnt = 1;
		int idx = 0;
		int i = 0;
		while (bead[i++] != 0) {
			if (exBead == bead[i])
				cnt++;
			else {
				newBead[idx++] = cnt;
				if (idx >= N * N - 1)
					break;

				newBead[idx++] = exBead;
				if (idx >= N * N - 1)
					break;

				exBead = bead[i];
				cnt = 1;
			}
		}

		maxBead = idx;
		return newBead;
	}

	static void setMatrix() {
		changeIdx = new int[N * N - 1][2];
		bead = new int[N * N - 1];
		int r = N / 2;
		int c = N / 2 - 1;
		int d = 0;
		int cnt = 0;
		while (!(r == 0 && c == -1)) {

			if (map[r][c] != 0)
				bead[cnt] = map[r][c];
			changeIdx[cnt][0] = r;
			changeIdx[cnt++][1] = c;

			if ((r == N - 1 - c) || (r < N / 2 && r - 1 == c) || (r > N / 2 && r == c))
				d = (++d % 4);
			r += dr[d];
			c += dc[d];

		}
		maxBead = cnt;

	}

	static void showMap(int m) {
		System.out.println();
		System.out.println(m + 1 + "번째");
		int[][] map = new int[N][N];
		for (int i = 0; i < N * N - 1; i++) {
			int r = changeIdx[i][0];
			int c = changeIdx[i][1];
			map[r][c] = bead[i];
		}
		for (int r = 0; r < N; r++) {

			for (int c = 0; c < N; c++) {
				System.out.print(map[r][c] + " ");
			}
			System.out.println();
		}
	}

}
