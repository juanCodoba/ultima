package com.proyectoCFIP.entities;

import com.proyectoCFIP.entities.CronogramaMantenimientoMaquina;
import com.proyectoCFIP.entities.CronogramaMantenimientos;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-11-26T10:36:43")
@StaticMetamodel(EstadoCronograma.class)
public class EstadoCronograma_ { 

    public static volatile ListAttribute<EstadoCronograma, CronogramaMantenimientoMaquina> cronogramaMantenimientoMaquinaList;
    public static volatile SingularAttribute<EstadoCronograma, Integer> idEstado;
    public static volatile SingularAttribute<EstadoCronograma, String> nombreEstado;
    public static volatile ListAttribute<EstadoCronograma, CronogramaMantenimientos> cronogramaMantenimientosList;

}