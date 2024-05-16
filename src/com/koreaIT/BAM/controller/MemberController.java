package com.koreaIT.BAM.controller;

import java.util.Scanner;

import com.koreaIT.BAM.service.MemberService;

public class MemberController extends Controller {
	private MemberService memberService;

	public MemberController(Scanner sc) {
		this.sc = sc;
		memberService = new MemberService();
	}	

	@Override
	public void cmd_check(String cmd, String method_name) {
	
		switch (method_name) {
		case "join":
			this.member_join();
			break;
		case "list":
			this.member_list();
			break;
		case "login":
			this.login();
			break;
		case "logout":
			this.logout();
			break;
		default:
			System.out.println("명령어를 다시 입력해 주세요.");
		}
	}
	
	private void login() {
		String input_id;
		String input_pass;

		System.out.print("아이디 ) ");
		input_id = sc.nextLine().trim();
		System.out.print("비밀번호 ) ");
		input_pass = sc.nextLine().trim();

		memberService.login(input_id, input_pass);	
	}
	
	private void logout() {
		System.out.printf("%s님이 로그아웃 되었습니다.\n", login_member.getName());
		Controller.login_member = null;
	}

	public void member_join() {
		String id = null;
		String pass = null;
		String name = null;

		// id 확인
		while (true) {
			System.out.print("사용하려는 ID : ");
			id = sc.nextLine().trim();

			if (id.length() == 0) {
				System.out.println("ID를 다시 입력해주세요.");
				continue;
			}

			if (memberService.id_dp_check(id) == false) {
				System.out.println("해당 ID는 이미 존재합니다. 다시 입력해주세요");
				continue;
			}

			System.out.println("[" + id + "] 은(는) 사용가능한 아이디입니다.");

			break;
		}

		// 비밀번호 확인
		while (true) {
			System.out.print("사용하려는 비밀번호 : ");
			pass = sc.nextLine().trim();

			if (pass.length() == 0) {
				System.out.println("비밀번호를 다시 입력해주세요.");
				continue;
			}

			System.out.print("입력한 비밀번호를 확인 : ");
			String temp = sc.nextLine().trim();

			if (!temp.equals(pass)) {
				System.out.println("입력하신 비밀번호와 다릅니다.");
				continue;
			}

			break;
		}

		// 이름 확인
		while (true) {
			System.out.print("이름을 입력해주세요 : ");
			name = sc.nextLine().trim();

			if (name.trim().length() == 0) {
				System.out.println("이름에 오류가 있습니다.");
				continue;
			}

			break;
		}

		memberService.member_join(id, pass, name);

		System.out.printf("[%s]번 회원님이 가입되었습니다.\n", id);
	}

	private void member_list() {
		memberService.member_list();
	}

	@Override
	public void test_data() {
		for (int i = 1; i <= 5; i++)
			memberService.member_join("user" + i, "user" + i, "유저" + i);

		System.out.println("테스트 멤버 5명 생성 완료");
	}
}