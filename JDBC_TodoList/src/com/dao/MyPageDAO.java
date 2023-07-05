package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.vo.TodoVO;

public class MyPageDAO {
	
	private ArrayList<TodoVO> todoList = new ArrayList<>();
	private TodoVO tvo = null;
	
	private static Connection conn = MyPageDAO.getConn();
	
	StringBuffer query = new StringBuffer();
	
	// ### 커넥션
	public static Connection getConn() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@127.0.0.1:1521:orcl";
			String dbid = "hr";
			String dbpw = "hr";
			return DriverManager.getConnection(url, dbid, dbpw);

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static void connClose() {
		try {
			conn.close();
		} catch(Exception ex) {
			
		}
	}

	// ### 특정 일자의 모든 할일 조회
	public List<TodoVO> selectByDate(String targetId, String targetDate) {
		
		query.setLength(0);
		todoList.clear();
		
		query.append("select todo_seq, todo_content, todo_date, todo_check, member_id");
		query.append(" from t_todo");
		query.append(" where member_id = ?");
		query.append(" and todo_date = ?");

		try(PreparedStatement pstmt = conn.prepareStatement(query.toString())) {
			pstmt.setString(1, targetId);
			pstmt.setString(2, targetDate);

		    try(ResultSet rs = pstmt.executeQuery()) {
		    	if(rs != null) {
		    		while (rs.next()) {
						int seq = rs.getInt("todo_seq");
						String id = rs.getString("member_id");
						String content = rs.getString("todo_content");
						String date = rs.getString("todo_date");
						String check = rs.getString("todo_check");

						tvo = new TodoVO(seq, id, content, date, check);
						todoList.add(tvo);
		    		}
		    	} 
		    }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return todoList;
	}

}
