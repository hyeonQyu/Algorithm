package level3;

import java.util.Iterator;
import java.util.LinkedList;

class Computer {
	int index;
	LinkedList<Computer> link = new LinkedList<>();
	boolean isVisit;
	
	Computer(int index) {
		this.index = index;
		isVisit = false;
	}
}

public class Network {
	
	static boolean isVisitComputer(Computer com) {
		if(com.isVisit)
			return false;
		
		// �湮���� ���� ��忡 ���Ͽ�
		com.isVisit = true;
		
		// ���� ��ǻ�Ϳ� ����Ǿ� �ִ� ��ǻ�͵� �湮
		Iterator<Computer> itr = com.link.iterator();
		while(itr.hasNext())
			isVisitComputer(itr.next());
		return true;
	}
	
	public static int solution(int n, int[][] computers) {
		int network = 0;
		
		Computer[] coms = new Computer[n];
		for(int i = 0; i < n; i++)
			coms[i] = new Computer(i + 1);
		
		// ���� ����Ǿ� �ִ� ��ǻ�� ����
		for(int i = 0; i < n - 1; i++) {
			for(int j = i + 1; j < n; j++) {
				if(computers[i][j] == 1) {
					coms[i].link.add(coms[j]);
					coms[j].link.add(coms[i]);
				}
			}
		}
		
		// Ž��
		for(int i = 0; i < n; i++) {
			// ���� ��ǻ�Ϳ� ���� �Ʒ� �Լ��� ���������� ����Ǹ� ��Ʈ��ũ �� ����
			if(isVisitComputer(coms[i]))
				network++;
		}
		
		return network;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][] com = {{1,0,0,1},{0,1,1,1},{0,1,1,0},{1,1,0,1}};
		System.out.println(solution(4, com));
	}

}
