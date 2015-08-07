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
 * @author JC-PC
 */
@Entity
@Table(name = "asistencia")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Asistencia.findAll", query = "SELECT a FROM Asistencia a"),
    @NamedQuery(name = "Asistencia.findByIdasistencia", query = "SELECT a FROM Asistencia a WHERE a.idasistencia = :idasistencia"),
    @NamedQuery(name = "Asistencia.findByAsistencia", query = "SELECT a FROM Asistencia a WHERE a.asistencia = :asistencia"),
    @NamedQuery(name = "Asistencia.findByValormulta", query = "SELECT a FROM Asistencia a WHERE a.valormulta = :valormulta"),
    @NamedQuery(name = "Asistencia.findByDescripcion", query = "SELECT a FROM Asistencia a WHERE a.descripcion = :descripcion")})
public class Asistencia implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDASISTENCIA")
    private Integer idasistencia;
    @Column(name = "ASISTENCIA")
    private String asistencia;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "VALORMULTA")
    private Float valormulta;
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @JoinColumn(name = "IDMEDIDOR", referencedColumnName = "IDMEDIDOR")
    @ManyToOne
    private Medidor idmedidor;
    @JoinColumn(name = "IDPLANIFICACION", referencedColumnName = "IDPLANIFICACION")
    @ManyToOne
    private Planificacion idplanificacion;
    @OneToMany(mappedBy = "idasistencia")
    private List<Pagosasistencia> pagosasistenciaList;

    public Asistencia() {
    }

    public Asistencia(Integer idasistencia) {
        this.idasistencia = idasistencia;
    }

    public Integer getIdasistencia() {
        return idasistencia;
    }

    public void setIdasistencia(Integer idasistencia) {
        this.idasistencia = idasistencia;
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

    public Medidor getIdmedidor() {
        return idmedidor;
    }

    public void setIdmedidor(Medidor idmedidor) {
        this.idmedidor = idmedidor;
    }

    public Planificacion getIdplanificacion() {
        return idplanificacion;
    }

    public void setIdplanificacion(Planificacion idplanificacion) {
        this.idplanificacion = idplanificacion;
    }

    @XmlTransient
    public List<Pagosasistencia> getPagosasistenciaList() {
        return pagosasistenciaList;
    }

    public void setPagosasistenciaList(List<Pagosasistencia> pagosasistenciaList) {
        this.pagosasistenciaList = pagosasistenciaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idasistencia != null ? idasistencia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Asistencia)) {
            return false;
        }
        Asistencia other = (Asistencia) object;
        if ((this.idasistencia == null && other.idasistencia != null) || (this.idasistencia != null && !this.idasistencia.equals(other.idasistencia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Asistencia[ idasistencia=" + idasistencia + " ]";
    }
    
}
