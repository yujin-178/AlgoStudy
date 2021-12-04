import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class test02 {
	public static void main(String[] args) {
		int n = 10;
		String[] recipes = { "A 3", "B 2" };
		String[] orders = { "A 1", "A 2", "B 3", "B 4" };
		System.out.println(solution(n, recipes, orders)); // 7
	}

	static HashMap<String, Integer> r;
	static ArrayList<Order> o;

	static public int solution(int n, String[] recipes, String[] orders) {
		int ans = 0;
		int rlen = recipes.length;
		int olen = orders.length;
		StringTokenizer st;
		r = new HashMap<String, Integer>();
		for (int i = 0; i < rlen; i++) {
			st = new StringTokenizer(recipes[i]);
			r.put(st.nextToken(), Integer.parseInt(st.nextToken()));
		}
		o = new ArrayList<Order>();
		for (int i = 0; i < rlen; i++) {
			st = new StringTokenizer(recipes[i]);
			o.add(new Order(st.nextToken(), Integer.parseInt(st.nextToken())));
		}
		boolean[] oven = new bool
		int idx = 0;
		while (idx != olen) {
			
		}

		return ans;
	}

	static class Order {
		String name;
		int time;

		public Order(String name, int time) {
			super();
			this.name = name;
			this.time = time;
		}

	}
}
