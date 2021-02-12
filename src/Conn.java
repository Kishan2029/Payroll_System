import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
public class Conn 
{
    public Connection c;
    public Statement s;
            
    public Conn()
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            c=DriverManager.getConnection("jdbc:mysql:///payroll","root","");
            s=c.createStatement();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
