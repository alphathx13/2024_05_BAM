package com.koreaIT.BAM.dto;

import com.koreaIT.BAM.util.*;

import java.time.Duration;
import java.time.LocalDateTime;

public class Article {
	private int article_number;
	private int view = 0;
	private LocalDateTime view_time = Util.getDateStr();
	private String title;
	private String body;
	private int member_number;
	private LocalDateTime write_time;
	private LocalDateTime update_time;

	public int getArticle_number() {
		return article_number;
	}

	public void setArticle_number(int article_number) {
		this.article_number = article_number;
	}

	public int getView() {
		return view;
	}

	public void setView(int view) {
		this.view = view;
	}

	public LocalDateTime getView_time() {
		return view_time;
	}

	public void setView_time(LocalDateTime view_time) {
		this.view_time = view_time;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public int getMember_number() {
		return member_number;
	}

	public void setMember_number(int member_number) {
		this.member_number = member_number;
	}

	public LocalDateTime getWrite_time() {
		return write_time;
	}

	public void setWrite_time(LocalDateTime write_time) {
		this.write_time = write_time;
	}

	public LocalDateTime getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(LocalDateTime update_time) {
		this.update_time = update_time;
	}

	public Article(int article_number, String title, String body, int member_number, int view) {
		this.article_number = article_number;
		this.title = title;
		this.body = body;
		this.view = view;
		this.member_number = member_number;
		this.write_time = Util.getDateStr();
		this.update_time = Util.getDateStr();
	}

	public void increase_view() {
		Duration temp = Duration.between(this.view_time, Util.getDateStr());
		long diff = temp.toSeconds();

		if (diff >= 3) {
			this.view++;
			this.view_time = Util.getDateStr();
		}
	}

//	날짜를 string으로 저장했을때
//	void increase_view() {
//		int tempA = Integer.parseInt(Util.getDateStr().substring(17, 19));
//		int tempB = Integer.parseInt(this.view_time.substring(17, 19));
//
//		if (Math.abs(tempA - tempB) >= 3 || this.view_time.equals(null)) {
//			this.view++;
//			this.view_time = Util.getDateStr();
//		}
//	}

}