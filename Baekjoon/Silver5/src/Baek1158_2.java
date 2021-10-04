import java.util.Scanner;

//public class Main {
public class Baek1158_2 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int K = sc.nextInt();
		int[] num = new int[N];
		for (int i = 1; i <= N; i++) {
			num[i - 1] = i;
		}
		StringBuilder sb = new StringBuilder();
		int idx = 0;
		int numLength = num.length;
		sb.append("<");
		while (numLength != 0) {
			idx += K - 1;
			idx %= numLength;
			sb.append(num[idx]).append(", ");
			for (int i = idx + 1; i < numLength; i++) {
				num[i - 1] = num[i];
			}
			--numLength;
			if (numLength != 0)
				idx %= numLength;
		}
		sb.delete(sb.length() - 2, sb.length());
		sb.append(">");
		System.out.print(sb);
		sc.close();
	}
}
