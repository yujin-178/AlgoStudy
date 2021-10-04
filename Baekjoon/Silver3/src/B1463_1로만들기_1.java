import java.util.Scanner;

/**
  * @FileName : B1463_1로만들기_1.java
  * @Date : 2021. 9. 14. 
  * @작성자 : KimYuJin
  * @특이점 : 그냥 만들었는데 상황에 따라 다른 방법을 적용해야 한다.
  */
public class B1463_1로만들기_1 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int X = sc.nextInt();
		int cnt = 0;
		while (X != 1) {
			if (X % 3 == 0)
				X /= 3;
			else if (X % 2 == 0)
				X /= 2;
			else
				X -= 1;
			cnt++;
			System.out.println(cnt+ " , "+X);
		}
		
		System.out.println(cnt);
	}
}
