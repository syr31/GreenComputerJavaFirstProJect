package gui;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.*;
public class FindingAMemberFrame extends JFrame implements ActionListener{
    JTextField tf;
    JLabel la;
    DefaultTableModel model;
    JTable table;
    public FindingAMemberFrame() {
        tf=new JTextField(15);
        la=new JLabel("이름을 입력해주세요.");
        
        String[] col= {"이름","[ ID,PWD,NAME,GENDER ]"};
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
        // TODO Auto-generated method stub
        if(e.getSource()==tf) {
            String name=tf.getText();
            if(name.length()<1)//�Է��� �ȵ� ���
            {
                JOptionPane.showMessageDialog(this, "회원이름을 입력하세요.");
                return;
            }
            //ó��
            for(int i=model.getRowCount()-1;i>=0;i--) {
                model.removeRow(i);
            }
            FindingAMemberDAO  dao=new FindingAMemberDAO();
            ArrayList<MemberSignUpVo> list=dao.postfind(name);
            
            // ���
            for(MemberSignUpVo vo:list) {
                String[] data= {vo.getName(), vo.getSignUp()};
                model.addRow(data);
            }    
        }
    }
}