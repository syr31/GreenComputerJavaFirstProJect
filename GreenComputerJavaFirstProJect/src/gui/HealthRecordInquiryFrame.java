package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;


public class HealthRecordInquiryFrame extends JFrame implements ActionListener{
	
    JTextField tf;
    JLabel la;
    DefaultTableModel model;
    JTable table;
    
    public HealthRecordInquiryFrame() {
        tf=new JTextField(26);
        la=new JLabel("날짜를 입력해주세요.");
        
        String[] col= {"날짜","[종목,무게,SET,횟수]"};
        String[][] row=new String[0][5];
        
        model=new DefaultTableModel(row,col);
        table=new JTable(model);
        JScrollPane js=new JScrollPane(table);
        JPanel p=new JPanel();
        p.add(la);
        p.add(tf);
        add("North",p);
        add("Center",js);
        
        setSize(450, 500);
        setVisible(true);
        tf.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==tf) {
            String Health_date=tf.getText();
            if(Health_date.length()<1)
            {
                JOptionPane.showMessageDialog(this, "날짜을 입력하세요.");
                return;
            }
            for(int i=model.getRowCount()-1;i>=0;i--) {
                model.removeRow(i);
            }
            MemberInquirydateDAO dao=new MemberInquirydateDAO();
            
            ArrayList<HealthRecordVO> list=dao.postfind(Health_date);
            
            for(HealthRecordVO vo:list) {
                String[] data= {vo.getHealth_date(), vo.getRegistration()};
                model.addRow(data);
            }    
        }
    }
}