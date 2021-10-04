

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//public class Main {
public class Test1244_1 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int bulbNum = Integer.valueOf(br.readLine());
		char[] bulb = new char[bulbNum];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < bulbNum; i++) {
			bulb[i] = st.nextToken().charAt(0);
		}
		int humunNum = Integer.valueOf(br.readLine());
		for (int i = 0; i < humunNum; i++) {
			st = new StringTokenizer(br.readLine());
			int gen = Integer.valueOf((st.nextToken()));
			int num = Integer.valueOf((st.nextToken()));
			if (gen == 1) {
				changeBulb(num, bulb);
			} else {
				changeBulb(num-1, bulb, 0);
			}

		}
		for (int i = 1; i <= bulb.length; i++) {
			if (i % 20 == 0) {
				System.out.print(bulb[i-1]);
				System.out.println();
			}
			else {
			System.out.print(bulb[i-1] + " ");
			}
			
		}
	}

	static void reverseBulb(int idx, char[] bulb) {
		if (bulb[idx] == '0')
			bulb[idx] = '1';
		else
			bulb[idx] = '0';
	}

	static void changeBulb(int idx, char[] bulb) {
		int i = 1;
		while ((idx * i - 1) < bulb.length) {
			reverseBulb(idx * i - 1, bulb);
			i++;
		}
	}

	static void changeBulb(int idx, char[] bulb, int move) {
		if (((idx - move) >= 0) && ((idx + move) < bulb.length) && (bulb[idx + move] == bulb[idx - move])) {
			if (move == 0)
				reverseBulb(idx, bulb);
			else {
				reverseBulb(idx + move, bulb);
				reverseBulb(idx - move, bulb);
			}
			changeBulb(idx, bulb, move + 1);
		} else
			return;
	}

}
