package com.proyectoCFIP.entities;

import com.proyectoCFIP.entities.DiagnosticoMaquina;
import com.proyectoCFIP.entities.EstadoCronograma;
import com.proyectoCFIP.entities.MaquinaConfecciones;
import com.proyectoCFIP.entities.TipoMantenimiento;
import com.proyectoCFIP.entities.Usuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-11-09T14:16:32")
@StaticMetamodel(CronogramaMantenimientoMaquina.class)
public class CronogramaMantenimientoMaquina_ { 

    public static volatile SingularAttribute<CronogramaMantenimientoMaquina, String> descripcionValoracion;
    public static volatile SingularAttribute<CronogramaMantenimientoMaquina, Boolean> estado;
    public static volatile SingularAttribute<CronogramaMantenimientoMaquina, Usuario> idUsuario;
    public static volatile SingularAttribute<CronogramaMantenimientoMaquina, MaquinaConfecciones> idMaquinaConfecciones;
    public static volatile SingularAttribute<CronogramaMantenimientoMaquina, Integer> valoracion;
    public static volatile SingularAttribute<CronogramaMantenimientoMaquina, Date> fechaReporte;
    public static volatile SingularAttribute<CronogramaMantenimientoMaquina, Date> fechaInicioMantenimiento;
    public static volatile SingularAttribute<CronogramaMantenimientoMaquina, TipoMantenimiento> idTipoMantenimiento;
    public static volatile SingularAttribute<CronogramaMantenimientoMaquina, String> descripcionProblema;
    public static volatile SingularAttribute<CronogramaMantenimientoMaquina, Integer> idCronogramaMantenimientoMaquina;
    public static volatile SingularAttribute<CronogramaMantenimientoMaquina, Date> horaMantenimiento;
    public static volatile SingularAttribute<CronogramaMantenimientoMaquina, EstadoCronograma> idEstadoCronograma;
    public static volatile ListAttribute<CronogramaMantenimientoMaquina, DiagnosticoMaquina> diagnosticoMaquinaList;

}