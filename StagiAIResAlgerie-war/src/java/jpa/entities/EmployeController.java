package jpa.entities;

import static com.sun.javafx.logging.PulseLogger.addMessage;
import java.awt.event.ActionEvent;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import jpa.entities.util.JsfUtil;
import jpa.entities.util.JsfUtil.PersistAction;

import java.io.Serializable;
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
import org.primefaces.event.TabChangeEvent;
import org.primefaces.event.TabCloseEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

@Named("employeController")
@SessionScoped
public class EmployeController implements Serializable {

    @EJB
    private jpa.entities.EmployeFacade ejbFacade;
    private List<Employe> items = null;
    private Employe selected;

    public EmployeController() {
    }

    public Employe getSelected() {
        return selected;
    }

    public void setSelected(Employe selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private EmployeFacade getFacade() {
        return ejbFacade;
    }

    public Employe prepareCreate() {
        selected = new Employe();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/resources/Bundle").getString("EmployeCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/resources/Bundle").getString("EmployeUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/resources/Bundle").getString("EmployeDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Employe> getItems() {
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
                    if(this.output!=null) this.selected.setPhoto(this.output);
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

    public Employe getEmploye(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Employe> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Employe> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Employe.class)
    public static class EmployeControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            EmployeController controller = (EmployeController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "employeController");
            return controller.getEmploye(getKey(value));
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
            if (object instanceof Employe) {
                Employe o = (Employe) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Employe.class.getName()});
                return null;
            }
        }

    }
    
        private final String destination="D:\\Photos\\";
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
    public void copyFile(String fileName, InputStream in) {
           try {
              
              
                // write the inputStream to a FileOutputStream
                OutputStream out = new FileOutputStream(new File(destination + fileName));
              byte[] bytes;
                int read = 0;
                bytes = new byte[1024];
             output = IOUtils.toByteArray(in);
                while ((read = in.read(bytes)) != -1) {
                    out.write(bytes, 0, read); }
                in.close();
                out.flush();
                out.close();
              
                System.out.println("New file created!");
                } catch (IOException e) {
                System.out.println(e.getMessage());
                }
    }
     

public void onTabChange(TabChangeEvent event) {
       this.selected = (Employe) event.getData();
         byte[] bytes = this.selected.getPhoto();
        System.out.println(this.selected.getId()+" ontabchange");
        
    }
         
    public void onTabClose(TabCloseEvent event) {
        
    }
     public void buttonAction(ActionEvent actionEvent) {
        addMessage("Welcome to Primefaces!!");
        System.out.println("HELLO");
    }
 
public StreamedContent getImageDisplay(Employe item) {
		StreamedContent image = new DefaultStreamedContent();
		/*String indexCategorie = FacesContext.getCurrentInstance()
				.getExternalContext().getRequestParameterMap()
				.get("indexCategorie");*/
                System.out.println(item.getNom() + " index");
		if (item!= null) {
			Employe employe = getFacade().find(item.getId());
                        System.out.println(employe.getNom()+"   image display nom");
			image = new DefaultStreamedContent(new ByteArrayInputStream(employe.getPhoto()));
                        System.out.println(employe.getPrenom()+"   image display prÃ©nom");

		}
		return image;
	}
    private DefaultStreamedContent content ;

    public StreamedContent getContent()
    {
        if(content == null)
        {
            /* use your database call here */
            BufferedInputStream in = new BufferedInputStream(EmployeController.class.getClassLoader().getResourceAsStream("D\\A.png"));
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

            byte[] bytes = this.selected.getPhoto();
            System.out.println("Bytes -> " + bytes.length);
            content = new DefaultStreamedContent(new ByteArrayInputStream(bytes), "E/image/png", "test.png");
        }
        return content;


}
}
