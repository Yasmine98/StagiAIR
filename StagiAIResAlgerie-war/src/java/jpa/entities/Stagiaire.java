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
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
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
@Table(name = "stagiaire")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Stagiaire.findAll", query = "SELECT s FROM Stagiaire s")
    , @NamedQuery(name = "Stagiaire.findById", query = "SELECT s FROM Stagiaire s WHERE s.id = :id")
    , @NamedQuery(name = "Stagiaire.findByDatenais", query = "SELECT s FROM Stagiaire s WHERE s.datenais = :datenais")
    , @NamedQuery(name = "Stagiaire.findByLieunais", query = "SELECT s FROM Stagiaire s WHERE s.lieunais = :lieunais")
    , @NamedQuery(name = "Stagiaire.findByEtablissement", query = "SELECT s FROM Stagiaire s WHERE s.etablissement = :etablissement")
    , @NamedQuery(name = "Stagiaire.findByNumtel", query = "SELECT s FROM Stagiaire s WHERE s.numtel = :numtel")
    , @NamedQuery(name = "Stagiaire.findByNom", query = "SELECT s FROM Stagiaire s WHERE s.nom = :nom")
    , @NamedQuery(name = "Stagiaire.findByPrenom", query = "SELECT s FROM Stagiaire s WHERE s.prenom = :prenom")
    , @NamedQuery(name = "Stagiaire.findBySpecialite", query = "SELECT s FROM Stagiaire s WHERE s.specialite = :specialite")})
public class Stagiaire implements Serializable {

    @Lob
    @Column(name = "photo")
    private byte[] photo;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "datenais")
    @Temporal(TemporalType.DATE)
    private Date datenais;
    @Basic(optional = false)
    @Column(name = "lieunais")
    private String lieunais;
    @Basic(optional = false)
    @Column(name = "etablissement")
    private String etablissement;
    @Basic(optional = false)
    @Column(name = "numtel")
    private String numtel;
    @Basic(optional = false)
    @Column(name = "nom")
    private String nom;
    @Basic(optional = false)
    @Column(name = "prenom")
    private String prenom;
    @Basic(optional = false)
    @Column(name = "specialite")
    private String specialite;
    @ManyToMany(mappedBy = "stagiaireCollection",fetch=EAGER)
    private Collection<Stage> stageCollection;

    public Stagiaire() {
    }

    public Stagiaire(Integer id) {
        this.id = id;
    }

    public Stagiaire(Integer id, Date datenais, String lieunais, String etablissement, String numtel, String nom, String prenom, String specialite) {
        this.id = id;
        this.datenais = datenais;
        this.lieunais = lieunais;
        this.etablissement = etablissement;
        this.numtel = numtel;
        this.nom = nom;
        this.prenom = prenom;
        this.specialite = specialite;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDatenais() {
        return datenais;
    }

    public void setDatenais(Date datenais) {
        this.datenais = datenais;
    }

    public String getLieunais() {
        return lieunais;
    }

    public void setLieunais(String lieunais) {
        this.lieunais = lieunais;
    }

    public String getEtablissement() {
        return etablissement;
    }

    public void setEtablissement(String etablissement) {
        this.etablissement = etablissement;
    }

    public String getNumtel() {
        return numtel;
    }

    public void setNumtel(String numtel) {
        this.numtel = numtel;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }


    @XmlTransient
    public Collection<Stage> getStageCollection() {
        return stageCollection;
    }

    public void setStageCollection(Collection<Stage> stageCollection) {
        this.stageCollection = stageCollection;
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
        if (!(object instanceof Stagiaire)) {
            return false;
        }
        Stagiaire other = (Stagiaire) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.entities.Stagiaire[ id=" + id + " ]";
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }
    
}
