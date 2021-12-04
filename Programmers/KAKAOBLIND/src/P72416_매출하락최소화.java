import java.io.IOException;
import java.util.Arrays;

public class P72416_매출하락최소화 {
	static boolean[] chk;
	static int ans, cnt, length;

	public static void main(String[] args) throws IOException {
		int[] sales = { 5, 6, 5, 3, 4 };
		int[][] links = { { 2, 3 }, { 1, 4 }, { 2, 5 }, { 1, 2 } };
		int ans = solution(sales, links);
		System.out.println(ans);
	}

	static public int solution(int[] sales, int[][] links) {
		int len = sales.length;
		Info[] info = new Info[len + 1];
		chk = new boolean[len + 1];
		length = len;
		info[0] = new Info(0, Integer.MAX_VALUE);
		for (int i = 0; i < len; i++) {
			info[i + 1] = new Info(i + 1, sales[i]);
		}

		len = links.length;
		for (int i = 0; i < len; i++) {
			info[links[i][1]].h = links[i][0];
			info[links[i][0]].head = true;
		}

		Arrays.sort(info);

//		for (int i = 0; i < len + 1; i++) {
//			System.out.println(info[i]);
//		}
		int result = 0;
		for (int i = 0; i < len + 1; i++) {
			if (!chk[info[i].h] || !chk[info[i].i]) {
				result += info[i].w;
				if (!chk[info[i].h])
					cnt++;
				if (!chk[info[i].i])
					cnt++;
				chk[info[i].h] = true;
				chk[info[i].i] = true;
			}
			if (cnt == length)
				break;
		}

		return result;
	}

	static class Info implements Comparable<Info> {
		int i, h, w;
		boolean head;

		public Info(int i, int w) {
			super();
			this.i = i;
			this.h = 1;
			this.w = w;
			this.head = false;
		}

		@Override
		public int compareTo(Info o) {
			return Integer.compare(this.w, o.w);
		}

		@Override
		public String toString() {
			return "Info [i=" + i + ", h=" + h + ", w=" + w + ", head=" + head + "]";
		}

	}
}
