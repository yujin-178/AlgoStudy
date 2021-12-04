import java.util.Arrays;
import java.util.PriorityQueue;

public class P42891_무지의먹방라이브5 {
	public static void main(String[] args) {
//		int[] food_times = { 100 };
//		long k = 1000;
		int[] food_times = { 1, 1, 1, 1, 1, 1, 1, 1, 1 };
		long k = 3;
//		int[] food_times = { 8378, 1928, 23, 4, 3, 425, 43, 3, 432, 23 };
//		long k = 10000;
//		int[] food_times = { 5, 3, 4, 56, 3 };
//		long k = 15;
//		int[] food_times = { 3, 1, 2 };
//		long k = 5;
		System.out.println("ans : " + solution(food_times, k));
	}

	static int solution(int[] food_times, long k) {
		PriorityQueue<Food> remain = new PriorityQueue<>();
		int len = food_times.length;

		Food[] food = new Food[len];
		for (int i = 0; i < len; i++) {
			food[i] = new Food(i + 1, food_times[i]);
			remain.add(food[i]);
		}
//		System.out.println(Arrays.toString(food));
		int step = 0;
		long time = 0;
		int cnt = 0;
		while (!remain.isEmpty() && (time + (len - cnt) < k)) {
			step++;
			time += len - cnt;
//			System.out.println(time);
			while (step == remain.peek().time) {
				food[remain.peek().idx - 1].time = 0;
				remain.poll();
				cnt++;
				if (remain.isEmpty())
					break;
			}
		}
//		System.out.println("len : " + len);
//		System.out.println("cnt : " + cnt);
//		System.out.println("step : " + step);
//		System.out.println("time : " + time);
//		System.out.println(Arrays.toString(food));
//		System.out.println(k - time);
		int ans = 0;
		if (len != cnt) {
			int idx = 0;
			for (int i = 0; i < k - time; idx++) {
//			System.out.println(idx % len);
				if (food[idx % len].time != 0) {
					i++;
					if (food[idx % len].time == (step + 1 + idx / len))
						food[idx % len].time = 0;
//				System.out.println(food[idx % len]);
				}
			}
			while (food[idx % len].time == 0) {
				idx++;
			}
			ans = food[idx % len].idx;
		} else {
			ans = -1;
		}

		return ans;
	}

	static class Food implements Comparable<Food> {

		int idx, time;

		public Food(int idx, int time) {
			super();

			this.idx = idx;
			this.time = time;
		}

		@Override
		public int compareTo(Food o) {
			return Integer.compare(this.time, o.time);
		}

		@Override
		public String toString() {
			return "[Food : idx=" + idx + ", time=" + time + "]\n";
		}

	}
}
