/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Formularios.FrmMedidor;
import entidades.Aguaganaderia;
import entidades.Usuarios;
import entidadesCruds.AguaganaderiaJpaController;
import entidadesCruds.exceptions.IllegalOrphanException;
import entidadesCruds.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author JC-PC
 */
public class classAguaganaderia {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("japPU");

    public entidadesCruds.UsuariosJpaController usuariosJpacontrolador = new entidadesCruds.UsuariosJpaController(emf);
    public AguaganaderiaJpaController aguaganaderiaJpacontrolador = new AguaganaderiaJpaController(emf);
    
    classusuario cu = new classusuario();
    Date date = new Date();

    public List<Aguaganaderia> getAguaganaderia() {
        return aguaganaderiaJpacontrolador.findAguaganaderiaEntities();

    }

    public List<Aguaganaderia> ListaOrdenada() {
        List<Aguaganaderia> lista = new ArrayList<>();
        for (Aguaganaderia m1 : aguaganaderiaJpacontrolador.findAguaganaderiaEntities()) {
            lista.add(m1);
        }
        Collections.sort(lista, new Comparator<Aguaganaderia>() {

            @Override
            public int compare(Aguaganaderia o1, Aguaganaderia o2) {
                return o1.getCodigoaguaganaderia().compareTo(o2.getCodigoaguaganaderia());
            }
        });
        return lista;
    }

    public void guardarAguaganaderia(int idUsuario, String serie, int codigoAguaganaderia, String estado, float valorConexion, String pagado, float saldo, Date fecha) {
        int i = 0;
        Aguaganaderia med = new Aguaganaderia();
        try {
            for (Aguaganaderia md : getAguaganaderia()) {
                if (md.getCodigoaguaganaderia().equals(codigoAguaganaderia) || md.getIdusuario().getIdusuario() == (idUsuario)) {
                    i = 1;
                    med = md;
                    break;
                }
            }
            if (i == 1) {
                JOptionPane.showMessageDialog(null, "No se pudo guardar\n Numero de codigo ya existe, por favor ingrese numero diferente", "Informacion", 1);
                med = null;

            } else {
                Usuarios usua = cu.usuariosJpacontrolador.findUsuarios(cu.buscarLoginId(idUsuario).getIdusuario());
                
                Aguaganaderia dat = new Aguaganaderia();
                dat.setIdusuario(usua);
                dat.setCodigoaguaganaderia(codigoAguaganaderia);
                dat.setEstado(estado);
                dat.setValorporconexion(valorConexion);
                dat.setPagado("NO");
                dat.setSaldo(valorConexion);
                dat.setFecha(fecha);
                aguaganaderiaJpacontrolador.create(dat);
                
                JOptionPane.showMessageDialog(null, "Registrado");
            }
        } catch (Exception e) {
            System.out.println("Error: "+e.getMessage());
        }
    }

    public boolean modificarAguaganaderia(int id, int idusuario, String serie, int codigoAguaganaderia, String estado, float valorConexion, String pagado, float saldo) {
        int i = 0;
        Aguaganaderia med = new Aguaganaderia();

        try {

                Usuarios usua = cu.usuariosJpacontrolador.findUsuarios(cu.buscarLoginId(idusuario).getIdusuario());
                Aguaganaderia dat = aguaganaderiaJpacontrolador.findAguaganaderia(id);
                if (dat == null) {
                    return false;
                }
                dat.setIdusuario(usua);
                dat.setCodigoaguaganaderia(codigoAguaganaderia);
                dat.setEstado(estado);
                dat.setValorporconexion(valorConexion);
                dat.setPagado(pagado);
                dat.setSaldo(saldo);
                aguaganaderiaJpacontrolador.edit(dat);
                JOptionPane.showMessageDialog(null, "Se Modifico exitosamente", "Información", 1);


        } catch (Exception e) {
        }
        return true;
    }

    public int medOcupado(int codigoAguaganaderia) {
        int r = 0;
        for (Aguaganaderia col : getAguaganaderia()) {
            if (col.getCodigoaguaganaderia()== codigoAguaganaderia) {
                r = r + 1;
            }
        }
        return r;
    }

    public boolean modificarValorConexion(int codigoAguaganaderia, float valorConexion) {
        Aguaganaderia med = new Aguaganaderia();

        try {
            Aguaganaderia dat = aguaganaderiaJpacontrolador.findAguaganaderia(buscarAguaganaderiaCodigo(codigoAguaganaderia).getIdaguaganaderia());
            if (dat == null) {
                return false;
            }

            dat.setSaldo(valorConexion);
            aguaganaderiaJpacontrolador.edit(dat);

        } catch (Exception e) {
        }
        return true;
    }

    public boolean modificarEstado(int id, String estado) {
        Aguaganaderia med = new Aguaganaderia();

        try {
            Aguaganaderia dat = aguaganaderiaJpacontrolador.findAguaganaderia(id);
            if (dat == null) {
                return false;
            }

            dat.setEstado(estado);
            aguaganaderiaJpacontrolador.edit(dat);

        } catch (Exception e) {
        }
        return true;
    }

    public void eliminarAguaganaderia(int id) throws NonexistentEntityException, IllegalOrphanException {

        try {
            aguaganaderiaJpacontrolador.destroy(id);
            JOptionPane.showMessageDialog(null, "Se Elimino exitosamente", "Información", 1);
        } catch (NonexistentEntityException ex) {
        }

    }

    public Aguaganaderia buscarAguaganaderiaCodigo(int codigoAguaganaderia) {

        for (Aguaganaderia dat : getAguaganaderia()) {
            if (dat.getCodigoaguaganaderia().equals(codigoAguaganaderia)) {
                return dat;
            }
        }
        return null;
    }
    //buscar medidor por idUsuario
    public Aguaganaderia buscarAguaganaderiaIdUsuario(int idUsuario) {
        for (Aguaganaderia dat : getAguaganaderia()) {
            
            if (dat.getIdusuario().getIdusuario().equals(idUsuario)) {
                return dat;
            }
        }
        return null;
    }
    //buscar medidor por idMedidor
    public Aguaganaderia buscarMedidorId(int idAguaganaderia) {

        for (Aguaganaderia dat : getAguaganaderia()) {
            if (dat.getIdaguaganaderia().equals(idAguaganaderia)) {
                return dat;
            }
        }
        return null;
    }

    public void cargarTablaMedidor(JTable tabla) {
        tabla.setDefaultRenderer(Object.class, new Render());
        DefaultTableModel modelo = new DefaultTableModel(){
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };    
        modelo.addColumn("id usuario");
        modelo.addColumn("#");
        modelo.addColumn("Cedula");
        modelo.addColumn("Usuario");
        modelo.addColumn("Apodo");
        modelo.addColumn("estado");
        modelo.addColumn("N° Medidor");
        
        modelo.addColumn("Ver");
        modelo.addColumn("Editar");
        modelo.addColumn("Eliminar");
        
        JButton btn_ver = new JButton("Ver");
        btn_ver.setName("btnVer");
        
        JButton btn_editar = new JButton("Editar");
        btn_editar.setName("btnEditar");
        
        JButton btn_eliminar = new JButton("Eliminar");
        btn_eliminar.setName("btnEliminar");

        Object[] fila = new Object[10];
        Integer i=1;
        for (Aguaganaderia u : ListaOrdenada()) {
            fila[0] = u.getIdusuario().getIdusuario();
            fila[1] = i;
            
            fila[2] = u.getIdusuario().getRucci();
            fila[3] = u.getIdusuario().getPrimerapellido() + "  " + u.getIdusuario().getSegundoapellido() + "  "
                    + u.getIdusuario().getPrimernombre() + "  " + u.getIdusuario().getSegundonombre();
            fila[4] = u.getIdusuario().getApadosn();
            fila[5] = u.getEstado();
            fila[6] = u.getCodigoaguaganaderia();
            
            fila[7] = btn_ver;
            fila[8] = btn_editar;
            fila[9] = btn_eliminar;

            //fila[6] = u.getIdusuario().getIdusuario();
            //FrmMedidor.label.setText(u.getIdusuario().getIdusuario().toString());
            modelo.addRow(fila);
            //tabla.setRowHeight(20);
            i++;
        }
        tabla.setModel(modelo);
        
        FrmMedidor.jTable1.getTableHeader().getColumnModel().getColumn(0).setMinWidth(0);
        FrmMedidor.jTable1.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);

        FrmMedidor.jTable1.getTableHeader().getColumnModel().getColumn(1).setMinWidth(20);
        FrmMedidor.jTable1.getTableHeader().getColumnModel().getColumn(1).setMaxWidth(40);

        FrmMedidor.jTable1.getTableHeader().getColumnModel().getColumn(2).setMinWidth(0);
        FrmMedidor.jTable1.getTableHeader().getColumnModel().getColumn(2).setMaxWidth(100);

        FrmMedidor.jTable1.getTableHeader().getColumnModel().getColumn(5).setMaxWidth(70);

        FrmMedidor.jTable1.getTableHeader().getColumnModel().getColumn(6).setMinWidth(15);
        FrmMedidor.jTable1.getTableHeader().getColumnModel().getColumn(6).setMaxWidth(70);

        FrmMedidor.jTable1.getTableHeader().getColumnModel().getColumn(7).setMinWidth(70);
        FrmMedidor.jTable1.getTableHeader().getColumnModel().getColumn(7).setMaxWidth(70);

        FrmMedidor.jTable1.getTableHeader().getColumnModel().getColumn(8).setMinWidth(70);
        FrmMedidor.jTable1.getTableHeader().getColumnModel().getColumn(8).setMaxWidth(70);
        
        FrmMedidor.jTable1.getTableHeader().getColumnModel().getColumn(9).setMinWidth(70);
        FrmMedidor.jTable1.getTableHeader().getColumnModel().getColumn(9).setMaxWidth(70);
        
    }

    public void cargarTablaApellidosNombres(String txt, JTable tabla) {
        Usuarios us = new Usuarios();
        DefaultTableModel modelo = new DefaultTableModel();
        tabla.setModel(modelo);
        Object[] fila = new Object[6];
        modelo.addColumn("#");
        modelo.addColumn("Cedula");
        modelo.addColumn("Usuario");
        modelo.addColumn("Apodo");
        modelo.addColumn("estado");
        modelo.addColumn("N° Medidor");
        for (Aguaganaderia u : ListaOrdenada()) {
            if (u.getIdusuario().buscarUsuarios(u.getIdusuario().elimiEspacio(txt))) {
                fila[0] = u.getIdaguaganaderia();
                fila[1] = u.getIdusuario().getRucci();
                fila[2] = u.getIdusuario().getPrimerapellido() + "  " + u.getIdusuario().getSegundoapellido() + "  "
                        + u.getIdusuario().getPrimernombre() + "  " + u.getIdusuario().getSegundonombre();
                fila[3] = u.getIdusuario().getApadosn();
                fila[5] = u.getEstado();
                fila[6] = u.getCodigoaguaganaderia();

                modelo.addRow(fila);

            }

            FrmMedidor.jTable1.getTableHeader().getColumnModel().getColumn(0).setMinWidth(0);
            FrmMedidor.jTable1.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
            FrmMedidor.jTable1.getTableHeader().getColumnModel().getColumn(1).setMinWidth(60);
            FrmMedidor.jTable1.getTableHeader().getColumnModel().getColumn(1).setMaxWidth(65);
            FrmMedidor.jTable1.getTableHeader().getColumnModel().getColumn(4).setMinWidth(70);
            FrmMedidor.jTable1.getTableHeader().getColumnModel().getColumn(4).setMaxWidth(85);
            FrmMedidor.jTable1.getTableHeader().getColumnModel().getColumn(5).setMinWidth(40);
            FrmMedidor.jTable1.getTableHeader().getColumnModel().getColumn(5).setMaxWidth(45);
            FrmMedidor.jTable1.getTableHeader().getColumnModel().getColumn(6).setMinWidth(45);
            FrmMedidor.jTable1.getTableHeader().getColumnModel().getColumn(6).setMaxWidth(70);

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

    public TableModel AguaganaderiaTabla(JTable tabla) {
        DefaultTableModel dtm = (DefaultTableModel) tabla.getModel();
        dtm.setRowCount(0);
        diseñoTabla(tabla);
        for (Aguaganaderia u : ListaOrdenada()) {
            dtm.addRow(new Object[]{
                u.getIdaguaganaderia(),
                u.getCodigoaguaganaderia(),
                u.getIdusuario().getPrimerapellido() + "  " + u.getIdusuario().getSegundoapellido() + "  "
                + u.getIdusuario().getPrimernombre() + "  " + u.getIdusuario().getSegundonombre(),
                u.getIdusuario().getApadosn(),
                u.getEstado(),
                u.getIdusuario().getIdusuario()
            });
        }
        return dtm;
    }

    public List<Aguaganaderia> getbuscarAguaganaderia() {
        try {
            List<Aguaganaderia> datos = new ArrayList<Aguaganaderia>();
            for (Aguaganaderia e : getAguaganaderia()) {
                datos.add(e);
            }
            return datos;
        } catch (Exception e) {
        }
        return null;
    }
    
}
