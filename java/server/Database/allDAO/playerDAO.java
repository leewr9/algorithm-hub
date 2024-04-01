package allDAO;

import anyDTO.playerDTO;

public class playerDAO extends superDAO {
	private static playerDAO player = null;

	private playerDAO() {

	}

	public static playerDAO getInstance() {
		if (player == null) {
			player = new playerDAO();
		}
		return player;
	}

	public void join(String id, String name, String gender) {
		if (cn() != null) {
			try {
				ps = cn.prepareStatement(
						"insert into player values( ?, default, default, default, default, default, default, default, default, default, default, ?, ?)");
				ps.setString(1, name);
				ps.setString(2, gender);
				ps.setString(3, id);
				ps.executeUpdate();
			} catch (Exception e) {
			} finally {
				discn();
			}
		}
	}
	
	public boolean login(String id) {
		if (cn() != null) {
			try {
				ps = cn.prepareStatement("select * from player where id = ?");
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

	public playerDTO loading(String id) {
		playerDTO player = new playerDTO();
		if (cn() != null) {
			try {
				ps = cn.prepareStatement("select * from player where id = ?");
				ps.setString(1, id);
				rs = ps.executeQuery();
				if (rs.next()) {
					player.setName(rs.getString("name"));
					player.setLevel(rs.getInt("lv"));
					player.setExp(rs.getInt("exp"));
					player.setHp(rs.getInt("hp"));
					player.setStrength(rs.getInt("strength"));
					player.setAgility(rs.getInt("agility"));
					player.setPoint(rs.getInt("point"));
					player.setId(rs.getString("id"));
					player.setGender(rs.getString("gender"));
					player.setMaxexp();
					player.setMaxhp();
					player.setAttack();
					player.setDefence();
				}
			} catch (Exception e) {
			} finally {
				discn();
			}
		}

		return player;
	}

	public void saving(playerDTO player) {
		if (cn() != null) {
			try {
				ps = cn.prepareStatement(
						"update player set lv = ?, exp = ?, maxexp = ?, hp = ?, maxhp = ?, attack = ?, defence = ?, strength = ?, agility = ?, point = ?, gender = ? where name = ?");
				ps.setInt(1, player.getLevel());
				ps.setInt(2, player.getExp());
				ps.setInt(3, player.getMaxexp());
				ps.setInt(4, player.getHp());
				ps.setInt(5, player.getMaxhp());
				ps.setInt(6, player.getAttack());
				ps.setInt(7, player.getDefence());
				ps.setInt(8, player.getStrength());
				ps.setInt(9, player.getAgility());
				ps.setInt(10, player.getPoint());
				ps.setString(11, player.getGender());
				ps.setString(12, player.getName());
				ps.executeUpdate();
			} catch (Exception e) {
			} finally {
				discn();
			}
		}

	}

}
