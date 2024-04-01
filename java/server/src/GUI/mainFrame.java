package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;

import Server.ServerSetting;

public class mainFrame extends JFrame implements MouseListener, ActionListener {
	private ServerSetting main;
	private JScrollPane scrollPane;

	private JDialog frame = new JDialog();
	private JPanel player = new JPanel();
	private JTextArea serverInfo = new JTextArea();
	private JTextArea playerCnt = new JTextArea();
	private JTextArea playerInfo = new JTextArea();

	private JDialog logChk = new JDialog();
	private JPanel log = new JPanel();
	private JTextArea logPlayer = new JTextArea();
	private JTextArea logInfo = new JTextArea();

	private JButton logB = new JButton("클라이언트 로그보기");
	private JButton closeB = new JButton("닫　기");
	private JScrollPane playerS;
	private JScrollPane logS;

	public mainFrame(ServerSetting s, String a, int p) {
		super("온누리 THEWORLD 서버메인");
		main = s;
		JPanel background = new JPanel() {
			public void paintComponent(Graphics g) {
				g.drawImage(Toolkit.getDefaultToolkit().getImage("image/serverMain.png"), 0, -30, this);
				setOpaque(false);
				super.paintComponent(g);
			}
		};
		scrollPane = new JScrollPane(background);
		setContentPane(scrollPane);

		frame.setLayout(null);
		frame.setSize(700, 350);
		frame.setLocationRelativeTo(null);
		frame.setUndecorated(true);

		logChk.setLayout(null);
		logChk.setSize(500, 200);
		logChk.setLocationRelativeTo(null);
		logChk.setUndecorated(true);

		log.setLayout(null);
		log.setBorder(BorderFactory.createEtchedBorder());
		log.setBounds(0, 0, 500, 200);

		logPlayer.setBounds(0, 0, 500, 30);
		logPlayer.setBackground(Color.WHITE);
		logPlayer.setBorder(BorderFactory.createEtchedBorder());
		logPlayer.setFont(new Font("나눔고딕", 0, 15));
		logPlayer.setText("검색할 클라이언트의 아이디 입력");

		logPlayer.registerKeyboardAction(this, "SEND", KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0),
				JComponent.WHEN_FOCUSED);

		logS = new JScrollPane(logInfo, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		logS.setBounds(0, 28, 500, 150);
		logS.setBackground(Color.WHITE);
		logInfo.setFont(new Font("나눔고딕", 0, 15));
		logInfo.setEditable(false);

		closeB.setBounds(0, 177, 499, 22);
		closeB.setBackground(Color.WHITE);
		closeB.setFont(new Font("나눔고딕", 0, 15));
		closeB.setFocusPainted(false);

		player.setLayout(null);
		player.setBorder(BorderFactory.createEtchedBorder());
		player.setBounds(0, 0, 700, 350);

		serverInfo.setBounds(0, 0, 700, 30);
		serverInfo.setBackground(Color.WHITE);
		serverInfo.setBorder(BorderFactory.createEtchedBorder());
		serverInfo.setFont(new Font("나눔고딕", 0, 15));
		serverInfo.setEditable(false);
		serverInfo.setText("온누리 서버주소 : " + a + "     온누리 포트번호 : " + String.valueOf(p));

		playerCnt.setBounds(0, 29, 700, 30);
		playerCnt.setBackground(Color.WHITE);
		playerCnt.setBorder(BorderFactory.createEtchedBorder());
		playerCnt.setFont(new Font("나눔고딕", 0, 15));
		playerCnt.setEditable(false);
		playerCnt.setText("현재 접속자 수 : 0명");

		playerS = new JScrollPane(playerInfo, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		playerS.setBounds(0, 58, 700, 270);
		playerS.setBackground(Color.WHITE);
		playerInfo.setFont(new Font("나눔고딕", 0, 15));
		playerInfo.setEditable(false);

		logB.setBounds(0, 327, 699, 22);
		logB.setBackground(Color.WHITE);
		logB.setFont(new Font("나눔고딕", 0, 15));
		logB.setFocusPainted(false);

		player.add(serverInfo);
		player.add(playerCnt);
		player.add(playerS);
		player.add(logB);

		log.add(logPlayer);
		log.add(logS);
		log.add(closeB);

		logChk.add(log);
		frame.add(player);
		
		logPlayer.addMouseListener(this);
		logB.addMouseListener(this);
		closeB.addMouseListener(this);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("image/Icon.png"));
		this.setSize(800, 450);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(true);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				main.logUpdate();
				System.exit(0);
			}
		});
		frame.setVisible(true);
	}

	public void infoUpdate(int cnt, String msg) {
		playerCnt.setText("현재 접속자 수 : " + cnt + "명");
		playerInfo.append(msg + "\n");
		frame.requestFocus();
	}
	
	public void infoSearch(String msg) {
		logInfo.append(msg + "\n");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() == "SEND") {
			logInfo.removeAll();
			main.logSearch(logPlayer.getText());
			logPlayer.setText("");
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == logB) {
			logPlayer.removeAll();
			logChk.setVisible(true);
		} else if (e.getSource() == closeB) {
			logChk.setVisible(false);
		} else if (e.getSource() == logPlayer) {
			logPlayer.setText("");
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
