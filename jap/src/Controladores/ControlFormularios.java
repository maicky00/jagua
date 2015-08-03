/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Formularios.FrmPrincipal;
import java.beans.PropertyVetoException;
import javax.swing.JInternalFrame;
import javax.swing.SwingUtilities;

/**
 *
 * @author JC-PC
 */
public class ControlFormularios {

    public void ControlaInstancia(JInternalFrame inter) {

        boolean mostrar = true;
        String Nombre = inter.getTitle();
        for (int a = 0; a < FrmPrincipal.jDesktopPane1.getComponentCount(); a++) {
            if (inter.getClass().isInstance(FrmPrincipal.jDesktopPane1.getComponent(a))) {
                FrmPrincipal.jDesktopPane1.moveToFront(inter);
                inter.toFront();
                mostrar = false;

            } 
        }
        if (mostrar) {
            FrmPrincipal.jDesktopPane1.add(inter);

        }
        inter.show();
        
    }

    
}
