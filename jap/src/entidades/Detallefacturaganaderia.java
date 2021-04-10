/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Tech-Usuario
 */
@Entity
@Table(name = "detallefacturaganaderia")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Detallefacturaganaderia.findAll", query = "SELECT d FROM Detallefacturaganaderia d"),
    @NamedQuery(name = "Detallefacturaganaderia.findByIddetallefacganaderia", query = "SELECT d FROM Detallefacturaganaderia d WHERE d.iddetallefacganaderia = :iddetallefacganaderia"),
    @NamedQuery(name = "Detallefacturaganaderia.findByAniomes", query = "SELECT d FROM Detallefacturaganaderia d WHERE d.aniomes = :aniomes"),
    @NamedQuery(name = "Detallefacturaganaderia.findBySubtotal", query = "SELECT d FROM Detallefacturaganaderia d WHERE d.subtotal = :subtotal"),
    @NamedQuery(name = "Detallefacturaganaderia.findByTotal", query = "SELECT d FROM Detallefacturaganaderia d WHERE d.total = :total"),
    @NamedQuery(name = "Detallefacturaganaderia.findByObservacion", query = "SELECT d FROM Detallefacturaganaderia d WHERE d.observacion = :observacion")})
public class Detallefacturaganaderia implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDDETALLEFACGANADERIA")
    private Integer iddetallefacganaderia;
    @Column(name = "ANIOMES")
    private String aniomes;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "SUBTOTAL")
    private Float subtotal;
    @Column(name = "TOTAL")
    private Float total;
    @Column(name = "OBSERVACION")
    private String observacion;
    @JoinColumn(name = "IDAGUAGANADERIA", referencedColumnName = "IDAGUAGANADERIA")
    @ManyToOne(optional = false)
    private Aguaganaderia idaguaganaderia;
    @JoinColumn(name = "IDTARIFASGANADERIA", referencedColumnName = "IDTARIFASGANADERIA")
    @ManyToOne(optional = false)
    private Tarifasganaderia idtarifasganaderia;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "iddetallefacganaderia")
    private Collection<Facturasganaderia> facturasganaderiaCollection;

    public Detallefacturaganaderia() {
    }

    public Detallefacturaganaderia(Integer iddetallefacganaderia) {
        this.iddetallefacganaderia = iddetallefacganaderia;
    }

    public Integer getIddetallefacganaderia() {
        return iddetallefacganaderia;
    }

    public void setIddetallefacganaderia(Integer iddetallefacganaderia) {
        this.iddetallefacganaderia = iddetallefacganaderia;
    }

    public String getAniomes() {
        return aniomes;
    }

    public void setAniomes(String aniomes) {
        this.aniomes = aniomes;
    }

    public Float getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Float subtotal) {
        this.subtotal = subtotal;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Aguaganaderia getIdaguaganaderia() {
        return idaguaganaderia;
    }

    public void setIdaguaganaderia(Aguaganaderia idaguaganaderia) {
        this.idaguaganaderia = idaguaganaderia;
    }

    public Tarifasganaderia getIdtarifasganaderia() {
        return idtarifasganaderia;
    }

    public void setIdtarifasganaderia(Tarifasganaderia idtarifasganaderia) {
        this.idtarifasganaderia = idtarifasganaderia;
    }

    @XmlTransient
    public Collection<Facturasganaderia> getFacturasganaderiaCollection() {
        return facturasganaderiaCollection;
    }

    public void setFacturasganaderiaCollection(Collection<Facturasganaderia> facturasganaderiaCollection) {
        this.facturasganaderiaCollection = facturasganaderiaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iddetallefacganaderia != null ? iddetallefacganaderia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Detallefacturaganaderia)) {
            return false;
        }
        Detallefacturaganaderia other = (Detallefacturaganaderia) object;
        if ((this.iddetallefacganaderia == null && other.iddetallefacganaderia != null) || (this.iddetallefacganaderia != null && !this.iddetallefacganaderia.equals(other.iddetallefacganaderia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Detallefacturaganaderia[ iddetallefacganaderia=" + iddetallefacganaderia + " ]";
    }
    
}
