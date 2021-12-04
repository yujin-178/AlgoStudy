import java.util.LinkedList;
import java.util.PriorityQueue;

public class P42891_무지의먹방라이브 {
	public static void main(String[] args) {
		int[] food_times = { 5, 3, 4, 56, 3 };
		long k = 11;
		System.out.println("ans : " + solution(food_times, k));
	}

	static int solution(int[] food_times, long k) {
		LinkedList<Integer> food = new LinkedList<>();
		PriorityQueue<Integer> remain = new PriorityQueue<>();
		int len = food_times.length;
		for (int i = 0; i < len; i++) {
			food.add(food_times[i]);
			remain.add(food_times[i]);
		}

		int cnt = 0;
		int time = 0;
		int step = 1;

		while (cnt != len && k > time + (len - cnt)) {
			System.out.println("time : " + time + ", k : " + k);
			time += (len - cnt);
			if (remain.peek() == step) {
//				if (time + (len - cnt) > k)
//					break;
//				else {
				for (int i = 0; i < len - cnt; i++) {
					if (food.get(i) == step) {
						food.remove(i);
//						System.out.println(step + ", " + remain.poll());
//							remain.poll();
						i--;
						cnt++;
					}
				}
//				}
			}
			step++;

		}
		System.out.println("time : " + time + ", k : " + k);
		System.out.println(food);
		System.out.println(cnt);
		int order = 0;
		for (int i = time; i < k; i++) {
			if (food.get(order % (len - cnt)) == step) {
				food.remove(order--);
				remain.poll();
				cnt++;
			}
			order++;
		}
		System.out.println(food);
		return cnt == len ? -1 : ((order ) % (len - cnt)) +1;
	}
}
