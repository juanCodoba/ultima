package com.proyectoCFIP.entities;

import com.proyectoCFIP.entities.Novedad;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-06-26T10:01:24")
@StaticMetamodel(ActividadNovedad.class)
public class ActividadNovedad_ { 

    public static volatile SingularAttribute<ActividadNovedad, Date> fechaActual;
    public static volatile SingularAttribute<ActividadNovedad, String> descripcion;
    public static volatile SingularAttribute<ActividadNovedad, String> validacionIngenieria;
    public static volatile SingularAttribute<ActividadNovedad, Date> fechaCorrecion;
    public static volatile SingularAttribute<ActividadNovedad, Integer> idActividadNovedad;
    public static volatile SingularAttribute<ActividadNovedad, String> estadoNoConformeDiseño;
    public static volatile SingularAttribute<ActividadNovedad, String> verificacionCalidad;
    public static volatile SingularAttribute<ActividadNovedad, Novedad> idNovedad;

}