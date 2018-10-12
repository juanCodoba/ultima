package com.proyectoCFIP.entities;

import com.proyectoCFIP.entities.DiagnosticoMantenimientoDisCon;
import com.proyectoCFIP.entities.DispositivoConectividad;
import com.proyectoCFIP.entities.TipoJornada;
import com.proyectoCFIP.entities.TipoMantenimiento;
import com.proyectoCFIP.entities.Usuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-08-30T14:13:28")
@StaticMetamodel(CronogramaManteDisCon.class)
public class CronogramaManteDisCon_ { 

    public static volatile SingularAttribute<CronogramaManteDisCon, String> correoUsuarioReporte;
    public static volatile SingularAttribute<CronogramaManteDisCon, String> nombreUsuarioReporte;
    public static volatile SingularAttribute<CronogramaManteDisCon, Usuario> idUsuario;
    public static volatile SingularAttribute<CronogramaManteDisCon, Boolean> estadoMantenimientoDisCon;
    public static volatile SingularAttribute<CronogramaManteDisCon, Integer> idCronogramaManteDisCon;
    public static volatile SingularAttribute<CronogramaManteDisCon, Date> fechaProgMantenimientoDisCon;
    public static volatile SingularAttribute<CronogramaManteDisCon, DispositivoConectividad> idDispositivoConectividad;
    public static volatile SingularAttribute<CronogramaManteDisCon, TipoMantenimiento> idTipoMantenimiento;
    public static volatile SingularAttribute<CronogramaManteDisCon, String> descripcionProblemaDisCon;
    public static volatile SingularAttribute<CronogramaManteDisCon, Date> horaMantenimientoDisCon;
    public static volatile SingularAttribute<CronogramaManteDisCon, Date> fechaInicioMantenimientoDisCon;
    public static volatile ListAttribute<CronogramaManteDisCon, DiagnosticoMantenimientoDisCon> diagnosticoMantenimientoDisConList;
    public static volatile SingularAttribute<CronogramaManteDisCon, TipoJornada> idTipoJornada;

}