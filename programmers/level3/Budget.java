package level3;

public class Budget {

	public int solution(int[] budgets, int M) {
		if(budgets.length == M)
			return 1;
		long total = 0;
		int max = 0;
		int min = 100000;
		int limit;
		for(int i = 0; i < budgets.length; i++) {
			total += budgets[i];
			if(max < budgets[i])
				max = budgets[i];
			if(min > budgets[i])
				min = budgets[i];
		}
		
		// ó���� ��� ��û�� ������ �� �ִ� ���
		if(total <= M)
			return max;
		// ó���� ��� ��û�� ������ �� ���� ���
		else {
			if(min >= M / budgets.length)
				return M / budgets.length;
			
			long lack = 1000000000;
			// �ʱ� limit ��
			limit = (max + min) / 2;
			while(true) {
				total = 0;
				for(int i = 0; i < budgets.length; i++) {
					if(budgets[i] > limit)
						total += limit;
					else
						total += budgets[i];
				}
				
				// ����� �ʰ�
				if(total > M) {
					max = limit;
					// limit �� ����
					limit = (limit + min) / 2;
				}
				// ����� �̸�
				else if(total < M) {
					// ��� ��û�� ���� �ִ� ����ȿ� ���� �����ϴٸ�
					if(M - total < lack) {
						lack = (long)M - total;
						min = limit;
						// limit �� ����
						limit = (limit + max) / 2;
					}
					else if(M - total == lack)
						break;
				}
				// ����Ȱ� ��ġ
				else
					break;
			}
		}
		
		return limit;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] budgets = {9, 8, 10, 11, 6};
		new Budget().solution(budgets, 6);
	}

}
