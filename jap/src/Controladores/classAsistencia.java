/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Formularios.FrmAsistencia;
import entidades.Asistencia;
import entidades.Medidor;
import entidades.Planificacion;
import entidades.Usuarios;
import entidadesCruds.AsistenciaJpaController;
import entidadesCruds.PagosasistenciaJpaController;
import entidadesCruds.exceptions.IllegalOrphanException;
import entidadesCruds.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author JC-PC
 */
public class classAsistencia {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("japPU");
    public AsistenciaJpaController asistenciaJpacontrolador = new AsistenciaJpaController(emf);
    classPlanificacion cp = new classPlanificacion();
    classMedidor cm = new classMedidor();
    classusuario cu = new classusuario();
    DefaultTableModel modelo;

    public List<Asistencia> getAsistencia() {
        return asistenciaJpacontrolador.findAsistenciaEntities();
    }

    public void guardarAsistencia(int idPlanificacion, int NumMedidor, String asistencia, float valorMulta, String descripcion) {
        int i = 0;
        Asistencia a = new Asistencia();
        try {
            Medidor idMedidor = cm.medidorJpacontrolador.findMedidor(cm.buscarMedidorNumM(NumMedidor).getIdmedidor());
            Planificacion idP = cp.planificacionJpacontrolador.findPlanificacion(idPlanificacion);

            for (Asistencia as : getAsistencia()) {
                if (as.getIdmedidor().getIdmedidor().equals(idMedidor.getIdmedidor()) && as.getIdplanificacion().getIdplanificacion().equals(idP.getIdplanificacion())) {
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
                asistenciaJpacontrolador.create(dat);
                JOptionPane.showMessageDialog(null, "Guardado", "Información", 1);
            }

        } catch (Exception e) {

        }
    }

    public static void main(String[] args) {
        classAsistencia ca = new classAsistencia();
        ca.guardarAsistencia(1, 2, "NO", 20, "Hola mundo");
    }

    public boolean modificarAsistencia(int idAsistencia, int idPlanificacion, int NumMedidor, String asistencia, float valorMulta, String descripcion) {
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
            asistenciaJpacontrolador.edit(dat);
            JOptionPane.showMessageDialog(null, "Se Modifico exitosamente", "Información", 1);

        } catch (Exception e) {
        }
        return true;
    }

    public void eliminarAsistencia(int idAsistencia) throws IllegalOrphanException {

        try {
            asistenciaJpacontrolador.destroy(idAsistencia);
            JOptionPane.showMessageDialog(null, "Se Elimino exitosamente", "Información", 1);
        } catch (Exception ex) {
        }

    }

    public Asistencia buscarIdAsistencia(int idAsistencia) {

        for (Asistencia dat : getAsistencia()) {
            if (dat.getIdasistencia().equals(idAsistencia)) {
                return dat;
            }
        }
        return null;
    }

    public void cargarTablaAsistencia(JTable tabla, int idPlan) {

        modelo = new DefaultTableModel();
        tabla.setModel(modelo);
        Object[] fila = new Object[8];
        modelo.addColumn("id");
        modelo.addColumn("idPlan");
        modelo.addColumn("idMed");

        modelo.addColumn("Usuario");
        modelo.addColumn("cedula");
        modelo.addColumn("Asistencia");

        modelo.addColumn("multa");
        modelo.addColumn("descripcion");

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

                FrmAsistencia.jTable1.getTableHeader().getColumnModel().getColumn(0).setMinWidth(35);
                FrmAsistencia.jTable1.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(35);
                FrmAsistencia.jTable1.getTableHeader().getColumnModel().getColumn(1).setMinWidth(0);
                FrmAsistencia.jTable1.getTableHeader().getColumnModel().getColumn(1).setMaxWidth(0);
                FrmAsistencia.jTable1.getTableHeader().getColumnModel().getColumn(2).setMinWidth(0);
                FrmAsistencia.jTable1.getTableHeader().getColumnModel().getColumn(2).setMaxWidth(0);
                FrmAsistencia.jTable1.getTableHeader().getColumnModel().getColumn(4).setMinWidth(0);
                FrmAsistencia.jTable1.getTableHeader().getColumnModel().getColumn(4).setMaxWidth(0);
                FrmAsistencia.jTable1.getTableHeader().getColumnModel().getColumn(5).setMinWidth(60);
                FrmAsistencia.jTable1.getTableHeader().getColumnModel().getColumn(5).setMaxWidth(65);
                FrmAsistencia.jTable1.getTableHeader().getColumnModel().getColumn(6).setMinWidth(40);
                FrmAsistencia.jTable1.getTableHeader().getColumnModel().getColumn(6).setMaxWidth(45);
                FrmAsistencia.jTable1.getTableHeader().getColumnModel().getColumn(7).setMinWidth(0);
                FrmAsistencia.jTable1.getTableHeader().getColumnModel().getColumn(7).setMaxWidth(0);

                modelo.addRow(fila);
            }
        }
    }

}
