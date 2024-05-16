package com.koreaIT.BAM.service;

import com.koreaIT.BAM.dao.MemberDao;

public class MemberService {

	private MemberDao memberDao;
	
	public MemberService() {
		this.memberDao = new MemberDao();
	}

	public boolean id_dp_check(String id) {
		return memberDao.id_dp_check(id);
	}

	public void member_join(String id, String pass, String name) {
		memberDao.member_join(id, pass, name);
	}

	public void login(String input_id, String input_pass) {
		memberDao.login(input_id, input_pass);
	}

	public void member_list() {
		memberDao.member_list();
	}

}