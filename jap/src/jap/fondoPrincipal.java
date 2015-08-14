package jap;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;

public class fondoPrincipal extends JDesktopPane {

    private Image imagen;

    public fondoPrincipal() {
    }

    public fondoPrincipal(String nombreImagen) {
        if (nombreImagen != null) {
            imagen = new ImageIcon(this.getClass().getResource(nombreImagen)).getImage();
        }
    }

    public fondoPrincipal(Image imagenInicial) {
        if (imagenInicial != null) {
            imagen = imagenInicial;
        }
    }

    public void setImagen(String nombreImagen) {
        if (nombreImagen != null) {
            imagen = new ImageIcon(this.getClass().getResource(nombreImagen)).getImage();
        } else {
            imagen = null;
        }

        repaint();
    }

    public void setImagen(Image nuevaImagen) {
        imagen = nuevaImagen;

        repaint();
    }

    @Override
    public void paint(Graphics g) {
        if (imagen != null) {
            g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);
            setOpaque(false);
        } else{
            setOpaque(true);
        }

        super.paint(g);
    }
}
