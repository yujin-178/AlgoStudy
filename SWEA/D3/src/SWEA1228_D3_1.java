import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

//public class Solution{
public class SWEA1228_D3_1 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("C:/CodingStudy/SWEA/D3/1228_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		for(int tc = 1; tc<10;tc++) {
			int cryptogramNum = Integer.parseInt(br.readLine());
			
			LinkedList<String> l = new LinkedList<String>();
			st = new StringTokenizer(br.readLine());
			for(int i = 0;i<cryptogramNum;i++) {
				l.add(st.nextToken());
			}
			int commandNum = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i< commandNum;i++) {
//				System.out.println(st.nextToken());
				st.nextToken();
				int startIdx = Integer.parseInt(st.nextToken());
				int plusNum = Integer.parseInt(st.nextToken());
				for(int j = startIdx ;j<startIdx + plusNum;j++) {
					l.add(j,st.nextToken());
				}
			}
			System.out.print("#"+tc+ " ");
			for(int i = 0 ; i<10;i++) {
				System.out.print(l.get(i) + " ");
			}
			System.out.println();
		}
	}
}
