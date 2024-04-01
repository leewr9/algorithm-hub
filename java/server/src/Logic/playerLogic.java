package Logic;

import INF.allINFImpl;

public class playerLogic {
	private allINFImpl inf = new allINFImpl();
	private startLogic start = null;// ��ü ���� �Ŀ� �Է�
	private String id = null;
	private String gender = null;
	private static playerLogic player = null;

	private playerLogic() {

	}

	public static playerLogic getInstance() {
		if (player == null)
			player = new playerLogic();
		return player;
	}

	public String gender() {
		return gender;
	}

	public String idIn() {
		return id;
	}

	public boolean join(String id, int pw) {//ȸ������
		return inf.memberJoin(id, pw);
	}

	public boolean drop(String id, int pw) {//ȸ��Ż��
		return inf.memberDrop(id, pw);
	}

	public boolean load(String id) {//ĳ���� ��������
		loading(id);
		return inf.playerChk(id);
	}

	public void create(String id, String name, String gender) {//ĳ���� ����
		inf.createPlayer(id, name, gender);
	}

	public boolean login(String id, int pw) {//�α��� Ȯ��
		return inf.memberLogin(id, pw);
	}

	public startLogic loading(String id) {//�����޾ƿ���
		this.start = inf.startInfo(id);
		this.gender = start.getPlayer().getGender();
		return start;
	}

	public void saving(startLogic s) {//��������
		this.start = s;
		inf.update(start.getPlayer(), start.getInventory());
	}
}
