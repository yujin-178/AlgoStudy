import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class P72411_가장먼노드_49189 {

	static HashMap<String, Integer> map = new HashMap<>();
	static boolean[] chk;
	static String order;
	static int tmplen;
	static int[] cntMax;

	public static void main(String[] args) {
		int n = 6;
		int tmp[][] = { { 3, 6 }, { 4, 3 }, { 3, 2 }, { 1, 3 }, { 1, 2 }, { 2, 4 }, { 5, 2 } };
		System.out.println(solution(6, tmp));
	}

	static boolean[] ansNode;
	static ArrayList[] edgeInfo;
	static int N;

	static public int solution(int n, int[][] edge) {
		ansNode = new boolean[n + 1];
		edgeInfo = new ArrayList[n + 1];
		N = n;
		int cntEdge = edge.length;
		for (int i = 0; i < cntEdge; i++) {
			if (edgeInfo[edge[i][0]] == null)
				edgeInfo[edge[i][0]] = new ArrayList<Integer>();
			edgeInfo[edge[i][0]].add(edge[i][1]);
			if (edgeInfo[edge[i][1]] == null)
				edgeInfo[edge[i][1]] = new ArrayList<Integer>();
			edgeInfo[edge[i][1]].add(edge[i][0]);
		}

		return bfs();

	}

	static int bfs() {
		Queue<Integer> q = new LinkedList<>();
		q.add(1);
		ansNode[1] = true;
		int cnt = 0;
		int ansTmp = 0;
		int cntall = 1;
		while (!q.isEmpty()) {
			cnt++;
			int qSize = q.size();

			ansTmp = 0;
			for (int i = 0; i < qSize; i++) {
				int tmp = q.poll();
//				if (edgeInfo[tmp] == null) {
//					ansNode[tmp] = true;
//					ansTmp++;
//					continue;
//				}
				int nextSize = edgeInfo[tmp].size();
				for (int j = 0; j < nextSize; j++) {
					int nextTmp = (int) edgeInfo[tmp].get(j);
					if (ansNode[nextTmp])
						continue;

					q.add(nextTmp);
					ansNode[nextTmp] = true;
					ansTmp++;
					cntall++;
					if (cntall == N)
						return ansTmp;
				}
			}
		
		
		}
		return ansTmp;
	}
}
