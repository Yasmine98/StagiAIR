package jpa.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import jpa.entities.Employe;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-09-29T09:30:12")
@StaticMetamodel(Service.class)
public class Service_ { 

    public static volatile SingularAttribute<Service, Integer> id;
    public static volatile SingularAttribute<Service, String> nom;
    public static volatile CollectionAttribute<Service, Employe> employeCollection;

}