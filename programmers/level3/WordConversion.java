package level3;

import java.util.PriorityQueue;

class Word implements Comparable<Word>, Cloneable {
	String word;
	int diffFromTarget;		// Ÿ�����κ��� �ٸ� �ڸ��� ��
	PriorityQueue<Word> nextWords = new PriorityQueue<>();
	int level;
	
	Word(String word, String target) {
		this.word = word;
		diffFromTarget = getDiffFromTarget(target);
		level = 0;
	}
	
	public void addNextWords(Word otherWord) {
		// �ٸ� �ڸ��� 1����� nextWord �켱���� ť�� �߰�
		if(getDiffFromTarget(otherWord.word) == 1)
			nextWords.add(otherWord);
	}
	
	private int getDiffFromTarget(String target) {
		int value = 0;
		
		for(int i = 0; i < target.length(); i++) {
			if(target.charAt(i) != this.word.charAt(i))
				value++;
		}
		
		return value;
	}
	
	// diffFromTarget(Ÿ�����κ��� �ٸ� �ڸ��� ��)�� ������������ ����
	@Override
	public int compareTo(Word w) {
		if(diffFromTarget > w.diffFromTarget)
			return 1;
		else
			return -1;
	}
	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}

public class WordConversion {
	
	public static int solution(String begin, String target, String[] words) throws CloneNotSupportedException {
		int cost = 0;
		
		boolean isExist = false;
		for(int i = 0; i < words.length; i++) {
			if(words[i].equals(target)) {
				isExist = true;
				break;
			}
		}
		// target�� words�迭�� �������� �����Ƿ� 0�� ��ȯ 
		if(!isExist)
			return 0;
		
		// Word Ŭ������ �ʱ�ȭ
		Word beginWord = new Word(begin, target);
		Word[] theWords = new Word[words.length];
		for(int i = 0; i < words.length; i++) {
			theWords[i] = new Word(words[i], target);
		}
		for(int i = 0; i < theWords.length; i++) {
			beginWord.addNextWords(theWords[i]);
			for(int j = 0; j < theWords.length; j++) {
				if(i != j) {
					theWords[i].addNextWords(theWords[j]);
				}
			}
		}
		
		// �ְ�켱Ž��(�ΰ����� ����2 ����)		
		PriorityQueue<Word> pq = new PriorityQueue<>();
		boolean isFinish = false;
		// ���� �ܾ �켱���� ť�� ����
		pq.add(beginWord);
		while(!pq.isEmpty() && !isFinish) {
			Word tmp = pq.poll();
			int level = tmp.level;

			while(!tmp.nextWords.isEmpty()) {
				// level ������ ���� ��ü ����
				Word tmp2 = (Word) tmp.nextWords.poll().clone();
				tmp2.level = level + 1;
				
				// target �ܾ���
				if(tmp2.diffFromTarget == 0) {
					isFinish = true;
					cost = tmp2.level;
					break;
				}
				
				// Ȯ���� ����� �ڽĵ��� �켱���� ť�� ����
				pq.add(tmp2);
			}
		}

		return cost;
	}

	public static void main(String[] args) throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		String[] words = {"hot", "dot", "lot", "dog", "log", "cog"};
		System.out.println(solution("hit", "cog", words));
	}

}
