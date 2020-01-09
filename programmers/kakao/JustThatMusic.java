package kakao;

import java.util.PriorityQueue;

class MusicInfo implements Comparable<MusicInfo> {
	String startTime;
	String endTime;
	String title;
	String tone;
	int playTime;
	StringBuilder playTone = new StringBuilder();
	int startHour, startMinute, endHour, endMinute;
	
	MusicInfo(String knownTone) {
		String[] arr = knownTone.split(",");
		
		startTime = arr[0];
		endTime = arr[1];
		title = arr[2];
		tone = arr[3];
		
		getPlayTime();
		getPlayTone();
	}
	
	// ������ ����� �ð�
	private void getPlayTime() {		
		String[] start = startTime.split(":");
		String[] end = endTime.split(":");
		
		startHour = Integer.parseInt(start[0]);
		startMinute = Integer.parseInt(start[1]);
		endHour = Integer.parseInt(end[0]);
		endMinute = Integer.parseInt(end[1]);
		
		if(endMinute >= startMinute) {
			playTime = 60 * (endHour - startHour) + endMinute - startMinute;
			return;
		}
		endHour--;
		endMinute += 60;
		playTime = 60 * (endHour - startHour) + endMinute - startMinute;
	}
	
	// ����� �ð������� ��
	private void getPlayTone() {
		int i = 0;
		int play = playTime;
		int last = tone.length();
		
		while(play >= 0) {
			if(i == last) {
				i = 0;
				continue;
			}
			
			playTone.append(tone.charAt(i));
			
			try {				
				if(tone.charAt(i + 1) == '#') {
					playTone.append(tone.charAt(++i));
				}
			} catch(Exception e) {}
			
			i++;
			play--;
		}
	}

	// ����ð� ��������, �Էµ� �ð� ��������
	@Override
	public int compareTo(MusicInfo info) {
		int compare = -Integer.compare(playTime, info.playTime);
		if(compare == 0) {
			compare = Integer.compare(startHour, info.startHour);
			if(compare == 0) {
				return Integer.compare(startMinute, info.startMinute);
			}
		}
		return compare;
	}
}

public class JustThatMusic {
	
	public String solution(String m, String[] musicinfos) {
		PriorityQueue<MusicInfo> musics = new PriorityQueue<>();

		for(int i = 0; i < musicinfos.length; i++) {
			musics.add(new MusicInfo(musicinfos[i]));
		}
		
		while(!musics.isEmpty()) {
			MusicInfo music = musics.poll();
			
			if(isInclude(music.playTone.toString(), m))
				return music.title;
		}	
		
		return "(None)";
	}
	
	// ABC�� ABC#�� ���ԵǾ��ٰ� �Ǵ��ϸ� �ȵǱ� ������ String.contains�� ������� ����
	boolean isInclude(String playTone, String knownTone) {
		int length = knownTone.length();
		
		for(int i = 0; i < playTone.length(); i++) {
			if(playTone.charAt(i) == knownTone.charAt(0)) {
				try {
					// knownTone�� playTone�� ���Ե�
					if(knownTone.equals(playTone.substring(i, i + length))) {
						// knownTone�� ���� playTone�� �κй��ڿ����� ������ ���� ������ #�̸� �������� �ʴ� ����
						if(playTone.charAt(i + length) != '#') {
							return true;
						}
					}
				} catch(Exception e) {}
			}
		}
		
		return false;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] musicinfos = {"12:00,12:14,HELLO,CDEFGAB", "13:00,13:05,WORLD,ABCDEF"};
		new JustThatMusic().solution("ABCDEFG", musicinfos);
	}
}
