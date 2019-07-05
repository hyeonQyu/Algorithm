package level1;

import java.util.Arrays;

class CharDesc implements Comparable<CharDesc> {
	char c;
	
	CharDesc(char c) {
		this.c = c;
	}

	@Override
	public int compareTo(CharDesc charDesc) {
		if(Character.isUpperCase(c)) {
			if(Character.isUpperCase(charDesc.c)) {
				// �� �� �빮��, ���� ��
				return compare(charDesc);
			}
			// ���� ���ڸ� �빮��
			return 1;
		}
		else if(Character.isUpperCase(charDesc.c)) {
			// ���ϴ� ���ڸ� �빮��
			return -1;
		}
		// �� �� �ҹ���, ���� ��
		return compare(charDesc);
	}
	
	private int compare(CharDesc charDesc) {
		if(c > charDesc.c)
			return -1;
		return 1;
	}
	
}

public class StringDesc {
	
	public String solution(String s) {
		CharDesc[] cs = new CharDesc[s.length()];
		
		for(int i = 0; i < cs.length; i++) {
			cs[i] = new CharDesc(s.charAt(i));
		}
		
		Arrays.sort(cs);
		
		String answer = "";
		for(int i = 0; i < cs.length; i++) {
			answer += Character.toString(cs[i].c);
		}
		
		return answer;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

}
