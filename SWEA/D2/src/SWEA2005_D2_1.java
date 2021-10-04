import java.util.Scanner;

//public class Solution {
public class SWEA2005_D2_1 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();

		for (int tc = 1; tc <= T; tc++) {
			int num = sc.nextInt();
			int[][] line = new int[num + 1][];
			line[1] =new int [] {1};
//			System.out.println(Arrays.toString(line[1]));
			for (int i = 2; i <= num; i++) {
				int[] tmp = new int[i];
				for (int j = 0; j < i; j++) {
					if (j == 0 || j == (i-1))
						tmp[j] = 1;
					else
						tmp[j] = line[i-1][j] + line[i-1][j-1]; 
				}
				line[i] = tmp;
//				System.out.println(Arrays.toString(line[i]));
			}
			System.out.println("#"+tc);
			for(int i = 1;i<=num;i++) {
				for(int j = 0; j<i;j++) {
					System.out.print(line[i][j]+" ");
				}
				System.out.println();
			}
		}
		sc.close();
	}

}
