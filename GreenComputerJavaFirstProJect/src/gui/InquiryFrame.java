package gui;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.*;
public class InquiryFrame extends JFrame implements ActionListener{
    JTextField tf;
    JLabel la;
    DefaultTableModel model;
    JTable table;
    public InquiryFrame() {
        tf=new JTextField(15);
        la=new JLabel("이름을 입력해주세요.");
        
        String[] col= {"이름","[회원코드,이름,나이,키,몸무게]"};
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
            String re_name=tf.getText();
            if(re_name.length()<1)
            {
                JOptionPane.showMessageDialog(this, "회원이름을 입력하세요.");
                return;
            }
            for(int i=model.getRowCount()-1;i>=0;i--) {
                model.removeRow(i);
            }
            MemberInquiryNameEqualsDAO  dao=new MemberInquiryNameEqualsDAO();
            ArrayList<MemberRegistrationVo> list=dao.postfind(re_name);
            
            for(MemberRegistrationVo vo:list) {
                String[] data= {vo.getRe_name(), vo.getRegistration()};
                model.addRow(data);
            }    
        }
    }
}