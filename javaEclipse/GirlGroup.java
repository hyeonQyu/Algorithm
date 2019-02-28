package javaEclipse;
import java.util.Arrays;
import java.util.Scanner;

class GirlQuiz {
	int option;
	String teamOrMem;
}

public class GirlGroup {

	static void setTeamMembers(int nGirlGroups, String[] nameOfTeam, String[][] nameOfMember, Scanner scanner) {

		int nMembers;

		for (int i = 0; i < nGirlGroups; i++) {

//			System.out.print("���� �̸�? ");
			nameOfTeam[i] = scanner.next();
			while (nameOfTeam[i].length() > 100) {
//				System.out.print("���� �̸� ");
				nameOfTeam[i] = scanner.next();
			}

//			System.out.print("�ɱ׷� �ο� �� ");
			nMembers = scanner.nextInt();
			nameOfMember[i] = new String[nMembers];

//			System.out.println("��� �̸�");
			for (int j = 0; j < nMembers; j++) {
				nameOfMember[i][j] = scanner.next();
				if (nameOfMember[i][j].length() > 100) {
//					System.out.println("���ڼ� �ʰ�. �ٽ� �Է�");
					j--;
				}

				for (int k = 0; k < j; k++) {
					if (nameOfMember[i][k].equals(nameOfMember[i][j])) {
//						System.out.println("�̸� �ߺ�. �ٽ� �Է�");
						j--;
						break;
					}
				}
			}
		}
	}

	static void makeQuiz(int nQuiz, GirlQuiz[] quiz, Scanner scanner) {
//		System.out.println("���� �Է�");
		for (int i = 0; i < nQuiz; i++) {
			quiz[i] = new GirlQuiz();
			quiz[i].teamOrMem = scanner.next();
			quiz[i].option = scanner.nextInt();
		}
	}
	
	static void showMembers(String[] members) {
		for (int i = 0; i < members.length; i++) {
			Arrays.sort(members, String.CASE_INSENSITIVE_ORDER);
			System.out.println(members[i]);
		}
	}

	static void showAnswer(GirlQuiz[] quiz, String[] nameOfTeam, String[][] nameOfMember, Scanner scanner) {
		for (int i = 0; i < quiz.length; i++) {
			if (quiz[i].option == 0) {
				for (int j = 0; j < nameOfTeam.length; j++) {
					if (nameOfTeam[j].equals(quiz[i].teamOrMem))
						showMembers(nameOfMember[j]);
				}
			}
			else {
				for (int j = 0; j < nameOfMember.length; j++) {
					for (int k = 0; k < nameOfMember[j].length; k++) {
						if (nameOfMember[j][k].equals(quiz[i].teamOrMem))
							System.out.println(nameOfTeam[j]);
					}
				}
			}
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);

		int nGirlGroups, nQuiz;
		String[] nameOfTeam;
		String[][] nameOfMember;
		GirlQuiz[] quiz;

		nGirlGroups = 0;
		nQuiz = 0;

		while (1 > nGirlGroups || nGirlGroups > 99 || nQuiz < 1 || nQuiz > 99) {
	//		System.out.print("�ɱ׷� �� / ������ ��(1 ~ 99 ������ ��) ");
			nGirlGroups = scanner.nextInt();
			nQuiz = scanner.nextInt();
		}

		nameOfTeam = new String[nGirlGroups];
		nameOfMember = new String[nGirlGroups][];
		quiz = new GirlQuiz[nQuiz];

		setTeamMembers(nGirlGroups, nameOfTeam, nameOfMember, scanner);
		makeQuiz(nQuiz, quiz, scanner);
		showAnswer(quiz, nameOfTeam, nameOfMember, scanner);

		scanner.close();
	}

}
