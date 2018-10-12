package com.proyectoCFIP.entities;

import com.proyectoCFIP.entities.CronogramaActividadesEdificios;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-08-30T14:13:28")
@StaticMetamodel(TipoActividad.class)
public class TipoActividad_ { 

    public static volatile SingularAttribute<TipoActividad, Integer> idTipoActividad;
    public static volatile ListAttribute<TipoActividad, CronogramaActividadesEdificios> cronogramaActividadesEdificiosList;
    public static volatile SingularAttribute<TipoActividad, String> nombre;

}