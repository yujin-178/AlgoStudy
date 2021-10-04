import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

//public class Solution {
public class SWEA9229_D3_3_상우님 {
	static boolean[] select;
	static int[] weight;
	static int N;
	static int M;
	static int sum_weight;

	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("C:/CodingStudy/SWEA/D3/9229_input.txt"));
		Scanner sc = new Scanner(System.in);
		int tc = sc.nextInt();
		for (int t = 1; t <= tc; t++) {
			sum_weight = 0;
			N = sc.nextInt();
			weight = new int[N];
			select = new boolean[N];
			M = sc.nextInt();
			for (int i = 0; i < N; i++) {
				weight[i] = sc.nextInt();
			}
			snack(0, 0, 0);
			if (sum_weight > 0) {
				System.out.println("#" + t + " " + sum_weight);
			} else {
				System.out.println("#" + t + " " + "-1");
			}

		}
		sc.close();

	}

	public static void snack(int target, int cur_weight, int cnt) {
		if (cur_weight > M) // 무게 초과시 그냥 리턴
			return;
		if (target == N) { // N번째 과자는 없다. 리턴
			return;
		}
		if (cnt == 2) { // 2개를 집어들면 더 이상 진행하지 않는다.
			if (sum_weight < cur_weight) // 이때 가장 무거운걸 찾는다.
				sum_weight = cur_weight;
			return;
		}
		snack(target + 1, cur_weight, cnt);
		snack(target + 1, cur_weight + weight[target], cnt + 1);

	}

}
