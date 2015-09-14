/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import entidades.Asistencia;
import entidades.Pagosasistencia;
import entidadesCruds.PagosasistenciaJpaController;
import entidadesCruds.exceptions.IllegalOrphanException;
import entidadesCruds.exceptions.NonexistentEntityException;
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
 * @author JC-PC
 */
public class classPagosAsistencias {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("japPU");
    public PagosasistenciaJpaController pagosasistenciaJpacontrolador = new PagosasistenciaJpaController(emf);
    classAsistencia ca = new classAsistencia();

    public List<Pagosasistencia> getPagosasistencia() {
        return pagosasistenciaJpacontrolador.findPagosasistenciaEntities();
    }

    public void guardarPagosasistencia(int idAsistencia, Date fechaPago, int numMigas, float valorMingas, String observacion, String usuarioActual) {
        try {

            Asistencia idAsis = ca.asistenciaJpacontrolador.findAsistencia(ca.buscarIdAsistencia(idAsistencia).getIdasistencia());
            Pagosasistencia dat = new Pagosasistencia();
            dat.setIdasistencia(idAsis);
            dat.setFechapago(fechaPago);
            dat.setNummingas(numMigas);
            dat.setValormingas(valorMingas);
            dat.setUsuarioactual(usuarioActual);
            dat.setObservacion(observacion);
            pagosasistenciaJpacontrolador.create(dat);

        } catch (Exception e) {

        }
    }

    public boolean modificarPagosasistencia(int idPagosasistencia, int idAsistencia, Date fechaPago, int numMigas, float valorMingas, String observacion, String usuarioActual) {
        try {
            Pagosasistencia dat = pagosasistenciaJpacontrolador.findPagosasistencia(idPagosasistencia);
            if (dat == null) {
                return false;
            }
            Asistencia idAsis = ca.asistenciaJpacontrolador.findAsistencia(ca.buscarIdAsistencia(idAsistencia).getIdasistencia());
            dat.setIdasistencia(idAsis);
            dat.setFechapago(fechaPago);
            dat.setNummingas(numMigas);
            dat.setValormingas(valorMingas);
            dat.setUsuarioactual(usuarioActual);
            dat.setObservacion(observacion);
            pagosasistenciaJpacontrolador.edit(dat);
            JOptionPane.showMessageDialog(null, "Se Modifico exitosamente", "Información", 1);

        } catch (Exception e) {
        }
        return true;
    }

    public void eliminarPagosasistencia(int idPagosasistencia) throws NonexistentEntityException {

        try {
            pagosasistenciaJpacontrolador.destroy(idPagosasistencia);
            JOptionPane.showMessageDialog(null, "Se Elimino exitosamente", "Información", 1);
        } catch (NonexistentEntityException ex) {
        }

    }

    public Pagosasistencia buscarIdPagosasistencia(int idPagosasistencia) {

        for (Pagosasistencia dat : getPagosasistencia()) {
            if (dat.getIdpagoasistencia().equals(idPagosasistencia)) {
                return dat;
            }
        }
        return null;
    }

//    public int buscar(int idnumMed, int idPlan) {
//
//        for (Pagosasistencia dat : getPagosasistencia()) {
//            if (dat.getIdasistencia().g.equals(idnumMed)
//                    && dat.getIdasistencia().getIdplanificacion().getIdplanificacion().equals(idPlan)) {
//                return dat.getIdpagoasistencia();
//            }
//        }
//        return 0;
//    }

    public void Ingresarlist(List<Pagosasistencia> list) {
        try {
            if (list.size() > 0) {
                for (Pagosasistencia dat : list) {
                    pagosasistenciaJpacontrolador.create(dat);
                }
                JOptionPane.showMessageDialog(null, "Realizado", "Información", 1);

            } else {
                JOptionPane.showMessageDialog(null, "No hay valores a Pagar", "Información", 1);

            }
        } catch (Exception ex) {
        }

    }

    public List<Pagosasistencia> getTable(JTable table, int numFact, Date fechaEmision, String usuarioActual, float valor) {

        classAsistencia cdf = new classAsistencia();
        DefaultTableModel dtm = (DefaultTableModel) table.getModel();
        List<Pagosasistencia> lst = new ArrayList<>();
        int nRow = dtm.getRowCount(), nCol = dtm.getColumnCount();

        for (int i = 0; i < nRow; i++) {
            Asistencia idAsis = cdf.asistenciaJpacontrolador.findAsistencia(cdf.buscarIdAsistencia(Integer.valueOf(dtm.getValueAt(i, 0).toString())).getIdasistencia());

            Pagosasistencia dat = new Pagosasistencia();
            dat.setIdasistencia(idAsis);
            dat.setFechapago(fechaEmision);
            dat.setNummingas(1);
            dat.setValormingas(valor);
            dat.setObservacion(dtm.getValueAt(i, 2).toString());
            dat.setUsuarioactual(usuarioActual);
            dat.setNumfactura(numFact);
            lst.add(dat);
            cdf.modificarAsistencia(cdf.buscarIdAsistencia(Integer.valueOf(dtm.getValueAt(i, 0).toString())).getIdasistencia(), "SI");
        }
        return lst;
    }

    public int numFactura() {
        int i = 0;
        for (Pagosasistencia dat : getPagosasistencia()) {
            if (dat.getNumfactura() > i) {
                i = dat.getNumfactura();
            }
        }
        return i;
    }
}
