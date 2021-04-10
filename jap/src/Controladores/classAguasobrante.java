/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Formularios.FrmAguasobrante;
import Formularios.FrmListaUsuarioSinAguasobrante;
import Formularios.FrmMedidor;
import entidades.Aguasobrante;
import entidades.Detallefacturasobrante;
import entidades.Tarifassobrante;
import entidades.Usuarios;
import entidadesCruds.AguasobranteJpaController;
import entidadesCruds.DetallefacturasobranteJpaController;
import entidadesCruds.exceptions.IllegalOrphanException;
import entidadesCruds.exceptions.NonexistentEntityException;
import java.text.SimpleDateFormat;
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
public class classAguasobrante {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("japPU");

    public entidadesCruds.UsuariosJpaController usuariosJpacontrolador = new entidadesCruds.UsuariosJpaController(emf);
    public AguasobranteJpaController aguasobranteJpacontrolador = new AguasobranteJpaController(emf);
    public DetallefacturasobranteJpaController detallefacturasobranteJpacontrolador = new DetallefacturasobranteJpaController(emf);

    classusuario cu = new classusuario();
    classTarifassobrante ct=new classTarifassobrante();
    Date date = new Date();
    DefaultTableModel modelo;

    public List<Aguasobrante> getAguasobrante() {
        return aguasobranteJpacontrolador.findAguasobranteEntities();

    }

    public List<Usuarios> getUsuarios() {
        return usuariosJpacontrolador.findUsuariosEntities();
    }

    public List<Aguasobrante> ListaOrdenada() {
        List<Aguasobrante> lista = new ArrayList<>();
        for (Aguasobrante m1 : aguasobranteJpacontrolador.findAguasobranteEntities()) {
            lista.add(m1);
        }
        Collections.sort(lista, new Comparator<Aguasobrante>() {

            @Override
            public int compare(Aguasobrante o1, Aguasobrante o2) {
                return o1.getCodigoaguasobrante().compareTo(o2.getCodigoaguasobrante());
            }
        });
        return lista;
    }

    public void guardarAguasobrante(int idUsuario, String sector, String referencia, String observacion, int codigoaguasobrante, String estado, float valorConexion, Date fecha) {
        int i = 0;
        Aguasobrante med = new Aguasobrante();
        try {
            for (Aguasobrante md : getAguasobrante()) {
                if (md.getCodigoaguasobrante().equals(codigoaguasobrante) || md.getIdusuario().getIdusuario() == (idUsuario)) {
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

                Aguasobrante dat = new Aguasobrante();
                dat.setIdusuario(usua);
                dat.setSector(sector);
                dat.setReferencia(referencia);
                dat.setObservacion(observacion);
                dat.setCodigoaguasobrante(codigoaguasobrante);
                dat.setEstado(estado);
                dat.setValorporconexion(valorConexion);
                dat.setPagado("NO");
                dat.setSaldo(valorConexion);
                dat.setFecha(fecha);
                aguasobranteJpacontrolador.create(dat);
                
                //guardar detalle factura si hay valor mayor a 0
                if(valorConexion>0){
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M");
                    String aniomes = sdf.format(new Date());
                    
                    Tarifassobrante idTar = ct.tarifassobranteJpacontrolador.findTarifassobrante(ct.buscarIdTarifassobrante(1).getIdtarifassobrante());
                    Detallefacturasobrante datos= new Detallefacturasobrante();
                    datos.setAniomes(aniomes);
                    datos.setIdtarifassobrante(idTar);
                    datos.setIdaguasobrante(dat);
                    datos.setSubtotal(valorConexion);
                    datos.setTotal(valorConexion);
                    datos.setObservacion("NO");
                    detallefacturasobranteJpacontrolador.create(datos);
                    JOptionPane.showMessageDialog(null, "Registrado, Factura creada");
                }else{
                    JOptionPane.showMessageDialog(null, "Registrado");
                }
                
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public boolean modificarAguasobrante(int id, int idusuario, String sector, String referencia, String observacion, int codigoaguasobrante, String estado, float valorConexion) {
        Aguasobrante med = new Aguasobrante();

        try {

            Usuarios usua = cu.usuariosJpacontrolador.findUsuarios(cu.buscarLoginId(idusuario).getIdusuario());
            Aguasobrante dat = aguasobranteJpacontrolador.findAguasobrante(id);
            if (dat == null) {
                return false;
            }
            for (Aguasobrante md : getAguasobrante()) {
                if (md.getCodigoaguasobrante().equals(codigoaguasobrante)) {
                    med = md;
                    break;
                }
            }
            
            if (!med.getIdaguasobrante().equals(id)) {
                JOptionPane.showMessageDialog(null, "No se pudo guardar\n Numero de codigo ya existe, por favor ingrese numero diferente", "Informacion", 1);
                med = null;

            } else {
                dat.setIdusuario(usua);
                dat.setSector(sector);
                dat.setReferencia(referencia);
                dat.setObservacion(observacion);
                dat.setCodigoaguasobrante(codigoaguasobrante);
                dat.setEstado(estado);
                dat.setValorporconexion(valorConexion);
                aguasobranteJpacontrolador.edit(dat);
                JOptionPane.showMessageDialog(null, "Se Modifico exitosamente", "Información", 1);
            }

        } catch (Exception e) {
        }
        return true;
    }

    public int medOcupado(int codigoaguasobrante) {
        int r = 0;
        for (Aguasobrante col : getAguasobrante()) {
            if (col.getCodigoaguasobrante() == codigoaguasobrante) {
                r = r + 1;
            }
        }
        return r;
    }

    public boolean modificarValorConexion(int codigoaguasobrante, float valorConexion) {
        Aguasobrante med = new Aguasobrante();

        try {
            Aguasobrante dat = aguasobranteJpacontrolador.findAguasobrante(buscarAguasobranteCodigo(codigoaguasobrante).getIdaguasobrante());
            if (dat == null) {
                return false;
            }

            dat.setSaldo(valorConexion);
            aguasobranteJpacontrolador.edit(dat);

        } catch (Exception e) {
        }
        return true;
    }

    public boolean modificarEstado(int id, String estado) {
        Aguasobrante med = new Aguasobrante();

        try {
            Aguasobrante dat = aguasobranteJpacontrolador.findAguasobrante(id);
            if (dat == null) {
                return false;
            }

            dat.setEstado(estado);
            aguasobranteJpacontrolador.edit(dat);

        } catch (Exception e) {
        }
        return true;
    }

    public void eliminarAguasobrante(int id) throws NonexistentEntityException, IllegalOrphanException {

        try {
            aguasobranteJpacontrolador.destroy(id);
            JOptionPane.showMessageDialog(null, "Se Elimino exitosamente", "Información", 1);
        } catch (NonexistentEntityException ex) {
        }

    }

    public Aguasobrante buscarAguasobranteCodigo(int codigoaguasobrante) {

        for (Aguasobrante dat : getAguasobrante()) {
            if (dat.getCodigoaguasobrante().equals(codigoaguasobrante)) {
                return dat;
            }
        }
        return null;
    }

    //buscar medidor por idUsuario

    public Aguasobrante buscarAguasobranteIdUsuario(int idUsuario) {
        for (Aguasobrante dat : getAguasobrante()) {
            if (dat.getIdusuario().getIdusuario().equals(idUsuario)) {
                System.out.println(dat);
                return dat;
            }
        }
        return null;
    }

    //buscar Aguasobrante por idAguasobrante

    public Aguasobrante buscarAguasobranteId(int idAguasobrante) {

        for (Aguasobrante dat : getAguasobrante()) {
            if (dat.getIdaguasobrante().equals(idAguasobrante)) {
                return dat;
            }
        }
        return null;
    }

    public void cargarTablaAguasobrante(JTable tabla) {
        tabla.setDefaultRenderer(Object.class, new Render());
        DefaultTableModel modelo = new DefaultTableModel() {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        modelo.addColumn("id aguasobrante");
        modelo.addColumn("id usuario");
        modelo.addColumn("#");
        modelo.addColumn("Cedula");
        modelo.addColumn("Usuario");
        modelo.addColumn("Apodo");
        modelo.addColumn("estado");
        modelo.addColumn("Codigo usuario");

        Object[] fila = new Object[8];
        Integer i = 1;
        for (Aguasobrante u : ListaOrdenada()) {
            fila[0] = u.getIdaguasobrante();
            fila[1] = u.getIdusuario().getIdusuario();
            fila[2] = i;

            fila[3] = u.getIdusuario().getRucci();
            fila[4] = u.getIdusuario().getPrimerapellido() + "  " + u.getIdusuario().getSegundoapellido() + "  "
                    + u.getIdusuario().getPrimernombre() + "  " + u.getIdusuario().getSegundonombre();
            fila[5] = u.getIdusuario().getApadosn();
            fila[6] = u.getEstado();
            fila[7] = u.getCodigoaguasobrante();

            //fila[6] = u.getIdusuario().getIdusuario();
            //FrmMedidor.label.setText(u.getIdusuario().getIdusuario().toString());
            modelo.addRow(fila);
            //tabla.setRowHeight(20);
            i++;
        }
        tabla.setModel(modelo);
        /*
         FrmAguasobrante.jTable1.getTableHeader().getColumnModel().getColumn(0).setMinWidth(0);
         FrmAguasobrante.jTable1.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
        
         FrmAguasobrante.jTable1.getTableHeader().getColumnModel().getColumn(1).setMinWidth(0);
         FrmAguasobrante.jTable1.getTableHeader().getColumnModel().getColumn(1).setMaxWidth(0);

         FrmAguasobrante.jTable1.getTableHeader().getColumnModel().getColumn(2).setMinWidth(20);
         FrmAguasobrante.jTable1.getTableHeader().getColumnModel().getColumn(2).setMaxWidth(40);

         FrmAguasobrante.jTable1.getTableHeader().getColumnModel().getColumn(3).setMinWidth(0);
         FrmAguasobrante.jTable1.getTableHeader().getColumnModel().getColumn(3).setMaxWidth(100);

         FrmAguasobrante.jTable1.getTableHeader().getColumnModel().getColumn(6).setMaxWidth(70);

         FrmAguasobrante.jTable1.getTableHeader().getColumnModel().getColumn(7).setMinWidth(15);
         FrmAguasobrante.jTable1.getTableHeader().getColumnModel().getColumn(7).setMaxWidth(70);
         */
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
        for (Aguasobrante u : ListaOrdenada()) {
            if (u.getIdusuario().buscarUsuarios(u.getIdusuario().elimiEspacio(txt))) {
                fila[0] = u.getIdaguasobrante();
                fila[1] = u.getIdusuario().getRucci();
                fila[2] = u.getIdusuario().getPrimerapellido() + "  " + u.getIdusuario().getSegundoapellido() + "  "
                        + u.getIdusuario().getPrimernombre() + "  " + u.getIdusuario().getSegundonombre();
                fila[3] = u.getIdusuario().getApadosn();
                fila[5] = u.getEstado();
                fila[6] = u.getCodigoaguasobrante();

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

    public TableModel aguasobranteTabla(JTable tabla) {
        DefaultTableModel dtm = (DefaultTableModel) tabla.getModel();
        dtm.setRowCount(0);
        diseñoTabla(tabla);
        for (Aguasobrante u : ListaOrdenada()) {
            dtm.addRow(new Object[]{
                u.getIdaguasobrante(),
                u.getCodigoaguasobrante(),
                u.getIdusuario().getPrimerapellido() + "  " + u.getIdusuario().getSegundoapellido() + "  "
                + u.getIdusuario().getPrimernombre() + "  " + u.getIdusuario().getSegundonombre(),
                u.getIdusuario().getApadosn(),
                u.getEstado(),
                u.getIdusuario().getIdusuario()
            });
        }
        return dtm;
    }

    public List<Aguasobrante> getbuscarAguasobrante() {
        try {
            List<Aguasobrante> datos = new ArrayList<Aguasobrante>();
            for (Aguasobrante e : getAguasobrante()) {
                datos.add(e);
            }
            return datos;
        } catch (Exception e) {
        }
        return null;
    }

    public List<Usuarios> getUsuariosSinAguasobrante() {
        List<Usuarios> user = new ArrayList<Usuarios>();
        
        int i = 0;
        for (Usuarios us : getUsuarios()) {
            for (Aguasobrante dataguaso : getAguasobrante()) {
                //System.out.println(us.getIdusuario()+" "+dataguaso.getIdusuario().getIdusuario());
                if (dataguaso.getIdusuario().getIdusuario().equals(us.getIdusuario())) {
                    i = dataguaso.getIdusuario().getIdusuario();
                }
            }
            System.out.println(i);
            if(i!=us.getIdusuario()){
                user.add(us);
            }
        }
        System.out.println(user);
        return user;
    }

    public void cargarTablaUsuarioSinAguasobrante(JTable tabla) {

        modelo = new DefaultTableModel();
        tabla.setModel(modelo);
        Object[] fila = new Object[10];
        modelo.addColumn("N°");//id
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
        for (Usuarios u : getUsuariosSinAguasobrante()) {
            fila[0] = u.getIdusuario();
            fila[1] = u.getRucci();
            fila[2] = u.getPrimernombre() + " " + u.getSegundonombre() + " " + u.getPrimerapellido() + " " + u.getSegundoapellido();
            fila[3] = u.getApadosn();
            fila[4] = u.getDireccion();
            fila[5] = u.getTelefono();
            fila[6] = u.getCelular();
            fila[7] = u.getSector();
            fila[8] = u.getReferencia();
            fila[9] = u.getObservacion();

            modelo.addRow(fila);
        }
        FrmListaUsuarioSinAguasobrante.jTable1.getTableHeader().getColumnModel().getColumn(0).setMinWidth(35);
        FrmListaUsuarioSinAguasobrante.jTable1.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(40);
        FrmListaUsuarioSinAguasobrante.jTable1.getTableHeader().getColumnModel().getColumn(1).setMinWidth(75);
        FrmListaUsuarioSinAguasobrante.jTable1.getTableHeader().getColumnModel().getColumn(1).setMaxWidth(80);
        FrmListaUsuarioSinAguasobrante.jTable1.getTableHeader().getColumnModel().getColumn(4).setMinWidth(0);
        FrmListaUsuarioSinAguasobrante.jTable1.getTableHeader().getColumnModel().getColumn(4).setMaxWidth(0);
        FrmListaUsuarioSinAguasobrante.jTable1.getTableHeader().getColumnModel().getColumn(5).setMinWidth(0);
        FrmListaUsuarioSinAguasobrante.jTable1.getTableHeader().getColumnModel().getColumn(5).setMaxWidth(0);
        FrmListaUsuarioSinAguasobrante.jTable1.getTableHeader().getColumnModel().getColumn(6).setMinWidth(0);
        FrmListaUsuarioSinAguasobrante.jTable1.getTableHeader().getColumnModel().getColumn(6).setMaxWidth(0);
        FrmListaUsuarioSinAguasobrante.jTable1.getTableHeader().getColumnModel().getColumn(7).setMinWidth(0);
        FrmListaUsuarioSinAguasobrante.jTable1.getTableHeader().getColumnModel().getColumn(7).setMaxWidth(0);
        FrmListaUsuarioSinAguasobrante.jTable1.getTableHeader().getColumnModel().getColumn(8).setMinWidth(0);
        FrmListaUsuarioSinAguasobrante.jTable1.getTableHeader().getColumnModel().getColumn(8).setMaxWidth(0);
        FrmListaUsuarioSinAguasobrante.jTable1.getTableHeader().getColumnModel().getColumn(9).setMinWidth(0);
        FrmListaUsuarioSinAguasobrante.jTable1.getTableHeader().getColumnModel().getColumn(9).setMaxWidth(0);
    }
}
