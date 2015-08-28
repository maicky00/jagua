/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import entidades.Asistenciapesillo;
import entidades.Pagopesillo;
import entidadesCruds.PagopesilloJpaController;
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

/**
 *
 * @author Marco
 */
public class classPagoPesillo {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("japPU");
    public PagopesilloJpaController pagopesilloJpaController = new PagopesilloJpaController(emf);
    classAsistenciaPesillo cap = new classAsistenciaPesillo();

    public List<Pagopesillo> getPagoPesillo() {
        return pagopesilloJpaController.findPagopesilloEntities();
    }

    public void guardarPagoPesillo(int idAsistenciaPes, Date fecha, int numMingas, float valorMingas, String observacion, String usuarioActual) {
        try {
            Asistenciapesillo idAPes = cap.asistenciapesilloJpaController.findAsistenciapesillo(cap.buscarIdAsistenciaPes(idAsistenciaPes).getIdasistenciapesillo());
            Pagopesillo dat = new Pagopesillo();
            dat.setIdasistenciapesillo(idAPes);
            dat.setFechapago(fecha);
            dat.setNummingas(numMingas);
            dat.setValormingas(valorMingas);
            dat.setObservacion(observacion);
            dat.setUsuarioactual(usuarioActual);
            pagopesilloJpaController.create(dat);
            JOptionPane.showMessageDialog(null, "Se Guardo exitosamente", "Información", 1);

        } catch (Exception e) {
        }
    }

    public boolean modificarPagoPesillo(int idPagoPes, int idAsistenciaPes, Date fecha, int numMingas, float valorMingas, String observacion, String usuarioActual) {
        try {
            Pagopesillo dat = pagopesilloJpaController.findPagopesillo(idPagoPes);
            if (dat == null) {
                return false;
            }
            Asistenciapesillo idAPes = cap.asistenciapesilloJpaController.findAsistenciapesillo(cap.buscarIdAsistenciaPes(idAsistenciaPes).getIdasistenciapesillo());
            dat.setIdasistenciapesillo(idAPes);
            dat.setFechapago(fecha);
            dat.setNummingas(numMingas);
            dat.setValormingas(valorMingas);
            dat.setObservacion(observacion);
            dat.setUsuarioactual(usuarioActual);
            pagopesilloJpaController.edit(dat);
            JOptionPane.showMessageDialog(null, "Se Modifico exitosamente", "Información", 1);
        } catch (Exception e) {
        }
        return true;
    }

    public void eliminarPagoPesillo(int idPagoPes) throws IllegalOrphanException {
        try {
            pagopesilloJpaController.destroy(idPagoPes);
            JOptionPane.showMessageDialog(null, "Se Elimino exitosamente", "Información", 1);
        } catch (Exception e) {
        }
    }

    public Pagopesillo buscarIdPagoPesillo(int idPagoPes) {

        for (Pagopesillo dat : getPagoPesillo()) {
            if (dat.getIdpagopesillo().equals(idPagoPes)) {
                return dat;
            }
        }
        return null;
    }

    public void Ingresarlist(List<Pagopesillo> list) {
        try {
            if (list.size() > 0) {
                for (Pagopesillo dat : list) {
                    pagopesilloJpaController.create(dat);
                }
                JOptionPane.showMessageDialog(null, "Realizado", "Información", 1);

            } else {
                JOptionPane.showMessageDialog(null, "No hay valores a Pagar", "Información", 1);

            }
        } catch (Exception ex) {
        }
    }

    public List<Pagopesillo> getTable(JTable table, int numFact, Date fechaEmision, String usuarioActual, float valor) {

        classAsistenciaPesillo cdf = new classAsistenciaPesillo();
        DefaultTableModel dtm = (DefaultTableModel) table.getModel();
        List<Pagopesillo> lst = new ArrayList<>();
        int nRow = dtm.getRowCount(), nCol = dtm.getColumnCount();

        for (int i = 0; i < nRow; i++) {
            Asistenciapesillo idAsis = cdf.asistenciapesilloJpaController.findAsistenciapesillo(cdf.buscarIdAsistenciaPes(Integer.valueOf(dtm.getValueAt(i, 0).toString())).getIdasistenciapesillo());

            Pagopesillo dat = new Pagopesillo();
            dat.setIdasistenciapesillo(idAsis);
            dat.setFechapago(fechaEmision);
            dat.setNummingas(1);
            dat.setValormingas(valor);
            dat.setObservacion(dtm.getValueAt(i, 2).toString());
            dat.setUsuarioactual(usuarioActual);
            dat.setNumfactura(numFact);
            lst.add(dat);
            cdf.modificarAsistenciaPesillo(cdf.buscarIdAsistenciaPes(Integer.valueOf(dtm.getValueAt(i, 0).toString())).getIdasistenciapesillo(), "SI");
        }
        return lst;
    }

    public int numFactura() {
        int i = 0;
        for (Pagopesillo dat : getPagoPesillo()) {
            if (dat.getNumfactura() > i) {
                i = dat.getNumfactura();
            }
        }
        return i;
    }
}
