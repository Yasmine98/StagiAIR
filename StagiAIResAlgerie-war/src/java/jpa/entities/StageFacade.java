/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.entities;

import java.util.Collection;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author katia
 */
@Stateless
public class StageFacade extends AbstractFacade<Stage> {

    @PersistenceContext(unitName = "StagiAIResAlgerie-warPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public StageFacade() {
        super(Stage.class);
    }
    public void assignStagiaireToStage(java.lang.Integer stagiaireID, java.lang.Integer stageId) {
    try {
        Stage oStage = em.find(Stage.class, stageId);
        Stagiaire oStagiaire = em.find(Stagiaire.class, stagiaireID);    
        Collection<Stagiaire> lstStagiaire = oStage.getStagiaireCollection();
        Collection<Stage>  lstStage = oStagiaire.getStageCollection();
        if (!lstStage.contains(oStage)) {
                lstStagiaire.add(oStagiaire);
            lstStage.add(oStage);

            oStage.setStagiaireCollection(lstStagiaire);
                oStagiaire.setStageCollection(lstStage);
            em.merge(oStage);
            System.out.println("EHHH");
            em.getEntityManagerFactory().getCache().evictAll();
        } else {
            System.out.println("Entry Already Found");
        }


    } catch (Exception e) {
        System.out.println("Error :- " + e.getMessage());
    }
    }
    
public void removeStagiaireStage(Integer StagaireId, Integer subjectId) {
    try {
        Stage oStage = em.find(Stage.class, subjectId);
        Stagiaire oStagiaire = em.find(Stagiaire.class, StagaireId);

        Collection<Stage> lstStage = oStagiaire.getStageCollection();
        Collection<Stagiaire> lsStagiaire = oStage.getStagiaireCollection();

        lstStage.remove(oStage);
        lsStagiaire.remove(oStagiaire);

        em.merge(oStage);
    } catch (Exception e) {
        System.out.println("Error :- " + e.getMessage());
    }
}

    public void assignEncadreurToStage(java.lang.Integer encadreurID, java.lang.Integer stageId) {
    try {
        Stage oStage = em.find(Stage.class, stageId);
       Employe oEncadreur = em.find(Employe.class, encadreurID);    
        Collection<Employe> lstEncadreur = oStage.getEmployeCollection();
        Collection<Stage>  lstStage = oEncadreur.getStageCollection();
        if (!lstStage.contains(oStage)) {
                lstEncadreur.add(oEncadreur);
            lstStage.add(oStage);

            oStage.setEmployeCollection(lstEncadreur);
                oEncadreur.setStageCollection(lstStage);
            em.merge(oStage);
            System.out.println("EHHH");
            em.getEntityManagerFactory().getCache().evictAll();
        } else {
            System.out.println("Entry Already Found");
        }


    } catch (Exception e) {
        System.out.println("Error :- " + e.getMessage());
    }
    }
    
public void removeEncadreurStage(Integer EncadreurId, Integer subjectId) {
    try {
        Stage oStage = em.find(Stage.class, subjectId);
        Employe oEncadreur = em.find(Employe.class, EncadreurId);

        Collection<Stage> lstStage = oEncadreur.getStageCollection();
        Collection<Employe> lsEncadreur = oStage.getEmployeCollection();

        lstStage.remove(oStage);
        lsEncadreur.remove(oEncadreur);

        em.merge(oStage);
    } catch (Exception e) {
        System.out.println("Error :- " + e.getMessage());
    }
}


}
