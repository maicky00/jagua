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
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import sun.util.calendar.BaseCalendar;

/**
 *
 * @author JC-PC
 */
public class classCorte {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("japPU");
    public CorteJpaController corteJpacontrolador = new CorteJpaController(emf);
    classMedidor cm = new classMedidor();
    DefaultTableModel modelo;

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

    public void cargarCorte(JTable tabla) {
        classDetalleFactura cdf = new classDetalleFactura();
        modelo = new DefaultTableModel();
        tabla.setModel(modelo);
        Object[] fila = new Object[7];
        modelo.addColumn("id");
        modelo.addColumn("idmed");
        modelo.addColumn("corte");
        modelo.addColumn("fecha");
        modelo.addColumn("observacion");
        modelo.addColumn("multa");
        modelo.addColumn("mora");

        for (Corte c : getCorte()) {
            //if (c.getCorte().equals("SI")) {
                fila[0] = c.getIdcorte();
                fila[1] = c.getIdmedidor().getIdmedidor();
                fila[2] = c.getCorte();
                fila[3] = c.getFecha();
                fila[4] = c.getObservacion();
                fila[5] = c.getMulta();
                fila[6] = c.getMora();

//                FrmCorte.jTable1.getTableHeader().getColumnModel().getColumn(0).setMinWidth(50);
//                FrmCorte.jTable1.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(55);
                modelo.addRow(fila);
            //}
        }

    }

}
