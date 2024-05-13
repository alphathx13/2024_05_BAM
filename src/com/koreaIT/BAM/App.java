package com.koreaIT.BAM;

import java.util.Scanner;

import com.koreaIT.BAM.controller.*;

public class App {

	public void run() {
		String cmd;

		System.out.println("== 프로그램 시작 ==");

		Scanner sc = new Scanner(System.in);

		MemberController memberController = new MemberController(sc);
		memberController.test_data();

		ArticleController articleController = new ArticleController(sc);
		articleController.test_data();

		while (true) {
			if (memberController.getLogin_member() != null)
				System.out.printf("%s - 명령어) : ", memberController.getLogin_member().getName());
			else
				System.out.print("명령어) : ");

			cmd = sc.nextLine().trim();

			String[] cmds = cmd.split(" ");

			if (cmd.equals("exit"))
				break;

			String controller_name = cmds[0];
			String method_name = cmds[1];

			Controller controller = null;

			if (controller_name.equals("article")) {
				controller = articleController;
			} else if (controller_name.equals("member")) {
				controller = memberController;
			} else {
				System.out.println("명령어를 다시 입력해 주세요.");
				continue;
			}

			controller.cmd_check(cmd, method_name, memberController.getLogin_member());

//			String[] cmds = input(sc);
//
//			Controller controller = null;
//			
//			if (cmds[0].equals("exit")) {
//				break;
//			} else if (cmds[0].equals("article")) {
//				controller = articleController;
//			} else if (cmds[0].equals("member")) {
//				controller = memberController;
//			} else {
//				System.out.println("명령어를 다시 입력해 주세요.");
//				continue;
//			}
//			
//			controller.cmd_check(cmds[1], cmds[2]);
		}

		sc.close();
		System.out.println("== 프로그램 종료 ==");
	}

//	private String[] input(Scanner sc) {
//		while (true) {
//			boolean error = false;
//			
//			System.out.print("명령어) : ");
//			String cmd = sc.nextLine();
//			String[] cmds = cmd.trim().split(" ");
//
//			for (int i = 2; i < cmds.length - 1; i++) {
//				if (!cmds[i].equals("")) {
//					error = true;
//					break;
//				}
//			}
//
//			if (!cmds[cmds.length - 1].chars().allMatch(Character::isDigit))
//				error = true;
//
//			if (error == true) {
//				System.out.println("명령어를 다시 입력해주세요.");
//				continue;
//			}
//
//			return cmds;
//		}
//	}
}