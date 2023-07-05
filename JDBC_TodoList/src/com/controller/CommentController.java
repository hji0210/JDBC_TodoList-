package com.controller;

import java.util.ArrayList;
import java.util.List;

import com.dao.CommentDAO;
import com.vo.CommentVO;

public class CommentController {

	CommentDAO cmDao = new CommentDAO();
	List<CommentVO> commentList = new ArrayList<CommentVO>();
	
	// ### 시퀀스 댓글 조회
	public List<CommentVO> selectAll(int cSeq) {
		// TODO Auto-generated method stub
		
		commentList = cmDao.selectAll(cSeq);
		return commentList;
	}

	
	// ## 댓글 입력
	public int insert(int cSeq,String content, String loginId) {
		// TODO Auto-generated method stub
		
		int row = cmDao.insert(cSeq,content,loginId);
		
		
		return row;
	}

	
	
	
}//
