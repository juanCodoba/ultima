package com.proyectoCFIP.entities;

import com.proyectoCFIP.entities.CronogramaActividadesEdificios;
import com.proyectoCFIP.entities.CronogramaManteDisCon;
import com.proyectoCFIP.entities.CronogramaManteDispositivo;
import com.proyectoCFIP.entities.CronogramaMantenimientos;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-11-26T10:36:43")
@StaticMetamodel(TipoJornada.class)
public class TipoJornada_ { 

    public static volatile ListAttribute<TipoJornada, CronogramaManteDispositivo> cronogramaManteDispositivoList;
    public static volatile SingularAttribute<TipoJornada, String> correo;
    public static volatile ListAttribute<TipoJornada, CronogramaManteDisCon> cronogramaManteDisConList;
    public static volatile ListAttribute<TipoJornada, CronogramaMantenimientos> cronogramaMantenimientosList;
    public static volatile ListAttribute<TipoJornada, CronogramaActividadesEdificios> cronogramaActividadesEdificiosList;
    public static volatile SingularAttribute<TipoJornada, String> nombre;
    public static volatile SingularAttribute<TipoJornada, Integer> idTipoJornada;

}