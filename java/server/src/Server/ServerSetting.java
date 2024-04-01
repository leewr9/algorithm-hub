package Server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import GUI.mainFrame;
import Logic.ServerLogic;
import Logic.playerLogic;
import Logic.startLogic;
import anyDTO.inventoryDTO;
import anyDTO.logDTO;
import anyDTO.playerDTO;

public class ServerSetting {
	private ServerSocket theWorld;
	private Socket inClient;
	private ClientConnect client;
	private HashMap<String, ClientConnect> clientList = new HashMap<>();
	private HashMap<String, logDTO> log = new HashMap<>();
	private logDTO visit = null;

	private ServerLogic server = ServerLogic.getInstance();
	private playerLogic player = playerLogic.getInstance();
	private startLogic start = null;

	private String localHostAddress;
	private String clientHostAddress;
	private int Port = 9790;
	private mainFrame main = null;

	ServerSetting() {
		try {
			localHostAddress = InetAddress.getLocalHost().getHostAddress();
			main = new mainFrame(this, localHostAddress, Port);
			System.out.println(localHostAddress);
			theWorld = new ServerSocket();
			theWorld.bind(new InetSocketAddress(localHostAddress, Port));
			connect();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void connect() {
		try {
			while (true) {
				inClient = theWorld.accept();
				client = new ClientConnect(this, inClient);
				visit = new logDTO();
				visit.set(client.getId(), client.getAdress());
				clientList.put(client.getId(), client);
				log.put(visit.getId(), visit);
				main.infoUpdate(clientList.size(),
						visit.getStart() + " " + visit.getId() + "님 접속(접속 IP " + visit.getAddress() + ")");
				logUpdate();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void msgAnalysis(String id, String msg) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				ClientConnect chk = clientList.get(id);
				String[] analysis = msg.split(" ");
				if (msg.indexOf("@login") >= 0) {
					chk.export("@login " + String.valueOf(player.login(analysis[1], Integer.valueOf(analysis[2]))) + " "
							+ String.valueOf(player.load(analysis[1])) + " " + player.gender());
				} else if (msg.indexOf("@join") >= 0) {
					chk.export("@join " + String.valueOf(player.join(analysis[1], Integer.valueOf(analysis[2]))));
				} else if (msg.indexOf("@drop") >= 0) {
					chk.export("@drop " + String.valueOf(player.drop(analysis[1], Integer.valueOf(analysis[2]))));
				} else if (msg.indexOf("@create") >= 0) {
					player.create(analysis[1], analysis[2], analysis[3]);
				} else if (msg.indexOf("@load") >= 0) {
					chk.export("@load " + startSet(analysis[1]));
				} else if (msg.indexOf("@save") >= 0) {
					startGet(msg);
				} else if (msg.indexOf("@exit") >= 0) {
					clientList.remove(id);
					log.get(id).setEnd();
					main.infoUpdate(clientList.size(), visit.getEnd() + " " + id + "님 접속해지");
					connect();
				} else if (msg.indexOf("@all") >= 0) {
					allClient(msg);
				}
			}
		}).start();
	}

	public String startSet(String id) {
		start = player.loading(id);
		String player = start.getPlayer().getName() + " " + start.getPlayer().getLevel() + " "
				+ start.getPlayer().getExp() + " " + start.getPlayer().getHp() + " " + start.getPlayer().getStrength()
				+ " " + start.getPlayer().getAgility() + " " + start.getPlayer().getPoint() + " "
				+ start.getPlayer().getGender();
		String inventory = " " + start.getInventory().getWeapon() + " " + start.getInventory().getArmor() + " "
				+ start.getInventory().getGold() + " ";
		String itemList = "";
		for (String i : start.getInventory().getList()) {
			itemList += i + " ";
		}
		return player + inventory + itemList;
	}

	public void startGet(String update) {
		ArrayList<String> list = new ArrayList<>();
		String[] analysis = update.split(" ");
		startLogic s = new startLogic();
		s.setPlayer(new playerDTO());
		s.setInventory(new inventoryDTO());
		s.getPlayer().setName(analysis[1]);
		s.getPlayer().setLevel(Integer.valueOf(analysis[2]));
		s.getPlayer().setExp(Integer.valueOf(analysis[3]));
		s.getPlayer().setMe(Integer.valueOf(analysis[4]));
		s.getPlayer().setHp(Integer.valueOf(analysis[5]));
		s.getPlayer().setMh(Integer.valueOf(analysis[6]));
		s.getPlayer().setAtk(Integer.valueOf(analysis[7]));
		s.getPlayer().setDef(Integer.valueOf(analysis[8]));
		s.getPlayer().setStrength(Integer.valueOf(analysis[9]));
		s.getPlayer().setAgility(Integer.valueOf(analysis[10]));
		s.getPlayer().setPoint(Integer.valueOf(analysis[11]));
		s.getPlayer().setGender(analysis[12]);
		s.getInventory().setName(analysis[1]);
		s.getInventory().setWeapon(analysis[13]);
		s.getInventory().setArmor(analysis[14]);
		s.getInventory().setGold(Integer.valueOf(analysis[15]));
		for (int i = 16; i < analysis.length; i++) {
			list.add(analysis[i]);
		}
		s.getInventory().setList(list);
		player.saving(s);
	}

	public void allClient(String msg) {
		for (String c : clientList.keySet()) {
			clientList.get(c).export(msg);
		}
	}

	public void logSearch(String id) {
		Vector<logDTO> player = server.findLog(id);
		for (int i = 0; i < player.size(); i++) {
			main.infoSearch("클라이언트 : " + player.get(i).getId() + "(접속 IP " + player.get(i).getAddress() + ")");
			main.infoSearch("[시작 " + player.get(i).getStart() + "     끝" + player.get(i).getEnd() + "]");
		}
	}

	public void logUpdate() {
		for (String i : log.keySet()) {
			server.logUpdate(log.get(i));
		}
	}

}
