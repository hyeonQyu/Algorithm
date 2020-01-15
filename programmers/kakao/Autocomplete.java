package kakao;

import java.util.HashMap;
import java.util.Iterator;

class TrieNode {
	
	char value;
	int includeWords;
	HashMap<Character, TrieNode> childs = new HashMap<>();
	TrieNode parent;
	int level;
	boolean isEnd = false;
	
	TrieNode(char value, int level) {
		this.value = value;
		this.level = level;
		includeWords = 0;
	}
	
	// �� �ܾ��� ������ ǥ��
	public void setLastValue() {
		includeWords++;
		isEnd = true;
		
		TrieNode tmpParent = parent;
		// ��Ʈ���� �ö󰡸鼭 includeWords �� ����
		while(tmpParent.value != ' ') {
			tmpParent.includeWords++;
			tmpParent = tmpParent.parent;
		}
	}
	
	public boolean setChild(TrieNode child) {
		if(!childs.containsKey(child.value)) {
			// �ڽ����� �߰�
			childs.put(child.value, child);
			child.parent = this;
			return true;
		}
		// �ڽ����� �߰����� ����
		return false;
	}
	
	public void traverse() {
		Iterator<Character> itr = childs.keySet().iterator();
		while(itr.hasNext()) {
			char key = itr.next();
			TrieNode node = childs.get(key);
			
			// Ʈ���̰� �������� ����(���� �ٸ� �ܾ�� ������ ����) Ȥ�� �ܾ� �ϳ��� ������ ����
			if((includeWords > node.includeWords && node.includeWords == 1) || (node.isEnd && !node.childs.isEmpty())) {
				Autocomplete.total += node.level;
			}		
			
			node.traverse();
		}
	}
	
}

class Trie {
	
	private TrieNode root = new TrieNode(' ', 0);
	private TrieNode currentNode;
	
	Trie() {
		root.includeWords = 30;
	}
	
	public void add(char value, boolean isLastValue) {		
		TrieNode newNode = new TrieNode(value, currentNode.level + 1);
		
		// ���ο� ��尡 ���� ����� �ڽ��� ��
		if(currentNode.setChild(newNode))
			currentNode = newNode;
		else
			currentNode = currentNode.childs.get(value);
		
		// �� �ܾ��� ������ ǥ��
		if(isLastValue)
			currentNode.setLastValue();
	}
	
	public void setFirst() {
		currentNode = root;
	}
	
	public void traverse() {
		root.traverse();
	}
	
}

public class Autocomplete {
	
	public static int total = 0; 
	
	public int solution(String[] words) {
		Trie trie = new Trie();
		int number = words.length;
		for(int i = 0; i < number; i++) {
			// Ʈ���̸� ó��(����)���� �ǵ�������
			trie.setFirst();
			
			int length = words[i].length();
			for(int j = 0; j < length; j++) {
				trie.add(words[i].charAt(j), j == length - 1);
			}
		}
		trie.traverse();
		
		return total;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] words = {"aac", "aad", "aae", "aacd"};
		System.out.println(new Autocomplete().solution(words));
	}

}