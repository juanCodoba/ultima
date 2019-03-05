package com.proyectoCFIP.entities;

import com.proyectoCFIP.entities.CronogramaActividadesEdificios;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-11-26T10:36:43")
@StaticMetamodel(EstadoActividad.class)
public class EstadoActividad_ { 

    public static volatile SingularAttribute<EstadoActividad, Integer> idEstadoActividad;
    public static volatile ListAttribute<EstadoActividad, CronogramaActividadesEdificios> cronogramaActividadesEdificiosList;
    public static volatile SingularAttribute<EstadoActividad, String> nombre;

}