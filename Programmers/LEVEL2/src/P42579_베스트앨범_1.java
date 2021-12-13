import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.PriorityQueue;

public class P42579_베스트앨범_1 {
	public static void main(String[] args) {
		String[] genres = { "classic", "pop", "classic", "classic", "pop" };
		int[] plays = { 500, 600, 150, 800, 2500 };
		int[] ans = solution(genres, plays);
		System.out.println(Arrays.toString(ans));
	}

	static HashMap<String, PriorityQueue<Song>> record;
	static HashMap<String, Integer> cnt;

	static public int[] solution(String[] genres, int[] plays) {
		int len = genres.length;
		record = new HashMap<>();
		cnt = new HashMap<>();
		for (int i = 0; i < len; i++) {
			if (record.containsKey(genres[i]))
				record.get(genres[i]).add(new Song(i, plays[i]));
			else
				record.put(genres[i], new PriorityQueue<Song>());
			if (cnt.containsKey(genres[i])) {
				int tmpCnt = cnt.get(genres[i]);
				tmpCnt += plays[i];
				cnt.put(genres[i], tmpCnt);
			} else
				cnt.put(genres[i], plays[i]);
		}
		PriorityQueue<Gerne> tmp = new PriorityQueue<>();
		Iterator<String> iter = cnt.keySet().iterator();
		while (iter.hasNext()) {
			String name = iter.next();
			tmp.add(new Gerne(name, cnt.get(name)));
		}
		
		

		int[] answer = new int[len];
		return answer;
	}

	static class Gerne implements Comparable<Gerne> {
		String g;
		int cnt;

		public Gerne(String g, int cnt) {
			this.g = g;
			this.cnt = cnt;
		}

		@Override
		public int compareTo(Gerne o) {
			return -Integer.compare(this.cnt, o.cnt);
		}
	}

	static class Song implements Comparable<Song> {
		int idx, cnt;

		public Song(int idx, int cnt) {
			this.idx = idx;
			this.cnt = cnt;
		}

		@Override
		public int compareTo(Song o) {
			if (this.cnt == o.cnt)
				return Integer.compare(this.idx, o.idx);
			else
				return Integer.compare(this.cnt, o.cnt);
		}
	}
}
