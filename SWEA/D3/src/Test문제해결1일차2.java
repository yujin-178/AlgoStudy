
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//class Solution {
public class Test문제해결1일차2 {
	public static void main(String[] args) throws NumberFormatException, IOException, Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		for (int tc = 1; tc <= 10; tc++) {
			int cnt = Integer.valueOf(br.readLine());
			int highH = 0;
			int lowH = 0;
			int[] box = new int[100];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 100; i++) {
				box[Integer.valueOf(st.nextToken())-1]++;
			}
			while (cnt-- != 0) {
				highH = findHigh(box);
				lowH = findLow(box);
				box[highH]--;
				box[highH-1]++;
				box[lowH]--;
				box[lowH+1]++;
			}
			System.out.println("#" + tc + " " + (findHigh(box) - findLow(box)));
		}
	}
	public static int findHigh(int[] box) {
		for (int i = 99; i >= 0; i--) {
			if (box[i] != 0)
				return i;
		}
		return 100;
	}
	public static int findLow(int[] box) {
		for (int i = 0; i < 100; i++) {
			if (box[i] != 0)
				return i;
		}
		return 100;
	}
}
