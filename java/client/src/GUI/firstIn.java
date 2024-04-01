package GUI;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

public class firstIn extends JFrame implements KeyListener {
	private int x; // 플레이어 위치
	private int y; // 플레이어 위치
	private int backX; // 배경 위치
	private int backY; // 배경 위치

	private Image img;// 플레이어 이미지 입력
	private Image image;
	private Image pla;
	private Graphics imgG;// 더블 버퍼링을 위한 그래픽 선언

	private String gender = null;

	firstIn(String g, int row, int col, int bRow, int bCol) {
		super("온누리 THEWORLD");
		gender = g;

		if (gender.equals("남성")) {
			pla = Toolkit.getDefaultToolkit().getImage("image/player/playerMan.png");
		} else {
			pla = Toolkit.getDefaultToolkit().getImage("image/player/playerWoman.png");
		}

		this.x = row;
		this.y = col;
		this.backX = bRow;
		this.backY = bCol;
		
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("image/Icon.png"));
		this.setUndecorated(true);
		this.setLayout(null); // 레이아웃 매니저 사용 안함
		this.setSize(800, 450);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(true);
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

	@Override
	public void paint(Graphics g) {
		img = createImage(800, 450);
		imgG = img.getGraphics();
		if (gender != null) {
			imgG.drawImage(Toolkit.getDefaultToolkit().getImage("image/map/firstIn.png"), backX, backY, this);
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
		switch (key) {
		case KeyEvent.VK_W:
			if (y > 220)
				y = y - 10;
			if (backY < -30)
				backY = backY + 12;
			break;
		case KeyEvent.VK_S:
			if (y < 410)
				y = y + 10;
			if (y >= 410) {
				setVisible(false);
				new filedSelect(380, 10, 0, 0, gender);
			}
			if (backY > -175)
				backY = backY - 12;
			break;
		case KeyEvent.VK_D:
			if (x < 765)
				x = x + 15;
			break;
		case KeyEvent.VK_A:
			if (x > -15)
				x = x - 15;
			break;
		}
		repaint();
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
