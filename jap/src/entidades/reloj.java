/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.util.Calendar;
import Formularios.FrmPrincipal;

/**
 *
 * @author JC-PC
 */
public class reloj {

    java.util.Calendar calendario;
    int dia, mes, año, hora, minutos, segundos;
    private javax.swing.JLabel label;

    public reloj() {
        reloj();
    }

    private void reloj() {
        calendario = new java.util.GregorianCalendar();
        segundos = calendario.get(Calendar.SECOND);
        javax.swing.Timer timer = new javax.swing.Timer(1000, new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent ae) {
                java.util.Date actual = new java.util.Date();
                calendario.setTime(actual);
                dia = calendario.get(Calendar.DAY_OF_MONTH);
                mes = (calendario.get(Calendar.MONTH) + 1);
                año = calendario.get(Calendar.YEAR);
                hora = calendario.get(Calendar.HOUR_OF_DAY);
                minutos = calendario.get(Calendar.MINUTE);
                segundos = calendario.get(Calendar.SECOND);
                String hour = String.format("%02d : %02d : %02d", hora, minutos, segundos);
                String date = String.format("%02d / %02d / %02d", dia, mes, año);
                FrmPrincipal.reloj.setText("fecha: "+date+"   "+hour);
            }
        });
        timer.start();
    }
}
