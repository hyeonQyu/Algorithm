package level1;

public class CaesarCipher {
	
	static int getNewCode(int code, int limit, int n) {
		int newCode = code + n;
		
		if(newCode > limit)
			newCode -= 26;
		
		return newCode;
	}
	
	public static String solution(String s, int n) {
		String answer = "";
		
		for(int i = 0; i < s.length(); i++) {
			int code = (int)s.charAt(i);
			char c = ' ';
			
			if(65 <= code && code <= 90) {
				// �빮��
				c = (char)getNewCode(code, 90, n);
			}
			else if(97 <= code && code <= 122) {
				// �ҹ���
				c = (char)getNewCode(code, 122, n);
			}
			
			answer += c;
		}
		return answer;
	}

}
