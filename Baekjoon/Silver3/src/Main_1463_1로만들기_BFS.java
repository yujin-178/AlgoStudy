

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main_1463_1로만들기_BFS {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int X = sc.nextInt();
		
		System.out.println(bfs(X));
	}
	
	static int bfs(int X) {
		boolean[] visit = new boolean[X+1];
		Queue<Integer> queue = new LinkedList<Integer>();
		
		queue.add(X);
		visit[X]=true;
		int dist = 0;
		
		while(!queue.isEmpty()) {
			int size = queue.size();
			
			for(int s=0; s<size; s++) {
				int num = queue.poll();
				
				if(num==1)
					return dist;
				
				if(num%3==0 && !visit[num/3]) {
					queue.add(num/3);
					visit[num/3]=true;					
				}
				if(num%2==0 && !visit[num/2]) {
					queue.add(num/2);
					visit[num/2]=true;
				}
				if(!visit[num-1]) {
					queue.add(num-1);
					visit[num-1]=true;
				}
			}
			dist++;
		}
		return -1;
	}
}






