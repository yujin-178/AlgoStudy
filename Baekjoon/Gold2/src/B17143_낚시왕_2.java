import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B17143_낚시왕_2 {
	
	public static void main(String[] args) {
		String[] s = new String[5];
		s[0] = "0";
		s[1] = "1";
		s[2] = "2";
		s[3] = "3";
		s[4] = "4";
		for(int i = 0; i<5;i++) {
			System.out.println(s[i]);
		}
		System.out.println();
		s[2] = null;
		for(int i = 0; i<5;i++) {
			if(s[i] == null) continue;
			System.out.println(s[i]);
		}
	}

	
}
