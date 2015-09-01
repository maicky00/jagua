/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

/**
 *
 * @author Marco
 */
public class classDisenio {

    public static String txthtml(String mensaje) {
        String dato;
        dato = "<html>"
                + "<head>"
                + "<style>"
                + "#contenido{"
                + "background:#D8D8D8"
                + "color:white;"
                + "</style>"
                + "</head>"
                + "<body>"
                + "<div id='contenido'>"
                + "<p>"
                + mensaje
                + "</p>"
                + "</div>"
                + "</body>"
                + "</html>"
                + "";
        return dato;
    }

    public static String tablahtml(String mensaje) {
        String dato;
        dato = "<html>"
                + "<head>"
                + "<style>"
                + "#contenido{"
                + "background:#2E2E2E;"
                + "color:#FFFFFF;"
                + "</style>"
                + "</head>"
                + "<body>"
                + "<div id='contenido'>"
                + "<p>"
                + mensaje
                + "</p>"
                + "</div>"
                + "</body>"
                + "</html>"
                + "";
        return dato;
    }

    public static String texto(String mensaje) {
        String dato;
        dato = "<html>"
                + "<head>"
                + "<style>"
                + "#contenido{"
                + "background:#424242;"
                + "color:#FFFFFF;"
                + "}"
                + "</style>"
                + "</head>"
                + "<body>"
                + "<div id='contenido'>"
                + "<p>"
                + mensaje
                + "</p>"
                + "</div>"
                + "</body>"
                + "</html>"
                + "";
        return dato;
    }
    public static String textoNota(String mensaje,String nota) {
        String dato;
        dato = "<html><head>"
                + "<style>"
                + "#contenido{"
                + "background:#2E2E2E;"
                + "color:#FFFFFF;"
                + "}"
                + "#nota{"
                + "background:#848484;"
                + "color:#F5A9A9;"
                + "font-size:12;"
                + "font-family: \"Times New Roman\", Georgia, Serif;"
                + "}"
                + "</style>"
                + "</head><body>"
                + "<p id='contenido'>"
                + mensaje
                + "</p>"
                + "<p id='nota'>"
                + nota
                + "</p>"
                + "</body></html>"
                + "";
        return dato;
    }
}
