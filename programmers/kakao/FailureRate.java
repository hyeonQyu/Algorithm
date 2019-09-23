package kakao;

import java.util.Arrays;

class Rate implements Comparable<Rate> {
	int stage;
	float failureRate;
	
	Rate(int stage) {
		this.stage = stage;
	}
	
	@Override
	public int compareTo(Rate rate) {
		// �������� ���ٸ� �������� ��ȣ�� ��������
		if(failureRate == rate.failureRate) {
			return Integer.compare(stage, rate.stage);
		}
		// �������� ��������
		return -Float.compare(failureRate, rate.failureRate);
	}
}

class Stage {
	float users;
	float failUsers;
	
	float getFailureRate() {
		// �ش� ������������ ������ ����ڰ� ������ �������� 0
		if(users == 0)
			return users;
		return failUsers / users;
	}
}

public class FailureRate {
	
	public int[] solution(int N, int[] stages) {
		// �� ���������� ������
		Rate[] rates = new Rate[N];
		Stage[] stageInfo = new Stage[N];
		
		for(int i = 0; i < N; i++) {
			rates[i] = new Rate(i + 1);
			stageInfo[i] = new Stage();
		}
		
		for(int i = 0; i < stages.length; i++) {
			int tmpStage = stages[i] - 1;
			try {		
				// �ش� ���������� �ӹ��� �ִ� ����� ��
				stageInfo[tmpStage].failUsers++;			
			} catch(IndexOutOfBoundsException e) {
				// stage�� N+1�� ���: ��� ���������� Ŭ���� �� ���
				tmpStage--;
			}
			
			// �ش� ���������� ��ģ ����� ��
			for(int j = 0; j <= tmpStage; j++) {
				stageInfo[j].users++;
			}
		}
		
		// ������ ���
		for(int i = 0; i < N; i++)
			rates[i].failureRate = stageInfo[i].getFailureRate();
		
		// �������� ��������, ���������� ������������ ����
		Arrays.sort(rates);
		int[] answer = new int[N];
		for(int i = 0; i < N; i++)
			answer[i] = rates[i].stage;
		
		return answer;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] stages = {3, 3, 3, 3};
		new FailureRate().solution(5, stages);
	}

}
