import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class Baek17135_캐슬디펜스_3 {
	static int N, M, D;
	static LinkedList<Enemy> e = new LinkedList<Enemy>();
	static Archer[] a = new Archer[3];
	static int maxCnt = 0;
	static int[][] map;
	static boolean[][] chk;
	static int[] dc = { -1, 0, 1, 0 };
	static int[] dr = { 0, -1, 0, 1 };
	static Queue<SearchMap> q = new LinkedList<>();

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("C:/CodingStudy/Baekjoon/Gold4/17135_input"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		map = new int[N][M];

		for (int r = 0; r < N; r++) { // 적 정보 저장 !!!! 적을 하단 좌측 순서로 저장하면 최단거리 및 왼쪽 정보 저장됨 코딩중 깨달음
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < M; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				if (map[r][c] == 1) {
					e.add(new Enemy(r, c));
				}
			}
		}

		combArcher(0, 0);
		System.out.println(maxCnt);
	}

	static void showMap(int kill, LinkedList<Enemy> thisE) {
		int[][] map = new int[N][M];
		for (int i = 0; i < thisE.size(); i++) {
			map[thisE.get(i).r][thisE.get(i).c] = 1;
		}
		System.out.println("---------------------------");
		System.out.println("kill 수 : " + kill);
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				System.out.print(map[r][c] + " ");
			}
			System.out.println();
		}
	}

	static void combArcher(int cnt, int start) {
		if (cnt == 3) { // 궁수 조합생성 시뮬 실행

			int tmp = killEnemy();

			if (maxCnt < tmp)
				maxCnt = tmp; // 최대 킬 수 갱신
			return;
		}
		for (int i = start; i < M; i++) {
			a[cnt] = new Archer(i);
			combArcher(cnt + 1, i + 1);
		}
	}

	static int killEnemy() {
		int cnt = 0; // 매 턴 킬수
		
		LinkedList<Enemy> thisE = new LinkedList<Enemy>(); // 계획대로 가자...
		for (int i = 0; i < e.size(); i++) {
			thisE.push(new Enemy(e.get(i).r, e.get(i).c));
		}
		
		int[][] tmpMap = new int[N][M];
		
		for (int r = 0; r < N; r++) { // 적 정보 저장 !!!! 적을 하단 좌측 순서로 저장하면 최단거리 및 왼쪽 정보 저장됨 코딩중 깨달음
			for (int c = 0; c < M; c++) {
				tmpMap[r][c] = map[r][c];
			}
		}
		
		int n = 0;
		
		while (!thisE.isEmpty()) {// 적 죽이는 시뮬레이션 시작
			int[] killIdx = new int[3];
			chk = new boolean[N][M];
			for (int aIdx = 2; aIdx >= 0; aIdx--) { // step1. 궁수가 적 죽이기
				
				int dIdx = 0;
				while(dIdx < D) {
					for(int d= 0;d<4;d++) {
						
					}
				}
				
				
				for (int eIdx = 0; eIdx < thisE.size(); eIdx++) {
					int tmpc = Math.abs(thisE.get(eIdx).c - a[aIdx].c);
					int tmpr = Math.abs(thisE.get(eIdx).r - a[aIdx].r);
					if ((tmpc + tmpr) <= D) { // aIdx 번째 궁수가 eIdx 번째 적을 죽임
						thisE.remove(eIdx); // 거리내의 적 삭제
						++cnt; // 킬수 증가
						break; // 한명 죽이면 탐색을 멈춘다. 못 죽이면 탐색만 지나감
					}
				}
			} // end 적 죽이기

			for (int eIdx = 0; eIdx < thisE.size(); eIdx++) {// step2. 적 이동 및 맵 벗어나면 죽임
				if (thisE.size() == 0)
					break;
				else if ((thisE.get(eIdx).r + 1) == N) {
					thisE.remove(eIdx--); // 죽이고 나면 인덱스를 1 줄여서 해당 인덱스 부터 다시 탐색 하도록 해야한다.
				} else {// 아니면 거리 1 증가
					thisE.get(eIdx).r += 1;
				}
			} // end 적 이동
//			showMap(cnt, thisE);
		} // end kill enemy

		return cnt;
	}

	static class Enemy { // 적 위치
		int r;
		int c;

		public Enemy(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}

	}

	static class SearchMap { // 적 위치
		int r;
		int c;

		public SearchMap(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}

	}

	static class Archer { // 궁수 위치
		int r = N;
		int c;

		public Archer(int c) {
			super();
			this.c = c;
		}
	}
}
