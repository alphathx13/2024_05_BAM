package com.koreaIT.BAM.dao;

import java.time.format.DateTimeFormatter;
import java.util.List;

import com.koreaIT.BAM.container.Container;
import com.koreaIT.BAM.controller.Controller;
import com.koreaIT.BAM.dto.Member;

public class MemberDao {

	private List<Member> members;
	private int last_id;

	public MemberDao() {
		this.last_id = 1;
		this.members = Container.members;
	}

	public boolean id_dp_check(String id) {
		for (Member member : members) {
			if (member.getId().equals(id)) {
				return false;
			}
		}
		return true;
	}

	public void test_data(String id, String pass, String name) {
		members.add(new Member(last_id++, id, pass, name));
	}

	public void member_join(String id, String pass, String name) {
		members.add(new Member(last_id++, id, pass, name));
	}

	public void login(String input_id, String input_pass) {
		for (Member member : members) {
			if (member.getId().equals(input_id)) {
				if (member.getPass().equals(input_pass)) {
					System.out.println(member.getName() + "님 로그인을 환영합니다.");
					Controller.login_member = member;
					return;
				} else {
					System.out.println("암호가 일치하지 않습니다.");
					return;
				}
			}
		}

		System.out.println("해당 ID는 존재하지 않습니다. 회원가입을 해주세요.");
	}

	public void member_list() {
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
}