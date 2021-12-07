import java.util.HashSet;
import java.util.Iterator;

public class P86053_금과은운반하기2 {
	public static void main(String[] args) {
		System.out.println(solution(5, 12));
	}

	static HashSet<Integer> map[];

	static public int solution(int N, int number) {
		map = new HashSet[9];
		for (int i = 1; i < 9; i++) {
			map[i] = new HashSet<>();
		}

		return findDep(N, number);
	}

	static int findDep(int N, int number) {
		int NN = 0;
		for (int i = 1; i < 9; i++) {
			NN = NN * 10 + N;
			map[i].add(NN);
		}

		for (int i = 2; i < 9; i++) {
			for (int j = 1; j <= i / 2; j++) {
				Iterator<Integer> i1 = map[j].iterator();
				while (i1.hasNext()) {
					int tmp1 = i1.next();
					Iterator<Integer> i2 = map[i - j].iterator();
					while (i2.hasNext()) {
						
						int tmp2 = i2.next();
						int result = tmp1 + tmp2;
						if (result == number)
							return i;
						map[i].add(result);

						result = tmp1 - tmp2;
						if (result == number)
							return i;
						map[i].add(result);

						result = tmp2 - tmp1;
						if (result == number)
							return i;
						map[i].add(result);

						result = tmp1 * tmp2;
						if (result == number)
							return i;
						map[i].add(result);

						if (tmp2 != 0) {
							result = tmp1 / tmp2;
							if (result == number)
								return i;
							map[i].add(result);
						}

						if (tmp1 != 0) {
							result = tmp2 / tmp1;
							if (result == number)
								return i;
							map[i].add(result);
						}
					}
				}
			}
		}

		return -1;
	}
}
