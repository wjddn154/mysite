package com.douzone.mysite.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.douzone.mysite.vo.UserVO;

public class UserDao {

	private Connection getConnection() throws SQLException {
		Connection conn = null;
		try {
			// 1. JDBC Driver 로딩
			Class.forName("org.mariadb.jdbc.Driver");

			// 2. 연결하기
			String url = "jdbc:mysql://127.0.0.1:3306/webdb?charset=utf8";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		}
		return conn;
	}
	
	public UserVO findByEmailAndPassword(String email, String password) {
		UserVO vo = null;
		
		Connection conn = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    
	    try {
	    	 conn = getConnection();
	         
	         //3. SQL 준비
	         String sql = 
	        		"select no, name" + 
	        		" from user" +
	        		" where email=?" +
	        		" and password=?";
	         pstmt = conn.prepareStatement(sql);
	         
	         //4. 바인딩(binding)
	         pstmt.setString(1, email);
	         pstmt.setString(2, password);
	         
	         
	         //5. SQL 실행
	         rs = pstmt.executeQuery();
	         
	         while(rs.next()) {
	        	Long no = rs.getLong(1);
	        	String name = rs.getString(2);
	        	
	        	vo = new UserVO();
	        	vo.setNo(no);
	        	vo.setName(name);
	         }
	         
	      } catch (SQLException e) {
	         System.out.println("UserDAO findAll() error:" + e);
	      } finally {
	         //clean up
	         try {
	        	if(rs != null) {
	        	   rs.close();
				}
	        	
	        	if(pstmt != null) {
	        	   pstmt.close();
	        	}
	        	 
	            if(conn != null) {
	               conn.close();
	            }
	         } catch (SQLException e) {
	            e.printStackTrace();
	         }
	      }
	      return vo;
	}
	
	public boolean insert(UserVO vo) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();

			// 3. SQL 준비
			String sql = "insert into user values(null, ?, ?, ?, ?, now())";
			pstmt = conn.prepareStatement(sql);

			// 4. 바인딩(binding)
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getEmail());
			pstmt.setString(3, vo.getPassword());
			pstmt.setString(4, vo.getGender());

			// 5. SQL 실행
			int count = pstmt.executeUpdate();

			result = count == 1;

		} catch (SQLException e) {
			System.out.println("UserDAO insert() 예외 발생 error:" + e);
		} finally {

			try {
				if (pstmt != null) {
					pstmt.close();
				}

				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}
	
}
