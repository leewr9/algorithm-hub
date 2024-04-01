package Server;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

import Logic.startLogic;

public class ClientConnect {
	private Socket client;
	private OutputStream out;
	private InputStream in;
	private ServerSetting server;
	private String id;
	private String clientHostAddress;

	ClientConnect(ServerSetting serv, Socket s) {
		client = s;
		server = serv;
		connect();
		receive();
	}

	public String getId() {
		return id;
	}

	public String getAdress() {
		return clientHostAddress;
	}

	public void connect() {
		try {
			String conn = null;
			String[] clientInfo;
			out = client.getOutputStream();
			in = client.getInputStream();

			byte[] reMsg = new byte[100];
			in.read(reMsg);

			conn = new String(reMsg);
			clientInfo = conn.split(" ");//0 아이디 1 아이피
			id = clientInfo[0];
			clientHostAddress = clientInfo[1].trim();
		} catch (IOException e) {
			e.printStackTrace();
		}
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
						Thread.sleep(1);
						String msg = null;
						byte[] reMsg = new byte[1000];
						in.read(reMsg);
						msg = new String(reMsg);
						msg = msg.trim();
						server.msgAnalysis(id, msg);
						if (msg.indexOf("@exit") >= 0) {
							disconnect();
							Thread.interrupted();
							break;
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

	public void disconnect() {
		try {
			if (in != null)
				in.close();
			if (out != null)
				out.close();
			if (client != null)
				client.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
