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

    public FrmUsuario() {
        initComponents();
        inicio();
    }
    classMoverRegistros moverRegistros = new classMoverRegistros(cu.getUsuarios());
    ControlFormularios cf;

    JFileChooser seleccionado = new JFileChooser();
    File archivo = null;
    byte[] bytesImg = null;
    String ruta, nombre;
    BufferedImage img;

    public void inicio() {
        Dimension desktopSize = FrmPrincipal.jDesktopPane1.getSize();
        Dimension jInternalFrameSize = this.getSize();
        this.setLocation((desktopSize.width - jInternalFrameSize.width) / 2, 4);
        ci = new classInstitucion();
        cu = new classusuario();
        jLabel9.setText(ci.buscarIdInstitucion(1).getNombreinst().toString());

        jLabel1.setVisible(false);
        id.setVisible(false);
        cu.cargarTablaUsuario2(tablaUsuarios);
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
        btnEditar.setEnabled(false);
        btnEliminar.setEnabled(false);
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
        btnEditar.setEnabled(true);
        btnEliminar.setEnabled(true);
        btnGuardar.setEnabled(false);
        btnCancelar.setEnabled(false);
    }

    public void limpiar() {
        lblfoto.setIcon(null);
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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lblfoto = new javax.swing.JLabel();
        id = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtRuc = new javax.swing.JTextField();
        txtDir = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        txtNombre2 = new javax.swing.JTextField();
        txtApellido = new javax.swing.JTextField();
        txtApellido2 = new javax.swing.JTextField();
        txtApodo = new javax.swing.JTextField();
        txtTelefono = new javax.swing.JTextField();
        txtCelular = new javax.swing.JTextField();
        txtReferencia = new javax.swing.JTextField();
        txtObservar = new javax.swing.JTextField();
        buttonNice6 = new org.edisoncor.gui.button.ButtonNice();
        comboSector = new org.edisoncor.gui.comboBox.ComboBoxRect();
        label = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaUsuarios = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false; //Disallow the editing of any cell
            }
        };
        rbtTodos = new javax.swing.JRadioButton();
        jLabel10 = new javax.swing.JLabel();
        rbtNombres = new javax.swing.JRadioButton();
        rbtCedula = new javax.swing.JRadioButton();
        txtBuscar = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        btnNuevo = new org.edisoncor.gui.button.ButtonNice();
        btnGuardar = new org.edisoncor.gui.button.ButtonNice();
        btnEditar = new org.edisoncor.gui.button.ButtonNice();
        btnEliminar = new org.edisoncor.gui.button.ButtonNice();
        btnCancelar = new org.edisoncor.gui.button.ButtonNice();
        mensaje = new javax.swing.JLabel();
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

        jLabel1.setText("id");

        jLabel2.setText("RUC / ci:");

        jLabel3.setText("Primer Nombre:");

        jLabel4.setText("Segundo Nombre:");

        jLabel5.setText("Primer Apellido:");

        jLabel6.setText("Segundo Apellido:");

        jLabel7.setText("Apodo:");

        jLabel8.setText("telefono:");

        lblfoto.setBackground(new java.awt.Color(153, 204, 255));
        lblfoto.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel11.setText("Direccion");

        jLabel12.setText("Celular:");

        jLabel13.setText("Sector:");

        jLabel14.setText("Referencia:");

        jLabel15.setText("Observacion:");

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

        txtTelefono.setEditable(false);
        txtTelefono.setText("2 918 000");
        txtTelefono.setToolTipText("Ingrese 7 digitos");
        txtTelefono.setAutoscrolls(false);
        txtTelefono.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtTelefonoMouseClicked(evt);
            }
        });
        txtTelefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTelefonoKeyReleased(evt);
            }
        });

        txtCelular.setEditable(false);
        txtCelular.setText("095 9090 000");
        txtCelular.setToolTipText("Ingrese 10 digitos");
        txtCelular.setAutoscrolls(false);
        txtCelular.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtCelularMouseClicked(evt);
            }
        });
        txtCelular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCelularActionPerformed(evt);
            }
        });
        txtCelular.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCelularKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCelularKeyTyped(evt);
            }
        });

        txtReferencia.setEditable(false);
        txtReferencia.setToolTipText("Ingrese Lugar de Referemcia: ej. Cerca de la Escuela");

        txtObservar.setEditable(false);
        txtObservar.setToolTipText("TERCERA EDAD, INSTITUCION, etc.");
        txtObservar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtObservarKeyTyped(evt);
            }
        });

        buttonNice6.setBackground(new java.awt.Color(0, 102, 255));
        buttonNice6.setForeground(new java.awt.Color(255, 255, 255));
        buttonNice6.setText("CARGAR");
        buttonNice6.setSegundoColor(new java.awt.Color(0, 51, 255));
        buttonNice6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonNice6ActionPerformed(evt);
            }
        });

        comboSector.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione", "Capillapamba", "Coragaloma", "Tocagon Alto", "Pilchibuela" }));
        comboSector.setEnabled(false);
        comboSector.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N

        label.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jButton1.setText("Adelante");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Atras");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(id, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addComponent(lblfoto, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addComponent(jLabel12)
                            .addComponent(jLabel13)
                            .addComponent(jLabel15)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTelefono)
                            .addComponent(txtCelular, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtObservar, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtReferencia)
                            .addComponent(comboSector, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(125, 125, 125))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(86, 86, 86)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(label, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtApodo, javax.swing.GroupLayout.PREFERRED_SIZE, 357, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtApellido2, javax.swing.GroupLayout.PREFERRED_SIZE, 357, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 357, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtNombre2, javax.swing.GroupLayout.PREFERRED_SIZE, 357, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 357, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtDir, javax.swing.GroupLayout.DEFAULT_SIZE, 357, Short.MAX_VALUE)
                                    .addComponent(txtRuc))))
                        .addGap(116, 116, 116))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(buttonNice6, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel15))
                            .addComponent(lblfoto, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(23, 23, 23)
                        .addComponent(buttonNice6, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel8)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtCelular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel12))
                            .addComponent(id, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(comboSector, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtReferencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtObservar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtRuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(txtDir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(14, 14, 14)
                        .addComponent(jLabel3))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtNombre2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(txtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtApellido2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtApodo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1)
                            .addComponent(jButton2))
                        .addGap(0, 4, Short.MAX_VALUE)))
                .addContainerGap())
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

        busquedaGrupo.add(rbtTodos);
        rbtTodos.setSelected(true);
        rbtTodos.setText("Todos");
        rbtTodos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtTodosActionPerformed(evt);
            }
        });

        jLabel10.setText("Búsqueda:");

        busquedaGrupo.add(rbtNombres);
        rbtNombres.setText("Apellidos - Nombres");
        rbtNombres.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtNombresActionPerformed(evt);
            }
        });

        busquedaGrupo.add(rbtCedula);
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

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 662, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(77, 77, 77)
                                .addComponent(rbtTodos)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(rbtCedula)
                                .addGap(18, 18, 18)
                                .addComponent(rbtNombres))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 431, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbtTodos)
                    .addComponent(rbtNombres)
                    .addComponent(rbtCedula))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

        btnEditar.setBackground(new java.awt.Color(0, 102, 255));
        btnEditar.setForeground(new java.awt.Color(255, 255, 255));
        btnEditar.setText("EDITAR");
        btnEditar.setModelo(org.edisoncor.gui.button.ButtonNice.Modelo.RECT);
        btnEditar.setSegundoColor(new java.awt.Color(0, 51, 255));
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        btnEliminar.setBackground(new java.awt.Color(0, 102, 255));
        btnEliminar.setForeground(new java.awt.Color(255, 255, 255));
        btnEliminar.setText("ELIMINAR");
        btnEliminar.setModelo(org.edisoncor.gui.button.ButtonNice.Modelo.RECT);
        btnEliminar.setSegundoColor(new java.awt.Color(0, 51, 255));
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
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
                .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(mensaje, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                            .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 478, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(304, 304, 304)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 376, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(518, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addGap(4, 4, 4)
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
        bytesImg = null;
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed

        ocultar();
        limpiar();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        ocultar();
        mostrar();
    }//GEN-LAST:event_btnEditarActionPerformed

    private void txtCelularKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCelularKeyTyped
//        char c = evt.getKeyChar();
//
//        if (Character.isLetter(c)) {
//            getToolkit().beep();
//
//            evt.consume();
//
//            mensaje.setText("error de ingreso, ingrese digitos");
//        } else {
//            mensaje.setText("");
//        }
    }//GEN-LAST:event_txtCelularKeyTyped

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

    private void txtObservarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtObservarKeyTyped

        char c = evt.getKeyChar();

        if (Character.isDigit(c)) {
            getToolkit().beep();

            evt.consume();

            mensaje.setText("error de ingreso, ingrese letras!...");
        } else {
            mensaje.setText("");
        }
    }//GEN-LAST:event_txtObservarKeyTyped

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
        if (!txtNombre.getText().equals("") && (!txtApellido.getText().equals("") || !txtApellido2.getText().equals(""))) {
            if (!comboSector.getSelectedItem().toString().equals("Seleccione")) {
                if (id.getText().equals("")) {
                    int i = JOptionPane.showConfirmDialog(this, "¿Realmente desea Registrar?", "Confirmar", JOptionPane.YES_NO_OPTION);
                    if (i == 0) {

                        cu.guardarUsuarios(insti, rucCi, primNombre, segNombre, primApell, segApellidos, apodo, direccion, telefono, celular, sector, referencia, bytesImg, direccion);
                        cu.cargarTablaUsuario2(tablaUsuarios);
//                int j = JOptionPane.showConfirmDialog(this, "¿Asignar Medidor ?", "Confirmar", JOptionPane.YES_NO_OPTION);
//                if (j == 0) {
//                    FrmMedidor me = new FrmMedidor();
//                    cf = new ControlFormularios();
//                    cf.ControlaInstancia(me);
////                    FrmMedidor.
//                    this.dispose();
//                }
                        limpiar();
                        ocultar();

                    } else {
                    }
                } else if (!id.getText().equals("")) {
                    int i = JOptionPane.showConfirmDialog(this, "¿Realmente desea Modificar?", "Confirmar", JOptionPane.YES_NO_OPTION);
                    if (i == 0) {

//                bytesImg = cu.AbrirAImagen(archivo);
                        cu.modificarUsuario(Integer.valueOf(id.getText()), rucCi, primNombre, segNombre, primApell, segApellidos, apodo, direccion, telefono, celular, sector, referencia, bytesImg, direccion);
                        cu.cargarTablaUsuario2(tablaUsuarios);
                        limpiar();
                        ocultar();

                    } else {
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null,"Debe seleccionar un sector",
                    "Error de Ingreso", JOptionPane.ERROR_MESSAGE);
            }

        } else {
            JOptionPane.showMessageDialog(null,"El campo nombre y apellido es obligatorio",
                    "Error de Ingreso", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_btnGuardarActionPerformed

    private void buttonNice6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonNice6ActionPerformed
        // TODO add your handling code here:
        if (seleccionado.showDialog(null, "ABRIR ARCHIVO") == JFileChooser.APPROVE_OPTION) {
            archivo = seleccionado.getSelectedFile();
            if (archivo.canRead()) {
                if (archivo.getName().endsWith("jpg") || archivo.getName().endsWith("png") || archivo.getName().endsWith("gif")) {
                    ruta = seleccionado.getSelectedFile().getAbsolutePath();
                    bytesImg = cu.AbrirAImagen(archivo);
                    Image preview = Toolkit.getDefaultToolkit().getImage(ruta);
                    lblfoto.setIcon(new ImageIcon(bytesImg));
                    ImageIcon icon = new ImageIcon(preview.getScaledInstance(lblfoto.getWidth(), lblfoto.getHeight(), Image.SCALE_DEFAULT));
                    lblfoto.setIcon(icon);
                } else {
                    JOptionPane.showMessageDialog(null, "Por favor seleccione un archivo de imagen.");
                }
            }

        }
    }//GEN-LAST:event_buttonNice6ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        try {
            Usuarios usu = (Usuarios) moverRegistros.getNext();
            setDatosMover(usu);

        } catch (Exception e) {
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        try {
            Usuarios usu = (Usuarios) moverRegistros.getBack();
            setDatosMover(usu);

        } catch (Exception e) {
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        try {
            int i = JOptionPane.showConfirmDialog(this, "¿Realmente desea Eliminar?", "Confirmar", JOptionPane.YES_NO_OPTION);
            if (i == 0) {
                cu.eliminarUsuario(Integer.valueOf(id.getText()));
                cu.cargarTablaUsuario2(tablaUsuarios);
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "No se pudo Eliminar!.",
                "Error de Proceso",
                JOptionPane.ERROR_MESSAGE);

        }
    }//GEN-LAST:event_btnEliminarActionPerformed

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

    private void txtTelefonoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTelefonoMouseClicked
        txtTelefono.setText("");

    }//GEN-LAST:event_txtTelefonoMouseClicked

    private void txtCelularMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtCelularMouseClicked

        txtCelular.setText("");
    }//GEN-LAST:event_txtCelularMouseClicked

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
            lblfoto.setIcon(null);
            int n = tablaUsuarios.getSelectedRow();
            int idus=Integer.valueOf(tablaUsuarios.getValueAt(n, 0).toString());
            id.setText(cu.buscarLoginId(idus).getIdusuario().toString());
            txtRuc.setText(tablaUsuarios.getValueAt(n, 1).toString());
            txtNombre.setText(cu.buscarLoginId(idus).getPrimernombre());
            txtNombre2.setText(cu.buscarLoginId(idus).getSegundonombre());
            txtApellido.setText(cu.buscarLoginId(idus).getPrimerapellido());
            txtApellido2.setText(cu.buscarLoginId(idus).getSegundoapellido());
            txtApodo.setText(tablaUsuarios.getValueAt(n, 3).toString());
            txtDir.setText(cu.buscarLoginId(idus).getDireccion());
            txtTelefono.setText(tablaUsuarios.getValueAt(n, 4).toString());
            txtCelular.setText(tablaUsuarios.getValueAt(n, 5).toString());
            comboSector.setSelectedItem(tablaUsuarios.getValueAt(n, 6).toString());
            txtReferencia.setText(cu.buscarLoginId(idus).getReferencia());
            txtObservar.setText(cu.buscarLoginId(idus).getObservacion());

            // System.out.println(cu.buscarUsuarioRucCi(tablaUsuarios.getValueAt(n, 1).toString()).getFoto());
            byte[] data = cu.buscarLoginId(idus).getFoto();
            img = ImageIO.read(new ByteArrayInputStream(data));
            lblfoto.setIcon(new ImageIcon(img));
            lblfoto.setIcon(ajustarImagen(img));
            lblfoto.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

//            comboTipo.setSelectedItem(tablaUsuarios.getValueAt(n, 6).toString());
//            comboEstado.setSelectedItem(tablaUsuarios.getValueAt(n, 7).toString());
        } catch (Exception e) {
        }
    }//GEN-LAST:event_tablaUsuariosMouseClicked

    private void tablaUsuariosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablaUsuariosKeyReleased
        try {
            lblfoto.setIcon(null);
            int n = tablaUsuarios.getSelectedRow();
            int idb = Integer.valueOf(tablaUsuarios.getValueAt(n, 0).toString());
            id.setText(cu.buscarLoginId(idb).getIdusuario().toString());
            txtRuc.setText(cu.buscarLoginId(idb).getRucci().toString());

            txtNombre.setText(cu.buscarLoginId(idb).getPrimernombre());
            txtNombre2.setText(cu.buscarLoginId(idb).getSegundonombre());
            txtApellido.setText(cu.buscarLoginId(idb).getPrimerapellido());
            txtApellido2.setText(cu.buscarLoginId(idb).getSegundoapellido());
            txtApodo.setText(cu.buscarLoginId(idb).getApadosn());
            txtDir.setText(cu.buscarLoginId(idb).getDireccion());
            txtTelefono.setText(cu.buscarLoginId(idb).getTelefono());
            txtCelular.setText(cu.buscarLoginId(idb).getCelular());
            comboSector.setSelectedItem(cu.buscarLoginId(idb).getSector().toString());
            txtReferencia.setText(cu.buscarLoginId(idb).getReferencia());
            txtObservar.setText(cu.buscarLoginId(idb).getObservacion());

            byte[] data = cu.buscarLoginId(idb).getFoto();
            img = ImageIO.read(new ByteArrayInputStream(data));
            lblfoto.setIcon(new ImageIcon(img));
            lblfoto.setIcon(ajustarImagen(img));
            lblfoto.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

//            comboTipo.setSelectedItem(tablaUsuarios.getValueAt(n, 6).toString());
//            comboEstado.setSelectedItem(tablaUsuarios.getValueAt(n, 7).toString());
        } catch (Exception e) {
        }

    }//GEN-LAST:event_tablaUsuariosKeyReleased

    private void rbtTodosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtTodosActionPerformed
        cu.cargarTablaUsuario2(tablaUsuarios);
        txtBuscar.setText("");

    }//GEN-LAST:event_rbtTodosActionPerformed

    private void rbtCedulaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtCedulaActionPerformed
        txtBuscar.setText("");
    }//GEN-LAST:event_rbtCedulaActionPerformed

    private void rbtNombresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtNombresActionPerformed
        txtBuscar.setText("");
    }//GEN-LAST:event_rbtNombresActionPerformed

    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyReleased
        String cadena = (txtBuscar.getText()).toUpperCase();
        txtBuscar.setText(cadena);
        try {
            if (rbtNombres.isSelected() == true) {
                cu.cargarTablaApellidoNombre(txtBuscar.getText(), tablaUsuarios);
            } else if (rbtCedula.isSelected() == true) {
                cu.BuscarCed(txtBuscar.getText(), tablaUsuarios);
            }
            if (txtBuscar.getText().equals("")) {
                cu.cargarTablaUsuario2(tablaUsuarios);
            }
        } catch (Exception e) {

        }

    }//GEN-LAST:event_txtBuscarKeyReleased
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
            byte[] data = usuario.getFoto();
            if (data != null) {
                img = ImageIO.read(new ByteArrayInputStream(data));
                lblfoto.setIcon(ajustarImagen(img));
                lblfoto.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
            } else {
                lblfoto.setIcon(null);
            }

        } catch (Exception e) {
        }

    }

    private ImageIcon ajustarImagen(BufferedImage ico) {
        ImageIcon tmpIconAux = new ImageIcon(ico);
        ImageIcon tmpIcon = new ImageIcon(tmpIconAux.getImage().getScaledInstance(143, 175, Image.SCALE_DEFAULT));
        return tmpIcon;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.edisoncor.gui.button.ButtonNice btnCancelar;
    private org.edisoncor.gui.button.ButtonNice btnEditar;
    private org.edisoncor.gui.button.ButtonNice btnEliminar;
    private org.edisoncor.gui.button.ButtonNice btnGuardar;
    private org.edisoncor.gui.button.ButtonNice btnNuevo;
    private javax.swing.ButtonGroup busquedaGrupo;
    private org.edisoncor.gui.button.ButtonNice buttonNice6;
    private org.edisoncor.gui.comboBox.ComboBoxRect comboSector;
    private javax.swing.JLabel id;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
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
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JLabel label;
    private javax.swing.JLabel lblfoto;
    private javax.swing.JLabel mensaje;
    private javax.swing.JRadioButton rbtCedula;
    private javax.swing.JRadioButton rbtNombres;
    private javax.swing.JRadioButton rbtTodos;
    public static javax.swing.JTable tablaUsuarios;
    private javax.swing.JTextField txtApellido;
    private javax.swing.JTextField txtApellido2;
    private javax.swing.JTextField txtApodo;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtCelular;
    private javax.swing.JTextField txtDir;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtNombre2;
    private javax.swing.JTextField txtObservar;
    private javax.swing.JTextField txtReferencia;
    public static javax.swing.JTextField txtRuc;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}
