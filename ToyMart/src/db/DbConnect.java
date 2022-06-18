package db;

import java.sql.*;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class DbConnect {
    static public Connection c;
    static public Statement st;
    static{
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            c=DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/toymart",
            "root","Incapp@123");
            st=c.createStatement();
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,ex);
        }
    }
}
