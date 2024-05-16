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
	
	public void test_data(String id, String pass, String name) {
		memberDao.test_data(id, pass, name);
	}

	public void member_join(String id, String pass, String name) {
		memberDao.member_join(id, pass, name);
	}
}