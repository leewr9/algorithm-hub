package anyDTO;

public class playerDTO {
	private String name;// 캐릭터 이름
	private int level;// 레벨
	private int exp;// 현재 경험치
	private int maxexp;// 레벨업을 위한 경험치
	private int hp;// 현재체력
	private int maxhp;// 최대체력
	private int attack;// 공격력
	private int defence;// 방어력
	private int strength;// 힘
	private int agility;// 민첩
	private int point;// 스탯 포인트
	private String gender;// 성별
	private String id;// 유저id

	public void setAtk(int atk) {
		this.attack = atk;
	}

	public void setDef(int def) {
		this.defence = def;
	}

	public void setMe(int maxexp) {
		this.maxexp = maxexp;
	}

	public void setMh(int maxhp) {
		this.maxhp = maxhp;
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

	public int getExp() {
		return exp;
	}

	public void setExp(int exp) {
		this.exp = exp;
	}

	public int getMaxexp() {
		return maxexp;
	}

	public void setMaxexp() {
		if (level < 10) {
			this.maxexp = this.level * 10;
		} else if (level < 20) {
			this.maxexp = this.level * 20;
		} else if (level < 30) {
			this.maxexp = this.level * 30;
		} else if (level < 40) {
			this.maxexp = this.level * 40;
		} else {
			this.maxexp = this.level * 50;
		}
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getMaxhp() {
		return maxhp;
	}

	public void setMaxhp() {
		this.maxhp = (this.strength + this.agility) * 10;
	}

	public int getAttack() {
		return attack;
	}

	public void setAttack() {
		this.attack = this.strength * 10;
	}

	public int getDefence() {
		return defence;
	}

	public void setDefence() {
		this.defence = this.agility * 1;
	}

	public int getStrength() {
		return strength;
	}

	public void setStrength(int strength) {
		this.strength = strength;
	}

	public int getAgility() {
		return agility;
	}

	public void setAgility(int agility) {
		this.agility = agility;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

}
