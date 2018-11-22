package com.proyectoCFIP.entities;

import com.proyectoCFIP.entities.EstadoSolicitudEdificio;
import com.proyectoCFIP.entities.EstadoSolicitudRefrigerio;
import com.proyectoCFIP.entities.RespuestaRefrigerio;
import com.proyectoCFIP.entities.Seccion;
import com.proyectoCFIP.entities.Usuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-11-09T14:16:32")
@StaticMetamodel(SolicitudEdificio.class)
public class SolicitudEdificio_ { 

    public static volatile SingularAttribute<SolicitudEdificio, String> descripcion;
    public static volatile SingularAttribute<SolicitudEdificio, Date> fechaSolicitud;
    public static volatile SingularAttribute<SolicitudEdificio, EstadoSolicitudEdificio> idEstadoSolicitudEdificio;
    public static volatile SingularAttribute<SolicitudEdificio, Usuario> idUsuario;
    public static volatile SingularAttribute<SolicitudEdificio, Boolean> refrigerio;
    public static volatile SingularAttribute<SolicitudEdificio, Boolean> estadoRefrigerio;
    public static volatile ListAttribute<SolicitudEdificio, RespuestaRefrigerio> respuestaRefrigerioList;
    public static volatile SingularAttribute<SolicitudEdificio, Integer> idSolicitudEdificio;
    public static volatile SingularAttribute<SolicitudEdificio, Date> horaInicioAlquiler;
    public static volatile SingularAttribute<SolicitudEdificio, Seccion> idSeccion;
    public static volatile SingularAttribute<SolicitudEdificio, Date> fechaFinAlquiler;
    public static volatile SingularAttribute<SolicitudEdificio, Date> horaFinAlquiler;
    public static volatile SingularAttribute<SolicitudEdificio, EstadoSolicitudRefrigerio> idEstadoSolicitudRefrigerio;
    public static volatile SingularAttribute<SolicitudEdificio, Date> fechaAlquiler;
    public static volatile SingularAttribute<SolicitudEdificio, String> descRefrigerio;

}