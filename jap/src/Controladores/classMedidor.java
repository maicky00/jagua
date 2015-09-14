/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Formularios.FrmAsistencia;
import Formularios.FrmCorte;
import Formularios.FrmMedidor;
import Formularios.FrmOtrosPagos;
import Formularios.frmDetalleFactura;
import entidades.Corte;
import entidades.Detallefactura;
import entidades.Medidor;
import entidades.Usuarios;
import entidadesCruds.MedidorJpaController;
import entidadesCruds.exceptions.IllegalOrphanException;
import entidadesCruds.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.Date;
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

    public void guardarMedidor(int idUsuario, String serie, int numMedidor, String estado, float valorConexion, String pagado, float saldo, Date fecha) {
        int i = 0;
        Medidor med = new Medidor();
        try {
            for (Medidor md : getMedidor()) {
                if (md.getNummedidor().equals(numMedidor) || md.getIdusuario().getIdusuario() == (idUsuario)) {
                    i = 1;
                    med = md;
                    break;
                }
            }
            if (i == 1) {
                JOptionPane.showMessageDialog(null, "Numero Medidor/Usuario Existente \nNo se pudo Guardar", "Informacion", 1);
                med = null;

            } else {
                Usuarios usua = cu.usuariosJpacontrolador.findUsuarios(cu.buscarLoginId(idUsuario).getIdusuario());
                Medidor dat = new Medidor();
                dat.setIdusuario(usua);
                dat.setSerie(serie);
                dat.setNummedidor(numMedidor);
                dat.setEstado(estado);
                dat.setValorporconexion(valorConexion);
                dat.setPagado(pagado);
                dat.setSaldo(saldo);
                dat.setFecha(fecha);
                medidorJpacontrolador.create(dat);
                JOptionPane.showMessageDialog(null, "Registrado");
            }
        } catch (Exception e) {

        }
    }

    public boolean modificarMedidor(int id, int idusuario, String serie, int numMedidor, String estado, float valorConexion, String pagado, float saldo) {
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
            dat.setValorporconexion(valorConexion);
            dat.setPagado(pagado);
            dat.setSaldo(saldo);
            medidorJpacontrolador.edit(dat);
            JOptionPane.showMessageDialog(null, "Se Modifico exitosamente", "Información", 1);

        } catch (Exception e) {
        }
        return true;
    }

    public boolean modificarValorConexion(int numMed, float valorConexion) {
        Medidor med = new Medidor();

        try {
            Medidor dat = medidorJpacontrolador.findMedidor(buscarMedidorNumM(numMed).getIdmedidor());
            if (dat == null) {
                return false;
            }

            dat.setSaldo(valorConexion);
            medidorJpacontrolador.edit(dat);

        } catch (Exception e) {
        }
        return true;
    }

    public boolean modificarEstado(int id, String estado) {
        Medidor med = new Medidor();

        try {
            Medidor dat = medidorJpacontrolador.findMedidor(id);
            if (dat == null) {
                return false;
            }

            dat.setEstado(estado);
            medidorJpacontrolador.edit(dat);

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
        //modelo.addColumn("Nro");
        modelo.addColumn("#");
        modelo.addColumn("Cedula");
        modelo.addColumn("Usuario");
        modelo.addColumn("Apodo");
        modelo.addColumn("Serie");
        modelo.addColumn("estado");
        modelo.addColumn("N° Medidor");
        for (Medidor u : getMedidor()) {
            // fila[0] = u.getIdmedidor();
            fila[0] = u.getIdmedidor();

            fila[1] = u.getIdusuario().getRucci();
            fila[2] = u.getIdusuario().getPrimerapellido() + "  " + u.getIdusuario().getSegundoapellido() + "  "
                    + u.getIdusuario().getPrimernombre() + "  " + u.getIdusuario().getSegundonombre();
            fila[3] = u.getIdusuario().getApadosn();
            fila[4] = u.getSerie();
            fila[5] = u.getEstado();
            fila[6] = u.getNummedidor();

            //FrmMedidor.jTable1.getColumnModel().getColumn(0).setWidth(20);
            FrmMedidor.jTable1.getTableHeader().getColumnModel().getColumn(0).setMinWidth(0);
            FrmMedidor.jTable1.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);

            FrmMedidor.jTable1.getTableHeader().getColumnModel().getColumn(1).setMinWidth(60);
            FrmMedidor.jTable1.getTableHeader().getColumnModel().getColumn(1).setMaxWidth(65);

            //FrmMedidor.jTable1.getColumnModel().getColumn(3).setWidth(150);
            //FrmMedidor.jTable1.getTableHeader().getColumnModel().getColumn(3).setMinWidth(40);
            //FrmMedidor.jTable1.getTableHeader().getColumnModel().getColumn(3).setMaxWidth(150);
            FrmMedidor.jTable1.getTableHeader().getColumnModel().getColumn(4).setMinWidth(70);
            FrmMedidor.jTable1.getTableHeader().getColumnModel().getColumn(4).setMaxWidth(85);

            FrmMedidor.jTable1.getTableHeader().getColumnModel().getColumn(5).setMinWidth(40);
            FrmMedidor.jTable1.getTableHeader().getColumnModel().getColumn(5).setMaxWidth(45);

            FrmMedidor.jTable1.getTableHeader().getColumnModel().getColumn(6).setMinWidth(45);
            FrmMedidor.jTable1.getTableHeader().getColumnModel().getColumn(6).setMaxWidth(70);
            //fila[6] = u.getIdusuario().getIdusuario();
            //FrmMedidor.label.setText(u.getIdusuario().getIdusuario().toString());
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

    public List<Medidor> getbuscarMedidor() {
        try {
            List<Medidor> datos = new ArrayList<Medidor>();
            for (Medidor e : getMedidor()) {
                datos.add(e);
            }
            return datos;
        } catch (Exception e) {
        }
        return null;
    }

    public void cargarTablaMedidorDetalle(JTable tabla, String aniomes) {

        classDetalleFactura cdf = new classDetalleFactura();
        DefaultTableModel modelo = new DefaultTableModel();
        tabla.setModel(modelo);
        Object[] fila = new Object[7];
        //modelo.addColumn("Nro");
        modelo.addColumn("#");
        modelo.addColumn("N° Medidor");
        modelo.addColumn("Cedula");
        modelo.addColumn("Usuario");
        modelo.addColumn("Apodo");
        modelo.addColumn("Serie");
        modelo.addColumn("estado");
        for (Medidor u : getMedidor()) {
            if (!u.getEstado().equals("INACTIVO")) {
                if (cdf.buscardiferentes(u.getIdmedidor(), aniomes) != u.getIdmedidor()) {

                    // fila[0] = u.getIdmedidor();
                    fila[0] = u.getIdmedidor();
                    fila[1] = u.getNummedidor();
                    fila[2] = u.getIdusuario().getRucci();
                    fila[3] = u.getIdusuario().getPrimerapellido() + "  " + u.getIdusuario().getSegundoapellido() + "  "
                            + u.getIdusuario().getPrimernombre() + "  " + u.getIdusuario().getSegundonombre();
                    fila[4] = u.getIdusuario().getApadosn();
                    fila[5] = u.getSerie();
                    fila[6] = u.getEstado();

                    frmDetalleFactura.tabla.getTableHeader().getColumnModel().getColumn(0).setMinWidth(0);
                    frmDetalleFactura.tabla.getTableHeader().getColumnModel().getColumn(0).setMinWidth(0);
                    frmDetalleFactura.tabla.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
//            frmDetalleFactura.tabla.getTableHeader().getColumnModel().getColumn(0).setMinWidth(20);
//            frmDetalleFactura.tabla.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(20);
                    frmDetalleFactura.tabla.getTableHeader().getColumnModel().getColumn(1).setMinWidth(45);
                    frmDetalleFactura.tabla.getTableHeader().getColumnModel().getColumn(1).setMaxWidth(90);
                    frmDetalleFactura.tabla.getTableHeader().getColumnModel().getColumn(2).setMinWidth(60);
                    frmDetalleFactura.tabla.getTableHeader().getColumnModel().getColumn(2).setMaxWidth(65);
                    frmDetalleFactura.tabla.getTableHeader().getColumnModel().getColumn(5).setMinWidth(60);
                    frmDetalleFactura.tabla.getTableHeader().getColumnModel().getColumn(5).setMaxWidth(85);
                    frmDetalleFactura.tabla.getTableHeader().getColumnModel().getColumn(6).setMinWidth(40);
                    frmDetalleFactura.tabla.getTableHeader().getColumnModel().getColumn(6).setMaxWidth(45);

                    modelo.addRow(fila);
                }
            }
        }
    }

    public void cargarTablaMedidor2(JTable tabla) {
        DefaultTableModel modelo = new DefaultTableModel();
        tabla.setModel(modelo);
        Object[] fila = new Object[4];
        modelo.addColumn("Nro medidor");
        modelo.addColumn("Cedula");
        modelo.addColumn("Usuario");
        modelo.addColumn("Apodo");

        for (Medidor u : getMedidor()) {

            fila[0] = u.getNummedidor();
            fila[1] = u.getIdusuario().getRucci();
            fila[2] = u.getIdusuario().getPrimerapellido() + "  " + u.getIdusuario().getSegundoapellido() + "  "
                    + u.getIdusuario().getPrimernombre();
            fila[3] = u.getIdusuario().getApadosn();

            modelo.addRow(fila);
        }
    }

    public void cargarTablaMedidorAsistencia(JTable tabla, int idPlan) {
        classAsistencia ca = new classAsistencia();
        DefaultTableModel modelo = new DefaultTableModel();
        tabla.setModel(modelo);
        Object[] fila = new Object[4];
        modelo.addColumn("Nro medidor");
        modelo.addColumn("Cedula");
        modelo.addColumn("Usuario");
        modelo.addColumn("Apodo");

        for (Medidor u : getMedidor()) {

            if (!u.getEstado().equals("INACTIVO")) {
                if (ca.buscardiferentes(u.getIdmedidor(), idPlan) != u.getIdmedidor()) {
                    fila[0] = u.getNummedidor();
                    fila[1] = u.getIdusuario().getRucci();
                    fila[2] = u.getIdusuario().getPrimerapellido() + "  " + u.getIdusuario().getSegundoapellido() + "  "
                            + u.getIdusuario().getPrimernombre();
                    fila[3] = u.getIdusuario().getApadosn();

                    modelo.addRow(fila);
                }
            }
        }
    }

    public void cargarCorte(JTable tabla) {
        classDetalleFactura cdf = new classDetalleFactura();
        classOtrosConceptos coc = new classOtrosConceptos();
        classCorte cc = new classCorte();
        DefaultTableModel modelo = new DefaultTableModel();
        tabla.setModel(modelo);
        Object[] fila = new Object[6];
        modelo.addColumn("Medidor");
        modelo.addColumn("Cedula");
        modelo.addColumn("Usuario");
        modelo.addColumn("Apodo");
        modelo.addColumn("Meses no pagados");
        modelo.addColumn("id");

        for (Medidor u : getMedidor()) {
            // fila[0] = u.getIdmedidor();
            if (cdf.numContar(u.getIdmedidor()) >= coc.buscarOtrosconceptos(1).getTiempo()) {
                if (cc.verificarRepetidos(u.getIdmedidor()) != u.getIdmedidor()) {

                    fila[0] = u.getNummedidor();
                    fila[1] = u.getIdusuario().getRucci();
                    fila[2] = u.getIdusuario().getPrimerapellido() + "  " + u.getIdusuario().getSegundoapellido() + "  "
                            + u.getIdusuario().getPrimernombre();
                    fila[3] = u.getIdusuario().getApadosn();
                    fila[4] = cdf.numContar(u.getIdmedidor());
                    fila[5] = u.getIdmedidor();

                    FrmCorte.jTable1.getTableHeader().getColumnModel().getColumn(0).setMinWidth(50);
                    FrmCorte.jTable1.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(55);
                    FrmCorte.jTable1.getTableHeader().getColumnModel().getColumn(1).setMinWidth(70);
                    FrmCorte.jTable1.getTableHeader().getColumnModel().getColumn(1).setMaxWidth(75);
                    modelo.addRow(fila);
                }
            }

        }
    }
//
//    public void cargarTablaMedidorUsuarioDebe(JTable tabla) {
//        DefaultTableModel modelo = new DefaultTableModel();
//        tabla.setModel(modelo);
//        Object[] fila = new Object[4];
//        modelo.addColumn("N° Medidor");
//        modelo.addColumn("Cedula");
//        modelo.addColumn("Usuario");
//        modelo.addColumn("Apodo");
//        float valor = 0;
//        for (Medidor u : getMedidor()) {
//
//            valor = u.getValorporconexion();
//            if (valor > 0) {
//                fila[0] = u.getNummedidor();
//                fila[1] = u.getIdusuario().getRucci();
//                fila[2] = u.getIdusuario().getPrimerapellido() + "  " + u.getIdusuario().getSegundoapellido() + "  "
//                        + u.getIdusuario().getPrimernombre();
//                fila[3] = u.getIdusuario().getApadosn();
//                FrmOtrosPagos.jTable1.getTableHeader().getColumnModel().getColumn(0).setMinWidth(35);
//                FrmOtrosPagos.jTable1.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(45);
//                FrmOtrosPagos.jTable1.getTableHeader().getColumnModel().getColumn(1).setMinWidth(65);
//                FrmOtrosPagos.jTable1.getTableHeader().getColumnModel().getColumn(1).setMaxWidth(75);
//
//                modelo.addRow(fila);
//            }
//        }
//    }

    public void Ingresarlist() {
        classCorte cc = new classCorte();
        try {
            if (getlista().size() > 0) {
                for (Corte dat : getlista()) {
                    cc.corteJpacontrolador.create(dat);
                }

            }
        } catch (Exception ex) {
        }

    }

    public List<Corte> getlista() {
        classDetalleFactura cdf = new classDetalleFactura();
        classCorte cc = new classCorte();
        classOtrosConceptos coc = new classOtrosConceptos();

        List<Corte> lst = new ArrayList<>();
        if (coc.buscarOtrosconceptos(1).getActivo().equals("ACTIVAR")) {
            for (Medidor u : getMedidor()) {
                if (cdf.numContar(u.getIdmedidor()) >= coc.buscarOtrosconceptos(1).getTiempo()) {
                    if (cc.verificarRepetidos(u.getIdmedidor()) != u.getIdmedidor()) {
                        Corte dat = new Corte();
                        Medidor idmed = medidorJpacontrolador.findMedidor(u.getIdmedidor());
                        dat.setIdmedidor(idmed);
                        dat.setCorte("NO");
                        dat.setFecha(new Date());
                        dat.setObservacion(coc.buscarOtrosconceptos(1).getDescripcion());
                        dat.setMulta(coc.buscarOtrosconceptos(1).getCantidad());
                        dat.setMora(cdf.numContar(u.getIdmedidor()));
                        dat.setPagado("NO");
                        lst.add(dat);
                    }
                }
            }
        }

        return lst;
    }

}
