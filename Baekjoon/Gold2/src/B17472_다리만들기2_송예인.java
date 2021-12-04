
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class B17472_다리만들기2_송예인 {
	static int[] dx = { 0, 0, 1, -1 };
	static int[] dy = { 1, -1, 0, 0 };
	static int[][] map;
	static char[][] char_map;
	static boolean[][] check;
	static int N, M, island;

	static class Point {
		int x;
		int y;

		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static class Node implements Comparable<Node> {
		char ch;
		int dis;

		Node(char ch, int dis) {
			this.ch = ch;
			this.dis = dis;
		}

		@Override
		public int compareTo(Node o) {
			return this.dis - o.dis;
		}

	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		map = new int[N][M];
		char_map = new char[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				map[i][j] = sc.nextInt();
			}
		} // input end

		// 정점과(섬) 간선(섬-섬 최소 비용) 구하기

		// 1.모든 섬을 a,b,c,d 로 나타낸다.
		// 2.각 섬에대해 인접리스트를 완성한다. 이때, 같은 섬까지의 거리가 더 가까운 경우가 발견되었다면 바꾼다.
		// 3.프림 알고리즘을 이용하여 최소비용 거리를 구할 수 있다.

		// 1.
		char ch = 'A';
		check = new boolean[N][M];
		island = 0;// 섬의 개수
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 1 && !check[i][j]) {
					bfs(i, j, ch);// bfs 를 돌리면 섬 영역이 표시됨.
					ch = (char) (ch + 1);
					island++;
				}
				if (map[i][j] == 0)
					char_map[i][j] = 'a';
			}
		}

		// 같은 섬은 같은 알파벳으로 표시되는것 확인
//		for(int i=0;i<N;i++) {
//			for(int j=0;j<M;j++) {
//				System.out.print(char_map[i][j]);
//			}
//			System.out.println();
//		}

		// 2.섬의 개수만큼 인접리스트를 만들고, 섬과섬 사이의 (섬,거리) 저장
		// ArrayList<Character>[] list = new ArrayList[island];
		char som = 'A';
		ArrayList<Node>[] list = new ArrayList[island];

		for (int i = 0; i < island; i++) {
			list[i] = new ArrayList<>();
			// i번쨰 섬을 큐에 넣고 모든 섬과의 거리를...
			for (int x = 0; x < N; x++) {
				for (int y = 0; y < M; y++) {
					if (char_map[x][y] == som) {
						// 현재 x,y 로 동, 서 남 북 방향으로 만날 수 있는 섬이 있는지 확인
						for (int t = 0; t < 4; t++) {
							int xx = x;
							int yy = y;
							int distance = 0;
							while (true) {
								xx += dx[t];
								yy += dy[t];// 한 방향에 대한 증가가 일어난다.
								if (xx < 0 || xx >= N || yy < 0 || yy >= M || char_map[xx][yy] == som)
									break;
								if (char_map[xx][yy] != 'a') {// 어떤 섬을 발견함!!
									if (distance > 1) {
										list[i].add(new Node(char_map[xx][yy], distance));
									}
									break;
								}
								distance++;
							}
						}

					}
				}
			}
			som = (char) (som + 1);
		}

		// 리스트에 연결된 섬 집합 확인
//		for(int i=0;i<island;i++) {
//			System.out.println(i+" 번쨰 섬과 연결된 섬, 거리");
//			for(int j=0;j<list[i].size();j++) {
//				System.out.println(list[i].get(j).ch +" "+list[i].get(j).dis);
//			}
//		}

		// 3. 이 리스트를 이용해 프림알고리즘 사용
		// 출발지는 list[i]
		boolean[] check_som = new boolean[island];
		PriorityQueue<Node> pq = new PriorityQueue();
		pq.add(new Node('A', 0));
		int cnt = 0;// 섬 개수만큼 선택되면 끝
		int ans = 0;
		while (!pq.isEmpty()) {
			Node p = pq.poll();// 최소비용가진애뺌
			if (check_som[(int) (p.ch - 65)])
				continue;
			check_som[(int) (p.ch - 65)] = true;
			ans += p.dis;
			cnt++;// 섬하나 선택 됨
			if (cnt == island)
				break;

			// 이웃 노드 집어넣기
			for (int i = 0; i < list[(int) (p.ch - 65)].size(); i++) {
				if (check_som[(int) (list[(int) (p.ch - 65)].get(i).ch - 65)])
					continue;
				pq.add(new Node(list[(int) (p.ch - 65)].get(i).ch, list[(int) (p.ch - 65)].get(i).dis));
			}

		}

		if (ans == 0)
			ans = -1;
		System.out.println(ans);

	}

	// 각 섬의 영역표시를 위
	private static void bfs(int i, int j, char ch) {
		check[i][j] = true;
		char_map[i][j] = ch;
		Queue<Point> q = new LinkedList<>();
		q.add(new Point(i, j));
		while (!q.isEmpty()) {
			Point p = q.poll();
			for (int t = 0; t < 4; t++) {
				int x = p.x + dx[t];
				int y = p.y + dy[t];
				if (x >= 0 && x < N && y >= 0 && y < M && !check[x][y] && map[x][y] == 1) {
					check[x][y] = true;
					char_map[x][y] = ch;// 같은 영역표시
					q.add(new Point(x, y));
				}
			}
		}

	}

}