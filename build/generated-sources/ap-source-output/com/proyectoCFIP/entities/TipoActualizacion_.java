package com.proyectoCFIP.entities;

import com.proyectoCFIP.entities.HistorialComputador;
import com.proyectoCFIP.entities.HistorialOtroDispositivo;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-11-26T10:36:43")
@StaticMetamodel(TipoActualizacion.class)
public class TipoActualizacion_ { 

    public static volatile ListAttribute<TipoActualizacion, HistorialOtroDispositivo> historialOtroDispositivoList;
    public static volatile SingularAttribute<TipoActualizacion, Integer> idTipoActualizacion;
    public static volatile SingularAttribute<TipoActualizacion, String> nombreTipoActualizacion;
    public static volatile ListAttribute<TipoActualizacion, HistorialComputador> historialComputadorList;

}