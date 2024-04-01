package inGameLogic;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import ioDAT.input;
import monsterDTO.earthDTO;
import monsterDTO.skyDTO;
import monsterDTO.superMonsterDTO;
import monsterDTO.waterDTO;

public class cooldinateLogic {// �� ���� ��������ø��� ��ġ�ʱ�ȭ
	private ArrayList<superMonsterDTO> monsterList = null;
	private input getMonster = null;
	private String map = null;

	public cooldinateLogic(String name) throws ClassNotFoundException, IOException {
		map = name;
		getMonster = new input();
		monsterList = getMonster.monster(name);
		monsterC();
	}// �����������̽����� �����޾ƿ���

	public ArrayList<superMonsterDTO> appoint() {
		return monsterList;
	}// ��ǥ �����ѵ� ������

	public void monsterC() {
		ArrayList<superMonsterDTO> list = monsterList;// ���� �޾ƿ���
		ArrayList<superMonsterDTO> point = new ArrayList<>(26);// ���� �޾ƿ���
		int index = 0;
		int cnt = 0;
		superMonsterDTO monster = null;

		while (((list.size() - 1) * 5) + 1 > point.size()) {// ������ ������ 5���� ��ü����
			switch (map) {
			case "��":
				monster = new earthDTO();
				break;
			case "��":
				monster = new waterDTO();
				break;
			case "�ϴ�":
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

	public int row(int level) {// ������ ���� ������ x��ǥ
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

	public int col(int level) {// ������ ���� ������ y��ǥ
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
