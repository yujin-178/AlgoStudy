import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B17413_단어뒤집기2_1 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = br.readLine();
		StringBuilder ans = new StringBuilder();
		int startBarcket = 0, startWhite = 0, endBracket = 0, endWhite = 0;

		while (line.length() != 0) {
			StringBuilder tmp = new StringBuilder();
			startBarcket = line.indexOf('<');
			startWhite = line.indexOf(' ');
			if ((startBarcket  ==  -1 || startBarcket > 0) && (startWhite == -1||startWhite > 0)) {// 단어가 시작하는 경우
				endBracket = line.indexOf('>');
				endBracket = endBracket != -1 ? endBracket:line.length();
				endWhite = line.indexOf(' ');
				endWhite = endWhite != -1 ? endWhite:line.length();
				int end = Math.min( endBracket ,endWhite );
				ans.append(tmp.append(line.substring(0, end)).reverse().toString() + " ");
				line = line.substring(end + 1);
				System.out.println("1 : " + line);
			} else if (startBarcket == 0) { // 태크가 시작하는 경우
				endBracket = line.indexOf('>');
				ans.append(line.substring(startBarcket, endBracket + 1));
				line = line.substring(endBracket);
				System.out.println("2 : " + line);
			} else if (startWhite == 0) { // 단어나 태그가 빠지고 단어가 시작하는 경우에는 공백이 맨 앞에 있다.
				line = line.substring(1);
				endWhite = line.indexOf(' ');
				ans.append(tmp.append(line.substring(0, endWhite + 1)).reverse().toString()+" ");
				line = line.substring(endWhite);
				System.out.println("3 : " + line);
			}
		}
		System.out.println(ans.toString());

	}
}
