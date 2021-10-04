import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
  * @FileName : B2810_컵홀더_1.java
  * @Date : 2021. 9. 1. 
  * @작성자 : KimYuJin
  * @특이점 : 
  * 자리가 N개
  * 인접한 좌석 사이에 컵홀더 1개
  * 양끝에 컵홀더
  * 총 컵홀더 
  * 커플석 사이에는 컵홀더가 없다.
  * *ss*ss*
  * 시작 컵홀더 1
  * s  컵홀더+1  사람 +1
  * LL 컵홀더 +1 사람 +2
  * 
  * *LL*LL*S*
  * 1  2  3 4
  * 0 2  4 5
  */
public class B2810_컵홀더_1 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		char[] line = br.readLine().toCharArray();
		int idx = 0;
		int cup = 1;
		int human = 0;
		while(idx < N) {
			if(line[idx] == 'S') {
				idx +=1;
				cup += 1;
				human +=1;
			}else {
				idx+=2;
				cup +=1;
				human+=2;
			}		
		}
		
		System.out.println(Math.min(cup, human));

	}
}
