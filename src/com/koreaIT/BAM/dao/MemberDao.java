package com.koreaIT.BAM.dao;

import java.util.List;

import com.koreaIT.BAM.container.Container;
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
}