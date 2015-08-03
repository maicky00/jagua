/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author JC-PC
 */
@Entity
@Table(name = "pagosmingas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pagosmingas.findAll", query = "SELECT p FROM Pagosmingas p"),
    @NamedQuery(name = "Pagosmingas.findByIdpagominga", query = "SELECT p FROM Pagosmingas p WHERE p.idpagominga = :idpagominga"),
    @NamedQuery(name = "Pagosmingas.findByFechapago", query = "SELECT p FROM Pagosmingas p WHERE p.fechapago = :fechapago"),
    @NamedQuery(name = "Pagosmingas.findByNummingas", query = "SELECT p FROM Pagosmingas p WHERE p.nummingas = :nummingas"),
    @NamedQuery(name = "Pagosmingas.findByValormingas", query = "SELECT p FROM Pagosmingas p WHERE p.valormingas = :valormingas")})
public class Pagosmingas implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDPAGOMINGA")
    private Integer idpagominga;
    @Column(name = "FECHAPAGO")
    @Temporal(TemporalType.DATE)
    private Date fechapago;
    @Column(name = "NUMMINGAS")
    private Integer nummingas;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "VALORMINGAS")
    private Float valormingas;
    @JoinColumn(name = "IDMINGA", referencedColumnName = "IDMINGA")
    @ManyToOne
    private Mingas idminga;
    @OneToMany(mappedBy = "idpagominga")
    private List<Detallefactura> detallefacturaList;

    public Pagosmingas() {
    }

    public Pagosmingas(Integer idpagominga) {
        this.idpagominga = idpagominga;
    }

    public Integer getIdpagominga() {
        return idpagominga;
    }

    public void setIdpagominga(Integer idpagominga) {
        this.idpagominga = idpagominga;
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

    public Mingas getIdminga() {
        return idminga;
    }

    public void setIdminga(Mingas idminga) {
        this.idminga = idminga;
    }

    @XmlTransient
    public List<Detallefactura> getDetallefacturaList() {
        return detallefacturaList;
    }

    public void setDetallefacturaList(List<Detallefactura> detallefacturaList) {
        this.detallefacturaList = detallefacturaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idpagominga != null ? idpagominga.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pagosmingas)) {
            return false;
        }
        Pagosmingas other = (Pagosmingas) object;
        if ((this.idpagominga == null && other.idpagominga != null) || (this.idpagominga != null && !this.idpagominga.equals(other.idpagominga))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Pagosmingas[ idpagominga=" + idpagominga + " ]";
    }
    
}
