package com.douzone.mysite.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.douzone.guestbook.vo.GuestbookVO;

public class GuestbookDAO {

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

	public boolean insert(GuestbookVO vo) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();

			// 3. SQL 준비
			String sql = "insert into guestbook values(null, ?, ?, ?, now())";
			pstmt = conn.prepareStatement(sql);

			// 4. 바인딩(binding)
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getPassword());
			pstmt.setString(3, vo.getMessage());
//	         pstmt.setDate(1, vo.getRegDate());

			// 5. SQL 실행
			int count = pstmt.executeUpdate();

			result = count == 1;

		} catch (SQLException e) {
			System.out.println("GuestbookDAO insert() 예외 발생 error:" + e);
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
	
	public boolean delete(Long no, String password) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();

			// 3. SQL 준비
			String sql = "delete from guestbook where no=? and password=?";
			pstmt = conn.prepareStatement(sql);

			// 4. 바인딩(binding)
			pstmt.setLong(1, no);
			pstmt.setString(2, password);

			// 5. SQL 실행
			int count = pstmt.executeUpdate();

			result = count == 1;

		} catch (SQLException e) {
			System.out.println("GuestbookDAO delete() 예외 발생 error:" + e);
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
	
	public List<GuestbookVO> findAll() {
		List<GuestbookVO> result = new ArrayList<>();
		
		Connection conn = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    
	    try {
	    	 conn = getConnection();
	         
	         //3. SQL 준비
	         String sql = "select no, name, password, message, reg_date from guestbook";
	         pstmt = conn.prepareStatement(sql);
	         
	         //4. 바인딩(binding)
	         
	         //5. SQL 실행
	         rs = pstmt.executeQuery();
	         
	         while(rs.next()) {
	        	Long no = rs.getLong(1);
	        	String name = rs.getString(2);
	        	String password = rs.getString(3);
	        	String message = rs.getString(4);
	        	Date regDate = rs.getDate(5);
	        	
	        	GuestbookVO vo = new GuestbookVO();
	        	vo.setNo(no);
	        	vo.setName(name);
	        	vo.setPassword(password);
	        	vo.setMessage(message);
	        	vo.setRegDate(regDate);
	        	
	        	result.add(vo);
	         }
	         
	      } catch (SQLException e) {
	         System.out.println("GuestbookDAO findAll() error:" + e);
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
	      return result;
	}
	
	
}
