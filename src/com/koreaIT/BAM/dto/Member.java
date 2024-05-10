package com.koreaIT.BAM.dto;

import com.koreaIT.BAM.util.*;

import java.time.LocalDateTime;

public class Member {
	private int member_number;
	private String id;
	private String pass;
	private String name;
	private LocalDateTime reg_time;

	public int getMember_number() {
		return member_number;
	}

	public void setMember_number(int member_number) {
		this.member_number = member_number;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDateTime getReg_time() {
		return reg_time;
	}

	public void setReg_time(LocalDateTime reg_time) {
		this.reg_time = reg_time;
	}

	public Member(int member_number, String id, String pass, String name) {
		this.member_number = member_number;
		this.id = id;
		this.pass = pass;
		this.name = name;
		this.reg_time = Util.getDateStr();
	}
}