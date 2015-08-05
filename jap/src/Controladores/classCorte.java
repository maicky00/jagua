/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import entidades.Corte;
import entidades.Medidor;
import entidadesCruds.CorteJpaController;
import entidadesCruds.exceptions.IllegalOrphanException;
import entidadesCruds.exceptions.NonexistentEntityException;
import java.sql.Date;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;
import sun.util.calendar.BaseCalendar;

/**
 *
 * @author JC-PC
 */
public class classCorte {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("japPU");
    public CorteJpaController corteJpacontrolador = new CorteJpaController(emf);
    classMedidor cm = new classMedidor();

    public List<Corte> getCorte() {
        return corteJpacontrolador.findCorteEntities();
    }

    public void guardarCorte(int idMedidor, String corte, Date fecha, String observacion, float multa, int mora) {
        try {

            Medidor idmed = cm.medidorJpacontrolador.findMedidor(cm.buscarMedidorId(idMedidor).getIdmedidor());
            Corte dat = new Corte();
            dat.setIdmedidor(idmed);
            dat.setCorte(corte);
            dat.setFecha(fecha);
            dat.setObservacion(observacion);
            dat.setMulta(multa);
            dat.setMora(mora);
            corteJpacontrolador.create(dat);

        } catch (Exception e) {

        }
    }

    public boolean modificarCorte(int id, int idMedidor, String corte, Date fecha, String observacion, float multa, int mora) {

        try {
            Corte dat = corteJpacontrolador.findCorte(id);
            if (dat == null) {
                return false;
            }
            Medidor idmed = cm.medidorJpacontrolador.findMedidor(cm.buscarMedidorId(idMedidor).getIdmedidor());
            dat.setIdmedidor(idmed);
            dat.setCorte(corte);
            dat.setFecha(fecha);
            dat.setObservacion(observacion);
            dat.setMulta(multa);
            dat.setMora(mora);
            corteJpacontrolador.edit(dat);
            JOptionPane.showMessageDialog(null, "Se Modifico exitosamente", "Información", 1);

        } catch (Exception e) {
        }
        return true;
    }

    public void eliminarCorte(int id) throws IllegalOrphanException {

        try {
            corteJpacontrolador.destroy(id);
            JOptionPane.showMessageDialog(null, "Se Elimino exitosamente", "Información", 1);
        } catch (NonexistentEntityException ex) {
        }

    }

    public Corte buscarCorteNumMedidor(String numMedidor) {

        for (Corte dat : getCorte()) {
            if (dat.getIdmedidor().getNummedidor().equals(numMedidor)) {
                return dat;
            }
        }
        return null;
    }

    public Corte buscarIdCorte(int idCorte) {

        for (Corte dat : getCorte()) {
            if (dat.getIdcorte().equals(idCorte)) {
                return dat;
            }
        }
        return null;
    }

}
