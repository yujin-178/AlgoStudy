

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//public class Solution {
public class Test1873_1 {
	static int H;
	static int W;
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { -1, 0, 1, 0 };

	public static void main(String[] args) throws NumberFormatException, IOException { // main
		System.setIn(new FileInputStream("C:/CodingStudy/SWEA/D3/Test1873_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.valueOf(br.readLine());

		for (int tc = 1; tc <= T; tc++) { // ------------------------ tc 시작
			st = new StringTokenizer(br.readLine());
			H = Integer.valueOf(st.nextToken());
			W = Integer.valueOf(st.nextToken());
			char[][] map = new char[H][W];
			int[] tankPos = new int[3];
//			System.out.println("H:" + H + " " + "W:" + W);
			for (int c = 0; c < H; c++) { // ------------------------ 맵 입력 및 탱크 상태 확인
				String tmpMap = br.readLine();
				for (int r = 0; r < W; r++) {
					map[c][r] = tmpMap.charAt(r);
					switch (map[c][r]) {
					case '^':
						tankPos[2] = 0;
						tankPos[0] = c;
						tankPos[1] = r;
						map[c][r] = '.';
						break;
					case '>':
						tankPos[2] = 1;
						tankPos[0] = c;
						tankPos[1] = r;
						map[c][r] = '.';
						break;
					case 'v':
						tankPos[2] = 2;
						tankPos[0] = c;
						tankPos[1] = r;
						map[c][r] = '.';
						break;
					case '<':
						tankPos[2] = 3;
						tankPos[0] = c;
						tankPos[1] = r;
						map[c][r] = '.';
						break;
					}
				}
			} // ------------------------------------------------------end 맵 입력

			int comNum = Integer.valueOf(br.readLine());
			String comLine = br.readLine();
//			System.out.println(comLine);
			for (int comIdx = 0; comIdx < comNum; comIdx++) { // ----------command 돌리기

				switch (comLine.charAt(comIdx)) {
				case 'U':
					tankMove(tankPos, 0, map);
					break;
				case 'R':
					tankMove(tankPos, 1, map);
					break;
				case 'D':
					tankMove(tankPos, 2, map);
					break;
				case 'L':
					tankMove(tankPos, 3, map);
					break;
				case 'S':
					tankShot(tankPos, map);
					break;
				}
				
//				print(tankPos,map);
//				System.out.println(comLine.charAt(comIdx));

			} // --------------------------------------------------------command 종료
			System.out.print("#"+tc+" ");
			print(tankPos,map);
			
		} // ------------------------------------------------------------end tc

	}// end main
	
	public static void print(int[] pos, char[][] map) {
		for (int c = 0; c < H; c++) { // ------------------------ 맵 출력 탱크 추가 해야함
			for (int r = 0; r < W; r++) {
				if(c==pos[0] && r==pos[1]) {
					switch (pos[2]) {
					case 0:
						System.out.print('^');
						break;
					case 1:
						System.out.print('>');
						break;
					case 2:
						System.out.print('v');
						break;
					case 3:
						System.out.print('<');
						break;
					}
				}
				else
					System.out.print(map[c][r]);
			}
			System.out.println();
		} // ------------------------------------------------------end 맵 출력

	}
	
	public static void tankShot(int[] pos, char[][] map) {
		int sc = (pos[0] );
		int sr = (pos[1]);
		while (true) {
			sc += dy[pos[2]];
			sr += dx[pos[2]];
//			System.out.println("sc:" + sc + " " + "sr:" + sr);
			if ((sc < 0) || (sc >= H) || (sr < 0) || (sr >= W)) {
				return;
			} else if (map[sc][sr] == '*') {
				map[sc][sr] = '.';
				return;
			} else if (map[sc][sr] == '#') {
				return;
			}
		}

	}

	public static void tankMove(int[] pos, int dir, char[][] map) {
		pos[2] = dir;
		int newC = (pos[0] + dy[dir]);
		int newR = (pos[1] + dx[dir]);
		if ((newC >= 0) && (newC < H) && (newR >= 0) && (newR < W) && (map[newC][newR] == '.')) {
			pos[0] += dy[dir];
			pos[1] += dx[dir];
		}
		else {
			
		}
	}

}
