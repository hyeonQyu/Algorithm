package level3;

import java.util.LinkedList;

public class DoublePriorityQueue {
	
	public int[] solution(String[] operations) {
		LinkedList<Integer> dpq = new LinkedList<>();
		int[] answer = {0, 0};
		
		for(int i = 0; i < operations.length; i++) {
			// ����
			if(operations[i].charAt(0) == 'I') {
				dpq.add(Integer.parseInt(operations[i].substring(2)));
				dpq.sort(null);
			}
			// ����
			else {
				if(!dpq.isEmpty()) {
					// �ּڰ�
					if(operations[i].charAt(2) == '-')
						dpq.removeFirst();
					// �ִ�
					else
						dpq.removeLast();
				}				
			}
		}
		
		if(dpq.isEmpty())
			return answer;
			
		answer[0] = dpq.getLast();
		answer[1] = dpq.getFirst();
		return answer;
	}

}
