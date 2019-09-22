package level3;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Stack;

// ���� Ŭ����
class Airport {	
	String name;
	// �ش� ���׿��� �̵� �� �� �ִ� ���׵��� ����Ʈ(���� ������ ���ĺ� �������� ����, Ƽ�� ��ȣ�� �Բ� ������)
	PriorityQueue<Ticket> next = new PriorityQueue<>(new Comparator<Ticket>() {
		@Override
		public int compare(Ticket o1, Ticket o2) {
			return -o1.arrival.name.compareTo(o2.arrival.name);
		}
	});
	
	Airport(String name) {
		this.name = name;
	}
	
	void addNext(Ticket ticket) {
		next.add(ticket);
	}
}

class Ticket {
	Airport arrival;
	int ticketNo;
	
	Ticket(Airport arrival, int ticketNo) {
		this.arrival = arrival;
		this.ticketNo = ticketNo;
	}
}

public class TripPath {
	
	public String[] solution(String[][] tickets) {
		// ���� �̸��� �ߺ��Ͽ� Airport ��ü�� �������� �ʵ��� �ϱ� ���� HashMap
		HashMap<String, Airport> airportMap = new HashMap<>();
		
		// ���� ��ü�� ����: key���� ���� �̸��̹Ƿ� ���� �̸��� tickets���� ���ĵ� �ϳ��� ���׸� ������
		for(int i = 0; i < tickets.length; i++) {
			airportMap.put(tickets[i][0], new Airport(tickets[i][0]));
			airportMap.put(tickets[i][1], new Airport(tickets[i][1]));
		}
		
		// �� ���׿��� �� �� �ִ� ���׵��� ����Ʈ ������ ����(�� ���׿��� �� �� �ִ� ���)
		for(int i = 0; i < tickets.length; i++) {
			// ���� �̸����� ������ ã��(�ؽ���)
			Airport departure = airportMap.get(tickets[i][0]);
			Airport arrival = airportMap.get(tickets[i][1]);
			
			// �� ���׿� ������ �߰�
			Ticket ticket = new Ticket(arrival, i);
			departure.addNext(ticket);
		}
		
		/* ��� ���� �ʱ�ȭ �Ϸ� */
		
		// ���̿켱Ž��
		// ���̿켱Ž���� �ϴ� ���� Ƽ�Ϲ�ȣ�� ��ġ�� �ǵ��ƿ�(��� �ǵ��ƿ��� ����x ��ȯ)
		// ���̿켱Ž���� ���� ����
		Stack<Ticket> stack = new Stack<>();
		// ��� Ƽ���� ����Ͽ����� �˻��ϱ� ����
		boolean[] isTicketContain = new boolean[tickets.length];
		// Ƽ���� ��� ���Ǿ����� ����
		boolean isAllUsed = false;
		// ���
		String[] path = new String[tickets.length + 1];
		int index = 0;
		
		// ICN �������κ��� ����, Ƽ�Ϲ�ȣ�� -1(���� Ƽ��)
		stack.add(new Ticket(airportMap.get("ICN"), -1));
		while(!isAllUsed) {
			// ���� ����
			Ticket parent = stack.pop();
			path[index++] = parent.arrival.name;
			// ���� ���׿��� �� ���� ������ ���ٸ� ���ÿ��� ���⸸ ��
			if(parent.arrival.next.isEmpty()) {
				// �׷��� ������ ����ٸ� Ž�� ����
				if(stack.isEmpty())
					break;
				// ��ο����� ������
				path[--index] = null;
				continue;
			}	
			
			// ���� �������� ���� Ƽ���� �����
			try {
				isTicketContain[parent.ticketNo] = true;
			}
			catch(IndexOutOfBoundsException e) {
			}
			
			Iterator<Ticket> itr = parent.arrival.next.iterator();
			while(itr.hasNext()) {
				// ���� ������ ���� �������� �ߺ��� Ƽ���� �ƴϸ� ���ÿ� ����
				Ticket ticket = itr.next();
				if(!isTicketContain[ticket.ticketNo])
					stack.push(ticket);
			}
			
			// ��� Ƽ���� ���Ǿ����� Ž���� ������
			isAllUsed = true;
			for(int i = 0; i < isTicketContain.length; i++) {
				if(!isTicketContain[i]) {
					isAllUsed = false;
					break;
				}
			}
		}
		
		for(int i = 0; i < path.length; i++)
			System.out.println(path[i]);
		return path;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//String[][] tickets = {{"ICN", "SFO"}, {"ICN", "ATL"}, {"SFO", "ATL"}, {"ATL", "ICN"}, {"ATL", "SFO"}};
		String[][] tickets = {{"ICN", "COO"}, {"ICN", "BOO"}, {"COO", "ICN"}};
		
		new TripPath().solution(tickets);
	}

}
