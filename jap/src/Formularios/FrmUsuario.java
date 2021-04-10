/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Formularios;

import Controladores.ControlFormularios;
import Controladores.ValidarCedula;
import Controladores.classDisenio;
import Controladores.classInstitucion;
import Controladores.classMoverRegistros;
import Controladores.classusuario;
import entidades.Usuarios;
import entidadesCruds.exceptions.IllegalOrphanException;
import jap.ReportesControlador;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author Marco
 */
public class FrmUsuario extends javax.swing.JInternalFrame {

    /**
     * Creates new form FrmUsuario
     */
    ValidarCedula valCed = new ValidarCedula();
    classInstitucion ci = new classInstitucion();
    classusuario cu = new classusuario();
    Usuarios us=new Usuarios();

    public FrmUsuario() {
        initComponents();
        inicio();
    }
    classMoverRegistros moverRegistros = new classMoverRegistros(cu.getUsuarios());
    ControlFormularios cf;
    ReportesControlador b = new ReportesControlador();

    public void inicio() {
        Dimension desktopSize = FrmPrincipal.jDesktopPane1.getSize();
        Dimension jInternalFrameSize = this.getSize();
        this.setLocation((desktopSize.width - jInternalFrameSize.width) / 2, 4);
        
        jLabel9.setText(ci.buscarIdInstitucion(1).getNombreinst().toString());

        //jLabel1.setVisible(false);
        //id.setVisible(false);
        
        cu.cargarTablaUsuario2(tablaUsuarios,"","","");
        txtRuc.setToolTipText(classDisenio.txthtml("Ingrese su numero de cedula, 10 digitos"));
        txtDir.setToolTipText(classDisenio.txthtml("Ingrese la direccion de domicilio"));
        txtNombre.setToolTipText(classDisenio.txthtml("Ingrese primer nombre, nombre paterno"));
        txtNombre2.setToolTipText(classDisenio.txthtml("ingrese segundo nombre, nombre materno"));
        txtApellido.setToolTipText(classDisenio.txthtml("Ingrese primer apellido, apellido paterno"));
        txtApellido2.setToolTipText(classDisenio.txthtml("Ingrese segundo apellido, apellido materno"));
        txtApodo.setToolTipText(classDisenio.txthtml("Ingrse apodo"));
        txtTelefono.setToolTipText(classDisenio.txthtml("Ingrese telefono fijo 9 digitos"));
        txtCelular.setToolTipText(classDisenio.txthtml("Ingrese numero de celular 10 digitos"));
        comboSector.setToolTipText(classDisenio.txthtml("Seleccione el sector donde vive"));
        txtReferencia.setToolTipText(classDisenio.txthtml("Referencia ejemplo: vive a lado de la pana"));
        txtObservar.setToolTipText(classDisenio.txthtml("Observacion ejemplo: tercera edad, capacidades especiales, madre soltera"));
        txtBuscar.setToolTipText(classDisenio.txthtml("Selecciona que tipo de busqueda realizar y digite la forma de busqueda seleccionada"));
        tablaUsuarios.setToolTipText(classDisenio.tablahtml("Lista de tablas de usuarios"));
    }

    public void mostrar() {
        txtRuc.setEditable(true);
        txtDir.setEditable(true);
        txtNombre.setEditable(true);
        txtNombre2.setEditable(true);
        txtApellido.setEditable(true);
        txtApellido2.setEditable(true);
        txtApodo.setEditable(true);
        txtTelefono.setEditable(true);
        txtCelular.setEditable(true);
        txtReferencia.setEditable(true);
        txtObservar.setEditable(true);
        comboSector.setEnabled(true);
        comboSector.setSelectedIndex(0);
        btnGuardar.setEnabled(true);
        btnCancelar.setEnabled(true);
    }

    public void ocultar() {
        txtRuc.setEditable(false);
        txtDir.setEditable(false);
        txtNombre.setEditable(false);
        txtNombre2.setEditable(false);
        txtApellido.setEditable(false);
        txtApellido2.setEditable(false);
        txtApodo.setEditable(false);
        txtTelefono.setEditable(false);
        txtCelular.setEditable(false);
        txtReferencia.setEditable(false);
        txtObservar.setEditable(false);
        comboSector.setEnabled(false);
        btnGuardar.setEnabled(false);
        btnCancelar.setEnabled(false);
    }

    public void limpiar() {
        id.setText("");
        txtRuc.setText("");
        txtDir.setText("");
        txtNombre.setText("");
        txtNombre2.setText("");
        txtApellido.setText("");
        txtApellido2.setText("");
        txtTelefono.setText("");
        txtCelular.setText("");
        txtReferencia.setText("");
        txtObservar.setText("");
        txtApodo.setText("");
        comboSector.setSelectedIndex(0);
    }

    private boolean letras(String str) {
        boolean respuesta = false;
        if ((str).matches("([a-z]|[A-Z])+")) {
            respuesta = true;
        }
        return respuesta;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        busquedaGrupo = new javax.swing.ButtonGroup();
        jPanel3 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtRuc = new javax.swing.JTextField();
        txtDir = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        txtNombre2 = new javax.swing.JTextField();
        txtApellido = new javax.swing.JTextField();
        txtApellido2 = new javax.swing.JTextField();
        txtApodo = new javax.swing.JTextField();
        comboSector = new org.edisoncor.gui.comboBox.ComboBoxRect();
        label = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        id = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        txtCelular = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtTelefono = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtReferencia = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtObservar = new javax.swing.JTextArea();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaUsuarios = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false; //Disallow the editing of any cell
            }
        };
        jLabel10 = new javax.swing.JLabel();
        rbtNombres = new javax.swing.JRadioButton();
        rbtCedula = new javax.swing.JRadioButton();
        txtBuscar = new javax.swing.JTextField();
        rbtApodo = new javax.swing.JRadioButton();
        jPanel4 = new javax.swing.JPanel();
        btnNuevo = new org.edisoncor.gui.button.ButtonNice();
        btnGuardar = new org.edisoncor.gui.button.ButtonNice();
        btnCancelar = new org.edisoncor.gui.button.ButtonNice();
        mensaje = new javax.swing.JLabel();
        btnNuevo1 = new org.edisoncor.gui.button.ButtonNice();
        jLabel9 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("FORMULARIO USUARIOS");

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("usuario Nuevo"));
        jPanel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel2.setText("RUC / CI:");

        jLabel3.setText("Primer Nombre:");

        jLabel4.setText("Segundo Nombre:");

        jLabel5.setText("Primer Apellido:");

        jLabel6.setText("Segundo Apellido:");

        jLabel7.setText("Apodo:");

        jLabel11.setText("Direccion");

        jLabel13.setText("Sector:");

        txtRuc.setEditable(false);
        txtRuc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtRucKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtRucKeyTyped(evt);
            }
        });

        txtDir.setEditable(false);

        txtNombre.setEditable(false);
        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNombreKeyReleased(evt);
            }
        });

        txtNombre2.setEditable(false);
        txtNombre2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNombre2KeyReleased(evt);
            }
        });

        txtApellido.setEditable(false);
        txtApellido.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtApellidoKeyReleased(evt);
            }
        });

        txtApellido2.setEditable(false);
        txtApellido2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtApellido2KeyReleased(evt);
            }
        });

        txtApodo.setEditable(false);

        comboSector.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione", "Capillapamba", "Coragaloma", "Tocagon Alto", "Pilchibuela" }));
        comboSector.setEnabled(false);
        comboSector.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N

        label.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabel1.setText("Id:");

        jLabel12.setText("Celular:");

        txtCelular.setEditable(false);
        txtCelular.setToolTipText("Ingrese 10 digitos");
        txtCelular.setAutoscrolls(false);
        txtCelular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCelularActionPerformed(evt);
            }
        });
        txtCelular.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCelularKeyReleased(evt);
            }
        });

        jLabel8.setText("telefono:");

        txtTelefono.setEditable(false);
        txtTelefono.setToolTipText("Ingrese 7 digitos");
        txtTelefono.setAutoscrolls(false);
        txtTelefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTelefonoKeyReleased(evt);
            }
        });

        jLabel14.setText("Referencia:");

        txtReferencia.setEditable(false);
        txtReferencia.setToolTipText("Ingrese Lugar de Referemcia: ej. Cerca de la Escuela");

        jLabel15.setText("Observacion:");

        txtObservar.setEditable(false);
        txtObservar.setColumns(20);
        txtObservar.setRows(5);
        jScrollPane2.setViewportView(txtObservar);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14)
                    .addComponent(jLabel12)
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(txtCelular, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtReferencia))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtCelular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(txtReferencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtApodo, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtApellido2, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtNombre2, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel13))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtRuc)
                            .addComponent(comboSector, javax.swing.GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE))
                        .addGap(72, 72, 72)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(id, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtDir, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(label, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(id, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jLabel2)
                        .addGap(13, 13, 13)
                        .addComponent(jLabel13))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(txtRuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addComponent(comboSector, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtNombre2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(txtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtApellido2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtApodo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtDir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(151, 151, 151)
                        .addComponent(label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Usuarios Actuales"));

        tablaUsuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tablaUsuarios.setRowHeight(25);
        tablaUsuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaUsuariosMouseClicked(evt);
            }
        });
        tablaUsuarios.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tablaUsuariosKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tablaUsuarios);

        jLabel10.setText("Búsqueda:");

        busquedaGrupo.add(rbtNombres);
        rbtNombres.setText("Apellidos - Nombres");
        rbtNombres.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtNombresActionPerformed(evt);
            }
        });

        busquedaGrupo.add(rbtCedula);
        rbtCedula.setSelected(true);
        rbtCedula.setText("Cedula");
        rbtCedula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtCedulaActionPerformed(evt);
            }
        });

        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarKeyReleased(evt);
            }
        });

        busquedaGrupo.add(rbtApodo);
        rbtApodo.setText("Apodo");
        rbtApodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtApodoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(rbtCedula)
                                .addGap(18, 18, 18)
                                .addComponent(rbtNombres)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(rbtApodo))
                            .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 431, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbtNombres)
                    .addComponent(rbtCedula)
                    .addComponent(rbtApodo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 388, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnNuevo.setBackground(new java.awt.Color(0, 102, 255));
        btnNuevo.setForeground(new java.awt.Color(255, 255, 255));
        btnNuevo.setText("NUEVO");
        btnNuevo.setModelo(org.edisoncor.gui.button.ButtonNice.Modelo.ROUND_LEFT);
        btnNuevo.setSegundoColor(new java.awt.Color(0, 51, 255));
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        btnGuardar.setBackground(new java.awt.Color(0, 102, 255));
        btnGuardar.setForeground(new java.awt.Color(255, 255, 255));
        btnGuardar.setText("GUARDAR");
        btnGuardar.setEnabled(false);
        btnGuardar.setModelo(org.edisoncor.gui.button.ButtonNice.Modelo.RECT);
        btnGuardar.setSegundoColor(new java.awt.Color(0, 51, 255));
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnCancelar.setBackground(new java.awt.Color(0, 102, 255));
        btnCancelar.setForeground(new java.awt.Color(255, 255, 255));
        btnCancelar.setText("CANCELAR");
        btnCancelar.setEnabled(false);
        btnCancelar.setModelo(org.edisoncor.gui.button.ButtonNice.Modelo.ROUND_RIGHT);
        btnCancelar.setSegundoColor(new java.awt.Color(0, 51, 255));
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        mensaje.setForeground(new java.awt.Color(204, 0, 0));

        btnNuevo1.setBackground(new java.awt.Color(157, 55, 55));
        btnNuevo1.setForeground(new java.awt.Color(255, 255, 255));
        btnNuevo1.setText("REPORTES");
        btnNuevo1.setSegundoColor(new java.awt.Color(187, 105, 115));
        btnNuevo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevo1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnNuevo1, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(mensaje, javax.swing.GroupLayout.PREFERRED_SIZE, 466, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(mensaje, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnNuevo1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jLabel9.setText("Nombre institucion");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(323, 323, 323)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 376, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(606, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 434, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addGap(10, 10, 10)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtCelularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCelularActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCelularActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        limpiar();
        mostrar();
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed

        ocultar();
        limpiar();
        btnNuevo.setEnabled(true);
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void txtRucKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRucKeyTyped
        char c = evt.getKeyChar();

        if (Character.isLetter(c)) {
            getToolkit().beep();

            evt.consume();

            mensaje.setText("error de ingreso, ingrese digitos");
        } else {
            mensaje.setText("");
        }
    }//GEN-LAST:event_txtRucKeyTyped

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed

        String insti = jLabel9.getText();
        String rucCi = txtRuc.getText();
        String primNombre = txtNombre.getText();
        String segNombre = txtNombre2.getText();
        String primApell = txtApellido.getText();
        String segApellidos = txtApellido2.getText();
        String apodo = txtApodo.getText();
        String direccion = txtDir.getText();
        String telefono = txtTelefono.getText();
        String celular = txtCelular.getText();
        String sector = comboSector.getSelectedItem().toString();
        String referencia = txtReferencia.getText();
        String observacion= txtObservar.getText();
        
        if (!txtNombre.getText().equals("") && (!txtApellido.getText().equals("") || !txtApellido2.getText().equals(""))) {
            if (!comboSector.getSelectedItem().toString().equals("Seleccione")) {
                //guardar nuevo
                if (id.getText().equals("")) {
                    int i = JOptionPane.showConfirmDialog(this, "¿Realmente desea Registrar?", "Confirmar", JOptionPane.YES_NO_OPTION);
                    if (i == 0) {

                        cu.guardarUsuarios(insti, rucCi, primNombre, segNombre, primApell, segApellidos, apodo, direccion, telefono, celular, sector, referencia, observacion);
                        cu.cargarTablaUsuario2(tablaUsuarios,"","","");
                        limpiar();
                        ocultar();
                        btnNuevo.setEnabled(true);
                    } else {
                    }
                    
                }
                //editar
                else if (!id.getText().equals("")) {
                    int i = JOptionPane.showConfirmDialog(this, "¿Realmente desea Modificar?", "Confirmar", JOptionPane.YES_NO_OPTION);
                    if (i == 0) {

                        cu.modificarUsuario(Integer.valueOf(id.getText()), rucCi, primNombre, segNombre, primApell, segApellidos, apodo, direccion, telefono, celular, sector, referencia,observacion);
                        cu.cargarTablaUsuario2(tablaUsuarios,"","","");
                        limpiar();
                        ocultar();
                        btnNuevo.setEnabled(true);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Debe seleccionar un sector",
                        "Error de Ingreso", JOptionPane.ERROR_MESSAGE);
            }

        } else {
            JOptionPane.showMessageDialog(null, "El campo nombre y apellido es obligatorio",
                    "Error de Ingreso", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_btnGuardarActionPerformed

    private void txtApellido2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtApellido2KeyReleased
        String texto = txtApellido2.getText().toUpperCase();

        Pattern patron = Pattern.compile("[^A-Za-z|Ñ|Á|É|Í|Ó|Ú]");
        Matcher encaja = patron.matcher(texto);
        if (!encaja.find()) {
            mensaje.setText("");
            txtApellido2.setText(texto);
        } else {
            evt.consume();
            mensaje.setText("error de ingreso, contiene caracter no valido!...");
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_txtApellido2KeyReleased

    private void txtNombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyReleased
        String texto = txtNombre.getText().toUpperCase();

        Pattern patron = Pattern.compile("[^A-Za-z|Ñ|Á|É|Í|Ó|Ú]");
        Matcher encaja = patron.matcher(texto);
        if (!encaja.find()) {
            mensaje.setText("");
            txtNombre.setText(texto);
        } else {
            getToolkit().beep();
            evt.consume();
            mensaje.setText("error de ingreso, contiene caracter no valido!...");
        }
    }//GEN-LAST:event_txtNombreKeyReleased

    private void txtNombre2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombre2KeyReleased
        String texto = txtNombre2.getText().toUpperCase();

        Pattern patron = Pattern.compile("[^A-Za-z|Ñ|Á|É|Í|Ó|Ú]");
        Matcher encaja = patron.matcher(texto);
        if (!encaja.find()) {
            mensaje.setText("");
            txtNombre2.setText(texto);
        } else {
            getToolkit().beep();
            evt.consume();
            mensaje.setText("error de ingreso, contiene caracter no valido!...");
        }
    }//GEN-LAST:event_txtNombre2KeyReleased

    private void txtApellidoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtApellidoKeyReleased
        String texto = txtApellido.getText().toUpperCase();

        Pattern patron = Pattern.compile("[^A-Za-z|Ñ|Á|É|Í|Ó|Ú]");
        Matcher encaja = patron.matcher(texto);
        if (!encaja.find()) {
            mensaje.setText("");
            txtApellido.setText(texto);
        } else {
            getToolkit().beep();
            evt.consume();
            mensaje.setText("error de ingreso, contiene caracter no valido!...");
        }
    }//GEN-LAST:event_txtApellidoKeyReleased

    private void txtTelefonoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefonoKeyReleased

        String texto = txtTelefono.getText();
        Pattern pat = Pattern.compile("[0-9]{7}");
        Matcher mat = pat.matcher(texto);
        if (mat.matches()) {
            mensaje.setText("");
            //System.out.println("SI");
        } else {
            //getToolkit().beep();
            evt.consume();
            mensaje.setText("error de ingreso, contiene caracter no valido!...");
        }
    }//GEN-LAST:event_txtTelefonoKeyReleased

    private void txtCelularKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCelularKeyReleased
        String texto = txtCelular.getText();
        Pattern pat = Pattern.compile("0[0-9]{9}");
        Matcher mat = pat.matcher(texto);
        if (mat.matches()) {
            mensaje.setText("");
            //System.out.println("SI");
        } else {
            //getToolkit().beep();
            evt.consume();
            mensaje.setText("error de ingreso, contiene caracter no valido!...");
        }
    }//GEN-LAST:event_txtCelularKeyReleased

    private void txtRucKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRucKeyReleased

        if (valCed.validadorDeCedula(txtRuc.getText()) == true) {
            mensaje.setForeground(Color.green);
            mensaje.setText("cedula valida");
        } else {
            mensaje.setForeground(new Color(204, 0, 0));
            mensaje.setText("cedula invalida, ingrese una cedula valida");
        }

    }//GEN-LAST:event_txtRucKeyReleased

    private void tablaUsuariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaUsuariosMouseClicked
        try {
            //EVENTOS DEL BOTON
            int column = tablaUsuarios.getColumnModel().getColumnIndexAtX(evt.getX());
            int row = evt.getY()/tablaUsuarios.getRowHeight();
            
            int n = tablaUsuarios.getSelectedRow();
            int idus = Integer.valueOf(tablaUsuarios.getValueAt(n, 0).toString());
            us=cu.buscarLoginId(idus);
            id.setText(us.getIdusuario().toString());
            
            //boton
            if(row < tablaUsuarios.getRowCount() && row >= 0 && column < tablaUsuarios.getColumnCount() && column >= 0){
                Object value = tablaUsuarios.getValueAt(row, column);
                if(value instanceof JButton){
                    ((JButton)value).doClick();
                    JButton boton = (JButton) value;
                    
                    if(boton.getName().equals("btnVer")){
                        System.out.println("Click en el boton ver");
                        //EVENTOS VER
                    }
                    if(boton.getName().equals("btnEditar")){
                        System.out.println("Click en el boton Editar");
                        ocultar();
                        mostrar();
                        btnNuevo.setEnabled(false);
                    }
                    if(boton.getName().equals("btnEliminar")){
                        int i = JOptionPane.showConfirmDialog(this, "¿Realmente desea Eliminar?", "Confirmar", JOptionPane.YES_NO_OPTION);
                        //EVENTOS ELIMINAR
                        try {
                            if (i == 0) {
                                cu.eliminarUsuario(Integer.valueOf(id.getText()));
                                cu.cargarTablaUsuario2(tablaUsuarios,"","","");
                            }

                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, "No se pudo Eliminar!.",
                                    "Error de Proceso",
                                    JOptionPane.ERROR_MESSAGE);

                        }
                    }
                }

            }
            
            txtRuc.setText(tablaUsuarios.getValueAt(n, 2).toString());
            txtNombre.setText(us.getPrimernombre());
            txtNombre2.setText(us.getSegundonombre());
            txtApellido.setText(us.getPrimerapellido());
            txtApellido2.setText(us.getSegundoapellido());
            txtApodo.setText(tablaUsuarios.getValueAt(n, 4).toString());
            txtDir.setText(us.getDireccion());
            txtTelefono.setText(us.getTelefono());
            txtCelular.setText(us.getCelular());
            comboSector.setSelectedItem(tablaUsuarios.getValueAt(n, 6).toString());
            txtReferencia.setText(us.getReferencia());
            txtObservar.setText(us.getObservacion());

        } catch (Exception e) {
        }
    }//GEN-LAST:event_tablaUsuariosMouseClicked

    private void tablaUsuariosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablaUsuariosKeyReleased
        try {
            int n = tablaUsuarios.getSelectedRow();
            int idus = Integer.valueOf(tablaUsuarios.getValueAt(n, 0).toString());
            
            us=cu.buscarLoginId(idus);
            
            id.setText(us.getIdusuario().toString());
            txtRuc.setText(tablaUsuarios.getValueAt(n, 2).toString());
            txtNombre.setText(us.getPrimernombre());
            txtNombre2.setText(us.getSegundonombre());
            txtApellido.setText(us.getPrimerapellido());
            txtApellido2.setText(us.getSegundoapellido());
            txtApodo.setText(tablaUsuarios.getValueAt(n, 4).toString());
            txtDir.setText(us.getDireccion());
            txtTelefono.setText(us.getTelefono());
            txtCelular.setText(us.getCelular());
            comboSector.setSelectedItem(tablaUsuarios.getValueAt(n, 6).toString());
            txtReferencia.setText(us.getReferencia());
            txtObservar.setText(us.getObservacion());

        } catch (Exception e) {
        }

    }//GEN-LAST:event_tablaUsuariosKeyReleased

    private void rbtCedulaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtCedulaActionPerformed
        txtBuscar.setText("");
        cu.cargarTablaUsuario2(tablaUsuarios,"","","");
    }//GEN-LAST:event_rbtCedulaActionPerformed

    private void rbtNombresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtNombresActionPerformed
        txtBuscar.setText("");
        cu.cargarTablaUsuario2(tablaUsuarios,"","","");
    }//GEN-LAST:event_rbtNombresActionPerformed

    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyReleased
        //cu.cargarTablaUsuario2(tablaUsuarios,txtBuscar.getText());
        //String cadena = (txtBuscar.getText()).toUpperCase();
        //txtBuscar.setText(cadena);
        try {
            if (rbtNombres.isSelected() == true) {
                //cu.cargarTablaApellidoNombre(txtBuscar.getText(), tablaUsuarios);
                cu.cargarTablaUsuario2(tablaUsuarios,"",txtBuscar.getText(),"");
            } else if (rbtCedula.isSelected() == true) {
                //cu.BuscarCed(txtBuscar.getText(), tablaUsuarios);
                cu.cargarTablaUsuario2(tablaUsuarios,txtBuscar.getText(),"","");
            }
            else if(rbtApodo.isSelected()==true){
                cu.cargarTablaUsuario2(tablaUsuarios,"","", txtBuscar.getText());
            }
            /*
            if (txtBuscar.getText().equals("")) {
                cu.cargarTablaUsuario2(tablaUsuarios,"","","");
            }
            */
        } catch (Exception e) {
            System.out.println("Error: "+e.getMessage());
        }
    }//GEN-LAST:event_txtBuscarKeyReleased

    private void btnNuevo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevo1ActionPerformed
        Object[] options = {"LISTA DE USUARIOS ACTIVOS","LISTA DE USUARIOS INACTIVOS"};
        Object n = JOptionPane.showInputDialog(null, "QUE REPORTES DESEA VER?:\n\n",
            "Seleccione una opcion", JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        b = new ReportesControlador();
        if(n.equals("LISTA DE USUARIOS ACTIVOS")){
            System.out.println("Ha seleccionado usuarios activos");
            b.reporte("usuariosConMedidorActivos.jasper", "UsuariosMedidorActivos");
        }else if(n.equals("LISTA DE USUARIOS INACTIVOS")){
            b.reporte("usuariosConMedidorNoActivos.jasper", "UsuariosMedidorNoActivos");
            System.out.println("Ha seleccionado usuarios inactivos");
        }
    }//GEN-LAST:event_btnNuevo1ActionPerformed

    private void rbtApodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtApodoActionPerformed
        txtBuscar.setText("");
        cu.cargarTablaUsuario2(tablaUsuarios,"","","");
    }//GEN-LAST:event_rbtApodoActionPerformed
    private void setDatosMover(Usuarios usuario) {
//        classMedidor cm = new classMedidor();
//        classusuario cu = new classusuario();
        try {
            id.setText(usuario.getIdusuario().toString());
            txtRuc.setText(usuario.getRucci());
            txtNombre.setText(usuario.getPrimernombre());
            txtNombre2.setText(usuario.getSegundonombre());
            txtApellido.setText(usuario.getPrimerapellido());
            txtApellido2.setText(usuario.getSegundoapellido());
            txtApodo.setText(usuario.getApadosn());
            txtDir.setText(usuario.getDireccion());
            txtTelefono.setText(usuario.getDireccion());
            txtCelular.setText(usuario.getCelular());
            comboSector.setSelectedItem(usuario.getSector().toString());
            txtReferencia.setText(usuario.getReferencia());
        } catch (Exception e) {
        }

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.edisoncor.gui.button.ButtonNice btnCancelar;
    private org.edisoncor.gui.button.ButtonNice btnGuardar;
    private org.edisoncor.gui.button.ButtonNice btnNuevo;
    private org.edisoncor.gui.button.ButtonNice btnNuevo1;
    private javax.swing.ButtonGroup busquedaGrupo;
    private org.edisoncor.gui.comboBox.ComboBoxRect comboSector;
    private javax.swing.JLabel id;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    public javax.swing.JLabel label;
    private javax.swing.JLabel mensaje;
    private javax.swing.JRadioButton rbtApodo;
    private javax.swing.JRadioButton rbtCedula;
    private javax.swing.JRadioButton rbtNombres;
    public static javax.swing.JTable tablaUsuarios;
    private javax.swing.JTextField txtApellido;
    private javax.swing.JTextField txtApellido2;
    private javax.swing.JTextField txtApodo;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtCelular;
    private javax.swing.JTextField txtDir;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtNombre2;
    private javax.swing.JTextArea txtObservar;
    private javax.swing.JTextField txtReferencia;
    public static javax.swing.JTextField txtRuc;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}
