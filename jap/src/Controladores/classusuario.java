/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Formularios.FrmBusqueda;
import Formularios.FrmListaUsuarioSinMedidor;
import Formularios.FrmUsuario;
import entidades.Institucion;
import entidades.Medidor;
import entidades.Usuarios;
import entidadesCruds.MedidorJpaController;
import entidadesCruds.UsuariosJpaController;
import entidadesCruds.exceptions.IllegalOrphanException;
import entidadesCruds.exceptions.NonexistentEntityException;
import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author JC-PC
 */
public class classusuario {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("japPU");
    public UsuariosJpaController usuariosJpacontrolador = new UsuariosJpaController(emf);
    public MedidorJpaController medidorJpacontrolador = new MedidorJpaController(emf);
    
    classInstitucion ci = new classInstitucion();
    FileInputStream entrada;
    FileOutputStream salida;
    DefaultTableModel modelo;
    File archivo;
    Date date = new Date();

    public List<Usuarios> getUsuarios() {
        return usuariosJpacontrolador.findUsuariosEntities();
    }
    public List<Medidor> getMedidor() {
        return medidorJpacontrolador.findMedidorEntities();
    }
    public List<Usuarios> getUsuariosSinMedidor() {
        List<Usuarios> user = new ArrayList<Usuarios>();
        for (Usuarios us : getUsuarios()) {
            if(us.getEstadoMedidor().equals("SIN MEDIDOR")){
                user.add(us);
            }
            
        }
        return user;
    }

    public void guardarUsuarios(String nombreInst, String rucCi, String priNombre, String segNombre, String priApellido, String segApellido, String apodoNS, String direccion, String telefono, String celular, String sector, String referencia, String observacion) {
        int i = 0;
        //obtener fecha actual
        DateFormat hourdateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        //System.out.println("Hora y fecha: "+hourdateFormat.format(date));

        try {
            for (Usuarios us : getUsuarios()) {
                if (!rucCi.equals("")) {
                    if (us.getRucci().equals(rucCi)) {
                        i = 1;
                        break;
                    }
                }
            }
            if (i == 1) {
                JOptionPane.showMessageDialog(null, "Cédula de identidad o RUC ya Existente, debe ingresar un usuario no existente", "Información", 1);
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
                //datos de control
                dat.setEstado("1");
                dat.setVisto("0");
                dat.setEstadoMedidor("SIN MEDIDOR");
                dat.setCreatedAt(date);
                dat.setUpdatedAt(date);
                
                usuariosJpacontrolador.create(dat);
                JOptionPane.showMessageDialog(null, "Guardado correctamente", "Informacion", 1);

            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public boolean modificarUsuario(int id, String rucCi, String priNombre, String segNombre, String priApellido, String segApellido, String apodoNS, String direccion, String telefono, String celular, String sector, String referencia, String observacion) {
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
            //datos de control
            dat.setUpdatedAt(date);
            
            usuariosJpacontrolador.edit(dat);
            JOptionPane.showMessageDialog(null, "El Usuario se Modifico exitosamente", "Información", 1);

        } catch (Exception e) {
        }
        return true;
    }

    public void eliminarUsuario(int id) throws NonexistentEntityException, IllegalOrphanException {

        usuariosJpacontrolador.destroy(id);
        JOptionPane.showMessageDialog(null, "El Usuario se Elimino exitosamente", "Informacion", 1);

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

    public void cargarTablaUsuario2(JTable tabla,String cedula,String apellidos,String apodo) {
        tabla.setDefaultRenderer(Object.class, new Render());
        DefaultTableModel modelo = new DefaultTableModel(){
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        modelo.addColumn("id");
        modelo.addColumn("#");
        modelo.addColumn("CI/RUC");
        modelo.addColumn("Nombres");
        modelo.addColumn("Apodo");
        modelo.addColumn("medidor");
        modelo.addColumn("Nº");
        modelo.addColumn("sector");
        
        modelo.addColumn("Ver");
        modelo.addColumn("Editar");
        modelo.addColumn("Eliminar");
        
        JButton btn_addmed = new JButton("Agregar Medidor");
        btn_addmed.setName("btnAgregarMedidor");
        
        JButton btn_ver = new JButton("Ver");
        btn_ver.setName("btnVer");
        
        JButton btn_editar = new JButton("Editar");
        btn_editar.setName("btnEditar");
        
        JButton btn_eliminar = new JButton("Eliminar");
        btn_eliminar.setName("btnEliminar");
        
        Object[] fila = new Object[11];
        List<Usuarios> getUsuarios=buscarUsuario(cedula, apellidos, apodo);
        int i=1;
        for (Usuarios u : getUsuarios) {
            fila[0] = u.getIdusuario();
            fila[1] = i;
            fila[2] = u.getRucci();
            fila[3] = u.getPrimerapellido() + "  " + u.getSegundoapellido() + "  "
                    + u.getPrimernombre() + "  " + u.getSegundonombre();
            fila[4] = u.getApadosn();
            
            fila[5] = u.getEstadoMedidor();
            /*
            if(u.getEstadoMedidor().equals("SIN MEDIDOR")){
                fila[5] = btn_addmed;
            }
            else{
                fila[5] = u.getEstadoMedidor();
            }
            */
            fila[6] = u.getNummedidor();
            fila[7] = u.getSector();
            
            fila[8] = btn_ver;
            fila[9] = btn_editar;
            fila[10] = btn_eliminar;
            
            
            
            modelo.addRow(fila);
            i++;
        }
        tabla.setModel(modelo);
        
        FrmUsuario.tablaUsuarios.getTableHeader().getColumnModel().getColumn(0).setMinWidth(0);
        FrmUsuario.tablaUsuarios.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
        
        FrmUsuario.tablaUsuarios.getTableHeader().getColumnModel().getColumn(1).setMinWidth(10);
        FrmUsuario.tablaUsuarios.getTableHeader().getColumnModel().getColumn(1).setMaxWidth(35);
        
        FrmUsuario.tablaUsuarios.getTableHeader().getColumnModel().getColumn(2).setMinWidth(15);
        FrmUsuario.tablaUsuarios.getTableHeader().getColumnModel().getColumn(2).setMaxWidth(70);
        
        //FrmUsuario.tablaUsuarios.getTableHeader().getColumnModel().getColumn(2).setMinWidth(0);
        //FrmUsuario.tablaUsuarios.getTableHeader().getColumnModel().getColumn(2).setMaxWidth(300);
        
        FrmUsuario.tablaUsuarios.getTableHeader().getColumnModel().getColumn(4).setMinWidth(10);
        FrmUsuario.tablaUsuarios.getTableHeader().getColumnModel().getColumn(4).setMaxWidth(70);
        
        FrmUsuario.tablaUsuarios.getTableHeader().getColumnModel().getColumn(5).setMinWidth(15);
        FrmUsuario.tablaUsuarios.getTableHeader().getColumnModel().getColumn(5).setMaxWidth(75);
        
        //FrmUsuario.tablaUsuarios.getTableHeader().getColumnModel().getColumn(5).setMinWidth(15);
        FrmUsuario.tablaUsuarios.getTableHeader().getColumnModel().getColumn(6).setMaxWidth(35);
        
        FrmUsuario.tablaUsuarios.getTableHeader().getColumnModel().getColumn(7).setMinWidth(70);
        FrmUsuario.tablaUsuarios.getTableHeader().getColumnModel().getColumn(7).setMaxWidth(70);

        FrmUsuario.tablaUsuarios.getTableHeader().getColumnModel().getColumn(8).setMinWidth(70);
        FrmUsuario.tablaUsuarios.getTableHeader().getColumnModel().getColumn(8).setMaxWidth(70);
        
        FrmUsuario.tablaUsuarios.getTableHeader().getColumnModel().getColumn(9).setMinWidth(70);
        FrmUsuario.tablaUsuarios.getTableHeader().getColumnModel().getColumn(9).setMaxWidth(70);
        
        FrmUsuario.tablaUsuarios.getTableHeader().getColumnModel().getColumn(10).setMinWidth(70);
        FrmUsuario.tablaUsuarios.getTableHeader().getColumnModel().getColumn(10).setMaxWidth(70);
        
        JTableHeader anHeader = FrmUsuario.tablaUsuarios.getTableHeader();
        anHeader.setForeground(new Color(0).black);
    }

    public List<Usuarios> buscarUsuario(String cedula,String apellidos,String apodo){
        EntityManager em=usuariosJpacontrolador.getEntityManager();
        List<Usuarios> lista=new ArrayList<Usuarios>();
        Usuarios u =new Usuarios();
        
        if(!cedula.equals("")){
            Query query=em.createQuery("SELECT p FROM Usuarios p WHERE p.rucci LIKE :rucci");
            query.setParameter("rucci", cedula+"%");
            lista = query.getResultList();
        }
        else if(!apellidos.equals("")){
            String a[] = apellidos.split(" ");
            String salida[]={"","","",""};
            if(a.length==1){
                salida[0]=a[0];
            }
            else if(a.length==2){
                salida[1]=a[1];
            }
            else if(a.length==3){
                salida[2]=a[2];
            }
            else if(a.length==4){
                salida[3]=a[3];
            }
            
            Query query=em.createQuery("SELECT p FROM Usuarios p WHERE p.primerapellido LIKE :primerapellido AND p.segundoapellido LIKE :segundoapellido AND p.primernombre LIKE :primernombre AND p.segundonombre LIKE :segundonombre");
            query.setParameter("primerapellido", salida[0]+"%");
            query.setParameter("segundoapellido", salida[1]+"%");
            query.setParameter("primernombre", salida[2]+"%");
            query.setParameter("segundonombre", salida[3]+"%");
            //SELECT p FROM Usuarios p WHERE p.primerapellido LIKE 'tocagon%' AND p.segundoapellido LIKE 'tocagon%' AND p.primernombre LIKE 'marco%'
            //AND p.segundonombre LIKE 'benjamin%'
            lista = query.getResultList();
        }
        else if(!apodo.equals("")){
            Query query=em.createQuery("SELECT p FROM Usuarios p WHERE p.apadosn LIKE :apadosn");
            query.setParameter("apadosn", apodo+"%");
            lista = query.getResultList();
        }
        else{
            Query query=em.createQuery("SELECT p FROM Usuarios p");
            lista = query.getResultList();
        }
        
        return lista;
    }
    
    public void cargarTablaUsuario(JTable tabla) {

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
        for (Usuarios u : getUsuarios()) {
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
            FrmBusqueda.jTable1.getTableHeader().getColumnModel().getColumn(0).setMinWidth(35);
            FrmBusqueda.jTable1.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(40);
            FrmBusqueda.jTable1.getTableHeader().getColumnModel().getColumn(1).setMinWidth(75);
            FrmBusqueda.jTable1.getTableHeader().getColumnModel().getColumn(1).setMaxWidth(80);
            FrmBusqueda.jTable1.getTableHeader().getColumnModel().getColumn(4).setMinWidth(0);
            FrmBusqueda.jTable1.getTableHeader().getColumnModel().getColumn(4).setMaxWidth(0);
            FrmBusqueda.jTable1.getTableHeader().getColumnModel().getColumn(5).setMinWidth(0);
            FrmBusqueda.jTable1.getTableHeader().getColumnModel().getColumn(5).setMaxWidth(0);
            FrmBusqueda.jTable1.getTableHeader().getColumnModel().getColumn(6).setMinWidth(0);
            FrmBusqueda.jTable1.getTableHeader().getColumnModel().getColumn(6).setMaxWidth(0);
            FrmBusqueda.jTable1.getTableHeader().getColumnModel().getColumn(7).setMinWidth(0);
            FrmBusqueda.jTable1.getTableHeader().getColumnModel().getColumn(7).setMaxWidth(0);
            FrmBusqueda.jTable1.getTableHeader().getColumnModel().getColumn(8).setMinWidth(0);
            FrmBusqueda.jTable1.getTableHeader().getColumnModel().getColumn(8).setMaxWidth(0);
            FrmBusqueda.jTable1.getTableHeader().getColumnModel().getColumn(9).setMinWidth(0);
            FrmBusqueda.jTable1.getTableHeader().getColumnModel().getColumn(9).setMaxWidth(0);
            modelo.addRow(fila);
        }
    }
    
    public void cargarTablaUsuarioSinMedidor(JTable tabla) {

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
        for (Usuarios u : getUsuariosSinMedidor()) {
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
        FrmListaUsuarioSinMedidor.jTable1.getTableHeader().getColumnModel().getColumn(0).setMinWidth(35);
        FrmListaUsuarioSinMedidor.jTable1.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(40);
        FrmListaUsuarioSinMedidor.jTable1.getTableHeader().getColumnModel().getColumn(1).setMinWidth(75);
        FrmListaUsuarioSinMedidor.jTable1.getTableHeader().getColumnModel().getColumn(1).setMaxWidth(80);
        FrmListaUsuarioSinMedidor.jTable1.getTableHeader().getColumnModel().getColumn(4).setMinWidth(0);
        FrmListaUsuarioSinMedidor.jTable1.getTableHeader().getColumnModel().getColumn(4).setMaxWidth(0);
        FrmListaUsuarioSinMedidor.jTable1.getTableHeader().getColumnModel().getColumn(5).setMinWidth(0);
        FrmListaUsuarioSinMedidor.jTable1.getTableHeader().getColumnModel().getColumn(5).setMaxWidth(0);
        FrmListaUsuarioSinMedidor.jTable1.getTableHeader().getColumnModel().getColumn(6).setMinWidth(0);
        FrmListaUsuarioSinMedidor.jTable1.getTableHeader().getColumnModel().getColumn(6).setMaxWidth(0);
        FrmListaUsuarioSinMedidor.jTable1.getTableHeader().getColumnModel().getColumn(7).setMinWidth(0);
        FrmListaUsuarioSinMedidor.jTable1.getTableHeader().getColumnModel().getColumn(7).setMaxWidth(0);
        FrmListaUsuarioSinMedidor.jTable1.getTableHeader().getColumnModel().getColumn(8).setMinWidth(0);
        FrmListaUsuarioSinMedidor.jTable1.getTableHeader().getColumnModel().getColumn(8).setMaxWidth(0);
        FrmListaUsuarioSinMedidor.jTable1.getTableHeader().getColumnModel().getColumn(9).setMinWidth(0);
        FrmListaUsuarioSinMedidor.jTable1.getTableHeader().getColumnModel().getColumn(9).setMaxWidth(0);
    }
    
    public void cargarTablaUsuarioBuscar(JTable tabla, String nombres) {

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
        for (Usuarios u : getUsuarios()) {
            if (u.buscarUsuarios(u.elimiEspacio(nombres))) {
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
                FrmBusqueda.jTable1.getTableHeader().getColumnModel().getColumn(0).setMinWidth(35);
                FrmBusqueda.jTable1.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(40);
                FrmBusqueda.jTable1.getTableHeader().getColumnModel().getColumn(1).setMinWidth(75);
                FrmBusqueda.jTable1.getTableHeader().getColumnModel().getColumn(1).setMaxWidth(80);
                FrmBusqueda.jTable1.getTableHeader().getColumnModel().getColumn(4).setMinWidth(0);
                FrmBusqueda.jTable1.getTableHeader().getColumnModel().getColumn(4).setMaxWidth(0);
                FrmBusqueda.jTable1.getTableHeader().getColumnModel().getColumn(5).setMinWidth(0);
                FrmBusqueda.jTable1.getTableHeader().getColumnModel().getColumn(5).setMaxWidth(0);
                FrmBusqueda.jTable1.getTableHeader().getColumnModel().getColumn(6).setMinWidth(0);
                FrmBusqueda.jTable1.getTableHeader().getColumnModel().getColumn(6).setMaxWidth(0);
                FrmBusqueda.jTable1.getTableHeader().getColumnModel().getColumn(7).setMinWidth(0);
                FrmBusqueda.jTable1.getTableHeader().getColumnModel().getColumn(7).setMaxWidth(0);
                FrmBusqueda.jTable1.getTableHeader().getColumnModel().getColumn(8).setMinWidth(0);
                FrmBusqueda.jTable1.getTableHeader().getColumnModel().getColumn(8).setMaxWidth(0);
                FrmBusqueda.jTable1.getTableHeader().getColumnModel().getColumn(9).setMinWidth(0);
                FrmBusqueda.jTable1.getTableHeader().getColumnModel().getColumn(9).setMaxWidth(0);
                modelo.addRow(fila);
            }
        }
    }
    
    public void actualizarUsuarioEstadomedidor(){
        for (Usuarios u : getUsuarios()) {
            for (Medidor e : getMedidor()) {
                if(u.getIdusuario()==e.getIdusuario().getIdusuario()){
                    if(u.getEstadoMedidor()==null||u.getEstadoMedidor().equals("")){
                        try {
                            Usuarios dat = usuariosJpacontrolador.findUsuarios(u.getIdusuario());
                            dat.setEstadoMedidor(e.getEstado());
                            dat.setNummedidor(e.getNummedidor().toString());
                            if(e.getEstado().equals("ACTIVO")){
                                dat.setEstado("1");
                                dat.setVisto("1");
                                dat.setCreatedAt(e.getFecha());
                                dat.setUpdatedAt(date);
                            }else{
                                dat.setEstado("0");
                                dat.setVisto("3");
                                dat.setCreatedAt(e.getFecha());
                                dat.setUpdatedAt(date);
                            }
                            usuariosJpacontrolador.edit(dat);
                            System.out.println("Usuario "+ dat.getIdusuario()+" actualizado");
                        } catch (Exception ex) {
                            System.out.println("Error al actualizar" + ex.getMessage());
                        }
                    }
                    break;
                }
            }
        }
        for (Usuarios u : getUsuarios()) {
            if(u.getEstadoMedidor()==null||u.getEstadoMedidor().equals("")){
                try {
                    System.out.println("Usuario "+u.getIdusuario()+" sin medidor");
                    Usuarios dat = usuariosJpacontrolador.findUsuarios(u.getIdusuario());
                    dat.setEstadoMedidor("SIN MEDIDOR");
                    dat.setEstado("1");
                    dat.setVisto("0");
                    dat.setCreatedAt(date);
                    dat.setUpdatedAt(date);
                            
                    usuariosJpacontrolador.edit(dat);
                } catch (Exception e) {
                }
            }
        }
        System.out.println("Usuarios actualizados");
    }
}
