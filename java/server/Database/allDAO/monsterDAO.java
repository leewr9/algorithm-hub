package allDAO;

import java.util.ArrayList;

import monsterDTO.bossDTO;
import monsterDTO.earthDTO;
import monsterDTO.skyDTO;
import monsterDTO.superMonsterDTO;
import monsterDTO.waterDTO;

public class monsterDAO extends superDAO {
	private static monsterDAO monster = null;

	private monsterDAO() {

	}

	public static monsterDAO getInstance() {
		if (monster == null) {
			monster = new monsterDAO();
		}
		return monster;
	}

	public ArrayList<superMonsterDTO> loading(String name) {
		ArrayList<superMonsterDTO> list = new ArrayList<>(20);
		superMonsterDTO monster = null;
		String sql = null;
		
		switch (name) {
		case "¶¥":
			sql = "select * from earth_monster where map_name = ?";
			break;
		case "¹°":
			sql = "select * from water_monster where map_name = ?";
			break;
		case "ÇÏ´Ã":
			sql = "select * from sky_monster where map_name = ?";
			break;
		case "ÇÕµ¿":
			sql = "select * from boss_monster where map_name = ?";
			break;
		}
		
		if (cn() != null) {
			try {
				ps = cn.prepareStatement(sql);
				ps.setString(1, name);
				rs = ps.executeQuery();
				while (rs.next()) {
					switch (name) {
					case "¶¥":
						monster = new earthDTO();
						break;
					case "¹°":
						monster = new waterDTO();
						break;
					case "ÇÏ´Ã":
						monster = new skyDTO();
						break;
					case "ÇÕµ¿":
						monster = new bossDTO();
						break;
					}
					monster.setName(rs.getString("name"));
					monster.setLevel(rs.getInt("lv"));
					monster.setHp(rs.getInt("hp"));
					monster.setAttack(rs.getInt("attack"));
					monster.setDefence(rs.getInt("defence"));
					monster.setList(rs.getString("list").split(" "));
					monster.setMap_name(rs.getString("map_name"));
					monster.setColsize(rs.getInt("colsize"));
					monster.setRowsize(rs.getInt("rowsize"));
					list.add(monster);
				}
			} catch (Exception e) {
			} finally {
					discn();
			}
		}
		return list;
	}

}
