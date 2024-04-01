package GUI;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;

import inGameLogic.cooldinateLogic;
import monsterDTO.superMonsterDTO;

public class filedZone extends JFrame implements KeyListener {
	private int x;
	private int y;
	private int backX;
	private int backY;

	private Image img;
	private Image image;
	private Image filed;
	private Image pla;
	private Graphics imgG;

	private String map = null;
	private String gender = null;

	private cooldinateLogic cool = null;
	private ArrayList<superMonsterDTO> monsterInfo = null;

	filedZone(int row, int col, int bRow, int bCol, String map, String g) {
		super("온누리 THEWORLD - " + map + " 사냥터");
		this.image = Toolkit.getDefaultToolkit().getImage("image/loading/loadingFirst.png");
		this.map = map;
		this.gender = g;
		
		if (gender.equals("남성")) {
			pla = Toolkit.getDefaultToolkit().getImage("image/player/playerMan.png");
		} else {
			pla = Toolkit.getDefaultToolkit().getImage("image/player/playerWoman.png");
		}

		switch (map) {
		case "땅":
			filed = Toolkit.getDefaultToolkit().getImage("image/map/earth.png");
			break;
		case "물":
			filed = Toolkit.getDefaultToolkit().getImage("image/map/water.png");
			break;
		case "하늘":
			filed = Toolkit.getDefaultToolkit().getImage("image/map/sky.png");
			break;
		}

		this.x = row;
		this.y = col;
		this.backX = bRow;
		this.backY = bCol;

		new Thread(new Runnable() {
			@Override
			public void run() {
				while (cool == null) {
					try {
						Thread.sleep(500);
						image = Toolkit.getDefaultToolkit().getImage("image/loading/loadingFirst.png");
						repaint();
						Thread.sleep(500);
						image = Toolkit.getDefaultToolkit().getImage("image/loading/loadingSecond.png");
						repaint();
						Thread.sleep(500);
						image = Toolkit.getDefaultToolkit().getImage("image/loading/loadingThird.png");
						repaint();
					} catch (Exception e) {
					}
				}
			}
		}).start();

		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					cool = new cooldinateLogic(map);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				monsterInfo = cool.appoint();
			}
		}).start();
		
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("image/Icon.png"));
		this.setUndecorated(true);
		this.setLayout(null); // 레이아웃 매니저 사용 안함
		this.setSize(800, 450);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setResizable(false);
		this.addKeyListener(this);
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					try {
						Thread.sleep(10000);
						requestFocus();
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
			}
		}).start();
	}

	public void battle() {
		for (int i = 0; i < monsterInfo.size(); i++) {// 몬스터의 위치 찾기
			int monsterX = Math.abs(monsterInfo.get(i).getRowsize() - (x - backX));
			int monsterY = Math.abs(monsterInfo.get(i).getColsize() - (y - backY));
			if (monsterX < 30 && monsterY < 30) {
				setVisible(false);
				monsterInfo.get(i).setMap_name(map);
				new Battle(monsterInfo.get(i), x, y, backX, backY, this.getX() + 14, this.getY(), gender);
				monsterInfo.remove(i);
				break;
			}
		}
	}

	@Override
	public void paint(Graphics g) {
		img = createImage(1280, 1350);
		imgG = img.getGraphics();
		if (monsterInfo != null && cool != null) {
			imgG.drawImage(filed, backX, backY, this);
			imgG.drawImage(pla, x, y, this);
		} else {
			imgG.drawImage(image, 0, 0, this);
		}
		g.drawImage(img, 0, 0, this);
	}

	@Override
	public void update(Graphics g) {
		paint(g);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		imgG.clearRect(0, 0, getWidth(), getHeight());
		int key = e.getKeyCode();
		try {
			if (monsterInfo != null)
				switch (key) {
				case KeyEvent.VK_W:
					if (y > 10)
						y = y - 3;
					if (y <= 10) {
						setVisible(false);
						new filedSelect(380, 150, 0, -180, gender);
					}
					if (backY < 10)
						backY = backY + 12;
					break;
				case KeyEvent.VK_S:
					if (y < 410)
						y = y + 3;
					if (backY > -900)
						backY = backY - 12;
					break;
				case KeyEvent.VK_D:
					if (x < 765)
						x = x + 10;
					if (backX > -380)
						backX = backX - 8;
					break;
				case KeyEvent.VK_A:
					if (x > -15)
						x = x - 10;
					if (backX < 0)
						backX = backX + 8;
					break;
				}
			battle();
			repaint();
		} catch (Exception e2) {
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

}
