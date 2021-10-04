import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 * 왼쪽의 이동, 오른쪽이동, 타겟의 인덱스를 반환하는 메서드를 구현하였으며,
 * 목표의 index가 중앙보다 앞에 있는경우 왼쪽으로 돌리고, 중앙보다 뒤에 있는 경우에는 오른쪽으로 돌려서 목표를 꺼내는 방식으로 구현하였다.
 * 우식님은 메서드를 만들어서 index를 찾지 않고 list.indexOf(); 메서드를 이용해서 리스트의 인덱스를 찾았으며,
 *  deque을 사용해서 구현하는 것이 좀 더 효율적인 사용임을 배울 수 있었다.
 */
public class B1021_회전하는큐_1 {
	static int N, M, cnt;
	static LinkedList<Integer> l;
	static int[] target;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		l = new LinkedList<Integer>();
		target = new int[M];
		st = new StringTokenizer(br.readLine());
		for (int m = 0; m < M; m++) {
			target[m] = Integer.parseInt(st.nextToken()); // 목표를 저장한다.
		}
		for (int n = 1; n <= N; n++) {
			l.add(n); // 1 ~ N 을 입력한다.
		}

		for (int i = 0; i < M; i++) {
			int tmp = target[i]; // 타겟 저장
			while (l.size() != N - M) {
				int idx = search(tmp); // 목표의 인덱스 출력
				if (tmp == l.get(0)) { // 목표와 맨앞의 요소가 같다면
					l.remove(0); // 빼내기
					break; // 그럼 다음 타겟을 노려야 한다. 반복문 종료
				}
				if (idx > l.size() / 2) // 목표의 인덱스가 중앙보다 뒤라면
					Rrotate(); // 목표를 뒤로 빼내는게 빠르다.
				else // 목표의 인덱스가 중앙보다 앞이라면
					Lrotate(); // 목표를 앞으로 빼내는게 빠르다.

				cnt++; // rotate 연산 횟수 체크
			}
		}

		System.out.println(cnt); // rotate 연산 횟수 출력

	}

	static int search(int tar) {
		for (int i = 0; i < l.size(); i++) {
			if (l.get(i) == tar) // 타겟이 몇번에 위치해 있는지
				return i; // 인덱스를 반환
		}
		return -1;
	}

	static void Lrotate() {
		l.addLast(l.remove(0)); // 0 번째 (맨 앞)요소를 빼서 맨 뒤에 집어 넣는다.
	}

	static void Rrotate() {
		l.addFirst(l.remove(l.size() - 1)); // 맨 마지막(맨 뒤) 요소를 빼서 맨 앞에 집어 넣는다.
	}
}
