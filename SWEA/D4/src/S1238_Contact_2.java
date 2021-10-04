import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


/**
 * @author deter
 *	이거 깊은 복사 해가지고 array에 저장한 친구도 사라진 것 같다....
 */
public class S1238_Contact_2 {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("1238_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int tc = 1; tc <= 1; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken()); // 입력 개수
			int start = Integer.parseInt(st.nextToken()); // 시작 번호
			Person[] array = new Person[101];// 배열 생성
			st = new StringTokenizer(br.readLine()); //
			Queue<Person> q = new LinkedList<>();

			for (int i = 0; i < N / 2; i++) {
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				if (array[from] == null) {
					array[from] = new Person(from, to); // from에는 연락 가능 노드까지 기록
					if (array[to] == null) // to가 없다면
						array[to] = new Person(to); // to는 생성 이렇게 하면 최종 점도 생성되어 있다.
				} else
					array[from].addTo(to); // 기존에 존재한다면
			} // end input
			System.out.println(Arrays.toString(array[1].to.toArray()));
//			System.out.println(array[7].to.size());
			q.add(array[start]); // 시작점 추가
			array[start].chk = true; // 방문 기록
			int cnt = array[start].to.size(); // 시작점 방문 개수
			int nextCnt = 0;
			int max = 0;
			while (!q.isEmpty()) {
				for (int i = 0; i < cnt; i++) {
					Person tmp = q.poll();
//					System.out.println(tmp.num + "개수 : " + tmp.to.size());
					for (int j = 0; j < tmp.to.size(); j++) {
						System.out.print(tmp.to.get(j)+" ");
						if (!array[tmp.to.get(j)].chk) { // 방문하지 않았다면
							q.add(array[tmp.to.get(j)]); // queue에 추가
							array[tmp.to.get(j)].chk = true; // 방문 표시
							nextCnt += array[tmp.to.get(j)].to.size();
							max = Math.max(max, array[tmp.to.get(j)].num);
						}
						else continue;
					}
					System.out.println();
				}
				
				

				if (q.isEmpty())
					break;
				cnt = nextCnt;
				nextCnt = 0;
			}
			System.out.println(array[1].num);
			System.out.println(max);

		}
	}

	static class Person {
		int num;
		LinkedList<Integer> to = new LinkedList<>();
		boolean chk;

		public Person(int num) {
			super();
			this.num = num;
			chk = false;
		}

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
