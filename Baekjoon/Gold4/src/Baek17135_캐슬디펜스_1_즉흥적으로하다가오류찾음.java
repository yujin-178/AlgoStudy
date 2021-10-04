import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Baek17135_캐슬디펜스_1_즉흥적으로하다가오류찾음 {
	static int N, M, D;
	static LinkedList<Enemy> e = new LinkedList<Enemy>();
	static Archer[] a = new Archer[3];

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("C:/CodingStudy/Baekjoon/Gold4/17135_input"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		// input

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());

		for (int r = N - 1; r >= 0; r--) { // 적 정보 저장 !!!! 적을 하단 좌측 순서로 저장하면 최단거리 및 왼쪽 정보 저장됨 코딩중 깨달음
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < M; c++) {
				if (st.nextElement() == "1") {
					e.add(new Enemy(r, c));
				}
			}
		} // end enemy info

		// end input

	}

	static int killEnemy() {
		int cnt = 0; // 매 턴 킬수

//		LinkedList<Enemy> thisE = new LinkedList<Enemy>(); // 이거 코드 짜다가 생각남
//		for(int i = 0; i<e.size();i++) { // 조합마다 테스트 할꺼니까 저장된 적 정보 가져옴
//			thisE.add(e.get(i));
//		}이렇게 할 필요가 없네??

		LinkedList<SimulEnemy> thisE = new LinkedList<>(); // 이거 코드 짜다가 생각남

		for (int eIdx = 0; eIdx < e.size(); eIdx++) { // 이부분 코드 짜가다 생각남 적의 거리에 대해 생각하고 나옴
			int ec = e.get(eIdx).c;
			int er = e.get(eIdx).r;
			int[] tmp = { Math.abs(a[0].c - ec) + Math.abs(a[0].r - er), Math.abs(a[1].c - ec) + Math.abs(a[1].r - er), Math.abs(a[2].c - ec) + Math.abs(a[2].r - er) };
			thisE.add(new SimulEnemy(tmp));
		}
		
		while(thisE.size() != 0) {// 적 죽이는 시뮬레이션 시작
			for(int aIdx = 0;aIdx < 3;aIdx++) { // step1. 궁수가 적 죽이기
				for(int eIdx = 0; eIdx < e.size(); eIdx++) {
					if(thisE.get(eIdx).archerDis[aIdx] < D) { // aIdx 번째 궁수가 eIdx 번째 적을 죽임
						thisE.remove(eIdx); // 거리내의 적 삭제
						++cnt; // 킬수 증가
						break; // 한명 죽이면 탐색을 멈춘다. 못 죽이면 탐색만 지나감
					}
				}
			} // end 적 죽이기
			
			for(int aIdx = 0;aIdx < 3;aIdx++) { //  step2. 적 이동 및 맵 벗어나면 죽임
				for(int eIdx = 0; eIdx < e.size(); eIdx++) {
					if(--(thisE.get(eIdx).archerDis[aIdx]) == 0) {
						thisE.remove(eIdx);
						--eIdx; // 죽이고 나면 인덱스를 1 줄여서 해당 인덱스 부터 다시 탐색 하도록 해야한다.
					}
				}
			}
			
			
			
		}
		

		return cnt;
	}

	static class SimulEnemy {
		int[] archerDis = new int[3];

		public SimulEnemy(int[] archerDis) {
			super();
			this.archerDis = archerDis;
		}

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

	static class Archer { // 궁수 위치
		int r = N;
		int c;
	}
}
