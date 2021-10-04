import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

//public class Main {
public class Baek15828_1 {
	public static void main(String[] args) {
		Queue<Integer> q = new LinkedList<>();
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();

		while (true) {
			int tmp = sc.nextInt();
			if (tmp == -1)
				break;
			else if (tmp == 0)
				q.remove();
			else {
				if (q.size() >= N) {}
				else
					q.add(tmp);
			}
		}
		if(q.size() == 0)
			System.out.println("empty");
		else {
			while(q.size() != 0) {
				System.out.print(q.poll() + " ");
			}
		}
	}
}
