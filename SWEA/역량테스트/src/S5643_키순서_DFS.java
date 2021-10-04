import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
/**
 * @author THKim 
 */
public class S5643_키순서_DFS {

	static int N,M,adj[][],radj[][];
	static int gtCnt,ltCnt;
	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(in.readLine());
		for (int tc = 1; tc <= TC; tc++) {
			
			N =Integer.parseInt(in.readLine()); // 학생수
			M =Integer.parseInt(in.readLine()); // 간선정보수 
			adj = new int[N+1][N+1];
			radj = new int[N+1][N+1];
			
			StringTokenizer st = null;
			for (int m = 0; m < M; m++) {
				st = new StringTokenizer(in.readLine(), " ");
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				radj[to][from] = adj[from][to] = 1; // from보다 to가 키가 크다.
			}
			
			int ans = 0;
			for (int i = 1; i <= N; i++) {
				gtCnt = ltCnt = 0;
				gtDFS(i,new boolean[N+1]); // 자신보다 큰 학생 탐색
				ltDFS(i,new boolean[N+1]);// 자신보다 작은 학생 탐색

				if(gtCnt+ltCnt == N-1) ++ans;
			}
			System.out.println("#"+tc+" "+ans);
		}
	}
	
	// 자신보다 큰  학생따라 탐색
	private static void gtDFS(int cur,  boolean[] visited) {
		visited[cur] = true;
		for (int i = 1; i <= N; i++) {
			if(!visited[i] && adj[cur][i] == 1) {
				++gtCnt;
				gtDFS(i,visited);
			}
		}
	}
	// 자신보다 작은  학생따라 탐색
	private static void ltDFS(int cur,  boolean[] visited) {
		visited[cur] = true;
		for (int i = 1; i <= N; i++) {
			if(!visited[i] && adj[i][cur] == 1) {
				++ltCnt;
				ltDFS(i,visited);
			}
		}
	}
	
}
