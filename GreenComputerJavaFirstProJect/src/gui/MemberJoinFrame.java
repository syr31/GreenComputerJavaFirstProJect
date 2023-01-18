package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class MemberJoinFrame extends JFrame implements ActionListener {

	// score GUI
	JFrame score_frame = new JFrame("[   회원정보   ]");
	JPanel score_pane = new JPanel();
	JLabel title_label = new JLabel("[ 회원정보등록 ]");
	JLabel code_label = new JLabel("등록번호");
	JLabel age_label = new JLabel("나 이");
	JLabel height_label = new JLabel(" 키 ");
	JLabel weight_label = new JLabel("몸 무 게");
	JTextField code_tf = new JTextField();
	JTextField name_tf = new JTextField();
	JTextField age_tf = new JTextField();
	JTextField height_tf = new JTextField();
	JTextField weight_tf = new JTextField();
	JButton reg_btn = new JButton("등록");
	JButton rst_btn = new JButton("삭제");
	JButton start_btn = new JButton("시작");
	JButton inquiry_btn = new JButton("조회");
	JTextArea table_ta = new JTextArea();
	JScrollPane scrollPane = new JScrollPane();

	public MemberJoinFrame() {
		score_gui();

	}

	public void score_gui() {
		score_frame.setVisible(true);
		score_frame.setResizable(false);
		score_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		score_frame.setBounds(300, 400, 500, 320);
		score_frame.add(score_pane);

		score_pane.setLayout(null);

		Font font = new Font(null, Font.BOLD, 13);
		title_label.setBounds(50, 20, 120, 25);
		title_label.setFont(font);
		score_pane.add(title_label);

		code_label.setBounds(20, 70, 70, 25);
		score_pane.add(code_label);

		age_label.setBounds(30, 110, 70, 25);
		score_pane.add(age_label);

		height_label.setBounds(35, 150, 70, 25);
		score_pane.add(height_label);

		weight_label.setBounds(20, 190, 70, 25);
		score_pane.add(weight_label);

		code_tf.setBounds(70, 70, 120, 25);
		score_pane.add(code_tf);

		name_tf.setBounds(70, 110, 120, 25);
		score_pane.add(name_tf);

		age_tf.setBounds(70, 150, 120, 25);
		score_pane.add(age_tf);

		height_tf.setBounds(70, 190, 120, 25);
		score_pane.add(height_tf);

		weight_tf.setBounds(70, 230, 120, 25);
		score_pane.add(weight_tf);

		reg_btn.setBounds(15, 220, 75, 25);
		score_pane.add(reg_btn);

		start_btn.setBounds(15, 251, 75, 25);
		score_pane.add(start_btn);

		rst_btn.setBounds(115, 251, 75, 25);
		score_pane.add(rst_btn);

		inquiry_btn.setBounds(115, 220, 75, 25);
		score_pane.add(inquiry_btn);

		// TextArea ����
		table_ta.setEditable(false);

		scrollPane.setBounds(210, 20, 270, 250);
		scrollPane.setViewportView(table_ta);
		score_pane.add(scrollPane);

		String header[] = { "[등록번호]", "[이  름]", "[나  이]", "[  키  ]", "[몸 무 게]" };
		String contents[][] = { { "20230103", "신oo", "50", "176", "80" } };

		DefaultTableModel model = new DefaultTableModel(contents, header);
		JTable table = new JTable(model);
		JScrollPane scrollpane = new JScrollPane(table);

		JTextField code_tf = new JTextField(20);
		JTextField name_tf = new JTextField(10);
		JTextField age_tf = new JTextField(10);
		JTextField height_tf = new JTextField(10);
		JTextField weight_tf = new JTextField(10);

		JPanel score_pane = new JPanel();
		score_pane.setLayout(new BoxLayout(score_pane, BoxLayout.X_AXIS));

		score_pane.add(code_tf);
		score_pane.add(name_tf);
		score_pane.add(age_tf);
		score_pane.add(height_tf);
		score_pane.add(weight_tf);

		JButton reg_btn = new JButton("등록");
		reg_btn.setBackground(Color.PINK);
		reg_btn.addActionListener(this);

		reg_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (reg_btn.equals(e.getSource())) {
					MemberRegistrationDAO dao = new MemberRegistrationDAO();
					dao.list(code_tf.getText(), name_tf.getText(), age_tf.getText(), height_tf.getText(),
							weight_tf.getText());
				}
				String inputStr[] = new String[5];

				inputStr[0] = code_tf.getText();
				inputStr[1] = name_tf.getText();
				inputStr[2] = age_tf.getText();
				inputStr[3] = height_tf.getText();
				inputStr[4] = weight_tf.getText();

				model.addRow(inputStr);

				code_tf.setText("");
				name_tf.setText("");
				age_tf.setText("");
				height_tf.setText("");
				weight_tf.setText("");
			}
		});

		JButton rst_btn = new JButton("삭제");
		rst_btn.setBackground(Color.PINK);
		
		JButton start_btn = new JButton("시작");
		start_btn.setBackground(Color.PINK);
		
		JButton inquiry_btn = new JButton("조회");
		inquiry_btn.setBackground(Color.PINK);

		rst_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRow() == -1) {

					return;
				} else {
					model.removeRow(table.getSelectedRow());
				}
			}
		});

		score_pane.add(reg_btn);
		score_pane.add(rst_btn);
		score_pane.add(inquiry_btn);
		score_pane.add(start_btn);

		score_frame.add(scrollpane);
		score_frame.add(score_pane, BorderLayout.SOUTH);
		score_frame.pack();
		score_frame.setVisible(true);

		start_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HealthAlarmFrame frame = new HealthAlarmFrame();
			}
		});
		inquiry_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InquiryFrame frame = new InquiryFrame();
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	}
}