

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * @author comkkyu
 * @date 21. 10. 1
 *
 * 백준 17143 - 낚시왕
 * https://www.acmicpc.net/problem/17143
 *
 * 시뮬레이션 문제이다.
 * 다만 시간제한이 엄청 빡센문제라서 좀더 고민을 해볼 필요가 있다.
 * 일단 문제접근의 경우 상어의크기가 중복되지않는 다는 조건이 있었기 때문에 큐로 현재 상어 정보에 대해서 관리하려다
 * HashMap 을 이용해서 관리하도록 했다.
 * 상어가 이동하면서 바뀌는 2차원배열에 대해서 기존배열에 영향을 주지않기위해 새로 배열을 만들어서 이동을 진행했고
 * 도달하는 칸에 이미 상어가 존재하는 경우에는 더 크기가 큰 상어의 size 로 해당 칸의 값을 업데이트하고 크기가 작은 상어의 무게를 리스트에 추가해주었다. (나중에 HashMap 에서 제거하기 위해)
 * 최종적으로 이동이 완료된 후에는 기존 map 배열을 이동이 완료된 임시배열의 값으로 복사해주고 크기가 작아서 경쟁에서 밀려난 상어 리스트에 해당하는 무게들을
 * HashMap 에서 제거해줌으로써 현재 남아있는 상어정보를 유지하도록 했다.
 *
 */
public class B17143_낚시왕_한선규 {

    static class Shark {
        int row; // 상어 행 좌표
        int col; // 상어 열 좌표
        int speed; // 상어 속력
        int dir; // 상어 방향
        int size; // 상어 크기

        public Shark(int r, int c, int s, int d, int z) {
            this.row = r;
            this.col = c;
            this.speed = s;
            this.dir = d;
            this.size = z;
        }
    }

    private static Map<Integer, Shark> sharks; // 현재 바다에 남아있는 상어 목록
    private static int[][] map;
    private static int R, C, M, totalSize;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[R+1][C+1];
        sharks = new HashMap<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());

            sharks.put(z, new Shark(r, c, s, d, z));
            map[r][c] = z; // 상어가 존재하면 상어의 크기, 존재하지 않는다면 0
        }

        for (int i = 1; i <= C; i++) {
            catchShark(i);
            moveShark();
        }

        System.out.println(totalSize);
    }

    private static void moveShark() { // 상어 이동 메서드
        ArrayList<Integer> smallSharks = new ArrayList<>(); // 경쟁에서 지는 크기가 작은 상어들의 정보를 담을 리스트
        int[][] d = { {0, 0}, {-1, 0}, {1, 0}, {0, 1}, {0, -1} }; // 위, 아래, 오른쪽, 왼쪽 한칸 이동 배열
        int[][] temp = new int[R+1][C+1]; // 이동한 상어들의 좌표를 계산하기위한 임시 배열 (잡아 먹히는 경우도 계산해야 하기 때문)

        for (Map.Entry<Integer, Shark> entry : sharks.entrySet()) { // 현재 존재하는 모든 상어에 대해서 이동을 시키자!
            Shark shark = entry.getValue();
            map[shark.row][shark.col] = 0; // 현재 상어는 이동할 것이므로 일단 0으로 초기화

            // 상어가 왕복이동을 해서 제자리로 돌아오는 경우를 계산해서 해당 경우로 나눠서 나머지 만큼으로 속력값을 수정
            // 연산횟수를 최소화 시켜서 시간적인 측면에서 효율적인 코드가 완성된다.
            int speed = shark.speed;
            if (shark.dir == 1 || shark.dir == 2)
                speed %= (R - 1) * 2;
            else
                speed %= (C - 1) * 2;

            for (int i = 0; i < speed; i++) { // 속력만큼 반복하면 총 speed 만큼 칸을 이동하게 된다.
                // 방향을 바꿔야 하는 4가지 경우
                if (shark.dir == 1 && shark.row == 1) { // 상어가 위로 가야하는데 첫번째 행에 있을 경우
                    shark.dir = 2; // 아래로 방향을 바꿔준다.
                } else if (shark.dir == 2 && shark.row == R) { // 상어가 아래로 가야하는데 마지막 행에 있을 경우
                    shark.dir = 1; // 위로 방향을 바꿔준다.
                } else if (shark.dir == 3 && shark.col == C) { // 상어가 오른쪽으로 가야하는데 마지막 열에 있을 경우
                    shark.dir = 4; // 왼쪽으로 방향을 바꿔준다.
                } else if (shark.dir == 4 && shark.col == 1) { // 상어가 왼쪽으로 가야하는데 첫번째 열에 있을 경우
                    shark.dir = 3; // 오른쪽으로 방향을 바꿔준다.
                }

                // 완성된 방향으로 한칸 이동
                shark.row += d[shark.dir][0];
                shark.col += d[shark.dir][1];
            }

            if (temp[shark.row][shark.col] == 0) { // 아직 상어가 존재하지 않고 빈칸이라면
                temp[shark.row][shark.col] = shark.size; // 상어의 크기로 값을 할당
            }
//            else { // 상어가 존재한다면 크기를 비교해야 함.
//                int smallShark = Math.min(shark.size, temp[shark.row][shark.col]);
//                smallSharks.add(smallShark);
//            }
            else if (shark.size > temp[shark.row][shark.col]) { // 지금 들어오는 상어가 더 크다면
                smallSharks.add(temp[shark.row][shark.col]); // 기존 상어를 잡어먹기위해서 리스트에 추가하고
                temp[shark.row][shark.col] = shark.size; // 새로운 상어크기로 업데이트
            }
            else if (shark.size < temp[shark.row][shark.col]) { // 기존에 들어있던 상어가 더 크다면
                smallSharks.add(shark.size); // 새로 들어온 상어를 잡아먹기위해서 리스트에 추가
            }
        }

        // 모든 상어들에 대해서 이동 및 서열정리(?)가 끝났다면 완성된 temp 배열의 정보를 map 배열에 복사
        for (int i = 1; i <= R; i++) {
            System.arraycopy(temp[i], 1, map[i], 1, C);
        }

        // 살아남은 상어들의 목록을 최신화
        Iterator<Integer> iter = smallSharks.iterator();
        while (iter.hasNext()) {
            sharks.remove(iter.next());
        }
    }

    private static void catchShark(int col) { // 상어 낚시 메서드
        for (int i = 1; i <= R; i++) { // 땅에서 가장 가까운 행부터 탐색
            if (map[i][col] > 0) { // 상어가 존재한다면
                totalSize += map[i][col]; // 상어 크기 누적 합
                sharks.remove(map[i][col]); // 낚시왕에게 그만 잡혀버려서...사망
                break;
            }
        }
    }
}
