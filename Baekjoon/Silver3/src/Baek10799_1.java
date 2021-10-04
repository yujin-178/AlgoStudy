import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//public class Main {
public class Baek10799_1 {
	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String str = br.readLine();
		char[] c = new char[str.length()];
		c[0] = str.charAt(0);
		int laserNum = 0;
		int plateNum = 0;
		int cnt = 0;
		int sum = 0;
		if (c[0] == '(') {
			++cnt;
			plateNum++;
		}
		for (int idx = 1; idx < str.length(); idx++) {

			c[idx] = str.charAt(idx);
			if ((c[idx - 1] == '(') && (c[idx] == ')')) {
				++laserNum;
				sum += (plateNum - 1);
			}
			if (c[idx] == '(') {
				++plateNum;
				++cnt;
			} else
				--plateNum;
		}
		sum += (cnt - laserNum);
		System.out.println(sum);

	}
}