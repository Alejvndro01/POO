package Controlador;

import Modelo.Pais;
import Modelo.Ciudad;
import Vista.Ventana;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class Controlador implements ActionListener {

    PaisOperaciones paisOp = new PaisOperaciones();
    CiudadOperaciones ciudadOp = new CiudadOperaciones();
    Pais p = new Pais();
    Ventana vista = new Ventana();
    DefaultTableModel modelo = new DefaultTableModel();
    
    public Controlador(Ventana v) {
        this.vista = v;
        this.vista.btnConsultar.addActionListener(this);
        this.vista.btnConsultar1.addActionListener(this);
        this.vista.btnAgregar.addActionListener(this);
        this.vista.btnAgregar1.addActionListener(this);
        this.vista.btnModificar.addActionListener(this);
        this.vista.btnModificar1.addActionListener(this);
        this.vista.btnEliminar.addActionListener(this);
        this.vista.btnEliminar1.addActionListener(this);
        this.vista.btnGuardar.addActionListener(this);
        this.vista.btnLimpiar.addActionListener(this);
        this.vista.btnLimpiar1.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == vista.btnAgregar) {
            agregarPais();
            limpiarPais();
        }
        if (e.getSource() == vista.btnAgregar1) {
            agregarCiudad();
            limpiarCiudad();
        }
        if (e.getSource() == vista.btnConsultar) {
            consultarPais();
        }
        if (e.getSource() == vista.btnConsultar1){
            consultarCiudad();
        }
        if (e.getSource() == vista.btnModificar) {
            modificarPais();
            limpiarPais();
        }
        if (e.getSource() == vista.btnModificar1) {
            modificarCiudad();
        }
        if (e.getSource() == vista.btnGuardar) {
          
        }
        if (e.getSource() == vista.btnEliminar) {
           
        }
        if (e.getSource()  == vista.btnEliminar1) {
            eliminarCiudad();
        }
        if (e.getSource() == vista.btnLimpiar) {
            limpiarPais();
        }
        if (e.getSource() == vista.btnLimpiar1) {
            limpiarCiudad();
        }

    }

    void limpiarPais() {
        vista.txtNombrePais.setText("");
        vista.txtCodigoPais.setText("");
        vista.txtPoblacionPais.setText("");
        vista.txtContinentePais.setText("");
        vista.txtCodigoPais.requestFocus();
    }
    void limpiarCiudad() {
        vista.txtIDCiudad.setText("");
        vista.txtNombreCiudad.setText("");
        vista.txtPoblacionCiudad.setText("");
        vista.txtCodigoPaCiudad.setText("");
    }

   
    public void agregarPais() {
        String codigo = vista.txtCodigoPais.getText();
        String nombre = vista.txtNombrePais.getText();
        String continente = vista.txtContinentePais.getText();
        int poblacion = Integer.parseInt(vista.txtPoblacionPais.getText());
            p.setCodigo(codigo);
            p.setNombre(nombre);
            p.setContinente(continente);
            p.setPoblacion(poblacion);
            int r = paisOp.agregarPais(p);
            if (r == 1) {
                JOptionPane.showMessageDialog(vista, "Pais agregado con éxito.");
            } else {
                JOptionPane.showMessageDialog(vista, "Error");
            }
            limpiarTabla();
    }
    
    private void agregarCiudad() {
        String nombreCiudad = vista.txtNombreCiudad.getText(); 
        String codigoPais = vista.txtCodigoPaCiudad.getText(); 
        String idCiudadStr = vista.txtIDCiudad.getText(); 
        String poblacionStr = vista.txtPoblacionCiudad.getText(); 

            if (nombreCiudad.isEmpty() || codigoPais.isEmpty() || idCiudadStr.isEmpty() || poblacionStr.isEmpty()) {
                JOptionPane.showMessageDialog(vista, "Todos los campos deben ser llenados.", "ERROR", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int idCiudad;
            int poblacion;

            try {
                idCiudad = Integer.parseInt(idCiudadStr); 
                poblacion = Integer.parseInt(poblacionStr); 
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(vista, "El ID y la población deben ser números válidos.", "ERROR", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            if (!paisOp.codigoPaisExiste(codigoPais)) {
                JOptionPane.showMessageDialog(vista, "El código de país no existe. No se puede agregar la ciudad.", "ERROR", JOptionPane.ERROR_MESSAGE);
                return; 
            }

            Ciudad ciudad = new Ciudad(idCiudad, nombreCiudad, codigoPais, poblacion);
            int resultado = ciudadOp.agregarCiudad(ciudad); 

            if (resultado == 1) {
                JOptionPane.showMessageDialog(vista, "Ciudad agregada con éxito.");
                limpiarCiudad(); 
            } else {
                JOptionPane.showMessageDialog(vista, "Error al agregar la ciudad.");
            }
}
    
    
    
    public void consultarPais() {
        String codigo = vista.txtCodigoPais.getText();
            if (codigo.isEmpty()) {
                JOptionPane.showMessageDialog(vista, "Por favor ingrese un código de país.", "ERROR", JOptionPane.ERROR_MESSAGE);
                return;
            }
            Pais pais = paisOp.consultarPorCodigo(codigo);
            if (pais != null) {
                vista.txtNombrePais.setText(pais.getNombre());
                vista.txtContinentePais.setText(pais.getContinente());
                vista.txtPoblacionPais.setText(String.valueOf(pais.getPoblacion()));
            } else {
                JOptionPane.showMessageDialog(vista, "No se encontró un país con el código especificado.", "ERROR", JOptionPane.WARNING_MESSAGE);
            }
    }
    
    
    private void consultarCiudad() {
        String nombreCiudad = vista.txtNombreCiudad.getText();
            if (nombreCiudad.isEmpty()) {
                JOptionPane.showMessageDialog(vista, "Por favor ingrese un nombre de ciudad.", "ERROR", JOptionPane.ERROR_MESSAGE);
                return;
            }
            Ciudad ciudad = ciudadOp.consultarPorNombre(nombreCiudad);
            if (ciudad != null) {
                vista.txtIDCiudad.setText(String.valueOf(ciudad.getId()));
                vista.txtPoblacionCiudad.setText(String.valueOf(ciudad.getPoblacion()));
                vista.txtCodigoPaCiudad.setText(ciudad.getCodigoPais());
            } else {
                JOptionPane.showMessageDialog(vista, "No se encontró una ciudad con el nombre especificado.", "ERROR", JOptionPane.WARNING_MESSAGE);
            }
    }
    
    public void modificarPais() {
        String codigo = vista.txtCodigoPais.getText();
        String nombre = vista.txtNombrePais.getText();
        String continente = vista.txtContinentePais.getText();
        String poblacionStr = vista.txtPoblacionPais.getText();

            if (codigo.isEmpty() || nombre.isEmpty() || continente.isEmpty() || poblacionStr.isEmpty()) {
                JOptionPane.showMessageDialog(vista, "Todos los campos deben ser llenados.", "ERROR", JOptionPane.ERROR_MESSAGE);
                return;
            }
            int poblacion;
            try {
                poblacion = Integer.parseInt(poblacionStr);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(vista, "La población debe ser un número válido.", "ERROR", JOptionPane.ERROR_MESSAGE);
                return;
            }
        p.setCodigo(codigo);
        p.setNombre(nombre);
        p.setContinente(continente);
        p.setPoblacion(poblacion);
        
        int r = paisOp.modificar(p);
            if (r == 1) {
                JOptionPane.showMessageDialog(vista, "País modificado con éxito.");
            } else {
                JOptionPane.showMessageDialog(vista, "Error al modificar el país.");
            }
            limpiarTabla();
    }
    
    private void modificarCiudad() {
        String nombreCiudad = vista.txtNombreCiudad.getText(); 
        String idCiudadStr = vista.txtIDCiudad.getText(); 
        String poblacionStr = vista.txtPoblacionCiudad.getText(); 
        String codigoPais = vista.txtCodigoPaCiudad.getText(); 

            if (nombreCiudad.isEmpty() || idCiudadStr.isEmpty() || poblacionStr.isEmpty() || codigoPais.isEmpty()) {
                JOptionPane.showMessageDialog(vista, "Todos los campos deben ser llenados.", "ERROR", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int idCiudad;
            int poblacion;

            try {
                idCiudad = Integer.parseInt(idCiudadStr); 
                poblacion = Integer.parseInt(poblacionStr); 
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(vista, "La población y el ID deben ser números válidos.", "ERROR", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Ciudad ciudad = new Ciudad(idCiudad, nombreCiudad, codigoPais, poblacion);
            int resultado = ciudadOp.modificar(ciudad); 

            if (resultado == 1) {
                JOptionPane.showMessageDialog(vista, "Ciudad modificada con éxito.");
                limpiarCiudad();
            } else {
                JOptionPane.showMessageDialog(vista, "Error al modificar la ciudad.");
            }
}
    
    private void eliminarCiudad() {
        String idCiudadStr = vista.txtIDCiudad.getText();
            if (idCiudadStr.isEmpty()) {
                JOptionPane.showMessageDialog(vista, "Por favor ingresa el ID de la ciudad a eliminar.", "ERROR", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int idCiudad;
            try {
                idCiudad = Integer.parseInt(idCiudadStr);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(vista, "El ID debe ser un número válido.", "ERROR", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int resultado = ciudadOp.eliminarCiudad(idCiudad); 

            if (resultado == 1) {
                JOptionPane.showMessageDialog(vista, "Ciudad eliminada con éxito.");
                limpiarCiudad(); // Método para limpiar los campos
            } else {
                JOptionPane.showMessageDialog(vista, "Error al eliminar la ciudad.");
            }
}
   
    void centrarCeldas(JTable tabla) {
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < vista.jTablePais.getColumnCount(); i++) {
            tabla.getColumnModel().getColumn(i).setCellRenderer(tcr);
        }
    }

    void limpiarTabla() {
        for (int i = 0; i < vista.jTablePais.getRowCount(); i++) {  
            modelo.removeRow(i);
            i = i - 1;
        }
    }
}
