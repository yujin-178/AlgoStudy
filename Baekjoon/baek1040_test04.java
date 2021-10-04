
package Baekjoon_test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author yujin
 * cnt로 세는건 시간이 너무 걸리는듯
 * 아예 새로운 방법으로 접근해보자
 *
 */
public class baek1040_test04 {
	static int[] cntNum = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }; // 각 숫자가 몇개 존재하는지 체크
	static int[] numSplit = new int[20]; // 숫자 회득하면 자리수대로 분해하기
	static boolean[] chkZero = new boolean[20]; // 숫자가 0인 부분 체크
	static int digits=0; // 입력받은 수가 몇 자리수인지 기록하기
	static long inNum = 0; // 
	static int goalCnt= 0; // 목표로하는 숫자의 종류 개수
	static int nowCnt = 0;
	public static void main(String args[]) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in)); //버퍼로 생성
		String[] s = bf.readLine().split(" "); // 받은 String을 띄어씌기 단위로 분해
		inNum = Long.parseLong(s[0]);
		goalCnt = Integer.parseInt(s[1]);
		
		dataAcq();
		cntChk();
		
		if (goalCnt == nowCnt) { // 숫자의 종류가 목표와 일치한다면 그냥 출력 
		}
		else if(goalCnt < nowCnt){ // 숫자의 종류가 목표보다 크다면 줄이는 동작
			
		}
		else { // 숫자의 종류가 목표보다 적다면 늘리는 동작
			
		}
		System.out.println(digits);
		showNumPart();
	}
	
	public static void dataAcq() {
		long tmp = inNum;
		while ((tmp % 10) != 0) {
			int oneTmp = (int) (tmp % 10); // 매 단계의 tmp의 1의 자리수 획득
			cntNum[oneTmp]++; // 자릿수 인덱스에 카운트 1 증가
			numSplit[digits] = oneTmp; // 해당 자릿수에 숫자 입력
			tmp /= 10; // 10을 나눠서 다음 단계를 위한 숫자로 변형
			digits++; // numPart에 저장할 자릿수 증가
		}
	}
	
	public static void cntChk() {
		for (int i = 0; i < 10;i++) {
			if (cntNum[i] != 0)
				nowCnt++;
		}
	}
	
	public static void numInit() {
		for (int i = 0; i < 10; i++) {
			cntNum[i] = 0;
		}
	}
	
	public static void showNumPart() {
		for (int i = digits - 1; i >= 0; i--) {
			System.out.print(numSplit[i] + " ");
		}
	}

}
