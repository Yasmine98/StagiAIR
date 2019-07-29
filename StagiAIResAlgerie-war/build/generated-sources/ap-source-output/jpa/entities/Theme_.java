package jpa.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import jpa.entities.Employe;
import jpa.entities.Stage;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-09-29T09:30:12")
@StaticMetamodel(Theme.class)
public class Theme_ { 

    public static volatile SingularAttribute<Theme, Employe> idemploye;
    public static volatile SingularAttribute<Theme, Integer> id;
    public static volatile SingularAttribute<Theme, String> nom;
    public static volatile CollectionAttribute<Theme, Stage> stageCollection;

}