package level3;

public class LongJump {
	
	public static long solution(int n) {
		// ������ȹ������ nCn���� combination ���
		long[][] combination = new long[n + 1][n + 1];
		combination[1][0] = 1;
		combination[1][1] = 1;
		for(int i = 2; i <= n; i++) {
			combination[i][0] = 1;
			for(int j = 1; j <= i; j++) {
				combination[i][j] = (combination[i-1][j-1] + combination[i-1][j]) % 1234567;
			}
		}
		
		// 1�� ����, 2�� ������ ���� combination�� �� ��ŭ count
		long count = 1;
		int time = 1;
		while(n - 2 * time >= 0) {
			int ones = n - 2 * time;
			int total = ones + time;
			count += combination[total][time];
			time++;
		}
		
		return count % 1234567;
	}

}
