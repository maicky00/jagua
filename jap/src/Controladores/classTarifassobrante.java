/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import entidades.Tarifassobrante;
import entidadesCruds.TarifassobranteJpaController;
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
public class classTarifassobrante {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("japPU");
    public TarifassobranteJpaController tarifassobranteJpacontrolador = new TarifassobranteJpaController(emf);

    public List<Tarifassobrante> getTarifassobrante() {
        return tarifassobranteJpacontrolador.findTarifassobranteEntities();
    }

    public void guardartarifa(float tarifamensual, String descripcion,float iva) {
        try {
                Tarifassobrante dat = new Tarifassobrante();
                dat.setTarifamensual(tarifamensual);
                dat.setDescripcion(descripcion);
                dat.setIva(iva);
                tarifassobranteJpacontrolador.create(dat);
        } catch (Exception e) {

        }
    }

    public boolean modificarTarifa(int id, float tarifamensual, String descripcion,float iva) {

        try {

            Tarifassobrante dat = tarifassobranteJpacontrolador.findTarifassobrante(id);
            if (dat == null) {
                return false;
            }
            dat.setTarifamensual(tarifamensual);
            dat.setDescripcion(descripcion);
            dat.setIva(iva);
            tarifassobranteJpacontrolador.edit(dat);
            

            JOptionPane.showMessageDialog(null, "Se Modifico exitosamente", "Información", 1);

        } catch (Exception e) {
        }
        return true;
    }

    public void eliminartarifa(int id) throws NonexistentEntityException, IllegalOrphanException {

        try {
            tarifassobranteJpacontrolador.destroy(id);
            JOptionPane.showMessageDialog(null, "El Usuario se Elimino exitosamente", "Información", 1);
        } catch (NonexistentEntityException ex) {
        }

    }

    public Tarifassobrante buscarTarifaDescrip(String descripcion) {

        for (Tarifassobrante dat : getTarifassobrante()) {
            if (dat.getDescripcion().equals(descripcion)) {
                return dat;
            }
        }
        return null;
    }

    public Tarifassobrante buscarIdTarifassobrante(int idtarifa) {

        for (Tarifassobrante dat : getTarifassobrante()) {
            if (dat.getIdtarifassobrante().equals(idtarifa)) {
                return dat;
            }
        }
        return null;
    }

    public void cargarCmbTarifassobrante(JComboBox lista) {
        for (Tarifassobrante item : getTarifassobrante()) {
            lista.addItem(item.getDescripcion());
        }
    }
}
