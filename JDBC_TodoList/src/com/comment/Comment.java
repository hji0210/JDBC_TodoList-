package com.comment;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import com.community.Community;
import com.controller.CommentController;
import com.controller.CommunityController;
import com.controller.FeedController;
import com.dao.CommentDAO;
import com.dao.CommunityDAO;
import com.dao.TodoDAO;
import com.vo.CommentVO;
import com.vo.CommunityVO;
import com.vo.TodoVO;

public class Comment {

	public void commentRun(int cSeq, String writerId,int cCnt, String loginId) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		Community community = new Community();
		CommentController cmc = new CommentController();
		CommunityController cc = new CommunityController();
		FeedController fc = new FeedController();

		CommunityVO cvo = null;

		String checkBox = "";
		List<CommentVO> commentList = new ArrayList<CommentVO>();
		List<TodoVO> todoList = new ArrayList<TodoVO>();

		
		System.out.println();
		System.out.println();

		cc.plusCnt(cSeq,cCnt);
		cvo = cc.selectByCSeq(cSeq);
		commentList = cmc.selectAll(cSeq);
		todoList = fc.todayRead(writerId);
		

		// =================================

	

			System.out.println("\r\n" + " _____                                       _ _         \r\n"
					+ "/  __ \\                                     (_) |        \r\n"
					+ "| /  \\/ ___  _ __ ___  _ __ ___  _   _ _ __  _| |_ _   _ \r\n"
					+ "| |    / _ \\| '_ ` _ \\| '_ ` _ \\| | | | '_ \\| | __| | | |\r\n"
					+ "| \\__/\\ (_) | | | | | | | | | | | |_| | | | | | |_| |_| |\r\n"
					+ " \\____/\\___/|_| |_| |_|_| |_| |_|\\__,_|_| |_|_|\\__|\\__, |\r\n"
					+ "                                                    __/ |\r\n"
					+ "                                                   |___/ \r\n" + "");

			System.out.println(" |￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣|\r\n" 				
					+ "          \r\n" + "     [작성자: " + cvo.getMId() + " | " + cvo.getCDate()+ " | 조회수 :" + cvo.getCCnt() + "]\r\n"
					+ "\r\n     TITLE : " + cvo.getCTitle() + " \r\n"
					 + "\r\n     CONTENT : " + cvo.getCContent() + "\r\n"
					+ "\r\n" + " |＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿|\r\n"
					+ "                       \\ (•◡•) /\r\n"

					+ "");

			if (todoList.size() == 0) {
				System.out.println("   # 오늘 할일이 없습니다.");
			} else {
				
				System.out.println(" ┏━━━━━━━[" + cvo.getMId() + "님의 Todo List]━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
				System.out.println(" ┃");
				for (int i = 0; i < todoList.size(); i++) {

					System.out.print("   " + (i + 1));
					System.out.print(". " + todoList.get(i).getContent());
					System.out.print(" / " + todoList.get(i).getDate());
					if (todoList.get(i).getCheck().contains("Y")) {
						checkBox = "√";
					} else if (todoList.get(i).getCheck().contains("N")) {
						checkBox = "X";
					}

					System.out.print(" / " + checkBox);
					System.out.println();

				}
				System.out.println(" ┃");
				System.out.println(" ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");

			}

			System.out.println();

			for (CommentVO vo : commentList) {
				System.out.println("   └➤ " + vo.getCmContent() + " | " + vo.getMId() + " | " + vo.getCmDate());

			}

			System.out.println();
			System.out.println("     ≡≡≡≡≡ ≡≡≡≡≡ [추가하기] ≡≡≡≡≡ ≡≡≡≡≡");
			System.out.println();
			System.out.println("         1.댓글쓰기 / 2.돌아기기");
			System.out.println();
			System.out.println("     ≡≡≡≡≡ ≡≡≡≡≡ ≡≡≡≡≡≡≡≡ ≡≡≡≡≡ ≡≡≡≡≡");
			System.out.println();
			System.out.print("      메뉴 선택 => ");
			String choice1 = sc.nextLine();

			if (choice1.contains("1")) {
				System.out.println();
				System.out.println("               |￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣|");
				System.out.println("        	       댓글 입력");
				System.out.println();
				System.out.print("                 => ");
				String content = sc.nextLine();
				int row = cmc.insert(cvo.getCSeq(), content, loginId);

				if (row > 0) {
					System.out.println("        	        # 입력 성공 ");
					System.out.println("               |＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿|\r\n");
				} else {
					System.out.println("        	        # 입력 실패 ");
					System.out.println("               |＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿|\r\n");

				}
				Comment comment = new Comment();
				comment.commentRun(cSeq, writerId, cvo.getCCnt() ,loginId);
				
			}else {
				
				community.communityRun(loginId);
			}
			
			
			
		

		// =======================================

	}//

}//
