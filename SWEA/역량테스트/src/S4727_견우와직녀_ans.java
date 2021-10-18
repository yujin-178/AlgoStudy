//#include <iostream>
//#include <queue>
//using namespace std;
//int N, M;
//int map[11][11];
//int best[2][11][11];
//int direct[4][2] = { -1, 0, 1, 0, 0, 1, 0, -1 };
//struct Node {
//	int y, x;
//	int isUsedM;
//	int lev;
//};
//queue<Node> q;
//int getNextLev(int nowLev, int ny, int nx, int T)
//{
//	if (T == 1) return nowLev + 1;
//	if (T == 0) T = M;
//	return ((nowLev / T) + 1) * T;
//}
//int run()
//{
//	q.push({ 0, 0, 0, 0 });
//	while (!q.empty()) {
//		Node now = q.front();
//		q.pop();
//		for (int t = 0; t < 4; t++) {
//			int ny = now.y + direct[t][0];
//			int nx = now.x + direct[t][1];
//			if (ny < 0 || nx < 0 || ny >= N || nx >= N) continue;
//			if (now.isUsedM == 1 && map[ny][nx] == 0) continue;
//			//새로운 다리를 사용할지 결정
//			int nextUsedM = now.isUsedM;
//			if (now.isUsedM == 0 && map[ny][nx] == 0) nextUsedM = 1;
//			//절벽 연속 2개 방지
//			if (map[now.y][now.x] != 1 && map[ny][nx] != 1) continue;
//			
//			//시간 계산하기
//			int nextLev = getNextLev(now.lev, ny, nx, map[ny][nx]);
//			
//			//최적인지 확인
//			if (best[nextUsedM][ny][nx] <= nextLev) continue;
//			best[nextUsedM][ny][nx] = nextLev;
//			q.push({ ny, nx, nextUsedM, nextLev });
//		}
//	}
//	int mini = best[0][N - 1][N - 1];
//	if (mini > best[1][N - 1][N - 1]) mini = best[1][N - 1][N - 1];
//	return mini;
//}
//int main()
//{
//	ios::sync_with_stdio(0);
//	//freopen("text.txt", "r", stdin);
//	int tc;
//	cin >> tc;
//	for (int i = 1; i <= tc; i++) {
//		cin >> N >> M;
//		for (int y = 0; y < N; y++) {
//			for (int x = 0; x < N; x++) {
//				cin >> map[y][x];
//			}
//		}
//		while (!q.empty()) q.pop();
//		
//		for (int y = 0; y < 11; y++) {
//			for (int x = 0; x < 11; x++) {
//				best[0][y][x] = 21e8;
//				best[1][y][x] = 21e8;
//			}
//		}
//		int ret = run();
//		cout << "#" << i << " " << ret << endl;
//	}
//	return 0;
//}