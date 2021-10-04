import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
// 0에  가장 큰 기둥이 있다고 가정했으며 풀이 시간에 알려주신 stack개념을 배열에 적용해서 풀었음
//public class Main {
public class Test2493_3 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("C:/CodingStudy/Baekjoon/Gold5/2493_input"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());

		Tower[] t = new Tower[N + 1];
		t[0] = new Tower(0, Integer.MAX_VALUE);
		int nowIdx = 1;
		for (int i = 1; i <= N; i++) {
			int tmpH = Integer.parseInt(st.nextToken());
			for (int idx = nowIdx; idx > 0; idx--) {
//				System.out.println(idx);
				if (tmpH < t[idx-1].height) {
					t[nowIdx++] = new Tower(i, tmpH);
					sb.append(t[idx-1].idx + " ");
					break;
				} else
					--nowIdx;

//				System.out.println(nowIdx + " " + idx);
			}

		}
		System.out.println(sb);

	}

	static class Tower {
		int idx = 0;
		int height = 0;

		Tower(int idx, int h) {
			this.idx = idx;
			this.height = h;
//			System.out.println("save : " + idx + ", " + h);
		}
	}
}
