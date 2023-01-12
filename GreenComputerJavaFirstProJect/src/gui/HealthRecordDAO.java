package gui;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class HealthRecordDAO extends MemberDAO {

	private Connection con;
	private Statement stmt;
	private ResultSet rs;

	public ArrayList<HealthRecordVO> list(String Health_date, String Health_event, String Health_wei, String Health_set,
			String Health_num, String Health_pk) {
		ArrayList<HealthRecordVO> list = new ArrayList<HealthRecordVO>();

		try {
			connDB();

			String query = "select * from HEALTH_RECORD ";
			if (Health_date != null && Health_event != null && Health_wei != null && Health_set != null
					&& Health_num != null && Health_pk != null) {
				query += "where Health_date = '" + Health_date.toLowerCase() + "'";
				query += "or Health_event = '" + Health_event.toLowerCase() + "'";
				query += "or Health_wei = '" + Health_wei.toLowerCase() + "'";
				query += "or Health_set = '" + Health_set.toLowerCase() + "'";
				query += "or Health_num = '" + Health_num.toLowerCase() + "'";
				query += "or Health_pk = '" + Health_pk.toLowerCase() + "'";
			}
			System.out.println("SQL : " + query);

			rs = stmt.executeQuery(query);

			rs.last();
			System.out.println("rs.getRow() :" + rs.getRow());
			if (rs.getRow() == 0) {
				System.out.println("0 row selected....");
				rs.previous();
				query = "insert into HEALTH_RECORD(Health_date,Health_event,Health_wei,Health_set,Health_num,Health_pk) ";
				query += "VALUES('" + Health_date + "','" + Health_event + "', '" + Health_wei + "', '" + Health_set
						+ "', '" + Health_num + "', '" + Health_pk + "')";
				stmt.executeUpdate(query);
				System.out.println("Join complete");
				JOptionPane.showMessageDialog(null, "������ ����Ǿ����ϴ�");

			} else {
				System.out.println(rs.getRow() + "rows selected...");
				System.out.println("can not join");
				JOptionPane.showMessageDialog(null, "���� ������ �����Ͽ����ϴ�. �ٽ� Ȯ�����ּ���", "ERROR_MESSAGE",
						JOptionPane.ERROR_MESSAGE);

			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "���� ������ �����Ͽ����ϴ�. �ٽ� Ȯ�����ּ���.", "ERROR_MESSAGE",
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