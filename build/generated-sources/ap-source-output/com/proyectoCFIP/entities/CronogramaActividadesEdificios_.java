package com.proyectoCFIP.entities;

import com.proyectoCFIP.entities.DiagnosticoActividadEdificios;
import com.proyectoCFIP.entities.EstadoActividad;
import com.proyectoCFIP.entities.Seccion;
import com.proyectoCFIP.entities.TipoActividad;
import com.proyectoCFIP.entities.TipoJornada;
import com.proyectoCFIP.entities.Usuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-11-09T14:16:32")
@StaticMetamodel(CronogramaActividadesEdificios.class)
public class CronogramaActividadesEdificios_ { 

    public static volatile SingularAttribute<CronogramaActividadesEdificios, String> descripcion;
    public static volatile SingularAttribute<CronogramaActividadesEdificios, String> correoUsuarioReporte;
    public static volatile SingularAttribute<CronogramaActividadesEdificios, String> descripcionValoracion;
    public static volatile SingularAttribute<CronogramaActividadesEdificios, Boolean> estado;
    public static volatile SingularAttribute<CronogramaActividadesEdificios, Usuario> idUsuario;
    public static volatile SingularAttribute<CronogramaActividadesEdificios, String> nombreUsuarioReporte;
    public static volatile SingularAttribute<CronogramaActividadesEdificios, TipoActividad> idTipoActividad;
    public static volatile SingularAttribute<CronogramaActividadesEdificios, byte[]> imagen;
    public static volatile SingularAttribute<CronogramaActividadesEdificios, Integer> valoracion;
    public static volatile SingularAttribute<CronogramaActividadesEdificios, Date> fechaReporte;
    public static volatile SingularAttribute<CronogramaActividadesEdificios, Integer> idCronogramaActividadesEdificios;
    public static volatile SingularAttribute<CronogramaActividadesEdificios, EstadoActividad> idEstadoActividad;
    public static volatile SingularAttribute<CronogramaActividadesEdificios, Date> horaReporte;
    public static volatile SingularAttribute<CronogramaActividadesEdificios, Date> fechaValoracion;
    public static volatile SingularAttribute<CronogramaActividadesEdificios, Seccion> idSeccion;
    public static volatile ListAttribute<CronogramaActividadesEdificios, DiagnosticoActividadEdificios> diagnosticoActividadEdificiosList;
    public static volatile SingularAttribute<CronogramaActividadesEdificios, TipoJornada> idTipoJornada;

}