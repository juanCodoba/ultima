package com.proyectoCFIP.entities;

import com.proyectoCFIP.entities.CalidadPlanAccion;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-11-09T14:16:32")
@StaticMetamodel(CalidadCausa.class)
public class CalidadCausa_ { 

    public static volatile SingularAttribute<CalidadCausa, String> descripcion;
    public static volatile ListAttribute<CalidadCausa, CalidadPlanAccion> calidadPlanAccionList;
    public static volatile SingularAttribute<CalidadCausa, Integer> idCausa;

}