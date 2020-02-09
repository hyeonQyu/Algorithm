package level3;

public class Origami {
	
	public int[] solution(int n) {
		// �������� �� ���� (���� 2)
		int length = (int) ((1 - Math.pow(2, (double)n)) / -1);
		int[] answer = new int[length];
		
		answer[0] = 0;
		if(n == 1)
			return answer;
		
		double pow = 1;
		int end, center;
		end = 2;
		while(end < length) {
			center = end / 2;
			answer[center] = 0;
			// �߾��� �������� ��Ī
			for(int i = 0; i < center; i++) {
				answer[end - i] = (1 ^ answer[i]);
			}
			
			pow++;
			// ���� ���� 2�� �������� ��
			end += (int) Math.pow(2, pow);
		}
		
		return answer;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(1 ^ 1);
	}

}
