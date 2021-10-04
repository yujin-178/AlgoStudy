import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

//public class Main{
public class Baek2164_1 {
	public static void main(String[] args) {
		Queue<Integer> q = new LinkedList<>();
		Scanner sc = new Scanner(System.in);
		int num = sc.nextInt();
		
		for(int i = 1; i<=num;i++) {
			q.add(i);
		}
		while(q.size() != 1) {
			q.poll();
			int tmp = q.poll();
			q.add(tmp);
		}
		System.out.println(q.peek());

	}
}
