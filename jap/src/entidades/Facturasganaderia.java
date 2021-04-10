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
@Table(name = "facturasganaderia")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Facturasganaderia.findAll", query = "SELECT f FROM Facturasganaderia f"),
    @NamedQuery(name = "Facturasganaderia.findByIdfacturasganaderia", query = "SELECT f FROM Facturasganaderia f WHERE f.idfacturasganaderia = :idfacturasganaderia"),
    @NamedQuery(name = "Facturasganaderia.findByNumfactura", query = "SELECT f FROM Facturasganaderia f WHERE f.numfactura = :numfactura"),
    @NamedQuery(name = "Facturasganaderia.findByFechaemision", query = "SELECT f FROM Facturasganaderia f WHERE f.fechaemision = :fechaemision"),
    @NamedQuery(name = "Facturasganaderia.findBySubtotal", query = "SELECT f FROM Facturasganaderia f WHERE f.subtotal = :subtotal"),
    @NamedQuery(name = "Facturasganaderia.findByIva", query = "SELECT f FROM Facturasganaderia f WHERE f.iva = :iva"),
    @NamedQuery(name = "Facturasganaderia.findByTotal", query = "SELECT f FROM Facturasganaderia f WHERE f.total = :total")})
public class Facturasganaderia implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDFACTURASGANADERIA")
    private Integer idfacturasganaderia;
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
    @JoinColumn(name = "IDDETALLEFACGANADERIA", referencedColumnName = "IDDETALLEFACGANADERIA")
    @ManyToOne(optional = false)
    private Detallefacturaganaderia iddetallefacganaderia;

    public Facturasganaderia() {
    }

    public Facturasganaderia(Integer idfacturasganaderia) {
        this.idfacturasganaderia = idfacturasganaderia;
    }

    public Integer getIdfacturasganaderia() {
        return idfacturasganaderia;
    }

    public void setIdfacturasganaderia(Integer idfacturasganaderia) {
        this.idfacturasganaderia = idfacturasganaderia;
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

    public Detallefacturaganaderia getIddetallefacganaderia() {
        return iddetallefacganaderia;
    }

    public void setIddetallefacganaderia(Detallefacturaganaderia iddetallefacganaderia) {
        this.iddetallefacganaderia = iddetallefacganaderia;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idfacturasganaderia != null ? idfacturasganaderia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Facturasganaderia)) {
            return false;
        }
        Facturasganaderia other = (Facturasganaderia) object;
        if ((this.idfacturasganaderia == null && other.idfacturasganaderia != null) || (this.idfacturasganaderia != null && !this.idfacturasganaderia.equals(other.idfacturasganaderia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Facturasganaderia[ idfacturasganaderia=" + idfacturasganaderia + " ]";
    }
    
}
