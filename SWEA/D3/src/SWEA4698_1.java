import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//public class Solution {
public class SWEA4698_1 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("C:/CodingStudy/SWEA/D3/4698_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		boolean[] prime = new boolean[1000001];
		prime[0] = true;
		prime[1] = true;
		for(int i =2;i<=1000;i++) {
			int tmp = i+i;
			while(tmp <= 1000000) {
				prime[tmp] = true;
				tmp += i;
			}
		}
		for (int tc = 1; tc <= T; tc++) {
			
			st = new StringTokenizer(br.readLine());
			int D = Integer.parseInt(st.nextToken());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			int ans = 0;
			for(int i = A;i<=B;i++) {
				if(!prime[i]) {
					int tmp = i;
					while(tmp != 0) {
						if(tmp%10 == D) {
							++ans;
							break;
						}
						tmp/=10;
							
					}
				}
			}
			
			System.out.println("#" + tc + " " + ans);
		} // end tc
	}
}
