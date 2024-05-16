package com.koreaIT.BAM.controller;

import java.util.Scanner;

import com.koreaIT.BAM.dto.Article;
import com.koreaIT.BAM.service.ArticleService;

public class ArticleController extends Controller {
	private ArticleService articleService;

	public ArticleController(Scanner sc) {
		this.sc = sc;
		articleService = new ArticleService();
	}

	@Override
	public void cmd_check(String cmd, String method_name) {
		this.cmd = cmd;

		switch (method_name) {
		case "write":
			this.article_write();
			break;

		case "list":
			this.article_list();
			break;

		case "detail":
			this.article_detail();
			break;

		case "modify":
			this.article_modify();
			break;

		case "delete":
			this.article_delete();
			break;

		default:
			System.out.println("명령어를 다시 입력해 주세요.");
		}
	}

	private void article_write() {
		articleService.article_write(getTitleBody());
	}

	private void article_list() {
		articleService.article_list(cmd);
	}

	private void article_detail() {
		articleService.article_detail(cmd);
	}

	private void article_modify() {
		int num = articleService.getNum(cmd);
		Article found_article = articleService.getArticle(num);

		if (num == 0)
			return;
		else if (found_article == null) {
			System.out.println(num + "번 게시글이 존재하지 않습니다.");
			return;
		}

		if (Controller.login_member.getMember_number() != found_article.getMember_number()) {
			System.out.println("글수정은 글작성자만 가능합니다.");
			return;
		}

		String[] title_body = this.getTitleBody();
		
		articleService.article_modify(title_body, found_article);
	}

	private void article_delete() {
		articleService.article_delete(cmd);
	}
	
	private String[] getTitleBody() {
		String[] result = new String[2];

		while (true) {
			System.out.print("제목 : ");
			result[0] = sc.nextLine().trim();

			if (result[0].length() == 0) {
				System.out.println("제목을 다시 입력해주세요.");
				continue;
			}

			while (true) {
				System.out.print("내용 : ");
				result[1] = sc.nextLine().trim();

				if (result[1].length() == 0) {
					System.out.println("내용을 다시 입력해주세요.");
					continue;
				}

				break;
			}

			break;
		}

		return result;
	}

	@Override
	public void test_data() {
		for (int i = 1; i <= 5; i++)
			articleService.write_article("제목 " + i, "내용 " + i, ((int) (Math.random() * 100) % 5 + 1));

		System.out.println("테스트 게시글 5개 생성 완료");
	}
}