package com.koreaIT.BAM.controller;

import java.util.Scanner;

import com.koreaIT.BAM.dto.Member;

public abstract class Controller {
	public int last_id;
	public String cmd;
	public Scanner sc;
	
	public abstract void cmd_check(String cmds1, String cmds2, Member login_member);
	public abstract void test_data();
}