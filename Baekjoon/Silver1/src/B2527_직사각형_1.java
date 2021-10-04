import java.util.Scanner;

public class B2527_직사각형_1 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Square[] s = new Square[2];
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 2; j++) {
				s[j] = new Square(sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.nextInt());
			}

			if (s[0].x2 < s[1].x1 || s[0].y2 < s[1].y1 || s[0].x1 > s[1].x2 || s[0].y1 > s[1].y2)
				System.out.println("d");
			else if ((s[0].x2 == s[1].x1 && s[0].y2 == s[1].y1) || (s[0].x1 == s[1].x2 && s[0].y2 == s[1].y1)
					|| (s[0].x1 == s[1].x2 && s[0].y1 == s[1].y2) || (s[0].x2 == s[1].x1 && s[0].y1 == s[1].y2))
				System.out.println("c");
			else if ((s[1].x2 == s[0].x1 && s[0].y2 > s[1].y1 && s[0].y1 < s[1].y2)
					|| (s[1].x1 == s[0].x2 && s[0].y2 > s[1].y1 && s[0].y1 < s[1].y2)
					|| (s[1].y1 == s[0].y2 && s[0].x2 > s[1].x1 && s[0].x1 < s[1].x2)
					|| (s[1].y2 == s[0].y1 && s[0].x2 > s[1].x1 && s[0].x1 < s[1].x2))
				System.out.println("b");
			else
				System.out.println("a");

		}

		sc.close();
	}

	static class Square {
		int x1, x2, y1, y2;

		public Square(int x1, int y1, int x2, int y2) {
			super();
			this.x1 = x1;
			this.x2 = x2;
			this.y1 = y1;
			this.y2 = y2;
		}

	}
}
