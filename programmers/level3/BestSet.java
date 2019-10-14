package level3;

public class BestSet {
	
	public int[] solution(int n, int s) {
		int[] bestSet = new int[n];
		int rest = 0;
		while(s > 0) {
			if(s % n == 0) {
				setSet(n, bestSet, s / n, rest);
				break;
			}
			// s�� n���� ������ ������ ������ s�� �����ϰ�, �������� ������Ŵ
			else {
				s--;
				rest++;
			}
		}
		
		if(s == 0) {
			int[] set = {-1};
			return set;
		}
		
		return bestSet;
	}
	
	private void setSet(int n, int[] set, int value, int rest) {
		for(int i = 0; i < n; i++) {
			set[i] = value;
		}
		int i = n - 1;
		// ���������� ������ ���ҿ� ������
		while(rest > 0) {
			set[i]++;
			rest--;
			if(i == 0)
				i = n - 1;
			else
				i--;
		}
	}

}
