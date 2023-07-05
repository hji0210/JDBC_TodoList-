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

public class CommunityDAO {

	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private List<CommunityVO> communityList = new ArrayList<CommunityVO>();
	private CommunityVO cvo = null;

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

	// ### 글 쓰기
	public int insertCommunuty(String inPutTitle, String inPutContent, String id) {
		// TODO Auto-generated method stub

		getConn();

		// Query
		StringBuffer query = new StringBuffer();
		query.append(
				"insert into T_COMMUNITY (COMMUNITY_TITLE, COMMUNITY_CONTENT, COMMUNITY_CNT, COMMUNITY_DATE, MEMBER_ID )");
		query.append(" values(?,?,0,SYSDATE,?)");

		try {
			pstmt = conn.prepareStatement(query.toString());
			pstmt.setString(1, inPutTitle);
			pstmt.setString(2, inPutContent);
			pstmt.setString(3, id);
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

	// ### 글 전체 조회
	public List<CommunityVO> selectAll() {
		// TODO Auto-generated method stub
		getConn();

		// Query
		StringBuffer query = new StringBuffer();
		query.append(
				"select COMMUNITY_SEQ, COMMUNITY_TITLE, COMMUNITY_CONTENT, COMMUNITY_CNT, TO_CHAR(COMMUNITY_DATE,'YYYY-MM-DD'), MEMBER_ID ");
		query.append(" from T_COMMUNITY");
		query.append(" ORDER BY COMMUNITY_DATE DESC");

		try {
			pstmt = conn.prepareStatement(query.toString());

			rs = pstmt.executeQuery();

			// ## 전체 조회
			while (rs.next()) {

				int cSeq = rs.getInt(1);
				String cTitle = rs.getString(2);
				String cContent = rs.getString(3);
				int cCnt = rs.getInt(4);
				String cDate = rs.getString(5);
				String mId = rs.getString(6);

				cvo = new CommunityVO(cSeq, cTitle, cContent, cCnt, cDate, mId);

				communityList.add(cvo);
			}

			rs.close();
			pstmt.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}

		return communityList;
	}

	// ## 시퀀스 기준 조회
	public CommunityVO selectByCSeq(int seq) {
		// TODO Auto-generated method stub

		getConn();

		
		// Query
		StringBuffer query = new StringBuffer();
		query.append(
				"select COMMUNITY_SEQ, COMMUNITY_TITLE, COMMUNITY_CONTENT, COMMUNITY_CNT, TO_CHAR(COMMUNITY_DATE,'YYYY-MM-DD'), MEMBER_ID ");
		query.append(" from T_COMMUNITY");
		query.append(" WHERE COMMUNITY_SEQ = ?");

		try {
			pstmt = conn.prepareStatement(query.toString());
			pstmt.setInt(1, seq);

			rs = pstmt.executeQuery();

			// ## 전체 조회
			while (rs.next()) {

				int cSeq = rs.getInt(1);
				String cTitle = rs.getString(2);
				String cContent = rs.getString(3);
				int cCnt = rs.getInt(4);
				String cDate = rs.getString(5);
				String mId = rs.getString(6);

				cvo = new CommunityVO(cSeq, cTitle, cContent, cCnt, cDate, mId);

			}

			rs.close();
			pstmt.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}

		return cvo;
	}//
	
	// ## 조회수 증가
	public int plusCnt(int cSeq, int cCnt) {
		
		getConn();

		// Query
		StringBuffer query = new StringBuffer();
		query.append("UPDATE T_COMMUNITY ");
		query.append(" SET COMMUNITY_CNT = ?");
		query.append(" WHERE COMMUNITY_SEQ = ?");

		try {
			pstmt = conn.prepareStatement(query.toString());
			pstmt.setInt(1, cCnt+1);
			pstmt.setInt(2, cSeq);

			cnt = pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		return cnt;
		
		
	}//
	

}//
