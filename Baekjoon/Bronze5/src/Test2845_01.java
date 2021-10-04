
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
//public class Main{
public class Test2845_01{
    public static void main(String[] args)throws IOException{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	String s = br.readLine();
    	StringTokenizer st = new StringTokenizer(s);
    	int a = Integer.parseInt(st.nextToken());
    	int b = Integer.parseInt(st.nextToken());
    	int num = a*b;
    	s = br.readLine();
    	st = new StringTokenizer(s);
    	
    	for(int i= 0;i<5;i++) {
    		int newnum = Integer.parseInt(st.nextToken());
    		System.out.printf("%d ",newnum-num); 
    	}
        
    }
}