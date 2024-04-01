package inGameLogic;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import ioDAT.input;
import monsterDTO.earthDTO;
import monsterDTO.skyDTO;
import monsterDTO.superMonsterDTO;
import monsterDTO.waterDTO;

public class cooldinateLogic {// 맵 입장 전투종료시마다 위치초기화
	private ArrayList<superMonsterDTO> monsterList = null;
	private input getMonster = null;
	private String map = null;

	public cooldinateLogic(String name) throws ClassNotFoundException, IOException {
		map = name;
		getMonster = new input();
		monsterList = getMonster.monster(name);
		monsterC();
	}// 유저인터페이스에서 정보받아오기

	public ArrayList<superMonsterDTO> appoint() {
		return monsterList;
	}// 좌표 지정한뒤 보내기

	public void monsterC() {
		ArrayList<superMonsterDTO> list = monsterList;// 정보 받아오기
		ArrayList<superMonsterDTO> point = new ArrayList<>(26);// 정보 받아오기
		int index = 0;
		int cnt = 0;
		superMonsterDTO monster = null;

		while (((list.size() - 1) * 5) + 1 > point.size()) {// 보스를 제외한 5개의 객체생성
			switch (map) {
			case "땅":
				monster = new earthDTO();
				break;
			case "물":
				monster = new waterDTO();
				break;
			case "하늘":
				monster = new skyDTO();
				break;
			}

			monster.setName(list.get(index).getName());
			monster.setLevel(list.get(index).getLevel());
			monster.setHp(list.get(index).getHp());
			monster.setAttack(list.get(index).getAttack());
			monster.setDefence(list.get(index).getDefence());
			monster.setMap_name(list.get(index).getMap_name());
			monster.setList(list.get(index).getList());
			if (monster.getLevel() < 50) {
				monster.setColsize(col(monster.getLevel()));
				monster.setRowsize(row(monster.getLevel()));
			} else {
				monster.setColsize(list.get(index).getColsize());
				monster.setRowsize(list.get(index).getRowsize());
			}
			point.add(monster);
			cnt++;
			if (list.get(index).getLevel() >= 50 || cnt == 5) {
				index++;
				cnt = 0;
			}
		}

		monsterList = point;
	}

	public int row(int level) {// 레벨에 따른 서식지 x좌표
		Random r = new Random();
		int x = 0;

		switch (level) {
		case 1:
			x = r.nextInt(1260) + 10;
			break;
		case 10:
			x = r.nextInt(1260) + 10;
			break;
		case 20:
			x = r.nextInt(1260) + 10;
			break;
		case 30:
			x = r.nextInt(550) + 10;
			break;
		case 40:
			x = r.nextInt(550) + 720;
			break;
		}

		return x;
	}

	public int col(int level) {// 레벨에 따른 서식지 y좌표
		Random r = new Random();
		int y = 0;

		switch (level) {
		case 1:
			y = r.nextInt(250) + 70;
			break;
		case 10:
			y = r.nextInt(250) + 350;
			break;
		case 20:
			y = r.nextInt(250) + 630;
			break;
		case 30:
			y = r.nextInt(430) + 910;
			break;
		case 40:
			y = r.nextInt(430) + 910;
			break;
		}

		return y;
	}
}
