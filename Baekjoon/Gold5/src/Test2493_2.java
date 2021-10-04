
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//public class Main  {
public class Test2493_2 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("C:/CodingStudy/Baekjoon/Gold5/2493_input"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int num = Integer.valueOf(br.readLine()) + 1;
		int[] tower = new int[num];
		int[] towerIdx = new int[num];
		int[] ans = new int[num - 1];
		int max = 0;
		int maxIdx = 0;
		StringTokenizer st = new StringTokenizer(br.readLine());
		int cnt = 1;
		tower[0] = 0;
		towerIdx[0] = 0;
		int tIdx=0;		
//		while (cnt < num) {
//			int tmp = Integer.valueOf(st.nextToken());
//			if(tmp>=tower[cnt-1])
//				tower[cnt];
//				
//		}
		
//		StringBuilder???
		
		for (int i = 0; i < ans.length; i++) {
			System.out.print(ans[i] + " ");
		}

	}
}