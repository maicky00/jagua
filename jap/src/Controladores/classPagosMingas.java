/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import entidades.Mingas;
import entidades.Pagosmingas;
import entidadesCruds.MingasJpaController;
import entidadesCruds.PagosmingasJpaController;
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
public class classPagosMingas {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("japPU");
    public PagosmingasJpaController pagosMingasJpacontrolador = new PagosmingasJpaController(emf);
    classMingas cm = new classMingas();

    public List<Pagosmingas> getPagosmingas() {
        return pagosMingasJpacontrolador.findPagosmingasEntities();
    }

    public void guardarPagosmingas(int idMinga, Date fechaPago, int numMigas, float valorMingas, String usuarioActual, String observacion) {
        try {

            Mingas idmg = cm.mingasJpacontrolador.findMingas(cm.buscarIdMingas(idMinga).getIdminga());
            Pagosmingas dat = new Pagosmingas();
            dat.setIdminga(idmg);
            dat.setFechapago(fechaPago);
            dat.setNummingas(numMigas);
            dat.setValormingas(valorMingas);
            dat.setUsuarioactual(usuarioActual);
            dat.setObservacion(observacion);
            pagosMingasJpacontrolador.create(dat);

        } catch (Exception e) {

        }
    }

    public boolean modificarPagosmingas(int idPagosMingas, int idMinga, Date fechaPago, int numMigas, float valorMingas, String usuarioActual, String observacion) {
        try {
            Pagosmingas dat = pagosMingasJpacontrolador.findPagosmingas(idPagosMingas);
            if (dat == null) {
                return false;
            }
            Mingas idmg = cm.mingasJpacontrolador.findMingas(cm.buscarIdMingas(idMinga).getIdminga());
            dat.setIdminga(idmg);
            dat.setFechapago(fechaPago);
            dat.setNummingas(numMigas);
            dat.setValormingas(valorMingas);
            dat.setUsuarioactual(usuarioActual);
            dat.setObservacion(observacion);
            pagosMingasJpacontrolador.edit(dat);
            JOptionPane.showMessageDialog(null, "Se Modifico exitosamente", "Información", 1);

        } catch (Exception e) {
        }
        return true;
    }

    public void eliminarPagosmingas(int idPagosmingas) throws IllegalOrphanException {

        try {
            pagosMingasJpacontrolador.destroy(idPagosmingas);
            JOptionPane.showMessageDialog(null, "Se Elimino exitosamente", "Información", 1);
        } catch (NonexistentEntityException ex) {
        }

    }

    public Pagosmingas buscarIdPagosmingas(int idPagosmingas) {

        for (Pagosmingas dat : getPagosmingas()) {
            if (dat.getIdpagominga().equals(idPagosmingas)) {
                return dat;
            }
        }
        return null;
    }

}
