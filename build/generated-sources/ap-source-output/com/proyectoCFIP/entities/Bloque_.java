package com.proyectoCFIP.entities;

import com.proyectoCFIP.entities.Area;
import com.proyectoCFIP.entities.Seccion;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-11-26T10:36:43")
@StaticMetamodel(Bloque.class)
public class Bloque_ { 

    public static volatile SingularAttribute<Bloque, String> nombreBloque;
    public static volatile SingularAttribute<Bloque, Integer> idBloque;
    public static volatile ListAttribute<Bloque, Area> areaList;
    public static volatile ListAttribute<Bloque, Seccion> seccionList;

}