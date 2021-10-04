

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B9093_단어뒤집기_1 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int num = Integer.parseInt(br.readLine());

		for (int n = 0; n < num; n++) {

			String[] line = br.readLine().split(" ");
			StringBuilder sb;

			for (int i = 0; i < line.length; i++) {
				sb = new StringBuilder();
				line[i] = sb.append(line[i]).reverse().toString();
			}

			for (int i = 0; i < line.length; i++)
				System.out.print(line[i] + " ");
		}
	}

}
