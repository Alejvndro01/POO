package Controlador;

import Modelo.Ciudad;
import Modelo.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class CiudadOperaciones {
    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;
    private Conexion conectar = new Conexion();


    public int agregarCiudad(Ciudad ciudad) {
        int r = 0;
        String sql = "INSERT INTO ciudad(nombreCiudad, poblacionCiudad, codigoPais) VALUES(?, ?, ?)";
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, ciudad.getNombre());
            ps.setInt(2, ciudad.getPoblacion());
            ps.setString(3, ciudad.getCodigoPais());
            r = ps.executeUpdate();
            if (r == 1) {
                JOptionPane.showMessageDialog(null, "Ciudad agregada con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Error al agregar la ciudad.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al agregar la ciudad: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (con != null) con.close();
                if (ps != null) ps.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error al cerrar la conexión: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        return r;
    }

 
    public Ciudad consultarPorNombre(String nombre) {
        Ciudad ciudad = null;
        String sql = "SELECT * FROM ciudad WHERE nombreCiudad = ?";
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, nombre);
            rs = ps.executeQuery();
            if (rs.next()) {
                ciudad = new Ciudad();
                ciudad.setId(rs.getInt("idCiudad"));
                ciudad.setNombre(rs.getString("nombreCiudad"));
                ciudad.setPoblacion(rs.getInt("poblacionCiudad"));
                ciudad.setCodigoPais(rs.getString("codigoPais"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al consultar la ciudad: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (con != null) con.close();
                if (ps != null) ps.close();
                if (rs != null) rs.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error al cerrar la conexión: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        return ciudad;
    }

    public int modificar(Ciudad ciudad) {
        int r = 0;
        String sql = "UPDATE ciudad SET nombreCiudad = ?, poblacionCiudad = ?, codigoPais = ? WHERE idCiudad = ?";
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, ciudad.getNombre());
            ps.setInt(2, ciudad.getPoblacion());
            ps.setString(3, ciudad.getCodigoPais());
            ps.setInt(4, ciudad.getId());
            r = ps.executeUpdate();
            if (r == 1) {
                JOptionPane.showMessageDialog(null, "Ciudad modificada con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró una ciudad con el ID especificado.", "Error", JOptionPane.WARNING_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al modificar la ciudad: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (con != null) con.close();
                if (ps != null) ps.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error al cerrar la conexión: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        return r;
    }

    public int eliminarCiudad(int idCiudad) {
    String sql = "DELETE FROM ciudad WHERE idCiudad = ?";
    int r = 0;
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idCiudad);
            r = ps.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar laa ciudad: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (con != null) con.close();
                if (ps != null) ps.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error al cerrar la conexión: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        return r; 
        }
}    