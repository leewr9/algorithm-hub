package ioDAT;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import anyDTO.itemDTO;
import monsterDTO.superMonsterDTO;

public class input {
	public ArrayList<superMonsterDTO> monster(String map) throws IOException, ClassNotFoundException {
		String mon = null;
		switch (map) {
		case "¶¥":
			mon = "dat/earthMonster.dat";
			break;
		case "¹°":
			mon = "dat/waterMonster.dat";
			break;
		case "ÇÏ´Ã":
			mon = "dat/skyMonster.dat";
			break;
		}

		FileInputStream fis = new FileInputStream(mon);
		BufferedInputStream bis = new BufferedInputStream(fis);
		ObjectInputStream ois = new ObjectInputStream(bis);

		ArrayList<superMonsterDTO> list = new ArrayList<>();
		superMonsterDTO m = null;
		String msg = (String) ois.readUTF();
		System.out.println(msg);
		
		for (int i = 0; i < 6; i++) {
			m = (superMonsterDTO) ois.readObject();
			list.add(m);
		}

		ois.close();
		bis.close();
		fis.close();
		return list;
	}

	public ArrayList<itemDTO> item() throws IOException, ClassNotFoundException {
		String item = "dat/item.dat";

		FileInputStream fis = new FileInputStream(item);
		BufferedInputStream bis = new BufferedInputStream(fis);
		ObjectInputStream ois = new ObjectInputStream(bis);

		ArrayList<itemDTO> list = new ArrayList<>();
		itemDTO m = null;
		String msg = (String) ois.readUTF();
		System.out.println(msg);

		for (int i = 0; i < 25; i++) {
			m = (itemDTO) ois.readObject();
			list.add(m);
		}

		ois.close();
		bis.close();
		fis.close();
		return list;
	}

}
