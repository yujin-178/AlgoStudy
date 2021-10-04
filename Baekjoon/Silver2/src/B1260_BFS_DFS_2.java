import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B1260_BFS_DFS_2 {
	static boolean[] chk;
	static boolean[][] map;
	static int MAXIDX = 1001;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("1260_input"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int NodeNum = Integer.parseInt(st.nextToken());
		int EdgeNum = Integer.parseInt(st.nextToken());
		int start = Integer.parseInt(st.nextToken());
		map = new boolean[MAXIDX][MAXIDX];
		for (int i = 0; i < EdgeNum; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			map[from][to] = true;
			map[to][from] = true;

		}
		chk = new boolean[MAXIDX];
		chk[start] = true; 
		dfs(start);
		System.out.println();
		bfs(start);
	}

	static void dfs(int start) {
		System.out.print(start + " ");
		
		for (int i = 1; i < MAXIDX; i++) {
			if (map[start][i] && !chk[i]) {
				chk[i] = true;
				dfs(i);
			}

		}

	}

	static void bfs(int start) {
		Queue<Integer> queue = new LinkedList<>();
		chk = new boolean[MAXIDX];

		chk[start] = true;
		queue.add(start);

		while (!queue.isEmpty()) {
			int size = queue.size();

			for (int s = 0; s < size; s++) {
				int tmp = queue.poll();
				System.out.print(tmp + " ");

				for (int i = 1; i < MAXIDX; i++) {
					if (map[tmp][i] && !chk[i]) {
						queue.add(i);
						chk[i] = true;
					}
				}
			}
		}
	}

}
