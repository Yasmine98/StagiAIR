/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import static javax.persistence.FetchType.EAGER;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author katia
 */
@Entity
@Table(name = "stage")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Stage.findAll", query = "SELECT s FROM Stage s")
    , @NamedQuery(name = "Stage.findById", query = "SELECT s FROM Stage s WHERE s.id = :id")
    , @NamedQuery(name = "Stage.findByDatedeb", query = "SELECT s FROM Stage s WHERE s.datedeb = :datedeb")
    , @NamedQuery(name = "Stage.findByDatefin", query = "SELECT s FROM Stage s WHERE s.datefin = :datefin")})
public class Stage implements Serializable {

    @Lob
    @Column(name = "rapport")
    private byte[] rapport;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "datedeb")
    @Temporal(TemporalType.DATE)
    private Date datedeb;
    @Basic(optional = false)
    @Column(name = "datefin")
    @Temporal(TemporalType.DATE)
    private Date datefin;
    @ManyToMany(mappedBy = "stageCollection",fetch=EAGER)
    private Collection<Employe> employeCollection;
    @JoinTable(name = "stagiaireconcerne", joinColumns = {
        @JoinColumn(name = "idstage", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "idstagiaire", referencedColumnName = "id")})
    @ManyToMany(fetch= EAGER)
    private Collection<Stagiaire> stagiaireCollection;
    @JoinColumn(name = "idtheme", referencedColumnName = "id")
    @ManyToOne(optional = false,fetch=EAGER)
    private Theme idtheme;

    public Stage() {
    }

    public Stage(Integer id) {
        this.id = id;
    }

    public Stage(Integer id, Date datedeb, Date datefin) {
        this.id = id;
        this.datedeb = datedeb;
        this.datefin = datefin;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDatedeb() {
        return datedeb;
    }

    public void setDatedeb(Date datedeb) {
        this.datedeb = datedeb;
    }

    public Date getDatefin() {
        return datefin;
    }

    public void setDatefin(Date datefin) {
        this.datefin = datefin;
    }

    @XmlTransient
    public Collection<Employe> getEmployeCollection() {
        return employeCollection;
    }

    public void setEmployeCollection(Collection<Employe> employeCollection) {
        this.employeCollection = employeCollection;
    }

    @XmlTransient
    public Collection<Stagiaire> getStagiaireCollection() {
        return stagiaireCollection;
    }

    public void setStagiaireCollection(Collection<Stagiaire> stagiaireCollection) {
        this.stagiaireCollection = stagiaireCollection;
    }

    public Theme getIdtheme() {
        return idtheme;
    }

    public void setIdtheme(Theme idtheme) {
        this.idtheme = idtheme;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Stage)) {
            return false;
        }
        Stage other = (Stage) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.entities.Stage[ id=" + id + " ]";
    }

    public byte[] getRapport() {
        return rapport;
    }

    public void setRapport(byte[] rapport) {
        this.rapport = rapport;
    }
    
}
