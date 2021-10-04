

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class B16234_인구이동_은이님 {
    static int N, L, R;
    static int[][] map;
    static int[] parent;
    static Map<Integer, ArrayList> hashMap = new HashMap<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        L = sc.nextInt();
        R = sc.nextInt();
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = sc.nextInt();
            }
        }
        System.out.print(movePopulation());
    }

    private static int find(int a) {
        if (a == parent[a])
            return a; // 자신이 대표자
        return parent[a] = find(parent[a]); // 자신이 속한 집합의 대표자를 자신의 부모로 변경한다. : path compression
    }

    private static void make() {
        parent = new int[N * N];
        // 모든 원소를 자신을 대표로 만듦.
        for (int i = 0; i < N * N; i++) {
            parent[i] = i;
        }
    }

    // 두 원소를 하나의 집합으로 합치기
    private static boolean union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);

        if (aRoot == bRoot)
            return false; // 이미 같은 집합으로 합치지 않는다.
        parent[bRoot] = aRoot;
        return true;
    }


    private static int movePopulation() {
        int dayCnt = 0;

        bfs();
        while (hashMap.size() < N * N) {
            dayCnt++;

            for (Integer key : hashMap.keySet()) {
                ArrayList<Integer> que = hashMap.get(key);
                int queSize = que.size();
                if (queSize <= 1) continue;
                int sum = 0;
                for (int i = 0; i < queSize; i++) {
                    int tmp = que.get(i);
                    sum += map[tmp / N][tmp % N];
                }

                int people = sum / queSize;

                for (int i = 0; i < queSize; i++) {
                    int tmp = que.get(i);
                    map[tmp / N][tmp % N] = people;
                }
            }
            bfs();
        }
        return dayCnt;
    }

    static void bfs() {
        hashMap.clear();
        make();

        boolean[][] visited = new boolean[N][N];
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < 4; k++) {
                    int tx = i + dx[k];
                    int ty = j + dy[k];

                    if (tx < 0 || tx >= N || ty < 0 || ty >= N) continue;
                    if (visited[tx][ty]) {
                        continue;
                    }
                    int dif = Math.abs(map[i][j] - map[tx][ty]);
                    if (dif >= L && dif <= R ) {
                        union(i * N + j, tx * N + ty);
                        visited[i][j]=true;
                        visited[tx][ty]=true;
                    }
                }
            }
        }

        int p = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                p = find(i * N + j);
                if (!hashMap.containsKey(p)) hashMap.put(p, new ArrayList<>());
                hashMap.get(p).add(i * N + j);
            }
        }
    }
}