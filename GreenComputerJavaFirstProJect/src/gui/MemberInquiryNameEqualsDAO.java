package gui;

import java.sql.*;
import java.util.*;

public class MemberInquiryNameEqualsDAO {
	// ����
	private Connection conn;
	// ���� ���� => SQL
	private PreparedStatement ps;
	// ���� => ����Ŭ �ּ�
	String driver = "oracle.jdbc.driver.OracleDriver";
	String URL = "jdbc:oracle:thin:@localhost:1521:XE";
	String user = "c##green1234";
	String password = "green1234";

	// ����̹� ���
	public MemberInquiryNameEqualsDAO() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

	// ����
	public void getConnection() {
		try {
			conn = DriverManager.getConnection(URL, user, password);
			// conn hr/happy
		} catch (Exception ex) {
		}
	}

	// �ݱ�
	public void disConnection() {
		try {
			if (ps != null)
				ps.close();
			if (conn != null)
				conn.close();
			// exit
		} catch (Exception ex) {
		}
	}

	// �����ȣ ã��
	public ArrayList<MemberRegistrationVo> postfind(String re_name) {
		ArrayList<MemberRegistrationVo> list = new ArrayList<MemberRegistrationVo>();

		try {
			// ����
			getConnection();
			// SQL ���� ����
			String sql = "SELECT * FROM Member_Registration " + "WHERE re_name LIKE '%'||?||'%'";
			ps = conn.prepareStatement(sql);
			ps.setString(1, re_name);
			ResultSet rs = ps.executeQuery();// ����
			while (rs.next()) {
				MemberRegistrationVo vo = new MemberRegistrationVo();
				vo.setRe_code(rs.getString(1));
				vo.setRe_name(rs.getString(2));
				vo.setRe_age(rs.getString(3));
				vo.setRe_height(rs.getString(4));
				vo.setRe_weight(rs.getString(5));

				list.add(vo);
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		} finally {
			disConnection();
		}
		return list;
	}
}