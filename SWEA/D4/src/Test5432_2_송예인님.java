import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

//public class Solution{
public class Test5432_2_송예인님 {
	static class Box {
		char ch;
		int idx;

		Box(char ch, int idx) {
			this.ch = ch;
			this.idx = idx;
		}
	}

	static boolean[] check_bim;
	static int ans;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] ch_arr = br.readLine().toCharArray();
		Stack<Box> st = new Stack<>();
		check_bim = new boolean[ch_arr.length];
		st.push(new Box(ch_arr[0], 0)); // 어차피 처음에 하는거니까 밖에서 한번만하고 조건문 제거
		for (int n = 1; n < ch_arr.length; n++) {
			if(ch_arr[n]==')' && st.peek().ch=='(') { // 공통된 조건 한번만 확인하도록
				if(st.peek().idx + 1 == n) { // 해당 조건일 때 수행하면 continue로 동작 단축
					st.pop();
					check_bim[n] = true;
					continue;
				}
				Box box = st.pop();
				int seperate = 0;
				for (int i = box.idx; i < n; i++) {
					if (check_bim[i])
						seperate++;
				}
//				System.out.println(seperate+1);
				ans += seperate + 1;
				continue;
			}
			st.push(new Box(ch_arr[n],n));

		}
		System.out.println(ans);

	}

}
