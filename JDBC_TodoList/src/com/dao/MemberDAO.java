package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.login.Login;
import com.vo.MemberVO;
import com.vo.TodoVO;

public class MemberDAO {

	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private MemberVO mvo = null;
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

	// ### 회원가입
	public int join(String id, String pw, String nick) {
		// TODO Auto-generated method stub
		getConn();

		// Query
		StringBuffer query = new StringBuffer();
		query.append("insert into t_member");
		query.append(" values(?,?,?)");

		try {
			pstmt = conn.prepareStatement(query.toString());
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			pstmt.setString(3, nick);
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

	// ### 로그인
	public MemberVO login(String id, String pw) {
		// TODO Auto-generated method stub
		getConn();

		// Query
		StringBuffer query = new StringBuffer();
		query.append("select member_id, member_pw, member_nick");
		query.append(" from t_member");
		query.append(" where member_id = ?");

		try {
			pstmt = conn.prepareStatement(query.toString());
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			// ## 회원 조회
			while (rs.next()) {

				String mid = rs.getString("member_id");
				String password = rs.getString("member_pw");
				String nick = rs.getString("member_nick");
				mvo = new MemberVO(mid, password, nick);
			}

			rs.close();
			pstmt.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}

		if (mvo != null) {
			if (pw.equals(mvo.getMPw())) {
				mvo = mvo;
			} else {
				mvo = null;
			}
		} else {
			mvo = null;
		}
		return mvo;
	}//

	// ### 회원정보 수정
	public int memberUpdate(String id, String updatePw, String updateNick) {
		// TODO Auto-generated method stub
		getConn();

		// Query
		StringBuffer query = new StringBuffer();
		query.append("UPDATE T_MEMBER ");
		query.append(" SET member_PW = ?,member_NICK =?");
		query.append(" WHERE member_ID = ?");

		try {
			pstmt = conn.prepareStatement(query.toString());
			pstmt.setString(1, updatePw);
			pstmt.setString(2, updateNick);
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

	}//

}//
