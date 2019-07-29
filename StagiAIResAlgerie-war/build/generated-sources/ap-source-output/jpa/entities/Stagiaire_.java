package jpa.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import jpa.entities.Stage;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-09-29T09:30:12")
@StaticMetamodel(Stagiaire.class)
public class Stagiaire_ { 

    public static volatile SingularAttribute<Stagiaire, String> lieunais;
    public static volatile SingularAttribute<Stagiaire, String> specialite;
    public static volatile SingularAttribute<Stagiaire, byte[]> photo;
    public static volatile SingularAttribute<Stagiaire, String> etablissement;
    public static volatile SingularAttribute<Stagiaire, Integer> id;
    public static volatile SingularAttribute<Stagiaire, String> numtel;
    public static volatile SingularAttribute<Stagiaire, Date> datenais;
    public static volatile SingularAttribute<Stagiaire, String> nom;
    public static volatile SingularAttribute<Stagiaire, String> prenom;
    public static volatile CollectionAttribute<Stagiaire, Stage> stageCollection;

}