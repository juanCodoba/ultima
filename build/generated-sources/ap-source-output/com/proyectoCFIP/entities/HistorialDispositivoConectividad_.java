package com.proyectoCFIP.entities;

import com.proyectoCFIP.entities.DispositivoConectividad;
import com.proyectoCFIP.entities.TipoActualizacion;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-08-30T14:13:28")
@StaticMetamodel(HistorialDispositivoConectividad.class)
public class HistorialDispositivoConectividad_ { 

    public static volatile SingularAttribute<HistorialDispositivoConectividad, Integer> idHistorialDispositivoConectividad;
    public static volatile SingularAttribute<HistorialDispositivoConectividad, String> descripcionActualizacion;
    public static volatile SingularAttribute<HistorialDispositivoConectividad, String> seccion;
    public static volatile SingularAttribute<HistorialDispositivoConectividad, DispositivoConectividad> idDispositivoConectividad;
    public static volatile SingularAttribute<HistorialDispositivoConectividad, Date> fechaActualizacion;
    public static volatile SingularAttribute<HistorialDispositivoConectividad, TipoActualizacion> idTipoActualizacion;
    public static volatile SingularAttribute<HistorialDispositivoConectividad, String> usuario;

}