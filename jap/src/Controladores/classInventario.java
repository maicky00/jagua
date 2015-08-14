/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Formularios.FrmInventario;
import Formularios.FrmUsuario;
import entidades.Inventario;
import entidades.Usuarios;
import entidadesCruds.InventarioJpaController;
import entidadesCruds.UsuariosJpaController;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Date;
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
public class classInventario {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("japPU");
    public InventarioJpaController inventarioJpacontrolador = new InventarioJpaController(emf);

    FileInputStream entrada;
    FileOutputStream salida;
    DefaultTableModel modelo;
    File archivo;

    public List<Inventario> getnventario() {
        return inventarioJpacontrolador.findInventarioEntities();
    }

    public void guardarUsuarios(String codArticulo, String descripcion, Date fechaAdquisicion, float valor, String depeciable, String nombre, int cantidad, byte[] imagen) {

        try {
            Inventario dat = new Inventario();
            dat.setCodarticulo(codArticulo);
            dat.setDescripcion(descripcion);
            dat.setFechaadquisicion(fechaAdquisicion);
            dat.setValor(valor);
            dat.setDepreciable(depeciable);
            dat.setNombre(nombre);
            dat.setCantidad(cantidad);
            if (imagen != null) {
                dat.setImagen(imagen);
            } else {
                dat.setImagen(null);
            }
            inventarioJpacontrolador.create(dat);
            JOptionPane.showMessageDialog(null, "Guardado", "Informacion", 1);

        } catch (Exception e) {

        }
    }

    public boolean modificarUsuario(int idInventario, String codArticulo, String descripcion, Date fechaAdquisicion, float valor, String depeciable, String nombre, int cantidad, byte[] imagen) {

        try {
            Inventario dat = inventarioJpacontrolador.findInventario(idInventario);
            if (dat == null) {
                return false;
            }

            dat.setCodarticulo(codArticulo);
            dat.setDescripcion(descripcion);
            dat.setFechaadquisicion(fechaAdquisicion);
            dat.setValor(valor);
            dat.setDepreciable(depeciable);
            dat.setNombre(nombre);
            dat.setCantidad(cantidad);
            if (imagen != null) {
                dat.setImagen(imagen);
            } else {
                dat.setImagen(null);
            }
            inventarioJpacontrolador.edit(dat);
            JOptionPane.showMessageDialog(null, "El Usuario se Modifico exitosamente", "InformaciÃ³n", 1);

        } catch (Exception e) {
        }
        return true;
    }

    public void eliminarInventario(int idInventario) {

        try {
            inventarioJpacontrolador.destroy(idInventario);
            JOptionPane.showMessageDialog(null, "El Usuario se Elimino exitosamente", "InformaciÃ³n", 1);
        } catch (Exception ex) {

        }

    }

    public Inventario buscarIDInventario(int idInventario) {

        for (Inventario dat : getnventario()) {
            if (dat.getIdinventario().equals(idInventario)) {
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

    public void tablaArti(JTable tabla) {
        modelo = new DefaultTableModel();
        tabla.setModel(modelo);
        Object[] fila = new Object[8];
        modelo.addColumn("N°");
        modelo.addColumn("Cantidad");
        modelo.addColumn("Nombre");
        modelo.addColumn("Fecha Adquisicion");
        modelo.addColumn("Valor");
        modelo.addColumn("Depreciable");
        modelo.addColumn("Cod. articulo");
        modelo.addColumn("Descripcion");

        for (Inventario u : getnventario()) {
            fila[0] = u.getIdinventario();
            fila[1] = u.getCantidad();
            fila[2] = u.getNombre();
            fila[3] = u.getFechaadquisicion();
            fila[4] = u.getValor();
            fila[5] = u.getDepreciable();
            fila[6] = u.getCodarticulo();
            fila[7] = u.getDescripcion();

            FrmInventario.tabla.getTableHeader().getColumnModel().getColumn(0).setWidth(0);
            FrmInventario.tabla.getTableHeader().getColumnModel().getColumn(0).setWidth(0);
            FrmInventario.tabla.getTableHeader().getColumnModel().getColumn(0).setWidth(0);
            FrmInventario.tabla.getTableHeader().getColumnModel().getColumn(1).setMaxWidth(60);
            FrmInventario.tabla.getTableHeader().getColumnModel().getColumn(2).setMinWidth(100);
            FrmInventario.tabla.getTableHeader().getColumnModel().getColumn(3).setMaxWidth(110);
            FrmInventario.tabla.getTableHeader().getColumnModel().getColumn(4).setMaxWidth(100);
            FrmInventario.tabla.getTableHeader().getColumnModel().getColumn(5).setMaxWidth(80);
            FrmInventario.tabla.getTableHeader().getColumnModel().getColumn(7).setMaxWidth(130);
            modelo.addRow(fila);
        }
    }
}
