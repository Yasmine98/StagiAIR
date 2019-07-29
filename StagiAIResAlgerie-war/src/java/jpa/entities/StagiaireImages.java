/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.entities;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.inject.Named;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author toshiba
 */
@Named // Or @ManagedBean
@ApplicationScoped
public class StagiaireImages {

    @EJB
    private Stagiaire service;

    public StreamedContent getImage() throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();

        if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
            // So, we're rendering the HTML. Return a stub StreamedContent so that it will generate right URL.
            return new DefaultStreamedContent();
        }
        else {
            // So, browser is requesting the image. Return a real StreamedContent with the image bytes.
            String stagiaireId = context.getExternalContext().getRequestParameterMap().get("stagiaireId");
           // Stagiaire stagiaire = stagiaireService.find(Long.valueOf(stagiaireId));
            return null;
        }
    }

}
