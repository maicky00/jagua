/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Marco
 */
@Entity
@Table(name = "asistenciapesillo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Asistenciapesillo.findAll", query = "SELECT a FROM Asistenciapesillo a"),
    @NamedQuery(name = "Asistenciapesillo.findByIdasistenciapesillo", query = "SELECT a FROM Asistenciapesillo a WHERE a.idasistenciapesillo = :idasistenciapesillo"),
    @NamedQuery(name = "Asistenciapesillo.findByAsistencia", query = "SELECT a FROM Asistenciapesillo a WHERE a.asistencia = :asistencia"),
    @NamedQuery(name = "Asistenciapesillo.findByValormulta", query = "SELECT a FROM Asistenciapesillo a WHERE a.valormulta = :valormulta"),
    @NamedQuery(name = "Asistenciapesillo.findByDescripcion", query = "SELECT a FROM Asistenciapesillo a WHERE a.descripcion = :descripcion"),
    @NamedQuery(name = "Asistenciapesillo.findByObservacion", query = "SELECT a FROM Asistenciapesillo a WHERE a.observacion = :observacion")})
public class Asistenciapesillo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDASISTENCIAPESILLO")
    private Integer idasistenciapesillo;
    @Column(name = "ASISTENCIA")
    private String asistencia;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "VALORMULTA")
    private Float valormulta;
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Column(name = "OBSERVACION")
    private String observacion;
    @JoinColumn(name = "IDMEDIDOR", referencedColumnName = "IDMEDIDOR")
    @ManyToOne
    private Medidor idmedidor;
    @JoinColumn(name = "IDPLANIFICACIONPESILLO", referencedColumnName = "IDPLANIFICACIONPESILLO")
    @ManyToOne
    private Planificacionpesillo idplanificacionpesillo;
    @OneToMany(mappedBy = "idasistenciapesillo")
    private List<Pagopesillo> pagopesilloList;

    public Asistenciapesillo() {
    }

    public Asistenciapesillo(Integer idasistenciapesillo) {
        this.idasistenciapesillo = idasistenciapesillo;
    }

    public Integer getIdasistenciapesillo() {
        return idasistenciapesillo;
    }

    public void setIdasistenciapesillo(Integer idasistenciapesillo) {
        this.idasistenciapesillo = idasistenciapesillo;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Medidor getIdmedidor() {
        return idmedidor;
    }

    public void setIdmedidor(Medidor idmedidor) {
        this.idmedidor = idmedidor;
    }

    public Planificacionpesillo getIdplanificacionpesillo() {
        return idplanificacionpesillo;
    }

    public void setIdplanificacionpesillo(Planificacionpesillo idplanificacionpesillo) {
        this.idplanificacionpesillo = idplanificacionpesillo;
    }

    @XmlTransient
    public List<Pagopesillo> getPagopesilloList() {
        return pagopesilloList;
    }

    public void setPagopesilloList(List<Pagopesillo> pagopesilloList) {
        this.pagopesilloList = pagopesilloList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idasistenciapesillo != null ? idasistenciapesillo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Asistenciapesillo)) {
            return false;
        }
        Asistenciapesillo other = (Asistenciapesillo) object;
        if ((this.idasistenciapesillo == null && other.idasistenciapesillo != null) || (this.idasistenciapesillo != null && !this.idasistenciapesillo.equals(other.idasistenciapesillo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Asistenciapesillo[ idasistenciapesillo=" + idasistenciapesillo + " ]";
    }
    
}
