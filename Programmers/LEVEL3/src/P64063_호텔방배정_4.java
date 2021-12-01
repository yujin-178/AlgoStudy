import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

public class P64063_호텔방배정_4 {
	public static void main(String[] args) throws IOException {
		long k = 10;
		long[] s = { 1, 3, 4, 1, 3, 1 };
		System.out.println(Arrays.toString(solution(k, s)));
	}

	static HashMap<Long, Long> map = new HashMap<>();
	static public long[] solution(long k, long[] room_number) {
		int len = room_number.length;
		long[] answer = new long[len];
		for (int i = 0; i < len; i++) { // 입장하는 손님 idx
			answer[i] = findRoom(room_number[i]);
		}
		return answer;
	}
    
    static long findRoom(long target){
       
        if(!map.containsKey(target)){
        	map.put(target,target);
            return target;
        }
        map.put(target,findRoom(target + 1));
        return map.get(target);
       
    }
}
