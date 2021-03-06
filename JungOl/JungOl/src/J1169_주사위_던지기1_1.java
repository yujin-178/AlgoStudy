import java.util.Scanner;

public class J1169_주사위_던지기1_1 {
	static int M, N;
	static int[] arr;
	static boolean[] chk;
	static StringBuilder sb;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		sb = new StringBuilder();
		arr = new int[N];
		chk = new boolean[7];
		switch (M) {
		case 1:
			dfs1(0);
			break;
		case 2:
			dfs2(0, 1);
			break;
		case 3:
			dfs3(0);
			break;
		}
		System.out.println(sb.toString());
	}

	static void dfs1(int dep) {
		if (dep == N) {
			for(int i = 0; i< N;i++) {
				sb.append(arr[i] + " ");
			}
			sb.append("\n");
			return;
		}

		for (int i = 1; i <= 6; i++) {
			arr[dep] = i;
			dfs1(dep + 1);
		}
	}

	static void dfs2(int dep, int idx) {
		if (dep == N) {
			for(int i = 0; i< N;i++) {
				sb.append(arr[i] + " ");
			}
			sb.append("\n");
			return;
		}

		for (int i = idx; i <= 6; i++) {
			arr[dep] = i;
			dfs2(dep + 1, i);
		}
	}

	static void dfs3(int dep) {
		if (dep == N) {
			for(int i = 0; i< N;i++) {
				sb.append(arr[i] + " ");
			}
			sb.append("\n");
			return;
		}

		for (int i = 1; i <= 6; i++) {
			if (chk[i])
				continue;
			chk[i] = true;
			arr[dep] = i;
			dfs3(dep + 1);
			chk[i] = false;

		}
	}
}
