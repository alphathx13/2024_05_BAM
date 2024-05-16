package com.koreaIT.BAM.service;

import com.koreaIT.BAM.dao.ArticleDao;

public class ArticleService {

	private ArticleDao articleDao;
	
	public ArticleService() {
		this.articleDao = new ArticleDao();
	}
	
	public int write_article(String title, String body, int member_number) {
		return articleDao.write_article(title, body, member_number);
	}

}
