package com.koreaIT.BAM;

import java.util.Scanner;

import com.koreaIT.BAM.controller.*;

public class App {

	public void run() {
		System.out.println("== 프로그램 시작 ==");

		Scanner sc = new Scanner(System.in);

		MemberController memberController = new MemberController(sc);
		memberController.test_data();
		ArticleController articleController = new ArticleController(sc);
		articleController.test_data();

		while (true) {
			String cmd;

			if (Controller.login_member != null)
				System.out.printf("%s - 명령어) : ", Controller.login_member.getName());
			else
				System.out.print("명령어) : ");

			cmd = sc.nextLine().trim();

			String[] cmds = cmd.split(" ");

			if (cmd.equals("exit"))
				break;

			String controller_name = cmds[0];
			String method_name = cmds[1];

			if (Controller.is_login() == false && (method_name.equals("write") || method_name.equals("modify") || method_name.equals("delete")) || method_name.equals("logout")) {
				System.out.println("해당 기능은 로그인이 필요합니다.");
				continue;
			} else if (Controller.is_login() == true && (method_name.equals("login") || method_name.equals("join"))) {
				System.out.println("해당 기능은 로그인 상태로 사용할 수 없습니다.");
				continue;
			}

			Controller controller = null;

			if (controller_name.equals("article")) {
				controller = articleController;
			} else if (controller_name.equals("member")) {
				controller = memberController;
			} else {
				System.out.println("명령어를 다시 입력해 주세요.");
				continue;
			}

			controller.cmd_check(cmd, method_name);

		}

		sc.close();
		System.out.println("== 프로그램 종료 ==");
	}
}