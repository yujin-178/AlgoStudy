import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @FileName : B9205_맥주마시면서걸어가기_1.java
 * @Date : 2021. 9. 16.
 * @작성자 : KimYuJin
 * @특이점 : 1. 맥수는 최대 20개만 소유할 수 있다. 2. 맥주 한 병을 마시면 50미터를 이동할 수 있다. 3. 편의점에 들리면
 *      맥주를 최대 20개 획득할 수 있다. 3-1. 다만 편의점을 나오면 다시 맥주를 마셔야 한다. 입력 1. 테스트 케이스 2.
 *      편의점의 개수 n 3. n+2 줄에는 집, 편의점, 페스티벌 좌표 x,y 결국 1000m 씩 움직이는거 아닌가??? 맨해튼
 *      거리로....???? 왜 틀리지?????
 *      아 무조건 편의점을 들려야 하는게 아니다.
 *      중간에 페스티벌 갈 수도 있고
 *      편의점은 오히려 페스티벌에서 더 멀리 있을 수도 있다.
 */
public class B9205_맥주마시면서걸어가기_1 {
	static Pos[] p;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("9205_input"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			int storeNum = Integer.parseInt(st.nextToken());
			p = new Pos[storeNum + 2];

			for (int idx = 0; idx < storeNum + 2; idx++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());

				p[idx] = new Pos(x, y);

			}
			
			Arrays.sort(p);
			boolean ans = false;
			for (int i = 0; i < storeNum + 1; i++) {
				ans = calcDis(p[i], p[storeNum + 1]);
				if (ans)
					break;

				ans = calcDis(p[i], p[i + 1]);
				if (!ans)
					break;
			}

			if (ans)
				System.out.println("happy");
			else
				System.out.println("sad");
		}

	}

	static boolean calcDis(Pos p1, Pos p2) {
		int dis = Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y);
		if (dis <= 1000)
			return true;
		else
			return false;
	}

	static class Pos implements Comparable<Pos> {
		int x;
		int y;

		public Pos(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

		@Override
		public int compareTo(Pos o) {
			// TODO Auto-generated method stub

			return Integer.compare(Math.abs(p[0].x - this.x) + Math.abs(p[0].y + this.y),
					Math.abs(p[0].x - o.x) + Math.abs(p[0].y + o.y));
		}

	}
}
