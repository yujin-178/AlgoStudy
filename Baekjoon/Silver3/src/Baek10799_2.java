import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//public class Main {
public class Baek10799_2 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int layer = 0; // 현재 쇠막대기 층수
		int barCnt = 0; // 쇠막대기 개수
		int ans = 0; // 최종 쇠막대기 수
		char[] tmp = br.readLine().toCharArray();
		
		for(int idx = 0; idx<tmp.length;idx++) {
			if(tmp[idx] == '(') { // '('인 경우 쇠막대기 층과 쇠막대기 개수가 증가한다.
				++layer;
				++barCnt;
			}
			if(tmp[idx] == ')') {
				--layer; // 층은 확실하게 감소한다.
				if(tmp[idx-1] == '(') {
					ans += layer; // 현재 layer 위에 레이저가 존재하므로 layer 만큼 증가한다.
					--barCnt; // 레이저임을 파악하고 쇠막대기 수를 감소시킨다.
				}
			}
		}
		
		System.out.println(ans + barCnt);
	}
}