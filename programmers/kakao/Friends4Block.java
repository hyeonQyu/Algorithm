package kakao;

public class Friends4Block {
	
	public int solution(int m, int n, String[] board) {
		// �ش� ���� �� ĭ �� ���� �Ʒ� ���� ����Ű�� �迭
		int[] lowRow = new int[n];
		char[][] blocks = new char[m][n];
		
		for(int i = 0; i < m; i++) {
			for(int j = 0; j < n; j++) {
				blocks[i][j] = board[i].charAt(j);
			}
		}
		
		boolean isDelete = false;
		int total = 0;
		while(true) {
			isDelete = false;
			char[][] tmp = new char[m][n];
			for(int i = 0; i < m; i++) {
				tmp[i] = blocks[i].clone();
			}
			
			// ��� ����
			for(int i = 1; i < m; i++) {
				for(int j = 1; j < n; j++) {
					if(blocks[i][j] == ' ')
						continue;
					// 2x2���·� 4�� ����� �پ�����
					if(blocks[i][j] == blocks[i-1][j] && blocks[i][j] == blocks[i][j-1] && blocks[i][j] == blocks[i-1][j-1]) {
						// �̹� �������� ���� ��Ͽ� ���ؼ��� ������ ��� �� ����
						tmp[i][j] = ' ';
						total++;
						if(tmp[i-1][j] != ' ') {
							tmp[i-1][j] = ' ';
							total++;
						}
						if(tmp[i][j-1] != ' ') {
							tmp[i][j-1] = ' ';
							total++;
						}
						if(tmp[i-1][j-1] != ' ') {
							tmp[i-1][j-1] = ' ';
							total++;
						}				
						
						lowRow[j-1] = i;
						lowRow[j] = i;
						isDelete = true;
					}
				}
			}
			
			// ���ŵ� ����� ����
			if(!isDelete)
				break;
			
			// ����� �Ʒ��� ����
			for(int i = m - 2; i >= 0; i--) {
				for(int j = 0; j < n; j++) {
					if(tmp[i][j] != ' ' && tmp[i+1][j] == ' ') {
						tmp[lowRow[j]][j] = tmp[i][j];
						tmp[i][j] = ' ';
						lowRow[j]--;
					}
				}
			}
			
			blocks = tmp;
		}
		
		return total;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] board = {"TTTANT", "RRFACC", "RRRFCC", "TRRRAA", "TTMMMF", "TMMTTJ"};
		System.out.println(new Friends4Block().solution(6, 6, board));
	}

}
