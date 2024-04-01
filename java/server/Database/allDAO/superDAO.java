package allDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class superDAO {
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:orcl";
	private String id = "leewr9";
	private String pwd = "9790";

	protected Connection cn = null;
	protected PreparedStatement ps;// ���� ������ ����Ŭ���� ������ ���� ������Ƽ��
	protected Statement st;// ���� ������
	protected ResultSet rs;// �����ͺ��̽� ���� ����

	public Connection cn() {// ����
		try {
			Class.forName(driver);
			cn = DriverManager.getConnection(url, id, pwd);
			return cn;
		} catch (Exception e) {
		}
		return null;
	}

	public void discn() {// ��������
		try {
			if (cn != null) {
				cn.close();
			} else if (ps != null) {
				ps.close();
			} else if (st != null) {
				st.close();
			} else if (rs != null) {
				rs.close();
			}
		} catch (Exception e) {
		}
	}
	
}
