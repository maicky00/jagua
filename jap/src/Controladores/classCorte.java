/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Formularios.FrmCorte;
import Formularios.FrmOtrosPagos;
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
//import sun.util.calendar.BaseCalendar;

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

    public void guardarCorte(int idMedidor, String corte, Date fecha, String observacion, float multa, int mora, String pagado) {
        try {
            int i = 0;
            Corte med = new Corte();

            for (Corte md : getCorte()) {
                if (md.getIdmedidor().getIdmedidor() == idMedidor
                        && md.getPagado().equals("NO")) {
                    i = 1;
                    med = md;
                    break;
                }
            }
            if (i == 1) {
                med = null;

            } else {
                Medidor idmed = cm.medidorJpacontrolador.findMedidor(cm.buscarMedidorId(idMedidor).getIdmedidor());
                Corte dat = new Corte();
                dat.setIdmedidor(idmed);
                dat.setCorte(corte);
                dat.setFecha(fecha);
                dat.setObservacion(observacion);
                dat.setMulta(multa);
                dat.setMora(mora);
                dat.setPagado(pagado);
                corteJpacontrolador.create(dat);
                JOptionPane.showMessageDialog(null, "Se guardo exitosamente", "Información", 1);
            }

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

    public boolean modificarPago(int id, String pagado) {

        try {
            Corte dat = corteJpacontrolador.findCorte(id);
            if (dat == null) {
                return false;
            }
            dat.setPagado(pagado);
            corteJpacontrolador.edit(dat);

        } catch (Exception e) {
        }
        return true;
    }

    public void eliminarCorte(int id) throws NonexistentEntityException {

        corteJpacontrolador.destroy(id);
        JOptionPane.showMessageDialog(null, "Se Elimino exitosamente", "Información", 1);

    }

    public Corte buscarCorteNumMedidor(String numMedidor) {

        for (Corte dat : getCorte()) {
            if (dat.getIdmedidor().getNummedidor().equals(numMedidor)) {
                return dat;
            }
        }
        return null;
    }

    public Corte buscarMedidorNumM(int numMedidor) {

        for (Corte dat : getCorte()) {
            if (dat.getIdmedidor().getNummedidor().equals(numMedidor)) {
                return dat;
            }
        }
        return null;
    }

    public Corte buscarMedNum(int numMedidor) {

        for (Corte dat : getCorte()) {
            if (dat.getIdmedidor().getNummedidor().equals(numMedidor)
                    && dat.getPagado().equals("NO")) {
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

    public boolean verificarOtrPagos(int idnumMed) {

        for (Corte dat : getCorte()) {
            if (dat.getIdmedidor().getNummedidor().equals(idnumMed)
                    && dat.getPagado().equals("NO")) {
                return true;
            }
        }
        return false;
    }

    public void cargarCorte(JTable tabla) {
//        classDetalleFactura cdf = new classDetalleFactura();
        modelo = new DefaultTableModel();
        tabla.setModel(modelo);
        Object[] fila = new Object[6];
        modelo.addColumn("id");
        modelo.addColumn("N° Med");
        modelo.addColumn("Cedula");
        modelo.addColumn("Usuario");
        modelo.addColumn("Apodo");
        modelo.addColumn("multa");
        
        for (Corte c : getCorte()) {
            if (c.getCorte().equals("SI") && c.getPagado().equals("NO")) {
                if (c.getIdmedidor().getEstado().equals("ACTIVO")) {

                    fila[0] = c.getIdcorte();
                    fila[1] = c.getIdmedidor().getNummedidor();
                    fila[2] = c.getIdmedidor().getIdusuario().getRucci();
                    fila[3] = c.getIdmedidor().getIdusuario().getPrimernombre() + " "
                            + c.getIdmedidor().getIdusuario().getPrimerapellido() + " "
                            + c.getIdmedidor().getIdusuario().getSegundoapellido();
                    fila[4] = c.getIdmedidor().getIdusuario().getApadosn();
                    fila[5] = c.getMulta();

                    modelo.addRow(fila);
                }
            }
        }
        FrmCorte.jTable2.getTableHeader().getColumnModel().getColumn(0).setMinWidth(0);
        FrmCorte.jTable2.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);

    }

    public void cargarCorte2(JTable tabla) {
//        classDetalleFactura cdf = new classDetalleFactura();
        modelo = new DefaultTableModel();
        tabla.setModel(modelo);
        Object[] fila = new Object[4];
        //modelo.addColumn("id");
        modelo.addColumn("N° Med");
        modelo.addColumn("cedula");
        modelo.addColumn("usuario");
        modelo.addColumn("Apodo");
        //modelo.addColumn("multa");
        //modelo.addColumn("mora");

        for (Corte c : getCorte()) {
            if (c.getCorte().equals("SI")) {
//                fila[0] = c.getIdcorte();
                fila[0] = c.getIdmedidor().getNummedidor();
                fila[1] = c.getIdmedidor().getIdusuario().getRucci();
                fila[2] = c.getIdmedidor().getIdusuario().getPrimernombre() + " "
                        + c.getIdmedidor().getIdusuario().getPrimerapellido() + " "
                        + c.getIdmedidor().getIdusuario().getSegundoapellido();
                fila[3] = c.getIdmedidor().getIdusuario().getApadosn();
//                fila[5] = c.getMulta();
//                fila[6] = c.getMora();

                modelo.addRow(fila);
            }
        }
        FrmOtrosPagos.jTable1.getTableHeader().getColumnModel().getColumn(0).setMinWidth(35);
        FrmOtrosPagos.jTable1.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(45);
        FrmOtrosPagos.jTable1.getTableHeader().getColumnModel().getColumn(1).setMinWidth(65);
        FrmOtrosPagos.jTable1.getTableHeader().getColumnModel().getColumn(1).setMaxWidth(75);

    }

    public void cargarCorteSINO(JTable tabla) {
        classDetalleFactura cdf = new classDetalleFactura();
        modelo = new DefaultTableModel();
        tabla.setModel(modelo);
        Object[] fila = new Object[7];
        modelo.addColumn("id");
        modelo.addColumn("Med");
        modelo.addColumn("corte");
        modelo.addColumn("fecha");
        modelo.addColumn("observacion");
        modelo.addColumn("multa");
        modelo.addColumn("mora");

        for (Corte c : getCorte()) {
            if (c.getCorte().equals("NO") && c.getPagado().equals("NO")) {
                if (c.getIdmedidor().getEstado().equals("ACTIVO")) {

                    fila[0] = c.getIdcorte();
                    fila[1] = c.getIdmedidor().getNummedidor();
                    fila[2] = c.getCorte();
                    fila[3] = c.getFecha();
                    fila[4] = c.getObservacion();
                    fila[5] = c.getMulta();
                    fila[6] = c.getMora();

                    modelo.addRow(fila);
                }
            }
        }
        FrmCorte.jTable2.getTableHeader().getColumnModel().getColumn(0).setMinWidth(0);
        FrmCorte.jTable2.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);

    }

    public int verificarRepetidos(int idMed) {

        for (Corte dat : getCorte()) {
            if (dat.getIdmedidor().getIdmedidor() == (idMed) && dat.getPagado().equals("NO")) {
                return dat.getIdmedidor().getIdmedidor();
            }
        }
        return 0;
    }
}
