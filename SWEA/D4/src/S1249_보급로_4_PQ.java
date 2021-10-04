import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;
/**
 * @author THKim 
 */
public class S1249_보급로_4_PQ {

	static int N,map[][];
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	static final int INF = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(in.readLine());
		for (int tc = 1; tc <= TC; tc++) {
			
			N =Integer.parseInt(in.readLine()); //배열 크기
			map = new int[N][N];
			
			for (int i = 0; i < N; i++) {
				char[] ch = in.readLine().toCharArray();
				for (int j = 0; j < N; j++) {
					map[i][j] = ch[j] - '0';
				}
			}
			
			System.out.println("#"+tc+" "+dijkstra(0,0));
		}
	}

	private static int dijkstra(int startR, int startC) {

		boolean[][] visited = new boolean[N][N];
		int[][] minTime = new int[N][N];
		
		// 최소값이 갱신되도록 큰값으로 초기화
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				minTime[i][j] = INF;
			}
		}
		
		PriorityQueue<int[]> pQueue = new PriorityQueue<int[]>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[2] - o2[2];
			}
		});
		
		
		minTime[startR][startC] = 0;
		pQueue.offer(new int[] {startR,startC,minTime[startR][startC]});
		
		
		int r=0,c=0,minCost=0,nr,nc;
		while (true) {
			
			// step1
			int[] cur = pQueue.poll();
			r = cur[0];
			c = cur[1];
			minCost =cur[2];
			
			if(visited[r][c]) continue;
				
			visited[r][c] = true;
			if(r==N-1 && c==N-1) return minCost;
			
			// step2 : step1에서 선택된 정점을 경유지로 해서 인접정점 따져보기
			// 이 문제에서는 인접정점이 4방 정점
			for (int d = 0; d < 4; d++) {
				nr = r + dr[d];
				nc = c + dc[d];
				
				if(nr>=0 && nr<N && nc>=0 && nc<N && !visited[nr][nc] 
						&& minTime[nr][nc] > minCost + map[nr][nc] ) {
					minTime[nr][nc] = minCost + map[nr][nc];
					pQueue.offer(new int[] {nr,nc,minTime[nr][nc]});
				}
			}
			
		}
		
	}
	
}






