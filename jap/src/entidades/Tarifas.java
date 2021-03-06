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
@Table(name = "tarifas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tarifas.findAll", query = "SELECT t FROM Tarifas t"),
    @NamedQuery(name = "Tarifas.findByIdtarifas", query = "SELECT t FROM Tarifas t WHERE t.idtarifas = :idtarifas"),
    @NamedQuery(name = "Tarifas.findByBase", query = "SELECT t FROM Tarifas t WHERE t.base = :base"),
    @NamedQuery(name = "Tarifas.findByTarbase", query = "SELECT t FROM Tarifas t WHERE t.tarbase = :tarbase"),
    @NamedQuery(name = "Tarifas.findByValorexceso", query = "SELECT t FROM Tarifas t WHERE t.valorexceso = :valorexceso"),
    @NamedQuery(name = "Tarifas.findByAlcantarrillado", query = "SELECT t FROM Tarifas t WHERE t.alcantarrillado = :alcantarrillado"),
    @NamedQuery(name = "Tarifas.findByIva", query = "SELECT t FROM Tarifas t WHERE t.iva = :iva")})
public class Tarifas implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDTARIFAS")
    private Integer idtarifas;
    @Column(name = "BASE")
    private Integer base;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "TARBASE")
    private Float tarbase;
    @Lob
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Column(name = "VALOREXCESO")
    private Float valorexceso;
    @Column(name = "ALCANTARRILLADO")
    private Float alcantarrillado;
    @Column(name = "IVA")
    private Float iva;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idtarifas")
    private Collection<Detallefactura> detallefacturaCollection;

    public Tarifas() {
    }

    public Tarifas(Integer idtarifas) {
        this.idtarifas = idtarifas;
    }

    public Integer getIdtarifas() {
        return idtarifas;
    }

    public void setIdtarifas(Integer idtarifas) {
        this.idtarifas = idtarifas;
    }

    public Integer getBase() {
        return base;
    }

    public void setBase(Integer base) {
        this.base = base;
    }

    public Float getTarbase() {
        return tarbase;
    }

    public void setTarbase(Float tarbase) {
        this.tarbase = tarbase;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Float getValorexceso() {
        return valorexceso;
    }

    public void setValorexceso(Float valorexceso) {
        this.valorexceso = valorexceso;
    }

    public Float getAlcantarrillado() {
        return alcantarrillado;
    }

    public void setAlcantarrillado(Float alcantarrillado) {
        this.alcantarrillado = alcantarrillado;
    }

    public Float getIva() {
        return iva;
    }

    public void setIva(Float iva) {
        this.iva = iva;
    }

    @XmlTransient
    public Collection<Detallefactura> getDetallefacturaCollection() {
        return detallefacturaCollection;
    }

    public void setDetallefacturaCollection(Collection<Detallefactura> detallefacturaCollection) {
        this.detallefacturaCollection = detallefacturaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtarifas != null ? idtarifas.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tarifas)) {
            return false;
        }
        Tarifas other = (Tarifas) object;
        if ((this.idtarifas == null && other.idtarifas != null) || (this.idtarifas != null && !this.idtarifas.equals(other.idtarifas))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Tarifas[ idtarifas=" + idtarifas + " ]";
    }
    
}
