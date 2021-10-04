//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.StringTokenizer;
//
//public class B2116_주사위 {
//	public static void main(String[] args) throws IOException {
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		StringTokenizer st = new StringTokenizer(br.readLine());
//
//		int num = Integer.parseInt(st.nextToken());
//		Dice[] d = new Dice[num];
//		for (int i = 0; i < num; i++) {
//			st = new StringTokenizer(br.readLine());
//			d[i] = new Dice(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
//					Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
//					Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
//		}
//
//		int sum = 0;
//		int ans = 0;
//		int up = 0;
//		int downNum = 0;
//		for (int side = 0; side < 6; side++) {
//			downNum = d[0].num[side];
//			up = d[0].upSide(downNum);
//			sum = d[0].findMax(up);
//			System.out.println(side + "----------------");
//			System.out.println(d[0].findMax(side));
//			for (int i = 1; i < num; i++) {
//				System.out.println(d[i].findMax(up));
//				downNum = d[i].
//				sum += d[i].findMax(up);
//				up = d[i].upSide(up);
//			}
//			ans = Math.max(ans, sum);
//		}
//		System.out.println(ans);
//	}
//
//	static class Dice {
//		int[] num;
//
//		public Dice(int a, int b, int c, int d, int e, int f) {
//			super();
//
//			this.num = new int[] { a, b, c, d, e, f };
//		}
//
//		public int downSide(int downSideNum) {
//			int downSide = 0;
//			for (int i = 0; i < 6; i++) {
//				if (downSideNum == num[i])
//					downSide = i;
//			}
//			return downSide;
//		}
//
//		public int upSide(int downSide) {
//
//			switch (downSide) {
//			case 0:
//				return 5;
//			case 1:
//				return 3;
//			case 2:
//				return 4;
//			case 3:
//				return 1;
//			case 4:
//				return 2;
//			case 5:
//				return 0;
//			}
//			return -1;
//		}
//
//		public int findMax(int downSide) {
//			int use1 = downSide;
//			int use2 = upSide(downSide);
//			int max = 0;
//			for (int i = 0; i < 6; i++) {
//				if (i == use1 || i == use2)
//					continue;
//				max = Math.max(max, this.num[i]);
//			}
//			return max;
//		}
//
//	}
//}
