package GUI;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedInputStream;
import java.io.FileInputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import inGameLogic.battleLogic;
import inGameLogic.startLogic;
import monsterDTO.superMonsterDTO;

public class BattleInfo extends JFrame implements MouseListener {
	private superMonsterDTO mon = null;
	private battleLogic bat = null;
	private startLogic start = playerMenu.updateStart();
	private int cnt = 0;
	private Clip clip;

	private JButton atk = new JButton("������");
	private JButton run = new JButton("������");

	private JLabel mName = new JLabel();
	private JLabel mHp = new JLabel();
	private JLabel mAttack = new JLabel();
	private JLabel mDefence = new JLabel();

	private JLabel pName = new JLabel();
	private JLabel pHp = new JLabel();
	private JLabel pAttack = new JLabel();
	private JLabel pDefence = new JLabel();

	private JPanel player = new JPanel();
	private JPanel monster = new JPanel();

	private JDialog itemD = new JDialog();
	private JPanel itemP = new JPanel();
	private JLabel[] itemL = { new JLabel(), new JLabel(), new JLabel(), new JLabel(), new JLabel() };
	private JButton[] itemB = { new JButton("������"), new JButton("������") };

	private JDialog infoD = new JDialog();
	private JPanel infoP = new JPanel();
	private JLabel infoL = new JLabel();

	private Battle battle = null;

	BattleInfo(Battle load, int x, int y) {
		super("���� ����");
		battle = load;
		mon = battle.monsterInfo();
		bat = new battleLogic(start, mon);
		player.setLayout(new BoxLayout(player, BoxLayout.Y_AXIS));
		player.setBounds(0, 0, 284, 171);
		player.setBackground(Color.WHITE);
		player.setBorder(new LineBorder(Color.DARK_GRAY, 1));

		monster.setLayout(new BoxLayout(monster, BoxLayout.Y_AXIS));
		monster.setBounds(0, 170, 284, 171);
		monster.setBackground(Color.WHITE);
		monster.setBorder(new LineBorder(Color.DARK_GRAY, 1));

		atk.setBounds(0, 340, 142, 71);
		run.setBounds(141, 340, 143, 71);
		atk.setCursor(new Cursor(Cursor.HAND_CURSOR));
		run.setCursor(new Cursor(Cursor.HAND_CURSOR));
		atk.setBackground(Color.WHITE);
		run.setBackground(Color.WHITE);
		atk.setFocusPainted(false);
		run.setFocusPainted(false);

		atk.setFont(new Font("�������", 0, 20));
		run.setFont(new Font("�������", 0, 20));

		new Thread(new Runnable() {
			@Override
			public void run() {
				while (battle.isVisible()) {
					try {
						start.setPlayer(playerMenu.updatePlayer());
						Thread.sleep(100);
						pName.setText("�÷��̾�   " + start.getPlayer().getName() + "(������ "
								+ String.valueOf(start.getPlayer().getLevel()) + ")");
						pHp.setText("ü����   " + String.valueOf(start.getPlayer().getHp()));
						pAttack.setText("���ݷ�   " + String.valueOf(start.getPlayer().getAttack()));
						pDefence.setText("����   " + String.valueOf(start.getPlayer().getDefence()));
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();

		pName.setFont(new Font("�������", 0, 20));
		pHp.setFont(new Font("�������", 0, 20));
		pAttack.setFont(new Font("�������", 0, 20));
		pDefence.setFont(new Font("�������", 0, 20));

		player.add(pName);
		player.add(pHp);
		player.add(pAttack);
		player.add(pDefence);

		mName.setText("����   " + mon.getName() + "(������ " + String.valueOf(mon.getLevel()) + ")");
		mHp.setText("ü����   " + String.valueOf(mon.getHp()));
		mAttack.setText("���ݷ�   " + String.valueOf(mon.getAttack()));
		mDefence.setText("����   " + String.valueOf(mon.getDefence()));

		mName.setFont(new Font("�������", 0, 20));
		mHp.setFont(new Font("�������", 0, 20));
		mAttack.setFont(new Font("�������", 0, 20));
		mDefence.setFont(new Font("�������", 0, 20));

		monster.add(mName);
		monster.add(mHp);
		monster.add(mAttack);
		monster.add(mDefence);

		infoD.setLayout(null);
		infoD.setSize(151, 51);
		infoD.setLocationRelativeTo(null);
		infoD.setUndecorated(true);

		infoP.setLayout(null);
		infoP.setBackground(Color.WHITE);
		infoP.setBorder(BorderFactory.createEtchedBorder());
		infoP.setBounds(1, 1, 150, 50);

		infoL.setBounds(1, 0, 150, 50);
		infoL.setFont(new Font("�������", 0, 15));

		infoP.add(infoL);
		infoD.add(infoP);

		itemD.setLayout(null);
		itemD.setSize(201, 201);
		itemD.setLocationRelativeTo(null);
		itemD.setUndecorated(true);

		itemP.setLayout(null);
		itemP.setBackground(Color.WHITE);
		itemP.setBorder(BorderFactory.createEtchedBorder());
		itemP.setBounds(1, 1, 200, 200);

		itemD.add(itemP);

		for (int t = 0; t < itemL.length + 1; t++) {
			if (t < itemL.length) {
				itemL[t].setBounds(10, (t * 20) + 15, 150, 30);
				itemL[t].setFont(new Font("�������", 0, 15));
				itemP.add(itemL[t]);
			} else {
				itemB[0].setBounds(15, 155, 80, 30);
				itemB[0].setFont(new Font("�������", 0, 15));
				itemB[0].setBackground(Color.WHITE);
				itemB[0].setFocusPainted(false);
				itemP.add(itemB[0]);
				itemB[1].setBounds(105, 155, 80, 30);
				itemB[1].setFont(new Font("�������", 0, 15));
				itemB[1].setBackground(Color.WHITE);
				itemB[1].setFocusPainted(false);
				itemP.add(itemB[1]);
			}
		}

		this.add(player);
		this.add(monster);
		this.getContentPane().setBackground(Color.WHITE);

		this.add(atk);
		this.add(run);

		atk.addMouseListener(this);
		run.addMouseListener(this);
		itemB[0].addMouseListener(this);
		itemB[1].addMouseListener(this);

		this.setIconImage(Toolkit.getDefaultToolkit().getImage("image/Icon.png"));
		this.setUndecorated(true);
		this.setLayout(null); // ���̾ƿ� �Ŵ��� ��� ����
		this.setBounds(x, y, 285, 420); // â ũ�� ����
		this.setUndecorated(true);
		this.setResizable(false);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ���α׷� ���� ����
	}

	private void attack() {
		if (cnt == 2) {
			if (bat.counter()) {
				infoMsg("��� ����");
				sound("sound/attack.wav", false);
				battle.cnt();
				start.getPlayer().setHp(bat.playerHp());
				mon.setHp(bat.monsterHp());
				if (start.getPlayer().getHp() == 0) {
					infoMsg("ĳ���� ���");
				}
			} else {
				infoMsg("��� ����");
				start.getPlayer().setHp(bat.playerHp());
				mon.setHp(bat.monsterHp());
				if (mon.getHp() == 0) {
					start.getPlayer().setExp(start.getPlayer().getExp() + mon.exp());
					if (start.getPlayer().getExp() >= start.getPlayer().getMaxexp()) {
						start.getPlayer().setLevel(start.getPlayer().getLevel() + 1);
						start.getPlayer().setExp(start.getPlayer().getExp() - start.getPlayer().getMaxexp());
						start.getPlayer().setMaxexp();
						start.getPlayer().setPoint(start.getPlayer().getPoint() + 2);
					}
					itemInfo(bat.finish());
					itemD.setVisible(true);
				}
			}
			cnt = 0;
		} else {
			if (bat.battle()) {
				infoMsg("���� ����");
				sound("sound/attack.wav", false);
				battle.atk();
				cnt++;
				start.getPlayer().setHp(bat.playerHp());
				mon.setHp(bat.monsterHp());
				if (mon.getHp() == 0) {
					start.getPlayer().setExp(start.getPlayer().getExp() + mon.exp());
					if (start.getPlayer().getExp() >= start.getPlayer().getMaxexp()) {
						start.getPlayer().setLevel(start.getPlayer().getLevel() + 1);
						start.getPlayer().setExp(start.getPlayer().getExp() - start.getPlayer().getMaxexp());
						start.getPlayer().setMaxexp();
						start.getPlayer().setPoint(start.getPlayer().getPoint() + 2);
					}
					itemInfo(bat.finish());
					itemD.setVisible(true);
				}
			} else {
				cnt++;
				infoMsg("���� ����");
				start.getPlayer().setHp(bat.playerHp());
				mon.setHp(bat.monsterHp());
			}
			if (start.getPlayer().getHp() == 0) {
				infoMsg("ĳ���� ���");
			}
		}
//		pla.saving(start);
		pHp.setText("ü����   " + String.valueOf(start.getPlayer().getHp()));
		mHp.setText("ü����   " + String.valueOf(mon.getHp()));
	}

	public void sound(String file, boolean loop) {
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(new BufferedInputStream(new FileInputStream(file)));
			clip = AudioSystem.getClip();
			clip.open(ais);
			clip.start();
			if (loop)
				clip.loop(-1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == atk) {
			if(start.getPlayer().getHp() != 0 ) {
				attack();
			}
		} else if (e.getSource() == run) {
			setVisible(false);
			battle.run();
		} else if (e.getSource() == itemB[0]) {
			if (start.getInventory().getList().size() < 5) {
				start.getInventory().getList().add(itemL[2].getText().substring(6));
			} else {
				infoMsg("���� ĭ ����");
			}
//			pla.saving(start);
			itemD.setVisible(false);
			setVisible(false);
			battle.run();
		} else if (e.getSource() == itemB[1]) {
			itemD.setVisible(false);
			setVisible(false);
			battle.run();
		}
	}

	public void infoMsg(String msg) {
		infoL.setText(msg);
		infoD.setVisible(true);
		if (msg.equals("ĳ���� ���")) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						Thread.sleep(1000);
						infoL.setText("3�� �� �������� ��Ȱ");
						infoD.setVisible(true);
						Thread.sleep(1000);
						infoL.setText("2�� �� �������� ��Ȱ");
						infoD.setVisible(true);
						Thread.sleep(1000);
						infoL.setText("1�� �� �������� ��Ȱ");
						infoD.setVisible(true);
						Thread.sleep(1000);
						infoD.setVisible(false);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					start.getPlayer().setMaxhp();
					start.getPlayer().setHp(start.getPlayer().getMaxhp());
					setVisible(false);
					battle.end();
					new firstIn(start.getPlayer().getGender(), 380, 290, 0, -90);
				}
			}).start();
		} else {
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						Thread.sleep(1000);
						infoD.setVisible(false);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}).start();
		}
	}

	public void itemInfo(String name) {
		itemL[0].setText("���   ");
		itemL[1].setText("������   ");
		itemL[2].setText("�̡���   ");
		itemL[4].setText("����ġ   ");
		for (int i = 0; i < start.getItem().size(); i++) {
			if (start.getItem().get(i).getName().equals(name)) {
				itemL[0].setText(itemL[0].getText() + String.valueOf(start.getItem().get(i).getGrade()));
				itemL[1].setText(itemL[1].getText() + start.getItem().get(i).getPart());
				itemL[2].setText(itemL[2].getText() + start.getItem().get(i).getName());
				if (start.getItem().get(i).getPart().equals("����")) {
					itemL[3].setText("���ݷ�   ");
				} else if (start.getItem().get(i).getPart().equals("��")) {
					itemL[3].setText("����   ");
				} else {
					itemL[3].setText("ü����   ");
				}
				itemL[3].setText(itemL[3].getText() + String.valueOf(start.getItem().get(i).getStat()));
				itemL[4].setText(itemL[4].getText() + String.valueOf(start.getItem().get(i).getGold()));
				break;
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

}
