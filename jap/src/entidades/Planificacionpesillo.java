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
 * @author Marco
 */
@Entity
@Table(name = "planificacionpesillo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Planificacionpesillo.findAll", query = "SELECT p FROM Planificacionpesillo p"),
    @NamedQuery(name = "Planificacionpesillo.findByIdplanificacionpesillo", query = "SELECT p FROM Planificacionpesillo p WHERE p.idplanificacionpesillo = :idplanificacionpesillo"),
    @NamedQuery(name = "Planificacionpesillo.findByLugar", query = "SELECT p FROM Planificacionpesillo p WHERE p.lugar = :lugar"),
    @NamedQuery(name = "Planificacionpesillo.findByFecha", query = "SELECT p FROM Planificacionpesillo p WHERE p.fecha = :fecha"),
    @NamedQuery(name = "Planificacionpesillo.findByValormulta", query = "SELECT p FROM Planificacionpesillo p WHERE p.valormulta = :valormulta")})
public class Planificacionpesillo implements Serializable {
    @Column(name = "TIPOPLANPESILLO")
    private String tipoplanpesillo;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDPLANIFICACIONPESILLO")
    private Integer idplanificacionpesillo;
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
    @OneToMany(mappedBy = "idplanificacionpesillo")
    private List<Asistenciapesillo> asistenciapesilloList;

    public Planificacionpesillo() {
    }

    public Planificacionpesillo(Integer idplanificacionpesillo) {
        this.idplanificacionpesillo = idplanificacionpesillo;
    }

    public Integer getIdplanificacionpesillo() {
        return idplanificacionpesillo;
    }

    public void setIdplanificacionpesillo(Integer idplanificacionpesillo) {
        this.idplanificacionpesillo = idplanificacionpesillo;
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
    public List<Asistenciapesillo> getAsistenciapesilloList() {
        return asistenciapesilloList;
    }

    public void setAsistenciapesilloList(List<Asistenciapesillo> asistenciapesilloList) {
        this.asistenciapesilloList = asistenciapesilloList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idplanificacionpesillo != null ? idplanificacionpesillo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Planificacionpesillo)) {
            return false;
        }
        Planificacionpesillo other = (Planificacionpesillo) object;
        if ((this.idplanificacionpesillo == null && other.idplanificacionpesillo != null) || (this.idplanificacionpesillo != null && !this.idplanificacionpesillo.equals(other.idplanificacionpesillo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Planificacionpesillo[ idplanificacionpesillo=" + idplanificacionpesillo + " ]";
    }

    public String getTipoplanpesillo() {
        return tipoplanpesillo;
    }

    public void setTipoplanpesillo(String tipoplanpesillo) {
        this.tipoplanpesillo = tipoplanpesillo;
    }
    
}
