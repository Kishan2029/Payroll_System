import java.awt.*;
import javax.swing.*;
import java.sql.*;
import java.awt.event.*;
public class pay_slip extends JFrame implements ActionListener{
    
    Choice c1;
    JButton b1;
    JTextArea t1;
    
    pay_slip()
    {
        super("Pay Slip");
        setSize(1000,600);
        setLocation(450,200);  
        setLayout(new BorderLayout());
        c1=new Choice();
        try
        {
            Conn c= new Conn();
            ResultSet rs=c.s.executeQuery("select * from employee");
            
            while(rs.next())
            {
                c1.add(rs.getString("id"));
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        JPanel p1=new JPanel();
        p1.add(new JLabel("Select ID"));
        p1.add(c1);
        add(p1,"North");
        
        t1=new JTextArea(30,80);
        JScrollPane jsp = new JScrollPane(t1);
        
        Font f1=new Font("arial",Font.BOLD,20);
        t1.setFont(f1);
        
        b1=new JButton("Generate a Pay slip");
        add(b1,"South");
        add(jsp,"Center");
        
        b1.addActionListener(this);
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae)
    {
         try
        {
            Conn c= new Conn();
            ResultSet rs=c.s.executeQuery("select * from employee where id="+c1.getSelectedItem());
            rs.next();
            String name = rs.getString("name");
            rs.close();
            
            rs=c.s.executeQuery("select * from salary where id="+c1.getSelectedItem());
            double gross=0;
            double net=0;
            
            java.util.Date d1= new java.util.Date();
            int month=d1.getMonth();
            t1.setText("----------------------- PAY SLIP FOR THE MONTH OF "+month+" ,2021 -------------------");
            t1.append("\n");
            
            if(rs.next())
            {
                t1.append("\n        Employee ID"+rs.getString("id"));
                t1.append("\n        Employee Name"+name);
                
                t1.append("\n---------------------------------------------------\n");
                
                double hra= rs.getDouble("hra");
                t1.append("\n               HRA             :"+hra);
                double da= rs.getDouble("da");
                t1.append("\n               DA             :"+da);
                double med= rs.getDouble("med");
                t1.append("\n               MED             :"+med);
                double pf= rs.getDouble("pf");
                t1.append("\n               PF             :"+pf);
                double basic= rs.getDouble("basic_salary");
                t1.append("\n               Basic Salary             :"+basic);
                
                gross=hra+da+med+pf+basic;
                net=gross-pf;
                t1.append("\n---------------------------------------------------\n");
                
                t1.append("\n           GROSS SALARY:"+gross);
                t1.append("\n           NET SALARY:"+net);
                t1.append("\n           Tac   :   2.1 % of gross "+(gross*2.1/100));
                
                t1.append("\n---------------------------------------------------\n");
                t1.append("\n\n\n");
            }
        }
        catch(Exception ee)
        {
            ee.printStackTrace();
        }
    }
    public static void main(String[] args) {
        new pay_slip();
    }
}
