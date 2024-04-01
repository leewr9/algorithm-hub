package TestDAO;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import allDAO.inventoryDAO;
import allDAO.itemDAO;
import allDAO.membershipDAO;
import allDAO.monsterDAO;
import allDAO.playerDAO;
import anyDTO.inventoryDTO;
import anyDTO.playerDTO;
import monsterDTO.superMonsterDTO;

public class jUint {
	private inventoryDAO inventory = null;
	private itemDAO item = null;
	private membershipDAO membership = null;
	private monsterDAO monster = null;
	private playerDAO player = null;

	@Before
	public void load() {
		inventory = inventoryDAO.getInstance();
		item = itemDAO.getInstance();
		membership = membershipDAO.getInstance();
		monster = monsterDAO.getInstance();
		player = playerDAO.getInstance();
	}

	@Test
	public void membershipTest() {
		membership.join("leewr9", 9790);
		System.out.println(membership.login("leewr9", 9790));
		membership.drop("leewr9", 9790);
	}

	@Test
	public void playerTest() {
//		player.join("leewr9", "루암");
		playerDTO p = player.loading("leewr9");
		System.out.println(p.getName());
		System.out.println(p.getLevel());
		System.out.println(p.getId());
		System.out.println(p.getExp());
		p.setLevel(100);
		player.saving(p);
	}

	@Test
	public void inventoryTest() {
		inventory.join("루암");
		inventoryDTO i = inventory.loading("루암");
		System.out.println(i.getArmor());
		System.out.println(i.getWeapon());
		System.out.println(i.getGold());
//		System.out.println(i.getList().length);
		i.setGold(10000);
		String[] l = { "하급물약", "상급물약" };
//		i.setList(l);
		inventory.saving(i);
	}

	@Test
	public void itemTest() {
//		ArrayList<superItemDTO> list = item.loading();
//		System.out.println(list.size());
//		for (int i = 0; i < list.size(); i++) {
//			System.out.println(list.get(i).getName());
//			System.out.println(list.get(i).getGold());
//		}
	}

	@Test
	public void monsterTest() {
		ArrayList<superMonsterDTO> list = monster.loading("합동");
		System.out.println(list.size());
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).getName());
			System.out.println(list.get(i).getLevel());
		}
	}

}
