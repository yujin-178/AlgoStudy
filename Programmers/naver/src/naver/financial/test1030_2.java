package naver.financial;

import java.util.Arrays;

/**
  * @FileName : test1030_2.java
  * @Date : 2021. 10. 30. 
  * @작성자 : KimYuJin
  * @특이점 : 점프와 일치한만큼 0인 칸을 지나가야 숫자를 채울 수 있다.
  */
public class test1030_2 {
	public static void main(String[] args) {
		int n = 4;
		int jump = 1;
		System.out.println(Arrays.toString(solution(n, jump)));
	}

	static int[] dr = { 0, 1, 0, -1 };
	static int[] dc = { 1, 0, -1, 0 };

	static public int[] solution(int n, int jump) {
		int[][] map = new int[n][n];
		map[0][0] = 1;
		int cnt = 1;
		int nr = 0;
		int nc = 0;
		int jumpCnt = 1;
		int dir = 0;
		while (cnt != n * n) {
//			System.out.println(nr + ", " + nc);

			if (map[nr][nc] == 0) {
				if (jumpCnt == jump) {
					map[nr][nc] = ++cnt;
					jumpCnt = 1;
//					System.out.println(cnt);
				} else
					++jumpCnt;
			}
			if(cnt == n*n)
                break;
			if (n % 2 == 1) {
				if (nr == n / 2 && nc == n / 2) {
					nr = 0;
					nc = 0;
					dir = 0;
				}
				if ((nr > n / 2 && (nr == nc || nr + nc == n - 1)) || (nr < n / 2 && nr + nc == n - 1)
						|| (nr <= n / 2 && nr == nc + 1))
					dir = ++dir % 4;
				
			} else {
				if (nr == n / 2 && nc == n / 2 - 1) {
					nr = 0;
					nc = 0;
					dir = 0;
				}
				if ((nr >= n / 2 && (nr == nc || nr + nc == n - 1)) || (nr < n / 2 && nr + nc == n - 1)
						|| (nr < n / 2 && nr == nc + 1))
					dir = ++dir % 4;
			}
	
				nr += dr[dir];
				nc += dc[dir];


		}
//		for (int r = 0; r < n; r++) {
//			for (int c = 0; c < n; c++) {
//				System.out.print(map[r][c] + " ");
//			}
//			System.out.println();
//		}

		int[] answer = { nr + 1, nc + 1 };
		return answer;
	}
}
