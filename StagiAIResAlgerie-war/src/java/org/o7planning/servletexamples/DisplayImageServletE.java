package org.o7planning.servletexamples;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
 
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jpa.entities.Employe;

import java.sql.DriverManager;
import java.util.List;
import javax.jms.Session;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import jpa.entities.StagiaireFacade;
// /E/image?id=123
@WebServlet(urlPatterns = { "/E/image" })
public class DisplayImageServletE extends HttpServlet {
  private static final long serialVersionUID = 1L;
 @PersistenceContext(unitName = "StagiAIResAlgerie-warPU")
 EntityManager em;
  public DisplayImageServletE() {
      super();
  }
 
  private Employe getImageInTable(Connection conn, Long id) throws SQLException {
     
      Employe employe = em.find(Employe.class, 8);
      System.out.println(employe.getNom());
      return employe;
      
  }
 
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
     
      try {
         // Get Database Connection.
         // (See more in JDBC Tutorial)
         /* Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gestionstage?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&autoReconnect=true&useSSL=false", "root", "root");
         */
         String para = request.getParameter("id");
          Long id = null;
          try {
              id = Long.parseLong(request.getParameter("id"));
          } catch (Exception e) {
              System.out.println("EXCEPTION BDD GGGGG");
 
          }
          System.out.println(id + " GGGGGGGGGGG");
          Employe employe = new Employe();
          Integer i = id != null ? id.intValue() : null;
          employe = em.find(Employe.class, i);
      System.out.println(employe.getNom());
          if (employe == null) {
              // No record found, redirect to default image.
              response.sendRedirect(request.getContextPath() + "/E/images/noimage.jpg");
              return;
          }
        
          // trump.jpg, putin.png
          String imageFileName = employe.getNom();
          System.out.println("File Name: "+ imageFileName);
        
          // image/jpg
          // image/png
          String contentType = this.getServletContext().getMimeType(imageFileName);
          System.out.println("Content Type: "+ contentType);
        System.out.println("Content-Length: " + String.valueOf(employe.getPhoto().length));
          response.setHeader("Content-Type", contentType);
        
          response.setHeader("Content-Length", String.valueOf(employe.getPhoto().length));
        
          response.setHeader("Content-Disposition", "inline; filename=\"" + employe.getNom() + "\"");
 
          // Write image data to Response.
          response.getOutputStream().write(employe.getPhoto());
 
      } catch (Exception e) {
          throw new ServletException(e);
      }
  }
 
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
      doGet(request, response);
  }
 

 


}





/*package jpa.entities;


 * @author Brendan Healey
 *
 * Originates from BalusC.
 *
 *
 
import java.io.Closeable;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jpa.entities.PhotoInterface;

@WebServlet(name = "ImageServlet", urlPatterns = {"/ImageServlet/*"})
public class ImageServlet extends HttpServlet {
 
 /*
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
 
        String classString = request.getParameter("class");
        String idString = request.getParameter("id");
 
        if (classString == null || classString.isEmpty() || idString == null || idString.isEmpty()) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404.
            return;
        }
 
        Long id = Long.parseLong(idString.trim());
 
        PhotoInterface entry = null;
         
        Class<?> c = null;
 
        try {
            c = Class.forName("uk.co.mycompany.entities." + classString.trim());
        } catch (Exception ex) {
            SQLog.log("Unknown class name: " + classString);
        }
 
        try {
            entry = (PhotoInterface) ejb.find(c, id);
        } catch (Exception ex) {
            SQLog.log(ex.getMessage());
        }
 
        if (entry == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404.
            return;
        }
 
        ServletOutputStream out = null;
 
        try {
            response.reset();
 
            // It works ok without setting any of these...
            //response.setContentType(image.getContentType());
            //response.setHeader("Content-Length", String.valueOf(image.getLength()));
            //response.setHeader("Content-Disposition", "inline; filename=\"" + image.getName() + "\"");
            //response.setContentType("image/bmp");
            //response.setContentType("image/x-jpeg");
 
            out = response.getOutputStream();
 
            if (entry.getPhoto() != null && entry.getPhoto().length != 0) {
                out.write(entry.getPhoto());
            }
        } catch (IOException e) {
            SQLog.log(e.getMessage());
        } finally {
            close(out);
        }
 
    }
 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
 
    // Helpers (can be refactored to public utility class) ----------------------------------------
    private static void close(Closeable resource) {
        if (resource != null) {
            try {
                resource.close();
            } catch (IOException e) {
                SQLog.log(e.getMessage());
            }
        }
    }
}*/