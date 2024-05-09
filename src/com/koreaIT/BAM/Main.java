package com.koreaIT.BAM;

import java.util.Scanner;

import com.koreaIT.BAM.util.Util;
import com.koreaIT.BAM.dto.*;

import java.util.List;
import java.util.ArrayList;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class Main {
	static int last_number;
	static List<Article> articles;
	
	static {
		articles = new ArrayList<>();
		last_number = 1;
	}
	// 얘네들은 메서드 안에 있는게 아니니까 지역변수 X 전역변수 O
	// 전역변수는 생성과 동시에 초기화를 하지않음 -> final은 제외(final은 초기화하지 않으면 아예 사용이 불가능하므로)
	// 얘네는 static 변수이기 때문에 static 초기화 블럭을 이용해서 초기화
	
	public static void main(String[] args) {
		String cmd;

		System.out.println("== 프로그램 시작 ==");

		test_article();

		Scanner sc = new Scanner(System.in);

		while (true) {
			System.out.print("명령어) : ");
			cmd = sc.nextLine().trim();

			if (cmd.equals("exit")) {
				break;
			}

			// 게시글 작성
			else if (cmd.equals("article write")) {
				String title;
				String body;

				while (true) {
					System.out.print("제목 : ");
					title = sc.nextLine().trim();

					if (title.length() == 0) {
						System.out.println("제목을 다시 입력해주세요.");
						continue;
					}

					while (true) {
						System.out.print("내용 : ");
						body = sc.nextLine().trim();

						if (body.length() == 0) {
							System.out.println("내용을 다시 입력해주세요.");
							continue;
						}

						break;
					}

					last_number++;

					articles.add(new Article(last_number, title, body, 0));

					System.out.printf("%d번 글이 생성 되었습니다.\n", last_number);

					break;

				}
			}

			// 게시글 조회
			else if (cmd.equals("article list")) {
				if (articles.size() == 0) {
					System.out.println("게시글이 존재하지 않습니다.");
					continue;
				}

				System.out.println("글 번호 \t 글 제목 \t\t 글 내용 \t\t 글 작성시간 \t\t\t 글 수정시간 \t\t\t 글 조회수");

//				일반적으로 게시글 조회는 최신순부터 조회해야하므로 향상된 for문으로 활용하기 어려움
//				for(Article article : articles) {
//					System.out.printf("%d \t\t %s \t\t %s\n", article.article_number, article.title, article.body);
//				}

				for (int i = articles.size() - 1; i >= 0; i--) {
					Article article = articles.get(i);
					System.out.printf("%d \t\t %s \t\t %s \t\t %s \t\t %s \t\t %d\n", article.getArticle_number(),
							article.getTitle(), article.getBody(),
							article.getWrite_time().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
							article.getUpdate_time().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
							article.getView());
				}
			}

			// 게시글 상세 조회
			else if (cmd.startsWith("article detail ")) {
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
					continue;
				}

				int num = Integer.parseInt(cmds[cmds.length - 1]);

				Article found_article = null;

				for (Article article : articles) {
					if (article.getArticle_number() == num) {
						found_article = article;
						break;
					}
				}

				if (found_article == null) {
					System.out.println(num + "번 게시글이 존재하지 않습니다.");
					continue;
				}

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

//			게시글 상세 조회
//			else if (cmd.equals("article detail")) {
//				String num;
//				boolean check = false;
//
//				System.out.print("검색하실 글 번호를 입력하세요 : ");
//				num = sc.nextLine();
//
//				for (Article article : articles) {
//					if (article.article_number == Integer.parseInt(num)) {
//						System.out.printf("글 번호 : %d\n", article.article_number);
//						System.out.printf("글 제목 : %s\n", article.title);
//						System.out.printf("글 내용 : %s\n", article.body);
//						System.out.printf("글 작성시간 : %s\n", article.write_time);
//						System.out.printf("글 수정시간 : %s\n", article.update_time);
//						check = true;
//						break;
//					}
//				}
//
//				if (check == true)
//					continue;
//
//				System.out.println("해당 글번호의 게시글이 존재하지 않습니다.");
//			}

			// 게시글 수정
			else if (cmd.startsWith("article modify ")) {
				String[] cmds = cmd.split(" ");

				if (!cmds[2].chars().allMatch(Character::isDigit) || cmds[2].equals("")) {
					System.out.println("명령어를 다시 입력해주세요.");
					continue;
				}

				int num = Integer.parseInt(cmds[2]);

				Article found_article = null;

				for (Article article : articles) {
					if (article.getArticle_number() == num) {
						found_article = article;
						break;
					}
				}

				if (found_article == null) {
					System.out.println(num + "번 게시글이 존재하지 않습니다.");
					continue;
				}

				while (true) {
					System.out.printf("기존 글 제목 : %s\n", found_article.getTitle());
					System.out.print("-> 수정하려는 제목을 입력해 주세요 : ");
					String title_new = sc.nextLine().trim();

					if (title_new.length() == 0) {
						System.out.println("제목을 다시 입력해주세요.");
						continue;
					}

					while (true) {
						System.out.printf("기존 글 내용 : %s\n", found_article.getBody());
						System.out.print("-> 수정하려는 내용을 입력해 주세요 : ");
						String body_new = sc.nextLine().trim();

						if (body_new.length() == 0) {
							System.out.println("내용을 다시 입력해주세요.");
							continue;
						}

						found_article.setTitle(title_new);
						found_article.setBody(body_new);
						found_article.setUpdate_time(Util.getDateStr());

						break;
					}

					break;
				}
			}

			// 게시글 삭제
			else if (cmd.startsWith("article delete ")) {
				String[] cmds = cmd.split(" ");

				if (!cmds[2].chars().allMatch(Character::isDigit) || cmds[2].equals("")) {
					System.out.println("명령어를 다시 입력해주세요.");
					continue;
				}

				int num = Integer.parseInt(cmds[2]);

				Article found_article = null;

				for (Article article : articles) {
					if (article.getArticle_number() == num) {
						found_article = article;
						break;
					}
				}

				// index를 이용하는 방법도 넣기

				if (found_article == null)
					System.out.println(cmds[2] + "번 게시글이 존재하지 않습니다.");

				articles.remove(found_article);
				System.out.println(num + "번 글을 삭제하였습니다.");
			}

			else if (cmd.trim().equals("")) {
				System.out.println("명령어를 입력해 주세요.");
			}

			else {
				System.out.println("존재하지 않는 명령어 입니다.");
			}
		}

		sc.close();

		System.out.println("== 프로그램 종료 ==");

	}

	private static void test_article() {
		for (int i = 1; i < 10; i++)
			articles.add(new Article((last_number++), "제목 " + i, "내용 " + i, i * 10));
	}

}

