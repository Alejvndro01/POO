package Controlador;
import Vista.Ventana;
import Modelo.Conexion;
import Modelo.Pais;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class PaisOperaciones {
    Ventana vista = new Ventana();
    PreparedStatement ps;
    ResultSet rs;
    Connection con;
    Conexion conectar = new Conexion();
    Pais p = new Pais();

    public int agregar(Pais pais) {  
        int r=0;
        String sql="insert into pais(codigoPais,nombrePais,continentePais,poblacionPais, tipoGobierno)values(?,?,?,?,1)";
        try {
            
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);            
            ps.setString(1,pais.getCodigo());
            ps.setString(2,pais.getNombre());
            ps.setString(3,pais.getContinente());
            ps.setInt(4,pais.getPoblacion());
            r = ps.executeUpdate();   
            
            if(r==1){
                return 1;
            }
            else{
                return 0;
            }
        } catch (Exception e) {
        }  
        return r;
    }
    
    public Pais consultarPorCodigo(String codigo) {
        Pais pais = null;
        String sql = "SELECT * FROM pais WHERE codigoPais = ?";
            try {
                con = conectar.getConnection();
                ps = con.prepareStatement(sql);
                ps.setString(1, codigo);
                rs = ps.executeQuery();
                    if (rs.next()) {
                        pais = new Pais();
                        pais.setCodigo(rs.getString("codigoPais"));
                        pais.setNombre(rs.getString("nombrePais"));
                        pais.setContinente(rs.getString("continentePais"));
                        pais.setPoblacion(rs.getInt("poblacionPais"));
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error al consultar el país: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
            return pais;
}
    
    public int modificar(Pais pais) {  
        int r = 0;
        String sql = "UPDATE pais SET nombrePais = ?, continentePais = ?, poblacionPais = ? WHERE codigoPais = ?";
            try {
                con = conectar.getConnection();
                ps = con.prepareStatement(sql);
                ps.setString(1, pais.getNombre());
                ps.setString(2, pais.getContinente());
                ps.setInt(3, pais.getPoblacion());
                ps.setString(4, pais.getCodigo());
                r = ps.executeUpdate();
        
                if (r == 1) {
                    JOptionPane.showMessageDialog(null, "País modificado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    return 1;
                } else {
                    JOptionPane.showMessageDialog(null, "No se encontró un país con el código especificado.", "Error", JOptionPane.WARNING_MESSAGE);
                    return 0;
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Se Produjo un error al intentar modificar el país.", "Error", JOptionPane.ERROR_MESSAGE);
            }
            return r;
    }
    
    public int eliminar(String codigo){
        int r = 0; 
        String sql = "DELETE FROM pais WHERE codigoPais = ?"; 

            try (Connection con = conectar.getConnection(); 
                PreparedStatement ps = con.prepareStatement(sql)) {
        
                if (con != null) { 
                    ps.setString(1, codigo); 
                    r = ps.executeUpdate(); 
                if (r == 1) {
                    JOptionPane.showMessageDialog(null, "País eliminado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                        return 1; 
                } else {
                    JOptionPane.showMessageDialog(null, "No se encontró un país con el código especificado.", "Error", JOptionPane.WARNING_MESSAGE);
                        return 0; 
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Error: No se pudo establecer conexión a la base de datos.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Se produjo un error al intentar eliminar el país: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
            return r;
        }  
}
