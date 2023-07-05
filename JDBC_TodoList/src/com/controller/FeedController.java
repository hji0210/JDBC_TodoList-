package com.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.dao.TodoDAO;
import com.vo.TodoVO;

public class FeedController {

	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	Calendar calendar = Calendar.getInstance();
	String date = formatter.format(calendar.getTime());

	TodoDAO tdao = new TodoDAO();

	// ===오늘 할일 추가
	public void insert(String id, String content) {
		tdao.insert(id, content);
	}

	// ===오늘 할일 조회
	public List<TodoVO> todayRead(String id) {
		return tdao.selectByDate(id, date);
	}

	// ===오늘 할일 수정
	public void update(int updateSeq, String contentUp) {
		tdao.update(updateSeq, contentUp);
	}

	// ===오늘 할일 삭제
	public void delete(int deleteSeq) {
		tdao.delete(deleteSeq);
	}

	// ===오늘 할일 체크
	public void check(int checkSeq, String checkYN) {
		tdao.updateCheck(checkSeq, checkYN);
	}

}//
