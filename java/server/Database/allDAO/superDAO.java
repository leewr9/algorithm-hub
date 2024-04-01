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
	protected PreparedStatement ps;// 동적 쿼리문 하위클래스 실행을 위한 프로텍티드
	protected Statement st;// 정적 쿼리문
	protected ResultSet rs;// 데이터베이스 정보 저장

	public Connection cn() {// 접속
		try {
			Class.forName(driver);
			cn = DriverManager.getConnection(url, id, pwd);
			return cn;
		} catch (Exception e) {
		}
		return null;
	}

	public void discn() {// 접속해지
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
