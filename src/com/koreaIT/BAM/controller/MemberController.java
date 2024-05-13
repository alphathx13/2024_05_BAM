package com.koreaIT.BAM.controller;

import java.util.List;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import com.koreaIT.BAM.dto.Member;

public class MemberController extends Controller {
	private Member login_member;
	private List<Member> members;

	public MemberController(Scanner sc) {
		this.login_member = null;
		this.sc = sc;
		this.last_id = 1;
		this.members = new ArrayList<>();
	}	

	public Member getLogin_member() {
		return login_member;
	}

	@Override
	public void cmd_check(String cmd, String method_name, Member login_member) {

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
		if (login_member != null) {
			System.out.printf("이미 '%s'님이 로그인 되어있습니다.\n", login_member);
			return;
		}
		
		String input_id;
		String input_pass;

		System.out.print("아이디 ) ");
		input_id = sc.nextLine().trim();
		System.out.print("비밀번호 ) ");
		input_pass = sc.nextLine().trim();

		for (Member member : members) {
			if (member.getId().equals(input_id)) {
				if (member.getPass().equals(input_pass)) {
					System.out.println(member.getName() + "님 로그인을 환영합니다.");
					login_member = member;
					return;
				} else {
					System.out.println("암호가 일치하지 않습니다.");
					return;
				}
			}
		}
		
		System.out.println("해당 ID는 존재하지 않습니다. 회원가입을 해주세요.");
	}
	
	private void logout() {
		System.out.printf("%s님이 로그아웃 되었습니다.\n", login_member.getName());
		login_member = null;
	}

	public void member_join() {
		String id = null;
		String pass = null;
		String name = null;

		//// id 확인
		while (true) {
			System.out.print("사용하려는 ID : ");
			id = sc.nextLine().trim();

			if (id.length() == 0) {
				System.out.println("ID를 다시 입력해주세요.");
				continue;
			}

			if (id_dp_check(id) == false) {
				System.out.println("해당 ID는 이미 존재합니다. 다시 입력해주세요");
				continue;
			}

			System.out.println("[" + id + "] 은(는) 사용가능한 아이디입니다.");

			break;
		}

		//// 비밀번호 확인
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

		//// 이름 확인
		while (true) {
			System.out.print("이름을 입력해주세요 : ");
			name = sc.nextLine().trim();

			if (name.trim().length() == 0) {
				System.out.println("이름에 오류가 있습니다.");
				continue;
			}

			break;
		}

		members.add(new Member(last_id, id, pass, name));

		System.out.printf("[%s]번 회원님이 가입되었습니다.\n", id);
		last_id++;
	}

	private boolean id_dp_check(String id) {
		for (Member member : members) {
			if (member.getId().equals(id)) {
				return false;
			}
		}
		return true;
	}

	private void member_list() {
		if (members.size() == 0) {
			System.out.println("회원이 존재하지 않습니다.");
			return;
		}

		System.out.println("회원 번호 \t 회원 아이디 \t 회원 이름 \t 회원 가입시간");

		for (int i = members.size() - 1; i >= 0; i--) {
			Member member = members.get(i);
			System.out.printf("%d \t\t %s \t\t %s \t\t %s\n", member.getMember_number(), member.getId(),
					member.getName(), member.getReg_time().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
		}

	}

	@Override
	public void test_data() {
		for (int i = 1; i <= 5; i++)
			members.add(new Member((last_id++), "user" + i, "user" + i, "유저" + i));

		System.out.println("테스트 멤버 5명 생성 완료");
	}
}