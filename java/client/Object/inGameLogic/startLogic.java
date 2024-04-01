package inGameLogic;

import java.util.ArrayList;

import anyDTO.inventoryDTO;
import anyDTO.itemDTO;
import anyDTO.playerDTO;

public class startLogic {// 게임 시작시 로딩 클래스
	private playerDTO player;
	private inventoryDTO inventory;
	private ArrayList<itemDTO> item;

	public playerDTO getPlayer() {
		return player;
	}

	public void setPlayer(playerDTO player) {
		this.player = player;
	}

	public inventoryDTO getInventory() {
		return inventory;
	}

	public void setInventory(inventoryDTO inventory) {
		this.inventory = inventory;
	}

	public ArrayList<itemDTO> getItem() {
		return item;
	}

	public void setItem(ArrayList<itemDTO> item) {
		this.item = item;
	}

}
