package com.feed;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import com.controller.FeedController;
import com.login.App;
import com.vo.TodoVO;

public class Feed {


	Scanner sc = new Scanner(System.in);
	List<TodoVO> todayList = new ArrayList<TodoVO>();
	String checkBox ="";

	public void feedRun(String id) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		String date = formatter.format(calendar.getTime());

		FeedController feedcon = new FeedController();
		boolean loop = true;

		System.out.println("\r\n"
				+ "　　　　              ______               _ \r\n"
				+ "　　　　              |  ___|             | |\r\n"
				+ "　　　　              | |_  ___   ___   __| |\r\n"
				+ "　　　　              |  _|/ _ \\ / _ \\ / _` |\r\n"
				+ "　　　　              | | |  __/|  __/| (_| |\r\n"
				+ "　　　　              \\_|  \\___| \\___| \\__,_|\r\n"
				+ "　　　　                       \r\n"
				+ "");
		System.out.println(" 　　　　   [" + date + "]");
		
		while (loop) {
			System.out.println();
			System.out.println(
					"       ￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣	\r\n"
					+ "      |　메뉴선택　　　　　　　　　　　　　　　　　　　　　　[－][口][×] \r\n"
					+ "      |￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣ \r\n"
					+ "      |　　　　＿＿＿＿＿＿　　　　＿＿＿＿＿＿＿　　　　＿＿＿＿＿　       \r\n"
					+ "      | 　　　｜1.할일추가|　　｜2.조회하기｜   |3.수정하기|        \r\n"
					+ "      |　　　　￣￣￣￣￣￣　　　　￣￣￣￣￣￣￣　　　　￣￣￣￣￣        \r\n"
					+ "      |　　　　＿＿＿＿＿＿　　　　＿＿＿＿＿＿＿　　　　＿＿＿＿＿　       \r\n"
					+ "      | 　　　｜4.삭제하기|　 ｜5.체크하기｜   |9.돌아가기|        \r\n"
					+ "      |　　　　￣￣￣￣￣￣　　　　￣￣￣￣￣￣￣　　　　￣￣￣￣￣        \r\n"
					+ "      |　									　　		　\r\n"
					+ "      ￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣\r\n"
					+ "");
			


			System.out.print("      메뉴 선택 => ");
			String menu = sc.nextLine();

			if (menu.contains("1")) {
				System.out.println();
				System.out.println("     ≡≡≡≡≡ ≡≡≡≡≡ [추가하기] ≡≡≡≡≡ ≡≡≡≡≡");
				System.out.println();
				System.out.println("         1.할일 추가 / 2.돌아기기");
				System.out.println();
				System.out.println("     ≡≡≡≡≡ ≡≡≡≡≡ ≡≡≡≡≡≡≡≡ ≡≡≡≡≡ ≡≡≡≡≡");
				System.out.println();
				System.out.print("      메뉴 선택 => ");
				String choice1 = sc.nextLine();
				if (choice1.contains("1")) {
					String content;
					while(true) { // 최유민 추가
						System.out.println();
						System.out.println("               |￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣|");
						System.out.println("        	       오늘 할일 입력" );
						System.out.println();
						System.out.print("                 => ");
						content = sc.nextLine();
						if(content.isEmpty()) {
							System.out.println();
							System.out.println("입력하신 내용이 없습니다. 오늘 할일을 입력해 주세요");
							continue;
						}
						break;
					}
					
					feedcon.insert(id,content);
					System.out.println("        	        # 입력 성공 ");	
					System.out.println("               |＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿|\r\n");
				}

			} else if (menu.contains("2")) {
				System.out.println();
				
				todayList = feedcon.todayRead(id);
				if (todayList.size() == 0) {
					System.out.println("오늘 할일이 없습니다.");
				} else {
					
					
					System.out.println(" ┏━━━━━━━[Todo List]━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
					System.out.println(" ┃");
					for (int i = 0; i < todayList.size(); i++) {
						
					
						
						System.out.print("   "+(i + 1));
						System.out.print(". " + todayList.get(i).getContent());
						System.out.print(" / " + todayList.get(i).getDate());
						if(todayList.get(i).getCheck().contains("Y")) {
							checkBox ="√";
						}else if(todayList.get(i).getCheck().contains("N")) {
							checkBox ="X";
						}
						
						System.out.print(" / " + checkBox);
						System.out.println();
						try {
							TimeUnit.MILLISECONDS.sleep(400);
							
						} catch (InterruptedException e) {
							
							e.printStackTrace();
						}
					}
					System.out.println(" ┃");
					System.out.println(" ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
				}

			} else if (menu.contains("3")) {
				int updateSeq = 0; // 최유민 변경
				todayList = feedcon.todayRead(id);
				
				// 최유민 추가
				if(todayList.size() == 0) {
					System.out.println();
					System.out.println("수정할 일정이 없습니다.");
					continue;
				}
				
				System.out.println();
				System.out.println(" ┏━━━━━━━[수정 하기]━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
				System.out.println(" ┃");
				for (int i = 0; i < todayList.size(); i++) {
					System.out.print("   "+(i + 1));
					System.out.print(". " + todayList.get(i).getContent());
					System.out.print(" / " + todayList.get(i).getDate());
					if(todayList.get(i).getCheck().contains("Y")) {
						checkBox ="√";
					}else if(todayList.get(i).getCheck().contains("N")) {
						checkBox ="X";
					} // 최유민 추가

					System.out.print(" / " + checkBox); // 최유민 추가
					System.out.println();
					
					
				}
				System.out.println(" ┃");
				System.out.println(" ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
				System.out.println();
				System.out.println("               |￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣|");
				System.out.print("        	      수정 할 번호 : ");
				int choiceUp = Integer.parseInt(sc.nextLine()); // 최유민 변경

				String contentUp; // 최유민 변경
				while(true) { // 최유민 추가
					System.out.println("           	        내용 입력  ");
					System.out.print("                =>");
					contentUp = sc.nextLine();
					if(contentUp.isEmpty()) {
						System.out.println();
						System.out.println("입력하신 내용이 없습니다. 오늘 할일을 입력해 주세요");
						continue;
					}
					break;
				}

				updateSeq = getIndex(choiceUp); // 최유민 변경
				
				feedcon.update(updateSeq, contentUp);
				System.out.println("        	      # 할일 수정 성공 ");	
				System.out.println("               |＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿|\r\n");
				

			} else if (menu.contains("4")) {
				int deleteSeq = 0; // 최유민 변경
				todayList = feedcon.todayRead(id);
				
				// 최유민 추가
				if(todayList.size() == 0) {
					System.out.println();
					System.out.println("삭제 할 일정이 없습니다.");
					continue;
				}
				
				System.out.println();
				System.out.println(" ┏━━━━━━━[삭제 하기]━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
				System.out.println(" ┃");
				
				for (int i = 0; i < todayList.size(); i++) {
					System.out.print("   "+(i + 1));
					System.out.print(". " + todayList.get(i).getContent());
					System.out.print(" / " + todayList.get(i).getDate());
					if(todayList.get(i).getCheck().contains("Y")) {
						checkBox ="√";
					}else if(todayList.get(i).getCheck().contains("N")) {
						checkBox ="X";
					}
					
					System.out.print(" / " + checkBox);
					System.out.println();
					
					
				}
				System.out.println(" ┃");
				System.out.println(" ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
				System.out.println();
				
				System.out.println("               |￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣|");
				System.out.print("        	      삭제 할 번호 : ");
				int choiceDel = Integer.parseInt(sc.nextLine()); // 최유민 변경

				deleteSeq = getIndex(choiceDel); // 최유민 변경

				feedcon.delete(deleteSeq);
				System.out.println("        	      # 할일 삭제 성공 ");	
				System.out.println("               |＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿|\r\n");

			} else if (menu.contains("5")) {
				int checkSeq = 0;

				todayList = feedcon.todayRead(id);
				
				// 최유민 추가
				if(todayList.size() == 0) {
					System.out.println();
					System.out.println("체크할 일정이 없습니다.");
					continue;
				}

				System.out.println();
				System.out.println(" ┏━━━━━━━[체크 하기]━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
				System.out.println(" ┃");
				for (int i = 0; i < todayList.size(); i++) {
//					System.out.println(); // 최유민 변경
					System.out.print("   "+(i + 1));
					System.out.print(". " + todayList.get(i).getContent());
					System.out.print(" / " + todayList.get(i).getDate());
					if(todayList.get(i).getCheck().contains("Y")) {
						checkBox ="√";
					}else if(todayList.get(i).getCheck().contains("N")) {
						checkBox ="X";
					}
					
					System.out.print(" / " + checkBox);
					System.out.println();
					
					
				}			
				System.out.println(" ┃");
				System.out.println(" ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
				System.out.println();
				
				System.out.println("               |￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣|");
				System.out.print("        	      체크 할 번호 : ");
				int check = Integer.parseInt(sc.nextLine()); // 최유민 변경
				
				System.out.println("           	 1.체크하기 √ / 2.체크풀기 X  ");
				System.out.print("                =>");
				
				String check12 = sc.nextLine();
				String checkYN = "Y";
				if (check12.contains("1")) {
					checkYN = "Y";
				} else if (check12.contains("2")) {
					checkYN = "N";
				}
				
				checkSeq = getIndex(check); // 최유민 변경


				feedcon.check(checkSeq,checkYN);
				System.out.println("        	      # 할일 체크 성공 "); // 최유민 변경
				System.out.println("               |＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿|\r\n");

			}else if (menu.contains("9")) {
				System.out.println("피드를 종료합니다.");
				App app = new App();
				app.appRun(id, sc); // 최유민 변경
				break; // 최유민 변경
			}

		} // while

	};// feedRun
	
	// sacanner 로 입력받은 숫자로 index 찾기 // 최유민 변경
	private int getIndex(int targetNum) {
		return todayList.get(targetNum - 1).getSeq();
	}

}//
