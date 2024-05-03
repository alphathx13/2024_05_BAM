import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		String cmd;

		System.out.println("=== 게시판을 시작합니다 ===");

		Scanner sc = new Scanner(System.in);

		while (true) {
			System.out.print("명령어를 입력하세요. (모를경우 help) : ");
			cmd = sc.nextLine();

			if (cmd.equals("exit")) {
				break;
			} else if (cmd.equals("help")) {
				System.out.println("add : 게시글을 생성합니다.");
				System.out.println("edit : 게시글을 수정합니다.");
				System.out.println("exit : 게시판을 종료합니다.");
			} else {
				System.out.println("존재하지 않는 명령어입니다. 다시 입력해주세요.");
			}
		}

		sc.close();

		System.out.println("=== 게시판을 종료합니다 ===");
	}
}