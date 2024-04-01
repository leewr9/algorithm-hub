package INF;

import java.util.ArrayList;
import java.util.Vector;

import Logic.startLogic;
import allDAO.inventoryDAO;
import allDAO.itemDAO;
import allDAO.logDAO;
import allDAO.membershipDAO;
import allDAO.monsterDAO;
import allDAO.playerDAO;
import anyDTO.inventoryDTO;
import anyDTO.logDTO;
import anyDTO.playerDTO;
import monsterDTO.superMonsterDTO;

public class allINFImpl implements allINF {
	private inventoryDAO inventory = null;
	private membershipDAO membership = null;
	private playerDAO player = null;
	private logDAO log = null;
	private itemDAO item = null;
	private monsterDAO monster = null;

	public allINFImpl() {
		inventory = inventoryDAO.getInstance();
		membership = membershipDAO.getInstance();
		player = playerDAO.getInstance();
		log = logDAO.getInstance();
//		item = itemDAO.getInstance();
//		monster = monsterDAO.getInstance();
		
	}

	@Override
	public boolean memberLogin(String id, int pw) {
		return membership.login(id, pw);
	}

	@Override
	public boolean memberJoin(String id, int pw) {
		if (membership.idChk(id)) {
			return false;
		} else {
			membership.join(id, pw);
			return true;
		}
	}

	@Override
	public boolean memberDrop(String id, int pw) {
		if (membership.login(id, pw)) {
			membership.drop(id, pw);
			return true;
		} else {
			return false;
		}
	}
	

	@Override
	public void createPlayer(String id, String name, String gender) {
		player.join(id, name, gender);
		inventory.join(name);
	}

	@Override
	public startLogic startInfo(String id) {
		startLogic start = new startLogic();
		start.setPlayer(player.loading(id));
		start.setInventory(inventory.loading(start.getPlayer().getName()));
//		start.setItem(item.loading());
		return start;
	}

	@Override
	public ArrayList<superMonsterDTO> inGameInfo(String name) {
		return monster.loading(name);
	}

	@Override
	public void update(playerDTO p, inventoryDTO i) {
		player.saving(p);
		inventory.saving(i);
	}

	@Override
	public boolean playerChk(String id) {
		if (player.login(id)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void logUpdate(logDTO l) {
		log.input(l);
	}

	@Override
	public Vector<logDTO> playerLog(String id) {
		Vector<logDTO> logs = null;
		if(id.equals("@all")) {
			logs = log.allfind();
		}else {
			logs = log.find(id);
		}
		return logs;
	}
}
