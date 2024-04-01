package Client;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

import GUI.playerMenu;
import GUI.startIn;

public class ClientSetting {
	private Socket client;
	private OutputStream out;
	private InputStream in;
	private startIn startin = null;
	private playerMenu inGame = null;
	private String id = null;
	private String clientHostAddress;

	public ClientSetting() {
		try {
			clientHostAddress = InetAddress.getLocalHost().getHostAddress();
			startin = new startIn(this);
		} catch (Exception e) {
		}

	}

	public void connect(String i) {
		try {
			i += " " + clientHostAddress;// 아이디와 아이피 주소보내기
			client = new Socket("172.30.1.44", 9790);
			out = client.getOutputStream();
			out.write(i.getBytes());
			in = client.getInputStream();
			this.id = i;
			receive();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return this.id;
	}

	public void export(String msg) {// 보내기
		try {
			out.write(msg.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void receive() {// 받기
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					while (true) {
						String msg = null;
						byte[] reMsg = new byte[1000];
						in.read(reMsg);

						msg = new String(reMsg);
						msg = msg.trim();

						msgAnalysis(msg);
					}
				} catch (IOException e) {
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

	public void msgAnalysis(String msg) throws ClassNotFoundException, IOException {
		String[] analysis = msg.split(" ");
		if (msg.indexOf("@login") >= 0) {
			if (analysis[1].equals("true") && analysis[2].equals("true")) {
				inGame = new playerMenu(this, id, startin.getX() + 802, startin.getY());
			}
			startin.msgAnalysis(msg);
		} else if (msg.indexOf("@load") >= 0) {
			String load = "";
			for (int i = 1; i < analysis.length; i++) {
				load += analysis[i] + " ";
			}
			inGame.loadStart(load);
		} else if (msg.indexOf("@all") >= 0) {
			String chat = "";
			for (int i = 1; i < analysis.length; i++) {
				chat += analysis[i] + " ";
			}
			inGame.chatIn(chat);
		} else {
			startin.msgAnalysis(msg);
		}
	}

}
