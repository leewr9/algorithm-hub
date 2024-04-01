package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedInputStream;
import java.io.FileInputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import Client.ClientSetting;

public class startIn extends JFrame implements MouseListener {
	private JScrollPane scrollPane;
	private JTextField id = new JTextField();
	private JPasswordField pw = new JPasswordField();
	private JButton login = new JButton("로그인");
	private JButton join = new JButton("회원가입");
	private JButton drop = new JButton("회원탈퇴");

	private JDialog infoD = new JDialog();
	private JPanel infoP = new JPanel();
	private JLabel[] infoL = { new JLabel("게임 플레이 주의사항"), new JLabel("이동키 상 W 하 S 좌 A 우 D"), new JLabel("조작키 마우스 이용"),
			new JLabel("조작시 이동이 안된다면"), new JLabel("한/영 입력 또는 메인화면 클릭 시 가능합니다"), new JLabel("서버 연결을 위해 버튼을 2회 입력바랍니다"), new JLabel("종료는 인게임 좌측하단의 나가기를 이용바랍니다"), new JLabel("클릭하면 창은 사라집니다") };

	private JPanel joinP = new JPanel();
	private JPanel dropP = new JPanel();
	private JDialog joinD = new JDialog();
	private JButton joinB = new JButton("회원가입");
	private JDialog dropD = new JDialog();
	private JButton dropB = new JButton("회원탈퇴");
	private JLabel joinLid = new JLabel("아이디");
	private JLabel dropLid = new JLabel("아이디");
	private JLabel joinLpw = new JLabel("비밀번호");
	private JLabel dropLpw = new JLabel("비밀번호");
	private JTextField joinId = new JTextField("아이디 입력");
	private JTextField joinPw = new JTextField("비밀번호 입력 [4자리 숫자]");
	private JTextField dropId = new JTextField("아이디 입력");
	private JTextField dropPw = new JTextField("비밀번호 입력");

	private ClientSetting client = null;
	private Clip clip;
	private boolean loginChk;// 로그인유무
	private boolean playerChk;// 캐릭터유무
	private boolean joinChk;// 회원가입
	private String gender;

	public startIn(ClientSetting c) {
		super("온누리 THEWORLD");
		this.client = c;
		JPanel background = new JPanel() {
			public void paintComponent(Graphics g) {
				g.drawImage(Toolkit.getDefaultToolkit().getImage("image/map/startIn.png"), 0, -30, this);
				setOpaque(false);
				super.paintComponent(g);
			}
		};

		scrollPane = new JScrollPane(background);
		setContentPane(scrollPane);

		id.setBounds(221, 93, 362, 50);
		pw.setBounds(221, 154, 362, 50);
		id.setFont(new Font("나눔고딕", 0, 20));
		pw.setFont(new Font("나눔고딕", 0, 20));

		login.setBounds(220, 215, 363, 50);
		login.setBackground(Color.WHITE);
		login.setFocusPainted(false);
		login.setFont(new Font("나눔고딕", 0, 20));

		join.setBounds(220, 269, 176, 30);
		drop.setBounds(407, 269, 176, 30);
		join.setFont(new Font("나눔고딕", 0, 15));
		drop.setFont(new Font("나눔고딕", 0, 15));
		join.setBackground(Color.WHITE);
		join.setFocusPainted(false);
		drop.setBackground(Color.WHITE);
		drop.setFocusPainted(false);

		joinD.setLayout(null);
		joinD.setSize(420, 290);
		joinD.setLocationRelativeTo(null);
		joinD.setUndecorated(true);
		dropD.setLayout(null);
		dropD.setSize(420, 290);
		dropD.setLocationRelativeTo(null);
		dropD.setUndecorated(true);

		infoD.setLayout(null);
		infoD.setSize(420, 290);
		infoD.setLocationRelativeTo(null);
		infoD.setUndecorated(true);

		infoP.setLayout(null);
		infoP.setBackground(Color.WHITE);
		infoP.setBorder(BorderFactory.createEtchedBorder());
		infoP.setBounds(0, 0, 420, 290);
		infoL[0].setBounds(24, 20, 400, 50);
		infoL[0].setFont(new Font("나눔고딕", 0, 25));
		infoP.add(infoL[0]);
		int fY = 50;
		for(int i = 1; i<infoL.length; i++) {
			infoL[i].setBounds(24, fY, 400, 50);
			infoL[i].setFont(new Font("나눔고딕", 0, 15));
			infoP.add(infoL[i]);
			fY += 30;
		}
		infoD.add(infoP);

		joinP.setLayout(null);
		joinP.setBackground(Color.WHITE);
		joinP.setBorder(BorderFactory.createEtchedBorder());
		joinP.setBounds(0, 0, 420, 290);

		joinLid.setBounds(24, 50, 100, 50);
		joinLid.setFont(new Font("나눔고딕", 0, 15));
		joinLpw.setBounds(24, 120, 100, 50);
		joinLpw.setFont(new Font("나눔고딕", 0, 15));

		joinId.setBounds(100, 50, 297, 50);
		joinId.setFont(new Font("나눔고딕", 0, 15));
		joinPw.setBounds(100, 120, 297, 50);
		joinPw.setFont(new Font("나눔고딕", 0, 15));

		joinB.setBounds(23, 220, 373, 50);
		joinB.setBackground(Color.WHITE);
		joinB.setFocusPainted(false);
		joinB.setFont(new Font("나눔고딕", 0, 20));

		joinP.add(joinLid);
		joinP.add(joinLpw);
		joinP.add(joinB);
		joinP.add(joinId);
		joinP.add(joinPw);
		joinD.add(joinP);

		dropP.setLayout(null);
		dropP.setBackground(Color.WHITE);
		dropP.setBorder(BorderFactory.createEtchedBorder());
		dropP.setBounds(0, 0, 420, 290);

		dropLid.setBounds(24, 50, 100, 50);
		dropLid.setFont(new Font("나눔고딕", 0, 15));
		dropLpw.setBounds(24, 120, 100, 50);
		dropLpw.setFont(new Font("나눔고딕", 0, 15));

		dropId.setBounds(100, 50, 297, 50);
		dropId.setFont(new Font("나눔고딕", 0, 15));
		dropPw.setBounds(100, 120, 297, 50);
		dropPw.setFont(new Font("나눔고딕", 0, 15));

		dropB.setBounds(23, 220, 373, 50);
		dropB.setBackground(Color.WHITE);
		dropB.setFocusPainted(false);
		dropB.setFont(new Font("나눔고딕", 0, 20));

		dropP.add(dropLid);
		dropP.add(dropLpw);
		dropP.add(dropB);
		dropP.add(dropId);
		dropP.add(dropPw);
		dropD.add(dropP);

		this.add(join);
		this.add(drop);
		this.add(id);
		this.add(pw);
		this.add(login);

		infoD.addMouseListener(this);
		login.addMouseListener(this);
		join.addMouseListener(this);
		drop.addMouseListener(this);
		id.addMouseListener(this);
		joinId.addMouseListener(this);
		joinPw.addMouseListener(this);
		joinB.addMouseListener(this);
		dropId.addMouseListener(this);
		dropPw.addMouseListener(this);
		dropB.addMouseListener(this);
		joinD.addMouseListener(this);
		dropD.addMouseListener(this);
		
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("image/Icon.png"));
		this.setSize(800, 450);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(true);
//		sound("sound/mainBgm.wav", true);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				System.exit(0);
			}
		});
		infoD.setVisible(true);
	}

//	public void sound(String file, boolean loop) {
//		try {
//			AudioInputStream ais = AudioSystem.getAudioInputStream(new BufferedInputStream(new FileInputStream(file)));
//			clip = AudioSystem.getClip();
//			clip.open(ais);
//			clip.start();
//			if (loop)
//				clip.loop(-1);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	public String loginId() {
		return id.getText();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == login) {
			String inPw = "";
			for (char i : pw.getPassword()) {
				inPw += i;
			}
			if (client.getId() == null) {
				client.connect(id.getText());
			} else {
				client.export("@login " + id.getText() + " " + inPw);
			}
		} else if (e.getSource() == join) {
			joinD.setVisible(true);
		} else if (e.getSource() == drop) {
			dropD.setVisible(true);
		} else if (e.getSource() == joinId) {
			joinId.setText("");
		} else if (e.getSource() == joinPw) {
			joinPw.setText("");
		} else if (e.getSource() == joinB) {
			if (client.getId() == null) {
				client.connect(joinId.getText());
			} else {
				client.export("@join " + joinId.getText() + " " + joinPw.getText());
			}
		} else if (e.getSource() == dropId) {
			dropId.setText("");
		} else if (e.getSource() == dropPw) {
			dropPw.setText("");
		} else if (e.getSource() == dropB) {
			if (client.getId() == null) {
				client.connect(dropId.getText());
			} else {
				client.export("@drop " + dropId.getText() + " " + dropPw.getText());
			}
		} else if (e.getSource() == id) {
			id.setText("");
		}else if(e.getSource() == infoD) {
			infoD.setVisible(false);
		}else if(e.getSource() == joinD) {
			joinD.setVisible(false);
		}else if(e.getSource() == dropD) {
			dropD.setVisible(false);
		}
	}

	public void msgAnalysis(String msg) {
		String[] analysis = msg.split(" ");
		if (msg.indexOf("@login") >= 0) {
			loginChk = Boolean.valueOf(analysis[1]);
			playerChk = Boolean.valueOf(analysis[2]);
			gender = analysis[3];
			if (loginChk) {
				if (playerChk) {
					setVisible(false);
					new firstIn(analysis[3], 380, 290, 0, -90);
				} else {
					setVisible(false);
					String inPw = "";
					for (char i : pw.getPassword()) {
						inPw += i;
					}
					new createPlayer(client, id.getText(), inPw);
				}
			} else {
				id.setText("아이디나 비밀번호가 틀렸습니다");
				pw.setText("");
				client.setId(null);
				client.export("@exit");
			}
		} else if (msg.indexOf("@join") >= 0) {
			joinChk = Boolean.valueOf(analysis[1]);
			if (joinChk) {
				joinD.setVisible(false);
				client.setId(null);
				client.export("@exit");
			} else {
				joinId.setText("아이디가 중복되었습니다");
				joinPw.setText("");
				client.setId(null);
				client.export("@exit");
			}
		} else if (msg.indexOf("@drop") >= 0) {
			joinChk = Boolean.valueOf(analysis[1]);
			if (joinChk) {
				dropD.setVisible(false);
				client.setId(null);
				client.export("@exit");
			} else {
				dropId.setText("아이디나 비밀번호가 틀렸습니다");
				dropPw.setText("");
				client.setId(null);
				client.export("@exit");
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
