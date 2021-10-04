
// dowhile로 하면 왜 인지 모르곘는데 마지막 입력을 기다린다... 콘솔 결과확인할때 끝이 안난다 엔터를 한번더 쳐줘야함....
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Test1894_01 {
//public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st =new StringTokenizer(br.readLine());; // 이거 생략 가능했네;;;
//		int S = 3; // 시뮬횟수에 따라서 수정  // 채점 받기 전에 입력을 무한으로 받는다는걸 확인했다...... 그러탐 dowhile로 바꿔보자
		double[][] pos = new double[4][2]; // double인거 지금 깨달았다.....
		String str = null;
//		for (int s = 1; s <= S; s++) { // 시뮬횟수에 따라서 수정
		do {
			
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 2; j++) {
					pos[i][j] = Double.valueOf(st.nextToken()); // 토큰 받아서 입력
//					System.out.println(pos[i][j]);
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
			
			System.out.printf("%.3f %.3f\n",ad[0] - pos[Idx][0] , ad[1] - pos[Idx][1]);
			str =  br.readLine();
			st = new StringTokenizer(str); // 이거 위치 틀림
//			System.out.println(str.length());
		}while(str.length() == 47);//
//		} // end s
		
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
