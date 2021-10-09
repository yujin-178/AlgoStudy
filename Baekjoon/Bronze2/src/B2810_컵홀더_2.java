import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
  * @FileName : B2810_컵홀더_1.java
  * @Date : 2021. 10. 8. 
  * @작성자 : KimYuJin
  * @특이점 : 출력에 컵을 컵홀더에 놓을 수 있는 최대 사람의 수를 출력한다. 라는 문구가 있음
  * 즉 사람들이 심보가 꼬여서 다른 사람이 컵홀더를 사용할 수 있는 상황을 막지는 않음 그래서 간단하게 생각 가능함
  * 
  * 자리가 N개
  * 인접한 좌석 사이에 컵홀더 1개
  * 양끝에 컵홀더
  * 총 컵홀더 
  * 커플석 사이에는 컵홀더가 없다.
  * ^S^LL^S^
  * 시작 컵홀더 1
  * s  컵홀더 +1  사람 +1
  * LL 컵홀더 +1 사람 +2
  * 최종적으로 더 적은 숫자를 선택하면 끝난다.
  */
public class B2810_컵홀더_2 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		char[] line = br.readLine().toCharArray();
		int idx = 0;
		int cupHolder = 1;
		int human = 0;
		while(idx < N) {
			if(line[idx] == 'S') {
				idx +=1;
				cupHolder += 1;
				human +=1;
			}else {
				idx+=2;
				cupHolder +=1;
				human+=2;
			}		
		}
		
		System.out.println(Math.min(cupHolder, human));

	}
}
