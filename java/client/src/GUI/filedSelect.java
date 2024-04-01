package GUI;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

public class filedSelect extends JFrame implements KeyListener {
	int x; // �÷��̾� ��ġ
	int y; // �÷��̾� ��ġ
	int backX; //��� ��ġ
	int backY; //��� ��ġ

	private Image img;//�÷��̾� �̹��� �Է�
	private Image pla;
	private Graphics imgG;// ���� ���۸��� ���� �׷��� ����
	
	private String gender = null;

	filedSelect(int row, int col, int bRow, int bCol, String g) {
		super("�´��� THEWORLD �� ������");
		gender = g;
		if(gender.equals("����")) {
			pla = Toolkit.getDefaultToolkit().getImage("image/player/playerMan.png");
		} else {
			pla = Toolkit.getDefaultToolkit().getImage("image/player/playerWoman.png");
		}
		
		this.x = row;
		this.y = col;
		this.backX =  bRow;
		this.backY = bCol;
		
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("image/Icon.png"));
		this.setLayout(null); // ���̾ƿ� �Ŵ��� ��� ����
		this.setSize(800, 450);
		this.setLocationRelativeTo(null);
		this.setUndecorated(true);
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
		imgG.drawImage(Toolkit.getDefaultToolkit().getImage("image/map/battleSelect.png"), backX, backY, this);
		imgG.drawImage(pla, x, y, this);
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
			if (y > 0)
				y = y - 10;
			if (y <= 0) {
				setVisible(false);
				new firstIn(gender, 380, 400, 0, -180);
			}
			if (backY < 0)
				backY = backY + 10;
			break;
		case KeyEvent.VK_S:
			if (y < 410)
				y = y + 10;
			if (y == 410 && x >= 300 && x <= 440) {
				setVisible(false);
				new filedZone(380, 30, -240, 5, "��", gender);
			}
			if (backY > -180)
				backY = backY - 10;
			break;
		case KeyEvent.VK_D:
			if (x < 770)
				x = x + 15;
			if (x == 770 && y >= 190 && y <= 360) {
				setVisible(false);
				new filedZone(380, 30, -240, 5, "�ϴ�", gender);
			}
			break;
		case KeyEvent.VK_A:
			if (x > -10)
				x = x - 15;
			if (x == -10 && y >= 190 && y <= 360) {
				setVisible(false);
				new filedZone(380, 30, -240, 5, "��", gender);
			}
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
