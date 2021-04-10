/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jap;

import Controladores.classusuario;
import Formularios.FrmLogin;
import Formularios.Login;
import Formularios.FrmPrincipal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Marco
 */
public class Jap {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Login fl = new Login();
        FrmPrincipal principal=new FrmPrincipal();
        principal.show();
        
        classusuario cu=new classusuario();
        //cu.actualizarUsuarioEstadomedidor();
    }

}
