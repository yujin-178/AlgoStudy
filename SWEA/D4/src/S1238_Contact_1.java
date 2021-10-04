import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class S1238_Contact_1 {
	static boolean[] chk;
	static int start, N, fromCnt;
	static Queue<Person> q;
	static Person[] array;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("1238_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int tc = 1; tc <= 10; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			start = Integer.parseInt(st.nextToken());
			array = new Person[101];
			chk = new boolean[101];
			st = new StringTokenizer(br.readLine());
			fromCnt = 0;
			q = new LinkedList<>();
			for (int i = 0; i < N / 2; i++) {
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				if (array[from] == null) {
					array[from] = new Person(from, to);
					++fromCnt;
				} else
					array[from].addTo(to);
			}
			q.add(array[start]);
			array[start].chk = true;
			int cnt = array[start].to.size();
			int maxNum = 0;
			while (true) {
				maxNum = 0;
				int newCnt = 0;
				for (int from = 0; from < cnt; from++) {
					if (q.isEmpty())
						continue;
					Person tmp = q.poll();

					for (int i = 0; i < tmp.to.size(); i++) {
						if (array[tmp.to.get(i)] != null && !array[tmp.to.get(i)].chk) {
							q.add(array[array[tmp.num].to.get(i)]);
							array[array[tmp.num].to.get(i)].chk = true;
							newCnt++;
							if (tmp.num > maxNum)
								maxNum = tmp.num;
						}
					}
				}
				if (q.isEmpty())
					break;
				cnt = newCnt;
			}
			System.out.println(maxNum);
		}
	}

	static class Person {
		int num;
		LinkedList<Integer> to = new LinkedList<>();
		boolean chk;

		public Person(int num, int to) {
			super();
			this.num = num;
			this.to.add(to);
			chk = false;
		}

		public void addTo(int to) {
			this.to.add(to);
		}

	}
}
