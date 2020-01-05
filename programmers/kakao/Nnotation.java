package kakao;

import java.util.Stack;

public class Nnotation {
	
	int index = 1;
	
	public String solution(int n, int t, int m, int p) {
		char[] total = new char[t * m];
		StringBuilder answer = new StringBuilder();
		int num = 1;
		total[0] = '0';
		
		while(index < t * m) {
			convert2Nnotation(total, n, num);			
			num++;
		}
		
		for(int i = p - 1; i < t * m; i = i + m) {
			answer.append(total[i]);
		}
		
		return answer.toString();
	}
	
	// num�� n������ ��ȯ�Ͽ� �ڸ������� total�� �߰�
	void convert2Nnotation(char[] total, int n, int num) {
		Stack<Character> stack = new Stack<Character>();
		int q, r;
		q = num;
		
		// n������ ��ȯ
		while(q > 0) {
			r = q % n;
			q = q / n;
			
			char c;
			if(r < 10) {
				c = (char) (r + '0');
			}
			// �������� 10 �̻��� ��� ó��
			else {
				c = (char) (r - 10 + 'A');
			}
			stack.push(c);
		}
		
		// total�� �߰�
		while(!stack.isEmpty()) {
			try {
				total[index++] = stack.pop();
			} catch(Exception e) {
				break;
			}
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Nnotation().solution(2, 4, 2, 1);
	}

}
