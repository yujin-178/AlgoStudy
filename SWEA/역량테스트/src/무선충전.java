

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 무선충전 {
	static int T, M, BCnum; // TC 개수, 이동 횟수, BC 개수
	static int[] dr = { 0, -1, 0, 1, 0 }; // 이동명령 행 이동
	static int[] dc = { 0, 0, 1, 0, -1 }; // 이동명령 열 이동
	static USER A; // 사용자 A
	static USER B; // 사용자 B
	static BCInfo[] bcInfo; // BC 정보 저장

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("무선충전_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		T = Integer.parseInt(st.nextToken()); // T회 테케

		for (int tc = 1; tc <= T; tc++) { // tc
			st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken()); // M번 이동 명령
			BCnum = Integer.parseInt(st.nextToken()); // BCnum개의 BC
			A = new USER(0, 0, M); // 사용자 A생성
			B = new USER(9, 9, M); // 사용자 B생성
			bcInfo = new BCInfo[BCnum]; // BC개수만큼 배열 생성

			st = new StringTokenizer(br.readLine());
			for (int m = 1; m < M + 1; m++) { // A의 이동명령 입력
				A.m[m] = Integer.parseInt(st.nextToken());
			}

			st = new StringTokenizer(br.readLine());
			for (int m = 1; m < M + 1; m++) { // B의 이동명령 입력
				B.m[m] = Integer.parseInt(st.nextToken());
			}

			for (int bc = 0; bc < BCnum; bc++) { // BC 정보 입력
				st = new StringTokenizer(br.readLine());
				bcInfo[bc] = new BCInfo(Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken())-1,
						Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}
			Arrays.sort(bcInfo); // BC의 P 크기로 내림차순

//			for(BCInfo a : bcInfo) {
//				System.out.println(a.P);
//			} // 여기까지 10분 쉬는 시간

			simul();

			System.out.println("#" + tc + " " + (A.sum + B.sum));

		} // end tc
	}

	static void simul() {
		int idx = 0;
		do {
			// 위치 갱신
			A.r = A.r + dr[A.m[idx]];
			A.c = A.c + dc[A.m[idx]];
			B.r = B.r + dr[B.m[idx]];
			B.c = B.c + dc[B.m[idx]];

			// BC 선택하기
			int APidx = -1; // 선택한다면 새롭게 갱신
			int BPidx = -1; // 선택하지 못한다면 쭉 -1
			boolean Aselect = false;
			boolean Bselect = false;
			for (int bc = 0; bc < BCnum; bc++) {
				if (Aselect && Bselect) // 둘 다 선택하면 종료
					break;

				int disA = Math.abs(A.r - bcInfo[bc].r) + Math.abs(A.c - bcInfo[bc].c);
				int disB = Math.abs(B.r - bcInfo[bc].r) + Math.abs(B.c - bcInfo[bc].c);

				if (!Aselect && disA <= bcInfo[bc].distance) { // A 선택
					APidx = bc;
					Aselect = true;
				}

				if (!Bselect && disB <= bcInfo[bc].distance) { // B 선택
					BPidx = bc;
					Bselect = true;
				}
			}

			// APidx == BPidx 이면 BC 재선택
			if (APidx != -1 && APidx == BPidx) {
				Aselect = false;
				Bselect = false;
				int start = APidx; // 다음꺼부터 탐색
				APidx = -1;
				BPidx = -1;
				for (int bc = start + 1; bc < BCnum; bc++) {
					if (Aselect && Bselect) // 둘 다 선택하면 종료
						break;

					int disA = Math.abs(A.r - bcInfo[bc].r) + Math.abs(A.c - bcInfo[bc].c);
					int disB = Math.abs(B.r - bcInfo[bc].r) + Math.abs(B.c - bcInfo[bc].c);

					if (!Aselect && disA <= bcInfo[bc].distance) { // A 선택
						APidx = bc;
						Aselect = true;
					}

					if (!Bselect && disB <= bcInfo[bc].distance) { // B 선택
						BPidx = bc;
						Bselect = true;
					}
				}
				if (APidx == -1 && BPidx == -1) { // 더 이상 없는 경우
					APidx = start;
					BPidx = start;
				} else if (APidx == -1 && BPidx != -1) { // BPidx 만 있는 경우
					APidx = start;
				} else if (APidx != -1 && BPidx == -1) { // APidx 만 있는 경우
					BPidx = start;
				} else if (APidx == BPidx) { // 다음 BC가 같은 경우
					APidx = start;
				} else if (APidx < BPidx) { // A가 더 큰 충전인 경우
					BPidx = start;
				} else if (APidx > BPidx) { // B가 더 큰 충전인 경우
					APidx = start;
				} else { // 여긴 오면 경우의 수를 더 생각해야한다.
					System.out.println("여기오면 경우의 수를 더 생각해야함");
				}

			}

			// 충전하기
			if (APidx == BPidx && APidx == -1) { // 둘 다 -1인 경우
				A.sum += 0;
				B.sum += 0;
			} else if (APidx == BPidx) { // 둘이 -1이 아니고 같은 경우
				A.sum += bcInfo[APidx].P / 2;
				B.sum += bcInfo[APidx].P / 2;
			} else { // 둘이 다른 경우
				if (APidx == -1) // APidx -1 이면 충전 불가능
					A.sum += 0;
				else
					A.sum += bcInfo[APidx].P; // 충전 가능
		
				if (BPidx == -1) // BPidx -1 이면 충전 불가능 
					B.sum += 0;
				else
					B.sum += bcInfo[BPidx].P; // 충전 가능
			}
//			System.out.println(A.sum + " "+B.sum);
		} while (idx++ != M);
	}

	static class USER { // 사용자
		int r, c;
		int[] m;
		int sum;

		public USER(int r, int c, int M) {
			super();
			this.r = r;
			this.c = c;
			this.m = new int[M + 1];
			m[0] = 0;
			this.sum = 0;
		}
	}

	static class BCInfo implements Comparable<BCInfo> {
		int r, c, distance, P; // 행렬 위치, 충전범위, 충전파워

		public BCInfo(int c, int r, int dis, int P) {
			super();
			this.r = r;
			this.c = c;
			this.distance = dis;
			this.P = P;
		}

		@Override
		public int compareTo(BCInfo o) {
			// TODO Auto-generated method stub
			return o.P - this.P; // 내림차순으로 뒤집어 놓음
		}

	}
}
