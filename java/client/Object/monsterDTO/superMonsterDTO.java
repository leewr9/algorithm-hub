package monsterDTO;

import java.io.Serializable;

public abstract class superMonsterDTO implements Serializable {
	private String name;// 몬스터이름
	private int level;// 몬스터레벨
	private int hp;// 몬스터체력
	private int attack;// 몬스터공격력
	private int defence;// 몬스터방어력
	private String map_name;// 출연 맵
	private int colsize;// 출연위치 세로
	private int rowsize;// 출연위치 가로
	private String[] list = new String[2];// 보유 아이템 리스트
	
	private static final long serialVersionUID = 1L;

	public abstract String attack();// 공격방식 지정

	public int exp() {
		return level * 9;// 몬스터 레벨의 9배수 경험치
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
