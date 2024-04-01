package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Client.ClientSetting;

public class createPlayer extends JFrame implements MouseListener {
	private JPanel back = new JPanel();
	private JButton man = new JButton(new ImageIcon("image/player/playerMan.png"));
	private JButton woman = new JButton(new ImageIcon("image/player/playerWoman.png"));
	private JButton create = new JButton("생　성");
	private JTextField name = new JTextField("캐릭터의 이름을 입력하시오");

	private JLabel manL = new JLabel("남성 캐릭터");
	private JLabel womanL = new JLabel("여성 캐릭터");

	private ClientSetting client = null;
	private String id = null;
	private String password = null;

	createPlayer(ClientSetting c, String i, String pw) {
		super("온누리 THEWORLD 캐릭터 생성 ");
		client = c;
		id = i;
		password = pw;

		man.setBounds(210, 60, 150, 150);
		man.setBackground(Color.WHITE);
		man.setFocusable(false);
		man.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
		manL.setBounds(252, 200, 150, 50);
		manL.setFont(new Font("나눔고딕", 0, 14));

		woman.setBounds(420, 60, 150, 150);
		woman.setBackground(Color.WHITE);
		woman.setFocusable(false);
		woman.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
		womanL.setBounds(458, 200, 150, 50);
		womanL.setFont(new Font("나눔고딕", 0, 14));

		name.setBounds(210, 260, 364, 50);
		name.setFont(new Font("나눔고딕", 0, 15));
		create.setBounds(210, 320, 364, 50);
		create.setBackground(Color.WHITE);
		create.setFocusable(false);
		create.setFont(new Font("나눔고딕", 0, 20));

		back.setLayout(null);
		back.setBounds(0, 0, 800, 450);
		back.setBackground(Color.WHITE);

		back.add(man);
		back.add(manL);
		back.add(woman);
		back.add(womanL);
		back.add(name);
		back.add(create);

		man.addMouseListener(this);
		woman.addMouseListener(this);
		name.addMouseListener(this);
		create.addMouseListener(this);
		this.add(back);
		
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("image/Icon.png"));
		this.setUndecorated(true);
		this.setSize(800, 450);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(true);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == man) {
			if (man.isSelected()) {
				man.setSelected(false);
				man.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));
			} else {
				if (woman.isSelected()) {
					woman.setSelected(false);
					woman.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));
				}
				man.setSelected(true);
				man.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
			}
		} else if (e.getSource() == woman) {
			if (woman.isSelected()) {
				woman.setSelected(false);
				woman.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));
			} else {
				if (man.isSelected()) {
					man.setSelected(false);
					man.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));
				}
				woman.setSelected(true);
				woman.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
			}
		} else if (e.getSource() == name) {
			name.setText("");
		} else if (e.getSource() == create) {
			if (man.isSelected()) {
				client.export("@create "+id+" "+name.getText()+" "+"남성");
				client.export("@login "+id+" "+password);
				setVisible(false);
				System.out.println(id);
			} else {
				client.export("@create "+id+" "+name.getText()+" "+"여성");
				client.export("@login "+id+" "+password);
				setVisible(false);
			}
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
