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
@Table(name = "otrospagos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Otrospagos.findAll", query = "SELECT o FROM Otrospagos o"),
    @NamedQuery(name = "Otrospagos.findByIdotpagos", query = "SELECT o FROM Otrospagos o WHERE o.idotpagos = :idotpagos"),
    @NamedQuery(name = "Otrospagos.findByDerconx", query = "SELECT o FROM Otrospagos o WHERE o.derconx = :derconx"),
    @NamedQuery(name = "Otrospagos.findByMulrecx", query = "SELECT o FROM Otrospagos o WHERE o.mulrecx = :mulrecx"),
    @NamedQuery(name = "Otrospagos.findByInteres", query = "SELECT o FROM Otrospagos o WHERE o.interes = :interes"),
    @NamedQuery(name = "Otrospagos.findByTotal", query = "SELECT o FROM Otrospagos o WHERE o.total = :total"),
    @NamedQuery(name = "Otrospagos.findByNumfactura", query = "SELECT o FROM Otrospagos o WHERE o.numfactura = :numfactura"),
    @NamedQuery(name = "Otrospagos.findByFechapago", query = "SELECT o FROM Otrospagos o WHERE o.fechapago = :fechapago")})
public class Otrospagos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDOTPAGOS")
    private Integer idotpagos;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "DERCONX")
    private Float derconx;
    @Column(name = "MULRECX")
    private Float mulrecx;
    @Column(name = "INTERES")
    private Float interes;
    @Column(name = "TOTAL")
    private Float total;
    @Column(name = "NUMFACTURA")
    private Integer numfactura;
    @Lob
    @Column(name = "USUARIOACTUAL")
    private String usuarioactual;
    @Column(name = "FECHAPAGO")
    @Temporal(TemporalType.DATE)
    private Date fechapago;
    @JoinColumn(name = "IDCORTE", referencedColumnName = "IDCORTE")
    @ManyToOne
    private Corte idcorte;

    public Otrospagos() {
    }

    public Otrospagos(Integer idotpagos) {
        this.idotpagos = idotpagos;
    }

    public Integer getIdotpagos() {
        return idotpagos;
    }

    public void setIdotpagos(Integer idotpagos) {
        this.idotpagos = idotpagos;
    }

    public Float getDerconx() {
        return derconx;
    }

    public void setDerconx(Float derconx) {
        this.derconx = derconx;
    }

    public Float getMulrecx() {
        return mulrecx;
    }

    public void setMulrecx(Float mulrecx) {
        this.mulrecx = mulrecx;
    }

    public Float getInteres() {
        return interes;
    }

    public void setInteres(Float interes) {
        this.interes = interes;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    public Integer getNumfactura() {
        return numfactura;
    }

    public void setNumfactura(Integer numfactura) {
        this.numfactura = numfactura;
    }

    public String getUsuarioactual() {
        return usuarioactual;
    }

    public void setUsuarioactual(String usuarioactual) {
        this.usuarioactual = usuarioactual;
    }

    public Date getFechapago() {
        return fechapago;
    }

    public void setFechapago(Date fechapago) {
        this.fechapago = fechapago;
    }

    public Corte getIdcorte() {
        return idcorte;
    }

    public void setIdcorte(Corte idcorte) {
        this.idcorte = idcorte;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idotpagos != null ? idotpagos.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Otrospagos)) {
            return false;
        }
        Otrospagos other = (Otrospagos) object;
        if ((this.idotpagos == null && other.idotpagos != null) || (this.idotpagos != null && !this.idotpagos.equals(other.idotpagos))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Otrospagos[ idotpagos=" + idotpagos + " ]";
    }
    
}
