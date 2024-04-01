package allDAO;

import java.util.ArrayList;

import anyDTO.itemDTO;

public class itemDAO extends superDAO {
	private static itemDAO item = null;

	private itemDAO() {

	}

	public static itemDAO getInstance() {
		if (item == null) {
			item = new itemDAO();
		}
		return item;
	}
	
	public ArrayList<itemDTO> loading() {
		ArrayList<itemDTO> list = new ArrayList<>(23);
		itemDTO item = null;
		if (cn() != null) {
			try {
				st = cn.createStatement();
				rs = st.executeQuery("select * from item");
				while (rs.next()) {
					item = new itemDTO();
					item.setGrade(rs.getInt("grade"));
					item.setPart(rs.getString("type"));
					item.setName(rs.getString("name"));
					item.setLevel(rs.getInt("lv"));
					item.setStat(rs.getInt("stat"));
					item.setGold(rs.getInt("price"));
					list.add(item);
				}
			} catch (Exception e) {
			} finally {
					discn();
			}
		}
		return list;
	}

}
