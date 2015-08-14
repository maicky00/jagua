/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Formularios.FrmPlanificacion;
import com.sun.org.apache.bcel.internal.generic.AALOAD;
import entidades.Corte;
import entidades.Medidor;
import entidades.Planificacion;
import entidadesCruds.CorteJpaController;
import entidadesCruds.PlanificacionJpaController;
import entidadesCruds.exceptions.IllegalOrphanException;
import entidadesCruds.exceptions.NonexistentEntityException;
import java.util.Date;
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
public class classPlanificacion {
    
    DefaultTableModel modelo;

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("japPU");
    public PlanificacionJpaController planificacionJpacontrolador = new PlanificacionJpaController(emf);
//    classMedidor cm = new classMedidor();

    public List<Planificacion> getPlanificacion() {
        return planificacionJpacontrolador.findPlanificacionEntities();
    }

    public void guardarPlanificacion(String tipoPlanificacion, String lugar, Date fecha, float valorMulta, String descripcion) {
        try {
            Planificacion dat = new Planificacion();
            dat.setTipoplanificacion(tipoPlanificacion);
            dat.setLugar(lugar);
            dat.setFecha(fecha);
            dat.setValormulta(valorMulta);
            dat.setDescripcion(descripcion);
            planificacionJpacontrolador.create(dat);

        } catch (Exception e) {

        }
    }

    public boolean modificarPlanificacion(int idPlanificacion, String tipoPlanificacion, String lugar, Date fecha, float valorMulta, String descripcion) {
        try {
            Planificacion dat = planificacionJpacontrolador.findPlanificacion(idPlanificacion);
            if (dat == null) {
                return false;
            }
            dat.setTipoplanificacion(tipoPlanificacion);
            dat.setLugar(lugar);
            dat.setFecha(fecha);
            dat.setValormulta(valorMulta);
            dat.setDescripcion(descripcion);
            planificacionJpacontrolador.edit(dat);
            JOptionPane.showMessageDialog(null, "Se Modifico exitosamente", "Información", 1);

        } catch (Exception e) {
        }
        return true;
    }

    public void eliminarPlanificacion(int idPlanificacion) throws IllegalOrphanException {

        try {
            planificacionJpacontrolador.destroy(idPlanificacion);
            JOptionPane.showMessageDialog(null, "Se Elimino exitosamente", "Información", 1);
        } catch (NonexistentEntityException ex) {
        }

    }

    public Planificacion buscarIdPlanificacion(int idPlanificacion) {

        for (Planificacion dat : getPlanificacion()) {
            if (dat.getIdplanificacion().equals(idPlanificacion)) {
                return dat;
            }
        }
        return null;
    }

    public void cargarTablaPlanificacion(JTable tabla) {

        modelo = new DefaultTableModel();
        tabla.setModel(modelo);
        Object[] fila = new Object[6];
        modelo.addColumn("id");
        modelo.addColumn("Planificacion");
        modelo.addColumn("Lugar");

        modelo.addColumn("Fecha");

        modelo.addColumn("Multa");
        modelo.addColumn("Descripcion");
        

//        Medidor med=cm.medidorJpacontrolador.findMedidor(cm.buscarMedidorId(idMedidor));
        for (Planificacion p : getPlanificacion()) {

            fila[0] = p.getIdplanificacion();
            fila[1] = p.getTipoplanificacion();
            fila[2] = p.getLugar();
            fila[3] = p.getFecha();

            fila[4] = p.getValormulta();
            fila[5] = p.getDescripcion();

            FrmPlanificacion.jTable1.getTableHeader().getColumnModel().getColumn(0).setMinWidth(15);
            FrmPlanificacion.jTable1.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(20);
            FrmPlanificacion.jTable1.getTableHeader().getColumnModel().getColumn(1).setMinWidth(65);
            FrmPlanificacion.jTable1.getTableHeader().getColumnModel().getColumn(1).setMaxWidth(75);
            FrmPlanificacion.jTable1.getTableHeader().getColumnModel().getColumn(4).setMinWidth(45);
            FrmPlanificacion.jTable1.getTableHeader().getColumnModel().getColumn(4).setMaxWidth(55);
            
            modelo.addRow(fila);
        }
    }

}
