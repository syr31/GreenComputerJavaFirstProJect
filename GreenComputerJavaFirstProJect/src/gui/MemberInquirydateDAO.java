package gui;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class MemberInquirydateDAO {
	// ����
	private Connection conn;
	private PreparedStatement ps;
	
	String driver = "oracle.jdbc.driver.OracleDriver";
	String URL = "jdbc:oracle:thin:@localhost:1521:XE";
	String user = "c##green1234";
	String password = "green1234";
	
	public MemberInquirydateDAO() {
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
			// exit
		} catch (Exception ex) {
		}
	}
	
public ArrayList<HealthRecordVO> postfind(String health_date) {
	ArrayList<HealthRecordVO> list = new ArrayList<HealthRecordVO>();

	try {
		if (conn != null) {
			String sql = "SELECT * FROM Health_record " + "WHERE health_date LIKE '%'||?||'%'";
			ps = conn.prepareStatement(sql);
			ps.setString(1, health_date);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				HealthRecordVO vo = new HealthRecordVO();
				
				vo.setHealth_date(rs.getString(1));
				vo.setHealth_event(rs.getString(2));
				vo.setHealth_wei(rs.getString(3));
				vo.setHealth_set(rs.getString(4));
				vo.setHealth_num(rs.getString(5));

				list.add(vo);
			}
		}
		
	} catch (Exception ex) {
		System.out.println(ex.getMessage());
	}finally {
		disConnection();
	}

	return list;

}
}
