import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B17143_낚시왕_4 {
	static int R, C, M;
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };
	static int[][] map;
	static Shark[] shark;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		shark = new Shark[M + 1];
		map = new int[R][C];
		for (int m = 1; m <= M; m++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
			switch (d) {
			case 1:
				shark[m] = new Shark(r, c, s % ((R - 1) * 2), 0, z);
				break;
			case 2:
				shark[m] = new Shark(r, c, s % ((R - 1) * 2), 2, z);
				break;
			case 3:
				shark[m] = new Shark(r, c, s % ((C - 1) * 2), 1, z);
				break;
			case 4:
				shark[m] = new Shark(r, c, s % ((C - 1) * 2), 3, z);
				break;
			}
			map[r][c] = m;
		}

		System.out.println(simul());

	}

	static void moveShark() {
		map = new int[R][C];
		for (int m = 1; m <= M; m++) {
			if (shark[m] == null)
				continue;

			if (shark[m].d == 0 || shark[m].d == 2) {
				int dis = shark[m].s;
				int dir = shark[m].d;
				int r = shark[m].r;
				while (dis != 0) {
					if (r == 0 && dir == 0)
						dir = 2;
					if (r == R - 1 && dir == 2)
						dir = 0;
					r += dr[dir];
					--dis;
				}
				shark[m].r = r;
				shark[m].d = dir;
				int c = shark[m].c;
				if (map[r][c] == 0) {
					map[r][c] = m;
				} else {
					if (shark[m].z > shark[map[r][c]].z) {
						shark[map[r][c]] = null;
						map[r][c] = m;
					} else {
						shark[m] = null;
					}
				}

			} else {
				int dis = shark[m].s;
				int dir = shark[m].d;
				int c = shark[m].c;
				while (dis != 0) {
					if (c == 0 && dir == 3)
						dir = 1;
					if (c == C - 1 && dir == 1)
						dir = 3;
					c += dc[dir];
					--dis;
				}
				shark[m].c = c;
				shark[m].d = dir;
				int r = shark[m].r;

				if (map[r][c] == 0) {
					map[r][c] = m;
				} else {
					if (shark[m].z > shark[map[r][c]].z) {
						shark[map[r][c]] = null;
						map[r][c] = m;
					} else {
						shark[m] = null;
					}
				}
			}
		}
	}

	static int simul() {
		int ans = 0;

		for (int idx = 0; idx < C; idx++) { // 1 & 2 낚시왕이 오른쪽으로 한칸 이동해서 상어를 잡는다.
			for (int search = 0; search < R; search++) {
				if (map[search][idx] != 0) {
					ans += shark[map[search][idx]].z;
					shark[map[search][idx]] = null;
					map[search][idx] = 0;
					break;
				}
			}
			moveShark();
		}
		return ans;

	}

	static class Shark {
		int r, c, s, d, z;

		public Shark(int r, int c, int s, int d, int z) {
			super();
			this.r = r;
			this.c = c;
			this.s = s;
			this.d = d;
			this.z = z;
		}

	}
}
