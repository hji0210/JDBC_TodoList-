package com.community;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.comment.Comment;
import com.controller.CommunityController;
import com.login.App;
import com.vo.CommunityVO;

public class Community {

	public void communityRun(String id) {

		Scanner sc = new Scanner(System.in);
		CommunityController cc = new CommunityController();
		Comment comment = new Comment();

		System.out.println("\r\n" + " _____                                       _ _         \r\n"
				+ "/  __ \\                                     (_) |        \r\n"
				+ "| /  \\/ ___  _ __ ___  _ __ ___  _   _ _ __  _| |_ _   _ \r\n"
				+ "| |    / _ \\| '_ ` _ \\| '_ ` _ \\| | | | '_ \\| | __| | | |\r\n"
				+ "| \\__/\\ (_) | | | | | | | | | | | |_| | | | | | |_| |_| |\r\n"
				+ " \\____/\\___/|_| |_| |_|_| |_| |_|\\__,_|_| |_|_|\\__|\\__, |\r\n"
				+ "                                                    __/ |\r\n"
				+ "                                                   |___/ \r\n" + "");

		boolean loop = true;

		while (loop) {

			System.out.println("       ￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣	\r\n"
					+ "      |　메뉴선택　　　　　　　　　　　　　　　　　　　　　　[－][口][×] \r\n"
					+ "      |￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣ \r\n"
					+ "      |　　　　＿＿＿＿＿＿　　　　＿＿＿＿＿＿＿　　　　＿＿＿＿＿　       \r\n"
					+ "      | 　　　｜1.글쓰기|　　　｜2.조회하기｜    |9.종료　|        \r\n"
					+ "      |　　　　￣￣￣￣￣￣　　　　￣￣￣￣￣￣￣　　　　￣￣￣￣￣        \r\n"
					+ "      |　									　　		　\r\n"
					+ "      ￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣\r\n" + "");

			System.out.print("      메뉴 선택 => ");
			String ch = sc.nextLine();
			System.out.println("");
			System.out.println("");
			System.out.println("");
			System.out.println("");


			// ======================= 글쓰기 ===============================
			if (ch.contains("1")) {

				System.out.println("               |￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣|");
				System.out.print("        	 제목 입력 : ");
				String inPutTitle = sc.nextLine();
				System.out.println();
				System.out.print("        	 내용 입력 : ");
				String inPutContent = sc.nextLine();

				int cnt = cc.insertCommunity(inPutTitle, inPutContent, id);

				if (cnt > 0) {
					System.out.println();
					System.out.println("        	      # 글쓰기 성공 ");
					System.out.println("               |＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿|\r\n");

				} else {
					System.out.println();
					System.out.println("                 # 입력하신 정보가 잘못되었습니다.");
					System.out.println("               |＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿|\r\n");
				}

				// =========================조회 하기==========================
			} else if (ch.contains("2")) {
				
				 cc = new CommunityController();
				List<CommunityVO> communityList = new ArrayList<CommunityVO>();

				communityList = cc.selectAll();
				System.out.println(" ┏━━━━━━━[Community]━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
				System.out.println(" ┃");

				for (int i = 0; i < communityList.size(); i++) {
					
					System.out.println("   "+(i+1)
							+".제목 : "+(communityList.get(i).getCTitle())+" | 작성자: "
					+communityList.get(i).getMId()
					+" | "+communityList.get(i).getCDate()
					+" | 조회수:"+(communityList.get(i).getCCnt()));
				}
		 
				System.out.println(" ┃");
				System.out.println(" ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");

				
				System.out.println();
				System.out.println("     ≡≡≡≡≡ ≡≡≡≡≡ [메뉴선택] ≡≡≡≡≡ ≡≡≡≡≡");
				System.out.println();
				System.out.println("        1.게시글 조회 / 2.돌아기기");
				System.out.println();
				System.out.println("     ≡≡≡≡≡ ≡≡≡≡≡ ≡≡≡≡≡≡≡≡ ≡≡≡≡≡ ≡≡≡≡≡");
				System.out.println();
				System.out.print("      메뉴 선택 => ");
				String choice1 = sc.nextLine();
				System.out.println();
				
				if (choice1.contains("1")) {
					System.out.print("     조회 할 게시글 번호 입력 => ");
					int index = Integer.parseInt(sc.nextLine())-1;
					comment.commentRun(communityList.get(index).getCSeq(),communityList.get(index).getMId(),communityList.get(index).getCCnt(),id);
								
				}
					
			}else{
				
				App app = new App();
				app.appRun(id,sc);
				break;
			}
			// =================================================================
		} // ch2

	}//

}///
