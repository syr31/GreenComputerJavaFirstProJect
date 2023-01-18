package gui;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class JoinFrame extends JFrame implements ActionListener {
	private JPanel contentPane;
	private JLabel lblJoin;
	private JButton joinCompleteBtn;
	private JTextField tfUsername;
	private JTextField tfPassword;
	private JTextField tfName;
	private JTextField tfSortation;
	private JTextField tfPhone;
	private JTextField tfGender;

	public void run() {
		try {
			JoinFrame frame = new JoinFrame();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public JoinFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(430, 490);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		contentPane.setLayout(null);

		lblJoin = new JLabel("회원가입");
		Font f1 = new Font("����", Font.BOLD, 20);
		lblJoin.setFont(f1);
		lblJoin.setBounds(159, 41, 101, 20);
		contentPane.add(lblJoin);

		JLabel lblUsername = new JLabel("password");
		lblUsername.setBounds(69, 163, 69, 20);
		contentPane.add(lblUsername);

		JLabel label = new JLabel("username");
		label.setBounds(69, 113, 69, 20);
		contentPane.add(label);

		JLabel lalName = new JLabel("이름");
		lalName.setBounds(69, 210, 69, 20);
		contentPane.add(lalName);

		JLabel lalGender = new JLabel("성별");
		lalGender.setBounds(69, 257, 69, 20);
		contentPane.add(lalGender);

		JLabel lalSortation = new JLabel("회원구분");
		lalSortation.setBounds(69, 304, 69, 20);
		contentPane.add(lalSortation);

		JLabel lalPhone = new JLabel("핸드폰 번호");
		lalPhone.setBounds(69, 354, 69, 20);
		contentPane.add(lalPhone);

		tfUsername = new JTextField();
		tfUsername.setColumns(10);
		tfUsername.setBounds(159, 106, 186, 35);
		contentPane.add(tfUsername);

		tfPassword = new JTextField();
		tfPassword.setColumns(10);
		tfPassword.setBounds(159, 156, 186, 35);
		contentPane.add(tfPassword);

		tfName = new JTextField();
		tfName.setColumns(10);
		tfName.setBounds(159, 203, 186, 35);
		contentPane.add(tfName);

		tfGender = new JTextField();
		tfGender.setColumns(10);
		tfGender.setBounds(159, 250, 186, 35);
		contentPane.add(tfGender);

		tfSortation = new JTextField();
		tfSortation.setColumns(10);
		tfSortation.setBounds(159, 297, 186, 35);
		contentPane.add(tfSortation);

		tfPhone = new JTextField();
		tfPhone.setColumns(10);
		tfPhone.setBounds(159, 347, 186, 35);
		contentPane.add(tfPhone);

		joinCompleteBtn = new JButton("회원가입 완료");
		joinCompleteBtn.setBounds(206, 400, 139, 29);
		joinCompleteBtn.setBackground(Color.PINK);;
		contentPane.add(joinCompleteBtn);

		joinCompleteBtn.addActionListener(this);

		setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (joinCompleteBtn.equals(e.getSource())) {
			MemberSignUpDAO dao = new MemberSignUpDAO();
			dao.list(tfUsername.getText(), tfPassword.getText(), tfName.getText(), tfGender.getText(),
					tfSortation.getText(), tfPhone.getText());
			ManagerLoginFrame frame = new ManagerLoginFrame();
			setVisible(false);
		}
	}
}