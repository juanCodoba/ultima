package com.proyectoCFIP.entities;

import com.proyectoCFIP.entities.SolicitudEdificio;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-11-09T14:16:32")
@StaticMetamodel(RespuestaSolicitud.class)
public class RespuestaSolicitud_ { 

    public static volatile SingularAttribute<RespuestaSolicitud, String> descripcion;
    public static volatile SingularAttribute<RespuestaSolicitud, Integer> idRespuestaSolicitud;
    public static volatile SingularAttribute<RespuestaSolicitud, Date> fechaRespuesta;
    public static volatile SingularAttribute<RespuestaSolicitud, SolicitudEdificio> idSolicitudEdificio;

}