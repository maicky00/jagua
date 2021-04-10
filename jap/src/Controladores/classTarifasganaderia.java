/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import entidades.Tarifasganaderia;
import entidadesCruds.TarifasganaderiaJpaController;
import entidadesCruds.exceptions.IllegalOrphanException;
import entidadesCruds.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

/**
 *
 * @author JC-PC
 */
public class classTarifasganaderia {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("japPU");
    public TarifasganaderiaJpaController tarifasganaderiaJpacontrolador = new TarifasganaderiaJpaController(emf);

    public List<Tarifasganaderia> getTarifasganaderia() {
        return tarifasganaderiaJpacontrolador.findTarifasganaderiaEntities();
    }

    public void guardartarifa( float tarifamensual, String descripcion,float iva) {
        try {
            Tarifasganaderia dat = new Tarifasganaderia();
            dat.setTarifamensual(tarifamensual);
            dat.setDescripcion(descripcion);
            dat.setIva(iva);
            tarifasganaderiaJpacontrolador.create(dat);
        } catch (Exception e) {

        }
    }

    public boolean modificarTarifa(int id, float tarifamensual, String descripcion, float iva) {

        try {

            Tarifasganaderia dat = tarifasganaderiaJpacontrolador.findTarifasganaderia(id);
            if (dat == null) {
                return false;
            }
            dat.setTarifamensual(tarifamensual);
            dat.setDescripcion(descripcion);
            dat.setIva(iva);
            tarifasganaderiaJpacontrolador.edit(dat);
            
            JOptionPane.showMessageDialog(null, "Se Modifico exitosamente", "Información", 1);

        } catch (Exception e) {
        }
        return true;
    }

    public void eliminartarifa(int id) throws NonexistentEntityException, IllegalOrphanException {

        try {
            tarifasganaderiaJpacontrolador.destroy(id);
            JOptionPane.showMessageDialog(null, "El Usuario se Elimino exitosamente", "Información", 1);
        } catch (NonexistentEntityException ex) {
        }

    }

    public Tarifasganaderia buscarTarifaDescrip(String descripcion) {

        for (Tarifasganaderia dat : getTarifasganaderia()) {
            if (dat.getDescripcion().equals(descripcion)) {
                return dat;
            }
        }
        return null;
    }

    public Tarifasganaderia buscarIdTarifasganaderia(int idtarifa) {

        for (Tarifasganaderia dat : getTarifasganaderia()) {
            if (dat.getIdtarifasganaderia().equals(idtarifa)) {
                return dat;
            }
        }
        return null;
    }

    public void cargarCmbTarifasganaderia(JComboBox lista) {
        for (Tarifasganaderia item : getTarifasganaderia()) {
            lista.addItem(item.getDescripcion());
        }
    }
}
