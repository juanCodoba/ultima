package com.proyectoCFIP.entities;

import com.proyectoCFIP.entities.CronogramaManteDispositivo;
import com.proyectoCFIP.entities.CronogramaMantenimientoMaquina;
import com.proyectoCFIP.entities.CronogramaMantenimientos;
import com.proyectoCFIP.entities.Libro;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-11-26T10:36:43")
@StaticMetamodel(TipoMantenimiento.class)
public class TipoMantenimiento_ { 

    public static volatile ListAttribute<TipoMantenimiento, CronogramaMantenimientoMaquina> cronogramaMantenimientoMaquinaList;
    public static volatile ListAttribute<TipoMantenimiento, CronogramaManteDispositivo> cronogramaManteDispositivoList;
    public static volatile SingularAttribute<TipoMantenimiento, Integer> idTipoMantenimiento;
    public static volatile ListAttribute<TipoMantenimiento, Libro> libroList;
    public static volatile SingularAttribute<TipoMantenimiento, String> nombreTipoMantenimiento;
    public static volatile ListAttribute<TipoMantenimiento, CronogramaMantenimientos> cronogramaMantenimientosList;

}