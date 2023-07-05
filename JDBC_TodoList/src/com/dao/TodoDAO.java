package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.vo.TodoVO;

public class TodoDAO {
	
	private ArrayList<TodoVO> tlist = new ArrayList<>();
	private TodoVO tvo = null;
	
	// 최유민 변경
	StringBuffer query = new StringBuffer();

	// ### 커넥션
	public Connection getConn() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:orcl";
			String dbid = "hr";
			String dbpw = "hr";
			return DriverManager.getConnection(url, dbid, dbpw);

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// ### 오늘 할일 추가 // 최유민 변경
	public void insert(String id, String content) {
		
		query.setLength(0);
		query.append("insert into t_todo");
		query.append(" values (t_todo_seq.nextval, ?, to_char(sysdate, 'yyyy-mm-dd'), ?, ?)");
		
		try(Connection conn = getConn();
			PreparedStatement pstmt = conn.prepareStatement(query.toString())) {
			pstmt.setString(1, content);
			pstmt.setString(2, "N");
			pstmt.setString(3, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// ### 오늘 할일 조회 // 최유민 변경
	public List<TodoVO> selectByDate(String targetId, String targetDate) {
		
		query.setLength(0);
		tlist.clear();
		
		query.append("select todo_seq, todo_content, todo_date, todo_check, member_id");
		query.append(" from t_todo");
		query.append(" where member_id = ?");
		query.append(" and todo_date = ?");

		try(Connection conn = getConn();
			PreparedStatement pstmt = conn.prepareStatement(query.toString())) {
			pstmt.setString(1, targetId);
			pstmt.setString(2, targetDate);

		    try(ResultSet rs = pstmt.executeQuery()) {
		    	while (rs.next()) {
					int seq = rs.getInt("todo_seq");
					String id = rs.getString("member_id");
					String content = rs.getString("todo_content");
					String date = rs.getString("todo_date");
					String check = rs.getString("todo_check");

					tvo = new TodoVO(seq, id, content, date, check);
					tlist.add(tvo);
		    }
		}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return tlist;
	}

	// ### 오늘 할일 수정 // 최유민 변경
	public void update(int updateSeq, String contentUp) {
		query.setLength(0);
		
		query.append("update t_todo");
		query.append(" set todo_content=?");
		query.append(" where todo_seq=?");

		try(Connection conn = getConn();
			PreparedStatement pstmt = conn.prepareStatement(query.toString())) {
			pstmt.setString(1, contentUp);
			pstmt.setInt(2, updateSeq);
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}

	// ### 오늘 할일 삭제 // 최유민 변경
	public void delete(int deleteSeq) {
		query.setLength(0);
		
		query.append("delete t_todo");
		query.append(" where todo_seq=?");
		
		try(Connection conn = getConn();
			PreparedStatement pstmt = conn.prepareStatement(query.toString())) {
			pstmt.setInt(1, deleteSeq);

			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// ### 오늘 할일 체크 // 최유민 변경
	public void updateCheck(int checkSeq, String checkYN) {
		query.setLength(0);
		
		query.append("update t_todo");
		query.append(" set todo_check=?");
		query.append(" where todo_seq=?");

		try(Connection conn = getConn();
			PreparedStatement pstmt = conn.prepareStatement(query.toString())) {
			pstmt.setString(1, checkYN);
			pstmt.setInt(2, checkSeq);
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}

}//
