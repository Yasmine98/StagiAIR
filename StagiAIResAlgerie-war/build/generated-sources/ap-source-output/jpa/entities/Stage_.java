package jpa.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import jpa.entities.Employe;
import jpa.entities.Stagiaire;
import jpa.entities.Theme;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-09-29T09:30:12")
@StaticMetamodel(Stage.class)
public class Stage_ { 

    public static volatile SingularAttribute<Stage, Date> datedeb;
    public static volatile SingularAttribute<Stage, Theme> idtheme;
    public static volatile SingularAttribute<Stage, byte[]> rapport;
    public static volatile SingularAttribute<Stage, Date> datefin;
    public static volatile SingularAttribute<Stage, Integer> id;
    public static volatile CollectionAttribute<Stage, Employe> employeCollection;
    public static volatile CollectionAttribute<Stage, Stagiaire> stagiaireCollection;

}