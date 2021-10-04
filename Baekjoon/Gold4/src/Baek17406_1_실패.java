import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

//public class Main {
public class Baek17406_1_실패 {
	static int N, M, K;
	static int[][] num;
	static int[][] com;
	static int[][] command;
	static boolean[] chk;
	static ArrayList<LinkedList> a = new ArrayList<>();;
	static ArrayList b = new ArrayList<>();
	static int min = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("C:/CodingStudy/Baekjoon/Gold4/17406_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		com = new int[K][3];
		command = new int[K][3];
		num = new int[N][M];
		chk = new boolean[K];
		
		for (int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine());
			for (int m = 0; m < M; m++) {
				num[n][m] = Integer.parseInt(st.nextToken());
			}
		} // end 초기 num, ans 입력

		for (int idx = 0; idx < K; idx++) {
			st = new StringTokenizer(br.readLine());
			for (int row = 0; row < 3; row++) {
				command[idx][row] = Integer.parseInt(st.nextToken());
			}
		} // end command 입력
		int[][] test = new int[K][3];
		makeCommand(0,test);

		for (int cIdx = 0; cIdx < b.size(); cIdx++) {
			com =(int[][]) b.get(cIdx);
			for(int aaa = 0;aaa<com.length;aaa++) {
				System.out.println(Arrays.toString(com[aaa]));
			}
			System.out.println();
			for (int idx = 0; idx < K; idx++) {
				for (int i = 0; i < com[idx][2]; i++) {
					a.add(new LinkedList<Integer>());
				} // end 각 테두리를 관리할 링크드 리스트 생성
				readLine(0, idx); // 테두리 별로 링크드 리스트에 넣기
			}

			for (int idx = 0; idx < N; idx++) {
				int sum = 0;
				for (int row = 0; row < M; row++) {
					sum += num[idx][row];
				}
				if (sum < min)
					min = sum;
			}
			System.out.println(min);
		}
		
//
		br.close();

	}// end main

	public static void makeCommand(int idx,int[][] test) {
		if (idx == K) {
			b.add(test);
			
			for(int aaa = 0;aaa<com.length;aaa++) {
				System.out.println(Arrays.toString(test[aaa]));
			}
			System.out.println();
			return;
		}

		for (int i = 0; i < K; i++) {
			if (chk[i])
				continue;
			test[idx] = command[i];
			chk[i] = true;
			makeCommand(idx + 1,test);
			chk[i] = false;

		}

	}

	public static void readLine(int depth, int idx) {// 테두리 별로 링크드 리스트에 넣기

		int rs = com[idx][1] - com[idx][2] - 1;
		int cs = com[idx][0] - com[idx][2] - 1;
		int re = com[idx][1] + com[idx][2] - 1;
		int ce = com[idx][0] + com[idx][2] - 1;

		for (int row = rs + depth; row <= re - 1 - depth; row++) {
			a.get(depth).add(num[cs + depth][row]);
		}
		for (int col = cs + depth; col <= ce - 1 - depth; col++) {
			a.get(depth).add(num[col][re - depth]);
		}
		for (int row = re - depth; row > rs + depth; row--) {
			a.get(depth).add(num[ce - depth][row]);
		}
		for (int col = ce - depth; col > cs + depth; col--) {
			a.get(depth).add(num[col][rs + depth]);
		}

		a.get(depth).offerFirst(a.get(depth).pollLast()); // 1번 회전

		for (int row = rs + depth; row <= re - 1 - depth; row++) {
			num[cs + depth][row] = (int) a.get(depth).pop();
		}
		for (int col = cs + depth; col <= ce - 1 - depth; col++) {
			num[col][re - depth] = (int) a.get(depth).pop();
		}
		for (int row = re - depth; row > rs + depth; row--) {
			num[ce - depth][row] = (int) a.get(depth).pop();
		}
		for (int col = ce - depth; col > cs + depth; col--) {
			num[col][rs + depth] = (int) a.get(depth).pop();
		}

		System.out.println("depth : " + depth);
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(num[i][j] + " ");
			}
			System.out.println();
		}

		if (depth == com[idx][2] - 1)
			return;
		readLine(depth + 1, idx);
	}
}
