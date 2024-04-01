package Logic;

import INF.allINFImpl;

public class playerLogic {
	private allINFImpl inf = new allINFImpl();
	private startLogic start = null;// 객체 생성 후에 입력
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

	public boolean join(String id, int pw) {//회원가입
		return inf.memberJoin(id, pw);
	}

	public boolean drop(String id, int pw) {//회원탈퇴
		return inf.memberDrop(id, pw);
	}

	public boolean load(String id) {//캐릭터 유무여부
		loading(id);
		return inf.playerChk(id);
	}

	public void create(String id, String name, String gender) {//캐릭터 생성
		inf.createPlayer(id, name, gender);
	}

	public boolean login(String id, int pw) {//로그인 확인
		return inf.memberLogin(id, pw);
	}

	public startLogic loading(String id) {//정보받아오기
		this.start = inf.startInfo(id);
		this.gender = start.getPlayer().getGender();
		return start;
	}

	public void saving(startLogic s) {//정보저장
		this.start = s;
		inf.update(start.getPlayer(), start.getInventory());
	}
}
