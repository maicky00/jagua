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
@Table(name = "mingas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Mingas.findAll", query = "SELECT m FROM Mingas m"),
    @NamedQuery(name = "Mingas.findByIdminga", query = "SELECT m FROM Mingas m WHERE m.idminga = :idminga"),
    @NamedQuery(name = "Mingas.findByFecha", query = "SELECT m FROM Mingas m WHERE m.fecha = :fecha"),
    @NamedQuery(name = "Mingas.findByAsistencia", query = "SELECT m FROM Mingas m WHERE m.asistencia = :asistencia"),
    @NamedQuery(name = "Mingas.findByValormulta", query = "SELECT m FROM Mingas m WHERE m.valormulta = :valormulta")})
public class Mingas implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDMINGA")
    private Integer idminga;
    @Lob
    @Column(name = "LUGAR")
    private String lugar;
    @Column(name = "FECHA")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Column(name = "ASISTENCIA")
    private String asistencia;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "VALORMULTA")
    private Float valormulta;
    @OneToMany(mappedBy = "idminga")
    private List<Pagosmingas> pagosmingasList;
    @JoinColumn(name = "IDMEDIDOR", referencedColumnName = "IDMEDIDOR")
    @ManyToOne
    private Medidor idmedidor;

    public Mingas() {
    }

    public Mingas(Integer idminga) {
        this.idminga = idminga;
    }

    public Integer getIdminga() {
        return idminga;
    }

    public void setIdminga(Integer idminga) {
        this.idminga = idminga;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getAsistencia() {
        return asistencia;
    }

    public void setAsistencia(String asistencia) {
        this.asistencia = asistencia;
    }

    public Float getValormulta() {
        return valormulta;
    }

    public void setValormulta(Float valormulta) {
        this.valormulta = valormulta;
    }

    @XmlTransient
    public List<Pagosmingas> getPagosmingasList() {
        return pagosmingasList;
    }

    public void setPagosmingasList(List<Pagosmingas> pagosmingasList) {
        this.pagosmingasList = pagosmingasList;
    }

    public Medidor getIdmedidor() {
        return idmedidor;
    }

    public void setIdmedidor(Medidor idmedidor) {
        this.idmedidor = idmedidor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idminga != null ? idminga.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Mingas)) {
            return false;
        }
        Mingas other = (Mingas) object;
        if ((this.idminga == null && other.idminga != null) || (this.idminga != null && !this.idminga.equals(other.idminga))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Mingas[ idminga=" + idminga + " ]";
    }
    
}
