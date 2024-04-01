package INF;

import java.util.ArrayList;
import java.util.Vector;

import Logic.startLogic;
import anyDTO.inventoryDTO;
import anyDTO.logDTO;
import anyDTO.playerDTO;
import monsterDTO.superMonsterDTO;

public interface allINF {
	public boolean memberLogin(String id, int pw);//�α��� 
	
	public boolean memberJoin(String id, int pw);// ȸ������

	public boolean memberDrop(String id, int pw);// ȸ��Ż��
	
	public boolean playerChk(String id);// ĳ���ͻ�������

	public void createPlayer(String id, String name, String gender);// ĳ���ͻ���

	public startLogic startInfo(String id);// ���� �ε�

	public ArrayList<superMonsterDTO> inGameInfo(String name);// ���� �ε�

	public void update(playerDTO p, inventoryDTO i);// ������������
	
	public void logUpdate(logDTO l);//�α� ������Ʈ
	
	public Vector<logDTO> playerLog(String id);//�α� ������Ʈ

}
