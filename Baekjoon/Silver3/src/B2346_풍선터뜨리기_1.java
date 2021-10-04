/**
 * 풍선의 번호와 다음에 터뜨릴 풍선의 위치를 클래스로 만들었으며,
 * 다음에 터트릴 풍선의 위치가 양수인 경우에는 풍선을 터뜨리면서 다음 풍선들의 인덱스가 1씩 감소했기 때문에 다음 풍선의 위치에서 1을 빼고 이동했으며,
 * 음수의 경우에는 인덱스의 변화가 없으므로 그대로 인덱스를 옮겨서 풍선을 터뜨리는 방식으로 구현하였다.
 * 인덱스를 갱신하는 과정에서 음수거나 인덱스를 벗어나는 경우 나머지 연산과 리스트 크기만큼 더하는 연산을 통해서 0~list.size()-1 범위로 갱신하였다.
 * 다만 마지막 과정에서 리스트가 비어 있으므로 0나누기 에러가 발생한다. 이를 막기 위하여 if를 사용하였다.
 */

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

//public class Main {

public class B2346_풍선터뜨리기_1 {
	static LinkedList<balloon> l;
	static int N;

	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("2346_input"));
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		l = new LinkedList<balloon>();
		for (int i = 0; i < N; i++)
			l.add(new balloon(i + 1, sc.nextInt())); // 각 풍선들을 리스트에 넣어준다. (풍선 번호, 다음에 터트릴 풍선의 위치)
		int idx = 0; // 처음 풍선 인덱스
		while (!l.isEmpty()) { // 풍선이 없다면 종료
			balloon tmp = l.remove(idx); // 해당 풍선을 터트린다.
			if (tmp.next > 0)
				idx += tmp.next - 1; // 양수인 경우에는 풍선이 터지면서 다른 풍선들의 인덱스가 하나씩 줄었다.
			else
				idx += tmp.next; // 그 외의 경우에는 상관없다.

			if (!l.isEmpty()) {// 위에서 리스트에서 목표 풍선을 제거함
				idx %= l.size(); // 0으로 나누는 경우가 있다., 나누는 이유 : 몫만큼 회전하는건 제자리를 도는거라 의미가 없다.
				if (idx < 0)
					idx += l.size(); // 0보다 작은 경우에는 사이즈 만큼 더해서 0~l.size()-1 인덱스로 재 설정 해준다.
			}

			System.out.print(tmp.num + " "); // 터진 풍선의 번호 출력
		}
		sc.close();
	}

	static class balloon {
		int num; // 풍선 번호
		int next; // 다음에 몇번째 풍선을 터트릴꺼냐?

		public balloon(int num, int next) { // 생성자
			super();
			this.num = num;
			this.next = next;
		}
	}
}
