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
 * @author JC-PC
 */
@Entity
@Table(name = "facturas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Facturas.findAll", query = "SELECT f FROM Facturas f"),
    @NamedQuery(name = "Facturas.findByIdfactura", query = "SELECT f FROM Facturas f WHERE f.idfactura = :idfactura"),
    @NamedQuery(name = "Facturas.findByNumfactura", query = "SELECT f FROM Facturas f WHERE f.numfactura = :numfactura"),
    @NamedQuery(name = "Facturas.findByFechaemision", query = "SELECT f FROM Facturas f WHERE f.fechaemision = :fechaemision"),
    @NamedQuery(name = "Facturas.findBySubtotal", query = "SELECT f FROM Facturas f WHERE f.subtotal = :subtotal"),
    @NamedQuery(name = "Facturas.findByIva", query = "SELECT f FROM Facturas f WHERE f.iva = :iva"),
    @NamedQuery(name = "Facturas.findByTotal", query = "SELECT f FROM Facturas f WHERE f.total = :total")})
public class Facturas implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDFACTURA")
    private Integer idfactura;
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
    @JoinColumn(name = "IDDETALLEFAC", referencedColumnName = "IDDETALLEFAC")
    @ManyToOne
    private Detallefactura iddetallefac;

    public Facturas() {
    }

    public Facturas(Integer idfactura) {
        this.idfactura = idfactura;
    }

    public Integer getIdfactura() {
        return idfactura;
    }

    public void setIdfactura(Integer idfactura) {
        this.idfactura = idfactura;
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

    public Detallefactura getIddetallefac() {
        return iddetallefac;
    }

    public void setIddetallefac(Detallefactura iddetallefac) {
        this.iddetallefac = iddetallefac;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idfactura != null ? idfactura.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Facturas)) {
            return false;
        }
        Facturas other = (Facturas) object;
        if ((this.idfactura == null && other.idfactura != null) || (this.idfactura != null && !this.idfactura.equals(other.idfactura))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Facturas[ idfactura=" + idfactura + " ]";
    }
    
}
