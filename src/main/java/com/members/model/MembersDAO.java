package com.members.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.members.model.MembersVO;

public class MembersDAO implements MembersDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/CJA10135WebApp");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO members (member_email, member_password_hash, member_password_salt, member_name, member_birthdate, member_gender, member_phone, member_address, member_photo, member_created_at, member_updated_at, member_last_login_time, member_status)"
			+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT member_id, member_email, member_password_hash, member_password_salt, member_name, member_birthdate, member_gender, member_phone, member_address, member_photo, member_created_at, member_updated_at, member_last_login_time, member_status FROM members order by member_id";
	private static final String GET_ONE_STMT = "SELECT member_id, member_email, member_password_hash, member_password_salt, member_name, member_birthdate, member_gender, member_phone, member_address, member_photo, member_created_at, member_updated_at, member_last_login_time, member_status FROM members where member_id = ?";
	private static final String DELETE = "DELETE FROM members where member_id = ?";
	private static final String UPDATE = "UPDATE members set member_email=?, member_password_hash=?, member_password_salt=?, member_name=?, member_birthdate=?, member_gender=?, member_phone=?, member_address=?, member_photo=?, member_created_at=?, member_updated_at=?, member_last_login_time=?, member_status=? where member_id = ?";

	@Override
	public void insert(MembersVO membersVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, membersVO.getMemberEmail());
			pstmt.setString(2, membersVO.getMemberPasswordHash());
			pstmt.setString(3, membersVO.getMemberPasswordSalt());
			pstmt.setString(4, membersVO.getMemberName());
			pstmt.setDate(5, membersVO.getMemberBirthdate());
			pstmt.setInt(6, membersVO.getMemberGender());
			pstmt.setString(7, membersVO.getMemberPhone());
			pstmt.setString(8, membersVO.getMemberAddress());
			pstmt.setBytes(9, membersVO.getMemberPhoto());
			pstmt.setTimestamp(10, membersVO.getMemberCreatedAt());
			pstmt.setTimestamp(11, membersVO.getMemberUpdatedAt());
			pstmt.setTimestamp(12, membersVO.getMemberLastLoginTime());
			pstmt.setInt(13, membersVO.getMemberStatus());

			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public void update(MembersVO membersVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, membersVO.getMemberEmail());
			pstmt.setString(2, membersVO.getMemberPasswordHash());
			pstmt.setString(3, membersVO.getMemberPasswordSalt());
			pstmt.setString(4, membersVO.getMemberName());
			pstmt.setDate(5, membersVO.getMemberBirthdate());
			pstmt.setInt(6, membersVO.getMemberGender());
			pstmt.setString(7, membersVO.getMemberPhone());
			pstmt.setString(8, membersVO.getMemberAddress());
			pstmt.setBytes(9, membersVO.getMemberPhoto());
			pstmt.setTimestamp(10, membersVO.getMemberCreatedAt());
			pstmt.setTimestamp(11, membersVO.getMemberUpdatedAt());
			pstmt.setTimestamp(12, membersVO.getMemberLastLoginTime());
			pstmt.setInt(13, membersVO.getMemberStatus());
			pstmt.setInt(14, membersVO.getMemberId());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public void delete(Integer memberId) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, memberId);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public MembersVO findByPrimaryKey(Integer memberId) {
		// TODO Auto-generated method stub

		MembersVO membersVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, memberId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// membersVo 也稱為 Domain objects
				membersVO = new MembersVO();
				membersVO.setMemberId(rs.getInt("member_id"));
				membersVO.setMemberEmail(rs.getString("member_email"));
				membersVO.setMemberPasswordHash(rs.getString("member_password_hash"));
				membersVO.setMemberPasswordSalt(rs.getString("member_password_salt"));
				membersVO.setMemberName(rs.getString("member_name"));
				membersVO.setMemberBirthdate(rs.getDate("member_birthdate"));
				membersVO.setMemberGender(rs.getInt("member_gender"));
				membersVO.setMemberPhone(rs.getString("member_phone"));
				membersVO.setMemberAddress(rs.getString("member_address"));
				membersVO.setMemberPhoto(rs.getBytes("member_photo"));
				membersVO.setMemberCreatedAt(rs.getTimestamp("member_created_at"));
				membersVO.setMemberUpdatedAt(rs.getTimestamp("member_updated_at"));
				membersVO.setMemberLastLoginTime(rs.getTimestamp("member_last_login_time"));
				membersVO.setMemberStatus(rs.getInt("member_status"));
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return membersVO;
	}

	@Override
	public List<MembersVO> getAll() {
		// TODO Auto-generated method stub
		List<MembersVO> list = new ArrayList<MembersVO>();
		MembersVO membersVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// membersVO 也稱為 Domain objects
				membersVO = new MembersVO();
				membersVO.setMemberId(rs.getInt("member_id"));
				membersVO.setMemberEmail(rs.getString("member_email"));
				membersVO.setMemberPasswordHash(rs.getString("member_password_hash"));
				membersVO.setMemberPasswordSalt(rs.getString("member_password_salt"));
				membersVO.setMemberName(rs.getString("member_name"));
				membersVO.setMemberBirthdate(rs.getDate("member_birthdate"));
				membersVO.setMemberGender(rs.getInt("member_gender"));
				membersVO.setMemberPhone(rs.getString("member_phone"));
				membersVO.setMemberAddress(rs.getString("member_address"));
				membersVO.setMemberPhoto(rs.getBytes("member_photo"));
				membersVO.setMemberCreatedAt(rs.getTimestamp("member_created_at"));
				membersVO.setMemberUpdatedAt(rs.getTimestamp("member_updated_at"));
				membersVO.setMemberLastLoginTime(rs.getTimestamp("member_last_login_time"));
				membersVO.setMemberStatus(rs.getInt("member_status"));

				list.add(membersVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}
}
