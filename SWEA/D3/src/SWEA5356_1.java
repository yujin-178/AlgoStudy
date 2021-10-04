import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class SWEA5356_1 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("C:/CodingStudy/SWEA/D3/5356_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			StringBuilder sb = new StringBuilder();
			sb.append("#").append(tc).append(" ");
			char[][] tmp = new char[5][];
			int maxLength = 0;
			for (int col = 0; col < 5; col++) {
				tmp[col] = br.readLine().toCharArray();
				if (tmp[col].length > maxLength)
					maxLength = tmp[col].length;
			}
			for (int row = 0; row < maxLength; row++) {
				for (int col = 0; col < 5; col++) {
					if (tmp[col].length > row)
					sb.append(tmp[col][row]);
				}
			}
			System.out.println(sb);

		}
		br.close();
	}
}
