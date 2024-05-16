package com.koreaIT.BAM.service;

import com.koreaIT.BAM.dao.ArticleDao;
import com.koreaIT.BAM.dto.Article;

public class ArticleService {
	private ArticleDao articleDao;
	
	public ArticleService() {
		this.articleDao = new ArticleDao();
	}
	
	public int write_article(String title, String body, int member_number) {
		return articleDao.write_article(title, body, member_number);
	}

	public void article_list(String cmd) {
		articleDao.article_list(cmd);
	}

	public void article_detail(String cmd) {
		articleDao.article_detail(cmd);
	}

	public int getNum(String cmd) {
		return articleDao.getNum(cmd);
	}

	public Article getArticle(int num) {
		return articleDao.getArticle(num);
	}

	public void article_delete(String cmd) {
		articleDao.article_delete(cmd);
	}

	public void article_modify(String[] title_body, Article found_article) {
		articleDao.article_modify(title_body, found_article);
	}

	public void article_write(String[] title_body) {
		articleDao.article_write(title_body);
	}
}