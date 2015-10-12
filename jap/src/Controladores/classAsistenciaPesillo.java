/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Formularios.FrmAsistenciaPesillo;
import Formularios.FrmAsistenciaPlanPesillo;
import Formularios.frmPagoPesillo;
import static Formularios.frmPagoPesillo.lblfecha;
import entidades.Asistencia;
import entidades.Asistenciapesillo;
import entidades.Medidor;
import entidades.Planificacionpesillo;
import entidadesCruds.AsistenciapesilloJpaController;
import entidadesCruds.exceptions.IllegalOrphanException;
import entidadesCruds.exceptions.NonexistentEntityException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Marco
 */
public class classAsistenciaPesillo {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("japPU");
    public AsistenciapesilloJpaController asistenciapesilloJpaController = new AsistenciapesilloJpaController(emf);
    classPlanificacionPesillo cpp = new classPlanificacionPesillo();
    classMedidor cm = new classMedidor();
    DefaultTableModel modelo;

    public List<Asistenciapesillo> getAsistenciaPesillo() {
        return asistenciapesilloJpaController.findAsistenciapesilloEntities();
    }

    public void guardarAsistenciaPesillo(int numMedidor, int idPlanificacionPes, String asistencia, float valorMulta, String descripcion, String observacion) {
        int i = 0;
        Asistenciapesillo ap = new Asistenciapesillo();
        for (Asistenciapesillo as : getAsistenciaPesillo()) {
            if (as.getIdmedidor().getIdmedidor().equals(cm.buscarMedidorNumM(numMedidor).getIdmedidor()) && as.getIdplanificacionpesillo().getIdplanificacionpesillo().equals(cpp.buscarIdPlanPes(idPlanificacionPes).getIdplanificacionpesillo())) {
                i = 1;
                ap = as;
                break;
            }

        }
        if (i == 1) {
            ap = null;
            JOptionPane.showMessageDialog(null, "Numero Medidor/Usuario Existente \nNo se pudo Guardar", "Informacion", 1);
        } else {
            Planificacionpesillo idPlanPes = cpp.planificacionPesilloJpacontrolador.findPlanificacionpesillo(cpp.buscarIdPlanPes(idPlanificacionPes).getIdplanificacionpesillo());
            Medidor idMed = cm.medidorJpacontrolador.findMedidor(cm.buscarMedidorNumM(numMedidor).getIdmedidor());
            Asistenciapesillo dat = new Asistenciapesillo();
            dat.setIdmedidor(idMed);
            dat.setIdplanificacionpesillo(idPlanPes);
            dat.setAsistencia(asistencia);
            dat.setValormulta(valorMulta);
            dat.setDescripcion(descripcion);
            dat.setObservacion(observacion);
            asistenciapesilloJpaController.create(dat);
            JOptionPane.showMessageDialog(null, "Guardado", "Información", 1);
        }
    }

    public boolean modificarAsistenciaPesillo(int idAsistenciaPes, int numMedidor, int idPlanificacionPes, String asistencia, float valorMulta, String descripcion, String observacion) {
        try {
            Asistenciapesillo dat = asistenciapesilloJpaController.findAsistenciapesillo(idAsistenciaPes);
            if (dat == null) {
                return false;
            }
            Planificacionpesillo idPlanPes = cpp.planificacionPesilloJpacontrolador.findPlanificacionpesillo(idPlanificacionPes);
            Medidor idMed = cm.medidorJpacontrolador.findMedidor(cm.buscarMedidorNumM(numMedidor).getIdmedidor());
            dat.setIdmedidor(idMed);
            dat.setIdplanificacionpesillo(idPlanPes);
            dat.setAsistencia(asistencia);
            dat.setValormulta(valorMulta);
            dat.setDescripcion(descripcion);
            dat.setObservacion(observacion);
            asistenciapesilloJpaController.edit(dat);
            JOptionPane.showMessageDialog(null, "Se Modifico exitosamente", "Información", 1);

        } catch (Exception e) {
        }
        return true;
    }

    public boolean modificarAsistenciaPesillo(int idAsistencia, String observacion) {
        try {
            Asistenciapesillo dat = asistenciapesilloJpaController.findAsistenciapesillo(idAsistencia);
            if (dat == null) {
                return false;
            }
            dat.setObservacion(observacion);
            asistenciapesilloJpaController.edit(dat);
        } catch (Exception e) {
        }
        return true;
    }

    public void eliminarAsistenciaPesillo(int idAsistenciaPes) throws NonexistentEntityException {

        asistenciapesilloJpaController.destroy(idAsistenciaPes);
        JOptionPane.showMessageDialog(null, "Se Elimino exitosamente", "Información", 1);

    }

    public Asistenciapesillo buscarIdAsistenciaPes(int idAsistenciaPes) {
        for (Asistenciapesillo dat : getAsistenciaPesillo()) {
            if (dat.getIdasistenciapesillo().equals(idAsistenciaPes)) {
                return dat;
            }
        }
        return null;
    }

    public Asistenciapesillo buscarNumMed(int idNumMed) {
        for (Asistenciapesillo dat : getAsistenciaPesillo()) {
            if (dat.getIdmedidor().getNummedidor().equals(idNumMed)) {
                return dat;
            }
        }
        return null;
    }

    public void cargarTablaAsistenciaPesillo(JTable tabla, int idPlan) {

        modelo = new DefaultTableModel();
        tabla.setModel(modelo);
        Object[] fila = new Object[10];
        modelo.addColumn("id");
        modelo.addColumn("idMed");
        modelo.addColumn("idPlan");
        modelo.addColumn("Asistencia");
        modelo.addColumn("multa");
        modelo.addColumn("descripcion");
        modelo.addColumn("observacion");
        modelo.addColumn("Num. Medidor");
        modelo.addColumn("Usuario");
        modelo.addColumn("Ruc/CI");

//        Medidor med=cm.medidorJpacontrolador.findMedidor(cm.buscarMedidorId(idMedidor));
        for (Asistenciapesillo a : getAsistenciaPesillo()) {
            if (a.getIdplanificacionpesillo().getIdplanificacionpesillo() == idPlan) {

                fila[0] = a.getIdasistenciapesillo();
                fila[1] = a.getIdmedidor().getIdmedidor();
                fila[2] = a.getIdplanificacionpesillo().getIdplanificacionpesillo();
                fila[3] = a.getAsistencia();

                fila[4] = a.getValormulta();
                fila[5] = a.getDescripcion();
                fila[6] = a.getObservacion();
                fila[7] = a.getIdmedidor().getNummedidor();
                String nom = a.getIdmedidor().getIdusuario().getPrimerapellido() + "  "
                        + a.getIdmedidor().getIdusuario().getSegundoapellido() + "  "
                        + a.getIdmedidor().getIdusuario().getPrimernombre() + "  "
                        + a.getIdmedidor().getIdusuario().getSegundonombre();
                fila[8] = nom;
                fila[9] = a.getIdmedidor().getIdusuario().getRucci();
                FrmAsistenciaPesillo.tab.getTableHeader().getColumnModel().getColumn(0).setMinWidth(0);
                FrmAsistenciaPesillo.tab.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
                FrmAsistenciaPesillo.tab.getTableHeader().getColumnModel().getColumn(1).setMinWidth(0);
                FrmAsistenciaPesillo.tab.getTableHeader().getColumnModel().getColumn(1).setMaxWidth(0);
                FrmAsistenciaPesillo.tab.getTableHeader().getColumnModel().getColumn(2).setMinWidth(0);
                FrmAsistenciaPesillo.tab.getTableHeader().getColumnModel().getColumn(2).setMaxWidth(0);
                FrmAsistenciaPesillo.tab.getTableHeader().getColumnModel().getColumn(6).setMinWidth(0);
                FrmAsistenciaPesillo.tab.getTableHeader().getColumnModel().getColumn(6).setMaxWidth(0);
//                FrmAsistencia.tab.getTableHeader().getColumnModel().getColumn(0).setMinWidth(35);
//                FrmAsistencia.tab.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(35);
//                FrmAsistencia.tab.getTableHeader().getColumnModel().getColumn(1).setMinWidth(0);
//                FrmAsistencia.tab.getTableHeader().getColumnModel().getColumn(1).setMaxWidth(0);
//                FrmAsistencia.tab.getTableHeader().getColumnModel().getColumn(2).setMinWidth(0);
//                FrmAsistencia.tab.getTableHeader().getColumnModel().getColumn(2).setMaxWidth(0);
//                FrmAsistencia.tab.getTableHeader().getColumnModel().getColumn(4).setMinWidth(0);
//                FrmAsistencia.tab.getTableHeader().getColumnModel().getColumn(4).setMaxWidth(0);
//                FrmAsistencia.tab.getTableHeader().getColumnModel().getColumn(5).setMinWidth(60);
//                FrmAsistencia.tab.getTableHeader().getColumnModel().getColumn(5).setMaxWidth(65);
//                FrmAsistencia.tab.getTableHeader().getColumnModel().getColumn(6).setMinWidth(40);
//                FrmAsistencia.tab.getTableHeader().getColumnModel().getColumn(6).setMaxWidth(45);
//                FrmAsistencia.tab.getTableHeader().getColumnModel().getColumn(7).setMinWidth(0);
//                FrmAsistencia.tab.getTableHeader().getColumnModel().getColumn(7).setMaxWidth(0);
                modelo.addRow(fila);
            }
        }
    }

    public void diseño(JTable table) {
        table.getColumn(table.getColumnName(0)).setWidth(0);
        table.getColumn(table.getColumnName(0)).setMinWidth(0);
        table.getColumn(table.getColumnName(0)).setMaxWidth(0);

    }

    public TableModel tablaAsistenciaPesillo(JTable tabla, int numMedidor) {

        DefaultTableModel dtm = (DefaultTableModel) tabla.getModel();
        dtm.setRowCount(0);
        diseño(tabla);
        float subtotal = 0;
//        float iva = 0;
//        float total = 0;

        for (Asistenciapesillo u : getAsistenciaPesillo()) {
            if (u.getObservacion().equals("NO")
                    && u.getAsistencia().equals("NO")
                    && u.getIdmedidor().getNummedidor() == numMedidor) {
                subtotal = subtotal + u.getValormulta();
//                iva = u.getIdtarifas().getIva();
                Date fechaActual = u.getIdplanificacionpesillo().getFecha();
                SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
                String fec = formato.format(fechaActual);
                dtm.addRow(new Object[]{
                    u.getIdasistenciapesillo(),
                    u.getValormulta(),
                    u.getObservacion(),
                    fec,
                    u.getIdplanificacionpesillo().getTipoplanpesillo(),
                    u.getIdplanificacionpesillo().getLugar()
                });
            }
        }
        frmPagoPesillo.tabla2.setValueAt(subtotal, 0, 1);
        return dtm;
    }

    public int buscardiferentes(int idMedidor, int idPlan) {

        for (Asistenciapesillo dat : getAsistenciaPesillo()) {
            if (dat.getIdmedidor().getIdmedidor().equals(idMedidor) && dat.getIdplanificacionpesillo().getIdplanificacionpesillo().equals(idPlan)) {
                return dat.getIdmedidor().getIdmedidor();
            }
        }
        return 0;
    }

    public void cargarTablaMedidor2(JTable tabla, int idPlan) {
        classMedidor cm = new classMedidor();
        List<Asistenciapesillo> datosDet = new ArrayList<Asistenciapesillo>();
        for (Asistenciapesillo dat : getAsistenciaPesillo()) {
            if (idPlan == (dat.getIdplanificacionpesillo().getIdplanificacionpesillo())) {
                datosDet.add(dat);
            }
        }
        int i = 0;
        List<Medidor> datos = new ArrayList<Medidor>();
        for (Medidor e : cm.ListaOrdenada()) {
            for (Asistenciapesillo dat : datosDet) {
                if (dat.getIdmedidor().getIdmedidor().equals(e.getIdmedidor())) {
                    i = dat.getIdmedidor().getIdmedidor();
                }
            }
            if (i != e.getIdmedidor()) {
                datos.add(e);
            }
        }

        DefaultTableModel modelo = new DefaultTableModel();
        tabla.setModel(modelo);
        Object[] fila = new Object[4];
        modelo.addColumn("Nro medidor");
        modelo.addColumn("Cedula");
        modelo.addColumn("Usuario");
        modelo.addColumn("Apodo");

        for (Medidor u : datos) {
                fila[0] = u.getNummedidor();
                fila[1] = u.getIdusuario().getRucci();
                fila[2] = u.getIdusuario().getPrimerapellido() + "  " + u.getIdusuario().getSegundoapellido() + "  "
                        + u.getIdusuario().getPrimernombre();
                fila[3] = u.getIdusuario().getApadosn();

                modelo.addRow(fila);
        }
    }

}
