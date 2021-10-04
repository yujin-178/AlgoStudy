


import java.io.IOException;

import java.util.Scanner;


public class Test1894_03 {
//public class Main {
	public static void main(String[] args) throws IOException {
//		
		Scanner sc = new Scanner (System.in);
		
		
		double[][] pos = new double[4][2]; // double인거 지금 깨달았다.....
		
		while(sc.hasNext()) {
			
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 2; j++) {
					pos[i][j] = sc.nextDouble(); // 토큰 받아서 입력
					System.out.println(pos[i][j]);
				} // end j
			} // end i

			int Idx = findas(pos);
			int sameIdx = 0;
			if (Idx == 5) // 혹시라도 5받으면 문제임
				System.out.println("뭔가 이상함");
			for (int i = 0; i < 4; i++) {
				if ((i != Idx) && (pos[Idx][0] == pos[i][0]) && (pos[Idx][1] == pos[i][1]))
					sameIdx = i;
			}
			double[] ad = new double[2];
			if(Idx == 0) {
				ad[0] += pos[1][0]; 
				ad[1] += pos[1][1]; 
			}
			else {
				ad[0] += pos[0][0]; 
				ad[1] += pos[0][1];
			}
			if(sameIdx == 2){
				ad[0] += pos[3][0];
				ad[1] += pos[3][1];
			}
			else {
				ad[0] += pos[2][0];
				ad[1] += pos[2][1];
			}
			
			System.out.printf("%.03f %.03f\n",ad[0] - pos[Idx][0] , ad[1] - pos[Idx][1]);
//			if(sc.hasNext())
//				break; // 이렇게 하면 입력 한줄 받고 종료
		}
		sc.close();
	}// end main

	public static int findas(double[][] pos) {
		for (int i = 0; i < 4; i++) {
			// double[] tmp = { pos[i][0], pos[i][1] }; // 이거 필요 없었네;;;;
			for (int j = i + 1; j < 4; j++) {
				if ((pos[i][0] == pos[j][0]) && (pos[i][1] == pos[j][1]))
					return i;
			}
		}
		return 5; // 왜 이거 안하면 문제 에러라고 그러지? 범위 벗어나는 경우가 생겨서 그런가?
	}

}// end class
