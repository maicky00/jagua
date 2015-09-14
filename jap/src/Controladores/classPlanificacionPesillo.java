/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import entidades.Planificacionpesillo;
import entidadesCruds.PlanificacionpesilloJpaController;
import entidadesCruds.exceptions.IllegalOrphanException;
import entidadesCruds.exceptions.NonexistentEntityException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Marco
 */
public class classPlanificacionPesillo {

    DefaultTableModel modelo;
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("japPU");
    public PlanificacionpesilloJpaController planificacionPesilloJpacontrolador = new PlanificacionpesilloJpaController(emf);

    public List<Planificacionpesillo> getPlanificacionPesillo() {
        return planificacionPesilloJpacontrolador.findPlanificacionpesilloEntities();
    }

    public void guardarPlanificacionPesillo(String tipoPlanPesillo, String lugar, Date fecha, float valorMulta, String descripcion) {
        try {
            Planificacionpesillo dat = new Planificacionpesillo();
            dat.setTipoplanpesillo(tipoPlanPesillo);
            dat.setLugar(lugar);
            dat.setFecha(fecha);
            dat.setValormulta(valorMulta);
            dat.setDescripcion(descripcion);
            planificacionPesilloJpacontrolador.create(dat);
            JOptionPane.showMessageDialog(null, "Se guardo correctamente", "Información", 1);
        } catch (Exception e) {
        }
    }

    public boolean modificarPlanificacionPesillo(int idPes, String tipoPlanPesillo, String lugar, Date fecha, float valorMulta, String descripcion) {
        try {
            Planificacionpesillo dat = planificacionPesilloJpacontrolador.findPlanificacionpesillo(idPes);
            if (dat == null) {
                return false;
            }
            dat.setLugar(lugar);
            dat.setTipoplanpesillo(tipoPlanPesillo);
            dat.setFecha(fecha);
            dat.setValormulta(valorMulta);
            dat.setDescripcion(descripcion);
            planificacionPesilloJpacontrolador.edit(dat);
            JOptionPane.showMessageDialog(null, "Se Modifico exitosamente", "Información", 1);
        } catch (Exception e) {
        }
        return true;
    }

    public void eliminarPlanificacionPesillo(int idPes) throws NonexistentEntityException {
        try {
            planificacionPesilloJpacontrolador.destroy(idPes);
            JOptionPane.showMessageDialog(null, "Se Elimino exitosamente", "Información", 1);
        } catch (NonexistentEntityException e) {
        }
    }

    public Planificacionpesillo buscarIdPlanPes(int idPes) {
        for (Planificacionpesillo dat : getPlanificacionPesillo()) {
            if (dat.getIdplanificacionpesillo().equals(idPes)) {
                return dat;
            }
        }
        return null;
    }

    public Planificacionpesillo buscarIdPlanPes2(int idPes, int anio, int mes) {
        for (Planificacionpesillo dat : getPlanificacionPesillo()) {
            if (dat.getIdplanificacionpesillo().equals(idPes) && dat.getFecha().getMonth() + 1 == mes && dat.getFecha().getYear() + 1900 == anio) {
                return dat;
            }
        }
        return null;
    }

    public void cargarTablaPlanificacionPesillo(JTable tabla) {
        modelo = new DefaultTableModel();
        tabla.setModel(modelo);
        Object[] fila = new Object[6];
        modelo.addColumn("id");
        modelo.addColumn("Tipo Evento");
        modelo.addColumn("Lugar");
        modelo.addColumn("Fecha");
        modelo.addColumn("Multa");
        modelo.addColumn("Descripcion");
//        Medidor med=cm.medidorJpacontrolador.findMedidor(cm.buscarMedidorId(idMedidor));
        for (Planificacionpesillo p : getPlanificacionPesillo()) {
            Date fechaActual = p.getFecha();
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            String fecha = formato.format(fechaActual);
            fila[0] = p.getIdplanificacionpesillo();
            fila[1] = p.getTipoplanpesillo();
            fila[2] = p.getLugar();
            fila[3] = fecha;
            fila[4] = p.getValormulta();
            fila[5] = p.getDescripcion();
//            FrmPlanificacion.jTable1.getTableHeader().getColumnModel().getColumn(0).setMinWidth(15);
//            FrmPlanificacion.jTable1.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(20);
//            FrmPlanificacion.jTable1.getTableHeader().getColumnModel().getColumn(1).setMinWidth(65);
//            FrmPlanificacion.jTable1.getTableHeader().getColumnModel().getColumn(1).setMaxWidth(75);
//            FrmPlanificacion.jTable1.getTableHeader().getColumnModel().getColumn(4).setMinWidth(45);
//            FrmPlanificacion.jTable1.getTableHeader().getColumnModel().getColumn(4).setMaxWidth(55);
            modelo.addRow(fila);
        }
    }

    public void cargarTablaPesilloBuscar(JTable tabla, int anio, int mes) {

        modelo = new DefaultTableModel();
        tabla.setModel(modelo);
        Object[] fila = new Object[6];
        modelo.addColumn("id");
        modelo.addColumn("Tipo Evento");
        modelo.addColumn("Lugar");

        modelo.addColumn("Fecha");

        modelo.addColumn("Multa");
        modelo.addColumn("Descripcion");

//        Medidor med=cm.medidorJpacontrolador.findMedidor(cm.buscarMedidorId(idMedidor));
        for (Planificacionpesillo p : getPlanificacionPesillo()) {

            if (p.getFecha().getMonth() + 1 == mes && p.getFecha().getYear() + 1900 == anio) {

                Date fechaActual = p.getFecha();
                SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                String fecha = formato.format(fechaActual);

                fila[0] = p.getIdplanificacionpesillo();
                fila[1] = p.getTipoplanpesillo();
                fila[2] = p.getLugar();
                fila[3] = fecha;
                fila[4] = p.getValormulta();

                fila[5] = p.getDescripcion();

//            FrmPlanificacion.jTable1.getTableHeader().getColumnModel().getColumn(0).setMinWidth(15);
//            FrmPlanificacion.jTable1.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(20);
//            FrmPlanificacion.jTable1.getTableHeader().getColumnModel().getColumn(1).setMinWidth(65);
//            FrmPlanificacion.jTable1.getTableHeader().getColumnModel().getColumn(1).setMaxWidth(75);
//            FrmPlanificacion.jTable1.getTableHeader().getColumnModel().getColumn(4).setMinWidth(45);
//            FrmPlanificacion.jTable1.getTableHeader().getColumnModel().getColumn(4).setMaxWidth(55);
                modelo.addRow(fila);
            }
        }
    }

}
