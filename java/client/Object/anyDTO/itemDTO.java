package anyDTO;

import java.io.Serializable;

public class itemDTO implements Serializable {
	private int grade;// ������ ���
	private String part;// ���� ����
	private String name;// ������ �̸�
	private int level;// ���뷹��
	private int stat;// ȿ�� ���� ���ݷ����� �� �������� ���� ����ü������
	private int gold;// ����
	private String get;// ȹ���� ����, ����


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
