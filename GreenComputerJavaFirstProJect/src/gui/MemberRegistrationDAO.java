package gui;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class MemberRegistrationDAO extends MemberDAO {

	private Connection con;
	private Statement stmt;
	private ResultSet rs;

	public ArrayList<MemberRegistrationVo> list(String re_code, String re_name, String re_age, String re_height,
			String re_weight) {
		ArrayList<MemberRegistrationVo> list = new ArrayList<MemberRegistrationVo>();
		try {
			connDB();

			String query = "SELECT * FROM Member_Registration ";
			if (re_code != null && re_name != null && re_age != null && re_height != null && re_weight != null) {
				query += "where re_code = '" + re_code.toLowerCase() + "'";
				query += "or re_name = '" + re_name.toLowerCase() + "'";
				query += "or re_age = '" + re_age.toLowerCase() + "'";
				query += "or re_height = '" + re_height.toLowerCase() + "'";
				query += "or re_weight = '" + re_weight.toLowerCase() + "'";
			}
			
			System.out.println("SQL : " + query);

			rs = stmt.executeQuery(query);

			rs.last();

			System.out.println("rs.getRow() :" + rs.getRow());
			
			if (rs.getRow() == 0) {
				System.out.println("0 row selected...");
				rs.previous();

				query = "insert into Member_Registration(re_code,re_name,re_age,re_height,re_weight)";
				query += "VALUES('" + re_code + "', '" + re_name + "', '" + re_age + "', '"+ re_height + "', '" + re_weight + "')";
				stmt.executeUpdate(query);
				System.out.println("���������� ��ϵǾ����ϴ�.");
				JOptionPane.showMessageDialog(null, "���������� ��ϵǾ����ϴ�.");

			} else {
				System.out.println(rs.getRow() + "rows selected...");
				System.out.println("���� �ٽ� �ѹ� Ȯ�����ּ���.");
				JOptionPane.showMessageDialog(null, "���� �ٽ� �ѹ� Ȯ�����ּ���.", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "���� �ٽ� �ѹ� Ȯ�����ּ���.", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
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