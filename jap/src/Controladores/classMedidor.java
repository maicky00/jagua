/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import entidades.Medidor;
import entidades.Usuarios;
import entidadesCruds.MedidorJpaController;
import entidadesCruds.exceptions.IllegalOrphanException;
import entidadesCruds.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;

/**
 *
 * @author JC-PC
 */
public class classMedidor {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("japPU");
    public MedidorJpaController medidorJpacontrolador = new MedidorJpaController(emf);
    classusuario cu = new classusuario();

    public List<Medidor> getMedidor() {
        return medidorJpacontrolador.findMedidorEntities();
    }

    public void guardarMedidor(String rucCi, String serie, int numMedidor) {
        int i = 0;
        Medidor med = new Medidor();
        try {
            for (Medidor md : getMedidor()) {
                if (md.getNummedidor().equals(numMedidor)) {
                    i = 1;
                    med = md;
                    break;
                }
            }
            if (i == 1) {
                JOptionPane.showMessageDialog(null, "Numero Medidor Existente", "Información", 1);
                med = null;

            } else {
                Usuarios usua = cu.usuariosJpacontrolador.findUsuarios(cu.buscarUsuarioRucCi(rucCi).getIdusuario());
                Medidor dat = new Medidor();
                dat.setIdusuario(usua);
                dat.setSerie(serie);
                dat.setNummedidor(numMedidor);
                medidorJpacontrolador.create(dat);
            }
        } catch (Exception e) {

        }
    }

    public boolean modificarMedidor(int id, String rucCi, String serie, int numMedidor) {
        int i = 0;
        Medidor med = new Medidor();

        try {
            for (Medidor md : getMedidor()) {
                if (md.getNummedidor().equals(numMedidor)) {
                    i = 1;
                    med = md;
                    break;
                }
            }
            if (i == 1) {

                JOptionPane.showMessageDialog(null, "Numero Medidor Existente", "Información", 1);
                med = null;

            } else {

                Usuarios usua = cu.usuariosJpacontrolador.findUsuarios(cu.buscarUsuarioRucCi(rucCi).getIdusuario());
                Medidor dat = medidorJpacontrolador.findMedidor(id);
                if (dat == null) {
                    return false;
                }
                dat.setIdusuario(usua);
                dat.setSerie(serie);
                dat.setNummedidor(numMedidor);
                medidorJpacontrolador.edit(dat);
                JOptionPane.showMessageDialog(null, "Se Modifico exitosamente", "Información", 1);

            }
        } catch (Exception e) {
        }
        return true;
    }

    public void eliminarMedidor(int id) throws IllegalOrphanException {

        try {
            medidorJpacontrolador.destroy(id);
            JOptionPane.showMessageDialog(null, "Se Elimino exitosamente", "Información", 1);
        } catch (NonexistentEntityException ex) {
        }

    }

    public Medidor buscarMedidorNumM(String numMedidor) {

        for (Medidor dat : getMedidor()) {
            if (dat.getNummedidor().equals(numMedidor)) {
                return dat;
            }
        }
        return null;
    }

    public Medidor buscarMedidorId(int idMedidor) {

        for (Medidor dat : getMedidor()) {
            if (dat.getIdmedidor().equals(idMedidor)) {
                return dat;
            }
        }
        return null;
    }

}
