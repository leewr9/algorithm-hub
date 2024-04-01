package allDAO;

import java.util.Vector;

import anyDTO.logDTO;

public class logDAO extends superDAO {
	private static logDAO log = null;

	private logDAO() {

	}

	public static logDAO getInstance() {
		if (log == null) {
			log = new logDAO();
		}
		return log;
	}

	public Vector<logDTO> find(String id) {
		Vector<logDTO> log = new Vector<>();
		logDTO visit = null;
		if (cn() != null) {
			try {
				ps = cn.prepareStatement("select * from log where id = ? ");
				ps.setString(1, id);
				rs = ps.executeQuery();
				while (rs.next()) {
					visit = new logDTO();
					visit.load(rs.getString("id"), rs.getString("address"), rs.getString("starttime"), rs.getString("endtime"));
					log.add(visit);
				}
			} catch (Exception e) {
			} finally {
				discn();
			}
		}
		return log;
	}
	
	public Vector<logDTO> allfind() {
		Vector<logDTO> log = new Vector<>();
		logDTO visit = null;
		if (cn() != null) {
			try {
				st = cn.createStatement();
				rs = st.executeQuery("select * from log");
				while (rs.next()) {
					visit = new logDTO();
					visit.load(rs.getString("id"), rs.getString("address"), rs.getString("starttime"), rs.getString("endtime"));
					log.add(visit);
				}
			} catch (Exception e) {
			} finally {
				discn();
			}
		}
		return log;
	}

	public void input(logDTO log) {
		if (cn() != null) {
			try {
				ps = cn.prepareStatement("insert into log values( ? , ? , ?, ? )");
				ps.setString(1, log.getId());
				ps.setString(2, log.getAddress());
				ps.setString(3, log.getStart());
				ps.setString(4, log.getEnd());
				ps.executeUpdate();
			} catch (Exception e) {
			} finally {
				discn();
			}
		}
	}
}
