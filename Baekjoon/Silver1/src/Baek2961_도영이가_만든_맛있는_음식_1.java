import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

//public class Main {
public class Baek2961_도영이가_만든_맛있는_음식_1 {
	static boolean[] chk;
	static int[] sour, bitter;
	static int N;
	static ArrayList<Integer> resultSour = new ArrayList<Integer>();
	static ArrayList<Integer> resultBitter = new ArrayList<Integer>();
	static int minScore = Integer.MAX_VALUE;

	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("C:/CodingStudy/Baekjoon/Silver1/2961_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		sour = new int[N];
		bitter = new int[N];
		chk = new boolean[N];

		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			sour[i] = Integer.parseInt(st.nextToken());
			bitter[i] = Integer.parseInt(st.nextToken());
		}

		cooking(0, 1, 0);

//		for (int i = 0; i < resultSour.size(); i++) {
//			System.out.println(resultSour.get(i) + " " + resultBitter.get(i));
//		}
////		
//		System.out.println(Arrays.toString(sour));
		

		for (int i = 0; i <  resultSour.size()-1; i++) {
	
				int tmp = (resultSour.get(i) - resultBitter.get(i));
				if (tmp < 0)
					tmp *= -1;
				if (minScore > tmp)
					minScore = tmp;

		}
		System.out.println(minScore);

	}

	static void cooking(int idx, int s, int b) {
		if (idx == N) {
			resultBitter.add(b);
			resultSour.add(s);
			return;
		}

		cooking(idx + 1, s * sour[idx], b + bitter[idx]);
		cooking(idx + 1, s, b);
	}
}
