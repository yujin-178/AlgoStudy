import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B11399_ATM_1 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int num = Integer.parseInt(br.readLine());
		int[] input = new int[num];
		StringTokenizer st= new StringTokenizer(br.readLine());
		for(int i =0;i<num;i++) {
			input[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(input);
		int sum = 0;
		int ans = 0;
		for(int i =0;i<num;i++) {
			sum += input[i];
			ans += sum;
		}
		System.out.println(ans);
	}
}
