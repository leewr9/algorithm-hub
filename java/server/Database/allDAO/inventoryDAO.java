package allDAO;

import java.util.ArrayList;

import anyDTO.inventoryDTO;

public class inventoryDAO extends superDAO {
	private static inventoryDAO inventory = null;

	private inventoryDAO() {

	}

	public static inventoryDAO getInstance() {
		if (inventory == null) {
			inventory = new inventoryDAO();
		}
		return inventory;
	}

	public void join(String name) {
		if (cn() != null) {
			try {
				ps = cn.prepareStatement("insert into inventory values( ? , default, default, default, default)");
				ps.setString(1, name);
				ps.executeUpdate();
			} catch (Exception e) {
			} finally {
				discn();
			}
		}
	}

	public inventoryDTO loading(String name) {
		inventoryDTO inventory = new inventoryDTO();
		ArrayList<String> tempList = new ArrayList<>(5);
		String[] temp;
		if (cn() != null) {
			try {
				ps = cn.prepareStatement("select * from inventory where name = ?");
				ps.setString(1, name);
				rs = ps.executeQuery();
				if (rs.next()) {
					inventory.setName(rs.getString("name"));
					inventory.setWeapon(rs.getString("weapon"));
					inventory.setArmor(rs.getString("armor"));
					temp = rs.getString("list").split(" ");
					for(String i : temp) {
						tempList.add(i);
					}
					inventory.setList(tempList);
					inventory.setGold(rs.getInt("gold"));
				}
			} catch (Exception e) {
			} finally {
				discn();
			}
		}
		return inventory;
	}

	public void saving(inventoryDTO inventory) {
		String item = "";
		if (cn() != null) {
			try {
				ps = cn.prepareStatement(
						"update inventory set weapon = ?, armor = ?, gold = ?, list = ? where name = ?");
				ps.setString(1, inventory.getWeapon());
				ps.setString(2, inventory.getArmor());
				ps.setInt(3, inventory.getGold());
				for (String i : inventory.getList()) {
					item += i + " ";
				}
				ps.setString(4, item);
				ps.setString(5, inventory.getName());
				ps.executeUpdate();
			} catch (Exception e) {
			} finally {
				discn();
			}
		}

	}
}
