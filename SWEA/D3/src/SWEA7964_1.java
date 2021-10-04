import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA7964_1 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("C:/CodingStudy/SWEA/D3/7964_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int D = Integer.parseInt(st.nextToken());
			int[] city = new int[N];
			for (int i = 0; i < D; i++) {
				++city[i];
				++city[city.length - i - 1];
			}
			st = new StringTokenizer(br.readLine());
			for (int idx = 0; idx < N; idx++) {
				if (st.nextToken() == "1") {
					region(idx, D, city);
				}
			}
			
			
		}
	}

	public static void region(int idx, int D, int[] city) {
		for (int i = idx - D; i <= idx + D - 1; i++) {
			if (i >= 0 && i < city.length)
				++city[i];
		}
	}
}
