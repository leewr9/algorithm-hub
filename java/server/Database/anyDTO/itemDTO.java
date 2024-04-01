package anyDTO;

import java.io.Serializable;

public class itemDTO implements Serializable {
	private int grade;// 아이템 등급
	private String part;// 장착 부위
	private String name;// 아이템 이름
	private int level;// 착용레벨
	private int stat;// 효과 무기 공격력증가 방어구 방어력증가 포션 현재체력증가
	private int gold;// 가격
	private String get;// 획득방법 상점, 몬스터


	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public String getPart() {
		return part;
	}

	public void setPart(String part) {
		this.part = part;
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

	public int getStat() {
		return stat;
	}

	public void setStat(int stat) {
		this.stat = stat;
	}

	public int getGold() {
		return gold;
	}

	public void setGold(int gold) {
		this.gold = gold;
	}

	public String getGet() {
		return get;
	}

	public void setGet(String get) {
		this.get = get;
	}

}
