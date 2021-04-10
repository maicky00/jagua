/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Tech-Usuario
 */
@Entity
@Table(name = "facturassobrante")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Facturassobrante.findAll", query = "SELECT f FROM Facturassobrante f"),
    @NamedQuery(name = "Facturassobrante.findByIdfacturasobrante", query = "SELECT f FROM Facturassobrante f WHERE f.idfacturasobrante = :idfacturasobrante"),
    @NamedQuery(name = "Facturassobrante.findByNumfactura", query = "SELECT f FROM Facturassobrante f WHERE f.numfactura = :numfactura"),
    @NamedQuery(name = "Facturassobrante.findByFechaemision", query = "SELECT f FROM Facturassobrante f WHERE f.fechaemision = :fechaemision"),
    @NamedQuery(name = "Facturassobrante.findBySubtotal", query = "SELECT f FROM Facturassobrante f WHERE f.subtotal = :subtotal"),
    @NamedQuery(name = "Facturassobrante.findByIva", query = "SELECT f FROM Facturassobrante f WHERE f.iva = :iva"),
    @NamedQuery(name = "Facturassobrante.findByTotal", query = "SELECT f FROM Facturassobrante f WHERE f.total = :total")})
public class Facturassobrante implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDFACTURASOBRANTE")
    private Integer idfacturasobrante;
    @Column(name = "NUMFACTURA")
    private Integer numfactura;
    @Column(name = "FECHAEMISION")
    @Temporal(TemporalType.DATE)
    private Date fechaemision;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "SUBTOTAL")
    private Float subtotal;
    @Column(name = "IVA")
    private Float iva;
    @Column(name = "TOTAL")
    private Float total;
    @Lob
    @Column(name = "USUARIOACTUAL")
    private String usuarioactual;
    @JoinColumn(name = "IDDETALLEFACSOBRANTE", referencedColumnName = "IDDETALLEFACSOBRANTE")
    @ManyToOne(optional = false)
    private Detallefacturasobrante iddetallefacsobrante;

    public Facturassobrante() {
    }

    public Facturassobrante(Integer idfacturasobrante) {
        this.idfacturasobrante = idfacturasobrante;
    }

    public Integer getIdfacturasobrante() {
        return idfacturasobrante;
    }

    public void setIdfacturasobrante(Integer idfacturasobrante) {
        this.idfacturasobrante = idfacturasobrante;
    }

    public Integer getNumfactura() {
        return numfactura;
    }

    public void setNumfactura(Integer numfactura) {
        this.numfactura = numfactura;
    }

    public Date getFechaemision() {
        return fechaemision;
    }

    public void setFechaemision(Date fechaemision) {
        this.fechaemision = fechaemision;
    }

    public Float getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Float subtotal) {
        this.subtotal = subtotal;
    }

    public Float getIva() {
        return iva;
    }

    public void setIva(Float iva) {
        this.iva = iva;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    public String getUsuarioactual() {
        return usuarioactual;
    }

    public void setUsuarioactual(String usuarioactual) {
        this.usuarioactual = usuarioactual;
    }

    public Detallefacturasobrante getIddetallefacsobrante() {
        return iddetallefacsobrante;
    }

    public void setIddetallefacsobrante(Detallefacturasobrante iddetallefacsobrante) {
        this.iddetallefacsobrante = iddetallefacsobrante;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idfacturasobrante != null ? idfacturasobrante.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Facturassobrante)) {
            return false;
        }
        Facturassobrante other = (Facturassobrante) object;
        if ((this.idfacturasobrante == null && other.idfacturasobrante != null) || (this.idfacturasobrante != null && !this.idfacturasobrante.equals(other.idfacturasobrante))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Facturassobrante[ idfacturasobrante=" + idfacturasobrante + " ]";
    }
    
}
