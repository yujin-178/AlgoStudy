import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B2605_줄세우기_1 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int num = Integer.parseInt(br.readLine());
		int[] pos = new int[num];
		StringTokenizer st = new StringTokenizer(br.readLine());
		pos[0] = 1; // 1번 자리 1
		st.nextToken(); // 처음 0은 필요 없다.
		for (int i = 1; i < num; i++) {
			int tmp = i - Integer.parseInt(st.nextToken()); // 현재 위치 i에서 입력받은 만큼 앞으로
			int j = i; // j는 현재 위치
			while(j != tmp) { // 새로운 숫자가 들어갈 자리까지
				pos[j] = pos[j-1]; // 앞에 숫자 전부 한칸 뒤로
				--j; // 한칸 앞으로
			}
			pos[tmp] = i+1; // 새로운 숫자 삽입
		}
		StringBuilder sb = new StringBuilder();
		for(int i = 0;i<num;i++) {
			sb.append(pos[i]).append(" ");
		}
		System.out.println(sb);
	}
}
