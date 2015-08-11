/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Formularios.FrmBusqueda;
import Formularios.FrmUsuario;
import entidades.Institucion;
import entidades.Medidor;
import entidades.Usuarios;
import entidadesCruds.UsuariosJpaController;
import entidadesCruds.exceptions.IllegalOrphanException;
import entidadesCruds.exceptions.NonexistentEntityException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author JC-PC
 */
public class classusuario {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("japPU");
    public UsuariosJpaController usuariosJpacontrolador = new UsuariosJpaController(emf);
    classInstitucion ci = new classInstitucion();
    FileInputStream entrada;
    FileOutputStream salida;
    DefaultTableModel modelo;
    File archivo;

    public List<Usuarios> getUsuarios() {
        return usuariosJpacontrolador.findUsuariosEntities();
    }

    public void guardarUsuarios(String nombreInst, String rucCi, String priNombre, String segNombre, String priApellido, String segApellido, String apodoNS, String direccion, String telefono, String celular, String sector, String referencia, byte[] foto, String observacion) {
        int i = 0;
        Usuarios usu = new Usuarios();
        try {
            for (Usuarios us : getUsuarios()) {
                if (!rucCi.equals("")) {
                    if (us.getRucci().equals(rucCi) && rucCi.equals("")) {
                        i = 1;
                        usu = us;
                        break;
                    }
                }
            }
            if (i == 1) {
                JOptionPane.showMessageDialog(null, "Ruc/CI Existente", "InformaciÃ³n", 1);
                usu = null;
            } else {

                Institucion inst = ci.institucionJpacontrolador.findInstitucion(ci.buscarInstitucionNombre(nombreInst).getIdinstitucion());

                Usuarios dat = new Usuarios();
                dat.setIdinstitucion(inst);
                dat.setRucci(rucCi);
                dat.setPrimernombre(priNombre);
                dat.setSegundonombre(segNombre);
                dat.setPrimerapellido(priApellido);
                dat.setSegundoapellido(segApellido);
                dat.setApadosn(apodoNS);
                dat.setDireccion(direccion);
                dat.setTelefono(telefono);
                dat.setCelular(celular);
                dat.setSector(sector);
                dat.setReferencia(referencia);
                dat.setObservacion(observacion);
                if (foto != null) {
                    dat.setFoto(foto);
                } else {
                    dat.setFoto(null);
                }
                usuariosJpacontrolador.create(dat);
                JOptionPane.showMessageDialog(null, "Guardado", "Informacion", 1);

            }
        } catch (Exception e) {

        }
    }

    public boolean modificarUsuario(int id, String rucCi, String priNombre, String segNombre, String priApellido, String segApellido, String apodoNS, String direccion, String telefono, String celular, String sector, String referencia, byte[] foto, String observacion) {
        int i = 0;
        Usuarios usu = new Usuarios();

        try {
            Usuarios dat = usuariosJpacontrolador.findUsuarios(id);
            if (dat == null) {
                return false;
            }

            dat.setRucci(rucCi);
            dat.setPrimernombre(priNombre);
            dat.setSegundonombre(segNombre);
            dat.setPrimerapellido(priApellido);
            dat.setSegundoapellido(segApellido);
            dat.setApadosn(apodoNS);
            dat.setDireccion(direccion);
            dat.setTelefono(telefono);
            dat.setCelular(celular);
            dat.setSector(sector);
            dat.setReferencia(referencia);
            dat.setObservacion(observacion);
            if (foto != null) {
                dat.setFoto(foto);
            }
            usuariosJpacontrolador.edit(dat);
            JOptionPane.showMessageDialog(null, "El Usuario se Modifico exitosamente", "InformaciÃ³n", 1);

        } catch (Exception e) {
        }
        return true;
    }

    public void eliminarUsuario(int id) {

        try {
            usuariosJpacontrolador.destroy(id);
            JOptionPane.showMessageDialog(null, "El Usuario se Elimino exitosamente", "InformaciÃ³n", 1);
        } catch (Exception ex) {

        }

    }

    public Usuarios buscarUsuarioCed(String cedula) {

        for (Usuarios us : usuariosJpacontrolador.findUsuariosEntities()) {
            if (us.getRucci().equals(cedula)) {
                return us;
            }
            if (us == null) {
                JOptionPane.showMessageDialog(null, "No se encontro", "Información", 1);
            }
        }
        return null;
    }

    public Usuarios buscarUsuarioRucCi(String rucCi) {

        for (Usuarios dat : getUsuarios()) {
            if (dat.getRucci().equals(rucCi)) {
                return dat;
            }
        }
        return null;
    }

    public Usuarios buscarLoginId(int idUsuario) {

        for (Usuarios dat : getUsuarios()) {
            if (dat.getIdusuario().equals(idUsuario)) {
                return dat;
            }
        }
        return null;
    }

    public byte[] AbrirAImagen(File archivo) {
        byte[] bytesImg = new byte[1024 * 100];
        try {
            entrada = new FileInputStream(archivo);
            entrada.read(bytesImg);
        } catch (Exception e) {
        }
        return bytesImg;
    }

    public String GuardarAImagen(File archivo, byte[] bytesImg) {
        String respuesta = null;
        try {
            salida = new FileOutputStream(archivo);
            salida.write(bytesImg);
        } catch (Exception e) {
        }
        return respuesta;
    }

    public void cargarTablaUsuario(JTable tabla) {

        modelo = new DefaultTableModel();
        tabla.setModel(modelo);
        Object[] fila = new Object[10];
        modelo.addColumn("Id");
        modelo.addColumn("CI/RUC");
        modelo.addColumn("Nombres");
        modelo.addColumn("Apodo");
        modelo.addColumn("Dir");
        modelo.addColumn("tel");
        modelo.addColumn("cel");
        modelo.addColumn("sector");
        modelo.addColumn("Ref");
        modelo.addColumn("Observacion");
//        

//        Medidor med=cm.medidorJpacontrolador.findMedidor(cm.buscarMedidorId(idMedidor));
        for (Usuarios u : getUsuarios()) {
            fila[0] = u.getIdusuario();
            fila[1] = u.getRucci();
            fila[2] = u.getPrimernombre() + " " + u.getPrimerapellido();
            fila[3] = u.getApadosn();
            fila[4] = u.getDireccion();
            fila[5] = u.getTelefono();
            fila[6] = u.getCelular();
            fila[7] = u.getSector();
            fila[8] = u.getReferencia();
            fila[9] = u.getObservacion();
            modelo.addRow(fila);
        }
    }

    public void cargarTablaUsuario2(JTable tabla) {

        modelo = new DefaultTableModel();
        tabla.setModel(modelo);
        Object[] fila = new Object[7];
        modelo.addColumn("N°");
        modelo.addColumn("CI/RUC");
        modelo.addColumn("Nombres");

        modelo.addColumn("Apodo");

        modelo.addColumn("tel");
        modelo.addColumn("cel");
        modelo.addColumn("sector");

//        Medidor med=cm.medidorJpacontrolador.findMedidor(cm.buscarMedidorId(idMedidor));
        for (Usuarios u : getUsuarios()) {
            fila[0] = u.getIdusuario();
            fila[1] = u.getRucci();
            fila[2] = u.getPrimerapellido() + "  " + u.getSegundoapellido() + "  "
                    + u.getPrimernombre() + "  " + u.getSegundonombre();
            fila[3] = u.getApadosn();

            fila[4] = u.getTelefono();
            fila[5] = u.getCelular();
            fila[6] = u.getSector();

            FrmUsuario.tablaUsuarios.getTableHeader().getColumnModel().getColumn(0).setMinWidth(35);
            FrmUsuario.tablaUsuarios.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(35);
            FrmUsuario.tablaUsuarios.getTableHeader().getColumnModel().getColumn(1).setMinWidth(70);
            FrmUsuario.tablaUsuarios.getTableHeader().getColumnModel().getColumn(1).setMaxWidth(70);
            //FrmUsuario.tablaUsuarios.getTableHeader().getColumnModel().getColumn(4).setMinWidth(70);
            FrmUsuario.tablaUsuarios.getTableHeader().getColumnModel().getColumn(4).setMaxWidth(70);
            //FrmUsuario.tablaUsuarios.getTableHeader().getColumnModel().getColumn(5).setMinWidth(65);
            FrmUsuario.tablaUsuarios.getTableHeader().getColumnModel().getColumn(5).setMaxWidth(75);
            FrmUsuario.tablaUsuarios.getTableHeader().getColumnModel().getColumn(6).setMaxWidth(75);

            modelo.addRow(fila);
        }
    }

    public void cargarTablaApellidoNombre(String ced, JTable tabla) {
        //Usuarios us = new Usuarios();
        modelo = new DefaultTableModel();
        tabla.setModel(modelo);
        Object[] fila = new Object[7];
        modelo.addColumn("N°");
        modelo.addColumn("CI/RUC");
        modelo.addColumn("Nombres");

        modelo.addColumn("Apodo");

        modelo.addColumn("tel");
        modelo.addColumn("cel");
        modelo.addColumn("sector");
        for (Usuarios u : getUsuarios()) {
            if (u.buscarUsuarios(u.elimiEspacio(ced))) {
                fila[0] = u.getIdusuario();
                fila[1] = u.getRucci();
                fila[2] = u.getPrimerapellido() + "  " + u.getSegundoapellido() + "  "
                        + u.getPrimernombre() + "  " + u.getSegundonombre();
                fila[3] = u.getApadosn();
                fila[4] = u.getTelefono();
                fila[5] = u.getCelular();
                fila[6] = u.getSector();
                FrmUsuario.tablaUsuarios.getTableHeader().getColumnModel().getColumn(0).setMinWidth(35);
                FrmUsuario.tablaUsuarios.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(35);
                FrmUsuario.tablaUsuarios.getTableHeader().getColumnModel().getColumn(1).setMinWidth(70);
                FrmUsuario.tablaUsuarios.getTableHeader().getColumnModel().getColumn(1).setMaxWidth(70);
                //FrmUsuario.tablaUsuarios.getTableHeader().getColumnModel().getColumn(4).setMinWidth(70);
                FrmUsuario.tablaUsuarios.getTableHeader().getColumnModel().getColumn(4).setMaxWidth(70);
                //FrmUsuario.tablaUsuarios.getTableHeader().getColumnModel().getColumn(5).setMinWidth(65);
                FrmUsuario.tablaUsuarios.getTableHeader().getColumnModel().getColumn(5).setMaxWidth(75);
                FrmUsuario.tablaUsuarios.getTableHeader().getColumnModel().getColumn(6).setMaxWidth(75);
                modelo.addRow(fila);
            }
        }
    }

    public void BuscarCed(String txt, JTable tabla) {
        modelo = new DefaultTableModel();
        tabla.setModel(modelo);
        Object[] fila = new Object[7];
        modelo.addColumn("N°");
        modelo.addColumn("CI/RUC");
        modelo.addColumn("Nombres");

        modelo.addColumn("Apodo");

        modelo.addColumn("tel");
        modelo.addColumn("cel");
        modelo.addColumn("sector");

        for (Usuarios u : getUsuarios()) {
            if (u.buscarCedula(u.elimiEspacio(txt))) {
                fila[0] = u.getIdusuario();
                fila[1] = u.getRucci();
                fila[2] = u.getPrimerapellido() + "  " + u.getSegundoapellido() + "  "
                        + u.getPrimernombre() + "  " + u.getSegundonombre();
                fila[3] = u.getApadosn();
                fila[4] = u.getTelefono();
                fila[5] = u.getCelular();
                fila[6] = u.getSector();
                FrmUsuario.tablaUsuarios.getTableHeader().getColumnModel().getColumn(0).setMinWidth(35);
                FrmUsuario.tablaUsuarios.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(35);
                FrmUsuario.tablaUsuarios.getTableHeader().getColumnModel().getColumn(1).setMinWidth(70);
                FrmUsuario.tablaUsuarios.getTableHeader().getColumnModel().getColumn(1).setMaxWidth(70);
                //FrmUsuario.tablaUsuarios.getTableHeader().getColumnModel().getColumn(4).setMinWidth(70);
                FrmUsuario.tablaUsuarios.getTableHeader().getColumnModel().getColumn(4).setMaxWidth(70);
                //FrmUsuario.tablaUsuarios.getTableHeader().getColumnModel().getColumn(5).setMinWidth(65);
                FrmUsuario.tablaUsuarios.getTableHeader().getColumnModel().getColumn(5).setMaxWidth(75);
                FrmUsuario.tablaUsuarios.getTableHeader().getColumnModel().getColumn(6).setMaxWidth(75);
                modelo.addRow(fila);
            }
        }
    }

}
