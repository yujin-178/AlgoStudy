package d1;

import java.util.Scanner;

class QuickSort_test {
	//static int[] num = { 6, 9, 5, 1, 7, 2, 3, 8, 4, 0 };
	static int[] num = new int [10];
	static Scanner sc = new Scanner(System.in);
	public static void main(String args[]) {
		
		for (int i = 0; i<10;i++)
			num[i] = sc.nextInt();
		quick(0, 9);
		showNUm();
	}

	static public void quick(int s, int l) {
		int pivot, i, j, tmp;
		// pivot이란 임의의 알고리즘에서 먼저 선택된 행렬의 성분을 의미한다.
		if (s < l) { // 마지막 성분이 시작 성분보다 크다면 수행
			pivot = s; // 시작 성분을 pivot으로 설정
			i = s;
			j = l;
			while (i < j) { // 선택하는 인덱스가 교차되기 전까지
				while (num[i] <= num[pivot] && i < l) { 
					i++; // 피벗보다 같거나 작은 숫자들은 넘어감
				}
				while (num[j] > num[pivot]) {
					j--; // 피벗보다 큰 숫자들은 넘어감
				}
				if (i < j) { // 둘이 교차하기 전이라면 서로의 위치를 바꾼다.
					tmp = num[i];
					num[i] = num[j];
					num[j] = tmp;
				} 
				showNUm();
			}
			// 즉 피벗을 기준으로 작은숫자들은 앞으로 큰 숫자들은 뒤로 배치된다.
			tmp = num[pivot];
			num[pivot] = num[j];
			num[j] = tmp;
			//경계선의 숫자와 pivot의 위치를 바꾼다. pivot의 숫자는 위치가 고정된다.
			showNUm();
			//pivot을 기준으로 앞의 숫자들과 뒤의 숫자들의 재배치를 수행한다.
			quick(s, j - 1);
			quick(j + 1, l);
		}
	}

	static public void showNUm() {
		for (int i = 0; i < num.length; i++) {
			System.out.print(num[i] + " ");
		}
		System.out.print("\n");
	}
}