package kakao;

import java.util.Arrays;

class BTNode implements Comparable<BTNode> {
	int id;
	// ��� ���� ���� �ٸ� x�� �����Ƿ� x�� key, y�� level
	int key;
	int level;
	BTNode parent, left, right;
	
	BTNode(int id, int x, int y) {
		this.id = id;
		key = x;
		level = y;
		// id�� 0�� ���� null �����
		parent = null;
		left = null;
		right = null;
	}
	
	void setLeftChild(BTNode node) {
		left = node;
		node.parent = this;
	}
	
	void setRightChild(BTNode node) {
		right = node;
		node.parent = this;
	}

	@Override
	public int compareTo(BTNode node) {
		// level�� ���ؼ� ��������, key�� ���ؼ� ��������
		if(node.level != level)
			return -Integer.compare(level, node.level);
		return Integer.compare(key, node.key);
	}
}

public class FindPathGame {
	
	int[][] answer;
	int index;
	
	private void preOrder(BTNode node) {
		if(node == null)
			return;
		
		answer[0][index++] = node.id;
		preOrder(node.left);
		preOrder(node.right);
	}
	
	private void postOrder(BTNode node) {
		if(node == null)
			return;
		
		postOrder(node.left);
		postOrder(node.right);
		answer[1][index++] = node.id;
	}
	
	public int[][] solution(int[][] nodeinfo){		
		int n = nodeinfo.length;
		answer = new int[2][n];
		
		if(n == 1) {
			answer[0][0] = answer[1][0] = 1;
			return answer;
		}
		
		BTNode[] nodes = new BTNode[n];
		// ��� �ʱ�ȭ
		for(int i = 0; i < n; i++) {
			nodes[i] = new BTNode(i + 1, nodeinfo[i][0], nodeinfo[i][1]);
		}
		Arrays.sort(nodes);
		
		for(int i = 1; i < n; i++) {
			BTNode tmp = nodes[0];
			while(true) {
				// ����
				if(tmp.key > nodes[i].key) {
					// ���� �ڽ��� ����
					if(tmp.left == null) {
						tmp.setLeftChild(nodes[i]);
						break;
					}
					// ���ʿ� �ڽ��� ����
					else {
						tmp = tmp.left;
					}
				}
				// ������
				else {
					// �����ʿ� �ڽ��� ����
					if(tmp.right == null) {
						tmp.setRightChild(nodes[i]);
						break;
					}
					// �����ʿ� �ڽ��� ����
					else {
						tmp = tmp.right;
					}
				}
			}
		}
		
		index = 0;
		preOrder(nodes[0]);
		index = 0;
		postOrder(nodes[0]);
		
		return answer;
	}
}
