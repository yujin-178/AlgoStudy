import java.util.Scanner;

public class B2567_색종이2_1 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int[][] map = new int[100][100];
		int[] dr = { -1, 1, 0, 0 };
		int[] dc = { 0, 0, -1, 1 };

		int num = sc.nextInt();
		int[][] pos = new int[num][2];
		for (int n = 0; n < num; n++) {
			pos[n][0] = sc.nextInt();
			pos[n][1] = sc.nextInt();
		}

		for (int n = 0; n < num; n++) {
			for (int i = pos[n][0]; i < pos[n][0] + 10; i++) {
				for (int j = pos[n][1]; j < pos[n][1] + 10; j++) {
					map[i][j] = 1;
				}
			}
		}
		int cnt = 0;
		for (int r = 0; r < 100; r++) {
			for (int c = 0; c < 100; c++) {
				if (map[r][c] == 1) {

					for (int d = 0; d < 4; d++) {
						int newR = r + dr[d];
						int newC = c + dc[d];
						if (map[r][c] == 1 && (r == 0 || r == 99 || c == 0 || c == 99)
								&& (newR < 0 || newR >= 100 || newC < 0 || newC >= 100))
							++cnt;
						if (newR >= 0 && newR < 100 && newC >= 0 && newC < 100 && map[newR][newC] == 0)
							++cnt;
					}
				}
			}
		}
		System.out.println(cnt);
		sc.close();
	}
}
