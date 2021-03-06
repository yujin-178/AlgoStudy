import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B1753_최단경로_3 {
//	static Vertex[] v;
//	static int V, E, s;
//	static int ans[];
//	static boolean[] chk;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("1753_input"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		int current = Integer.parseInt(br.readLine()); // 시작 위치
		Vertex[] v = new Vertex[V + 1]; // 정점 정보
		int[] ans = new int[V + 1]; // 도착지 비용 기록
		boolean[] chk = new boolean[V + 1]; // 방문 체크용
		Arrays.fill(ans, Integer.MAX_VALUE); // 일단 전부 최대치로 채워넣기
		for (int i = 0; i < E; i++) { // 간선 정보 입력
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			if (v[start] == null) // 시작 지점이 비어 있다면
				v[start] = new Vertex(start); // 생성
			if (v[end] == null) // 도착 지점이 비어 있다면
				v[end] = new Vertex(end); // 생성
			v[start].addEdge(new Edge(start, end, weight)); // 출발지에서 도착지의 이동 정보 저장
		}
		ans[current] = 0; // 시작 지점 0으로 초기화
		chk[current] = true; // 시작 지점 방문
		int min = 0, minIdx = 0; // 최소 지점 탐색을 위한 값, 인덱스
		for (int i = 1; i <= V; ++i) {
			min = Integer.MAX_VALUE; // 최소값 찾기 위해서 최대로 갱신
			minIdx = 0; // 최소값 인덱스
			for (int j = 0; j < v[current].l.size(); j++) { // 현재 위치에서 방문 가능한 지점들 탐색
				int end = v[current].l.get(j).end; // 목표지점
				int weigth = v[current].l.get(j).weight; // 가중치

				ans[end] = Math.min(ans[current] + weigth, ans[end]);
				// 목표지점의 비용보다 현재 위치에서 목표지점으로 가는 비용의 합이 적을 경우 업데이트

			} // 방문이 끝나고
			for(int j = 1; j<=V ;j++) {
				if (!chk[j] && min > ans[j]) { // 방문한 적이 없는 도시 중 가장 비용이 적은 도시
					min = ans[j]; // 최소 비용 갱신
					minIdx = j; // 도시 위치 생신
				}
			}
			
			
			chk[minIdx] = true; // 최소 비용 도시 방문
			current = minIdx; // 다음 방문을 위한 현재 위치 업데이트
			if (minIdx == 0) // 만약 방문할 곳이 없다면 탐색 종료
				break;
		}
		for (int i = 1; i <= V; i++) { // 1번 도시 부터
			if (ans[i] == Integer.MAX_VALUE) // 최대값이면
				System.out.println("INF"); // 무한 출력
			else
				System.out.println(ans[i]); // 아니면 목표지점의 비용 출력
		}
	}

	static class Vertex {
		int num;
		ArrayList<Edge> l;

		public Vertex(int num) {
			super();
			this.num = num;
			l = new ArrayList<>();
		}

		void addEdge(Edge e) {
			this.l.add(e);
		}

	}

	static class Edge {
		int start;
		int end;
		int weight;

		public Edge(int start, int end, int weight) {
			super();
			this.start = start;
			this.end = end;
			this.weight = weight;
		}

	}
}
