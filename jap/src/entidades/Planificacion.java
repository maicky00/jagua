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
import javax.persistence.Lob;
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
@Table(name = "planificacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Planificacion.findAll", query = "SELECT p FROM Planificacion p"),
    @NamedQuery(name = "Planificacion.findByIdplanificacion", query = "SELECT p FROM Planificacion p WHERE p.idplanificacion = :idplanificacion"),
    @NamedQuery(name = "Planificacion.findByTipoplanificacion", query = "SELECT p FROM Planificacion p WHERE p.tipoplanificacion = :tipoplanificacion"),
    @NamedQuery(name = "Planificacion.findByFecha", query = "SELECT p FROM Planificacion p WHERE p.fecha = :fecha"),
    @NamedQuery(name = "Planificacion.findByValormulta", query = "SELECT p FROM Planificacion p WHERE p.valormulta = :valormulta")})
public class Planificacion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDPLANIFICACION")
    private Integer idplanificacion;
    @Column(name = "TIPOPLANIFICACION")
    private String tipoplanificacion;
    @Lob
    @Column(name = "LUGAR")
    private String lugar;
    @Column(name = "FECHA")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "VALORMULTA")
    private Float valormulta;
    @Lob
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @OneToMany(mappedBy = "idplanificacion")
    private List<Asistencia> asistenciaList;

    public Planificacion() {
    }

    public Planificacion(Integer idplanificacion) {
        this.idplanificacion = idplanificacion;
    }

    public Integer getIdplanificacion() {
        return idplanificacion;
    }

    public void setIdplanificacion(Integer idplanificacion) {
        this.idplanificacion = idplanificacion;
    }

    public String getTipoplanificacion() {
        return tipoplanificacion;
    }

    public void setTipoplanificacion(String tipoplanificacion) {
        this.tipoplanificacion = tipoplanificacion;
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

    @XmlTransient
    public List<Asistencia> getAsistenciaList() {
        return asistenciaList;
    }

    public void setAsistenciaList(List<Asistencia> asistenciaList) {
        this.asistenciaList = asistenciaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idplanificacion != null ? idplanificacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Planificacion)) {
            return false;
        }
        Planificacion other = (Planificacion) object;
        if ((this.idplanificacion == null && other.idplanificacion != null) || (this.idplanificacion != null && !this.idplanificacion.equals(other.idplanificacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Planificacion[ idplanificacion=" + idplanificacion + " ]";
    }
    
}
