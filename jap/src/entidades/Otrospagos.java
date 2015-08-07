/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
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
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author JC-PC
 */
@Entity
@Table(name = "otrospagos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Otrospagos.findAll", query = "SELECT o FROM Otrospagos o"),
    @NamedQuery(name = "Otrospagos.findByIdotpagos", query = "SELECT o FROM Otrospagos o WHERE o.idotpagos = :idotpagos"),
    @NamedQuery(name = "Otrospagos.findByMulrecx", query = "SELECT o FROM Otrospagos o WHERE o.mulrecx = :mulrecx"),
    @NamedQuery(name = "Otrospagos.findByMultms", query = "SELECT o FROM Otrospagos o WHERE o.multms = :multms"),
    @NamedQuery(name = "Otrospagos.findByInteres", query = "SELECT o FROM Otrospagos o WHERE o.interes = :interes"),
    
    
    //@NamedQuery(name = "Otrospagos.findUsuarios", query = "SELECT o FROM Otrospagos o WHERE o.interes = :interes"),
    //@NamedQuery(name = "Otrospagos.findUsuario", query = "SELECT u.primernombre FROM usuarios u, medidor m,corte c,otrospagos o where u.idusuario=m.idusuario and m.idMedidor=c.idmedidor and c.idcorte=o.idcorte;"),
    //@NamedQuery(name = "Usuario.findByUsuario", query = "SELECT u FROM Usuario u WHERE u.usuario = :usuario and u.clave = :clave"),
    
    
    @NamedQuery(name = "Otrospagos.findBySerie", query = "SELECT o FROM Otrospagos o WHERE o.serie = :serie")})
public class Otrospagos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDOTPAGOS")
    private Integer idotpagos;
    @Lob
    @Column(name = "DERCONX")
    private String derconx;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "MULRECX")
    private Float mulrecx;
    @Column(name = "MULTMS")
    private Float multms;
    @Column(name = "INTERES")
    private Float interes;
    @Column(name = "SERIE")
    private String serie;
    @JoinColumn(name = "IDCORTE", referencedColumnName = "IDCORTE")
    @ManyToOne
    private Corte idcorte;

    public Otrospagos() {
    }

    public Otrospagos(Integer idotpagos) {
        this.idotpagos = idotpagos;
    }

    public Integer getIdotpagos() {
        return idotpagos;
    }

    public void setIdotpagos(Integer idotpagos) {
        this.idotpagos = idotpagos;
    }

    public String getDerconx() {
        return derconx;
    }

    public void setDerconx(String derconx) {
        this.derconx = derconx;
    }

    public Float getMulrecx() {
        return mulrecx;
    }

    public void setMulrecx(Float mulrecx) {
        this.mulrecx = mulrecx;
    }

    public Float getMultms() {
        return multms;
    }

    public void setMultms(Float multms) {
        this.multms = multms;
    }

    public Float getInteres() {
        return interes;
    }

    public void setInteres(Float interes) {
        this.interes = interes;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public Corte getIdcorte() {
        return idcorte;
    }

    public void setIdcorte(Corte idcorte) {
        this.idcorte = idcorte;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idotpagos != null ? idotpagos.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Otrospagos)) {
            return false;
        }
        Otrospagos other = (Otrospagos) object;
        if ((this.idotpagos == null && other.idotpagos != null) || (this.idotpagos != null && !this.idotpagos.equals(other.idotpagos))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Otrospagos[ idotpagos=" + idotpagos + " ]";
    }
    
    
    
}
