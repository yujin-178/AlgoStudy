import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class B16235_나무재테크 {
	static int N, M, K;
	static int[][] map, add;
	static PriorityQueue<Integer>[][] treeMap;

	static int[] dr = { 0, -1, -1, -1, 0, 1, 1, 1 };
	static int[] dc = { -1, -1, 0, 1, 1, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 지도 크기
		M = Integer.parseInt(st.nextToken()); // 초기 나무 개수
		K = Integer.parseInt(st.nextToken()); // 목표 년도

		map = new int[N][N];
		add = new int[N][N];
		treeMap = new PriorityQueue[N][N];
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				add[r][c] = Integer.parseInt(st.nextToken());
				map[r][c] = 5;
				treeMap[r][c] = new PriorityQueue<Integer>();
			}
		}

		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			treeMap[r - 1][c - 1].add(a);
		}

		System.out.println(cntTree());

	}

	static int cntTree() {
		int ans = M;
		int year = 0;
		Queue<Integer>[][] tmpMap;
		tmpMap = new Queue[N][N];
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				tmpMap[r][c] = new LinkedList<Integer>();
			}
		}
		while (year != K) {

			// 봄 - 나무가 나이를 먹고 땅의 양분을 흡수한다.
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					while (!treeMap[r][c].isEmpty()) {
						int tmp = treeMap[r][c].poll();
						if (tmp <= map[r][c]) {
							map[r][c] -= tmp;
							tmpMap[r][c].offer(tmp + 1); // 나이 먹는다.
						} else { // 한번 양분보다 나이가 많다면 그 뒤의 나무들도 나이가 양분보다 더 많을거다
							treeMap[r][c].add(tmp);
							break;
						}
					}
				}
			} // 봄 끝~

			// 여름 - 죽은 나무는 양분으로 변한다.
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					while (!treeMap[r][c].isEmpty()) {
						map[r][c] += treeMap[r][c].poll() / 2;
						ans--;
					}
				}
			}

			// 가을 나무는 증식한다.
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					while (!tmpMap[r][c].isEmpty()) {
						int tmp = tmpMap[r][c].poll();
						if (tmp % 5 == 0) {
							ans += createTree(r, c);
						}
						treeMap[r][c].add(tmp);
					}
				}
			}

			// 겨울 양분을 추가한다.
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					map[r][c] += add[r][c];
				}
			}
			year++;
		}

		return ans;
	}

	static int createTree(int r, int c) {
		int cnt = 0;
		for (int d = 0; d < 8; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			if (nr < 0 || nc < 0 || nr >= N || nc >= N)
				continue;
			treeMap[nr][nc].add(1);
			cnt++;
		}
		return cnt;
	}

}
