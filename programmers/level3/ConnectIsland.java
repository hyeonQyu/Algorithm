package level3;

import java.util.Arrays;
import java.util.Comparator;

public class ConnectIsland {
	
	public static int solution(int n, int[][] costs) {
		// 2�����迭���� 3��° ����� ������ ����
		Arrays.sort(costs, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return Integer.compare(o1[2], o2[2]);
			}
		});
		
		// �� ���� ������ �ٸ� �Ǽ� ����
		boolean[] isConnect = new boolean[n];
		boolean[] isBuild = new boolean[costs.length];
		
		// ù �ٸ�(���� ª�� �ٸ�) ����
		int countConnect = 2;
		int totalCost = costs[0][2];
		isConnect[costs[0][0]] = true;
		isConnect[costs[0][1]] = true;
		
		for(int i = 0; i < costs.length; i++) {
			int is1 = costs[i][0];
			int is2 = costs[i][1];
			
			// �� �� �ϳ��� ����� ���� ��� �� �ٸ��� ����
			// �� �� ����Ǿ� ������ ������ �ʿ䰡 ����, �� �� ����Ǿ����� ���� ���̸� ������ �� ����(ũ�罺Į)
			// �ٸ��� �Ǽ����� ���� �ٸ��̸� ����
			if(!isBuild[i] && (isConnect[is1] ^ isConnect[is2])) {
				isConnect[is1] = true;
				isConnect[is2] = true;
				isBuild[i] = true;
				totalCost += costs[i][2];
				countConnect++;
				// �������� �ֽ�ȭ �� ó������ ���ư�(����� �� �ִ� �ٸ��� �ٲ� �� ����)
				i = 0;
			}
			
			if(countConnect == n)
				break;			
		}
		
		return totalCost;
	}

}
