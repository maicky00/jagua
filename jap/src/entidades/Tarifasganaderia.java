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
import javax.persistence.Lob;
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
@Table(name = "tarifasganaderia")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tarifasganaderia.findAll", query = "SELECT t FROM Tarifasganaderia t"),
    @NamedQuery(name = "Tarifasganaderia.findByIdtarifasganaderia", query = "SELECT t FROM Tarifasganaderia t WHERE t.idtarifasganaderia = :idtarifasganaderia"),
    @NamedQuery(name = "Tarifasganaderia.findByTarifamensual", query = "SELECT t FROM Tarifasganaderia t WHERE t.tarifamensual = :tarifamensual"),
    @NamedQuery(name = "Tarifasganaderia.findByIva", query = "SELECT t FROM Tarifasganaderia t WHERE t.iva = :iva")})
public class Tarifasganaderia implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDTARIFASGANADERIA")
    private Integer idtarifasganaderia;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "TARIFAMENSUAL")
    private Float tarifamensual;
    @Lob
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Column(name = "IVA")
    private Float iva;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idtarifasganaderia")
    private Collection<Detallefacturaganaderia> detallefacturaganaderiaCollection;

    public Tarifasganaderia() {
    }

    public Tarifasganaderia(Integer idtarifasganaderia) {
        this.idtarifasganaderia = idtarifasganaderia;
    }

    public Integer getIdtarifasganaderia() {
        return idtarifasganaderia;
    }

    public void setIdtarifasganaderia(Integer idtarifasganaderia) {
        this.idtarifasganaderia = idtarifasganaderia;
    }

    public Float getTarifamensual() {
        return tarifamensual;
    }

    public void setTarifamensual(Float tarifamensual) {
        this.tarifamensual = tarifamensual;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Float getIva() {
        return iva;
    }

    public void setIva(Float iva) {
        this.iva = iva;
    }

    @XmlTransient
    public Collection<Detallefacturaganaderia> getDetallefacturaganaderiaCollection() {
        return detallefacturaganaderiaCollection;
    }

    public void setDetallefacturaganaderiaCollection(Collection<Detallefacturaganaderia> detallefacturaganaderiaCollection) {
        this.detallefacturaganaderiaCollection = detallefacturaganaderiaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtarifasganaderia != null ? idtarifasganaderia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tarifasganaderia)) {
            return false;
        }
        Tarifasganaderia other = (Tarifasganaderia) object;
        if ((this.idtarifasganaderia == null && other.idtarifasganaderia != null) || (this.idtarifasganaderia != null && !this.idtarifasganaderia.equals(other.idtarifasganaderia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Tarifasganaderia[ idtarifasganaderia=" + idtarifasganaderia + " ]";
    }
    
}
