package anyDTO;

import java.util.ArrayList;

public class inventoryDTO {
	private String name;// 캐릭터id
	private String weapon;// 장착무기
	private String armor;// 장착방어구
	private int gold;// 보유 골드
	private ArrayList<String> list = new ArrayList<>(5);// 소지 아이템 리스트

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getWeapon() {
		return weapon;
	}

	public void setWeapon(String weapon) {
		this.weapon = weapon;
	}

	public String getArmor() {
		return armor;
	}

	public void setArmor(String armor) {
		this.armor = armor;
	}

	public int getGold() {
		return gold;
	}

	public void setGold(int gold) {
		this.gold = gold;
	}

	public ArrayList<String> getList() {
		return list;
	}

	public void setList(ArrayList<String> list) {
		this.list = list;
	}

}
