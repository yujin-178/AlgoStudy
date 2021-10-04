

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 요리사 {
	static int T, N, scoreA, scoreB;
	static int[][] scoreMap;
	static int[] A;
	static int[] B;
	static boolean[] chk, chk2;
	static int[] select = new int[2];
	static int minDiff;

	public static void main(String[] args) throws IOException {
//		System.setIn(new FileInputStream("C:/CodingStudy/SWEA/역량테스트/요리사_input.txt"));
		System.setIn(new FileInputStream("요리사_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		T = Integer.parseInt(st.nextToken());
		for (int tc = 1; tc <= T; tc++) {
			minDiff = Integer.MAX_VALUE;
			N = Integer.parseInt(br.readLine());
			scoreMap = new int[N][N];
			chk = new boolean[N];

			A = new int[N / 2];
			B = new int[N / 2];
			for (int r = 0; r < N; r++) {
				st = new StringTokenizer(br.readLine());
				for (int c = 0; c < N; c++) {
					scoreMap[r][c] = Integer.parseInt(st.nextToken());
				}
			} // end scoreMap input

			comb(0, 0);
			System.out.println("#" + tc + " " + minDiff);

		} // end tc

	}// end main

	static void comb(int n, int r) {
		if (r == N / 2) {
			int cntA = 0, cntB = 0;
			for (int i = 0; i < N; i++) {
				if (chk[i])
					A[cntA++] = i;
				else
					B[cntB++] = i;
			} // end A와 B음식 재료 선정

			chk2 = new boolean[N / 2];
			scoreA = 0;
			permuA(0);

			chk2 = new boolean[N / 2];
			scoreB = 0;
			permuB(0);
			int tmp = Math.abs(scoreA - scoreB);
			if (minDiff > tmp)
				minDiff = tmp;
			return;
		}

		if (n == N)
			return;
		chk[n] = true;
		comb(n + 1, r + 1);
		chk[n] = false;
		comb(n + 1, r);
	}

	static void permuA(int n) {
		if (n == 2) {
			scoreA += scoreMap[select[0]][select[1]];
			return;
		}

		for (int i = 0; i < N / 2; i++) {
			if (chk2[i])
				continue;
			select[n] = A[i];
			chk2[i] = true;
			permuA(n + 1);
			chk2[i] = false;
		}
	}

	static void permuB(int n) {
		if (n == 2) {
			scoreB += scoreMap[select[0]][select[1]];
			return;
		}

		for (int i = 0; i < N / 2; i++) {
			if (chk2[i])
				continue;
			select[n] = B[i];
			chk2[i] = true;
			permuB(n + 1);
			chk2[i] = false;
		}
	}

}
