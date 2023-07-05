package com.login;

import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import com.controller.MemberController;
import com.dao.MemberDAO;
import com.dao.MyPageDAO;

public class Login {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MemberController mc = new MemberController();
		
		// 임시 만들었다
		MemberDAO mDao = new MemberDAO();

		Scanner sc = new Scanner(System.in);
		boolean loop = true;
		
		System.out.println("\r\n"
				+ "  _______          _         _       _____   _____  _______ \r\n"
				+ " |__   __|        | |       | |     |_   _| / ____||__   __|\r\n"
				+ "    | |  ___    __| |  ___  | |       | |  | (___     | |   \r\n"
				+ "    | | / _ \\  / _` | / _ \\ | |       | |   \\___ \\    | |   \r\n"
				+ "    | || (_) || (_| || (_) || |____  _| |_  ____) |   | |   \r\n"
				+ "    |_| \\___/  \\__,_| \\___/ |______||_____||_____/    |_|   \r\n"
				+ "                                                            \r\n"
				+ "                                                            \r\n"
				+ "");
		
		while (loop) {
			
			
			

			System.out.println(
					"       ￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣	\r\n"
					+ "      |　메뉴선택　　　　　　　　　　　　　　　　　　　　　　[－][口][×] \r\n"
					+ "      |￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣ \r\n"
					+ "      |　　　　＿＿＿＿＿＿　　　　＿＿＿＿＿＿＿　　　　＿＿＿＿＿　       \r\n"
					+ "      | 　　　｜1.로그인|　　　｜2.회원가입｜    |9.종료　|        \r\n"
					+ "      |　　　　￣￣￣￣￣￣　　　　￣￣￣￣￣￣￣　　　　￣￣￣￣￣        \r\n"
					+ "      |　									　　		　\r\n"
					+ "      ￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣\r\n"
					+ "");
			
			System.out.print("      메뉴 선택 => ");
			String ch = sc.nextLine();
			System.out.println("");
			System.out.println("");
			System.out.println("");
			System.out.println("");
			System.out.println("");
			System.out.println("");
			
			if(ch.contains("1")) {
				
			System.out.println("               |￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣|");
			System.out.print("        	      ID 입력 : ");
			String inPutId  = sc.nextLine();
				
				System.out.println();
				System.out.print("        	      PW 입력 : ");	
				
				String inPutPw = sc.nextLine();
				Map<String, String> loginMap =mc.memberLoin(inPutId, inPutPw);
				String check = loginMap.get("loginCheck");
				String id = loginMap.get("loginId");
				
				if(check.equals("T")) {
					System.out.println();
					System.out.println("        	      # 로그인 성공 ");	
					System.out.println("               |＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿|\r\n");
					
					try {
						TimeUnit.MILLISECONDS.sleep(300);
					} catch (InterruptedException e) {
						
						e.printStackTrace();
					}
					
					App app = new App();
					app.appRun(id, sc); // 최유민 변경
				}else {
					System.out.println();
					System.out.println("                 # 입력하신 정보가 잘못되었습니다.");
					System.out.println("               |＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿|\r\n");
					
					try {
						TimeUnit.MILLISECONDS.sleep(100);
					} catch (InterruptedException e) {
						
						e.printStackTrace();
					}
					
				
					
				}
		
				
			}else if (ch.contains("2")) {
				String id;
				while(true) {
					System.out.println("               |￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣|");
					System.out.print("        	      ID 입력 : ");
					id = sc.nextLine();
					if(id.isEmpty()) {
						System.out.println();
						System.out.println("            # 아이디가 올바르지 않습니다. 다시 입력해주세요.");
						System.out.println();
						continue;
					} 
					break;
				}
				
				String pw;
                while(true) {
                	System.out.print("        	      PW 입력 : ");
                    pw = sc.nextLine();
                    if(pw.isEmpty()) {
                    	System.out.println();
                    	System.out.println("            # 패스워드가 올바르지 않습니다. 다시 입력해주세요.");
                    	System.out.println();
                        continue;
                    }
                    break;
                }  
            
			   
                String nick;
                while(true) {
                	System.out.print("        	      NICK 입력 : ");
                	nick = sc.nextLine();
                    if(nick.isEmpty()) {
                    	System.out.println();
                    	System.out.println("            # 닉네임이 올바르지 않습니다. 다시 입력해주세요.");
                    	System.out.println();
                    	continue;
                    }
                    break;
                }
            
			 
			//==============================================================

				//회원가입 하는 메소드 실행
			   	int row = mc.join(id, pw, nick);
		
			//=================================================================	
				
			System.out.println();
			if (row > 0) {
				System.out.println("        	      # 회원가입 성공 ");
				System.out.println("               |＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿|\r\n");

			} else {
				System.out.println("        	      # 회원가입 실패 ");
				System.out.println("               |＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿|\r\n");
			}

			}  else if (ch.contains("9")) { // 최유민 추가
				System.out.println("=========TodoList 종료==========");
				sc.close();
				MyPageDAO.connClose(); // 최유민 변경
				break;
			}
		}
		
	}//

}//
