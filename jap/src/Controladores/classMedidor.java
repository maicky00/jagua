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
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

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

    public void guardarMedidor(int idUsuario, String serie, int numMedidor, String estado) {
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
                JOptionPane.showMessageDialog(null, "Numero Medidor Existente \nNo se pudo Guardar", "Información", 1);
                med = null;

            } else {
                Usuarios usua = cu.usuariosJpacontrolador.findUsuarios(cu.buscarLoginId(idUsuario).getIdusuario());
                Medidor dat = new Medidor();
                dat.setIdusuario(usua);
                dat.setSerie(serie);
                dat.setNummedidor(numMedidor);
                dat.setEstado(estado);
                medidorJpacontrolador.create(dat);
                JOptionPane.showMessageDialog(null, "Registrado");
            }
        } catch (Exception e) {

        }
    }

    public boolean modificarMedidor(int id, int idusuario, String serie, int numMedidor, String estado) {
        int i = 0;
        Medidor med = new Medidor();

        try {

            Usuarios usua = cu.usuariosJpacontrolador.findUsuarios(cu.buscarLoginId(idusuario).getIdusuario());
            Medidor dat = medidorJpacontrolador.findMedidor(id);
            if (dat == null) {
                return false;
            }
            dat.setIdusuario(usua);
            dat.setSerie(serie);
            dat.setNummedidor(numMedidor);
            dat.setEstado(estado);
            medidorJpacontrolador.edit(dat);
            JOptionPane.showMessageDialog(null, "Se Modifico exitosamente", "Información", 1);

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

    public Medidor buscarMedidorNumM(int numMedidor) {

        for (Medidor dat : getMedidor()) {
            if (dat.getNummedidor().equals(numMedidor)) {
                return dat;
            }
        }
        return null;
    }

    public int buscarMedidorNumM2(int numMedidor) {
        int n = 0;
        for (Medidor dat : getMedidor()) {
            if (dat.getNummedidor().equals(numMedidor)) {
                n = dat.getIdmedidor();
            }
        }
        return n;
    }

    public Medidor buscarMedidorId(int idMedidor) {

        for (Medidor dat : getMedidor()) {
            if (dat.getIdmedidor().equals(idMedidor)) {
                return dat;
            }
        }
        return null;
    }

    public void cargarTablaMedidor(JTable tabla) {
        Usuarios us = new Usuarios();
        DefaultTableModel modelo = new DefaultTableModel();
        tabla.setModel(modelo);
        Object[] fila = new Object[7];
        modelo.addColumn("Nro");
        modelo.addColumn("Numero Medidor");
        modelo.addColumn("Usuario");
        modelo.addColumn("Apodo");
        modelo.addColumn("Serie");
        modelo.addColumn("estado");
        modelo.addColumn("#");
        for (Medidor u : getMedidor()) {
            fila[0] = u.getIdmedidor();
            fila[1] = u.getNummedidor();
            fila[2] = u.getIdusuario().getPrimerapellido() + "  " + u.getIdusuario().getSegundoapellido() + "  "
                    + u.getIdusuario().getPrimernombre() + "  " + u.getIdusuario().getSegundonombre();
            fila[3] = u.getIdusuario().getApadosn();
            fila[4] = u.getSerie();
            fila[5] = u.getEstado();
            fila[6] = u.getIdusuario().getIdusuario();
            modelo.addRow(fila);
        }
    }

    public void cargarTablaApellidosNombres(String txt, JTable tabla) {
        Usuarios us = new Usuarios();
        DefaultTableModel modelo = new DefaultTableModel();
        tabla.setModel(modelo);
        Object[] fila = new Object[7];
        modelo.addColumn("Nro");
        modelo.addColumn("Numero Medidor");
        modelo.addColumn("Usuario");
        modelo.addColumn("Apodo");
        modelo.addColumn("Serie");
        modelo.addColumn("estado");
        modelo.addColumn("#");
        for (Medidor u : getMedidor()) {
            if (u.getIdusuario().buscarUsuarios(u.getIdusuario().elimiEspacio(txt))) {
                fila[0] = u.getIdmedidor();
                fila[1] = u.getNummedidor();
                fila[2] = u.getIdusuario().getPrimerapellido() + "  " + u.getIdusuario().getSegundoapellido() + "  "
                        + u.getIdusuario().getPrimernombre() + "  " + u.getIdusuario().getSegundonombre();
                fila[3] = u.getIdusuario().getApadosn();
                fila[4] = u.getSerie();
                fila[5] = u.getEstado();
                fila[6] = u.getIdusuario().getIdusuario();
                modelo.addRow(fila);
            }
        }
    }

    public void diseñoTabla(JTable tabla) {
        tabla.getColumnModel().getColumn(0).setPreferredWidth(15);
        tabla.getColumnModel().getColumn(0).setResizable(false);
        tabla.getColumnModel().getColumn(1).setPreferredWidth(70);
        tabla.getColumnModel().getColumn(2).setPreferredWidth(200);
        tabla.getColumnModel().getColumn(3).setPreferredWidth(70);
        tabla.getColumnModel().getColumn(3).setResizable(false);
        tabla.getColumnModel().getColumn(4).setPreferredWidth(70);
        tabla.getColumnModel().getColumn(5).setPreferredWidth(80);
        tabla.getColumnModel().getColumn(6).setPreferredWidth(1);
        tabla.getColumnModel().getColumn(6).setResizable(false);

    }

    public TableModel medidoresTabla(JTable tabla) {
        DefaultTableModel dtm = (DefaultTableModel) tabla.getModel();
        dtm.setRowCount(0);
        diseñoTabla(tabla);
        for (Medidor u : getMedidor()) {
            dtm.addRow(new Object[]{
                u.getIdmedidor(),
                u.getNummedidor(),
                u.getIdusuario().getPrimerapellido() + "  " + u.getIdusuario().getSegundoapellido() + "  "
                + u.getIdusuario().getPrimernombre() + "  " + u.getIdusuario().getSegundonombre(),
                u.getIdusuario().getApadosn(),
                u.getSerie(),
                u.getEstado(),
                u.getIdusuario().getIdusuario()
            });
        }
        return dtm;
    }

}
