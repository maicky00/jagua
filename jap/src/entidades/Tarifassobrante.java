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
@Table(name = "tarifassobrante")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tarifassobrante.findAll", query = "SELECT t FROM Tarifassobrante t"),
    @NamedQuery(name = "Tarifassobrante.findByIdtarifassobrante", query = "SELECT t FROM Tarifassobrante t WHERE t.idtarifassobrante = :idtarifassobrante"),
    @NamedQuery(name = "Tarifassobrante.findByTarifamensual", query = "SELECT t FROM Tarifassobrante t WHERE t.tarifamensual = :tarifamensual"),
    @NamedQuery(name = "Tarifassobrante.findByIva", query = "SELECT t FROM Tarifassobrante t WHERE t.iva = :iva")})
public class Tarifassobrante implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDTARIFASSOBRANTE")
    private Integer idtarifassobrante;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "TARIFAMENSUAL")
    private Float tarifamensual;
    @Lob
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Column(name = "IVA")
    private Float iva;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idtarifassobrante")
    private Collection<Detallefacturasobrante> detallefacturasobranteCollection;

    public Tarifassobrante() {
    }

    public Tarifassobrante(Integer idtarifassobrante) {
        this.idtarifassobrante = idtarifassobrante;
    }

    public Integer getIdtarifassobrante() {
        return idtarifassobrante;
    }

    public void setIdtarifassobrante(Integer idtarifassobrante) {
        this.idtarifassobrante = idtarifassobrante;
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
    public Collection<Detallefacturasobrante> getDetallefacturasobranteCollection() {
        return detallefacturasobranteCollection;
    }

    public void setDetallefacturasobranteCollection(Collection<Detallefacturasobrante> detallefacturasobranteCollection) {
        this.detallefacturasobranteCollection = detallefacturasobranteCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtarifassobrante != null ? idtarifassobrante.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tarifassobrante)) {
            return false;
        }
        Tarifassobrante other = (Tarifassobrante) object;
        if ((this.idtarifassobrante == null && other.idtarifassobrante != null) || (this.idtarifassobrante != null && !this.idtarifassobrante.equals(other.idtarifassobrante))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Tarifassobrante[ idtarifassobrante=" + idtarifassobrante + " ]";
    }
    
}
