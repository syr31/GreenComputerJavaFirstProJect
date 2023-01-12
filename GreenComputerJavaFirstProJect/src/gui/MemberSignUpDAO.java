package gui;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class MemberSignUpDAO extends MemberDAO {

	private Connection con;
	private Statement stmt;
	private ResultSet rs;

	public ArrayList<MemberSignUpVo> list(String username, String password, String name, String gender, String sortation,
			String phone) {
		ArrayList<MemberSignUpVo> list = new ArrayList<MemberSignUpVo>();
		try {
			connDB();
			String query = "SELECT * FROM MEMBER ";
			if (username != null && password != null && name != null && gender != null && sortation != null
					&& phone != null) {
				query += "where username = '" + username.toLowerCase() + "'";
			}
			System.out.println("SQL : " + query);

			rs = stmt.executeQuery(query);

			rs.last();
			System.out.println("rs.getRow() :" + rs.getRow());
			if (rs.getRow() == 0) {
				System.out.println("0 row selected...");

				rs.previous();
				query = "INSERT INTO MEMBER(username,password,name,gender,sortation,phone) ";
				query += "VALUES('" + username + "', '" + password + "', '" + name + "', '" + gender + "', '" + sortation
						+ "', '" + phone + "')";
				stmt.executeUpdate(query);
				System.out.println("Join complete");
				JOptionPane.showMessageDialog(null, "회원가입이 완료되었습니다.");

			} else {
				System.out.println(rs.getRow() + "rows selected...");
				System.out.println("con not join");
				JOptionPane.showMessageDialog(null, "회원가입에 실패하였습니다. 중복된 아이디가 있습니다.", "ERROR_MESSAGE",
						JOptionPane.ERROR_MESSAGE);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "회원가입에 실패하였습니다. 회원정보를 다시 확인해주세요.", "ERROR_MESSAGE",
					JOptionPane.ERROR_MESSAGE);
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