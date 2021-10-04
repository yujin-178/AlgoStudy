import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

//public class Solution {
public class SWEA6485_1 {
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("C:/CodingStudy/SWEA/D3/6485_input.txt"));
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();
		
		for (int tc = 1; tc <= T; tc++) {
			int[] num = new int[5001];
			int N = sc.nextInt();
			for (int i = 1; i <= N; i++) {
				int A = sc.nextInt();
				int B = sc.nextInt();
				for(int idx = A;idx<=B;idx++) {
					++num[idx];
				}
			}
			int P = sc.nextInt();
			StringBuilder sb = new StringBuilder();
			sb.append("#").append(tc).append(" ");
			for(int i = 1; i<=P;i++) {
				sb.append(num[sc.nextInt()]).append(" ");
			}
			System.out.println(sb);
		}
		sc.close();

	}
}
