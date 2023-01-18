package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.table.DefaultTableModel;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

public class HealthAlarmFrame {
	private JSpinner pickTimeSpinner;
	private Date timeValue;
	private JButton setAlarmOnButton;
	private JButton setAlarmOffButton;
	private SimpleDateFormat timeFormat;
	private Boolean isLive;
	private Thread mainThread;
	private JLabel lAlarmStat;
	private JButton inquiryBtn;

	Connection con = null;
	Statement st;
	ResultSet rs;
	PreparedStatement pst = null;

	int n = 1;

	String driver = "oracle.jdbc.driver.OracleDriver";
	String URL = "jdbc:oracle:thin:@localhost:1521:XE";
	String user = "c##green1234";
	String password = "green1234";

	public HealthAlarmFrame() {

		JFrame mainAppFrame = new JFrame("AlarmApp");
		mainAppFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel mainAppPanel = new JPanel();
		mainAppPanel.setLayout(new BoxLayout(mainAppPanel, BoxLayout.X_AXIS));

		String header[] = { "[종목]", "[무게]", "[SET]", "[횟수]" };
		String contents[][] = { { "벤치프레스", "kg", "SET", "횟수" } };

		DefaultTableModel model2 = new DefaultTableModel(contents, header);
		JTable table = new JTable(model2);
		JScrollPane scrollpane = new JScrollPane(table);

		UtilDateModel model = new UtilDateModel();
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		datePicker.setBounds(220, 350, 120, 50);

		JTextField sbj1 = new JTextField(10);// 무게
		JTextField sbj2 = new JTextField(10);// set
		JTextField sbj3 = new JTextField(10);// 회수
		JTextField sbj4 = new JTextField(10);// pk

		mainAppPanel.add(datePicker);
		mainAppPanel.add(sbj1);
		mainAppPanel.add(sbj2);
		mainAppPanel.add(sbj3);
		mainAppPanel.add(sbj4);

		JButton addBtn = new JButton("추가");
		addBtn.setBackground(Color.pink);

		addBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<HealthRecordVO> list = new ArrayList<HealthRecordVO>();
				HealthRecordDAO dao = new HealthRecordDAO();

				Date datee = (Date) datePicker.getModel().getValue();
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				String reportDate = df.format(datee);

				if (datee != null) {

					String inputStr[] = new String[6];

					inputStr[0] = datee.toLocaleString();
					inputStr[1] = sbj1.getText();
					inputStr[2] = sbj2.getText();
					inputStr[3] = sbj3.getText();
					inputStr[4] = sbj4.getText();
					inputStr[5] = n + "";

					dao.list(datee.toLocaleString(), sbj1.getText(), sbj2.getText(), sbj3.getText(), sbj4.getText(),
							n + "");
					model2.addRow(inputStr);
					sbj1.setText("");
					sbj2.setText("");
					sbj3.setText("");
					sbj4.setText("");
				}
				n++;
			}

		});

		JButton cancelBtn = new JButton("삭제");
		cancelBtn.setBackground(Color.pink);

		cancelBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (table.getSelectedRow() == -1) {
					return;
				} else {
					model2.removeRow(table.getSelectedRow());
				}

			}
		});

		JButton inquiryBtn = new JButton("조회");
		inquiryBtn.setBackground(Color.pink);

		inquiryBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HealthRecordInquiryFrame frame = new HealthRecordInquiryFrame();

			}
		});

		mainAppPanel.add(addBtn);
		mainAppPanel.add(cancelBtn);
		mainAppPanel.add(inquiryBtn);

		mainAppFrame.add(scrollpane, BorderLayout.CENTER);
		mainAppFrame.add(mainAppPanel, BorderLayout.SOUTH);
		mainAppFrame.pack();
		mainAppFrame.setVisible(true);

		SpinnerDateModel model1 = new SpinnerDateModel();
		model1.setCalendarField(Calendar.SECOND);
		pickTimeSpinner = new JSpinner();
		pickTimeSpinner.setModel(model1);
		pickTimeSpinner.setEditor(new JSpinner.DateEditor(pickTimeSpinner, "HH:mm:ss"));
		String startTime = "00:00:00";

		lAlarmStat = new JLabel("stopped");
		timeFormat = new SimpleDateFormat("HH:mm:ss");
		try {
			Date spinnerStartTime = timeFormat.parse(startTime);
			pickTimeSpinner.setValue(spinnerStartTime);
		} catch (ParseException ex) {
			ex.printStackTrace();
		}

		setAlarmOnButton = new JButton("Start");
		setAlarmOffButton = new JButton("Stop");
		setAlarmOffButton.setEnabled(false);
		
		setAlarmOnButton.setBackground(Color.PINK);
		setAlarmOffButton.setBackground(Color.pink);

		setAlarmOnButton.addActionListener(new alarmOnListener());
		setAlarmOffButton.addActionListener(new alarmOffListener());

		Container containerForSpinner = new Container();
		containerForSpinner.setLayout(new FlowLayout(FlowLayout.CENTER, 1, 1));
		containerForSpinner.add(pickTimeSpinner);
		containerForSpinner.add(setAlarmOnButton);
		containerForSpinner.add(setAlarmOffButton);
		containerForSpinner.add(lAlarmStat);

		mainAppPanel.add(containerForSpinner);

		mainAppFrame.getContentPane().add(BorderLayout.SOUTH, mainAppPanel);
		mainAppFrame.setSize(700, 500);
		mainAppFrame.setVisible(true);
	}

	class Timer implements Runnable {
		SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
		Date timeValue;
		Calendar calendarHelper = Calendar.getInstance();
		String newTime;

		public Timer(Date startTime) {
			timeValue = startTime;
			calendarHelper.setTime(timeValue);
			newTime = timeFormat.format(calendarHelper.getTime());
			isLive = true;
		}

		public void run() {
			while (!(newTime.equals("00:00:00")) && isLive) {
				try {
					Thread.sleep(1000);
					calendarHelper.add(Calendar.SECOND, -1);
					newTime = timeFormat.format(calendarHelper.getTime());
					pickTimeSpinner.setValue(timeFormat.parse(newTime));
				} catch (InterruptedException ex) {
				} catch (ParseException ex) {
					ex.printStackTrace();
				}
			}
			playAlarmSound(newTime);
		}
	}

	class alarmOnListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			JOptionPane.showMessageDialog(null, "Started.");
			lAlarmStat.setText("Started.");

			pickTimeSpinner.setEnabled(false);
			setAlarmOnButton.setEnabled(false);
			setAlarmOffButton.setEnabled(true);

			timeValue = (Date) pickTimeSpinner.getValue();
			Runnable timerThread = new Timer(timeValue);
			mainThread = new Thread(timerThread);
			mainThread.start();
		}
	}

	class alarmOffListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			JOptionPane.showMessageDialog(null, "Stopped.");
			lAlarmStat.setText("Stopped.");

			pickTimeSpinner.setEnabled(true);
			setAlarmOnButton.setEnabled(true);
			setAlarmOffButton.setEnabled(false);
			isLive = false;
			mainThread.interrupt();

		}
	}

	public void playAlarmSound(String endTime) {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		if (endTime.equals("00:00:00")) {
			try {
				for (int i = 0; i < 5; i++) {
					toolkit.beep();
					Thread.sleep(500);
				}
				lAlarmStat.setText("Stopped.");
				pickTimeSpinner.setEnabled(true);
				setAlarmOnButton.setEnabled(true);
				setAlarmOffButton.setEnabled(false);
				isLive = false;
				mainThread.interrupt();
			} catch (Exception ex) {
			}

		}
	}

}
