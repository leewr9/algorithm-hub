package monsterDTO;

import java.io.Serializable;

public abstract class superMonsterDTO implements Serializable {
	private String name;// �����̸�
	private int level;// ���ͷ���
	private int hp;// ����ü��
	private int attack;// ���Ͱ��ݷ�
	private int defence;// ���͹���
	private String map_name;// �⿬ ��
	private int colsize;// �⿬��ġ ����
	private int rowsize;// �⿬��ġ ����
	private String[] list = new String[2];// ���� ������ ����Ʈ
	
	private static final long serialVersionUID = 1L;

	public abstract String attack();// ���ݹ�� ����

	public int exp() {
		return level * 9;// ���� ������ 9��� ����ġ
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}

	public int getDefence() {
		return defence;
	}

	public void setDefence(int defence) {
		this.defence = defence;
	}

	public String getMap_name() {
		return map_name;
	}

	public void setMap_name(String map_name) {
		this.map_name = map_name;
	}

	public int getColsize() {
		return colsize;
	}

	public void setColsize(int colsize) {
		this.colsize = colsize;
	}

	public int getRowsize() {
		return rowsize;
	}

	public void setRowsize(int rowsize) {
		this.rowsize = rowsize;
	}

	public String[] getList() {
		return list;
	}

	public void setList(String[] list) {
		this.list = list;
	}
}
