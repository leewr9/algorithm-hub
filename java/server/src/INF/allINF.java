package INF;

import java.util.ArrayList;
import java.util.Vector;

import Logic.startLogic;
import anyDTO.inventoryDTO;
import anyDTO.logDTO;
import anyDTO.playerDTO;
import monsterDTO.superMonsterDTO;

public interface allINF {
	public boolean memberLogin(String id, int pw);//로그인 
	
	public boolean memberJoin(String id, int pw);// 회원가입

	public boolean memberDrop(String id, int pw);// 회원탈퇴
	
	public boolean playerChk(String id);// 캐릭터생성여부

	public void createPlayer(String id, String name, String gender);// 캐릭터생성

	public startLogic startInfo(String id);// 시작 로딩

	public ArrayList<superMonsterDTO> inGameInfo(String name);// 게임 로딩

	public void update(playerDTO p, inventoryDTO i);// 유저정보갱신
	
	public void logUpdate(logDTO l);//로그 업데이트
	
	public Vector<logDTO> playerLog(String id);//로그 업데이트

}
