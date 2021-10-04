import java.util.Arrays;
import java.util.Scanner;

public class S1493_수의새로운연산_1 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = 200;
		int[][] map = new int[N][N];
		int cnt = 1;

		for (int i = 1; i < N; i++) {
			for (int j = 1; j <= i; j++) {
				map[j][i - j + 1] = cnt++;
			}
		}

		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			int p = sc.nextInt();
			int q = sc.nextInt();
			int px = 0;
			int py = 0;
			int qx = 0;
			int qy = 0;
			for (int i = 1; i < N; i++) {
				for (int j = 1; j <= i; j++) {
					if (map[j][i - j + 1] == p) {
						px = j;
						py = i - j + 1;
					}
					if (map[j][i - j + 1] == q) {
						qx = j;
						qy = i - j + 1;
					}
				}
			}

			System.out.println("#" + tc + " " + (map[px + qx][py + qy]));
		}

//		for (int i = 1; i < N; i++) {
//			System.out.println(Arrays.toString(map[i]));
//		}
		sc.close();
	}
}
