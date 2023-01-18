package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class ManagerLoginFrame extends JFrame implements ActionListener {
	private JPanel contentPane;
	private JTextField tfUsername, tfPassword;
	private JButton loginBtn, joinBtn, failedBtn, yesBtn, IdBtn;
	private MemberDAO dao;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManagerLoginFrame ManagerFrame = new ManagerLoginFrame();
					MemberLoginFrame MemberFrame = new MemberLoginFrame();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ManagerLoginFrame() {
		dao = new MemberDAO();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 300);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblLogin = new JLabel("username");
		lblLogin.setBounds(41, 52, 69, 35);
		contentPane.add(lblLogin);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(41, 103, 69, 35);
		contentPane.add(lblPassword);

		tfUsername = new JTextField();
		tfUsername.setBounds(157, 52, 176, 35);
		contentPane.add(tfUsername);
		tfUsername.setColumns(10);

		joinBtn = new JButton("회원가입");
		joinBtn.setBounds(229, 154, 104, 29);
		joinBtn.setBackground(Color.PINK);
		contentPane.add(joinBtn);

		loginBtn = new JButton("로그인");
		loginBtn.setBounds(108, 154, 106, 29);
		loginBtn.setBackground(Color.PINK);
		contentPane.add(loginBtn);

		IdBtn = new JButton("아이디 찾기");
		IdBtn.setBounds(140, 200, 160, 29);
		IdBtn.setBackground(Color.PINK);
		contentPane.add(IdBtn);

		tfPassword = new JTextField();
		tfPassword.setBounds(157, 103, 176, 35);
		contentPane.add(tfPassword);
		tfPassword.setColumns(10);

		setVisible(true);

		loginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand().equals("로그인")) {
					System.out.println("Click");
					ArrayList<MemberSignInVo> list = dao.list(tfUsername.getText(), tfPassword.getText());

					String username = "";
					String password = "";

					for (int i = 0; i < list.size(); i++) {
						MemberSignInVo data = (MemberSignInVo) list.get(i);
						username = data.getUsername();
						password = data.getPassword();

						System.out.println(username + " : " + password);
					}
					if (tfUsername.getText().equals(username) && tfPassword.getText().equals(password)) {
						System.out.println("로그인 성공");
						JOptionPane.showMessageDialog(null, "로그인 성공", "로그인 성공", JOptionPane.QUESTION_MESSAGE);
						setVisible(false);
						MemberJoinFrame frame = new MemberJoinFrame();
					} else {
						System.out.println("로그인 실패");
						JOptionPane.showMessageDialog(null, "아이디 또는 비밀번호를 잘 못 입력했습니다. 다시 확인해주세요", "로그인 실패",
								JOptionPane.QUESTION_MESSAGE);
					}
				}
			}
		});

		joinBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JoinFrame frame = new JoinFrame();
			}
		});

		IdBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FindingAMemberFrame MemberFrame = new FindingAMemberFrame();
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}
}