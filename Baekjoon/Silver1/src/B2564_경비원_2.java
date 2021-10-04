import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @FileName : B2564_경비원_2.java
 * @Date : 2021. 8. 29.
 * @작성자 : KimYuJin
 * @특이점 : ㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋ 1에서 동근이가 모든 상점을 탐색하는
 *      거리의 최소를 구하는 문제로 착각하고 풀었다. 현재 동근이 위치에서 각각의 상점의 최소 거리 합을 구하는 문제였다.
 */
public class B2564_경비원_2 {
	static int R, C, num, ans;
	static boolean[] chk;

	public static void main(String[] args) throws NumberFormatException, IOException {
		// start input & initialize

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		C = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		num = Integer.parseInt(br.readLine());
		chk = new boolean[num];
		Store[] s = new Store[num];
		for (int n = 0; n < num; n++) {
			st = new StringTokenizer(br.readLine());
			s[n] = new Store(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		st = new StringTokenizer(br.readLine());
		Dong d = new Dong(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), num);
		// end input & initialize

		// calc distance store from dong
		int min = Integer.MAX_VALUE;
		int minIdx = 0;
		for (int i = 0; i < num; i++) {
			d.dis[i] = disStore(d.line, d.pos, s[i].line, s[i].pos);
		}

		System.out.println(d.disSum());

	}

	static int disStore(int sline, int spos, int dline, int dpos) {
		if (sline == dline) // 같은 라인
			return Math.abs(spos - dpos);
		else if ((sline == 2 && dline == 1) || (sline == 1 && dline == 2)) // 맞은편 위아래
			return Math.abs(R + Math.min(Math.abs(spos + dpos), Math.abs(2 * C - (spos + dpos))));
		else if ((sline == 3 && dline == 4) || (sline == 4 && dline == 3)) // 맞은편 좌우
			return Math.abs(C + Math.min(Math.abs(spos + dpos), Math.abs(2 * R - (spos + dpos))));
		else if ((sline == 1 && dline == 3) || (sline == 3 && dline == 1)) // 모서리 좌상
			return spos + dpos;
		else if ((sline == 1 && dline == 4) || (sline == 4 && dline == 1)) // 모서리 우상
			return sline == 1 ? C - spos + dpos : C - dpos + spos;
		else if ((sline == 2 && dline == 3) || (sline == 3 && dline == 2)) // 모서리 좌하
			return sline == 2 ? spos + R - dpos : dpos + R - spos;
		else if ((sline == 2 && dline == 4) || (sline == 4 && dline == 2)) // 모서리 우하
			return sline == 2 ? C - spos + R - dpos : C - dpos + R - spos;
		return -1;

	}

	static class Store {
		int line, pos;

		public Store(int line, int pos) {
			super();
			this.line = line;
			this.pos = pos;
		}
	}

	static class Dong {
		int line, pos;
		int[] dis;

		public Dong(int a, int b, int N) {
			super();
			this.line = a;
			this.pos = b;
			this.dis = new int[N];
		}

		public int disSum() {
			int sum = 0;
			for (int d : this.dis) {
				sum += d;
			}
			return sum;
		}

	}
}
