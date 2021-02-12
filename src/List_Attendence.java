import java.awt.*;
import javax.swing.*;
import java.sql.*;
import java.awt.event.*;

public class List_Attendence extends JFrame implements ActionListener{
 
    JTable j1;
    JButton b1;
    String h[]={"Exm id","Date Time","First Half","Second Half"}; 
    String d[][]=new String[15][4];
    int i=0,j=0;
    
    List_Attendence()
    {
        super("View Employee Attendnce");
        setSize(1000,400);
        setLocation(450,200);
        
        try
        {
            Conn c= new Conn();
            ResultSet rs=c.s.executeQuery("select * from attendence");
            
            while(rs.next())
            {
                d[i][j++]=rs.getString("id");
                d[i][j++]=rs.getString("date_tm");
                d[i][j++]=rs.getString("f_half");
                d[i][j++]=rs.getString("s_half");
                
                i++;
                j=0;
            }
            j1=new JTable(d,h);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        b1=new JButton("Print");
        add(b1,"South");
        JScrollPane s1= new JScrollPane(j1);
        add(s1);
        b1.addActionListener(this);
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae)
    {
        try{
            j1.print();
        }catch(Exception e){}
    }
    public static void main(String[] args) {
     
        new List_Attendence();
    }

            
}
