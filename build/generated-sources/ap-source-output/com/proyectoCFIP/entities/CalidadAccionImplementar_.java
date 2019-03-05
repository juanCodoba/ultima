package com.proyectoCFIP.entities;

import com.proyectoCFIP.entities.CalidadPlanAccion;
import com.proyectoCFIP.entities.CalidadSeguimientoAccion;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-11-26T10:36:43")
@StaticMetamodel(CalidadAccionImplementar.class)
public class CalidadAccionImplementar_ { 

    public static volatile SingularAttribute<CalidadAccionImplementar, String> descripcion;
    public static volatile SingularAttribute<CalidadAccionImplementar, Boolean> estadoAccion;
    public static volatile SingularAttribute<CalidadAccionImplementar, Integer> idAccionImplementar;
    public static volatile SingularAttribute<CalidadAccionImplementar, Date> fecha;
    public static volatile ListAttribute<CalidadAccionImplementar, CalidadPlanAccion> calidadPlanAccionList;
    public static volatile SingularAttribute<CalidadAccionImplementar, String> responsable;
    public static volatile ListAttribute<CalidadAccionImplementar, CalidadSeguimientoAccion> calidadSeguimientoAccionList;
    public static volatile SingularAttribute<CalidadAccionImplementar, String> observaciones;

}