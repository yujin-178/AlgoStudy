import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class B1786_찾기_1 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] t = br.readLine().toCharArray();
		char[] p = br.readLine().toCharArray();
		int m = p.length; // 패턴 길이
		int n = t.length; // 텍스트 길이

		int[] pIdxList = new int[m];
		for (int i = 1, j = 0; i < m; i++) {
			while (j > 0 && p[i] != p[j]) {
				j = pIdxList[j - 1];
			}
			if (p[i] == p[j])
				pIdxList[i] = ++j; // 같은 패턴이 존재하면 증가한다.
			else
				pIdxList[i] = 0; // 이미 0으로 채워저 있어서 상관은 없다.
		}

		int cnt = 0;
		ArrayList<Integer> l = new ArrayList<Integer>();
		for (int i = 0, j = 0; i < n; ++i) {
			while (j > 0 && t[i] != p[j]) {
				j = pIdxList[j - 1];
			}
			if (t[i] == p[j]) {
				if (j == m - 1) {
					cnt++;
					l.add((i + 1) - m);
					j = pIdxList[j];
				} else {
					j++;
				}
			}
		}

		System.out.println(cnt);
		for (int i = 0; i < l.size(); i++) {
			System.out.print((l.get(i)+1) + " ");
		}

	}
}
