package kakao;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;

class Food {
	int index;
	int time;
	
	Food(int index, int time) {
		this.index = index;
		this.time = time;
	}
}

public class MukbangLive {
	
	public int solution(int[] food_times, long k) {
		int foodCount = food_times.length;
		LinkedList<Food> list = new LinkedList<>();
		
		for(int i = 0; i < foodCount; i++) {
			list.add(new Food(i, food_times[i]));
		}
		// �ð��� ���� �ɸ��� ���ĺ��� ���� �ɸ��� ���� ������ ����
		Collections.sort(list, new Comparator<Food>() {
			@Override
			public int compare(Food o1, Food o2) {
				return Integer.compare(o1.time, o2.time);
			}
		});
		
		Iterator<Food> itr = list.iterator();
		int index = 0;
		int before = 0;
		int current = 0;
		int width = foodCount;
		Food food = null;
		
		while(itr.hasNext()) {
			// ���� ������ �ɸ��� �ð�
			food = itr.next();
			current = food.time;
			// ����
			long height = current - before;
			if(height != 0) {
				long tmp = width * height;
				// ���� * ���� ��ŭ�� ������ �ѹ��� ����
				if(tmp <= k) {
					k -= tmp;
					before = current;
				}
				
				// ���� ������ �� ���� �� ����(����*���ΰ� ���� k���� ŭ)
				else {			
					k %= width;
					list.subList(index, foodCount).sort(new Comparator<Food>() {
						// ���� �ε������ ����
						@Override
						public int compare(Food o1, Food o2) {
							return Integer.compare(o1.index, o2.index);
						}
					});
					return list.get(index + (int)k).index + 1;
				}
			}
					
			index++;
			width--;
		}
		
		return -1;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] food_times = {4,1,1,5};
		System.out.println(new MukbangLive().solution(food_times, 7));
	}

}
