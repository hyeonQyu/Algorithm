package kakao;

import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;

class Block {
	int accessed;
	String data;
	
	Block(int accessed, String data) {
		this.accessed = accessed;
		this.data = data;
	}
}

public class Cache {
	
	public int solution(int cacheSize, String[] cities) {
		int time = 0;
		
		LinkedList<Block> cache = new LinkedList<>();

		Comparator<Block> c = new Comparator<Block>() {
			@Override
			public int compare(Block o1, Block o2) {
				return Integer.compare(o1.accessed, o2.accessed);
			}
		};
		
		if(cacheSize == 0)
			return 5 * cities.length;
		cache.add(new Block(0, cities[0]));
		time += 5;
		for(int i = 1; i < cities.length; i++) {
			Block newAccess = new Block(i, cities[i]);
			boolean isInCache = false;
			
			Iterator<Block> itr = cache.iterator();
			// ĳ�ÿ� �����Ͱ� �ִ��� �˻�
			while(itr.hasNext()) {
				Block inCache = itr.next();
				// ĳ�� hit
				if(inCache.data.toLowerCase().equals(newAccess.data.toLowerCase())) {
					time++;
					inCache.accessed = i;
					isInCache = true;
					cache.sort(c);
					break;
				}
			}
			// ĳ�� miss
			if(!isInCache) {
				// ĳ�ð� �� �������� ������ �� ���� ���� �� �����͸� ����
				if(cache.size() == cacheSize) {
					cache.poll();
				}
				cache.add(newAccess);
				cache.sort(c);
				time += 5;
			}
		}
		
		return time;
	}

}
