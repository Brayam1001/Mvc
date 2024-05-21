package Controller;

import Model.Aprendiz;
import Model.AprendizDAO;
import Model.Ficha;
import Model.FichaDAO;
import View.Formulary;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class Controlador implements ActionListener {

    AprendizDAO dao = new AprendizDAO();
    FichaDAO fao = new FichaDAO();
    Ficha fi = new Ficha();
    Aprendiz ap = new Aprendiz();
    Formulary form;

    public Controlador(Formulary f) {
        this.form = f;
        this.form.btnList.addActionListener(this);
        this.form.btnSave.addActionListener(this);
        this.form.btnDelete.addActionListener(this);
        this.form.btnSearch.addActionListener(this);
        this.form.btnUpdate.addActionListener(this);
        this.form.btnExit.addActionListener(this);
        this.form.btnDeleteToken.addActionListener(this);
        this.form.btnListToken.addActionListener(this);
        this.form.btnSaveToken.addActionListener(this);
        this.form.btnUpdateToken.addActionListener(this);
        this.form.btnSEEE.addActionListener(this);
        this.form.btnListarTodooo.addActionListener(this);
    
       
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==form.btnList) {
            limpiarTabla();
            listar(form.tblApprentice);
            limpiar();
        }
        if (e.getSource()==form.btnListToken) {
            limpiarTablaFI();
            listarfi(form.tblToken);
            limpiarrr();
        }
        if (e.getSource()==form.btnListarTodooo) {
            limpiarTodo();
            listartodoEnTabla(form.Tbltodo);
          
        }
        
        if (e.getSource()==form.btnSave) {
            guardar();
            listar(form.tblApprentice);
            limpiar();
        }
        if (e.getSource()==form.btnSaveToken) {
            guardarfi();
            listarfi(form.tblToken);
            limpiarrr();
        }
        
        
        if (e.getSource()==form.btnSearch) {
          int fila = form.tblApprentice.getSelectedRow();
            if (fila==-1) {
                JOptionPane.showMessageDialog(form, "Please select a row!!!");
            } else {
                int id = Integer.parseInt(form.tblApprentice.getValueAt(fila, 0).toString());
                String tipodoc = form.tblApprentice.getValueAt(fila, 1).toString();
                String numDoc = form.tblApprentice.getValueAt(fila, 2).toString();
                String nomb = form.tblApprentice.getValueAt(fila, 3).toString();
                String nac = form.tblApprentice.getValueAt(fila, 4).toString();
                String gener = form.tblApprentice.getValueAt(fila, 5).toString();
                String ciudad = form.tblApprentice.getValueAt(fila, 6).toString();
                
                form.txtId.setText(String.valueOf(id));
                form.cbxType.setSelectedItem(tipodoc);
                form.txtNumdoc.setText(numDoc);
                form.txtName.setText(nomb);
                form.jdcNacimi.setDate(java.sql.Date.valueOf(nac));
                
                if (gener.equalsIgnoreCase("Male")) {
                    form.rbMale.setSelected(true);
                } else if(gener.equalsIgnoreCase("Female")) {
                    form.rbFemale.setSelected(true);
                }
                form.txtCity.setText(ciudad);
            }
            
        }
        
        
        if (e.getSource()==form.btnSEEE) {
          int filas = form.tblToken.getSelectedRow();
            if (filas==-1) {
                JOptionPane.showMessageDialog(form, "Please select a row!!!");
            } else {
                int id = Integer.parseInt(form.tblToken.getValueAt(filas, 0).toString());
                
                String numficha = form.tblToken.getValueAt(filas, 1).toString();
                String nomficha = form.tblToken.getValueAt(filas, 2).toString();
                String sede = form.tblToken.getValueAt(filas, 3).toString();
                String ciudad = form.tblToken.getValueAt(filas, 4).toString();
                int idapre = Integer.parseInt(form.tblToken.getValueAt(filas,5).toString());
                
                form.txtCityToken.setText(ciudad);
                form.txtNameToken.setText(nomficha);
                form.txtNumToken.setText(numficha);
                form.cbxToken.setSelectedItem(sede);
                form.txtIdToken.setText(String.valueOf(id));
                form.txtIdApr.setText(String.valueOf(idapre));
                
            }
        }
        if (e.getSource()==form.btnUpdate) {
            actualizar();
            listar(form.tblApprentice);
            limpiar();
        }
         if (e.getSource()==form.btnUpdateToken) {
            actualizarfi();
            listarfi(form.tblToken);
            limpiarrr();
        }
         if (e.getSource()==form.btnDelete) {
            eliminar();
            listar(form.tblApprentice);
            limpiar();
        }
        
        if (e.getSource()==form.btnExit) {
            int confirmar = JOptionPane.showConfirmDialog(null, "¿Desa salir del programa!!!!!?", "Mensaje", JOptionPane.YES_NO_OPTION);
        if (confirmar == JOptionPane.OK_OPTION)
        {
            System.exit(0);
        }
        
        }
        if (e.getSource()==form.btnDeleteToken) {
            eliminarfi();
            listarfi(form.tblToken);
            limpiarrr();
        }
        
    }

    public void listar(JTable tblApprentice) {
        centrarCeldad(tblApprentice);
        DefaultTableModel modelo = (DefaultTableModel) form.tblApprentice.getModel();
        modelo.setRowCount(0);
        List<Aprendiz> lista = dao.Listar();

        for (Aprendiz apre : lista) {
            Object[] objeto = {apre.getId(), apre.getTipodoc(), apre.getNumerodoc(), apre.getNombre(), apre.getFechanacimi(), apre.getGenero(), apre.getCiudad()};
            modelo.addRow(objeto);
        }

        tblApprentice.setModel(modelo);

    }
    
     public void listartodoEnTabla(JTable tbltodo) {
         centrarultimo(tbltodo);
        DefaultTableModel modelo = (DefaultTableModel) form.Tbltodo.getModel();
        modelo.setRowCount(0);
        List<Object[]> lista = fao.Listartodo();

        for (Object[] datos : lista) {
            Ficha fi = (Ficha) datos[0];
            Aprendiz ap = (Aprendiz) datos[1];

            Object[] fila = {
                fi.getNumeroficha(),fi.getNombreficha(),ap.getNombre(),fi.getSede()
            };

            modelo.addRow(fila);
        }

        tbltodo.setModel(modelo);
    }

    
    public void listarfi (JTable tblToken){
        
        centrarceldaFI(tblToken);
        DefaultTableModel modelo = (DefaultTableModel) form.tblToken.getModel();
        modelo.setRowCount(0);
        List<Ficha> lista = fao.ListarFi();

        for (Ficha fis : lista) {
            Object[] objeto = {fis.getIdficha(), fis.getNumeroficha(), fis.getNombreficha(), fis.getSede(), fis.getCiudad(), fis.getIdapre()};
            modelo.addRow(objeto);
        }

        tblToken.setModel(modelo);

       
    }
    public void guardarfi (){
       String numFi = form.txtNumToken.getText();
       String nomFi = form.txtNameToken.getText();
       String sede = form.cbxToken.getSelectedItem().toString();
       String ciudad = form.txtCityToken.getText();
       
       int idapre;

    try {
        idapre = Integer.parseInt(form.txtIdApr.getText());
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(form, "Ficha NO Registrado");
        return; // Salir del método si el ID no es válido
    }
       
       Ficha fis = new Ficha();
       fis.setNumeroficha(numFi);
       fis.setNombreficha(nomFi);
       fis.setSede(sede);
       fis.setCiudad(ciudad);
       fis.setIdapre(idapre);
        
        int rr = fao.AgregarFi(fis);
        if (rr == 1) {
            JOptionPane.showMessageDialog(form, "Ficha Registrado");
        }

        
    }

    public void guardar() {
        String tipoDoc = form.cbxType.getSelectedItem().toString();
        String numDoc = form.txtNumdoc.getText();
        String nombre = form.txtName.getText();
        java.util.Date fechanac = form.jdcNacimi.getDate();
        String genero = form.rbMale.isSelected() ? "Male" : "Female";
        String ciudad = form.txtCity.getText();

        Aprendiz apre = new Aprendiz();
        apre.setTipodoc(tipoDoc);
        apre.setNumerodoc(numDoc);
        apre.setNombre(nombre);
        apre.setFechanacimi(fechanac);
        apre.setGenero(genero);
        apre.setCiudad(ciudad);

        int r = dao.Agregar(apre);
        if (r == 1) {
            JOptionPane.showMessageDialog(form, "Usuario Registrado");
        } else {
            JOptionPane.showMessageDialog(form, "Usuario NO Registrado");
        }

    }
public void actualizarfi(){
    if (form.txtIdApr.getText().equals("")) {
            JOptionPane.showMessageDialog(form, "Id no encontrado \n\nSeleccione un registro existente");
     
}
    
    
    else   {
        int idfi = Integer.parseInt(form.txtIdToken.getText());
        
       String numFi = form.txtNumToken.getText();
       String nomFi = form.txtNameToken.getText();
       String sede = form.cbxToken.getSelectedItem().toString();
       String ciudad = form.txtCityToken.getText();
       int idapre = Integer.parseInt(form.txtIdApr.getText());
       
       fi.setIdficha(idfi);
       fi.setNumeroficha(numFi);
       fi.setNombreficha(nomFi);
       fi.setSede(sede);
       fi.setCiudad(ciudad);
       fi.setIdapre(idapre);
        
        int r = fao.ActualizarFi(fi);
            if (r == 1) {
                JOptionPane.showMessageDialog(form, "Token Update");
            } else {
                JOptionPane.showMessageDialog(form, "Token not Updated");
            }

        
    }
}
    public void actualizar() {
        if (form.txtId.getText().equals("")) {
            JOptionPane.showMessageDialog(form, "Id no encontrado \n\nSeleccione un registro existente");
        } else {
            int id = Integer.parseInt(form.txtId.getText());
            String tipoDoc = form.cbxType.getSelectedItem().toString();
            String numDoc = form.txtNumdoc.getText();
            String nombre = form.txtName.getText();
            java.util.Date fechanac = form.jdcNacimi.getDate();
            String genero = form.rbMale.isSelected() ? "Male" : "Female";
            String ciudad = form.txtCity.getText();

            ap.setId(id);
            ap.setTipodoc(tipoDoc);
            ap.setNumerodoc(numDoc);
            ap.setNombre(nombre);
            ap.setFechanacimi(fechanac);
            ap.setGenero(genero);
            ap.setCiudad(ciudad);

            int r = dao.Actualizar(ap);
            if (r == 1) {
                JOptionPane.showMessageDialog(form, "User Update");
            } else {
                JOptionPane.showMessageDialog(form, "User not Updated");
            }

        }

    }
    public void eliminar(){
        int fila = form.tblApprentice.getSelectedRow();
        if (fila==-1) {
            JOptionPane.showMessageDialog(form, "Please select a row!!!");
        } else {
            int id = Integer.parseInt(form.tblApprentice.getValueAt(fila, 0).toString());
            dao.Eliminar(id);
            JOptionPane.showMessageDialog(form, "Deleted Record");
        }
        
      
        
    }
    public void eliminarfi(){
        
         int fila = form.tblToken.getSelectedRow();
        if (fila==-1) {
            JOptionPane.showMessageDialog(form, "Please select a row!!!");
        } else {
            int id = Integer.parseInt(form.tblToken.getValueAt(fila, 0).toString());
            fao.EliminarFi(id);
            JOptionPane.showMessageDialog(form, "Deleted Record");
        }
        
      
        
        
    }

    public void centrarCeldad(JTable tblApprentice) {
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < form.tblApprentice.getColumnCount(); i++) {
            tblApprentice.getColumnModel().getColumn(i).setCellRenderer(tcr);

        }}
    
    public void centrarceldaFI (JTable tblToken){
DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < form.tblToken.getColumnCount(); i++) {
            tblToken.getColumnModel().getColumn(i).setCellRenderer(tcr);

}}
        public void centrarultimo (JTable tbltodo){
DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < form.Tbltodo.getColumnCount(); i++) {
            tbltodo.getColumnModel().getColumn(i).setCellRenderer(tcr);


}
    }

    void limpiarTabla() {
        DefaultTableModel modelo = (DefaultTableModel) form.tblApprentice.getModel();
        modelo.setRowCount(0);
    }
    void limpiarTablaFI(){
        
        DefaultTableModel modelo = (DefaultTableModel) form.tblToken.getModel();
        modelo.setRowCount(0);
        
    }
    void limpiarTodo(){
        
        DefaultTableModel modelo = (DefaultTableModel) form.Tbltodo.getModel();
        modelo.setRowCount(0);
        
    }

    public void limpiar() {
        form.txtId.setText("");
        form.cbxType.setSelectedIndex(0);
        form.txtNumdoc.setText("");
        form.txtCity.setText("");
        form.txtName.setText("");
        form.jdcNacimi.setDate(null);
        form.rbFemale.setSelected(false);
        form.rbMale.setSelected(false);

    }
     
    
public void limpiarrr(){
    form.txtCityToken.setText("");
    form.txtIdApr.setText("");
    form.txtIdToken.setText("");
    form.txtNameToken.setText("");
    form.txtNumToken.setText("");
    form.cbxToken.setSelectedItem(0);
    
    
    
}

    private List<Object[]> Listartodo() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
}
