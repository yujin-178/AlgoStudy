import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class S5643_키순서_3 {
	static int N, M, ans;
	static Map<Integer, ArrayList<Integer>> sm;
	static Map<Integer, ArrayList<Integer>> lm;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			sm = new HashMap<>();
			lm = new HashMap<>();
			for (int m = 0; m < M; m++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				if (!sm.containsKey(a))
					sm.put(a, new ArrayList<Integer>());
				sm.get(a).add(b);

				if (!lm.containsKey(b))
					lm.put(b, new ArrayList<Integer>());
				lm.get(b).add(a);
			}
			ans = 0;
			for (int i = 1; i <= N; i++) {
				
				if (bfs(i) == N - 1)
					ans++;
			}

			System.out.println("#" + tc + " " + ans);

		}
	}

	static int bfs(int a) {
		Queue<Integer> q = new LinkedList<>();
		boolean[] chk = new boolean[N + 1];
		q.add(a);
		chk[a] = true;
		int cnt = 0;
		while (!q.isEmpty()) {
			int tmp = q.poll();
			if (sm.containsKey(tmp)) {
				for (int i = 0; i < sm.get(tmp).size(); i++) {
					if (!chk[sm.get(tmp).get(i)]) {
						q.add(sm.get(tmp).get(i));
						cnt++;
						chk[sm.get(tmp).get(i)] =true;
					}
				}
			}
		}
//		System.out.println("위"+a+":"+cnt);
		q.add(a);
		chk = new boolean[N + 1];
		chk[a] = true;
		while (!q.isEmpty()) {
			int tmp = q.poll();
			if (lm.containsKey(tmp) ) {
				for (int i = 0; i < lm.get(tmp).size(); i++) {
					if (!chk[lm.get(tmp).get(i)]) {
						q.add(lm.get(tmp).get(i));
						cnt++;
						chk[lm.get(tmp).get(i)] = true;
					}
				}
			}
		}
//		System.out.println("밑"+a+":"+cnt);
		return cnt;

	}

}
