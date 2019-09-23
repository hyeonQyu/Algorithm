package kakao;

import java.util.HashMap;

public class OpenChatting {
	
	public String[] solution(String[] record) {
		// ���� ������ ���� �ؽ���
		HashMap<String, String> users = new HashMap<>();
		
		int change = 0;
		// ���� ������ ����(�ֽ� ���� �ݿ�)
		for(int i = 0; i < record.length; i++) {
			String[] elements = record[i].split(" ");
			try {
				users.put(elements[1], elements[2]);
			} catch(IndexOutOfBoundsException e) {}
			if(elements[0].equals("Change"))
				change++;
		}
		
		String[] answer = new String[record.length - change];
		int j = 0;
		// �����ڿ��� ��µ� �޽���
		for(int i = 0; i < record.length; i++) {
			String[] elements = record[i].split(" ");
			String id = elements[1];
			try {
				String nickname = users.get(id);
				
				switch(elements[0]) {
				case "Enter":
					answer[j++] = nickname + "���� ���Խ��ϴ�.";
					break;
				case "Leave":
					answer[j++] = nickname + "���� �������ϴ�.";
					break;
				}
			} catch(IndexOutOfBoundsException e) {}			
		}
		return answer;
	}

}
