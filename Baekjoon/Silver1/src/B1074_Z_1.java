import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class B1074_Z_1 {
	static int N, R, C, ans;

	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("1074_input"));
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		R = sc.nextInt();
		C = sc.nextInt();
		int mid = (int) Math.pow(2, N - 1);
		visit(mid, mid, 0, mid/2);


	}

	static void visit(int midR, int midC, int dep, int mid) {
		if (dep == N) {
			System.out.print(ans);
			return;
		}

		int sum = (int) Math.pow(2, 2 * (N - (1 + dep)));

		if (R < midR && C < midC) { // 1사분면
			ans += 0;
			visit(midR - mid, midC - mid, dep + 1, mid / 2);
		} else if (R < midR && C >= midC) { // 2사분면
			ans += sum;
			visit(midR - mid, midC + mid, dep + 1, mid / 2);
		} else if (R >= midR && C < midC) { // 3사분면
			ans += 2 * sum;
			visit(midR + mid, midC - mid, dep + 1, mid / 2);
		} else if (R >= midR && C >= midC) { // 4사분면
			ans += 3 * sum;
			visit(midR + mid, midC + mid, dep + 1, mid / 2);
		}

	}
}
