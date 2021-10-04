import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//public class Main {
public class Test1159_01 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int nameNum = Integer.valueOf(br.readLine());
		int[] cntAlpha = new int[(int) ('z' - 'a' + 1)];
		char tmp =' ';
		int cntCnt = 0;
		for(int i = 0;i<nameNum;i++) {
			tmp = br.readLine().charAt(0);
			cntAlpha[(int)(tmp - 'a')]++;
		}
		for(int i = 0;i<cntAlpha.length;i++) {
			if(cntAlpha[i] >= 5) {
				System.out.print(""+(char)(i+'a'));
				cntCnt++;
			}
		}
		if(cntCnt==0)
			System.out.print("PREDAJA");
		
	}
}
