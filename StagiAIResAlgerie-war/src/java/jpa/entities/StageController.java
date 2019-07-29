package jpa.entities;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import jpa.entities.util.JsfUtil;
import jpa.entities.util.JsfUtil.PersistAction;

import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.apache.commons.io.IOUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

@Named("stageController")
@SessionScoped
public class StageController implements Serializable {

    @EJB
    private jpa.entities.StageFacade ejbFacade;
    private List<Stage> items = null;
    private Stage selected;
    private Stagiaire idstagiaire;
    private Employe idemploye;
    
    public StageController() {
    }

    public Stage getSelected() {
        return selected;
    }
    
     public Employe getIdemploye() {
        return idemploye;
    }
    
        public Stagiaire getIdstagiaire() {
        return idstagiaire;
    }
     public void setIdstagiaire(Stagiaire idstagiaire) {
        this.idstagiaire = idstagiaire;
    }
     
     public void setIdemploye(Employe idemploye) {
        this.idemploye = idemploye;
    }
     
    public void setSelected(Stage selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private StageFacade getFacade() {
        return ejbFacade;
    }

    public Stage prepareCreate() {
        selected = new Stage();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/resources/Bundle").getString("StageCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/resources/Bundle").getString("StageUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/resources/Bundle").getString("StageDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Stage> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    if(this.output!=null) this.selected.setRapport(this.output);
                    getFacade().edit(selected);
                    this.output=null;
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/resources/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/resources/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public Stage getStage(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Stage> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Stage> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Stage.class)
    public static class StageControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            StageController controller = (StageController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "stageController");
            return controller.getStage(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Stage) {
                Stage o = (Stage) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Stage.class.getName()});
                return null;
            }
        }

    }

    public void assignStagiaireToStage() {
            selected.getStagiaireCollection().size();
            items.size();
            selected.getEmployeCollection().size();
            System.out.println("stage "+selected.getId() +"stagiaire " + getIdstagiaire().getId() );
            getFacade().assignStagiaireToStage(getIdstagiaire().getId(), selected.getId());
         items = null;
         // Invalidate list of items to trigger re-query.
        
    }
    
public void removeStagiaireStage(){
      selected.getStagiaireCollection().size();
            items.size();
            selected.getEmployeCollection().size();
        getFacade().removeStagiaireStage(getIdstagiaire().getId(), selected.getId());
            items = null;
}
public void assignEncadreurToStage() {
      selected.getStagiaireCollection().size();
            items.size();
            selected.getEmployeCollection().size();
            System.out.println("stage "+selected.getId() +"stagiaire " + getIdemploye().getId() );
            getFacade().assignEncadreurToStage(getIdemploye().getId(), selected.getId());
             this.selected.setEmployeCollection(null);
            items = null;    // Invalidate list of items to trigger re-query.
        
    }
    
public void removeEncadreurStage(){
      selected.getStagiaireCollection().size();
            items.size();
            selected.getEmployeCollection().size();
        getFacade().removeEncadreurStage(getIdemploye().getId(), selected.getId());
         this.selected.setEmployeCollection(null);
            items = null;
}


 private String destination="D:\\Photos\\";
        private byte[] output=null;
    public void upload(FileUploadEvent event) {  
        FacesMessage msg = new FacesMessage("Success! ", event.getFile().getFileName() + " is uploaded.");  
        FacesContext.getCurrentInstance().addMessage(null, msg);
    
        // Do what you want with the file        
        try {
            copyFile(event.getFile().getFileName(), event.getFile().getInputstream());
        } catch (IOException e) {
            e.printStackTrace();
        }
 
    }  
        public void uploadFond(FileUploadEvent event) {  
        FacesMessage msg = new FacesMessage("Success! ", event.getFile().getFileName() + " is uploaded.");  
        FacesContext.getCurrentInstance().addMessage(null, msg);
    
        // Do what you want with the file        
        try {
            copyFond(event.getFile().getInputstream());
        } catch (IOException e) {
            e.printStackTrace();
        }
 
    }  
        public void uploadAuc() {  
        FacesMessage msg = new FacesMessage("Success! is uploaded.");  
        FacesContext.getCurrentInstance().addMessage(null, msg);
        System.out.println("AUCUN");
        // Do what you want with the file        
        
            copyAuc();
        
 
    }  
        public void uploadDef() throws IOException {  
      //  FacesMessage msg = new FacesMessage("Success! is uploaded.");  
       // FacesContext.getCurrentInstance().addMessage(null, msg);
       System.out.println("HELLOO");
       Path pathsrc;
        pathsrc = Paths.get("C:\\Users\\toshiba\\Desktop\\air algérie\\StagiAIResAlgerie\\StagiAIResAlgerie-war\\web\\resources\\images\\defaut.png");
       Path pathdest;
        pathdest = Paths.get("C:\\Users\\toshiba\\Desktop\\air algérie\\StagiAIResAlgerie\\StagiAIResAlgerie-war\\web\\resources\\images\\fonde.png");
     
        Files.copy(pathsrc, pathdest, StandardCopyOption.REPLACE_EXISTING);
        
 
    }  
    public void copyFile(String fileName, InputStream in) {
           try {
              
              
                // write the inputStream to a FileOutputStream
                OutputStream out = new FileOutputStream(new File(destination + fileName));
              byte[] bytes;
                int read = 0;
                bytes = new byte[1024];
             output = IOUtils.toByteArray(in);
                while ((read = in.read(bytes)) != -1) {
                    out.write(bytes, 0, read);
                };
                in.close();
                out.flush();
                out.close();
              
                System.out.println("New file created!");
                } catch (IOException e) {
                System.out.println(e.getMessage());
                }
    }
     public void copyFond( InputStream in) {
           try {
                 FacesContext facesContext = FacesContext.getCurrentInstance();
       // destination= facesContext.getExternalContext().getRequestContextPath() +"/fond.png";
                System.out.println(destination);destination="C:\\Users\\toshiba\\Desktop\\air algérie\\StagiAIResAlgerie\\StagiAIResAlgerie-war\\web\\resources\\images\\fonde.png";
                // write the inputStream to a FileOutputStream
                OutputStream out = new FileOutputStream(new File(destination));
                    byte[] bytes;
                int read = 0;
                bytes = new byte[1024];
             
                while ((read = in.read(bytes)) != -1) {
                    out.write(bytes, 0, read);
                };
                in.close();
               
                out.close();
              
                System.out.println("New file created!");
                destination="D:\\Photos\\";
                } catch (IOException e) {
                System.out.println(e.getMessage());
                }
    }
        public void copyAuc() {
           try {
                 FacesContext facesContext = FacesContext.getCurrentInstance();
       // destination= facesContext.getExternalContext().getRequestContextPath() +"/fond.png";
                System.out.println(destination);destination="C:\\Users\\toshiba\\Desktop\\air algérie\\StagiAIResAlgerie\\StagiAIResAlgerie-war\\web\\resources\\images\\fonde.png";
                // write the inputStream to a FileOutputStream
                OutputStream out = new FileOutputStream(new File(destination));
                    byte[] bytes;
                int read = 0;
                bytes = new byte[1024];
                out.flush();
               
                out.close();
              
                System.out.println("New file created!");
                destination="D:\\Photos\\";
                } catch (IOException e) {
                System.out.println(e.getMessage());
                }
    }
    public void copyDef(String fileName, InputStream in) {
           try {
              
               destination="C:\\Users\\toshiba\\Desktop\\air algérie\\StagiAIResAlgerie\\StagiAIResAlgerie-war\\web\\resources\\images\\fonde.png";
                // write the inputStream to a FileOutputStream
                OutputStream out = new FileOutputStream(new File(destination));
              byte[] bytes;
                int read = 0;
                bytes = new byte[1024];
             output = IOUtils.toByteArray(in);
                while ((read = in.read(bytes)) != -1) {
                    out.write(bytes, 0, read);
                };
                in.close();
               // out.flush();
                out.close();
              
                System.out.println("New file created!");
                } catch (IOException e) {
                System.out.println(e.getMessage());
                }
    } 
     
     
     
     
     
     
     
   
    /*****************displa
     * @return y**********************/
    public StreamedContent getImageDisplay(Stage item) {
		StreamedContent image = new DefaultStreamedContent();
		/*String indexCategorie = FacesContext.getCurrentInstance()
				.getExternalContext().getRequestParameterMap()
				.get("indexCategorie");*/
            //    System.out.println(item.getNom() + " index");
		if (item!= null) {
			Stage stage = getFacade().find(item.getId());
                     //   System.out.println(stagiaire.getNom()+"   image display nom");
			image = new DefaultStreamedContent(new ByteArrayInputStream(stage.getRapport()));
                        System.out.println(stage.getId()+"   image display prénom");

		}
		return image;
	}
    private DefaultStreamedContent content ;

    public StreamedContent getContent()
    {
        if(content == null)
        {
            /* use your database call here */
            BufferedInputStream in = new BufferedInputStream(StagiaireController.class.getClassLoader().getResourceAsStream("D\\A.png"));
            ByteArrayOutputStream out = new ByteArrayOutputStream();

            int val = -1;
            /* this is a simple test method to double check values from the stream */
            try
            {
                while((val = in.read()) != -1)
                    out.write(val);
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }

            byte[] bytes = this.selected.getRapport();
            System.out.println("Bytes -> " + bytes.length);
            content = new DefaultStreamedContent(new ByteArrayInputStream(bytes), "rapport/pdf", "test.pdf");
        }

        return content;
    }

}
