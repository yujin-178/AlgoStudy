import java.util.LinkedList;
import java.util.Scanner;

//public class Main {
public class Baek1158_1 {
	public static void main(String[] args) {
		LinkedList<Integer> l = new LinkedList<>();
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int K = sc.nextInt();

		for (int i = 1; i <= N; i++) {
			l.add(i);
		}
		StringBuilder sb = new StringBuilder();
		int idx = 0;
		sb.append("<");
		while (!l.isEmpty()) {
			idx += (K - 1);
			idx %= l.size();
			sb.append(l.get(idx)).append(", ");
			l.remove(idx);
			if (l.size() != 0)
				idx %= l.size();
		}
		sb.delete(sb.length() - 2, sb.length() );
		sb.append(">");
		System.out.print(sb);
		sc.close();
	}
}
