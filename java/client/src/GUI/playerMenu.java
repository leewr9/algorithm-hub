package GUI;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

import Client.ClientSetting;
import anyDTO.inventoryDTO;
import anyDTO.playerDTO;
import inGameLogic.startLogic;
import ioDAT.input;

public class playerMenu extends JFrame implements MouseListener, ActionListener {// ¸ðµç Á¤º¸¸¦ °¡Áö°í ÀÖ´Â GUI
	private static playerMenu allThis = null;
	private static startLogic start = null;
	private static String id;
	private ClientSetting client = null;
	private input getItem = null;
	private int inventoryI = 0;
	private int use = 0;

	private JButton playerInfo = new JButton("Á¤º¸º¸±â");
	private JButton inventory = new JButton("°¡¹æº¸±â");
	private JButton chating = new JButton("´ëÈ­ÇÏ±â");
	private JButton save = new JButton("ÀúÀåÇÏ±â");
	private JButton logOut = new JButton("³ª°¡±â");

	private JDialog infoD = new JDialog();
	private JDialog invenD = new JDialog();
	private JDialog chatD = new JDialog();
	private JDialog itemD = new JDialog();
	private JDialog martD = new JDialog();

	private JPanel infoP = new JPanel();
	private JPanel invenP = new JPanel();
	private JPanel itemP = new JPanel();
	private JPanel martP = new JPanel();
	private JTextArea chatP = new JTextArea();
	private JScrollPane chatS;

	private JButton statB = new JButton("Àû¡¡¿ë");
	private JButton martB = new JButton("»ó¡¡Á¡");

	private JLabel[] itemL = { new JLabel(), new JLabel(), new JLabel(), new JLabel(), new JLabel() };
	private JButton itemB = new JButton();
	private JButton buyB = new JButton("±¸¡¡¸Å");
	private JButton dropB = new JButton("ÆÇ¡¡¸Å");

	private JLabel[] infoL = { new JLabel("ÀÌ¡¡¸§"), new JLabel("·¹¡¡º§"), new JLabel("°æÇèÄ¡"), new JLabel("Ã¼¡¡·Â"),
			new JLabel("°ø°Ý·Â"), new JLabel("¹æ¾î·Â"), new JLabel("¡¡Èû¡¡"), new JLabel("¹Î¡¡Ã¸"), new JLabel("Æ÷ÀÎÆ®") };
	private JLabel[] infoLp = { new JLabel(), new JLabel(), new JLabel(), new JLabel(), new JLabel(), new JLabel(),
			new JLabel(), new JLabel(), new JLabel() };
	private JButton[] infoB = { new JButton("+"), new JButton("+"), new JButton("-"), new JButton("-") };

	private JLabel[] invenL = { new JLabel("ÀÌ¡¡¸§"), new JLabel("¹«¡¡±â"), new JLabel("¹æ¾î±¸"), new JLabel("°ñ¡¡µå"),
			new JLabel("¸ñ¡¡·Ï") };
	private JLabel[] invenLp = { new JLabel(), new JLabel(), new JLabel(), new JLabel(), new JLabel(), new JLabel(),
			new JLabel(), new JLabel(), new JLabel() };

	private JLabel[] martL = { new JLabel("ÇÏ±Þ¹°¾à"), new JLabel("Áß±Þ¹°¾à"), new JLabel("»ó±Þ¹°¾à") };
	private JLabel[] priceL = { new JLabel("100°ñµå"), new JLabel("500°ñµå"), new JLabel("1,000°ñµå") };

	private JTextField chatF = new JTextField();

	public playerMenu(ClientSetting c, String i, int x, int y) {
		allThis = this;
		client = c;
		id = i;
		load();

		playerInfo.setBounds(0, 0, 120, 84);
		inventory.setBounds(0, 83, 120, 84);
		chating.setBounds(0, 166, 120, 84);
		save.setBounds(0, 248, 120, 84);
		logOut.setBounds(0, 331, 120, 83);

		playerInfo.setCursor(new Cursor(Cursor.HAND_CURSOR));
		playerInfo.setBackground(Color.WHITE);
		playerInfo.setFocusPainted(false);
		playerInfo.setFont(new Font("³ª´®°íµñ", 0, 20));

		inventory.setCursor(new Cursor(Cursor.HAND_CURSOR));
		inventory.setBackground(Color.WHITE);
		inventory.setFocusPainted(false);
		inventory.setFont(new Font("³ª´®°íµñ", 0, 20));

		chating.setCursor(new Cursor(Cursor.HAND_CURSOR));
		chating.setBackground(Color.WHITE);
		chating.setFocusPainted(false);
		chating.setFont(new Font("³ª´®°íµñ", 0, 20));

		save.setCursor(new Cursor(Cursor.HAND_CURSOR));
		save.setBackground(Color.WHITE);
		save.setFocusPainted(false);
		save.setFont(new Font("³ª´®°íµñ", 0, 20));

		logOut.setCursor(new Cursor(Cursor.HAND_CURSOR));
		logOut.setBackground(Color.WHITE);
		logOut.setFocusPainted(false);
		logOut.setFont(new Font("³ª´®°íµñ", 0, 20));

		infoD.setLayout(null);
		infoD.setBounds(x + 120, y, 290, 370);
		infoD.setUndecorated(true);

		infoP.setLayout(null);
		infoP.setBackground(Color.WHITE);
		infoP.setBorder(BorderFactory.createEtchedBorder());
		infoP.setBounds(0, 0, 290, 370);

		infoB[0].setBounds(192, 220, 40, 30);
		infoB[0].setBackground(Color.WHITE);
		infoB[0].setFocusPainted(false);
		infoB[0].setFont(new Font("³ª´®°íµñ", 0, 9));
		infoB[2].setBounds(235, 220, 40, 30);
		infoB[2].setBackground(Color.WHITE);
		infoB[2].setFocusPainted(false);
		infoB[2].setFont(new Font("³ª´®°íµñ", 0, 11));

		infoB[1].setBounds(192, 255, 40, 30);
		infoB[1].setBackground(Color.WHITE);
		infoB[1].setFocusPainted(false);
		infoB[1].setFont(new Font("³ª´®°íµñ", 0, 9));
		infoB[3].setBounds(235, 255, 40, 30);
		infoB[3].setBackground(Color.WHITE);
		infoB[3].setFocusPainted(false);
		infoB[3].setFont(new Font("³ª´®°íµñ", 0, 11));

		int lY = 10;
		for (int l = 0; l < infoL.length; l++) {
			infoL[l].setBounds(20, lY, 50, 30);
			infoL[l].setFont(new Font("³ª´®°íµñ", 0, 15));
			infoLp[l].setBounds(130, lY, 100, 30);
			infoLp[l].setFont(new Font("³ª´®°íµñ", 0, 15));
			infoP.add(infoLp[l]);
			infoP.add(infoL[l]);
			lY += 35;
		}

		infoP.add(statB);

		invenD.setLayout(null);
		invenD.setBounds(x + 120, y, 290, 370);
		invenD.setUndecorated(true);

		invenP.setLayout(null);
		invenP.setBackground(Color.WHITE);
		invenP.setBorder(BorderFactory.createEtchedBorder());
		invenP.setBounds(0, 0, 290, 370);

		int vY = 10;
		int iL = 4;
		for (int v = 0; v < invenL.length; v++) {
			invenL[v].setBounds(20, vY, 50, 30);
			invenL[v].setFont(new Font("³ª´®°íµñ", 0, 15));
			invenLp[v].setBounds(130, vY, 100, 30);
			invenLp[v].setFont(new Font("³ª´®°íµñ", 0, 15));
			invenLp[v].addMouseListener(this);
			invenP.add(invenL[v]);
			invenP.add(invenLp[v]);
			vY += 35;
		}
		vY -= 35;

		for (int n = 0; n < 5; n++) {
			invenLp[iL].setBounds(130, vY, 100, 30);
			invenLp[iL].setFont(new Font("³ª´®°íµñ", 0, 15));
			invenLp[iL].addMouseListener(this);
			invenP.add(invenLp[iL]);
			vY += 35;
			iL++;
		}

		invenP.add(martB);

		chatD.setLayout(null);
		chatD.setBounds(x + 120, y, 290, 414);
		chatD.setUndecorated(true);

		chatF.setBounds(0, 368, 290, 46);
		chatF.setFont(new Font("³ª´®°íµñ", 0, 15));

		chatS = new JScrollPane(chatP, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		chatS.setBounds(0, 0, 290, 370);
		chatS.setBackground(Color.WHITE);
		chatP.setFont(new Font("³ª´®°íµñ", 0, 15));
		chatP.setEditable(false);

		itemD.setLayout(null);
		itemD.setBounds(x + 165, y + 85, 200, 200);
		itemD.setUndecorated(true);

		itemP.setLayout(null);
		itemP.setBackground(Color.WHITE);
		itemP.setBorder(BorderFactory.createEtchedBorder());
		itemP.setBounds(0, 0, 200, 200);

		for (int t = 0; t < itemL.length; t++) {
			itemL[t].setBounds(10, (t * 20) + 15, 150, 30);
			itemL[t].setFont(new Font("³ª´®°íµñ", 0, 15));
			itemP.add(itemL[t]);

		}
		itemB.setBounds(10, 125, 180, 30);
		itemB.setFont(new Font("³ª´®°íµñ", 0, 15));
		itemB.setBackground(Color.WHITE);
		itemB.setFocusPainted(false);

		dropB.setBounds(10, 160, 180, 30);
		dropB.setFont(new Font("³ª´®°íµñ", 0, 15));
		dropB.setBackground(Color.WHITE);
		dropB.setFocusPainted(false);

		buyB.setBounds(10, 160, 180, 30);
		buyB.setFont(new Font("³ª´®°íµñ", 0, 15));
		buyB.setBackground(Color.WHITE);
		buyB.setFocusPainted(false);

		martD.setLayout(null);
		martD.setBounds(x + 120, y + 368, 290, 200);
		martD.setUndecorated(true);

		martP.setLayout(null);
		martP.setBackground(Color.WHITE);
		martP.setBorder(BorderFactory.createEtchedBorder());
		martP.setBounds(0, 0, 290, 200);

		int mY = 10;
		for (int l = 0; l < martL.length; l++) {
			martL[l].setBounds(20, mY, 100, 30);
			martL[l].setFont(new Font("³ª´®°íµñ", 0, 15));
			martL[l].addMouseListener(this);
			priceL[l].setBounds(130, mY, 100, 30);
			priceL[l].setFont(new Font("³ª´®°íµñ", 0, 15));
			martP.add(martL[l]);
			martP.add(priceL[l]);
			mY += 35;
		}

		statB.setBounds(10, 330, 270, 30);
		statB.setFont(new Font("³ª´®°íµñ", 0, 15));
		statB.setBackground(Color.WHITE);
		statB.setFocusPainted(false);

		martB.setBounds(10, 330, 270, 30);
		martB.setFont(new Font("³ª´®°íµñ", 0, 15));
		martB.setBackground(Color.WHITE);
		martB.setFocusPainted(false);

		infoD.add(infoP);
		invenD.add(invenP);
		chatD.add(chatS);
		chatD.add(chatF);
		itemD.add(itemP);
		martD.add(martP);

		chatF.registerKeyboardAction(this, "SEND", KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0),
				JComponent.WHEN_FOCUSED);

		this.add(playerInfo);
		this.add(inventory);
		this.add(chating);
		this.add(save);
		this.add(logOut);

		playerInfo.addMouseListener(this);
		inventory.addMouseListener(this);
		chating.addMouseListener(this);
		save.addMouseListener(this);
		logOut.addMouseListener(this);
		infoB[0].addMouseListener(this);
		infoB[1].addMouseListener(this);
		infoB[2].addMouseListener(this);
		infoB[3].addMouseListener(this);
		itemD.addMouseListener(this);
		itemB.addMouseListener(this);
		dropB.addMouseListener(this);
		buyB.addMouseListener(this);
		statB.addMouseListener(this);
		martB.addMouseListener(this);

		this.setIconImage(Toolkit.getDefaultToolkit().getImage("image/Icon.png"));
		this.setLayout(null);
		this.setBounds(x, y, 120, 415); // Ã¢ Å©±â ÁöÁ¤
		this.setUndecorated(true);
		this.setResizable(false);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ÇÁ·Î±×·¥ ´ÝÈû ¼³Á¤
	}

	public static playerMenu allStatic() {
		return allThis;
	}

	public static startLogic updateStart() {
		return start;
	}

	public static playerDTO updatePlayer() {
		return start.getPlayer();
	}

	public void load() {
		String load = "@load " + id;
		client.export(load);
	}

	public void loadStart(String update) throws ClassNotFoundException, IOException {
		getItem = new input();

		ArrayList<String> list = new ArrayList<>();
		String[] analysis = update.split(" ");
		startLogic s = new startLogic();
		s.setPlayer(new playerDTO());
		s.setInventory(new inventoryDTO());
		s.getPlayer().setName(analysis[0]);
		s.getPlayer().setLevel(Integer.valueOf(analysis[1]));
		s.getPlayer().setExp(Integer.valueOf(analysis[2]));
		s.getPlayer().setHp(Integer.valueOf(analysis[3]));
		s.getPlayer().setStrength(Integer.valueOf(analysis[4]));
		s.getPlayer().setAgility(Integer.valueOf(analysis[5]));
		s.getPlayer().setPoint(Integer.valueOf(analysis[6]));
		s.getPlayer().setGender(analysis[7]);
		s.getInventory().setWeapon(analysis[8]);
		s.getInventory().setArmor(analysis[9]);
		s.getInventory().setGold(Integer.valueOf(analysis[10]));
		for (int i = 11; i < analysis.length; i++) {
			list.add(analysis[i]);
		}
		s.getInventory().setList(list);
		s.setItem(getItem.item());
		start = s;
		pointing();
		invenUpdate();
	}

	public String saveStart() {
		pointing();
		String player = start.getPlayer().getName() + " " + start.getPlayer().getLevel() + " "
				+ start.getPlayer().getExp() + " " + start.getPlayer().getMaxexp() + " " + start.getPlayer().getHp()
				+ " " + start.getPlayer().getMaxhp() + " " + start.getPlayer().getAttack() + " "
				+ start.getPlayer().getDefence() + " " + start.getPlayer().getStrength() + " "
				+ start.getPlayer().getAgility() + " " + start.getPlayer().getPoint() + " "
				+ start.getPlayer().getGender();
		String inventory = " " + start.getInventory().getWeapon() + " " + start.getInventory().getArmor() + " "
				+ start.getInventory().getGold() + " ";
		String itemList = "";
		for (String i : start.getInventory().getList()) {
			itemList += i + " ";
		}
		return player + inventory + itemList;
	}

	public void chatIn(String msg) {
		chatP.append(msg + "\n");
	}

	public void martMsg(String msg) {
		buyB.setText(msg);
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(500);
					buyB.setText("±¸¡¡¸Å");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

	public void pointing() {
		start.getPlayer().setMaxhp();
		start.getPlayer().setMaxexp();
		start.getPlayer().setAttack();
		start.getPlayer().setDefence();

		invenUpdate();
		infoLp[0].setText(start.getPlayer().getName());
		infoLp[1].setText(String.valueOf(start.getPlayer().getLevel()));
		infoLp[2].setText(String.valueOf(start.getPlayer().getExp() + "/" + start.getPlayer().getMaxexp()));
		infoLp[3].setText(String.valueOf(start.getPlayer().getHp() + "/" + start.getPlayer().getMaxhp()));
		infoLp[4].setText(String.valueOf(start.getPlayer().getAttack()));
		infoLp[5].setText(String.valueOf(start.getPlayer().getDefence()));
		infoLp[6].setText(String.valueOf(start.getPlayer().getStrength()));
		infoLp[7].setText(String.valueOf(start.getPlayer().getAgility()));
		infoLp[8].setText(String.valueOf(start.getPlayer().getPoint()));

	}

	public void infoPlayer() {
		pointing();
		if (start.getPlayer().getPoint() > 0) {
			infoP.add(infoB[0]);
			infoP.add(infoB[2]);
			infoP.add(infoB[1]);
			infoP.add(infoB[3]);
		} else {
			infoP.remove(infoB[0]);
			infoP.remove(infoB[2]);
			infoP.remove(infoB[1]);
			infoP.remove(infoB[3]);
		}
	}

	public void invenUpdate() {
		invenLp[0].setText(start.getPlayer().getName());
		invenLp[1].setText(start.getInventory().getWeapon());
		itemInfo(start.getInventory().getWeapon(), 0);
		start.getPlayer().setAtk(start.getPlayer().getAttack() + Integer.valueOf(itemL[3].getText().substring(6)));
		invenLp[2].setText(start.getInventory().getArmor());
		itemInfo(start.getInventory().getArmor(), 0);
		start.getPlayer().setDef(start.getPlayer().getDefence() + Integer.valueOf(itemL[3].getText().substring(6)));
		invenLp[3].setText(String.valueOf(start.getInventory().getGold()));
		int iL = 4;
		for (int n = 4; n < 9; n++) {
			invenLp[n].setText("");
		}
		for (int n = 0; n < start.getInventory().getList().size(); n++) {
			invenLp[iL].setText(start.getInventory().getList().get(n));
			iL++;
		}
	}

	public void itemInfo(String name, int in) {
		inventoryI = in;

		if (name.equals("")) {
			inventoryI = 0;
		}
		itemL[0].setText("µî¡¡±Þ   ");
		itemL[1].setText("Á¾¡¡·ù   ");
		itemL[2].setText("ÀÌ¡¡¸§   ");
		itemL[4].setText("°¡¡¡Ä¡   ");
		itemB.setText("Âø¡¡¿ë");
		for (int i = 0; i < start.getItem().size(); i++) {
			if (start.getItem().get(i).getName().equals(name)) {
				itemL[0].setText(itemL[0].getText() + String.valueOf(start.getItem().get(i).getGrade()));
				itemL[1].setText(itemL[1].getText() + start.getItem().get(i).getPart());
				itemL[2].setText(itemL[2].getText() + start.getItem().get(i).getName());
				if (start.getItem().get(i).getPart().equals("¹«±â")) {
					itemL[3].setText("°ø°Ý·Â   ");
				} else if (start.getItem().get(i).getPart().equals("¹æ¾î±¸")) {
					itemL[3].setText("¹æ¾î·Â   ");
				} else {
					itemL[3].setText("Ã¼¡¡·Â   ");
					itemB.setText("»ç¡¡¿ë");
				}
				itemL[3].setText(itemL[3].getText() + String.valueOf(start.getItem().get(i).getStat()));
				itemL[4].setText(itemL[4].getText() + String.valueOf(start.getItem().get(i).getGold()));
				use = i;
				break;
			}
		}
		if (in == 1 || in == 2) {
			itemP.remove(itemB);
			itemP.remove(dropB);
			itemP.remove(buyB);
		} else if (in == 9) {
			itemP.remove(itemB);
			itemP.remove(dropB);
			itemP.add(buyB);
		} else {
			itemP.add(itemB);
			itemP.add(dropB);
			itemP.remove(buyB);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == playerInfo) {
			infoPlayer();
			if (infoD.isVisible()) {
				infoD.setVisible(false);
			} else {
				infoD.setVisible(true);
			}
		} else if (e.getSource() == inventory) {
			pointing();
			if (invenD.isVisible()) {
				invenD.setVisible(false);
				martD.setVisible(false);
			} else {
				invenD.setVisible(true);
			}
		} else if (e.getSource() == chating) {
			if (chatD.isVisible()) {
				chatD.setVisible(false);
			} else {
				chatD.setVisible(true);
			}
		} else if (e.getSource() == save) {
			client.export("@save " + saveStart());
		} else if (e.getSource() == logOut) {
			client.setId(null);
			client.export("@save " + saveStart());
			client.export("@exit");
			System.exit(0);
		} else if (e.getSource() == infoB[0]) {
			if (start.getPlayer().getPoint() > 0 && start.getPlayer().getStrength() < 999) {
				start.getPlayer().setStrength(start.getPlayer().getStrength() + 1);
				start.getPlayer().setPoint(start.getPlayer().getPoint() - 1);
				infoLp[6].setText(String.valueOf(start.getPlayer().getStrength()));
				infoLp[8].setText(String.valueOf(start.getPlayer().getPoint()));
				pointing();
			}
		} else if (e.getSource() == infoB[2]) {
			if (start.getPlayer().getStrength() > 1) {
				start.getPlayer().setStrength(start.getPlayer().getStrength() - 1);
				start.getPlayer().setPoint(start.getPlayer().getPoint() + 1);
				infoLp[6].setText(String.valueOf(start.getPlayer().getStrength()));
				infoLp[8].setText(String.valueOf(start.getPlayer().getPoint()));
				pointing();
			}
		} else if (e.getSource() == infoB[1]) {
			if (start.getPlayer().getPoint() > 0 && start.getPlayer().getAgility() < 999) {
				start.getPlayer().setAgility(start.getPlayer().getAgility() + 1);
				start.getPlayer().setPoint(start.getPlayer().getPoint() - 1);
				infoLp[7].setText(String.valueOf(start.getPlayer().getAgility()));
				infoLp[8].setText(String.valueOf(start.getPlayer().getPoint()));
				pointing();
			}
		} else if (e.getSource() == infoB[3]) {
			if (start.getPlayer().getAgility() > 1) {
				start.getPlayer().setAgility(start.getPlayer().getAgility() - 1);
				start.getPlayer().setPoint(start.getPlayer().getPoint() + 1);
				infoLp[7].setText(String.valueOf(start.getPlayer().getAgility()));
				infoLp[8].setText(String.valueOf(start.getPlayer().getPoint()));
				pointing();
			}
		} else if (e.getSource() == invenLp[1] || e.getSource() == invenLp[2] || e.getSource() == invenLp[4]
				|| e.getSource() == invenLp[5] || e.getSource() == invenLp[6] || e.getSource() == invenLp[7]
				|| e.getSource() == invenLp[8] || e.getSource() == martL[0] || e.getSource() == martL[1]
				|| e.getSource() == martL[2]) {
			if (e.getSource() == invenLp[1])
				itemInfo(invenLp[1].getText(), 1);
			if (e.getSource() == invenLp[2])
				itemInfo(invenLp[2].getText(), 2);
			if (e.getSource() == invenLp[4])
				itemInfo(invenLp[4].getText(), 4);
			if (e.getSource() == invenLp[5])
				itemInfo(invenLp[5].getText(), 5);
			if (e.getSource() == invenLp[6])
				itemInfo(invenLp[6].getText(), 6);
			if (e.getSource() == invenLp[7])
				itemInfo(invenLp[7].getText(), 7);
			if (e.getSource() == invenLp[8])
				itemInfo(invenLp[8].getText(), 8);
			if (e.getSource() == martL[0])
				itemInfo(martL[0].getText(), 9);
			if (e.getSource() == martL[1])
				itemInfo(martL[1].getText(), 9);
			if (e.getSource() == martL[2])
				itemInfo(martL[2].getText(), 9);
			if (inventoryI != 0)
				itemD.setVisible(true);
		} else if (e.getSource() == itemD) {
			if (itemD.isVisible()) {
				itemD.setVisible(false);
			}
		} else if (e.getSource() == itemB) {
			if (itemL[1].getText().equals("Á¾¡¡·ù   ¹«±â")) {
				start.getInventory().setWeapon(itemL[2].getText().substring(6));
				start.getInventory().getList().remove(inventoryI - 4);
				start.getInventory().getList().add(invenLp[1].getText());
				start.getPlayer().setAttack();
				start.getPlayer().setAtk(start.getPlayer().getAttack() + start.getItem().get(use).getStat());
			} else if (itemL[1].getText().equals("Á¾¡¡·ù   ¹æ¾î±¸")) {
				start.getInventory().setArmor(itemL[2].getText().substring(6));
				start.getInventory().getList().remove(inventoryI - 4);
				start.getInventory().getList().add(invenLp[2].getText());
				start.getPlayer().setDefence();
				start.getPlayer().setDef(start.getPlayer().getDefence() + start.getItem().get(use).getStat());
			} else if (itemL[1].getText().equals("Á¾¡¡·ù   ÀâÈ­")) {
				start.getInventory().getList().remove(inventoryI - 4);
				start.getPlayer().setHp(start.getPlayer().getHp() + Integer.valueOf(itemL[3].getText().substring(6)));
				if (start.getPlayer().getHp() > start.getPlayer().getMaxhp()) {
					start.getPlayer().setHp(start.getPlayer().getMaxhp());
				}
			}
			pointing();
			itemD.setVisible(false);
		} else if (e.getSource() == dropB) {
			if (start.getInventory().getGold() + Integer.valueOf(itemL[4].getText().substring(6)) > 999999) {
				start.getInventory().setGold(999999);
			}else {
				start.getInventory().setGold(start.getInventory().getGold() + Integer.valueOf(itemL[4].getText().substring(6)));
			}
			start.getInventory().getList().remove(inventoryI - 4);
			pointing();
			itemD.setVisible(false);
		} else if (e.getSource() == statB) {
		} else if (e.getSource() == martB) {
			if (martD.isVisible()) {
				martD.setVisible(false);
			} else {
				martD.setVisible(true);
			}
		} else if (e.getSource() == buyB) {
			if (start.getInventory().getGold() >= Integer.valueOf(itemL[4].getText().substring(6))) {
				if (start.getInventory().getList().size() < 5) {
					start.getInventory()
							.setGold(start.getInventory().getGold() - Integer.valueOf(itemL[4].getText().substring(6)));
					start.getInventory().getList().add(itemL[2].getText().substring(6));
					pointing();
					itemD.setVisible(false);
				} else {
					martMsg("°¡¹æ Ä­ ºÎÁ·");
				}
			} else {
				martMsg("°ñµå ºÎÁ·");
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getActionCommand() == "SEND") {
			client.export("@all " + start.getPlayer().getName() + " : " + chatF.getText());
			chatF.setText("");
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}
}
