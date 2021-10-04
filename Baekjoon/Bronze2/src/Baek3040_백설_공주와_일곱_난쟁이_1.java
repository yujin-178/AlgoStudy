import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

//public class Main {
public class Baek3040_백설_공주와_일곱_난쟁이_1 {
	static int N = 9, R = 7, SUM_ANS = 100;
	static int[] num = new int[N];
	static boolean[] chk = new boolean[N];
	static int[] ans = new int[R];

	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("C:/CodingStudy/Baekjoon/Bronze2/3040_input.txt"));
		Scanner sc = new Scanner(System.in);

		for (int i = 0; i < 9; i++) {
			num[i] = sc.nextInt();
		}
		findComb(0, 0, 0);

	}

	static void findComb(int idx, int start, int sum) {
		if (idx == R) {
			if(sum  == SUM_ANS) {
				for(int i = 0; i<ans.length;i++) {
					System.out.println(ans[i]);
				}
			}
			return;
		}

		for (int i = start; i < N; i++) {
			ans[idx] = num[i];

			findComb(idx + 1, i + 1, sum + ans[idx]);

		}

	}
}
