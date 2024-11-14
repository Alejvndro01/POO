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
        if (e.getSource() == vista.btnModificar) {
          
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
