/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import entidades.Tarifas;
import entidadesCruds.TarifasJpaController;
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
public class classTarifas {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("japPU");
    public TarifasJpaController tarifasJpacontrolador = new TarifasJpaController(emf);

    public List<Tarifas> getTarifas() {
        return tarifasJpacontrolador.findTarifasEntities();
    }

    public void guardartarifa(int base, float tarBase, String descripcion, float valorExc, float alcant,float iva) {
        int i = 0;
        Tarifas usu = new Tarifas();
        try {
            for (Tarifas us : getTarifas()) {
                if (us.getDescripcion().equals(descripcion)) {
                    i = 1;
                    usu = us;
                    break;
                }
            }
            if (i == 1) {
                JOptionPane.showMessageDialog(null, "Descripcion Existente", "Información", 1);
                usu = null;
            } else {

                Tarifas dat = new Tarifas();
                dat.setBase(base);
                dat.setTarbase(tarBase);
                dat.setDescripcion(descripcion);
                dat.setValorexceso(valorExc);
                dat.setAlcantarrillado(alcant);
                dat.setIva(iva);
                tarifasJpacontrolador.create(dat);
            }
        } catch (Exception e) {

        }
    }

    public boolean modificarTarifa(int id, int base, float tarBase, String descripcion, float valorExc, float alcant,float iva) {

        try {

            Tarifas dat = tarifasJpacontrolador.findTarifas(id);
            if (dat == null) {
                return false;
            }
            dat.setBase(base);
            dat.setTarbase(tarBase);
            dat.setDescripcion(descripcion);
            dat.setValorexceso(valorExc);
            dat.setAlcantarrillado(alcant);
            dat.setIva(iva);
            tarifasJpacontrolador.edit(dat);
            

            JOptionPane.showMessageDialog(null, "Se Modifico exitosamente", "Información", 1);

        } catch (Exception e) {
        }
        return true;
    }

    public void eliminartarifa(int id) throws NonexistentEntityException {

        try {
            tarifasJpacontrolador.destroy(id);
            JOptionPane.showMessageDialog(null, "El Usuario se Elimino exitosamente", "Información", 1);
        } catch (NonexistentEntityException ex) {
        }

    }

    public Tarifas buscarTarifaDescrip(String descripcion) {

        for (Tarifas dat : getTarifas()) {
            if (dat.getDescripcion().equals(descripcion)) {
                return dat;
            }
        }
        return null;
    }

    public Tarifas buscarIdTarifas(int idtarifa) {

        for (Tarifas dat : getTarifas()) {
            if (dat.getIdtarifas().equals(idtarifa)) {
                return dat;
            }
        }
        return null;
    }

    public void cargarCmbTarifas(JComboBox lista) {
        for (Tarifas item : getTarifas()) {
            lista.addItem(item.getDescripcion());
        }
    }
}
