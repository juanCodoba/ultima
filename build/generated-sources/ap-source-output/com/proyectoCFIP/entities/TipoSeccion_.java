package com.proyectoCFIP.entities;

import com.proyectoCFIP.entities.Seccion;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-11-26T10:36:43")
@StaticMetamodel(TipoSeccion.class)
public class TipoSeccion_ { 

    public static volatile SingularAttribute<TipoSeccion, Integer> idTipoSeccion;
    public static volatile SingularAttribute<TipoSeccion, String> nombre;
    public static volatile ListAttribute<TipoSeccion, Seccion> seccionList;

}