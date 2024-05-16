package com.koreaIT.BAM.dao;

import java.util.List;

import com.koreaIT.BAM.container.Container;
import com.koreaIT.BAM.dto.Article;

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
}
