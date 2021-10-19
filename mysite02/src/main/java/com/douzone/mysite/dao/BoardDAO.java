package com.douzone.mysite.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.douzone.mysite.vo.BoardDTO;
import com.douzone.mysite.vo.BoardVO;

public class BoardDAO {

	private Connection getConnection() throws SQLException {
		Connection conn = null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			String url = "jdbc:mysql://127.0.0.1:3306/webdb?characterEncoding=utf8";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		} 
		return conn;
	}	
	
	
	//join된 결과를 표시하기 위해 dto 사용
	public List<BoardDTO> findAll(Long pageNo) {
		List<BoardDTO> list = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			
			String sql =
				"select b.no, b.title, b.contents, b.hit, b.reg_date, b.group_no, b.order_no, b.depth, b.user_no, u.name" +
				"  from board b, user u" +
				" where b.user_no = u.no" +
				" order by b.group_no desc, b.order_no desc, b.depth" +
				" limit ?, ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, (pageNo-1) * 10);
			pstmt.setLong(2, (pageNo) * 10);

			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Long no = rs.getLong(1);
				String title = rs.getString(2);
				String contents = rs.getString(3);
				Long hit = rs.getLong(4);
				String regDate = rs.getString(5);
				Long groupNo = rs.getLong(6);
				Long orderNo = rs.getLong(7);
				Long depth = rs.getLong(8);
				Long userNo = rs.getLong(9);
				String name = rs.getString(10);
				
				BoardDTO dto = new BoardDTO();
				dto.setNo(no);
				dto.setTitle(title);
				dto.setContents(contents);
				dto.setHit(hit);
				dto.setRegDate(regDate);
				dto.setGroupNo(groupNo);
				dto.setOrderNo(orderNo);
				dto.setDepth(depth);
				dto.setUserNo(userNo);
				dto.setUserName(name);
				
				list.add(dto);
			}
			
		} catch (SQLException e) {
			System.out.println("BoardDAO findAll() error:" + e);
		} finally {
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
		
		return list;
	}
	
	public boolean insert(BoardVO vo) {
		boolean result = false;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			
			String sql =
					"insert into board values (null, ?, ?, 0,  now(), (SELECT IFNULL(MAX(group_no) + 1, 1) FROM board b), 0, 0, ?)";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContents());
			pstmt.setLong(3, vo.getNo());
			
			int count = pstmt.executeUpdate();
			result = count == 1;
			
		} catch (SQLException e) {
			System.out.println("BoardDAO insert() error:" + e);
		} finally {
			try {
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
	
	public boolean update(BoardVO vo) {
		boolean result = false;

		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			
				String sql =
						" update board " + 
						"    set title=?, contents=?" + 
						"  where no=?";
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, vo.getTitle());
				pstmt.setString(2, vo.getContents());
				pstmt.setLong(3, vo.getNo());
			
			int count = pstmt.executeUpdate();
			result = count == 1;			
		} catch (SQLException e) {
			System.out.println("BoardDAO update() error:" + e);
		} finally {
			try {
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
	
	public boolean delete(BoardVO vo) {
		boolean result = false;

		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			
			String sql =
					" delete" +
					"   from board" +
					"  where no=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setLong(1, vo.getNo());
			
			int count = pstmt.executeUpdate();
			result = count == 1;
			
		} catch (SQLException e) {
			System.out.println("BoardDAO delete() error:" + e);
		} finally {
			try {
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


	public BoardDTO findByNo(Long no) {
		BoardDTO dto = null;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			
			String sql =
				"select b.no, b.title, b.contents, b.hit, b.reg_date, b.group_no, b.order_no, b.depth, u.name, b.user_no" +
				"  from board b, user u" +
				" where b.user_no = u.no" +
				"   and b.no =?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, no);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dto = new BoardDTO();
				
				dto.setNo(rs.getLong(1));
				dto.setTitle(rs.getString(2));
				dto.setContents(rs.getString(3));
				dto.setHit(rs.getLong(4));
				dto.setRegDate(rs.getString(5));
				dto.setGroupNo(rs.getLong(6));
				dto.setOrderNo(rs.getLong(7));
				dto.setDepth(rs.getLong(8));
				dto.setUserName(rs.getString(9));
				dto.setUserNo(rs.getLong(10));
			}
			
		} catch (SQLException e) {
			System.out.println("BoardDAO findByNo() error:" + e);
		} finally {
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
		
		return dto;
	}


	//조회수 처리
	public boolean updateHit(Long no) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			
				String sql =
						" update board " + 
						"    set hit=hit+1" + 
						"  where no=?";
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setLong(1, no);
			
			int count = pstmt.executeUpdate();
			result = count == 1;			

		} catch (SQLException e) {
			System.out.println("BoardDAO updateHit() error:" + e);
		} finally {
			try {
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
	
	
	//게시물 삭제
	public boolean deleteboard(BoardVO vo) {
		boolean result = false;

		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			
				String sql =
						" update board " + 
						"    set title=?, hit=?" + 
						"  where no=?";
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, vo.getTitle());
				pstmt.setLong(2, vo.getHit());
				pstmt.setLong(3, vo.getNo());
			
			int count = pstmt.executeUpdate();
			result = count == 1;			
		} catch (SQLException e) {
			System.out.println("BoardDAO deleteboard() error:" + e);
		} finally {
			try {
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


	public BoardVO findHigerBoard(Long checkNo) {
		BoardVO vo = null;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			
			String sql =
				"select no, group_no, order_no, depth" +
				"  from board" +
				" where no=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, checkNo);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Long no = rs.getLong(1);
				Long groupNo = rs.getLong(2);
				Long orderNo = rs.getLong(3);
				Long depth = rs.getLong(4);
				
				vo = new BoardVO();
				vo.setNo(no);
				vo.setGroupNo(groupNo);
				vo.setOrderNo(orderNo);
				vo.setDepth(depth);
				
			}
			
		} catch (SQLException e) {
			System.out.println("BoardDAO findHigerBoard() error:" + e);
		} finally {
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


	public boolean insertReply(BoardVO vo) {
		boolean result = false;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			
			String sql =
					"insert into board values (null, ?, ?, 0,  now(), ?, ?, ?, ?)";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContents());
			pstmt.setLong(3, vo.getGroupNo());
			pstmt.setLong(4, vo.getOrderNo());
			pstmt.setLong(5, vo.getDepth());
			pstmt.setLong(6, vo.getNo());

			
			int count = pstmt.executeUpdate();
			result = count == 1;
			
		} catch (SQLException e) {
			System.out.println("BoardDAO insertreply() error:" + e);
		} finally {
			try {
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


	public boolean updateParentBoard(BoardVO vo) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			
				String sql =
						" update board " + 
						"    set order_no=?" + 
						"  where no<=?";
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setLong(1, vo.getOrderNo()+1);
				pstmt.setLong(2, vo.getNo());
			
			int count = pstmt.executeUpdate();
			result = count == 1;			
		} catch (SQLException e) {
			System.out.println("BoardDAO updateParentBoard() error:" + e);
		} finally {
			try {
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
	
	
	//전체 게시글 수
	public int countBoard() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int no = 0;

		try {
			conn = getConnection();
			
			String sql =
				"select count(*)" +
				"  from board";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				no = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			System.out.println("BoardDAO countBoard() error:" + e);
		} finally {
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
		
		return no;
	}
	
	
	
	
	
}
