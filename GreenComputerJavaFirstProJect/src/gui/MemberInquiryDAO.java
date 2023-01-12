package gui;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.*;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class MemberInquiryDAO extends MemberDAO {

	private Connection con;
	private Statement stmt;
	private ResultSet rs;

	public ArrayList<MemberRegistrationVo> list(String re_code, String re_name, String re_age, String re_height,
			String re_weight) {
		ArrayList<MemberRegistrationVo> list = new ArrayList<MemberRegistrationVo>();

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			connDB();
			String sql = "SELECT * FROM Member_Registration";
			rs = stmt.executeQuery(sql);
//			rs.last();

			System.out.println("rs.getRow() :" + rs.getRow());

			while (rs.next()) {
				String code = rs.getString("re_code");
				String name = rs.getString("re_name");
				String age = rs.getString("re_age");
				String hei = rs.getString("re_height");
				String wei = rs.getString("re_weight");

				Object data[] = { rs.getString("re_code"), rs.getString("re_name"), rs.getString("re_age"),
						rs.getString("re_height"), rs.getString("re_weight") };

				System.out.println(code + ", " + name + ", " + age + ", " + hei + ", " + wei);


			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "���� �ҷ����� �� �߽��ϴ�.", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		return list;
	}
	

	public void connDB() {

		try {
			con = DriverManager.getConnection(url, user, password);
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}