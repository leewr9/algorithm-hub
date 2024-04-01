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
		monster("��");
		monster("��");
		monster("�ϴ�");
		item();
	}

	public static void monster(String map) throws IOException {
		String mon = null;
		String ver = null;
		switch (map) {
		case "��":
			mon = "C:/Users/leewr/OneDrive/���� ȭ��/dat/earthMonster.dat";
			break;
		case "��":
			mon = "C:/Users/leewr/OneDrive/���� ȭ��/dat/waterMonster.dat";
			break;
		case "�ϴ�":
			mon = "C:/Users/leewr/OneDrive/���� ȭ��/dat/skyMonster.dat";
			break;
		}

		FileOutputStream fos = new FileOutputStream(mon);
		DataOutputStream dos = new DataOutputStream(fos);
		ObjectOutputStream oos = new ObjectOutputStream(dos);
		String[][] name = { { "���ϱްź���", "�ϱްź���", "�߱ްź���", "��ްź���", "�ֻ�ްź���", "����" },
				{ "���ϱ�ȣ����", "�ϱ�ȣ����", "�߱�ȣ����", "���ȣ����", "�ֻ��ȣ����", "��ȣ" },
				{ "���ϱ޻�", "�ϱ޻�", "�߱޻�", "��޻�", "�ֻ�޻�", "����" } };
		int[] level = { 1, 10, 20, 30, 40, 50 };
		int[] hp = { 100, 500, 1000, 2500, 5000, 10000 };
		int[] attack = { 10, 50, 100, 250, 500, 1000 };
		int[] defence = { 5, 25, 50, 100, 250, 500 };
		int col = 0;
		int row = 0;
		int bcol = 1150;
		int brow = 600;
		String[][] waterItem = { { "�ϱ޹���", "�ϱް���", "�ϱ޲���" }, { "�߱޹���", "�߱ް���", "�߱޲���" }, { "�߱޹���", "�߱ް���", "�߱޲���" },
				{ "��޹���", "��ް���", "��޲���" }, { "��޹���", "��ް���", "��޲���" }, { "��޹���", "�����", "���఩��" } };
		String[][] earthItem = { { "�ϱ޹���", "�ϱ޹���", "�ϱް���" }, { "�߱޹���", "�߱޹���", "�߱ް���" }, { "�߱޹���", "�߱޹���", "�߱ް���" },
				{ "��޹���", "��޹���", "��ް���" }, { "��޹���", "��޹���", "��ް���" }, { "��޹���", "�����", "���఩��" } };
		String[][] skyItem = { { "�ϱ޹���", "�ϱޱ���", "�ϱ޳���" }, { "�߱޹���", "�߱ޱ���", "�߱޳���" }, { "�߱޹���", "�߱ޱ���", "�߱޳���" },
				{ "��޹���", "��ޱ���", "��޳���" }, { "��޹���", "��ޱ���", "��޳���" }, { "��޹���", "�����", "���఩��" } };

		ArrayList<superMonsterDTO> list = new ArrayList<>();
		superMonsterDTO m = null;
		String[][] item = new String[6][3];
		int num = 0;
		for (int i = 0; i < 6; i++) {
			switch (map) {
			case "��":
				m = new earthDTO();
				m.setMap_name("��");
				num = 1;
				ver = "onnuri earthMonster ver 1";
				item = earthItem;
				break;
			case "��":
				m = new waterDTO();
				m.setMap_name("��");
				num = 0;
				ver = "onnuri waterMonster ver 1";
				item = waterItem;
				break;
			case "�ϴ�":
				m = new skyDTO();
				m.setMap_name("�ϴ�");
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
		String item = "C:/Users/leewr/OneDrive/���� ȭ��/dat/item.dat";

		FileOutputStream fos = new FileOutputStream(item);
		DataOutputStream dos = new DataOutputStream(fos);
		ObjectOutputStream oos = new ObjectOutputStream(dos);
		String[] weapon = { "������", "�ϱް���", "�ϱ޹���", "�ϱޱ���", "�߱ް���", "�߱޹���", "�߱ޱ���", "��ް���", "��޹���", "��ޱ���", "�����" };
		String[] armor = { "��������", "�ϱ޲���", "�ϱް���", "�ϱ޳���", "�߱޲���", "�߱ް���", "�߱޳���", "��޲���", "��ް���", "��޳���", "���఩��" };
		String[] potion = { "�ϱ޹���", "�߱޹���", "��޹���" };

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
			t.setPart("����");
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
			t.setPart("��");
			t.setGrade(itemgrade[i]);
			t.setLevel(itemlevel[i]);
			t.setStat(itemstat[i]);
			t.setGold(itemgold[i]);
			list.add(t);
		}

		for (int i = 0; i < 3; i++) {
			t = new itemDTO();
			t.setName(potion[i]);
			t.setPart("��ȭ");
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
