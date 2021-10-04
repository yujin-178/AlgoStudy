import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;



public class B9205_맥주마시면서걸어가기_3 {
	static Pos[] p;
	static boolean[] chk;
	static int storeNum;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("9205_input"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			storeNum = Integer.parseInt(st.nextToken());
			p = new Pos[storeNum + 2];

			for (int idx = 0; idx < storeNum + 2; idx++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());

				p[idx] = new Pos(x, y);

			}
//			Arrays.sort(p);
			chk = new boolean[storeNum + 2];
			if (bfs(p[0]))
				System.out.println("happy");
			else
				System.out.println("sad");
		}

	}

	static boolean bfs(Pos p1) {
		Queue<Pos> q = new LinkedList<Pos>();
		q.add(p1);
		chk[0] = true;
		while (!q.isEmpty()) {
			Pos tmp = q.poll();
			for (int i = 1; i < storeNum + 2; i++) {
				if (chk[i])
					continue;
				if (calcDis(tmp, p[i])) {
					if (i == storeNum + 1)
						return true;
					chk[i] = true;
					q.add(p[i]);
				}
			}
		}
		return false;
	}

	static boolean calcDis(Pos p1, Pos p2) {
		int dis = Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y);
		if (dis <= 1000)
			return true;
		else
			return false;
	}

	static class Pos  {
		int x;
		int y;

		public Pos(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		

	}
}
