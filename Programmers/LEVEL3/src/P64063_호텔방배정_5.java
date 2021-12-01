import java.io.IOException;
import java.util.Arrays;
import java.util.PriorityQueue;

public class P64063_호텔방배정_5 {
	public static void main(String[] args) throws IOException {
		long k = 10;
		long[] s = { 1, 3, 4, 1, 3, 1 };
		System.out.println(Arrays.toString(solution(k, s)));
	}

	static PriorityQueue<Long> heap = new PriorityQueue<>();
    static public long[] solution(long k, long[] room_number) {
		int len = room_number.length;
		long[] answer = new long[len];
		for (int i = 0; i < len; i++) { // 입장하는 손님 idx
			 answer[i]=findRoom(room_number[i]);
		}
		return answer;
	}
     static long findRoom(long target){
       
    	 if(!heap.contains(target)) {
    		 heap.add(target);
    		 return target;
    	 }
    	 
    	 heap.add(findRoom(target+1));
    	 return 
    		 
        if (heap.contains(target)) {
				long tmp = target;
				while (heap.contains(++tmp)) {
				}
				answer[i] = tmp;
				System.out.println(tmp);
				heap.add(tmp);
			} else {
				answer[i] = room_number[i];
				heap.add(room_number[i]);
			}
        
    }
}
