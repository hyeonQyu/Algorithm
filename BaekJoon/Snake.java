import java.io.*;
import java.util.*;

public class Snake {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		byte[][] board = new byte[n][n];
		
		int loop = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for(int t = 0; t < loop; t++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			board[r][c] = 1;
		}
		
		loop = Integer.parseInt(br.readLine());
		int[] times = new int[loop];
		char[] vectors = new char[loop];
		for(int t = 0; t < loop; t++) {
			st = new StringTokenizer(br.readLine());
			times[t] = Integer.parseInt(st.nextToken());
			vectors[t] = st.nextToken().charAt(0);
		}
		
		LinkedList<int[]> snake = new LinkedList<>();
		int[] start = {0, 0};
		snake.add(start);
		board[0][0] = 2;
		
		int vector = 0;
		int index = 0;
		int time = 0;
		// 1�� ����ڸ�, 2�� ���� ��ġ�� ��
		while(true) {
			// ���� ����
			try {
				if(time == times[index]) {
					vector = getNextVector(vector, vectors[index]);
					index++;
				}				
			} catch (Exception e) {}
			
			time++;
			
			// �Ӹ� �̵�
			try {
				move(board, snake, vector);				
			} catch (Exception e) {
				break;
			}
		}
		
		System.out.println(time);
	}

	static int getNextVector(int vector, char rotation) {
		int next;
		if(rotation == 'D')
			next = (vector + 1) % 4;
		else
			next = (vector + 3) % 4;
		return next;
	}
	
	static void move(byte[][] board, LinkedList<int[]> snake, int vector) throws Exception{
		// ���� �Ӹ�
		int[] cur = snake.getFirst();
		
		int[] next = getNextHead(cur, vector);
		int nextBoard = board[next[0]][next[1]];
		
		// �Ӹ� �̵�
		snake.addFirst(next);
		board[next[0]][next[1]] = 2;
		// ���� ����
		if(nextBoard == 0) {
			int[] tail = snake.removeLast();
			board[tail[0]][tail[1]] = 0;
		}
		// �ڱ� �ڽſ� ����
		else if(nextBoard == 2)
			throw new Exception();
	}
	
	static int[] getNextHead(int[] cur, int vector) {
		int[] next = {cur[0], cur[1]};

		switch (vector) {
		case 0:
			next[1]++;
			break;		
		case 1:
			next[0]++;
			break;			
		case 2:
			next[1]--;
			break;			
		case 3:
			next[0]--;
			break;
		}
		
		return next;
	}
}
