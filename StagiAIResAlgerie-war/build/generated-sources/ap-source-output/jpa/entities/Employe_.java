package jpa.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import jpa.entities.Service;
import jpa.entities.Stage;
import jpa.entities.Theme;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-09-29T09:30:12")
@StaticMetamodel(Employe.class)
public class Employe_ { 

    public static volatile SingularAttribute<Employe, Service> idservice;
    public static volatile SingularAttribute<Employe, byte[]> photo;
    public static volatile CollectionAttribute<Employe, Theme> themeCollection;
    public static volatile SingularAttribute<Employe, Integer> id;
    public static volatile SingularAttribute<Employe, String> poste;
    public static volatile SingularAttribute<Employe, String> numtel;
    public static volatile SingularAttribute<Employe, String> nom;
    public static volatile SingularAttribute<Employe, String> prenom;
    public static volatile CollectionAttribute<Employe, Stage> stageCollection;

}