
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//public class Main  {
public class Test2493_1 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("C:/CodingStudy/Baekjoon/Gold5/2493_input"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int num = Integer.valueOf(br.readLine()) + 1;
		int[] tower = new int[num];
		int[] ans = new int[num - 1];
		int max = 0;
		int maxIdx = 0;
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i < num; i++) {
			tower[i] = Integer.valueOf(st.nextToken());
			if (tower[i] > max) {
				max = tower[i];
				maxIdx = i;
				ans[i - 1] = 0;
			} else if (tower[i] == max) {
				max = tower[i];
				ans[i - 1] = maxIdx;
				maxIdx = i;
			} else {
				int tmpMax = tower[i];
				for (int j = i - 1; j >= maxIdx; j--) {
					if (tmpMax <= tower[j]) {
						ans[i - 1] = j;
						break;
					}
				}
			}
		}
		for (int i = 0; i < ans.length; i++) {
			System.out.print(ans[i] + " ");
		}

	}
}