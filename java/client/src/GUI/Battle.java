package GUI;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;

import monsterDTO.superMonsterDTO;

public class Battle extends JFrame {
	private superMonsterDTO mst = null;
	private int x;
	private int y;
	private int backX;
	private int backY;

	private int pove = 40;
	private int moveX;
	private int moveY;

	private Image img;
	private Graphics imgG;

	private String map = null;
	private String gender = null;

	private Image back;
	private Image monAtk = Toolkit.getDefaultToolkit().getImage("image/monster/monsterMotion.png");
	private Image plaAtk = Toolkit.getDefaultToolkit().getImage("image/monster/monsterMotion.png");

	private Image pla;
	private Image monster;
	private Image change = null;

	private Battle battle = null;

	Battle(superMonsterDTO m, int row, int col, int bRow, int bCol, int wX, int wY, String g) {
		super("온누리 THEWORLD - " + m.getName() + " 전투 화면");
		battle = this;
		mst = m;
		gender = g;

		new Thread(new Runnable() {
			@Override
			public void run() {
				new BattleInfo(battle, wX + 502, wY);
			}
		}).start();

		if (gender.equals("남성")) {
			pla = Toolkit.getDefaultToolkit().getImage("image/player/playerMan.png");
		} else {
			pla = Toolkit.getDefaultToolkit().getImage("image/player/playerWoman.png");
		}

		this.x = row;
		this.y = col;
		this.backX = bRow;
		this.backY = bCol;

		switch (m.getMap_name()) {
		case "땅":
			back = Toolkit.getDefaultToolkit().getImage("image/battle/earthBattle.png");
			if (m.getLevel() < 50) {
				monster = Toolkit.getDefaultToolkit().getImage("image/monster/earth/monsterEarth.png");
				moveX = 370;
				moveY = 170;
			} else {
				monster = Toolkit.getDefaultToolkit().getImage("image/monster/earth/bossEarth.png");
				moveX = 300;
				moveY = 120;
			}
			map = "땅";
			break;
		case "물":
			back = Toolkit.getDefaultToolkit().getImage("image/battle/waterBattle.png");
			if (m.getLevel() < 50) {
				monster = Toolkit.getDefaultToolkit().getImage("image/monster/water/monsterWater.png");
				moveX = 370;
				moveY = 170;
			} else {
				monster = Toolkit.getDefaultToolkit().getImage("image/monster/water/bossWater.png");
				moveX = 290;
				moveY = 120;
			}
			map = "물";
			break;
		case "하늘":
			back = Toolkit.getDefaultToolkit().getImage("image/battle/skyBattle.png");
			if (m.getLevel() < 50) {
				monster = Toolkit.getDefaultToolkit().getImage("image/monster/sky/monsterSky.png");
				moveX = 370;
				moveY = 170;
			} else {
				monster = Toolkit.getDefaultToolkit().getImage("image/monster/sky/bossSky.png");
				moveX = 290;
				moveY = 130;

			}
			map = "하늘";
			break;
		}
//708 232 214 200
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("image/Icon.png"));
		this.setUndecorated(true);
		this.setLayout(null); // 레이아웃 매니저 사용 안함
		this.setBounds(wX, wY, 500, 450); // 창 크기 지정
		this.setResizable(false);
		this.setVisible(true);
	}

	public superMonsterDTO monsterInfo() {
		return mst;
	}

	public void atk() {
		pove = 350;
		repaint();
		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 6; i++) {
					try {
						Thread.sleep(100);
					} catch (Exception e) {
					}
					change = monster;
					monster = monAtk;
					monAtk = change;
					repaint();
					if (i == 5) {
						pove = 40;
						repaint();
					}
				}
			}
		}).start();
	}

	public void cnt() {
		moveX = 60;
		repaint();
		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 6; i++) {
					try {
						Thread.sleep(100);
					} catch (Exception e) {
					}
					change = pla;
					pla = plaAtk;
					plaAtk = change;
					repaint();
					if (i == 5) {
						if (mst.getLevel() < 50) {
							moveX = 370;
						}else {
							moveX = 270;
						}
						repaint();
					}
				}
			}
		}).start();
	}

	@Override
	public void paint(Graphics g) {
		img = createImage(500, 450);
		imgG = img.getGraphics();
		imgG.drawImage(back, 0, 0, this);
		imgG.drawImage(monster, moveX, moveY, this);
		imgG.drawImage(pla, pove, 200, this);
		g.drawImage(img, 0, 0, this);

	}

	@Override
	public void update(Graphics g) {
		paint(g);
	}

	public void run() {
		setVisible(false);
		new filedZone(x, y, backX, backY, map, gender);
	}

	public void end() {
		setVisible(false);
	}

}
