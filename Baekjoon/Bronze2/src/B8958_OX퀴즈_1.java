import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B8958_OX퀴즈_1 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int i = 0; i < T; i++) {
			String line = br.readLine();
			int cnt = 0;
			int sum = 0;
			for (int l = 0; l < line.length(); l++) {
				if (line.charAt(l) == 'O')
					sum += ++cnt;
				else
					cnt = 0;
			}
			System.out.println(sum);
		}

	}
}
