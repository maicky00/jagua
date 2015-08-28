/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import entidades.Medidor;
import entidades.Otrospagos;
import entidades.Pagosnuevomed;
import entidadesCruds.PagosnuevomedJpaController;
import entidadesCruds.exceptions.IllegalOrphanException;
import entidadesCruds.exceptions.NonexistentEntityException;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author JC-PC
 */
public class classPagosNuevoMed {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("japPU");
    public PagosnuevomedJpaController pagosnuevomedJpacontrolador = new PagosnuevomedJpaController(emf);
    classMedidor cm = new classMedidor();

    public List<Pagosnuevomed> getPagosnuevomed() {
        return pagosnuevomedJpacontrolador.findPagosnuevomedEntities();
    }

    public void guardarPagosnuevomed(int NumMed, String observacion, float cantidad, Date fecha,int numFact) {
        try {

            Medidor idmed = cm.medidorJpacontrolador.findMedidor(cm.buscarMedidorNumM(NumMed).getIdmedidor());
            Pagosnuevomed dat = new Pagosnuevomed();
            dat.setIdmedidor(idmed);
            dat.setObsercion(observacion);
            dat.setCantidadpagar(cantidad);
            dat.setFechapago(fecha);
            dat.setNumfact(numFact);
            pagosnuevomedJpacontrolador.create(dat);

        } catch (Exception e) {

        }
    }

    public boolean modificarCorte(int id, int NumMed, String observacion, float cantidad, Date fecha) {

        try {
            Pagosnuevomed dat = pagosnuevomedJpacontrolador.findPagosnuevomed(id);
            if (dat == null) {
                return false;
            }
            Medidor idmed = cm.medidorJpacontrolador.findMedidor(cm.buscarMedidorNumM(id).getIdmedidor());
            dat.setIdmedidor(idmed);
            dat.setObsercion(observacion);
            dat.setCantidadpagar(cantidad);
            dat.setFechapago(fecha);
            pagosnuevomedJpacontrolador.edit(dat);

        } catch (Exception e) {
        }
        return true;
    }

    public void eliminarCorte(int id) throws IllegalOrphanException {

        try {
            pagosnuevomedJpacontrolador.destroy(id);
        } catch (NonexistentEntityException ex) {
        }

    }

    public Pagosnuevomed buscaridPagosnuevomed(int numagosnuevomed) {

        for (Pagosnuevomed dat : getPagosnuevomed()) {
            if (dat.getIdnuevomed().equals(numagosnuevomed)) {
                return dat;
            }
        }
        return null;
    }

    public int numCuotas(int id) {
        int r = 0;
        for (Pagosnuevomed dat : getPagosnuevomed()) {
            if (dat.getIdmedidor().getIdmedidor().equals(id)) {
                r++;
            }
        }
        return r;
    }
     public int numFactura() {

        int j = 1;

        for (Pagosnuevomed dat : getPagosnuevomed()) {
            if (dat.getNumfact() > j) {
                j = dat.getNumfact();
            }
        }

        return j;
    }
}
