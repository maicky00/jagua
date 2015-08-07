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
@Table(name = "medidor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Medidor.findAll", query = "SELECT m FROM Medidor m"),
    @NamedQuery(name = "Medidor.findByIdmedidor", query = "SELECT m FROM Medidor m WHERE m.idmedidor = :idmedidor"),
    @NamedQuery(name = "Medidor.findBySerie", query = "SELECT m FROM Medidor m WHERE m.serie = :serie"),
    @NamedQuery(name = "Medidor.findByNummedidor", query = "SELECT m FROM Medidor m WHERE m.nummedidor = :nummedidor"),
    @NamedQuery(name = "Medidor.findByEstado", query = "SELECT m FROM Medidor m WHERE m.estado = :estado")})
public class Medidor implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDMEDIDOR")
    private Integer idmedidor;
    @Column(name = "SERIE")
    private String serie;
    @Column(name = "NUMMEDIDOR")
    private Integer nummedidor;
    @Column(name = "ESTADO")
    private String estado;
    @OneToMany(mappedBy = "idmedidor")
    private List<Corte> corteList;
    @OneToMany(mappedBy = "idmedidor")
    private List<Asistencia> asistenciaList;
    @OneToMany(mappedBy = "idmedidor")
    private List<Detallefactura> detallefacturaList;
    @JoinColumn(name = "IDUSUARIO", referencedColumnName = "IDUSUARIO")
    @ManyToOne
    private Usuarios idusuario;

    public Medidor() {
    }

    public Medidor(Integer idmedidor) {
        this.idmedidor = idmedidor;
    }

    public Integer getIdmedidor() {
        return idmedidor;
    }

    public void setIdmedidor(Integer idmedidor) {
        this.idmedidor = idmedidor;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public Integer getNummedidor() {
        return nummedidor;
    }

    public void setNummedidor(Integer nummedidor) {
        this.nummedidor = nummedidor;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @XmlTransient
    public List<Corte> getCorteList() {
        return corteList;
    }

    public void setCorteList(List<Corte> corteList) {
        this.corteList = corteList;
    }

    @XmlTransient
    public List<Asistencia> getAsistenciaList() {
        return asistenciaList;
    }

    public void setAsistenciaList(List<Asistencia> asistenciaList) {
        this.asistenciaList = asistenciaList;
    }

    @XmlTransient
    public List<Detallefactura> getDetallefacturaList() {
        return detallefacturaList;
    }

    public void setDetallefacturaList(List<Detallefactura> detallefacturaList) {
        this.detallefacturaList = detallefacturaList;
    }

    public Usuarios getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(Usuarios idusuario) {
        this.idusuario = idusuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idmedidor != null ? idmedidor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Medidor)) {
            return false;
        }
        Medidor other = (Medidor) object;
        if ((this.idmedidor == null && other.idmedidor != null) || (this.idmedidor != null && !this.idmedidor.equals(other.idmedidor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Medidor[ idmedidor=" + idmedidor + " ]";
    }
    
}
