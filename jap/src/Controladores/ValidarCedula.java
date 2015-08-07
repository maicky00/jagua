package Controladores;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.Scanner;

/**
 *
 * @author Admin
 */
public class ValidarCedula{

    public boolean validadorDeCedula(String ncedula) {

        int cedula[] = new int[ncedula.length()];

        int res = 0;

        if (cedula.length == 10) {
            for (int i = 0; i < cedula.length; i++) {
                cedula[i] = Integer.parseInt(String.valueOf(ncedula.charAt(i)));
                int r = i % 2;

                if (r == 0) {
                    cedula[i] = cedula[i] * 2;
                    if (cedula[i] > 9) {
                        cedula[i] = cedula[i] - 9;
                    }
                }

            }

            for (int i = 0; i < cedula.length - 1; i++) {
                res = res + cedula[i];
            }

            res = res % 10;

            if (res != 0) {
                res = 10 - res;
            }

            if (res == cedula[9]) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }

    }
}
