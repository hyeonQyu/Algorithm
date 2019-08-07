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
		
		// 1�� ����, 2�� ������ ���� combination�� �� ��ŭ ��
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
	
	static long getCombination(long n, long r) {
		System.out.println("n: " + n);
		System.out.println("r: " + r);
		long nFac = getFactorial(n);
		long nrFac = getFactorial(n-r);
		long rFac = getFactorial(r);
		System.out.println("nrFac: " + nrFac);
		System.out.println("rFac: " + rFac);
		
		return nFac / (nrFac * rFac);
	}
	
	static long getFactorial(long n) {
		int fac = 1;
		for(long i = 2; i <= n; i++)
			fac *= i;
		return fac;
	}

}
