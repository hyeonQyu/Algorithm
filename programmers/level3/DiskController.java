package level3;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class DiskController {
	
	public int solution(int[][] jobs) {
		int numOfJobs = jobs.length;
		
		// �۾��� ��û�Ǵ� ������ ������������ ����, ������ ���ٸ� �۾��ð��� ������������ ����
		Arrays.sort(jobs, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				if(o1[0] < o2[0])
					return -1;
				else if(o1[0] == o2[0])
					return Integer.compare(o1[1], o2[1]);
				else
					return 1;
				//return Integer.compare(o1[0], o2[0]);
			}		
		});
		
		// �۾� �ð��� ª�� ���� �켱�� �켱���� ť(��û ���)
		PriorityQueue<int[]> requestList = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return Integer.compare(o1[1], o2[1]);
			}
		});
		
		int time = jobs[0][0];			// ù ������ ���� ���� ��û�Ǵ� �۾��� �����ϴ� ����
		int jobIndex = 0;
		int jobStartTime = time;		// Ư�� �۾��� ���۵Ǵ� ����
		int[] runJob = jobs[0];
		int totalRequireTime = 0;		// �� �۾����� ��û���κ��� ������� �ҿ�Ǵ� �ð�
		
		while(true) {
			// ���� �۾��� ����
			if(time == jobStartTime + runJob[1]) {
				totalRequireTime += (time - runJob[0]);
				
				// ��û�� ����� ����
				if(requestList.isEmpty()) {
					// ��� �۾��� �Ϸ���
					if(jobIndex == numOfJobs - 1)
						break;
					else {
						// �ð��� ��ŵ
						jobIndex++;
						runJob = jobs[jobIndex];
						time = runJob[0];
					}
				}
				// ��û ��� �� ���� �տ� �ִ� �۾��� ���� �۾����� ����
				else
					runJob = requestList.poll();
				
				jobStartTime = time;
			}
			
			// ���ο� ��û �۾��� ��û ��Ͽ� �߰�
			try {
				while(time == jobs[jobIndex + 1][0]) {
					jobIndex++;
					requestList.add(jobs[jobIndex]);
				}
			}
			catch(ArrayIndexOutOfBoundsException e) {				
			}
			
			time++;
		}
		
		System.out.println("answer: " + totalRequireTime / numOfJobs);
		return totalRequireTime / numOfJobs;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][] jobs = {{0,9}, {0,4}, {0,5}, {0,7}, {0,3}};
		new DiskController().solution(jobs);
	}

}
