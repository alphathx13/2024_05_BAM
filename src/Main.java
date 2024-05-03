import java.util.*;

public class Main {
	public static void main(String[] args) {
		int last_number = 0;
		String cmd;

		System.out.println("== 프로그램 시작 ==");

		Scanner sc = new Scanner(System.in);

		List <Article> articles = new ArrayList<>();
		
		while (true) {
			System.out.print("명령어(모르면 help) : ");
			cmd = sc.nextLine().trim();
			
			if (cmd.equals("exit")) {
				break;
			} 
			
			else if(cmd.equals("article list")) {
				if (articles.size() == 0) {
					System.out.println("게시글이 존재하지 않습니다.");
					continue;
				}
				
				System.out.println("글 번호 \t 글 제목 \t 글 내용");
				
				for(Article article : articles) {
					System.out.printf("%d \t\t %s \t\t %s\n", article.article_number, article.title, article.body);
				}
			}
			
			else if(cmd.equals("article search")) {
				int num;
				boolean check = false;
				
				System.out.print("검색하실 글 번호를 입력하세요 : ");
				num = sc.nextInt();
				
				for(Article article : articles) {
					if(article.article_number == num) {
						System.out.println("글 번호 \t 글 제목 \t 글 내용");
						System.out.printf("%d \t\t %s \t\t %s\n", article.article_number, article.title, article.body);
						check = true;
						break;
					}
				}
				
				if (check == true) continue;
				
				System.out.println("해당 글번호의 게시글이 없습니다.");
			}
			
			else if(cmd.equals("article write")) {
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
				
				articles.add(new Article(last_number, title, body));
				
				System.out.printf("%d번 글이 생성 되었습니다.\n", last_number);
				
				break;
			
				}
			}
			
			else if (cmd.equals("help")) {
				System.out.println("article write : 게시글을 생성합니다.");
				System.out.println("article list : 게시글을 조회합니다.");
				System.out.println("article search : 특정 게시글을 검색합니다.");
				System.out.println("exit : 게시판을 종료합니다.");
			} 
		
			else if (cmd.equals("")) {
				System.out.println("명령어를 입력해 주세요.");
			}
			
			else {
				System.out.println("존재하지 않는 명령어 입니다.");
			}
		}

		sc.close();

		System.out.println("== 프로그램 종료 ==");
	}
}

class Article {
	int article_number;
	String title;
	String body;
	
	Article(int article_number, String title, String body) {
		this.article_number = article_number;
		this.title = title;
		this.body = body;
	}
}