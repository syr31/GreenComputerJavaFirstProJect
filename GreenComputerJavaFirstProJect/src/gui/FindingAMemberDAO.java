package gui;
import java.sql.*;
import java.util.*;

public class FindingAMemberDAO {
	private Connection conn;
	private PreparedStatement ps;
	
	String driver = "oracle.jdbc.driver.OracleDriver";
	String URL = "jdbc:oracle:thin:@localhost:1521:XE";
	String user = "c##green1234";
	String password = "green1234";

	public FindingAMemberDAO() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

	public void getConnection() {
		try {
			conn = DriverManager.getConnection(URL, user, password);
		} catch (Exception ex) {
		}
	}

	public void disConnection() {
		try {
			if (ps != null)
				ps.close();
			if (conn != null)
				conn.close();
		} catch (Exception ex) {
		}
	}

	public ArrayList<MemberSignUpVo> postfind(String name) {
		ArrayList<MemberSignUpVo> list = new ArrayList<MemberSignUpVo>();

		try {
			getConnection();
			
			String sql = "SELECT username,password,name,gender FROM Member " + "WHERE name LIKE '%'||?||'%'";
			ps = conn.prepareStatement(sql);
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				MemberSignUpVo vo = new MemberSignUpVo();
				vo.setUsername(rs.getString(1));
				vo.setPassword(rs.getString(2));
				vo.setName(rs.getString(3));
				vo.setGender(rs.getString(4));

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