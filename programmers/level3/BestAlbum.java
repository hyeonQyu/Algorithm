package level3;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;

/* ������ ������ȣ�� ��� ���� ��� Ŭ���� */
class Music implements Comparable<Music> {
	int index;
	int play;
	
	Music(int index, int play) {
		this.index = index;
		this.play = play;
	}

	@Override
	// play�� ���Ͽ� ��������, index�� ���Ͽ� ��������
	public int compareTo(Music o) {
		if(play > o.play)
			return -1;
		else if(play < o.play)
			return 1;
		else {
			if(index < o.index)
				return -1;
			else
				return 1;
		}
	}
}

/* �帣 �� ��� ���� �� �帣�� ���� ���ǵ��� ��� Ŭ���� */
class Genre implements Comparable<Genre> {
	String genre;
	PriorityQueue<Music> musics = new PriorityQueue<>();
	int play;
	
	Genre(String genre) {
		this.genre = genre;
		play = 0;
	}
	
	void addMusic(Music music) {
		musics.add(music);
		play += music.play;
	}

	// ��Ʈ���� ���� ������������ ����
	@Override
	public int compareTo(Genre o) {
		return play > o.play ? -1 : 1; 
	}
}

public class BestAlbum {
	
	public static int[] solution(String[] genres, int[] plays) {
		ArrayList<Genre> genreList = new ArrayList<>();
		
		genreList.add(new Genre(genres[0]));
		genreList.get(0).addMusic(new Music(0, plays[0]));
		
		for(int i = 1; i < genres.length; i++) {
			Music music = new Music(i, plays[i]);
			boolean isExist = false;
			
			for(int j = 0; j < genreList.size(); j++) {
				Genre curGenre = genreList.get(j);				

				// ���� �뷡�� �帣�� �帣 ����Ʈ�� ����
				if(genres[i].equals(curGenre.genre)) {
					curGenre.addMusic(music);
					isExist = true;
					break;
				}
			}
			// ���� �뷡�� �帣�� �帣 ����Ʈ�� �����Ƿ� �߰�
			if(!isExist) {
				genreList.add(new Genre(genres[i]));
				genreList.get(genreList.size() - 1).addMusic(music);
			}
		}

		// ���� ���� ����� �帣 ������ ����
		genreList.sort(null);
		// ������ ������ ����Ʈ
		LinkedList<Integer> list = new LinkedList<>();
		for(int i = 0; i < genreList.size(); i++) {
			Genre curGenre = genreList.get(i);
			for(int j = 0; j < 2; j++) {
				// �帣�� �뷡�� �� �� �̻��̶�� ���� �� ���� �߰�, �� ���̶�� �� � �߰�
				try {
					list.add(curGenre.musics.poll().index);
				}
				catch(Exception e) {
				}
			}
		}
		
		// ����Ʈ�� �ִ� ������ �迭�� �ű�
		int[] answer = new int[list.size()];
		int index = 0;
		Iterator<Integer> itr = list.iterator();
		while(itr.hasNext()) {
			answer[index++] = itr.next();
		}
		return answer;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] genres = {"classic", "pop", "classic", "rock", "classic", "pop", "pop"};
		int[] plays = {10, 800, 30, 200, 70, 500, 500};
		int[] answer = solution(genres, plays);
		for(int i = 0; i < answer.length; i++) {
			System.out.println(answer[i]);
		}
	}

}
