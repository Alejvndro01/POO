package Controlador;

import Modelo.Pais;
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
    Pais p = new Pais();
    Ventana vista = new Ventana();
    DefaultTableModel modelo = new DefaultTableModel();

    public Controlador(Ventana v) {
        this.vista = v;
        this.vista.btnConsultar.addActionListener(this);
        this.vista.btnAgregar.addActionListener(this);
        this.vista.btnModificar.addActionListener(this);
        this.vista.btnEliminar.addActionListener(this);
        this.vista.btnGuardar.addActionListener(this);
        this.vista.btnLimpiar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == vista.btnAgregar) {
            agregar();
            limpiar();
        }
        if (e.getSource() == vista.btnConsultar) {
        consultar();
        }
        if (e.getSource() == vista.btnModificar) {
            modificar();
            limpiar();
        }
        if (e.getSource() == vista.btnGuardar) {
          
        }
        if (e.getSource() == vista.btnEliminar) {
           
        }
        if (e.getSource() == vista.btnLimpiar) {
            limpiar();
        }

    }

    void limpiar() {
        vista.txtNombrePais.setText("");
        vista.txtCodigoPais.setText("");
        vista.txtPoblacionPais.setText("");
        vista.txtContinentePais.setText("");
        vista.txtCodigoPais.requestFocus();
    }

   
    public void agregar() {
        String codigo = vista.txtCodigoPais.getText();
        String nombre = vista.txtNombrePais.getText();
        String continente = vista.txtContinentePais.getText();
        int poblacion = Integer.parseInt(vista.txtPoblacionPais.getText());
        p.setCodigo(codigo);
        p.setNombre(nombre);
        p.setContinente(continente);
        p.setPoblacion(poblacion);
        int r = paisOp.agregar(p);
        if (r == 1) {
            JOptionPane.showMessageDialog(vista, "Pais agregado con éxito.");
        } else {
            JOptionPane.showMessageDialog(vista, "Error");
        }
        limpiarTabla();
    }
    
    public void consultar() {
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
    
    public void modificar() {
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
