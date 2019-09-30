package kakao;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;

public class CandidateKey {
	
	// 1�� ��Ʈ�� ���� �� -> ���� �� ������ ����
	Comparator<Integer> comp = new Comparator<Integer>() {
		@Override
		public int compare(Integer o1, Integer o2) {
			int bits1 = 0;
			int bits2 = 0;
			while(o1 > 0 || o2 > 0) {
				if((o1 & 1) == 1)
					bits1++;
				if((o2 & 1) == 1)
					bits2++;
				
				o1 = o1 >> 1;
				o2 = o2 >> 1;
			}
			
			return Integer.compare(bits1, bits2);
		}
	};
	
	private boolean isSuperKey(String[][] relation, int attribute) {
		String[] setOfAttr = new String[relation.length];
		for(int i = 0; i < setOfAttr.length; i++)
			setOfAttr[i] = "";
		
		for(int k = 0; k < relation[0].length; k++) {
			// attribute�� ���� ǥ�� ��Ʈ�� 1�� ���� ���� �Ӽ��� �κ����� ����
			if((attribute & (1 << k)) > 0) {
				for(int i = 0; i < relation.length; i++) {
					setOfAttr[i] += relation[i][k];
				}
			}
		}
		
		// �ߺ��˻�
		for(int i = 0; i < setOfAttr.length - 1; i++) {
			for(int j = i + 1; j < setOfAttr.length; j++) {
				if(setOfAttr[i].equals(setOfAttr[j]))
					return false;
			}
		}
		return true;
	}
	
	public int solution(String[][] relation) {
		LinkedList<Integer> supers = new LinkedList<>();
		
		for(int i = 1; i < (1 << relation[0].length); i++) {
			// ���ϼ��� �����ϸ� ����Ʈ�� �߰�
			if(isSuperKey(relation, i))
				supers.add(i);
		}
		
		// 1 ��Ʈ ����� ����
		Collections.sort(supers);
		// ����Ʈ�� �Ű� ���� �迭
		int[] candidates = new int[supers.size()];
		
		Iterator<Integer> itr = supers.iterator();
		int index = 0;
		while(itr.hasNext()) {
			candidates[index++] = itr.next();
		}
		
		// �ּҼ� �˻�
		for(int i = 0; i < candidates.length - 1; i++) {
			if(candidates[i] > 0) {
				for(int j = i + 1; j < candidates.length; j++) {
					// �� ���� ��� ��ȿ�� �����̾�� ��(�ּҼ� �˻翡�� Ż������ ���� ��)
					if(candidates[j] > 0) {
						// �ڿ� �ִ� ���� Ż���ؾ� �ϴ� ���(�ĺ�Ű���� Ż��)
						if((candidates[i] & candidates[j]) > 0 && (candidates[i] | candidates[j]) == candidates[j]) {
							candidates[j] = 0;
						}
					}
				}
			}		
		}
		
		int answer = 0;
		// Ż������ ���� ��(�ĺ�Ű)�� ��
		for(int i = 0; i < candidates.length; i++) {
			if(candidates[i] != 0)
				answer++;
		}
		
		return answer;
	}

}
