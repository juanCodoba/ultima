package com.proyectoCFIP.entities;

import com.proyectoCFIP.entities.Computador;
import com.proyectoCFIP.entities.EstadoCronograma;
import com.proyectoCFIP.entities.TipoJornada;
import com.proyectoCFIP.entities.TipoMantenimiento;
import com.proyectoCFIP.entities.Usuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-11-09T14:16:32")
@StaticMetamodel(CronogramaMantenimientos.class)
public class CronogramaMantenimientos_ { 

    public static volatile SingularAttribute<CronogramaMantenimientos, String> correoUsuarioReporte;
    public static volatile SingularAttribute<CronogramaMantenimientos, String> descripcionValoracion;
    public static volatile SingularAttribute<CronogramaMantenimientos, Boolean> estadoReporte;
    public static volatile SingularAttribute<CronogramaMantenimientos, Date> fechaProgMantenimiento;
    public static volatile SingularAttribute<CronogramaMantenimientos, Integer> valoracionReporte;
    public static volatile SingularAttribute<CronogramaMantenimientos, byte[]> imagenMantenimiento;
    public static volatile SingularAttribute<CronogramaMantenimientos, Computador> idComputador;
    public static volatile SingularAttribute<CronogramaMantenimientos, String> nombreUsuarioReporte;
    public static volatile SingularAttribute<CronogramaMantenimientos, Usuario> idUsuario;
    public static volatile SingularAttribute<CronogramaMantenimientos, EstadoCronograma> estadoMantenimiento;
    public static volatile SingularAttribute<CronogramaMantenimientos, Date> fechaDiagnostico;
    public static volatile SingularAttribute<CronogramaMantenimientos, Date> fechaInicioMantenimiento;
    public static volatile SingularAttribute<CronogramaMantenimientos, TipoMantenimiento> idTipoMantenimiento;
    public static volatile SingularAttribute<CronogramaMantenimientos, String> descripcionProblema;
    public static volatile SingularAttribute<CronogramaMantenimientos, Integer> idCronogramaMantenimientos;
    public static volatile SingularAttribute<CronogramaMantenimientos, Date> fechaValoracion;
    public static volatile SingularAttribute<CronogramaMantenimientos, Date> horaMantenimiento;
    public static volatile SingularAttribute<CronogramaMantenimientos, String> telefono;
    public static volatile SingularAttribute<CronogramaMantenimientos, TipoJornada> idTipoJornada;

}