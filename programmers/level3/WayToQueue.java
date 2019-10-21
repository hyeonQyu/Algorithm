package level3;

import java.util.LinkedList;

public class WayToQueue {
	
	public int[] solution(int n, long k) {
		int[] answer = new int[n];
		int index = 0;
		
		LinkedList<Integer> list = new LinkedList<>();
		for(int i = 1; i <= n; i++) {
			list.add(i);
		}
		
		// div���� (n-1)!�� ��
		long[] div = new long[n];
		div[0] = 1;
		for(int i = 1; i < n; i++) {
			div[i] = div[i - 1] * i;
		}
		
		k--;
		for(int i = n - 1; i >= 0; i--) {
			// k/div�� ���� ����Ʈ���� ������ �ε����� ��
			int removeIndex = (int) (k / div[i]);
			// k�� k/div�� �������� ��
			k %= div[i];
			int num = list.remove(removeIndex);
			answer[index++] = num;
		}
		
		for(int i = 0; i < n; i++)
			System.out.println(answer[i] + " ");
		return answer;
	}

}
