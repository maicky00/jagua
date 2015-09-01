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
import javax.persistence.Lob;
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
@Table(name = "corte")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Corte.findAll", query = "SELECT c FROM Corte c"),
    @NamedQuery(name = "Corte.findByIdcorte", query = "SELECT c FROM Corte c WHERE c.idcorte = :idcorte"),
    @NamedQuery(name = "Corte.findByCorte", query = "SELECT c FROM Corte c WHERE c.corte = :corte"),
    @NamedQuery(name = "Corte.findByFecha", query = "SELECT c FROM Corte c WHERE c.fecha = :fecha"),
    @NamedQuery(name = "Corte.findByMulta", query = "SELECT c FROM Corte c WHERE c.multa = :multa"),
    @NamedQuery(name = "Corte.findByMora", query = "SELECT c FROM Corte c WHERE c.mora = :mora")})
public class Corte implements Serializable {
    @Column(name = "PAGADO")
    private String pagado;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDCORTE")
    private Integer idcorte;
    @Column(name = "CORTE")
    private String corte;
    @Column(name = "FECHA")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Lob
    @Column(name = "OBSERVACION")
    private String observacion;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "MULTA")
    private Float multa;
    @Column(name = "MORA")
    private Integer mora;
    @JoinColumn(name = "IDMEDIDOR", referencedColumnName = "IDMEDIDOR")
    @ManyToOne
    private Medidor idmedidor;
    @OneToMany(mappedBy = "idcorte")
    private List<Otrospagos> otrospagosList;

    public Corte() {
    }

    public Corte(Integer idcorte) {
        this.idcorte = idcorte;
    }

    public Integer getIdcorte() {
        return idcorte;
    }

    public void setIdcorte(Integer idcorte) {
        this.idcorte = idcorte;
    }

    public String getCorte() {
        return corte;
    }

    public void setCorte(String corte) {
        this.corte = corte;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Float getMulta() {
        return multa;
    }

    public void setMulta(Float multa) {
        this.multa = multa;
    }

    public Integer getMora() {
        return mora;
    }

    public void setMora(Integer mora) {
        this.mora = mora;
    }

    public Medidor getIdmedidor() {
        return idmedidor;
    }

    public void setIdmedidor(Medidor idmedidor) {
        this.idmedidor = idmedidor;
    }

    @XmlTransient
    public List<Otrospagos> getOtrospagosList() {
        return otrospagosList;
    }

    public void setOtrospagosList(List<Otrospagos> otrospagosList) {
        this.otrospagosList = otrospagosList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcorte != null ? idcorte.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Corte)) {
            return false;
        }
        Corte other = (Corte) object;
        if ((this.idcorte == null && other.idcorte != null) || (this.idcorte != null && !this.idcorte.equals(other.idcorte))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Corte[ idcorte=" + idcorte + " ]";
    }

    public String getPagado() {
        return pagado;
    }

    public void setPagado(String pagado) {
        this.pagado = pagado;
    }
    
}
