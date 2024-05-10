package com.koreaIT.BAM.controller;

import java.util.Scanner;
import java.util.List;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import com.koreaIT.BAM.dto.Article;
import com.koreaIT.BAM.util.Util;

public class ArticleController {
	private int last_number;
	private List<Article> articles;
	private Scanner sc;

	public ArticleController(Scanner sc) {
		this.sc = sc;
		this.last_number = 1;
		this.articles = new ArrayList<>();
	}

	public void write() {
		String[] title_body = this.getTitleBody();

		articles.add(new Article(last_number, title_body[0], title_body[1], 0));

		System.out.printf("%d번 글이 생성 되었습니다.\n", this.last_number);

		this.last_number++;
	}

	public void list(String cmd) {
		List<Article> list_search = new ArrayList<>();
		
		System.out.println(cmd.substring("article list".length()).trim());

		if (cmd.length() == 12) 
			list_search = articles;

		else {
			String search = cmd.substring("article list".length()).trim();

			for (Article article : articles) {
				if (article.getTitle().contains(search))
					list_search.add(article);
			}
		}

		if (list_search.size() == 0) {
			System.out.println("결과가 존재하지 않습니다.");
			return;
		}

		System.out.println("글 번호 \t 글 제목 \t\t 글 내용 \t\t 글 작성시간 \t\t\t 글 수정시간 \t\t\t 글 조회수");

		for (int i = list_search.size() - 1; i >= 0; i--) {
			Article article = list_search.get(i);
			System.out.printf("%d \t\t %s \t\t %s \t\t %s \t\t %s \t\t %d\n", article.getArticle_number(),
					article.getTitle(), article.getBody(),
					article.getWrite_time().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
					article.getUpdate_time().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
					article.getView());
		}

	}

	public void detail(String cmd) {
		int num = this.getNum(cmd);
		Article found_article = this.getArticle(num);

		if (found_article == null) {
			System.out.println(num + "번 게시글이 존재하지 않습니다.");
			return;
		} else if (num == 0)
			return;

		found_article.increase_view();

		System.out.printf("글 번호 : %d\n", found_article.getArticle_number());
		System.out.printf("글 제목 : %s\n", found_article.getTitle());
		System.out.printf("글 내용 : %s\n", found_article.getBody());
		System.out.printf("글 작성시간 : %s\n",
				found_article.getWrite_time().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
		System.out.printf("글 수정시간 : %s\n",
				found_article.getUpdate_time().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
		System.out.printf("글 조회수 : %d\n", found_article.getView());

	}

	public void modify(String cmd) {
		int num = this.getNum(cmd);
		Article found_article = this.getArticle(num);

		if (num == 0)
			return;
		else if (found_article == null) {
			System.out.println(num + "번 게시글이 존재하지 않습니다.");
			return;
		}

		String[] title_body = this.getTitleBody();

		found_article.setTitle(title_body[0]);
		found_article.setBody(title_body[1]);
		found_article.setUpdate_time(Util.getDateStr());

		System.out.println(num + "번 게시글을 수정하였습니다.");

	}

	public void delete(String cmd) {
		int num = this.getNum(cmd);
		Article found_article = this.getArticle(num);

		if (found_article == null) {
			System.out.println(num + "번 게시글이 존재하지 않습니다.");
			return;
		} else if (num == 0)
			return;

		articles.remove(found_article);
		System.out.println(num + "번 글을 삭제하였습니다.");

	}

	private Article getArticle(int num) {

		for (Article article : articles) {
			if (article.getArticle_number() == num) {
				return article;
			}
		}

		return null;
	}

	private int getNum(String cmd) {
		boolean error = false;
		String[] cmds = cmd.trim().split(" ");

		for (int i = 2; i < cmds.length - 1; i++) {
			if (!cmds[i].equals("")) {
				error = true;
				break;
			}
		}

		if (!cmds[cmds.length - 1].chars().allMatch(Character::isDigit))
			error = true;

		if (error == true) {
			System.out.println("명령어를 다시 입력해주세요.");
			return 0;
		}

		return Integer.parseInt(cmds[cmds.length - 1]);
	}
	
	public void test_article() {
		for (int i = 1; i <= 5; i++)
			articles.add(new Article((last_number++), "제목 " + i, "내용 " + i, i * 10));

		System.out.println("테스트 게시글 5개 생성 완료");
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

}