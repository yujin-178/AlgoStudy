import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class P72416_매출하락최소화7 {

	static int[] reSales, chk;
	static int ans, cnt, len;
	static HashMap<Integer, ArrayList<Integer>> map;

	public static void main(String[] args) throws IOException {
		int[] sales = { 5, 6, 5, 3, 4 };
		int[][] links = { { 2, 3 }, { 1, 4 }, { 2, 5 }, { 1, 2 } };
		int ans = solution(sales, links);
		System.out.println(ans);
	}

	static public int solution(int[] sales, int[][] links) {
		len = sales.length + 1;
		reSales = new int[len];
		chk = new int[len];
		for (int i = 1; i <= len; i++) {
			reSales[i] = sales[i - 1];
		}

		for (int i = 0; i <= len; i++) {
			if (!map.containsKey(links[i][0])) {
				map.put(links[i][0], new ArrayList<>());
			}
			map.get(links[i][0]).add(links[i][1]);
		}

		dfs(1, 0);

		return ans;
	}

	static void dfs(int head, int sum) {

		int num = map.get(head).size();
		for (int i = 0; i < num; i++) {
			int n = map.get(head).get(i);
			if (map.containsKey(n)) {
				
			}
		}
	}

}
