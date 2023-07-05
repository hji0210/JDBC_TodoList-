package com.controller;

import java.util.ArrayList;
import java.util.List;

import com.dao.CommunityDAO;

import com.vo.CommunityVO;

public class CommunityController {

	CommunityDAO cDao = new CommunityDAO();
	List<CommunityVO> communityList = new ArrayList<>();
	CommunityVO cvo = null;

	// ### 글쓰기
	public int insertCommunity(String inPutTitle, String inPutContent, String id) {
		// TODO Auto-generated method stub

		int cnt = cDao.insertCommunuty(inPutTitle, inPutContent, id);

		return cnt;
	}//

	// ### 글 전체 조회
	public List<CommunityVO> selectAll() {
		// TODO Auto-generated method stub

		communityList = cDao.selectAll();

		return communityList;
	}//

	
	// ### 시퀀스 게시글 조회
	public CommunityVO selectByCSeq(int cSeq) {
		// TODO Auto-generated method stub
		
		cvo = cDao.selectByCSeq(cSeq);
		
		
		return cvo;
	}

	public void plusCnt(int cSeq, int cCnt) {
		// TODO Auto-generated method stub
		
		
		cDao.plusCnt(cSeq,cCnt);
		
	}//
	
	

}//
