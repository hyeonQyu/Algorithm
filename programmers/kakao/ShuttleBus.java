package kakao;

import java.util.Collections;
import java.util.PriorityQueue;

class Time implements Comparable<Time> {
	int hour;
	int minute;
	
	Time(String time) {
		String[] arr = time.split(":");
		
		hour = Integer.parseInt(arr[0]);
		minute = Integer.parseInt(arr[1]);
	}
	
	Time(Time time) {
		hour = time.hour;
		minute = time.minute;
	}
	
	void addTime(int t) {
		minute += t;
		
		if(minute >= 60) {
			minute -= 60;
			hour++;
		}
	}
	
	// �ð��� �������� ����
	@Override
	public int compareTo(Time t) {
		int c = Integer.compare(hour, t.hour);
		if(c == 0)
			return Integer.compare(minute, t.minute);
		return c;
	}
}

class Bus {
	Time time;
	PriorityQueue<Time> passengers = new PriorityQueue<>(Collections.reverseOrder());
	
	Bus(Time time) {
		this.time = time;
	}
	
	// �°��� �¿�
	void addPassenger(Time passenger) {
		passengers.add(passenger);
	}
}

public class ShuttleBus {
	
	public String solution(int n, int t, int m, String[] timetable) {
		// ũ��� ���� �� ������� ���ĵ�
		PriorityQueue<Time> crews = new PriorityQueue<>();
		for(int i = 0; i < timetable.length; i++) {
			crews.add(new Time(timetable[i]));
		}
		
		Bus[] bus = new Bus[n];
		Time time = new Time("09:00");
		for(int i = 0; i < n; i++) {
			if(i != 0)
				time.addTime(t);
			bus[i] = new Bus(time);
			
			// �� ������ m����� ž���� �� ����
			for(int j = 0; j < m; j++) {
				// �°��� �������� ���� �Դٸ�
				try {
					if(bus[i].time.compareTo(crews.peek()) >= 0) {
						// ������ �°� ž��
						if(!crews.isEmpty()) {
							bus[i].addPassenger(crews.poll());
						}
					}
					// ���� �°����ʹ� ������ Ÿ�� ��(������ m���� ä���� ����)
					else
						break;
				} catch(Exception e) {
					break;
				}
			}		
		}

		int hour, minute;
		// ������ �°��� �� ��
		if(bus[n - 1].passengers.size() == m) {
			Time lastPassenger = bus[n - 1].passengers.poll();
			
			// ���� �������� ź �°����� 1�� �� ����� ��
			hour = lastPassenger.hour;
			minute = lastPassenger.minute - 1;
			
			if(minute < 0) {
				minute = 59;
				hour--;
			}
		}
		// ������ ���� ����
		else {
			// �����ð��� ����
			hour = bus[n - 1].time.hour;
			minute = bus[n - 1].time.minute;
		}

		String hourStr, minStr;
		if(hour < 10)
			hourStr = "0" + hour;
		else
			hourStr = Integer.toString(hour);
		if(minute < 10)
			minStr = "0" + minute;
		else
			minStr = Integer.toString(minute);

		return hourStr + ":" + minStr;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] timetable = {"09:10", "09:09", "08:00"};
		new ShuttleBus().solution(2, 10, 2, timetable);
	}

}
