package ioDAT;

import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import anyDTO.itemDTO;
import monsterDTO.earthDTO;
import monsterDTO.skyDTO;
import monsterDTO.superMonsterDTO;
import monsterDTO.waterDTO;

public class output {

	public static void main(String[] args) throws IOException {
		monster("땅");
		monster("물");
		monster("하늘");
		item();
	}

	public static void monster(String map) throws IOException {
		String mon = null;
		String ver = null;
		switch (map) {
		case "땅":
			mon = "C:/Users/leewr/OneDrive/바탕 화면/dat/earthMonster.dat";
			break;
		case "물":
			mon = "C:/Users/leewr/OneDrive/바탕 화면/dat/waterMonster.dat";
			break;
		case "하늘":
			mon = "C:/Users/leewr/OneDrive/바탕 화면/dat/skyMonster.dat";
			break;
		}

		FileOutputStream fos = new FileOutputStream(mon);
		DataOutputStream dos = new DataOutputStream(fos);
		ObjectOutputStream oos = new ObjectOutputStream(dos);
		String[][] name = { { "최하급거북이", "하급거북이", "중급거북이", "상급거북이", "최상급거북이", "현무" },
				{ "최하급호랑이", "하급호랑이", "중급호랑이", "상급호랑이", "최상급호랑이", "백호" },
				{ "최하급새", "하급새", "중급새", "상급새", "최상급새", "주작" } };
		int[] level = { 1, 10, 20, 30, 40, 50 };
		int[] hp = { 100, 500, 1000, 2500, 5000, 10000 };
		int[] attack = { 10, 50, 100, 250, 500, 1000 };
		int[] defence = { 5, 25, 50, 100, 250, 500 };
		int col = 0;
		int row = 0;
		int bcol = 1150;
		int brow = 600;
		String[][] waterItem = { { "하급물약", "하급갈퀴", "하급껍질" }, { "중급물약", "중급갈퀴", "중급껍질" }, { "중급물약", "중급갈퀴", "중급껍질" },
				{ "상급물약", "상급갈퀴", "상급껍질" }, { "상급물약", "상급갈퀴", "상급껍질" }, { "상급물약", "집행검", "집행갑옷" } };
		String[][] earthItem = { { "하급물약", "하급발톱", "하급가죽" }, { "중급물약", "중급발톱", "중급가죽" }, { "중급물약", "중급발톱", "중급가죽" },
				{ "상급물약", "상급발톱", "상급가죽" }, { "상급물약", "상급발톱", "상급가죽" }, { "상급물약", "집행검", "집행갑옷" } };
		String[][] skyItem = { { "하급물약", "하급깃털", "하급날개" }, { "중급물약", "중급깃털", "중급날개" }, { "중급물약", "중급깃털", "중급날개" },
				{ "상급물약", "상급깃털", "상급날개" }, { "상급물약", "상급깃털", "상급날개" }, { "상급물약", "집행검", "집행갑옷" } };

		ArrayList<superMonsterDTO> list = new ArrayList<>();
		superMonsterDTO m = null;
		String[][] item = new String[6][3];
		int num = 0;
		for (int i = 0; i < 6; i++) {
			switch (map) {
			case "땅":
				m = new earthDTO();
				m.setMap_name("땅");
				num = 1;
				ver = "onnuri earthMonster ver 1";
				item = earthItem;
				break;
			case "물":
				m = new waterDTO();
				m.setMap_name("물");
				num = 0;
				ver = "onnuri waterMonster ver 1";
				item = waterItem;
				break;
			case "하늘":
				m = new skyDTO();
				m.setMap_name("하늘");
				num = 2;
				ver = "onnuri skyMonster ver 1";
				item = skyItem;
				break;
			}
			m.setName(name[num][i]);
			m.setLevel(level[i]);
			m.setHp(hp[i]);
			m.setAttack(attack[i]);
			m.setDefence(defence[i]);
			if (i == 5) {
				m.setColsize(bcol);
				m.setRowsize(brow);
			} else {
				m.setColsize(col);
				m.setRowsize(row);
			}
			m.setList(item[i]);
			list.add(m);

		}
		
		oos.writeUTF(ver);
		for (superMonsterDTO i : list) {
			System.out.println(i.getName());
			oos.writeObject(i);
		}
		oos.flush();
		oos.close();
		dos.close();
		fos.close();

	}

	public static void item() throws IOException {
		String item = "C:/Users/leewr/OneDrive/바탕 화면/dat/item.dat";

		FileOutputStream fos = new FileOutputStream(item);
		DataOutputStream dos = new DataOutputStream(fos);
		ObjectOutputStream oos = new ObjectOutputStream(dos);
		String[] weapon = { "나무검", "하급갈퀴", "하급발톱", "하급깃털", "중급갈퀴", "중급발톱", "중급깃털", "상급갈퀴", "상급발톱", "상급깃털", "집행검" };
		String[] armor = { "나무갑옷", "하급껍질", "하급가죽", "하급날개", "중급껍질", "중급가죽", "중급날개", "상급껍질", "상급가죽", "상급날개", "집행갑옷" };
		String[] potion = { "하급물약", "중급물약", "상급물약" };

		int[] itemlevel = { 1, 1, 1, 1, 15, 15, 15, 30, 30, 30, 50 };
		int[] itemgrade = { 1, 1, 1, 1, 2, 2, 2, 3, 3, 3, 5 };
		int[] itemstat = { 10, 15, 15, 15, 150, 150, 150, 1500, 1500, 1500, 5000 };
		int[] itemgold = { 1000, 1000, 1000, 1000, 5000, 5000, 5000, 10000, 10000, 10000, 99999 };

		int[] potionstat = { 10, 100, 1000 };
		int[] potiongrade = { 1, 2, 3 };
		int[] potiongold = { 100, 500, 1000 };
		ArrayList<itemDTO> list = new ArrayList<>();
		itemDTO t = null;
		for (int i = 0; i < 11; i++) {
			t = new itemDTO();
			t.setName(weapon[i]);
			t.setPart("무기");
			t.setGrade(itemgrade[i]);
			t.setLevel(itemlevel[i]);
			t.setStat(itemstat[i]);
			t.setGold(itemgold[i]);
			System.out.println(t.getName());
			list.add(t);
		}

		for (int i = 0; i < 11; i++) {
			t = new itemDTO();
			t.setName(armor[i]);
			t.setPart("방어구");
			t.setGrade(itemgrade[i]);
			t.setLevel(itemlevel[i]);
			t.setStat(itemstat[i]);
			t.setGold(itemgold[i]);
			list.add(t);
		}

		for (int i = 0; i < 3; i++) {
			t = new itemDTO();
			t.setName(potion[i]);
			t.setPart("잡화");
			t.setGrade(potiongrade[i]);
			t.setLevel(1);
			t.setStat(potionstat[i]);
			t.setGold(potiongold[i]);
			list.add(t);
		}

		oos.writeUTF("onnuri item ver 1");
		for (itemDTO i : list) {
			System.out.println(i.getName());
			oos.writeObject(i);
		}

		oos.flush();
		oos.close();
		dos.close();
		fos.close();
	}

}
