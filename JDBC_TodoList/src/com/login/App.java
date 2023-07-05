package com.login;

import java.util.Scanner;

import com.community.Community;
import com.controller.MemberController;
import com.feed.Feed;
import com.mypage.MyPage;


public class App {

	public void appRun(String id, Scanner sc) {

		Feed feed = new Feed();
		MyPage myPage = new MyPage();
		Community community = new Community();
		MemberController mc = new MemberController();
	
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println("\r\n" + "  _______          _         _       _____   _____  _______ \r\n"
				+ " |__   __|        | |       | |     |_   _| / ____||__   __|\r\n"
				+ "    | |  ___    __| |  ___  | |       | |  | (___     | |   \r\n"
				+ "    | | / _ \\  / _` | / _ \\ | |       | |   \\___ \\    | |   \r\n"
				+ "    | || (_) || (_| || (_) || |____  _| |_  ____) |   | |   \r\n"
				+ "    |_| \\___/  \\__,_| \\___/ |______||_____||_____/    |_|   \r\n"
				+ "                                                            \r\n"
				+ "                                                            \r\n" + "");

		System.out.println("               |￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣|");
		System.out.println("                       " + id + "의 TodoList");
		System.out.println("               |＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿|");
		System.out.println();

		System.out.println("       ￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣	\r\n"
				+ "      |　메뉴선택　　　　　　　　　　　　　　　　　　　　　　[－][口][×] \r\n"
				+ "      |￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣ \r\n"
				+ "      |　　　　＿＿＿＿＿＿　　　　＿＿＿＿＿＿＿　　　＿＿＿＿＿＿＿＿＿　       \r\n"
				+ "      | 　　　|1.피드 |　　　|2.마이페이지|   |3.회원정보수정|        \r\n"
				+ "      |　　　　￣￣￣￣￣￣　　　　￣￣￣￣￣￣￣　　　￣￣￣￣￣￣￣￣￣        \r\n"
				+ "      |　									　　		　\r\n"
				+ "      |         ＿＿＿＿＿＿	         ＿＿＿＿＿		　\r\n"
				+ "      |        |4.커뮤니티|     	|9.종료 |　　		　\r\n"
				+ "      |         ￣￣￣￣￣￣￣        ￣￣￣￣￣　		　\r\n"
				+ "      |　									　　		　\r\n"
				+ "      ￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣\r\n" + "");

		System.out.print("      메뉴 선택 => ");
		String choice = sc.nextLine();

		if (choice.contains("1")) {
			feed.feedRun(id);
		} else if (choice.contains("2")) {
			myPage.mypageRun(id,sc);

		}
		// =========================회원정보 수정=============================
		else if (choice.contains("3")) {
			System.out.println("               |￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣|");
			System.out.print("        	      ID : "+id);
			
			String updatePw;
			String updateNick;
			while(true) {
				System.out.print("        	      PW 입력 : ");	
				
				updatePw = sc.nextLine();
				if(updatePw.isEmpty()) {
					System.out.println("            # 비밀번호가 올바르지 않습니다. 정확하게 입력해 주세요.");
				    continue;
				}
				break;
			}
			
			while(true) {
				System.out.print("        	     NICK 입력 : ");	
				
				updateNick = sc.nextLine();
				if(updateNick.isEmpty()) {
					System.out.println("            # 닉네임이 올바르지 않습니다. 정확하게 입력해 주세요.");
				    continue;
				}
				break;
			}
		
			int cnt = mc.memberUpdate(id, updatePw, updateNick);
	
			if (cnt > 0) {
				System.out.println();
				System.out.println("        	      # 정보수정 성공 ");
				System.out.println("               |＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿|\r\n");
			} else {
				System.out.println();
				System.out.println("        	      # 정보수정 실패 ");
				System.out.println("               |＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿|\r\n");
			}
			// ================================================================
		}else if (choice.contains("4")) {
			
			community.communityRun(id);
			// ================================================================
		} 
		else if(choice.contains("9")) {
			System.out.println("=========로그아웃==========");
		}

	}//

}//
