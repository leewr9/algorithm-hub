package Test;

import ioDAT.input;

public class fileStreamTest {
	public static void main(String[] args) throws Exception {
//		String water = "C:/Users/leewr/OneDrive/���� ȭ��/dat/waterMonster.dat";
//		String earth = "C:/Users/leewr/OneDrive/���� ȭ��/dat/earthMonster.dat";
//		String sky = "C:/Users/leewr/OneDrive/���� ȭ��/dat/skyMonster.dat";
//		// 2. ��Ʈ�� �غ�(����)
////		FileOutputStream fos = new FileOutputStream(earth);
////		DataOutputStream dos = new DataOutputStream(fos);
////		ObjectOutputStream oos = new ObjectOutputStream(dos);
////		String[] waterName = { "���ϱްź���", "�ϱްź���", "�߱ްź���", "��ްź���", "�ֻ�ްź���", "����" };
////		String[] earthName = { "���ϱ�ȣ����", "�ϱ�ȣ����", "�߱�ȣ����", "���ȣ����", "�ֻ��ȣ����", "��ȣ" };
////		String[] skyName = { "���ϱ޻�", "�ϱ޻�", "�߱޻�", "��޻�", "�ֻ�޻�", "����" };
////		int[] level = { 1, 10, 20, 30, 40, 50 };
////		int[] hp = { 100, 500, 1000, 2500, 5000, 10000 };
////		int[] attack = { 10, 50, 100, 250, 500, 1000 };
////		int[] defence = { 5, 25, 50, 100, 250, 500 };
////		int col = 0;
////		int row = 0;
////		int bcol = 600;
////		int brow = 1150;
////		String[][] waterItem = { { "�ϱ޹���", "�ϱް���", "�ϱ޲���" }, { "�߱޹���", "�߱ް���", "�߱޲���" }, { "�߱޹���", "�߱ް���", "�߱޲���" },
////				{ "��޹���", "��ް���", "��޲���" }, { "��޹���", "��ް���", "��޲���" }, {"��޹���", "�����", "���఩��"} };
////		String[][] earthItem = { { "�ϱ޹���", "�ϱ޹���", "�ϱް���" }, { "�߱޹���", "�߱޹���", "�߱ް���" }, { "�߱޹���", "�߱޹���", "�߱ް���" },
////				{ "��޹���", "��޹���", "��ް���" }, { "��޹���", "��޹���", "��ް���" }, {"��޹���", "�����", "���఩��"} };
////		String[][] skyItem = { { "�ϱ޹���", "�ϱޱ���", "�ϱ޳���" }, { "�߱޹���", "�߱ޱ���", "�߱޳���" }, { "�߱޹���", "�߱ޱ���", "�߱޳���" },
////				{ "��޹���", "��ޱ���", "��޳���" }, { "��޹���", "��ޱ���", "��޳���" }, {"��޹���", "�����", "���఩��"} };
////
////		ArrayList<superMonsterDTO> list = new ArrayList<>();
////		superMonsterDTO m = null;
////		for (int i = 0; i < 6; i++) {
////			m = new earthDTO();
////			m.setName(earthName[i]);
////			m.setLevel(level[i]);
////			m.setHp(hp[i]);
////			m.setAttack(attack[i]);
////			m.setDefence(defence[i]);
////			m.setColsize(col);
////			m.setRowsize(row);
////			m.setList(earthItem[i]);
////			list.add(m);
////		}
////	
////		oos.writeUTF("onnuri earthMonster ver 1");
////		for(superMonsterDTO i : list) {
////			oos.writeObject(i);
////		}
////		
////		
////		// 4. ����
////		oos.flush();
////		// 5. �ڿ� �ݳ�
////		oos.close();
////		dos.close();
////		fos.close();
//
//		FileInputStream fis = new FileInputStream(earth);
//		BufferedInputStream bis = new BufferedInputStream(fis);
//		ObjectInputStream ois = new ObjectInputStream(bis);
//		
//		ArrayList<superMonsterDTO> list = new ArrayList<>();
//		superMonsterDTO m = null;
//        String msg = (String)ois.readUTF();
//        System.out.println(msg);
//
//		for (int i = 0; i < 6; i++) {
//			m = (earthDTO) ois.readObject();
//			list.add(m);
//		}
//		System.out.println(list.size());
//////         
//////        // 4. ����ϱ�
//////        Set<String> set = map.keySet();
//////        for(String key : set) {
//////            System.out.println(key + " : " + map.get(key));
//////        }
//////         
//////        for(int i = 0; i < scores.length; i++) { System.out.println(scores[i]); }
//////         
//////        System.out.println(msg);
////        
//		// 4. ����
//		ois.close();
//		bis.close();
//		fis.close();
		
		input test = new input();
		test.monster("��");
		test.monster("�ϴ�");
		test.monster("��");
		test.item();
	}

}
