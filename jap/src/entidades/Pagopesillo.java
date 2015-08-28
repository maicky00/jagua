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
 * @author Marco
 */
@Entity
@Table(name = "pagopesillo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pagopesillo.findAll", query = "SELECT p FROM Pagopesillo p"),
    @NamedQuery(name = "Pagopesillo.findByIdpagopesillo", query = "SELECT p FROM Pagopesillo p WHERE p.idpagopesillo = :idpagopesillo"),
    @NamedQuery(name = "Pagopesillo.findByFechapago", query = "SELECT p FROM Pagopesillo p WHERE p.fechapago = :fechapago"),
    @NamedQuery(name = "Pagopesillo.findByNummingas", query = "SELECT p FROM Pagopesillo p WHERE p.nummingas = :nummingas"),
    @NamedQuery(name = "Pagopesillo.findByValormingas", query = "SELECT p FROM Pagopesillo p WHERE p.valormingas = :valormingas"),
    @NamedQuery(name = "Pagopesillo.findByObservacion", query = "SELECT p FROM Pagopesillo p WHERE p.observacion = :observacion"),
    @NamedQuery(name = "Pagopesillo.findByNumfactura", query = "SELECT p FROM Pagopesillo p WHERE p.numfactura = :numfactura")})
public class Pagopesillo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDPAGOPESILLO")
    private Integer idpagopesillo;
    @Column(name = "FECHAPAGO")
    @Temporal(TemporalType.DATE)
    private Date fechapago;
    @Column(name = "NUMMINGAS")
    private Integer nummingas;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "VALORMINGAS")
    private Float valormingas;
    @Column(name = "OBSERVACION")
    private String observacion;
    @Column(name = "NUMFACTURA")
    private Integer numfactura;
    @Lob
    @Column(name = "USUARIOACTUAL")
    private String usuarioactual;
    @JoinColumn(name = "IDASISTENCIAPESILLO", referencedColumnName = "IDASISTENCIAPESILLO")
    @ManyToOne
    private Asistenciapesillo idasistenciapesillo;

    public Pagopesillo() {
    }

    public Pagopesillo(Integer idpagopesillo) {
        this.idpagopesillo = idpagopesillo;
    }

    public Integer getIdpagopesillo() {
        return idpagopesillo;
    }

    public void setIdpagopesillo(Integer idpagopesillo) {
        this.idpagopesillo = idpagopesillo;
    }

    public Date getFechapago() {
        return fechapago;
    }

    public void setFechapago(Date fechapago) {
        this.fechapago = fechapago;
    }

    public Integer getNummingas() {
        return nummingas;
    }

    public void setNummingas(Integer nummingas) {
        this.nummingas = nummingas;
    }

    public Float getValormingas() {
        return valormingas;
    }

    public void setValormingas(Float valormingas) {
        this.valormingas = valormingas;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
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

    public Asistenciapesillo getIdasistenciapesillo() {
        return idasistenciapesillo;
    }

    public void setIdasistenciapesillo(Asistenciapesillo idasistenciapesillo) {
        this.idasistenciapesillo = idasistenciapesillo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idpagopesillo != null ? idpagopesillo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pagopesillo)) {
            return false;
        }
        Pagopesillo other = (Pagopesillo) object;
        if ((this.idpagopesillo == null && other.idpagopesillo != null) || (this.idpagopesillo != null && !this.idpagopesillo.equals(other.idpagopesillo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Pagopesillo[ idpagopesillo=" + idpagopesillo + " ]";
    }
    
}
