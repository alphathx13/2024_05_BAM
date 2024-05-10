package com.koreaIT.BAM;

import java.util.Scanner;

import com.koreaIT.BAM.controller.*;

public class App {

	public void run() {
		String cmd;

		System.out.println("== 프로그램 시작 ==");

		Scanner sc = new Scanner(System.in);
		
		MemberController memberController = new MemberController(sc);
		memberController.test_member();
		
		ArticleController articleController = new ArticleController(sc);
		articleController.test_article();

		while (true) {
			System.out.print("명령어) : ");
			cmd = sc.nextLine().trim();

			if (cmd.equals("exit")) {
				break;
			}

			// 회원 가입
			else if (cmd.equals("member join")) {
				memberController.join();
			}

			// 멤버 리스트
			else if (cmd.equals("member list")) {
				memberController.list();
			}

			// 게시글 작성
			else if (cmd.equals("article write")) {
				articleController.write();
			}

			// 게시글 조회
			else if (cmd.startsWith("article list")) {
				articleController.list(cmd);
			}

			// 게시글 상세 조회
			else if (cmd.startsWith("article detail ")) {
				articleController.detail(cmd);
			}

			// 게시글 수정
			else if (cmd.startsWith("article modify ")) {
				articleController.modify(cmd);
				
			}

			// 게시글 삭제
			else if (cmd.startsWith("article delete ")) {
				articleController.delete(cmd);
			}

			else {
				System.out.println("명령어를 다시 입력해 주세요.");
			}
		}

		sc.close();

		System.out.println("== 프로그램 종료 ==");

	}
}