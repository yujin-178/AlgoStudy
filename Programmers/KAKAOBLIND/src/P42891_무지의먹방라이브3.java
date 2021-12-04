import java.util.Arrays;
import java.util.PriorityQueue;

public class P42891_무지의먹방라이브3 {
	public static void main(String[] args) {
		int[] food_times = { 5, 3, 4, 56, 3 };
		long k = 15;
//		int[] food_times = { 3, 1, 2 };
//		long k = 5;
		System.out.println("ans : " + solution(food_times, k));
	}

	static int solution(int[] food_times, long k) {
		PriorityQueue<Integer> remain = new PriorityQueue<>();
		int len = food_times.length;
		Food[] food = new Food[len];
		for (int i = 0; i < len; i++) {
			food[i] = new Food(false, i + 1, food_times[i]);
			remain.add(food_times[i]);
		}

		int cnt = 0;
		long time = 0;
		int step = 0;

		while (cnt != len && k > time + (len - cnt)) {
			time += (len - cnt);
			++step;
			if (remain.peek() == step) {
				for (int i = 0; i < len; i++) {
					if (food[i].r)
						continue;
					if (food[i].time == step) {
						food[i].r = true;
						food[i].time = 0;
						cnt++;
					}
				}
			}
		}
		System.out.println("time : " + time + ", k : " + k);
		System.out.println(Arrays.toString(food));
		System.out.println("cnt : " + cnt);
		int order = 0;
		int ans = 0;
		if (cnt != len) {
			int remainTime = (int) (k - time);
			for (int i = 0; i < remainTime; order++) {
				if (!food[order % len].r) {
					if (food[order % len].time == step + 1) {
						food[order % len].r = true;
						food[order % len].time = 0;
					}
					i++;
				}
			}
			System.out.println(Arrays.toString(food));
			for (int i = 0; i <= remainTime; order++) {
				if (!food[order % len].r) {
					ans = food[order % len].idx;
					break;
				}
			}
		}
		return cnt == len ? -1 : ans;
	}

	static class Food {
		boolean r;
		int idx, time;

		public Food(boolean r, int idx, int time) {
			super();
			this.r = r;
			this.idx = idx;
			this.time = time;
		}

		@Override
		public String toString() {
			return "Food [r=" + r + ", idx=" + idx + ", time=" + time + "]\n";
		}
	}
}
