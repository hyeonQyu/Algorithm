package level1;

public class MrKim {
	
	public static String solution(String[] seoul) {
		for(int i = 0; i < seoul.length; i++) {
			if(seoul[i].equals("Kim"))
				return "�輭���� " + i + "�� �ִ�";
		}
		return null;
	}

}
