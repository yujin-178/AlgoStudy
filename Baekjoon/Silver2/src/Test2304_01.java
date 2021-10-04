

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//public class Main {
public class Test2304_01 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int num = Integer.valueOf(br.readLine());
		int maxL = 0;
		int maxH = 0;
		boolean[][] map = new boolean[1005][1005];
		for (int i = 0; i < num; i++) {
			st = new StringTokenizer(br.readLine());
			int l = Integer.valueOf(st.nextToken());
			int h = Integer.valueOf(st.nextToken());
			if (l > maxL)
				maxL = l;
			if (h > maxH)
				maxH = h;
			for (int j = 0; j < h; j++) {
				map[l][j] = true;
			}
		}
//		System.out.println(maxL);
//		System.out.println(maxH);
//		for (int i = 0; i < maxL + 1; i++) {
//			for (int j = 0; j < maxH; j++) {
////				System.out.print(map[i][j] + " ");
//			}
////			System.out.println();
//
//		}
		int sum = 0;
		for (int row = 0; row <= maxL + 2; row++) {
			int x = 0;
			while ((x <= maxL + 1) && (map[x][row] != true))
				x++;
			if (x >= maxL + 1)
				break;
			int y = maxL + 2;
			while ((y >= 0) && (map[y][row] != true))
				y--;
			System.out.println("y : "+y+" x : " +x + "\ty - x + 1 = "+ (y - x+1));
			sum += (++y - x );
//			System.out.println(sum);
			
		}
		System.out.println(sum);


	}
}
