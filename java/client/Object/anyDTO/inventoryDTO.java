package anyDTO;

import java.util.ArrayList;

public class inventoryDTO {
	private String name;// ĳ����id
	private String weapon;// ��������
	private String armor;// ������
	private int gold;// ���� ���
	private ArrayList<String> list = new ArrayList<>(5);// ���� ������ ����Ʈ

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
