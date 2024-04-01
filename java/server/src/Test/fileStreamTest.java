package Test;

import ioDAT.input;

public class fileStreamTest {
	public static void main(String[] args) throws Exception {
//		String water = "C:/Users/leewr/OneDrive/바탕 화면/dat/waterMonster.dat";
//		String earth = "C:/Users/leewr/OneDrive/바탕 화면/dat/earthMonster.dat";
//		String sky = "C:/Users/leewr/OneDrive/바탕 화면/dat/skyMonster.dat";
//		// 2. 스트림 준비(보조)
////		FileOutputStream fos = new FileOutputStream(earth);
////		DataOutputStream dos = new DataOutputStream(fos);
////		ObjectOutputStream oos = new ObjectOutputStream(dos);
////		String[] waterName = { "최하급거북이", "하급거북이", "중급거북이", "상급거북이", "최상급거북이", "현무" };
////		String[] earthName = { "최하급호랑이", "하급호랑이", "중급호랑이", "상급호랑이", "최상급호랑이", "백호" };
////		String[] skyName = { "최하급새", "하급새", "중급새", "상급새", "최상급새", "주작" };
////		int[] level = { 1, 10, 20, 30, 40, 50 };
////		int[] hp = { 100, 500, 1000, 2500, 5000, 10000 };
////		int[] attack = { 10, 50, 100, 250, 500, 1000 };
////		int[] defence = { 5, 25, 50, 100, 250, 500 };
////		int col = 0;
////		int row = 0;
////		int bcol = 600;
////		int brow = 1150;
////		String[][] waterItem = { { "하급물약", "하급갈퀴", "하급껍질" }, { "중급물약", "중급갈퀴", "중급껍질" }, { "중급물약", "중급갈퀴", "중급껍질" },
////				{ "상급물약", "상급갈퀴", "상급껍질" }, { "상급물약", "상급갈퀴", "상급껍질" }, {"상급물약", "집행검", "집행갑옷"} };
////		String[][] earthItem = { { "하급물약", "하급발톱", "하급가죽" }, { "중급물약", "중급발톱", "중급가죽" }, { "중급물약", "중급발톱", "중급가죽" },
////				{ "상급물약", "상급발톱", "상급가죽" }, { "상급물약", "상급발톱", "상급가죽" }, {"상급물약", "집행검", "집행갑옷"} };
////		String[][] skyItem = { { "하급물약", "하급깃털", "하급날개" }, { "중급물약", "중급깃털", "중급날개" }, { "중급물약", "중급깃털", "중급날개" },
////				{ "상급물약", "상급깃털", "상급날개" }, { "상급물약", "상급깃털", "상급날개" }, {"상급물약", "집행검", "집행갑옷"} };
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
////		// 4. 비우기
////		oos.flush();
////		// 5. 자원 반납
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
//////        // 4. 출력하기
//////        Set<String> set = map.keySet();
//////        for(String key : set) {
//////            System.out.println(key + " : " + map.get(key));
//////        }
//////         
//////        for(int i = 0; i < scores.length; i++) { System.out.println(scores[i]); }
//////         
//////        System.out.println(msg);
////        
//		// 4. 해제
//		ois.close();
//		bis.close();
//		fis.close();
		
		input test = new input();
		test.monster("땅");
		test.monster("하늘");
		test.monster("물");
		test.item();
	}

}
