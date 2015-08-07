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
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;

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

    public void eliminarPagosasistencia(int idPagosasistencia) throws IllegalOrphanException {

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

}
