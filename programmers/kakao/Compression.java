package kakao;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

public class Compression {
	
	public int[] solution(String msg) {
		HashMap<String, Integer> map = new HashMap<>();
		
		// A ~ Z ������ ���
		for(int i = 0; i < 26; i++) {
			char c = (char) ('A' + i);
			map.put(Character.toString(c), i + 1);
		}
		
		int index = 0;
		int add = 27;
		LinkedList<Integer> list = new LinkedList<>();
		while(index < msg.length()) {
			char start = msg.charAt(index);
			StringBuilder tmp = new StringBuilder(Character.toString(start));
			
			int output = 0;
			// ������ �ִ��� �˻�, ������ ������ �ݺ��� Ż��
			while(map.get(tmp.toString()) != null) {
				output = map.get(tmp.toString());
				index++;
				try {
					tmp.append(msg.charAt(index));
				} catch(Exception e) {
					break;
				}
			}
			// ���� �߰�
			map.put(tmp.toString(), add++);
			// ��°�
			list.add(output);
		}
		
		int[] answer = new int[list.size()];
		Iterator<Integer> itr = list.iterator();
		index = 0;
		while(itr.hasNext()) {
			answer[index++] = itr.next();
		}
		
		return answer;
	}

}
