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
@Table(name = "detallefacturasobrante")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Detallefacturasobrante.findAll", query = "SELECT d FROM Detallefacturasobrante d"),
    @NamedQuery(name = "Detallefacturasobrante.findByIddetallefacsobrante", query = "SELECT d FROM Detallefacturasobrante d WHERE d.iddetallefacsobrante = :iddetallefacsobrante"),
    @NamedQuery(name = "Detallefacturasobrante.findByAniomes", query = "SELECT d FROM Detallefacturasobrante d WHERE d.aniomes = :aniomes"),
    @NamedQuery(name = "Detallefacturasobrante.findBySubtotal", query = "SELECT d FROM Detallefacturasobrante d WHERE d.subtotal = :subtotal"),
    @NamedQuery(name = "Detallefacturasobrante.findByTotal", query = "SELECT d FROM Detallefacturasobrante d WHERE d.total = :total"),
    @NamedQuery(name = "Detallefacturasobrante.findByObservacion", query = "SELECT d FROM Detallefacturasobrante d WHERE d.observacion = :observacion")})
public class Detallefacturasobrante implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDDETALLEFACSOBRANTE")
    private Integer iddetallefacsobrante;
    @Column(name = "ANIOMES")
    private String aniomes;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "SUBTOTAL")
    private Float subtotal;
    @Column(name = "TOTAL")
    private Float total;
    @Column(name = "OBSERVACION")
    private String observacion;
    @JoinColumn(name = "IDAGUASOBRANTE", referencedColumnName = "IDAGUASOBRANTE")
    @ManyToOne(optional = false)
    private Aguasobrante idaguasobrante;
    @JoinColumn(name = "IDTARIFASSOBRANTE", referencedColumnName = "IDTARIFASSOBRANTE")
    @ManyToOne(optional = false)
    private Tarifassobrante idtarifassobrante;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "iddetallefacsobrante")
    private Collection<Facturassobrante> facturassobranteCollection;

    public Detallefacturasobrante() {
    }

    public Detallefacturasobrante(Integer iddetallefacsobrante) {
        this.iddetallefacsobrante = iddetallefacsobrante;
    }

    public Integer getIddetallefacsobrante() {
        return iddetallefacsobrante;
    }

    public void setIddetallefacsobrante(Integer iddetallefacsobrante) {
        this.iddetallefacsobrante = iddetallefacsobrante;
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

    public Aguasobrante getIdaguasobrante() {
        return idaguasobrante;
    }

    public void setIdaguasobrante(Aguasobrante idaguasobrante) {
        this.idaguasobrante = idaguasobrante;
    }

    public Tarifassobrante getIdtarifassobrante() {
        return idtarifassobrante;
    }

    public void setIdtarifassobrante(Tarifassobrante idtarifassobrante) {
        this.idtarifassobrante = idtarifassobrante;
    }

    @XmlTransient
    public Collection<Facturassobrante> getFacturassobranteCollection() {
        return facturassobranteCollection;
    }

    public void setFacturassobranteCollection(Collection<Facturassobrante> facturassobranteCollection) {
        this.facturassobranteCollection = facturassobranteCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iddetallefacsobrante != null ? iddetallefacsobrante.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Detallefacturasobrante)) {
            return false;
        }
        Detallefacturasobrante other = (Detallefacturasobrante) object;
        if ((this.iddetallefacsobrante == null && other.iddetallefacsobrante != null) || (this.iddetallefacsobrante != null && !this.iddetallefacsobrante.equals(other.iddetallefacsobrante))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Detallefacturasobrante[ iddetallefacsobrante=" + iddetallefacsobrante + " ]";
    }
    
}
