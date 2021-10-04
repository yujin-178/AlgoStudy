

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//public class Solution {
public class Test1225_1 {
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("C:/CodingStudy/SWEA/D3/1225_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		for (int tc = 1; tc <= 10; tc++) {
			int n = Integer.valueOf(br.readLine());
			st = new StringTokenizer(br.readLine());
			int[] num = new int[8];
			for(int idx= 0;idx<8;idx++) {
				num[idx] = Integer.valueOf(st.nextToken());
			}
			
			int cntMinus = 1;
			int numIdx = 0;
			while(true) {
				num[numIdx] -= cntMinus;
				if(num[numIdx] <= 0) {
					num[numIdx] = 0;
					break;
				}
					
				if(cntMinus<5)
					++cntMinus;
				else
					cntMinus = 1;
				numIdx = ++numIdx%8;
				
			}
			System.out.print("#"+tc+" ");
			for(int i = numIdx ; i<numIdx+8;i++) {
				System.out.print(num[(i+1)%8]+" ");
			}
			System.out.println();
			
		}

	}
}
