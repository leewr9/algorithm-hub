package allDAO;

public class membershipDAO extends superDAO {
	private static membershipDAO membership = null;

	private membershipDAO() {

	}

	public static membershipDAO getInstance() {
		if (membership == null) {
			membership = new membershipDAO();
		}
		return membership;
	}

	public boolean login(String id, int pw) {
		if (cn() != null) {
			try {
				ps = cn.prepareStatement("select * from membership where id = ? and pw = ?");
				ps.setString(1, id);
				ps.setInt(2, pw);
				rs = ps.executeQuery();
				if (rs.next()) {
					return true;
				} else {
					return false;
				}
			} catch (Exception e) {
			} finally {
				discn();
			}
		}
		return false;
	}
	
	public boolean idChk(String id) {
		if (cn() != null) {
			try {
				ps = cn.prepareStatement("select * from membership where id = ?");
				ps.setString(1, id);
				rs = ps.executeQuery();
				if (rs.next()) {
					return true;
				} else {
					return false;
				}
			} catch (Exception e) {
			} finally {
				discn();
			}
		}
		return false;
	}

	public void join(String id, int pw) {
		if (cn() != null) {
			try {
				ps = cn.prepareStatement("insert into membership values( membership_sqe.nextval , ? , ? )");
				ps.setString(1, id);
				ps.setInt(2, pw);
				ps.executeUpdate();
			} catch (Exception e) {
			} finally {
				discn();
			}
		}
	}
	
	public void drop(String id, int pw) {
		if (cn() != null) {
			try {
				ps = cn.prepareStatement("delete from membership where id = ? and pw = ?");
				ps.setString(1, id);
				ps.setInt(2, pw);
				ps.executeUpdate();
			} catch (Exception e) {
			} finally {
				discn();
			}
		}
	}

}
