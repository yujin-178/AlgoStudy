

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//public class Main { // 접근을 바꿔보자
public class Test2304_3 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.valueOf(br.readLine());
		int maxL = 0;
		int maxH = 0;
		int maxHIndexL = 0;
		int[] pillar = new int [1001];

		for (int n = 1; n <= N; n++) {
			st = new StringTokenizer(br.readLine());
			int Ln = Integer.valueOf(st.nextToken());
			int Hn = Integer.valueOf(st.nextToken());
			
			if (Ln > maxL)
				maxL = Ln;
			if (Hn > maxH) {
				maxH = Hn;
				maxHIndexL = Ln; 
			}
			pillar[Ln] = Hn;

		}
		int sum = 0;
		int leftIdx = 0;
		int leftMaxPillar = 0;
		while(leftIdx != maxHIndexL) {
			if(leftMaxPillar < pillar[leftIdx])
				leftMaxPillar = pillar[leftIdx];
			sum += leftMaxPillar;
			leftIdx++;
		}
		
		int rightIdx = 1000;
		int rightMaxPillar = 0;
		while(rightIdx != maxHIndexL) {
			if(rightMaxPillar < pillar[rightIdx])
				rightMaxPillar = pillar[rightIdx];
			sum += rightMaxPillar;
			rightIdx--;
		}
		sum += maxH;
		System.out.println(sum);
	}
}
