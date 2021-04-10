/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Formularios.FrmFactura;
import entidades.Aguasobrante;
import entidades.Detallefacturasobrante;
import entidades.Tarifassobrante;
import entidadesCruds.DetallefacturasobranteJpaController;
import entidadesCruds.exceptions.IllegalOrphanException;
import entidadesCruds.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author JC-PC
 */
public class classDetalleFacturasobrante {
    
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("japPU");
    public DetallefacturasobranteJpaController detallefacturasobranteJpacontrolador = new DetallefacturasobranteJpaController(emf);
    classAguasobrante cm;
    classTarifassobrante ct = new classTarifassobrante();
    DefaultTableModel mod;
    
    public List<Detallefacturasobrante> detallefacturaDatos ;
    
    EntityManager em = emf.createEntityManager();
    
    public List<Detallefacturasobrante> getDetallefacturaSobrante() {
        return detallefacturasobranteJpacontrolador.findDetallefacturasobranteEntities();
    }
    
    public void guardarDetallefacturasobrante(int idTarifas, int idAguasobrante, String anioMes, float subtotal, float total, String observacion) {
        try {
            cm = new classAguasobrante();
            int i = 0;
            Detallefacturasobrante med = new Detallefacturasobrante();
            for (Detallefacturasobrante md : getDetallefacturaSobrante()) {
                if (md.getIdaguasobrante().getIdaguasobrante().equals(idAguasobrante) && md.getAniomes().equals(anioMes)) {
                    i = 1;
                    med = md;
                    break;
                }
            }
            if (i == 1) {
                JOptionPane.showMessageDialog(null, "Num. Codigo, Fecha Ya Estan Registrados", "Informacion", 1);
                med = null;
                
            } else {
                Aguasobrante idmed = cm.aguasobranteJpacontrolador.findAguasobrante(cm.buscarAguasobranteId(idAguasobrante).getIdaguasobrante());
                Tarifassobrante idTar = ct.tarifassobranteJpacontrolador.findTarifassobrante(ct.buscarIdTarifassobrante(idTarifas).getIdtarifassobrante());
                Detallefacturasobrante dat = new Detallefacturasobrante();
                
                dat.setAniomes(anioMes);
                dat.setIdtarifassobrante(idTar);
                dat.setIdaguasobrante(idmed);
                dat.setSubtotal(subtotal);
                dat.setTotal(total);
                dat.setObservacion(observacion);
                detallefacturasobranteJpacontrolador.create(dat);
                JOptionPane.showMessageDialog(null, "Guardado", "Información", 1);
            }
        } catch (Exception e) {
            
        }
    }
    
    public boolean modificarDetallefacturasobrante(int idDetallefacturasobrante, int idTarifas, int idAguasobrante, String anioMes, float subtotal, float total, String observacion) {
        try {
            cm = new classAguasobrante();
            Detallefacturasobrante dat = detallefacturasobranteJpacontrolador.findDetallefacturasobrante(idDetallefacturasobrante);
            if (dat == null) {
                return false;
            }
            Aguasobrante idmed = cm.aguasobranteJpacontrolador.findAguasobrante(cm.buscarAguasobranteId(idAguasobrante).getIdaguasobrante());
            Tarifassobrante idTar = ct.tarifassobranteJpacontrolador.findTarifassobrante(ct.buscarIdTarifassobrante(idTarifas).getIdtarifassobrante());
            
            dat.setIdtarifassobrante(idTar);
            dat.setAniomes(anioMes);
            dat.setIdaguasobrante(idmed);
            dat.setSubtotal(subtotal);
            dat.setTotal(total);
            dat.setObservacion(observacion);
            detallefacturasobranteJpacontrolador.edit(dat);
            JOptionPane.showMessageDialog(null, "Se Modifico exitosamente", "Información", 1);
            
        } catch (Exception e) {
        }
        return true;
    }
    
    public void eliminarDetallefacturasobrante(int idetallefacturasobrante) throws NonexistentEntityException, IllegalOrphanException {
        
        try {
            detallefacturasobranteJpacontrolador.destroy(idetallefacturasobrante);
            JOptionPane.showMessageDialog(null, "Se Elimino exitosamente", "Información", 1);
        } catch (NonexistentEntityException ex) {
        }
        
    }
    
    public Detallefacturasobrante buscarIdDetallefacturasobrante(int idDetallefacturasobrante) {
        
        for (Detallefacturasobrante dat : getDetallefacturaSobrante()) {
            //System.out.println(dat);
            if (dat.getIddetallefacsobrante().equals(idDetallefacturasobrante)) {
                //System.out.println(dat);
                return dat;
            }
        }
        return null;
    }
    
    public Detallefacturasobrante buscarNumMedDetallefacturasobrante(int codigoaguasobrante) {
        
        for (Detallefacturasobrante dat : getDetallefacturaSobrante()) {
            if (dat.getIdaguasobrante().getCodigoaguasobrante().equals(codigoaguasobrante)) {
                return dat;
            }
        }
        return null;
    }
    
    public Detallefacturasobrante buscarNumAguasobranteAnioMes(int codigoaguasobrante, String anioMes) {
        
        for (Detallefacturasobrante dat : getDetallefacturaSobrante()) {
            if (dat.getIdaguasobrante().getCodigoaguasobrante().equals(codigoaguasobrante) && dat.getAniomes().equals(anioMes)) {
                return dat;
            }
        }
        return null;
    }
    
    public int buscarAnioMes(String anioMes) {
        for (Detallefacturasobrante dat : getDetallefacturaSobrante()) {
            if (dat.getAniomes().equals(anioMes)) {
                return 1;
            }
        }
        return 0;
    }
    
    public int buscardiferentes(int id, String anioMes) {
        List<Detallefacturasobrante> datos = new ArrayList<Detallefacturasobrante>();
        for (Detallefacturasobrante dat : getDetallefacturaSobrante()) {
            if (anioMes.equals(dat.getAniomes())) {
                datos.add(dat);
            }
        }
        for (Detallefacturasobrante dat : datos) {
            if (dat.getIdaguasobrante().getIdaguasobrante().equals(id)) {
                return dat.getIdaguasobrante().getIdaguasobrante();
            }
        }
        return 0;
    }
    ///////////////////sin retorno
    public int medidaAnterior(int idaguasobrante) {
        int r = 0;
        int r2 = 0;
        for (Detallefacturasobrante dat : getDetallefacturaSobrante()) {
            if (dat.getIdaguasobrante().getIdaguasobrante()== idaguasobrante && (dat.getIddetallefacsobrante()> r2)) {
//                if (dat.getIddetallefac() > r) {
                r2 = dat.getIddetallefacsobrante();
                //r = dat.getMedidaact();
//                }
            }
        }
        return r;
    }
    
    public TableModel tablaDetalles(JTable tabla, int codigoAguaSobrante) {
        
        DefaultTableModel dtm = (DefaultTableModel) tabla.getModel();
        dtm.setRowCount(0);
        diseñoTabla(tabla);
        float subtotal = 0;
        float iva = 0;
        float total = 0;
        for (Detallefacturasobrante u : getDetallefacturaSobrante()) {
            if (u.getObservacion().equals("NO") && u.getIdaguasobrante().getCodigoaguasobrante() == codigoAguaSobrante) {
                subtotal = subtotal + u.getTotal();
                iva = u.getIdtarifassobrante().getIva();
                dtm.addRow(new Object[]{
                    u.getIddetallefacsobrante(),
                    u.getAniomes(),
                    u.getSubtotal(),
                    u.getTotal()
                });
            }
        }
        FrmFactura.tabla2.setValueAt(subtotal, 0, 1);
        FrmFactura.tabla2.setValueAt(iva, 1, 0);
        FrmFactura.tabla2.setValueAt(iva * subtotal, 1, 1);
        total = (iva * subtotal) + subtotal;
        float recon = Float.valueOf(FrmFactura.txtIdCorte.getText());
        FrmFactura.tabla2.setValueAt(total, 2, 1);
        FrmFactura.txtTotalgeneral.setText(String.valueOf(total + recon));
        FrmFactura.txtTotalgeneral.setText(String.valueOf(total + recon));
        return dtm;
    }
    
    public void diseñoTabla(JTable tabla) {
//        tabla.getColumnModel().getColumn(0).setPreferredWidth(15);
        tabla.getColumn(tabla.getColumnName(0)).setWidth(0);
        tabla.getColumn(tabla.getColumnName(0)).setMinWidth(0);
        tabla.getColumn(tabla.getColumnName(0)).setMaxWidth(0);
        tabla.getColumnModel().getColumn(0).setResizable(false);
        tabla.getColumnModel().getColumn(1).setPreferredWidth(180);
        tabla.getColumnModel().getColumn(2).setPreferredWidth(130);
        tabla.getColumnModel().getColumn(3).setPreferredWidth(100);
        tabla.getColumnModel().getColumn(4).setPreferredWidth(90);
        tabla.getColumnModel().getColumn(5).setPreferredWidth(100);
        tabla.getColumnModel().getColumn(6).setPreferredWidth(140);
        tabla.getColumnModel().getColumn(7).setPreferredWidth(180);
        tabla.getColumnModel().getColumn(8).setPreferredWidth(70);
        
    }
    
    public boolean modificarTransaccion(int idDetallefactura, String observacion) {
        try {
            Detallefacturasobrante dat = detallefacturasobranteJpacontrolador.findDetallefacturasobrante(idDetallefactura);
            if (dat == null) {
                return false;
            }
            dat.setObservacion(observacion);
            detallefacturasobranteJpacontrolador.edit(dat);
            
        } catch (Exception e) {
        }
        return true;
    }
    
    
    public List<Detallefacturasobrante> cargarBusquedaAnioMesDatos(String anioMes){
        List<Detallefacturasobrante> detallelist = new ArrayList<>();
        
        for (Detallefacturasobrante u : getDetallefacturaSobrante()) {
            if (u.getAniomes().equals(anioMes)) {
                detallelist.add(u);
            }
        }
        return detallelist;
    }
    //modificado
    public void cargarBusquedaAnioMes(JTable tabla, String anioMes) {
        DefaultTableModel modelo = new DefaultTableModel();
        tabla.setModel(modelo);
        modelo.addColumn("N°");
        modelo.addColumn("Tarifa");
        modelo.addColumn("Num. Medidor");
        modelo.addColumn("Usuario");
        modelo.addColumn("Fecha Correspondiente");
        modelo.addColumn("Medida Anterior");
        modelo.addColumn("Medida Actual");
        
        //Medidor med=cm.medidorJpacontrolador.findMedidor(cm.buscarMedidorId(idMedidor));
        Object[] fila = new Object[7];
        for (Detallefacturasobrante u : getDetallefacturaSobrante()) {
            if (u.getAniomes().equals(anioMes)) {
                fila[0] = u.getIddetallefacsobrante();
                fila[1] = u.getIdtarifassobrante().getDescripcion();
                fila[2] = u.getIdaguasobrante().getCodigoaguasobrante();
                fila[3] = u.getIdaguasobrante().getIdusuario().getPrimerapellido() + "  " + u.getIdaguasobrante().getIdusuario().getSegundoapellido() + "  "
                        + u.getIdaguasobrante().getIdusuario().getPrimernombre() + "  " + u.getIdaguasobrante().getIdusuario().getSegundonombre();
                fila[4] = u.getAniomes();
                
                modelo.addRow(fila);
            }
        }
        tabla.getTableHeader().getColumnModel().getColumn(0).setMinWidth(0);
        tabla.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(50);
        
        tabla.getTableHeader().getColumnModel().getColumn(1).setMaxWidth(60);
        tabla.getTableHeader().getColumnModel().getColumn(2).setMaxWidth(90);
        tabla.getTableHeader().getColumnModel().getColumn(3).setMaxWidth(200);
        tabla.getTableHeader().getColumnModel().getColumn(4).setMaxWidth(170);
        tabla.getTableHeader().getColumnModel().getColumn(5).setMaxWidth(100);
        tabla.getTableHeader().getColumnModel().getColumn(6).setMaxWidth(100);
    }
    
    public void tablaCorte(JTable tabla) {
        
        mod = new DefaultTableModel();
        tabla.setModel(mod);
        Object[] fila = new Object[4];
        mod.addColumn("id");
        mod.addColumn("idNum");
        mod.addColumn("Num medidor");
        mod.addColumn("observacion");
        
        for (Detallefacturasobrante u : getDetallefacturaSobrante()) {
            //System.out.println(u.getIdaguasobrante().getCodigoaguasobrante()+ " " + u.getIdmedidor().getIdmedidor() + u.getObservacion());
            if (numContar(u.getIdaguasobrante().getIdaguasobrante()) > 3 && u.getObservacion().equals("NO")) {
                //System.out.println(u.getIdmedidor().getNummedidor() + " " + u.getIdmedidor().getIdmedidor());
                fila[0] = u.getIddetallefacsobrante();
                fila[1] = u.getIdaguasobrante().getIdaguasobrante();
                fila[2] = u.getIdaguasobrante().getCodigoaguasobrante();
                fila[3] = u.getObservacion();
            }
        }
        mod.addRow(fila);
        
    }
    
    public int numContar(int idNumMedidor) {
        
        int papitas = 0;
        for (Detallefacturasobrante dat : getDetallefacturaSobrante()) {
            if (dat.getIdaguasobrante().getIdaguasobrante().equals(idNumMedidor) && dat.getObservacion().equals("NO")) {
                papitas = papitas + 1;
            }
        }
        return papitas;
    }
    public boolean ingresoMesMayor(int idaguasobrante, int año, int mes) {
        boolean retorno = false;
        String re = "0-0";
        int re2 = 0;
        classAguasobrante cm = new classAguasobrante();
        for (Detallefacturasobrante dat : getDetallefacturaSobrante()) {
            if (dat.getIdaguasobrante().getIdaguasobrante()== idaguasobrante && (dat.getIddetallefacsobrante()> re2)) {
                re2 = dat.getIddetallefacsobrante();
                re = dat.getAniomes();
            }
        }
        
        String r = "";
        String fechaing;
        if (mes < 10) {
            fechaing = año + "0" + mes;
        } else {
            fechaing = año + "" + mes;
        }
        int añoIng = Integer.valueOf(fechaing);
        String r1[] = re.split("-");
        
        String fechaRec = r1[0] + r1[1];
        if (Integer.valueOf(r1[1]) < 10) {
            fechaRec = r1[0] + "0" + r1[1];
        } else {
            fechaRec = r1[0] + r1[1];
        }
        int añoRec = Integer.valueOf(fechaRec);
        
        if (añoIng >= añoRec) {
            retorno = true;
            r = añoIng + "  " + añoRec;
        } else {
            retorno = false;
            JOptionPane.showMessageDialog(null, "Medidor Tiene Registro Superior \nal que desea ingresar\n "
                    + "Elimine el Registro " + re + " y vuleva a intentar",
                    "Error de Ingreso", JOptionPane.ERROR_MESSAGE);
            
        }
        return retorno;
    }
    
    public List<Aguasobrante> listaMed(String aniomes) {
        try {
            List<Detallefacturasobrante> datosDet = new ArrayList<Detallefacturasobrante>();
            for (Detallefacturasobrante dat : getDetallefacturaSobrante()) {
                if (aniomes.equals(dat.getAniomes())) {
                    datosDet.add(dat);
                }
            }
            
            int i = 0;
            classAguasobrante cm = new classAguasobrante();
            List<Aguasobrante> datos = new ArrayList<Aguasobrante>();
            for (Aguasobrante e : cm.ListaOrdenada()) {
                if (!e.getEstado().equals("INACTIVO")) {
                    for (Detallefacturasobrante dat : datosDet) {
                        if (dat.getIdaguasobrante().getIdaguasobrante().equals(e.getIdaguasobrante())) {
                            i = dat.getIdaguasobrante().getIdaguasobrante();
                        }
                    }
                    if (i != e.getIdaguasobrante()) {
                        datos.add(e);
                    }
                }
            }
            return datos;
        } catch (Exception e) {
        }
        return null;
    }
    
    public void cargarTablaMedidorDetalle(JTable tabla, String aniomes, List<Aguasobrante> listaMed) {
        tabla.setDefaultRenderer(Object.class, new Render());
        DefaultTableModel modelo = new DefaultTableModel(){
            public boolean isCellEditable(int row, int column){
                return false;
            }
        }; 
        
        //DefaultTableModel modelo = new DefaultTableModel();
        Object[] fila = new Object[6];
        //modelo.addColumn("Nro");
        modelo.addColumn("#");
        modelo.addColumn("Codigo usuario");
        modelo.addColumn("Cedula");
        modelo.addColumn("Usuario");
        modelo.addColumn("Apodo");
        modelo.addColumn("estado");
        /*
        tabla.getTableHeader().getColumnModel().getColumn(0).setMinWidth(0);
        tabla.getTableHeader().getColumnModel().getColumn(0).setMinWidth(0);
        tabla.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
        tabla.getTableHeader().getColumnModel().getColumn(1).setMinWidth(45);
        tabla.getTableHeader().getColumnModel().getColumn(1).setMaxWidth(90);
        tabla.getTableHeader().getColumnModel().getColumn(2).setMinWidth(60);
        tabla.getTableHeader().getColumnModel().getColumn(2).setMaxWidth(65);
        tabla.getTableHeader().getColumnModel().getColumn(5).setMinWidth(60);
        tabla.getTableHeader().getColumnModel().getColumn(5).setMaxWidth(85);
        tabla.getTableHeader().getColumnModel().getColumn(6).setMinWidth(40);
        tabla.getTableHeader().getColumnModel().getColumn(6).setMaxWidth(45);
        */
        for (Aguasobrante u : listaMed) {
            fila[0] = u.getIdaguasobrante();
            fila[1] = u.getCodigoaguasobrante();
            fila[2] = u.getIdusuario().getRucci();
            fila[3] = u.getIdusuario().getPrimerapellido() + "  " + u.getIdusuario().getSegundoapellido() + "  "
                    + u.getIdusuario().getPrimernombre() + "  " + u.getIdusuario().getSegundonombre();
            fila[4] = u.getIdusuario().getApadosn();
            fila[5] = u.getEstado();
            modelo.addRow(fila);
            
        }
        tabla.setModel(modelo);
    }
    
}
