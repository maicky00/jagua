/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Formularios.FrmAsistencia;
import Formularios.FrmPagosAsistemcia;
import entidades.Asistencia;
import entidades.Medidor;
import entidades.Planificacion;
import entidades.Usuarios;
import entidadesCruds.AsistenciaJpaController;
import entidadesCruds.PagosasistenciaJpaController;
import entidadesCruds.exceptions.IllegalOrphanException;
import entidadesCruds.exceptions.NonexistentEntityException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

///**
// *
// * @author JC-PC
// */
public class classAsistencia {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("japPU");
    public AsistenciaJpaController asistenciaJpacontrolador = new AsistenciaJpaController(emf);
    classPlanificacion cp = new classPlanificacion();
    classMedidor cm = new classMedidor();
//    classusuario cu = new classusuario();
    DefaultTableModel modelo;

    public List<Asistencia> getAsistencia() {
        return asistenciaJpacontrolador.findAsistenciaEntities();
    }

    public void guardarAsistencia(int idPlanificacion, int NumMedidor, String asistencia, float valorMulta, String descripcion, String obSservacion) {
        int i = 0;
        Asistencia a = new Asistencia();
//        try {
        for (Asistencia as : getAsistencia()) {
//            Medidor idMedidor = cm.medidorJpacontrolador.findMedidor(cm.buscarMedidorNumM(NumMedidor).getIdmedidor());
//            Planificacion idP = cp.planificacionJpacontrolador.findPlanificacion(idPlanificacion);

            if (as.getIdmedidor().getIdmedidor().equals(cm.buscarMedidorNumM(NumMedidor).getIdmedidor()) && as.getIdplanificacion().getIdplanificacion().equals(cp.buscarIdPlanificacion(idPlanificacion).getIdplanificacion())) {
                i = 1;
                a = as;
                break;
            }
        }
        if (i == 1) {
            a = null;
            JOptionPane.showMessageDialog(null, "Numero Medidor/Usuario Existente \nNo se pudo Guardar", "Informacion", 1);
        } else {
            Planificacion idPlan = cp.planificacionJpacontrolador.findPlanificacion(cp.buscarIdPlanificacion(idPlanificacion).getIdplanificacion());
            Medidor idMed = cm.medidorJpacontrolador.findMedidor(cm.buscarMedidorNumM(NumMedidor).getIdmedidor());
            Asistencia dat = new Asistencia();
            dat.setIdplanificacion(idPlan);
            dat.setIdmedidor(idMed);
            dat.setAsistencia(asistencia);
            dat.setValormulta(valorMulta);
            dat.setDescripcion(descripcion);
            dat.setObsevacion(obSservacion);
            asistenciaJpacontrolador.create(dat);
            JOptionPane.showMessageDialog(null, "Guardado", "Información", 1);
        }

//        } catch (Exception e) {
//
//        }
    }

    public static void main(String[] args) {
        classAsistencia ca = new classAsistencia();
//        ca.guardarAsistencia(1, 2, "NO", 20, "Hola mundo");
    }

    public boolean modificarAsistencia(int idAsistencia, int idPlanificacion, int NumMedidor, String asistencia, float valorMulta, String descripcion, String obSservacion) {
        try {
            Asistencia dat = asistenciaJpacontrolador.findAsistencia(idAsistencia);
            if (dat == null) {
                return false;
            }
            Planificacion idPlan = cp.planificacionJpacontrolador.findPlanificacion(idPlanificacion);
            Medidor idMed = cm.medidorJpacontrolador.findMedidor(cm.buscarMedidorNumM(NumMedidor).getIdmedidor());
            dat.setIdplanificacion(idPlan);
            dat.setIdmedidor(idMed);
            dat.setAsistencia(asistencia);
            dat.setValormulta(valorMulta);
            dat.setDescripcion(descripcion);
            dat.setObsevacion(obSservacion);
            asistenciaJpacontrolador.edit(dat);
            JOptionPane.showMessageDialog(null, "Se Modifico exitosamente", "Información", 1);

        } catch (Exception e) {
        }
        return true;
    }

    public boolean modificarAsistencia(int idAsistencia, String obSservacion) {
        try {
            Asistencia dat = asistenciaJpacontrolador.findAsistencia(idAsistencia);
            if (dat == null) {
                return false;
            }

            dat.setObsevacion(obSservacion);
            asistenciaJpacontrolador.edit(dat);
        } catch (Exception e) {
        }
        return true;
    }

    public void eliminarAsistencia(int idAsistencia) throws NonexistentEntityException  {


            asistenciaJpacontrolador.destroy(idAsistencia);
            JOptionPane.showMessageDialog(null, "Se Elimino exitosamente", "Información", 1);
  

    }

    public Asistencia buscarIdAsistencia(int idAsistencia) {

        for (Asistencia dat : getAsistencia()) {
            if (dat.getIdasistencia().equals(idAsistencia)) {
                return dat;
            }
        }
        return null;
    }

    public Asistencia buscarNumMed(int idNumMed) {

        for (Asistencia dat : getAsistencia()) {
            if (dat.getIdmedidor().getNummedidor().equals(idNumMed)) {
                return dat;
            }
        }
        return null;
    }

    public int buscarMultaPagado(int idNumMed) {
        int i = 0;
        for (Asistencia dat : getAsistencia()) {
            if (dat.getIdmedidor().getNummedidor().equals(idNumMed)
                    && dat.getObsevacion().equals("NO")) {
                i++;
            }
        }
        return i;
    }

    public void cargarTablaAsistencia(JTable tabla, int idPlan) {

        modelo = new DefaultTableModel();
        tabla.setModel(modelo);
        Object[] fila = new Object[9];
        modelo.addColumn("id");
        modelo.addColumn("idPlan");
        modelo.addColumn("idMed");

        modelo.addColumn("Usuario");
        modelo.addColumn("cedula");
        modelo.addColumn("Asistencia");

        modelo.addColumn("multa");
        modelo.addColumn("descripcion");
        modelo.addColumn("Nro Medidor");

//        Medidor med=cm.medidorJpacontrolador.findMedidor(cm.buscarMedidorId(idMedidor));
        for (Asistencia a : getAsistencia()) {
            if (a.getIdplanificacion().getIdplanificacion() == idPlan) {
                fila[0] = a.getIdasistencia();
                fila[1] = a.getIdplanificacion().getIdplanificacion();
                fila[2] = a.getIdmedidor().getIdmedidor();

                fila[3] = a.getIdmedidor().getIdusuario().getPrimernombre() + " " + a.getIdmedidor().getIdusuario().getPrimerapellido()
                        + " " + a.getIdmedidor().getIdusuario().getSegundoapellido();
                fila[4] = a.getIdmedidor().getIdusuario().getRucci();
                fila[5] = a.getAsistencia();

                fila[6] = a.getValormulta();
                fila[7] = a.getDescripcion();
                fila[8] = a.getIdmedidor().getNummedidor();

                FrmAsistencia.tab.getTableHeader().getColumnModel().getColumn(0).setMinWidth(35);
                FrmAsistencia.tab.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(35);
                FrmAsistencia.tab.getTableHeader().getColumnModel().getColumn(1).setMinWidth(0);
                FrmAsistencia.tab.getTableHeader().getColumnModel().getColumn(1).setMaxWidth(0);
                FrmAsistencia.tab.getTableHeader().getColumnModel().getColumn(2).setMinWidth(0);
                FrmAsistencia.tab.getTableHeader().getColumnModel().getColumn(2).setMaxWidth(0);
                FrmAsistencia.tab.getTableHeader().getColumnModel().getColumn(4).setMinWidth(0);
                FrmAsistencia.tab.getTableHeader().getColumnModel().getColumn(4).setMaxWidth(0);
                FrmAsistencia.tab.getTableHeader().getColumnModel().getColumn(5).setMinWidth(60);
                FrmAsistencia.tab.getTableHeader().getColumnModel().getColumn(5).setMaxWidth(65);
                FrmAsistencia.tab.getTableHeader().getColumnModel().getColumn(6).setMinWidth(40);
                FrmAsistencia.tab.getTableHeader().getColumnModel().getColumn(6).setMaxWidth(45);
                FrmAsistencia.tab.getTableHeader().getColumnModel().getColumn(7).setMinWidth(0);
                FrmAsistencia.tab.getTableHeader().getColumnModel().getColumn(7).setMaxWidth(0);

                modelo.addRow(fila);
            }
        }
    }

    public TableModel tablaAsistencias(JTable tabla, int numMedidor) {

        DefaultTableModel dtm = (DefaultTableModel) tabla.getModel();
        dtm.setRowCount(0);
        float subtotal = 0;
//        float iva = 0;
//        float total = 0;

        for (Asistencia u : getAsistencia()) {
            if (u.getObsevacion().equals("NO")
                    //                    && u.getAsistencia().equals("NO")
                    && u.getIdmedidor().getNummedidor() == numMedidor) {
                subtotal = subtotal + u.getValormulta();
//                iva = u.getIdtarifas().getIva();
                Date fechaActual = u.getIdplanificacion().getFecha();
                SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
                String fec = formato.format(fechaActual);
                dtm.addRow(new Object[]{
                    u.getIdasistencia(),
                    u.getValormulta(),
                    u.getObsevacion(),
                    fec,
                    u.getIdplanificacion().getTipoplanificacion(),
                    u.getIdplanificacion().getLugar()
                });
            }
        }
        FrmPagosAsistemcia.tabla2.setValueAt(subtotal, 0, 1);
//        FrmFactura.tabla2.setValueAt(iva, 1, 0);
//        FrmFactura.tabla2.setValueAt(iva * subtotal, 1, 1);
//        total = (iva * subtotal) + subtotal;
//        FrmPagosAsistemcia.tabla2.setValueAt(total, 2, 1);
        return dtm;
    }

    public int buscardiferentes(int idMedidor, int idPlan) {

        for (Asistencia dat : getAsistencia()) {
            if (dat.getIdmedidor().getIdmedidor().equals(idMedidor) && dat.getIdplanificacion().getIdplanificacion().equals(idPlan)) {
                return dat.getIdmedidor().getIdmedidor();
            }
        }
        return 0;
    }
}
