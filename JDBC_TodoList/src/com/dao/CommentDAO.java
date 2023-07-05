package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.vo.CommentVO;
import com.vo.CommunityVO;

public class CommentDAO {

	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private CommentVO cmvo = null;
	private List<CommentVO> commentList = new ArrayList<CommentVO>();
	int cnt = 0;

	// ### 커넥션
	public void getConn() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@127.0.0.1:1521:orcl";
			String dbid = "hr";
			String dbpw = "hr";
			conn = DriverManager.getConnection(url, dbid, dbpw);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}//

	// ### 연결끊기
	public void close() {
		try {
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}//

	
	// ### 댓글 조회
	public List<CommentVO> selectAll(int seq) {
		// TODO Auto-generated method stub

		getConn();

		// Query
		StringBuffer query = new StringBuffer();
		query.append("select COMMENT_SEQ, COMMUNITY_SEQ, COMMENT_CONTENT,  TO_CHAR(COMMENT_DATE,'YYYY-MM-DD'), MEMBER_ID ");
		query.append(" from T_COMMENT");
		query.append(" WHERE COMMUNITY_SEQ = ?");
		query.append(" ORDER BY COMMENT_DATE");
		try {
			pstmt = conn.prepareStatement(query.toString());
			pstmt.setInt(1, seq);

			rs = pstmt.executeQuery();

			// ## 전체 조회
			while (rs.next()) {

				int cmSeq = rs.getInt(1);
				int cSeq = rs.getInt(2);
				String cmContent = rs.getString(3);
				String cmDate = rs.getString(4);
				String mId = rs.getString(5);

				cmvo = new CommentVO(cmSeq, cSeq, cmContent, cmDate, mId);
				commentList.add(cmvo);

			}

			rs.close();
			pstmt.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}

		return commentList;
	}

	// ## 댓글 추가
	public int insert(int cSeq,String content , String loginId) {
		// TODO Auto-generated method stub
		getConn();

		// Query
		StringBuffer query = new StringBuffer();
		query.append(
				"insert into T_COMMENT (COMMUNITY_SEQ, COMMENT_CONTENT,COMMENT_DATE, MEMBER_ID )");
		query.append(" values(?,?,SYSDATE,?)");

		try {
			pstmt = conn.prepareStatement(query.toString());
			pstmt.setInt(1, cSeq);
			pstmt.setString(2, content);
			pstmt.setString(3, loginId);
			cnt = pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		return cnt;
	}

}
