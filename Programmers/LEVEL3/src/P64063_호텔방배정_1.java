import java.io.IOException;
import java.util.Arrays;

public class P64063_호텔방배정_1 {
	public static void main(String[] args) throws IOException {
		long k = 10;
		long[] s = { 1, 3, 4, 1, 3, 1 };
		System.out.println(Arrays.toString(solution(k, s)));
	}

	static public long[] solution(long k, long[] room_number) {
		int len = room_number.length;
		long[] setRoom = new long[len];
		long[] answer = new long[len];
		for (int i = 0; i < len; i++) { // 입장하는 손님 idx
			Arrays.sort(setRoom);
			if (find(i,answer,room_number[i])) {
				long tmp = room_number[i];
				
				answer[i] = tmp;
				System.out.println(tmp);
				heap.add(tmp);
				System.out.println("다른 방 " + i + "번째 사람 " + room_number[i] + "번째 방");
			} else {
				answer[i] = room_number[i];
				heap.add(room_number[i]);
				System.out.println("빈방 " + i + "번째 사람 " + room_number[i] + "번째 방");
			}
		}
		return answer;
	}
	
	static boolean find(int maxIdx, long[] num,long target) {
		for(int i = 0;i<=maxIdx;i++) {
			if(target == num[i])
				return true;
			if(target < num[i])
				return false;
		}
		return false;
	}
	static long returnNum (int maxIdx, long[] num,long target) {
		while(++target) {
			
		}
	}
}