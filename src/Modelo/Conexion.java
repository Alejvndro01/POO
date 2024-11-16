package Modelo;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

public class Conexion {
    
    private String ip = "localhost"; 
    private String bd = "dilan"; 
    private String url = "jdbc:mysql://"+ip+":3306/"+bd; 
    private String user = "root"; 
    private String pass = "alumnos"; 
    
    Connection con;
    public Connection getConnection(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con=DriverManager.getConnection(url,user,pass);
            JOptionPane.showMessageDialog(null, "Conexion exitosa");
            return con;
        } catch (Exception e) { 
            JOptionPane.showMessageDialog(null, "Error" + e);
        }
        return con;
    }
}
