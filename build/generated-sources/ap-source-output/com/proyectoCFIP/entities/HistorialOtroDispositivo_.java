package com.proyectoCFIP.entities;

import com.proyectoCFIP.entities.OtroDispositivo;
import com.proyectoCFIP.entities.TipoActualizacion;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-08-30T14:13:28")
@StaticMetamodel(HistorialOtroDispositivo.class)
public class HistorialOtroDispositivo_ { 

    public static volatile SingularAttribute<HistorialOtroDispositivo, String> descripcionActualizacion;
    public static volatile SingularAttribute<HistorialOtroDispositivo, String> seccion;
    public static volatile SingularAttribute<HistorialOtroDispositivo, Date> fechaActualizacion;
    public static volatile SingularAttribute<HistorialOtroDispositivo, TipoActualizacion> idTipoActualizacion;
    public static volatile SingularAttribute<HistorialOtroDispositivo, String> usuario;
    public static volatile SingularAttribute<HistorialOtroDispositivo, Integer> idHistorialOtroDispositivo;
    public static volatile SingularAttribute<HistorialOtroDispositivo, OtroDispositivo> idOtroDispositivo;

}