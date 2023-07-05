package com.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dao.MemberDAO;
import com.vo.MemberVO;

public class MemberController {

	MemberDAO mdao = new MemberDAO();
	MemberVO mvo = new MemberVO();
	int cnt = 0;

	// ### 회원 가입
	public int join(String id, String pw, String nick) {
		// TODO Auto-generated method stub
		cnt = mdao.join(id, pw, nick);

		return cnt;
	}//

	// ### 회원정보 수정
	public int memberUpdate(String id, String updatePw, String updateNick) {
		// TODO Auto-generated method stub
		cnt = mdao.memberUpdate(id, updatePw, updateNick);

		return cnt;
	}//

	// ### 로그인
	public Map<String, String> memberLoin(String id, String pw) {
		// TODO Auto-generated method stub
		String loginCheck = "F";
		String loginId = "";
		Map<String, String> loginMap = new HashMap<String, String>();

		mvo = mdao.login(id, pw);

		if (mvo != null) {
			loginCheck = "T";
			loginId = mvo.getMId();
		} else {
			loginId = id;
		}
		loginMap.put("loginCheck", loginCheck);
		loginMap.put("loginId", loginId);
		return loginMap;

	}//

}//
