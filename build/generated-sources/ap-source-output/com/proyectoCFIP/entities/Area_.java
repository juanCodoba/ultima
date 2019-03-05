package com.proyectoCFIP.entities;

import com.proyectoCFIP.entities.Bloque;
import com.proyectoCFIP.entities.Seccion;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-11-26T10:36:43")
@StaticMetamodel(Area.class)
public class Area_ { 

    public static volatile SingularAttribute<Area, String> nombreArea;
    public static volatile ListAttribute<Area, Bloque> bloqueList;
    public static volatile SingularAttribute<Area, Integer> idArea;
    public static volatile ListAttribute<Area, Seccion> seccionList;

}