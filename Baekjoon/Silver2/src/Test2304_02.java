

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//public class Main {
public class Test2304_02 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.valueOf(br.readLine());
		int maxL = 0;
		int maxH = 0;
		boolean[][] map = new boolean[1001][1001];
		for (int n = 1; n <= N; n++) {
			st = new StringTokenizer(br.readLine());
			int Ln = Integer.valueOf(st.nextToken());
			int Hn = Integer.valueOf(st.nextToken());
			if (Ln > maxL)
				maxL = Ln;
			if (Hn > maxH)
				maxH = Hn;
			for (int h = 1; h <= Hn; h++) {
				map[Ln][h] = true;
			}
		}
		int sum = 0;
		for (int row = 1; row < 1001; row++) {
			int x = 0;
			while ((x <= 1000) && (map[x][row] != true))
				x++;
			if (x > 1000)
				break;
			int y = maxL + 2;
			while ((y >= 0) && (map[y][row] != true))
				y--;
			System.out.println("y : " + y + " x : " + x + "\ty - x + 1 = " + (y - x + 1));
			sum += (y - x + 1);
		}
		System.out.println(sum);

	}
}
