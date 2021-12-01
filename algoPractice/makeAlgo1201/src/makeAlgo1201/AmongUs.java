package makeAlgo1201;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class AmongUs {
	static int N, M, cNum, iNum, maxTime;
	static Crew[] cMember;
	static Imposter[] iMember;
	static int[][] map;
	static int[] dr = { 0, -1, 1, 0, 0 };
	static int[] dc = { 0, 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input_sample"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		st = new StringTokenizer(br.readLine());
		cNum = Integer.parseInt(st.nextToken());
		iNum = Integer.parseInt(st.nextToken());
		maxTime = Integer.parseInt(st.nextToken());
		cMember = new Crew[cNum];
		iMember = new Imposter[iNum];

		for (int idx = 0; idx < cNum; idx++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int[] com = new int[maxTime];
			st = new StringTokenizer(br.readLine());
			for (int t = 0; t < maxTime; t++) {
				com[t] = Integer.parseInt(st.nextToken());
			}
			cMember[idx] = new Crew(x, y, com);
		}

		for (int idx = 0; idx < iNum; idx++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int[] com = new int[maxTime];
			st = new StringTokenizer(br.readLine());
			for (int t = 0; t < maxTime; t++) {
				com[t] = Integer.parseInt(st.nextToken());
			}
			iMember[idx] = new Imposter(x, y, com);
		}

		// input 종료
		// 1. 크루원 맵, 임포스터 맵 만들고
		// 2. 맵에 인원수를 기록
		// 3. 같은 방에 있는 경우에는 이벤트에 돌입
		// 4. 이벤트가 끝나고 그 방에 있는 인원들 숙청완료
		System.out.println("승리 " + (simulation() ? "크루원" : "임포스터"));

	}

	static boolean simulation() {
		int crewNum = cNum;
		int impoNum = iNum;

		for (int i = 0; i < maxTime; i++) { // 시뮬레이션 시작
			int[][][] map = new int[2][N][M];

			for (int cIdx = 0; cIdx < cNum; cIdx++) {
				if (cMember[cIdx] == null)
					continue;
				cMember[cIdx].x = cMember[cIdx].x + dr[cMember[cIdx].c[i]] < 0
						|| cMember[cIdx].x + dr[cMember[cIdx].c[i]] >= N ? cMember[cIdx].x
								: cMember[cIdx].x + dr[cMember[cIdx].c[i]];
				cMember[cIdx].y = cMember[cIdx].y + dc[cMember[cIdx].c[i]] < 0
						|| cMember[cIdx].y + dc[cMember[cIdx].c[i]] >= M ? cMember[cIdx].y
								: cMember[cIdx].y + dc[cMember[cIdx].c[i]];
//				System.out.println(cMember[cIdx].x + ", " + cMember[cIdx].y);
				map[0][cMember[cIdx].x][cMember[cIdx].y]++;
			} // 크루원 배치 완료

			for (int iIdx = 0; iIdx < iNum; iIdx++) {
				if (iMember[iIdx] == null)
					continue;
				iMember[iIdx].x = iMember[iIdx].x + dr[iMember[iIdx].c[i]] < 0
						|| iMember[iIdx].x + dr[iMember[iIdx].c[i]] >= N ? iMember[iIdx].x
								: iMember[iIdx].x + dr[iMember[iIdx].c[i]];
				iMember[iIdx].y = iMember[iIdx].y + dc[iMember[iIdx].c[i]] < 0
						|| iMember[iIdx].y + dc[iMember[iIdx].c[i]] >= M ? iMember[iIdx].y
								: iMember[iIdx].y + dc[iMember[iIdx].c[i]];

				map[1][iMember[iIdx].x][iMember[iIdx].y]++;
			} // 임포스터 배치 완료

			System.out.println(i + "번째 위치");
			for (int row = 0; row < N; row++) {
				for (int col1 = 0; col1 < M; col1++) {
					System.out.print(map[0][row][col1] + " ");
				}
				System.out.print("    ");
				for (int col2 = 0; col2 < M; col2++) {
					System.out.print(map[1][row][col2] + " ");
				}
				System.out.println();
			}
			System.out.println();

			// 맵 탐색하면서 사건 일어나는지 확인
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < M; c++) {
					if (map[0][r][c] > 0 && map[1][r][c] > 0) {// 사건 발생 // 임포스터, 크루원이 같은 방에 1명 이상씩 있는 경우
						if (map[0][r][c] > map[1][r][c]) {// 크루원이 더 많은 경우
							int ccNum = map[1][r][c];
							int iiNum = map[1][r][c];

							for (int cIdx = 0; cIdx < cNum; cIdx++) {
								if (cMember[cIdx] != null && cMember[cIdx].x == r && cMember[cIdx].y == c) {
									cMember[cIdx] = null;
									--crewNum;
									if (--ccNum == 0) //
										break;
								}
							}

							for (int iIdx = 0; iIdx < iNum; iIdx++) {
								if (iMember[iIdx] != null && iMember[iIdx].x == r && iMember[iIdx].y == c) {
									iMember[iIdx] = null;
									--impoNum;
									if (--iiNum == 0)
										break;
								}
							}

						} else { // 임포스터가 크루원보다 많거나 같은 경우
							for (int cIdx = 0; cIdx < cNum; cIdx++) {
								if (cMember[cIdx] != null && cMember[cIdx].x == r && cMember[cIdx].y == c) {
									cMember[cIdx] = null;
									--crewNum;
								}
							}
						}
					} // 사건 종료
					if (crewNum <= impoNum)
						return false; // 임포스터 승리
				}
			} // 맵 탐색 종료
			System.out.println(i + "번째 종료 후 " + "크루원 : " + crewNum + ", 임포스터 : " + impoNum);

		}
		return true; // 크루원 승리
	}

	static class Imposter {
		int x, y;
		int[] c;

		public Imposter(int x, int y, int[] c) {
			this.x = x;
			this.y = y;
			this.c = c;
		}
	}

	static class Crew {
		int x, y;
		int[] c;

		public Crew(int x, int y, int[] c) {
			this.x = x;
			this.y = y;
			this.c = c;
		}
	}
}
