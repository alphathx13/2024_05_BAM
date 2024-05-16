package com.koreaIT.BAM.dao;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.koreaIT.BAM.container.Container;
import com.koreaIT.BAM.controller.Controller;
import com.koreaIT.BAM.dto.Article;
import com.koreaIT.BAM.dto.Member;
import com.koreaIT.BAM.util.Util;

public class ArticleDao {

	private List<Article> articles;
	private int last_id;

	public ArticleDao() {
		this.last_id = 1;
		this.articles = Container.articles;
	}

	public int write_article(String title, String body, int member_number) {
		articles.add(new Article(last_id, title, body, member_number, 0));
		return last_id++;
	}

	public void article_list(String cmd) {
		List<Article> list_search = new ArrayList<>();

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

		System.out.println("글 번호 \t 글 제목 \t 글 작성자 \t 글 조회수");

		for (int i = list_search.size() - 1; i >= 0; i--) {
			Article article = list_search.get(i);

			System.out.printf("%d \t\t %s \t %s \t\t %d\n", article.getArticle_number(), article.getTitle(),
					get_writer_by_member_number(article.getMember_number()), article.getView());
		}

	}

	private String get_writer_by_member_number(int id) {
		for (Member member : Container.members) {
			if (member.getMember_number() == id) {
				return member.getName();
			}
		}

		return null;
	}

	public void article_detail(String cmd) {
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
		System.out.printf("글 작성자 : %s\n", get_writer_by_member_number(found_article.getMember_number()));
		System.out.printf("글 작성시간 : %s\n",
				found_article.getWrite_time().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
		System.out.printf("글 수정시간 : %s\n",
				found_article.getUpdate_time().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
		System.out.printf("글 조회수 : %d\n", found_article.getView());

	}

	public int getNum(String cmd) {
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

	public Article getArticle(int num) {
		for (Article article : articles) {
			if (article.getArticle_number() == num) {
				return article;
			}
		}
		return null;
	}

	public void article_delete(String cmd) {
		int num = getNum(cmd);
		Article found_article = getArticle(num);

		if (found_article == null) {
			System.out.println(num + "번 게시글이 존재하지 않습니다.");
			return;
		} else if (num == 0)
			return;

		if (Controller.login_member.getMember_number() != found_article.getMember_number()) {
			System.out.println("글삭제는 글작성자만 가능합니다.");
			return;
		}

		articles.remove(found_article);
		System.out.println(num + "번 글을 삭제하였습니다.");
	}

	public void article_modify(String[] title_body, Article found_article) {
		found_article.setTitle(title_body[0]);
		found_article.setBody(title_body[1]);
		found_article.setUpdate_time(Util.getDateStr());

		System.out.println(found_article.getArticle_number() + "번 게시글을 수정하였습니다.");
	}

	public void article_write(String[] title_body) {		
		write_article(title_body[0], title_body[1], Controller.login_member.getMember_number());
		
		System.out.printf("%d번 글이 생성 되었습니다.\n", last_id);
	}	

}