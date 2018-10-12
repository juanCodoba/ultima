package com.proyectoCFIP.entities;

import com.proyectoCFIP.entities.SolicitudEdificio;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-08-30T14:13:28")
@StaticMetamodel(RespuestaRefrigerio.class)
public class RespuestaRefrigerio_ { 

    public static volatile SingularAttribute<RespuestaRefrigerio, String> descripcion;
    public static volatile SingularAttribute<RespuestaRefrigerio, Integer> idRespuestaRefrigerio;
    public static volatile SingularAttribute<RespuestaRefrigerio, Date> fechaRespuesta;
    public static volatile SingularAttribute<RespuestaRefrigerio, SolicitudEdificio> idSolicitudEdificio;

}