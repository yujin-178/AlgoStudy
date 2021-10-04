package d1;

public class PreStudy_control {
public static void main(String args[]) {


		
		// 조건 제어문
		ifControl1();
//		ifControl2();
//		//ifControl3(args[0]);
//		String t[] = {"1"};
//		ifControl3(t[0]);
//		switchControl1("13");
		
		// 반복 제어문
//		forControl();
//		whileControl();
//		doWhileControl();
		
		// 이동 제어문
//		breakControl();
//		continueControl();
//		labelControl1();
//		labelControl2();
//		labelControl3();
		//double avgScore = acalcAvg({64,8,95,44,8,45,54}); // 이거 안됨 왜지?
		int [] scoreList = {34,56,21,73,5,17,98};
		double avgScore=calcAvg(scoreList);
		System.out.println("평균점수 : " + avgScore);
	}

	public static void ifControl1() {
		int num = 10;
		if ((num%2) == 0) {
			System.out.println(num + "은 짝수!");
		}
		if ((num%2) != 0) {
			System.out.println(num + "은 홀수!");
		}
	}
	public static void ifControl2 () {
		int num = 9;
		if ((num%2) == 0) {
			System.out.println(num + "은 짝수!");
		}
		else {
			System.out.println(num + "은 홀수!");
		}
	}
	//public static void ifControl3 (String test) {
	public static void ifControl3 (String test) {
		int month = Integer.parseInt(test);
		
		if(month == 3|| month == 4 || month ==5) {
			System.out.println(month + "월은 봅입니다.");
		}
		else if(month == 6||month == 7||month == 8) {
			System.out.println(month + "월은 여름입니다.");
		}
		else if(month == 9||month == 10||month == 11) {
			System.out.println(month + "월은 가을입니다.");
		}
		else if(month == 12||month == 1||month == 2){
			System.out.println(month + "월은 겨울입니다.");
		}
		else
			System.out.println("1~12 의 숫자만 입력하시오");
		
	}
	public static void switchControl1(String s1) {
		int month = Integer.parseInt(s1);
		switch(month) {
		case 3:
		case 4:
		case 5:
			System.out.println(month + "월은 봄입니다.");
			break;
		case 6:
		case 7:
		case 8:
			System.out.println(month + "월은 여름입니다.");
			break;
		case 9:
		case 10:
		case 11:
			System.out.println(month + "월은 가을입니다.");
			break;
		case 12:
		case 1:
		case 2:
			System.out.println(month + "월은 겨울입니다.");
			break;
		default:
			System.out.println("1~12 의 숫자만 입력하시오");
			break;
		}
	}
	public static void forControl() {
		for (int i = 0; i < 10; i++) {
			for (int j= 0; j<10-i;j++) {
				System.out.print("*");
			}
			System.out.print("\n");
		}
	}
	public static void whileControl() {
		int i = 0;
		while(i<10) {
			System.out.println("숫자 : " + ++i);
			//i++;
		}
	}
	public static void doWhileControl() {
		int i = 1;
		do {
			System.out.println(i+"번째 재생 중");
			i++;
		}while(i < 10);
	}
	public static void breakControl() {
		int[] scoreList = {98,57,49,100,99,85,77};
		int scoreSum = 0;
		for (int i = 0;i <scoreList.length; i++) {
			if((scoreList[i] % 2) != 0) {
				break;
			}
			scoreSum += scoreList[i];
					
		}
		System.out.println("scoreSum = " + scoreSum);
	}
	public static void continueControl() {
		int[] scoreList = {98,57,49,100,99,85,77};
		int scoreSum = 0;
		for (int i = 0;i <scoreList.length; i++) {
			if((scoreList[i] % 2) == 0) {
				continue;
			}
			scoreSum += scoreList[i];
			System.out.println("scoreSum = " + scoreSum);			
		}
		System.out.println("scoreSum = " + scoreSum);	
	}
	public static void labelControl1() {
		System.out.println("메서드 시작");
		outer: for(int i = 0;i<4;i++) {
			for (int j = 0;j<3;j++) {
				if(i==2)
					break outer;
				System.out.println("i = " + i + " j = " + j);
			}
		}
		System.out.println("메서드 종료");
	}
	public static void labelControl2() {
		System.out.println("메서드 시작");
		outer: for(int i = 0;i<4;i++) {
			for (int j = 0;j<3;j++) {
				if(i==2)
					continue outer;
				System.out.println("i = " + i + " j = " + j);
			}
		}
		System.out.println("메서드 종료");
	}
	public static void labelControl3() {

		System.out.println("메서드 시작");
		for (int i = 0;i<5;i++) {
			if(i==3)
				return;
			System.out.println(i);
		}
		System.out.println("메서드 종료");
	}
	
	private static double calcAvg(int[] scoreList) {
		int sum = 0;
		for (int i= 0;i<scoreList.length;i++) {
			sum += scoreList[i];
		}
		return (double)sum/scoreList.length;
	}
}
