package com.proyectoCFIP.entities;

import com.proyectoCFIP.entities.DiagnosticoManteDispositivo;
import com.proyectoCFIP.entities.OtroDispositivo;
import com.proyectoCFIP.entities.TipoJornada;
import com.proyectoCFIP.entities.TipoMantenimiento;
import com.proyectoCFIP.entities.Usuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-11-09T14:16:32")
@StaticMetamodel(CronogramaManteDispositivo.class)
public class CronogramaManteDispositivo_ { 

    public static volatile SingularAttribute<CronogramaManteDispositivo, Date> fechaInicioMantenimientoDis;
    public static volatile SingularAttribute<CronogramaManteDispositivo, String> correoUsuarioReporte;
    public static volatile SingularAttribute<CronogramaManteDispositivo, Date> horaMantenimientoDis;
    public static volatile ListAttribute<CronogramaManteDispositivo, DiagnosticoManteDispositivo> diagnosticoManteDispositivoList;
    public static volatile SingularAttribute<CronogramaManteDispositivo, Boolean> estadoMantenimientoDis;
    public static volatile SingularAttribute<CronogramaManteDispositivo, String> nombreUsuarioReporte;
    public static volatile SingularAttribute<CronogramaManteDispositivo, Usuario> idUsuario;
    public static volatile SingularAttribute<CronogramaManteDispositivo, TipoMantenimiento> idTipoMantenimiento;
    public static volatile SingularAttribute<CronogramaManteDispositivo, String> descripcionProblemaDis;
    public static volatile SingularAttribute<CronogramaManteDispositivo, Integer> idCronogramaManteDispositivo;
    public static volatile SingularAttribute<CronogramaManteDispositivo, Date> fechaProgMantenimientoDis;
    public static volatile SingularAttribute<CronogramaManteDispositivo, OtroDispositivo> idOtroDispositivo;
    public static volatile SingularAttribute<CronogramaManteDispositivo, TipoJornada> idTipoJornada;

}